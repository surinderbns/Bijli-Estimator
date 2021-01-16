package com.example.bijliestimator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

import static java.lang.Math.ceil;
import static java.lang.String.format;

public class loadcalculator extends AppCompatActivity {
    private TextView lp,fp, ws, ps,  hp, ps2,acn,ldn,result,btn;
    private EditText acp;
    private EditText ldp;
    private EditText ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadcalculator);
        ll = findViewById(R.id.ldlp);
        lp =  findViewById(R.id.etlp);
        fp =  findViewById(R.id.etfp);
        ws = findViewById(R.id.etws);
        ps =  findViewById(R.id.etps);
        hp =  findViewById(R.id.etmotor);
        ps2 = findViewById(R.id.etps2);
        acp =  findViewById(R.id.etac3);
        acn = findViewById(R.id.etac2);
        ldp = findViewById(R.id.etmotor3);
        ldn =  findViewById(R.id.etmotor2);
        btn =  findViewById(R.id.tvmessage);
        Button decLp = findViewById(R.id.declp);
        Button incLp = findViewById(R.id.inclp);
        Button decFp = findViewById(R.id.decfp);
        Button incFp = findViewById(R.id.incfp);
        Button decWs = findViewById(R.id.decws);
        Button incWs = findViewById(R.id.incws);
        Button decPs = findViewById(R.id.decps);
        Button incPs = findViewById(R.id.incps);
        Button decPs2 = findViewById(R.id.decps2);
        Button incPs2 = findViewById(R.id.incps2);
        Button decAc2 = findViewById(R.id.decac2);
        Button incAc2 = findViewById(R.id.incac2);
        Button decMotor = findViewById(R.id.decmotor);
        Button incMotor = findViewById(R.id.incmotor);
        Button decMotor2 = findViewById(R.id.decmotor2);
        Button incMotor2 = findViewById(R.id.incmotor2);
        result = findViewById(R.id.tvload);
        if (ll.getText().toString().length() == 0) {
            ll.setText(R.string.def_lp);
        }

        {
            lp.setText(R.string.def_lpn);
        }
        {
            fp.setText(R.string.def_fpn);
        }
        {
            ws.setText(R.string.def_wsn);
        }
        {
            ps.setText(R.string.def_psn);
        }
        {
            hp.setText(R.string.def_hp);
        }
        {
            ps2.setText(R.string.def_ldn);
        }
        if (acp.getText().toString().length() == 0) {
            acp.setText(R.string.def_acp);
        }
        {
            acn.setText(R.string.def_acn);
        }

        if (ldp.getText().toString().length() == 0) {
            ldp.setText(R.string.def_ldp);
        }
        {
            ldn.setText(R.string.def_ldn);
        }

        incLp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n1 = Integer.parseInt(lp.getText().toString())+1;
                lp.setText(format(Locale.US,"%d", n1));

            }
        });
        decLp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n2 = Integer.parseInt(lp.getText().toString());
                if (n2 > 0 ){n2 = n2-1;}
                lp.setText(format(Locale.US,"%d", n2));
            }
        });
        incFp.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v) {
                int n3 = Integer.parseInt(fp.getText().toString())+1;
                fp.setText(format(Locale.US,"%d", n3));
            }
        });
        decFp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n4 = Integer.parseInt(fp.getText().toString());
                if (n4 > 0 ){n4 = n4-1;}
                fp.setText(format(Locale.US,"%d", n4));
            }
        });
        incWs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n5 = Integer.parseInt(ws.getText().toString())+1;
                ws.setText(format(Locale.US,"%d", n5));
            }
        });
        decWs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n6 = Integer.parseInt(ws.getText().toString());
                if (n6 > 0 ){n6 = n6-1;}
                ws.setText(format(Locale.US,"%d", n6));
            }
        });
        incPs.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int n7 = Integer.parseInt(ps.getText().toString())+1;
                ps.setText(format(Locale.US,"%d", n7));
            }
        });
        decPs.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int n8 = Integer.parseInt(ps.getText().toString());
                if (n8 > 0 ){n8 = n8-1;}
                ps.setText(format(Locale.US,"%d", n8));
            }
        });

        incPs2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int n13 = Integer.parseInt(ps2.getText().toString())+1;
                ps2.setText(format(Locale.US,"%d", n13));
            }
        });
        decPs2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n14 = Integer.parseInt(ps2.getText().toString());
                if (n14 > 0 ){n14 = n14-1;}
                ps2.setText(format(Locale.US,"%d", n14));
            }
        });

        incAc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n17 = Integer.parseInt(acn.getText().toString())+1;
                acn.setText(format(Locale.US,"%d", n17));
            }
        });
        decAc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n18 = Integer.parseInt(acn.getText().toString());
                if (n18 > 0 ){n18 = n18-1;}
                acn.setText(format(Locale.US,"%d", n18));
            }
        });
        incMotor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double n19 = Double.parseDouble(hp.getText().toString())+0.50;
                hp.setText(format(Locale.US,"%s", n19));
            }
        });
        decMotor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double n20 = Double.parseDouble(hp.getText().toString());
                if (n20 > 0 ){n20 = n20-0.50;}
                hp.setText(format(Locale.US,"%s", n20));
            }
        });
        incMotor2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int n21 = Integer.parseInt(ldn.getText().toString())+1;
                ldn.setText(format(Locale.US,"%d", n21));
            }
        });
        decMotor2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n22 = Integer.parseInt(ldn.getText().toString());
                if (n22 > 0 ){n22 = n22-1;}
                ldn.setText(format(Locale.US,"%d", n22));
            }
        });


        Spinner sp2 = findViewById(R.id.spn_lan);
        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ll.getText().toString().length() == 0) {
                            ll.setText(R.string.def_lp);
                        }

                        if (acp.getText().toString().length() == 0) {
                            acp.setText(R.string.def_acp);
                        }
                        if (ldp.getText().toString().length() == 0) {
                            ldp.setText(R.string.def_ldp);
                        }

                        double a,al,b,c,d,f,g,h,i,j,k,l,m;

                        a = Double.parseDouble(lp.getText().toString());
                        al = Double.parseDouble(ll.getText().toString());
                        b = Double.parseDouble(fp.getText().toString());
                        c = Double.parseDouble(ws.getText().toString());
                        d = Double.parseDouble(ps.getText().toString());
                        f = Double.parseDouble(hp.getText().toString());
                        g = Double.parseDouble(ps2.getText().toString());
                        h = Double.parseDouble(acp.getText().toString());
                        i = Double.parseDouble(acn.getText().toString());
                        j = Double.parseDouble(ldp.getText().toString());
                        k = Double.parseDouble(ldn.getText().toString());

                        switch (position) {
                            case 1:

                                l = ((al * a ) + (60 * b) + (60 * ceil(c / 3)) + (1000 * ceil(d / 2))  + (f * 746) + (6000 * ceil(g / 2)) + (h * i ) + (j * k))/1000;
                                result.setText(format(Locale.US,"%s", l));


                                break;
                            case 0:
                            default:
                                m = ((al * ceil(a / 2)) + (60 * ceil(b / 3)) + (60 * ceil(c / 4)) + (1000 * ceil(d / 4))  + (f * 746) + (6000 * ceil(g / 2)) + (h * ceil(i / 2)) + (j * k)) / 1000;
                                result.setText(format(Locale.US,"%s", m));
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


