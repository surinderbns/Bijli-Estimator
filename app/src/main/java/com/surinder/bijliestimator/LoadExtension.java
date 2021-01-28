package com.surinder.bijliestimator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.Gravity;
import android.view.View;
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
                est.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (position) {

                            case 1:
                                if (clo.getText().toString().length() == 0) {
                                    clo.setText("1");
                                }
                                if (cln.getText().toString().length() == 0) {
                                cln.setText("2");
                                }

                                double CLo = Double.parseDouble(clo.getText().toString());
                                if (CLo>20){unit.setText(R.string._kva);} else {unit.setText(R.string._kw);}
                                if (CLo>100) {clo.setText(R.string.cl_max);
                                    Toast toast=Toast.makeText(LoadExtension.this,"Please Enter Value upto 100KVA only",Toast.LENGTH_LONG);
                                    toast.setGravity(Gravity.CENTER,0,0);
                                    toast.show();
                                }


                                double CLn = Double.parseDouble(cln.getText().toString());
                                if (CLn>20){unit3.setText(R.string._kva);} else {unit3.setText(R.string._kw);}
                                if (CLn>100) {cln.setText((R.string.cl_max));
                                    Toast toast=Toast.makeText(LoadExtension.this,"Please Enter Value upto 100KVA only",Toast.LENGTH_LONG);
                                    toast.setGravity(Gravity.CENTER,0,0);
                                    toast.show();
                                }


                                if (CLo > 20) ln11.setText(R.string._bt);else ln11.setText(R.string._bto);
                                if (CLn > 20) { ln111.setText(R.string._bt); } else { ln111.setText(R.string._bto); }

                                if (CLo*0.9 > 30) { ln21.setText(R.string.mtr_ltct); } else if (CLo > 7) { ln21.setText(R.string.mtr_3ph); } else { ln21.setText(R.string.mtr_1ph); }
                                if (CLn*0.9 > 30) { ln01.setText(R.string.mtr_ltct); } else if (CLn > 7) { ln01.setText(R.string.mtr_3ph); } else { ln01.setText(R.string.mtr_1ph); }

                                String btn = (ln111.getText().toString());
                                String ito = (ln21.getText().toString());
                                String itn = (ln01.getText().toString());


                                 double Rn;
                                if (btn.equals ("Spot") ){ if (CLn > 50) { Rn=235; } else { Rn=470; }
                                } else { if (CLn > 100) { Rn=420; } else if (CLn > 20) { Rn=470; } else {Rn=700;}}

                                double Ln32;
                                if (CLn > 20 & CLo<20 ) {Ln32 =(ceil (CLn-CLo/0.9))*Rn; }else { Ln32 = (ceil (CLn-CLo))*Rn;}
                                ln32.setText(format(Locale.US,"%s",Math.round(Ln32 * 100.0 / 100.0)));
                                ln31.setText(format(Locale.US,"%s",Math.round(Rn* 100.0 / 100.0)));



                               double RSn;
                                if (CLn > 100) {RSn=1800; } else if (CLn > 50) { RSn=1800; } else if (CLn > 7) { RSn=1600; }
                                else { RSn=1000; }
                                double Ln42;
                                if (CLn > 20&CLo<20 ) {Ln42 = (CLn -CLo/0.9 )* RSn; }else { Ln42 = (CLn-CLo )* RSn;}
                                ln42.setText(format(Locale.US,"%s",Math.round(Ln42 * 100.0 / 100.0)));
                                ln41.setText(format(Locale.US,"%s",Math.round(RSn * 100.0 / 100.0)));



                                double a;double b;
                                if (itn.equals("LT-CT Meter")) { a=4200;} else if (itn.equals("3-Ph Meter")) { a=1530; } else { a=400;}
                                if (ito.equals("LT-CT Meter")) { b=4200;} else if (ito.equals("3-Ph Meter")) { b=1530; } else { b=400;}
                                double c= a-b;
                                final String format = format(Locale.US, "%s", Math.round(c * 100.0 / 100.0));
                                ln52.setText(format);
                                ln51.setText(format);

                                double d;double e;
                                if (itn.equals("LT-CT Meter")) { d=1780;} else if (itn.equals("3-Ph Meter")){d=350;}else{d=225;}
                                if (ito.equals("LT-CT Meter")) { e=1780;} else if (ito.equals("3-Ph Meter")){e=350; }else{ e=225;}
                                double f= d-e;
                                final String format1 = format(Locale.US, "%s", Math.round(f * 100.0 / 100.0));
                                ln62.setText(format1);
                                ln61.setText(format1);

                                if(CLn>7){ln71.setText(R.string.fee1_nrs);}  else {ln71.setText(R.string.fee2_nrs);}
                                double Ln71 = Double.parseDouble(ln71.getText().toString());
                                ln72.setText(format(Locale.US,"%s",Math.round(Ln71 * 100.0 / 100.0)));
                                double Ln82 = 0.18 * Ln71;
                                ln82.setText(format(Locale.US,"%s",Math.round(Ln82 * 100.0 / 100.0)));
                                double Ln91 = Ln32 + Ln42 + c + f + Ln71 + Ln82;
                                ln91.setText(format(Locale.US,"%s",Math.round(Ln91 * 100.0 / 100.0)));
                                break;
                            case 0:
                            default:
                                if (clo.getText().toString().length() == 0) {
                                    clo.setText("1");
                                }
                                if (cln.getText().toString().length() == 0) {
                                    cln.setText("2");
                                }



                                double CLo0 = Double.parseDouble(clo.getText().toString());
                                if (CLo0>50){unit.setText(R.string._kva);} else {unit.setText(R.string._kw);}
                                if (CLo0>100) {clo.setText(R.string.cl_max);
                                    Toast toast=Toast.makeText(LoadExtension.this,"Please Enter Value upto 100KVA only",Toast.LENGTH_LONG);
                                    toast.setGravity(Gravity.CENTER,0,0);
                                    toast.show();
                                }

                                double CLn0 = Double.parseDouble(cln.getText().toString());
                                if (CLn0>50){unit3.setText(R.string._kva);} else {unit3.setText(R.string._kw);}
                                if (CLn0>100) {cln.setText(R.string.cl_max);
                                    Toast toast=Toast.makeText(LoadExtension.this,"Please Enter Value upto 100KVA only",Toast.LENGTH_LONG);
                                    toast.setGravity(Gravity.CENTER,0,0);
                                    toast.show();
                                }



                                if (CLo0 > 20) { ln11.setText(R.string._bt); } else { ln11.setText(R.string._bto); }
                                if (CLn0 > 20) { ln111.setText(R.string._bt); } else { ln111.setText(R.string._bto); }
                                if (CLo0 > 30) { ln21.setText(R.string.mtr_ltct); } else if (CLo0 > 7) { ln21.setText(R.string.mtr_3ph); } else { ln21.setText(R.string.mtr_1ph); }
                                if (CLn0 > 30) { ln01.setText(R.string.mtr_ltct); } else if (CLn0 > 7) { ln01.setText(R.string.mtr_3ph); } else { ln01.setText(R.string.mtr_1ph); }

                                String btn0 = (ln111.getText().toString());
                                String ito0 = (ln21.getText().toString());
                                String itn0 = (ln01.getText().toString());




                                double Rn0;
                                if (btn0.equals ("Spot") ){ if (CLn0 > 50) { Rn0=185; } else { Rn0=370; }
                                } else { if (CLn0 > 100) { Rn0=330; } else if (CLn0 > 20) { Rn0=370; } else {Rn0=500;}}
                                double Ln320;
                                if (CLn0 > 50 & CLo0<50 ) {Ln320 =(ceil (CLn0-CLo0/0.9))*Rn0; }else { Ln320 = (ceil (CLn0-CLo0))*Rn0;}
                                ln32.setText(format(Locale.US,"%s",Math.round(Ln320 * 100.0 / 100.0)));
                                ln31.setText(format(Locale.US,"%s",Math.round(Rn0 * 100.0 / 100.0)));




                                 double RSn0;
                                if (CLn0 > 100) {RSn0=1750; } else if (CLn0 > 50) { RSn0=1750; } else if (CLn0 > 7) { RSn0=1500; }else if (CLn0 > 2) { RSn0=1000; }
                                else { RSn0=450; }

                                double Ln420;
                                if (CLn0 > 50&CLo0<50 ) {Ln420 = (CLn0 -CLo0/0.9 )* RSn0; }else { Ln420 = (CLn0-CLo0 )* RSn0;}
                                ln42.setText(format(Locale.US,"%s",Math.round(Ln420 * 100.0 / 100.0)));
                                ln41.setText(format(Locale.US,"%s",Math.round(RSn0 * 100.0 / 100.0)));









                                double a0;double b0;
                                if (itn0.equals("LT-CT Meter")) { a0=4200;} else if (itn0.equals("3-Ph Meter")) { a0=1530; } else { a0=400;}
                                if (ito0.equals("LT-CT Meter")) { b0=4200;} else if (ito0.equals("3-Ph Meter")) { b0=1530; } else { b0=400;}
                                double c0= a0-b0;
                                final String format2 = format(Locale.US, "%s", Math.round(c0 * 100.0 / 100.0));
                                ln52.setText(format2);
                                ln51.setText(format2);

                                double d0;double e0;
                                if (itn0.equals("LT-CT Meter")) { d0=1780;} else if (itn0.equals("3-Ph Meter")) { d0=350; } else { d0=225;}
                                if (ito0.equals("LT-CT Meter")) { e0=1780;} else if (ito0.equals("3-Ph Meter")) { e0=350; } else { e0=225;}
                                double f0= d0-e0;
                                String text = format(Locale.US, "%s", Math.round(f0 * 100.0 / 100.0));
                                ln62.setText(text);
                                ln61.setText(text);

                                if(CLn0>7){ln71.setText(R.string.fee1_ds);}  else {ln71.setText(R.string.fee2_ds);}
                                double Ln710 = Double.parseDouble(ln71.getText().toString());
                                ln72.setText(format(Locale.US,"%s",Math.round(Ln710 * 100.0/ 100.0)));

                                double Ln820 = 0.18 * Ln710;
                                ln82.setText(format(Locale.US,"%s",Math.round(Ln820 * 100.0 / 100.0)));

                                double Ln910 = Ln320 + Ln420 + c0 + f0 + Ln710 + Ln820;
                                ln91.setText(format(Locale.US,"%s",Math.round(Ln910*100.0/100.0)));

                                break;


                        }

                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}