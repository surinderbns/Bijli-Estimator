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

import static java.lang.String.format;


public class ConExp extends AppCompatActivity {
    private EditText cl;
    private Button est;
    private TextView unit;
    private TextView ln11;
    private TextView ln21;
    private TextView  ln31, ln32;
    private TextView ln41, ln42;
    private TextView ln51,ln52;
    private TextView ln61,ln62;
    private TextView ln71,ln72;
    private TextView ln82;
    private TextView ln91;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_con_exp);
        Spinner tvSp2;
        tvSp2 = findViewById(R.id.spn_lan);
        unit =findViewById(R.id.tv_unit);
        cl = findViewById(R.id.et_cl1);

        ln11 = findViewById(R.id.tv_ln11);
        ln21 = findViewById(R.id.tv_ln21);

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
        tvSp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                est.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        //Setting Default Input to avoid crash
                        if (cl.getText().toString().length() == 0) { cl.setText("1");}
                        double Cl= Double.parseDouble(cl.getText().toString());
                        // If input is more than 100 then warn.
                        if (Cl>100) {cl.setText(R.string.cl_max);
                            Toast toast=Toast.makeText(ConExp.this,"Please Enter Value upto 100KVA only",Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER,0,0);
                            toast.show();
                        }

                        //  new billing type
                        if (Cl>20){ln11.setText(R.string._bt);} else {ln11.setText(R.string._bto);}

                        //Meter Installation Type
                        if (Cl>30){ln21.setText(R.string.mtr_ltct);} else if(Cl>7){ln21.setText(R.string.mtr_3ph);} else {ln21.setText(R.string.mtr_1ph);}


                        String bt;String it;
                        double Ln31;double Ln32;
                        double Ln41;double Ln42;
                        double Ln52;
                        double Ln61;
                        double Ln71 ;double Ln82;
                        double Ln91;
                        // take billing type and installation type  to string  for future usage
                        bt=(ln11.getText().toString());
                        it=(ln21.getText().toString());
                        switch (position){
                            case 1:

                                // fr load more than 20 change units to KVA otherwise keep KW.
                                if (Cl>20){unit.setText(R.string._kva);} else {unit.setText(R.string._kw);}

                                // security rates and amount
                                if (bt.equals( "Spot")){ if(Cl>50){ln31.setText(R.string.acd1_spot_nrs);}else {ln31.setText(R.string.acd2_spot_nrs);}}
                                else {if(Cl>100){ln31.setText(R.string.acd1_other_nrs);} else if(Cl>20){ln31.setText(R.string.acd2_other_nrs);} else {ln31.setText(R.string.acd3_other_nrs);}}


                                // serv conn charges rate and amount
                                if(Cl>100){ln41.setText(R.string.scc1_nrs);}else if(Cl>50) {ln41.setText(R.string.scc1_nrs);}else if(Cl>7) {ln41.setText(R.string.scc2_nrs);}else {ln41.setText(R.string.scc3_nrs);}

                                 //meter security
                                if(it.equals("LT-CT Meter")){ln51.setText(R.string.met_sec_ltct);} else if(it.equals("3-Ph Meter")){ln51.setText(R.string.met_sec_3ph);} else {ln51.setText(R.string.met_sec_1ph);}

                                //Mcb Security
                                if(it.equals("LT-CT Meter")){ln61.setText(R.string.mcb_sec_ltct);} else if(it.equals("3-Ph Meter")){ln61.setText(R.string.mcb_sec_iph);} else {ln61.setText(R.string.mcb_sec_1ph);}

                                //proc fees
                                if(Cl>7){ln71.setText(R.string.fee1_nrs);} else {ln71.setText(R.string.fee2_nrs);}

                                break;
                            case 0:
                                default:
                                    // fr load more than 50 change units to KVA otherwise keep KW.
                                    if (Cl>50){unit.setText(R.string._kva);} else {unit.setText(R.string._kw);}

                                    // security rates and amount
                                     if (bt.equals("Spot")){ if(Cl>50){ln31.setText(R.string.acd1_spot_ds);}else {ln31.setText(R.string.acd2_spot_ds);}}
                                     else {if(Cl>100){ln31.setText(R.string.acd2_other_ds);} else if(Cl>20){ln31.setText(R.string.acd3_other_ds);} else {ln31.setText(R.string.acd4_other_ds);}}
                                    // serv conn charges rate and amount
                                     if(Cl>100){ln41.setText(R.string.scc1_ds);}else if(Cl>50) {ln41.setText(R.string.scc1_ds);}else if(Cl>7) {ln41.setText(R.string.scc2_ds);}else if(Cl>2) {ln41.setText(R.string.scc3_ds);}else {ln41.setText(R.string.scc4_ds);}


                                    //meter security
                                     if(it.equals("LT-CT Meter")){ln51.setText(R.string.met_sec_ltct);} else if(it.equals("3-Ph Meter")){ln51.setText(R.string.met_sec_3ph);} else {ln51.setText(R.string.met_sec_1ph);}

                                    ////Mcb Security
                                    if(it.equals("LT-CT Meter")){ln61.setText(R.string.mcb_sec_ltct);} else if(it.equals("3-Ph Meter")){ln61.setText(R.string.mcb_sec_iph);} else {ln61.setText(R.string.mcb_sec_1ph);}

                                    //proc fees
                                    if(Cl>7){ln71.setText(R.string.fee1_ds);}  else {ln71.setText(R.string.fee2_ds);}

                                    break;

                        }
                        Ln31 =  Double.parseDouble(ln31.getText().toString());
                        Ln32 = Math.ceil(Cl)*Ln31;ln32.setText(format(Locale.US,"%s",Math.round(Ln32*100.0/100.0)));

                        Ln41 =  Double.parseDouble(ln41.getText().toString());
                        Ln42 = (Cl)*Ln41;ln42.setText(format(Locale.US,"%s",Math.round(Ln42*100.0/100.0)));

                        Ln52 = Double.parseDouble(ln51.getText().toString());ln52.setText(format(Locale.US,"%s",Math.round(Ln52*100.0/100.0)));
                        Ln61 =  Double.parseDouble(ln61.getText().toString());
                        ln62.setText(format(Locale.US,"%s",Math.round(Ln61 *100.0/100.0)));

                        Ln71 =  Double.parseDouble(ln71.getText().toString());
                        ln72.setText(format(Locale.US,"%s",Math.round(Ln71 *100.0/100.0)));

                        //gst
                        Ln82 = 0.18* Ln71;ln82.setText(format(Locale.US,"%s",Math.round(Ln82*100.0/100.0)));
                        Ln91 = Ln32+Ln42+Ln52+ Ln61 + Ln71 +Ln82;ln91.setText(format(Locale.US,"%s",Math.round(Ln91*100.0/100.0)));

                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}