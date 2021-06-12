package com.surinder.bijliestimator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import static java.lang.Math.ceil;
import static java.lang.String.format;

public class LoadExtension extends AppCompatActivity {
    private EditText clo, cln;
    private Button est;
    private TextView unit,unit3;
    private TextView ln11;
    private TextView ln111;
    private TextView ln21;
    private TextView ln01;
    private TextView ln32,ln31;
    private TextView ln42,ln41;
    private TextView ln51;TextView ln52;
    private TextView ln61;TextView ln62;
    private TextView ln71;TextView ln72;
    private TextView ln82;
    private TextView ln91;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_extension);
        Spinner tvsp2 = findViewById(R.id.spn_lan);
        unit= findViewById(R.id.spn_cl);
        unit3= findViewById(R.id.cd_unit);
        clo = findViewById(R.id.et_cl1);
        cln=findViewById(R.id.et_cd);
        ln11 = findViewById(R.id.tv_ln11);
        ln111 = findViewById(R.id.tv_bTn1);
        ln21 = findViewById(R.id.tv_ln21);
        ln01 = findViewById(R.id.tv_ln);
        ln31 = findViewById(R.id.tv_ln31);
        ln32 = findViewById(R.id.tv_ln32);
        ln41 = findViewById(R.id.tv_ln41);
        ln42 = findViewById(R.id.tv_ln42);
        ln51 = findViewById(R.id.tv_ln51);
        ln52 = findViewById(R.id.tv_ln52);
        ln61 = findViewById(R.id.tv_ln61);
        ln62 = findViewById(R.id.tv_ln62);
        ln71 = findViewById(R.id.tv_ln71);
        ln72 = findViewById(R.id.tv_ln72);
        ln82 = findViewById(R.id.tv_ln82);
        ln91 = findViewById(R.id.tv_ln911);
        est = findViewById(R.id.btn_nc);
        tvsp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                est.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Setting Default Input to avoid crash
                        if (clo.getText().toString().length() == 0) { clo.setText("1"); }
                        if (cln.getText().toString().length() == 0) { cln.setText("2"); }
                        //extract old and new load
                        double CLo = Double.parseDouble(clo.getText().toString());
                        double CLn = Double.parseDouble(cln.getText().toString());

                        //if new load entered is lesser then set increment it to one KW to avoid negative outputs

                        if (CLn<CLo)
                        {CLn=CLo+1;cln.setText(format(Locale.US,"%s",Math.round(CLn * 100.0 / 100.0)));
                            Toast toast=Toast.makeText(LoadExtension.this,"Please Enter Value more than existing load",Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER,0,0);
                            toast.show();
                        }
                        // If input is more than 100 then warn.
                        if (CLo>100) {clo.setText(R.string.cl_max);
                            Toast toast=Toast.makeText(LoadExtension.this,"Please Enter Value up to 100KVA only",Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER,0,0);
                            toast.show();
                        }
                        if (CLn>100) {cln.setText((R.string.cl_max));
                            Toast toast=Toast.makeText(LoadExtension.this,"Please Enter Value up to 100KVA only",Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER,0,0);
                            toast.show();
                        }
                        // old and new billing type
                        if (CLo > 20) ln11.setText(R.string._bt);else ln11.setText(R.string._bto);
                        if (CLn > 20) { ln111.setText(R.string._bt); } else { ln111.setText(R.string._bto); }

                        String btn;String itn;String ito;
                        double Rn;double Ln32;double RSn;double Ln42;
                        double a;double b;double c;double d;double e;double f;String ms; String cbs;
                        double Ln71 ;double Ln82;
                        double Ln91;

                        switch (position) {

                            case 1:

                                // Change Unit to KVA when load exceed 20 KW
                                if (CLo>20){unit.setText(R.string._kva);} else {unit.setText(R.string._kw);}
                                if (CLn>20){unit3.setText(R.string._kva);} else {unit3.setText(R.string._kw);}

                                // deciding meter connection type
                                if (CLo*0.9 > 30) { ln21.setText(R.string.mtr_ltct); } else if (CLo > 7) { ln21.setText(R.string.mtr_3ph); } else { ln21.setText(R.string.mtr_1ph); }
                                if (CLn*0.9 > 30) { ln01.setText(R.string.mtr_ltct); } else if (CLn > 7) { ln01.setText(R.string.mtr_3ph); } else { ln01.setText(R.string.mtr_1ph); }

                                //taking billing type ,installation type for future considerations.
                                btn = (ln111.getText().toString());
                                ito = (ln21.getText().toString());
                                itn = (ln01.getText().toString());
                                // Applicable Security Charges Rate and Amount

                                if (btn.equals ("SPOT") ){ if (CLn > 50) { Rn=235; } else { Rn=470; }
                                } else { if (CLn > 100) { Rn=420; } else if (CLn > 20) { Rn=470; } else {Rn=700;}}


                                if (CLn > 20 & CLo<20 ) {Ln32 =(ceil (CLn-CLo/0.9))*Rn; }else { Ln32 = (ceil (CLn-CLo))*Rn;}

                                //Service Connection Charges Rates and Amount

                                if (CLn > 100) {RSn=1800; } else if (CLn > 50) { RSn=1800; } else if (CLn > 7) { RSn=1600; } else { RSn=1000; }
                                //Amount

                                if (CLn>20&CLo<20 ) {Ln42 = (CLn -CLo/0.9 )* RSn; }else { Ln42 = (CLn-CLo )* RSn;}

                                //Meter Security Rates and Charges
                                if (itn.equals("LT-CT Meter")) { a=4200;} else if (itn.equals("3-Ph Meter")) { a=1530; } else { a=400;}
                                if (ito.equals("LT-CT Meter")) { b=4200;} else if (ito.equals("3-Ph Meter")) { b=1530; } else { b=400;}

                                //MCB Security Rates And Charges
                                if (itn.equals("LT-CT Meter")) { d=1780;} else if (itn.equals("3-Ph Meter")){d=350;}else{d=225;}
                                if (ito.equals("LT-CT Meter")) { e=1780;} else if (ito.equals("3-Ph Meter")){e=350; }else{ e=225;}

                                //processing fees
                                if(CLn>7){ln71.setText(R.string.fee1_nrs);}  else {ln71.setText(R.string.fee2_nrs);}



                                break;
                            case 0:
                            default:
                                // Change Unit to KVA when load exceed 50 KW
                                if (CLo>50){unit.setText(R.string._kva);} else {unit.setText(R.string._kw);}
                                if (CLn>50){unit3.setText(R.string._kva);} else {unit3.setText(R.string._kw);}
                                // deciding meter connection type
                                if (CLo > 30) { ln21.setText(R.string.mtr_ltct); } else if (CLo > 7) { ln21.setText(R.string.mtr_3ph); } else { ln21.setText(R.string.mtr_1ph); }
                                if (CLn > 30) { ln01.setText(R.string.mtr_ltct); } else if (CLn > 7) { ln01.setText(R.string.mtr_3ph); } else { ln01.setText(R.string.mtr_1ph); }
                                //taking billing type ,installation type for future considerations.
                                 btn= (ln111.getText().toString());
                                ito= (ln21.getText().toString());
                                 itn= (ln01.getText().toString());
                                //Applicable Security Charges Rate and Amount

                                if (btn.equals ("Spot") ){ if (CLn > 50) { Rn=185; } else { Rn=370; }
                                } else { if (CLn > 100) { Rn=330; } else if (CLn > 20) { Rn=370; } else {Rn=500;}}

                                if (CLn > 50 & CLo<50 ) {Ln32 =(ceil (CLn-CLo/0.9))*Rn; }else { Ln32 = (ceil (CLn-CLo))*Rn;}
                                //Service Connection Charges Rates and Amount

                                if (CLn > 100) {RSn=1750; } else if (CLn > 50) { RSn=1750; } else if (CLn > 7) { RSn=1500; }else if (CLn > 2) { RSn=1000; }
                                else { RSn=450; }

                                if (CLn > 50&CLo<50 ) {Ln42 = (CLn -CLo/0.9 )* RSn; }else { Ln42 = (CLn-CLo )* RSn;}

                                //Meter Security Rates and Charges

                                if (itn.equals("LT-CT Meter")) { a=4200;} else if (itn.equals("3-Ph Meter")) { a=1530; } else { a=400;}
                                if (ito.equals("LT-CT Meter")) { b=4200;} else if (ito.equals("3-Ph Meter")) { b=1530; } else { b=400;}

                               //MCB Security Rates And Charges

                                if (itn.equals("LT-CT Meter")) { d=1780;} else if (itn.equals("3-Ph Meter")) { d=350; } else { d=225;}
                                if (ito.equals("LT-CT Meter")) { e=1780;} else if (ito.equals("3-Ph Meter")) { e=350; } else { e=225;}
                                //processing fees
                                if(CLn>7){ln71.setText(R.string.fee1_ds);}  else {ln71.setText(R.string.fee2_ds);}

                                break;
                        }

                        ln32.setText(format(Locale.US,"%s",Math.round(Ln32 * 100.0 / 100.0)));
                        ln31.setText(format(Locale.US,"%s",Math.round(Rn* 100.0 / 100.0)));

                        ln42.setText(format(Locale.US,"%s",Math.round(Ln42 * 100.0 / 100.0)));
                        ln41.setText(format(Locale.US,"%s",Math.round(RSn * 100.0 / 100.0)));
                        c= a-b;
                        ms = format(Locale.US, "%s", Math.round(c * 100.0 / 100.0));
                        ln52.setText(ms);
                        ln51.setText(ms);
                        f= d-e;
                        cbs = format(Locale.US, "%s", Math.round(f * 100.0 / 100.0));
                        ln62.setText(cbs);
                        ln61.setText(cbs);
                        Ln71 = Double.parseDouble(ln71.getText().toString());
                        ln72.setText(format(Locale.US,"%s",Math.round(Ln71 * 100.0 / 100.0)));
                        //Gst Rate and  Amount
                        Ln82 = 0.18 * Ln71;
                        ln82.setText(format(Locale.US,"%s",Math.round(Ln82 * 100.0 / 100.0)));
                        //Total Amount
                        Ln91 = Ln32 + Ln42 + c + f + Ln71 + Ln82;
                        ln91.setText(format(Locale.US,"%s",Math.round(Ln91 * 100.0 / 100.0)));

                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}