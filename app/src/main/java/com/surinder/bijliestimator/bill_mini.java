package com.surinder.bijliestimator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import static java.lang.String.format;

public class bill_mini extends AppCompatActivity {
    private EditText cl,bp,uc,conc;
    private Button cal;
    private TextView tvsp3,tvsp4;
    private TextView fch1;
    private TextView fch2;
    private TextView fch3;
    private TextView ech1,ech2,ech3,ech4,ech5,ech6,ech7,ech8,ech9,ech10,ech11,ech12,ech13;

    private TextView tax;
    private TextView gt;
    private TextView mr11,mr12,mc11,mc12;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billmini);
        findViewById(R.id.spn_lan);
        Spinner tvsp2;
        Button link=findViewById(R.id.tv_pspcl);
        link.setMovementMethod(LinkMovementMethod.getInstance());
        tvsp3 = findViewById(R.id.spn_cl);
        tvsp4 = findViewById(R.id.spn_unit);
        cl = findViewById(R.id.et_cl);
        bp = findViewById(R.id.et_bp);
        uc = findViewById(R.id.et_uc);
        conc = findViewById(R.id.et_conc);

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
        mr11=findViewById(R.id.tv_mr1);
        mr12=findViewById(R.id.tv_mr12);
        mc11=findViewById(R.id.tv_mc11);
        mc12=findViewById(R.id.tv_mc12);
        tax = findViewById(R.id.tv_ln82);
        gt = findViewById(R.id.tv_ln911);
        tvsp2 = findViewById(R.id.spn_lan);
        cal = findViewById(R.id.btn_nc);



        tvsp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                cal.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        //setting default values to avoid crashes
                        if (cl.getText().toString().length() == 0) { cl.setText(R.string.def_cl);}
                        if (cl.getText().toString().length() == 0) { cl.setText(R.string.def_cl);}
                        if (bp.getText().toString().length() == 0) { bp.setText(R.string.def_bp);}
                        if (uc.getText().toString().length() == 0) { uc.setText(R.string.def_uc);}
                        if (conc.getText().toString().length() == 0) { conc.setText("0");}
                        //convert all inputs to double
                        double days= Double.parseDouble(bp.getText().toString());
                        double Cl= Double.parseDouble(cl.getText().toString());
                        double Uc= Double.parseDouble(uc.getText().toString());
                        //conversion of period in days to months
                        double month= days * 12/366; double moNth;
                        if (days<=33&&days>=27){moNth=1;}else if(days<=63&&days>=57){moNth=2;}else  {moNth=days/30;}
                        double rate = 0; double Fixed;
                        double E1;double E2 ;double E3 ;
                        double E4;double E5 ;double E6 ;
                        double E7;double E8;double E9 ;
                        double E10;double E11;double E12 ;
                        double E13;double E15;
                        double M1;
                        double M3;
                        //fixed charge rate and amount calculation.
                        double Load= Math.round(Cl*80/100*100.0)/100.0;
                        //meter rent calculator and display meter rent and mcb rent
                        if (Cl>30){M1=109.74;M3=30.68;}else if(Cl>7&&Cl<=30) {M1=29.5;M3=7.08;}else{M1=9.44;M3=4.72;}
                        double Mr=0; double Mc=0;

                        switch (position){
                            case 3:

                                conc.setText(R.string.Sc_Bc);
                                //setting up unit in KWH or KVAH based on entered value of connected load

                                if (Cl>1) {cl.setText("1");
                                    Toast toast=Toast.makeText(bill_mini.this,"Load can't be More Than 1 KW for Concession",Toast.LENGTH_LONG);
                                    toast.setGravity(Gravity.CENTER,0,0);
                                    toast.show();
                                }

                                //fixed charge rate calculation.

                                Fixed= rate*Load*month;
                                // Energy Charges calculation Table

                                double UconS= Double.parseDouble(conc.getText().toString());
                                //Applicable Rates
                                if(Uc<=UconS*moNth)
                                {E2=E5=E8=E11=0;}
                                else{E2=4.49;E5=6.34;E8=7.30;E11=7.30;}



                                //monthwise slab consumption calculations
                                if (Uc/moNth<= 100   ) {  E1 = Uc; E4 = 0; E7 = 0;E10 = 0; }
                                else if(Uc/moNth >100 && Uc/moNth<=300 ) { E1 = 100*moNth;E4= Uc-(100*moNth);E7 = 0;E10 = 0; }
                                else if(Uc/moNth >300 && Uc/moNth <=500) { E1 = 100*moNth;E4= 200*moNth;E7=Uc-(300*moNth);E10 = 0; }
                                else { E1 = 100*moNth;E4= 200*moNth;E7=200*moNth;E10=Uc-(500*moNth); }

                                double e1,e4,e7,e10;

                                if (UconS<= 100  ) {  e1 = UconS*moNth; e4 = 0; e7 = 0;e10 = 0; }
                                else if(UconS >100 && UconS<=300 ) { e1 = 100*moNth;e4= (UconS-100)*moNth;e7 = 0;e10 = 0; }
                                else if(UconS>300 && UconS <=500) { e1 = 100*moNth;e4= 200*moNth;e7=(UconS-300)*moNth;e10 = 0; }
                                else { e1 = 100*moNth;e4= 200*moNth;e7=200*moNth;e10=(UconS-500)*moNth; }

                                E3 = (E1-e1)*E2; E6 = (E4-e4)*E5; E9 = (E7-e7)*E8; E12 = (E10-e10)*E11; E13 = E3+E6+E9+E12;
                                E15=20*(E13+Fixed)/100 +0.02*Uc;

                                break;
                            case 2:

                                //setting up unit in KWH or KVAH based on entered value of connected load

                                if (Cl>50){tvsp3.setText(R.string._kva);tvsp4.setText(R.string._kvah);}
                                else {tvsp3.setText(R.string._kw);tvsp4.setText(R.string.kwh);}

                                //fixed charge rate calculation.

                                if (Cl <= 2  )              {rate = 35;}
                                else if(Cl > 2 && Cl <=7)   { rate = 60; }
                                else if(Cl > 7 && Cl <=50)  {rate = 75; }
                                else if(Cl > 50&& Cl <=100 ){ rate = 100; }
                                else if(Cl > 100 ) { rate = 110; }
                                Fixed= rate*Load*month;
                                // Energy Charges calculation Table

                                double Ucon= Double.parseDouble(conc.getText().toString());
                                double Uuc;
                                if(Uc>Ucon*moNth){ Uuc= Uc-Ucon*moNth;} else {Uuc=0 ;}
                                if(Cl<=50){E2=4.49;} else if(Cl>50&&Cl<=100) {E2=6.33;}else {E2=6.53;}
                                if(Cl<=50){E5=6.34;} else if(Cl>50&&Cl<=100) {E5=6.33;}else {E5=6.53;}
                                if(Cl<=50){E8=7.30;} else if(Cl>50&&Cl<=100) {E8=6.33;}else {E8=6.53;}
                                if(Cl<=50){E11=7.30;}else if(Cl>50&&Cl<=100) {E11=6.33;}else {E11=6.53;}


                                if (Uuc/moNth<= 100   ) {  E1 = Uuc; E4 = 0; E7 = 0;E10 = 0; }
                                else if(Uuc/moNth >100 && Uuc/moNth<=300 ) { E1 = 100*moNth;E4= Uuc-(100*moNth);E7 = 0;E10 = 0; }
                                else if(Uuc/moNth >300 && Uuc/moNth <=500) { E1 = 100*moNth;E4= 200*moNth;E7=Uuc-(300*moNth);E10 = 0; }
                                else { E1 = 100*moNth;E4= 200*moNth;E7=200*moNth;E10=Uuc-(500*moNth); }
                                E3 = E1*E2; E6 = E4*E5; E9 = E7*E8; E12 = E10*E11;
                                E13 = E3+E6+E9+E12;

                                double EE1,EE4,EE7,EE10 = 0;

                                if (Uc/moNth <= 100   ) { EE1 = Uc; EE4 = 0;EE7 = 0;EE10 = 0; }
                                else if(Uc/moNth >100 && Uc/moNth<=300 ) { EE1 = 100*moNth;EE4= Uc-(100*moNth);EE7 = 0;E10 = 0; }
                                else if(Uc/moNth >300 && Uc/moNth <=500) { EE1 = 100*moNth;EE4= 200*moNth;EE7=Uc-(300*moNth);EE10 = 0; }
                                else { EE1 = 100*moNth;EE4= 200*moNth;EE7=200*moNth;EE10=Uc-(500*moNth); }
                                double EE3 = EE1*E2;double EE6 = EE4*E5;double EE9 = EE7*E8;double EE12 = EE10*E11;double EE13 = EE3+EE6+EE9+EE12;

                                E15=20*(EE13+Fixed)/100 +0.02*Uc;
                                Mr=M1*moNth;
                                Mc=M3*moNth;


                                break;

                            case 1:
                                //setting up unit in KWH or KVAH based on entered value of connected load
                                if (Cl>20){tvsp3.setText(R.string._kva);tvsp4.setText(R.string._kvah);}
                                else {tvsp3.setText(R.string._kw);tvsp4.setText(R.string.kwh);}

                                //Rate selection for fixed charges
                                if (Cl <= 7  ){rate = 45;}
                                else if(Cl > 7 && Cl <=20)  {rate = 70; }
                                else if(Cl > 20&& Cl <=100 ){ rate = 100; }
                                else if(Cl > 100 ) { rate = 110; }
                                Fixed= rate*Load*month;
                                // Energy Charges calculation Table
                                if(Cl<=20){E2=6.91;} else if(Cl>20&&Cl<=100) {E2=6.35;}else {E2=6.55;}
                                if(Cl<=20){E5=7.17;} else if(Cl>20&&Cl<=100) {E5=6.35;}else {E5=6.55;}
                                if(Cl<=20){E8=7.17;} else if(Cl>20&&Cl<=100) {E8=6.35;}else {E8=6.55;}
                                if(Cl<=20){E11=7.29;}else if(Cl>20&&Cl<=100) {E11=6.35;}else {E11=6.55;}


                                //monthwise slab consumption calculations
                                if (Uc/moNth <= 100   ) { E1 = Uc; E4 = 0;E7 = 0;E10 = 0; }
                                else if(Uc/moNth >100 && Uc/moNth<=300 ) { E1 = 100*moNth;E4= Uc-(100*moNth);E7 = 0;E10 = 0; }
                                else if(Uc/moNth >300 && Uc/moNth <=500) { E1 = 100*moNth;E4= 200*moNth;E7=Uc-(300*moNth);E10 = 0; }
                                else { E1 = 100*moNth;E4= 200*moNth;E7=200*moNth;E10=Uc-(500*moNth); }
                                E3 = E1*E2;  E6 = E4*E5; E9 = E7*E8; E12 = E10*E11; E13 = E3+E6+E9+E12;
                                E15=20*(E13+Fixed)/100 +0.02*Uc;
                                //Meter Rent Calculator
                                Mr=M1*month;
                                Mc=M3*month;

                                break;

                            case 0:
                            default:
                                //setting up unit in KWH or KVAH based on entered value of connected load

                                if (Cl>50){tvsp3.setText(R.string._kva);tvsp4.setText(R.string._kvah);}
                                else {tvsp3.setText(R.string._kw);tvsp4.setText(R.string.kwh);}

                                //fixed charge rate calculation.

                                if (Cl <= 2  )              {rate = 35;}
                                else if(Cl > 2 && Cl <=7)   { rate = 60; }
                                else if(Cl > 7 && Cl <=50)  {rate = 75; }
                                else if(Cl > 50&& Cl <=100 ){ rate = 100; }
                                else if(Cl > 100 ) { rate = 110; }
                                Fixed= rate*Load*month;
                                // Energy Charges calculation Table
                                if(Cl<=50){E2=4.49;} else if(Cl>50&&Cl<=100) {E2=6.33;}else {E2=6.53;}
                                if(Cl<=50){E5=6.34;} else if(Cl>50&&Cl<=100) {E5=6.33;}else {E5=6.53;}
                                if(Cl<=50){E8=7.30;} else if(Cl>50&&Cl<=100) {E8=6.33;}else {E8=6.53;}
                                if(Cl<=50){E11=7.30;}else if(Cl>50&&Cl<=100) {E11=6.33;}else {E11=6.53;}

                                // Energy Charges for calculating Tax for BE Category consumer



                                if (Uc/moNth<= 100   ) {  E1 = Uc; E4 = 0; E7 = 0;E10 = 0; }
                                else if(Uc/moNth >100 && Uc/moNth<=300 ) { E1 = 100*moNth;E4= Uc-(100*moNth);E7 = 0;E10 = 0; }
                                else if(Uc/moNth >300 && Uc/moNth <=500) { E1 = 100*moNth;E4= 200*moNth;E7=Uc-(300*moNth);E10 = 0; }
                                else { E1 = 100*moNth;E4= 200*moNth;E7=200*moNth;E10=Uc-(500*moNth); }

                                E3 = E1*E2; E6 = E4*E5; E9 = E7*E8; E12 = E10*E11; E13 = E3+E6+E9+E12;
                                E15=20*(E13+Fixed)/100 +0.02*Uc;


                                Mr=M1*month;
                                Mc=M3*month;



                                break;





                        }
                        //fixed charges calculations and display

                        fch1.setText(format(Locale.US,"%s",Load));
                        fch2.setText(format(Locale.US,"%s",rate));
                        fch3.setText(format(Locale.US,"%s",(Math.round(Fixed*100/100))));




                        mr11.setText(format(Locale.US,"%s",M1));mc11.setText(format(Locale.US,"%s",M3));
                        mr12.setText(format(Locale.US,"%s", ( Math.round(Mr*100/100))));
                        mc12.setText(format(Locale.US,"%s", Math.round(Mc*100/100)));
                        // energy charges calculations

                        ech1.setText(format(Locale.US,"%s",Math.round(E1*100/100)));ech2.setText(format(Locale.US,"%s",E2));ech3.setText(format(Locale.US,"%s",Math.round(E3*100/100)));
                        ech4.setText(format(Locale.US,"%s",Math.round(E4*100/100)));ech5.setText(format(Locale.US,"%s",E5));ech6.setText(format(Locale.US,"%s",Math.round(E6*100/100)));
                        ech7.setText(format(Locale.US,"%s",Math.round(E7*100/100)));ech8.setText(format(Locale.US,"%s",E8));ech9.setText(format(Locale.US,"%s",Math.round(E9*100/100)));
                        ech10.setText(format(Locale.US,"%s",Math.round(E10*100/100)));ech11.setText(format(Locale.US,"%s",E11));ech12.setText(format(Locale.US,"%s",Math.round(E12*100/100)));
                        ech13.setText(format(Locale.US,"%s",Math.round(E13*100/100)));


                        //Tax calculation

                        tax.setText(format(Locale.US,"%s",Math.round(E15*100/100)));
                        // grand total
                        double E16=Fixed+E13+E15+Mr+Mc;
                        gt.setText(format(Locale.US,"%s",Math.round(E16*100/100)));



                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });

    }


}

