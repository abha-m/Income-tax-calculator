package com.example.abha.incometax1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button calculate = (Button) findViewById(R.id.calculate);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText income = (EditText) findViewById(R.id.income);
                EditText policy = (EditText) findViewById(R.id.policy);
                EditText mediclaim = (EditText) findViewById(R.id.mediclaim);
                EditText princi = (EditText) findViewById(R.id.princi);
                EditText interest = (EditText) findViewById(R.id.interest);
                EditText education = (EditText) findViewById(R.id.edu);
                TextView result = (TextView) findViewById(R.id.result);

                int inc = Integer.parseInt(String.valueOf(income.getText()));
                int pol = Integer.parseInt(String.valueOf(policy.getText()));
                int med = Integer.parseInt(String.valueOf(mediclaim.getText()));
                int homeloanp = Integer.parseInt(String.valueOf(princi.getText()));
                int homeloani = Integer.parseInt(String.valueOf(interest.getText()));
                int edu = Integer.parseInt(String.valueOf(education.getText()));

                int polt = pol, medt = med, loanpt = homeloanp, loanit = homeloani, edut = edu;

                if(pol > 100000)
                    polt = 100000;
                if(med > 50000)
                    medt = 50000;
                if(homeloanp > 300000)
                    loanpt = 300000;
                if(homeloani > 150000)
                    loanit = 150000;
                if(edu > 50000)
                    edut = 50000;

                int taxable = inc - polt - medt - loanpt - loanit - edut;
                double baseval = calc(taxable);
                double cessval = baseval * 0.3;
                double finval = baseval * 1.3;

                Log.i("base", Double.toString(baseval));

                result.setText("Base value : " + Double.toString(baseval) + "\n" +
                "Cess value : " + Double.toString(cessval) + "\n" +
                "Final value : " + Double.toString(finval));

            }

            public double calc(int t) {
                double tax;
                if (t > 0 && t <= 250000) {
                    tax = 0;
                } else if (t > 250000 && t <= 500000) {
                    tax = (t - 250000) * 0.1;
                } else if (t > 500000 && t <= 750000) {
                    tax = (t - 500000) * 0.2 + 250000 * 0.1;
                } else if (t > 750000 && t <= 1000000) {
                    tax = (t - 750000) * 0.3 + 250000 * (0.2 + 0.1);
                } else {
                    tax = (t - 1000000) * 0.35 + 250000 * (0.3 + 0.2 + 0.1);
                }
                return tax;
            }

        });

    }


}
