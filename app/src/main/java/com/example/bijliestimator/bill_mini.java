package com.example.bijliestimator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Locale;

import static java.lang.String.format;

public class bill_mini extends AppCompatActivity {
    private EditText cl,bp,uc;
    private Button cal;
    private TextView tvsp3,tvsp4;
    private TextView fch1;
    private TextView fch2;
    private TextView fch3;
    private TextView ech1,ech2,ech3,ech4,ech5,ech6,ech7,ech8,ech9,ech10,ech11,ech12,ech13;
    private TextView fca;
    private TextView tax;
    private TextView gt;
    private TextView mr11,mr12,mc11,mc12;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billmini);
        findViewById(R.id.spn_lan);
        Spinner tvsp2;
        tvsp3 = findViewById(R.id.spn_cl);
        tvsp4 = findViewById(R.id.spn_unit);

        cl = findViewById(R.id.et_cl1);
        bp = findViewById(R.id.et_cl2);
        uc = findViewById(R.id.et_uc);
        fch1 = findViewById(R.id.tv_ln1);
        fch2 = findViewById(R.id.tv_fc2);
        fch3 = findViewById(R.id.tv_ln21);
        ech1 = findViewById(R.id.tv_ln3);
        ech2 = findViewById(R.id.tv_ln31);
        ech3 = findViewById(R.id.tv_ln32);
        ech4 = findViewById(R.id.tv_ln4);
        ech5 = findViewById(R.id.tv_ln41);
        ech6 = findViewById(R.id.tv_ln42);
        ech7 = findViewById(R.id.tv_ln5);
        ech8 = findViewById(R.id.tv_ln51);
        ech9 = findViewById(R.id.tv_ln52);
        ech10 = findViewById(R.id.tv_ln6);
        ech11 = findViewById(R.id.tv_ln61);
        ech12 = findViewById(R.id.tv_ln62);
        ech13 = findViewById(R.id.tv_ec13);
        fca = findViewById(R.id.tv_ln72);
        mr11=findViewById(R.id.tv_mr1);
        mr12=findViewById(R.id.tv_mr12);
        mc11=findViewById(R.id.tv_mc11);
        mc12=findViewById(R.id.tv_mc12);
        tax = findViewById(R.id.tv_ln82);
        gt = findViewById(R.id.tv_ln911);
        tvsp2 = findViewById(R.id.spn_lan);
        cal = findViewById(R.id.btn_nc);


        tvsp2.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                cal.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        switch (position){
                            case 1:
                                if (cl.getText().toString().length() == 0) { cl.setText(R.string.def_cl);}
                                if (bp.getText().toString().length() == 0) { bp.setText(R.string.def_bp);}
                                if (uc.getText().toString().length() == 0) { uc.setText(R.string.def_uc);}
                                double rate=0;
                                double Fixed;
                                double days= Double.parseDouble(bp.getText().toString());
                                double month= days * 12/366; double moNth= days/30;
                                double Cl= Double.parseDouble(cl.getText().toString());

                                if (Cl>20){tvsp3.setText(R.string._kva);tvsp4.setText(R.string._kvah);}
                                else {tvsp3.setText(R.string._kw);tvsp4.setText(R.string._kwh);}

                                double Load= Math.round( Cl * 80/100*100.0)/100.0;
                                if (Cl <= 7  ){rate = 45;}
                                else if(Cl > 7 && Cl <=20)  {rate = 70; }
                                else if(Cl > 20&& Cl <=100 ){ rate = 100; }
                                else if(Cl > 100 ) { rate = 110; }
                                Fixed= rate*Load*month;
                                fch1.setText(format(Locale.US,"%s",Load));
                                fch2.setText(format(Locale.US,"%s",rate));
                                fch3.setText(format(Locale.US,"%s",(Math.round(Fixed*100/100))));

                                double E1;double E2 ;if(Cl<=20){E2=6.91;} else if(Cl>20&&Cl<=100) {E2=6.35;}else {E2=6.55;}
                                double E4;double E5 ;if(Cl<=20){E5=7.17;} else if(Cl>20&&Cl<=100) {E5=6.35;}else {E5=6.55;}
                                double E7;double E8 ;if(Cl<=20){E8=7.17;} else if(Cl>20&&Cl<=100) {E8=6.35;}else {E8=6.55;}
                                double E10;double E11;if(Cl<=20){E11=7.29;}else if(Cl>20&&Cl<=100) {E11=6.35;}else {E11=6.55;}

                                double Uc= Double.parseDouble(uc.getText().toString());

                                if (Uc/moNth <= 100   ) { E1 = Uc; E4 = 0;E7 = 0;E10 = 0; }
                                else if(Uc/moNth >100 && Uc/moNth<=300 ) { E1 = 100*moNth;E4= Uc-(100*moNth);E7 = 0;E10 = 0; }
                                else if(Uc/moNth >300 && Uc/moNth <=500) { E1 = 100*moNth;E4= 200*moNth;E7=Uc-(300*moNth);E10 = 0; }
                                else { E1 = 100*moNth;E4= 200*moNth;E7=200*moNth;E10=Uc-(500*moNth); }


                                double M1; double M3;if (Cl>30){M1=109.74;M3=30.68;}else if(Cl>7&&Cl<=30) {M1=29.5;M3=7.08;}else{M1=9.44;M3=4.72;}
                               mr11.setText(format(Locale.US,"%s",M1));mc11.setText(format(Locale.US,"%s",M3));
                                double Mr=M1*month;
                                double Mc=M3*month;
                                mr12.setText(format(Locale.US,"%s", ( Math.round(Mr*100/100))));mc12.setText(format(Locale.US,"%s", Math.round(Mc*100/100)));

                                double E3 = E1*E2;double E6 = E4*E5;double E9 = E7*E8;double E12 = E10*E11;double E13 = E3+E6+E9+E12;double E14= (0.29*Uc);double E15=20*(E13+E14+Fixed)/100;
                                double E16=Fixed+E13+E14+E15+Mr+Mc;

                                ech1.setText(format(Locale.US,"%s",Math.round(E1*100/100)));ech2.setText(format(Locale.US,"%s",E2));ech3.setText(format(Locale.US,"%s",Math.round(E3*100/100)));
                                ech4.setText(format(Locale.US,"%s",Math.round(E4*100/100)));ech5.setText(format(Locale.US,"%s",E5));ech6.setText(format(Locale.US,"%s",Math.round(E6*100/100)));
                                ech7.setText(format(Locale.US,"%s",Math.round(E7*100/100)));ech8.setText(format(Locale.US,"%s",E8));ech9.setText(format(Locale.US,"%s",Math.round(E9*100/100)));
                                ech10.setText(format(Locale.US,"%s",Math.round(E10*100/100)));ech11.setText(format(Locale.US,"%s",E11));ech12.setText(format(Locale.US,"%s",Math.round(E12*100/100)));
                                ech13.setText(format(Locale.US,"%s",Math.round(E13*100/100)));fca.setText(format(Locale.US,"%s",Math.round(E14*100/100)));tax.setText(format(Locale.US,"%s",Math.round(E15*100/100)));
                                gt.setText(format(Locale.US,"%s",Math.round(E16*100/100)));




                                break;
                            case 0:
                            default:
                                if (cl.getText().toString().length() == 0) { cl.setText(R.string.def_cl);}
                                if (bp.getText().toString().length() == 0) { bp.setText(R.string.def_bp);}
                                if (uc.getText().toString().length() == 0) { uc.setText(R.string.def_uc);}
                                double Rate=0;
                                double Fixed0;
                                double days0= Double.parseDouble(bp.getText().toString());
                                double month0= days0 * 12/366;double moNth0= days0 /30;
                                double Cl0= Double.parseDouble(cl.getText().toString());

                                if (Cl0>50){tvsp3.setText(R.string._kva);tvsp4.setText(R.string._kvah);}
                                else {tvsp3.setText(R.string._kw);tvsp4.setText(R.string._kwh);}

                                double Load0= Math.round( Cl0 * 80/100*100.0)/100.0;
                                if (Cl0 <= 2  )              {Rate = 35;}
                                else if(Cl0 > 2 && Cl0 <=7)   { Rate = 60; }
                                else if(Cl0 > 7 && Cl0 <=50)  {Rate = 75; }
                                else if(Cl0 > 50&& Cl0 <=100 ){ Rate = 100; }
                                else if(Cl0 > 100 ) { Rate = 110; }
                                Fixed0= Rate*Load0*month0;
                                fch1.setText(format(Locale.US,"%s", Load0));
                                fch2.setText(format(Locale.US,"%s",Rate));
                                fch3.setText(format(Locale.US,"%s",(Math.round(Fixed0*100/100))));

                                double EE1;double EE2 ;if(Cl0<=50){EE2=4.49;} else if(Cl0>50&&Cl0<=100) {EE2=6.33;}else {EE2=6.53;}
                                double EE4;double EE5 ;if(Cl0<=50){EE5=6.34;} else if(Cl0>50&&Cl0<=100) {EE5=6.33;}else {EE5=6.53;}
                                double EE7;double EE8 ;if(Cl0<=50){EE8=7.30;} else if(Cl0>50&&Cl0<=100) {EE8=6.33;}else {EE8=6.53;}
                                double EE10;double EE11;if(Cl0<=50){EE11=7.30;}else if(Cl0>50&&Cl0<=100) {EE11=6.33;}else {EE11=6.53;}

                                double UUc= Double.parseDouble(uc.getText().toString());

                                if (UUc/moNth0 <= 100   ) { EE1 = UUc; EE4 = 0;EE7 = 0;EE10 = 0; }
                                else if(UUc/moNth0 >100 && UUc/moNth0<=300 ) { EE1 = 100*moNth0;EE4= UUc-(100*moNth0);EE7 = 0;EE10 = 0; }
                                else if(UUc/moNth0 >300 && UUc/moNth0 <=500) { EE1 = 100*moNth0;EE4= 200*moNth0;EE7=UUc-(300*moNth0);EE10 = 0; }
                                else { EE1 = 100*moNth0;EE4= 200*moNth0;EE7=200*moNth0;EE10=UUc-(500*moNth0); }

                                double EM1; double EM3;if (Cl0>30){EM1=109.74;EM3=30.68;}else if(Cl0>7&&Cl0<=30) {EM1=29.5;EM3=7.08;}else{EM1=9.44;EM3=4.72;}
                                mr11.setText(format(Locale.US,"%s",EM1));mc11.setText(format(Locale.US,"%s",EM3));
                                double EMr=EM1*month0;
                                double EMc=EM3*month0;
                                mr12.setText(format(Locale.US,"%s", Math.round(EMr*100/100)));mc12.setText(format(Locale.US,"%s",Math.round(EMc*100/100)));



                                double EE3 = EE1*EE2;double EE6 = EE4*EE5;double EE9 = EE7*EE8;double EE12 = EE10*EE11;double EE13 = EE3+EE6+EE9+EE12;double EE14= (0.29*UUc);double EE15=20*(EE13+EE14+Fixed0)/100;
                                double EE16=Fixed0+EE13+EE14+EE15+EMr+EMc;
                                ech1.setText(format(Locale.US,"%s",Math.round(EE1*100/100)));ech2.setText(format(Locale.US,"%s",EE2));ech3.setText(format(Locale.US,"%s",Math.round(EE3*100/100)));
                                ech4.setText(format(Locale.US,"%s",Math.round(EE4*100/100)));ech5.setText(format(Locale.US,"%s",EE5));ech6.setText(format(Locale.US,"%s",Math.round(EE6*100/100)));
                                ech7.setText(format(Locale.US,"%s",Math.round(EE7*100/100)));ech8.setText(format(Locale.US,"%s",EE8));ech9.setText(format(Locale.US,"%s",Math.round(EE9*100/100)));
                                ech10.setText(format(Locale.US,"%s",Math.round(EE10*100/100)));ech11.setText(format(Locale.US,"%s",EE11));ech12.setText(format(Locale.US,"%s",Math.round(EE12*100/100)));
                                ech13.setText(format(Locale.US,"%s",Math.round(EE13*100/100)));fca.setText(format(Locale.US,"%s",Math.round(EE14*100/100)));tax.setText(format(Locale.US,"%s",Math.round(EE15*100/100)));
                                gt.setText(format(Locale.US,"%s",Math.round(EE16*100/100)));

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


