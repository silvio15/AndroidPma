package com.example.pmaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SummaryActivity extends AppCompatActivity {

    private String sImeStudenta;
    private TextView oImeStudenta;

    private String sPrezimeStudenta;
    private TextView oPrezimeStudenta;

    private String sDatumRodenjaStudenta;
    private TextView oDatumRodenjaStudenta;

    private String sNazivPredmeta;
    private TextView oNazivPredmeta;

    private String sProfesor;
    private TextView oProfesor;

    private String sAkGod;
    private TextView oAkGod;

    private String sSatiPredavanja;
    private TextView oSatiPredavanja;

    private String sSatiLV;
    private TextView oSatiLV;

    private Button oBtnPovratak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        final Bundle oExtras = getIntent().getExtras();

        sImeStudenta = oExtras.getString("imeStudenta");
        oImeStudenta = (TextView)findViewById(R.id.tvImeStudenta);
        oImeStudenta.setText(sImeStudenta);

        sPrezimeStudenta = oExtras.getString("prezimeStudenta");
        oPrezimeStudenta = (TextView)findViewById(R.id.prezimeStudenta);
        oPrezimeStudenta.setText(sPrezimeStudenta);

        sDatumRodenjaStudenta = oExtras.getString("datumRodenjaStudenta");
        oDatumRodenjaStudenta = (TextView)findViewById(R.id.datumRodenjaStudenta);
        oDatumRodenjaStudenta.setText(sDatumRodenjaStudenta);

        sNazivPredmeta = oExtras.getString("nazivPredmeta");
        oNazivPredmeta = (TextView)findViewById(R.id.tvNazivPredmeta);
        oNazivPredmeta.setText(sNazivPredmeta);
        sProfesor = oExtras.getString("profesor");
        oProfesor = (TextView)findViewById(R.id.profesor);
        oProfesor.setText(sProfesor);

        sAkGod = oExtras.getString("akGod");
        oAkGod = (TextView)findViewById(R.id.akGod);
        oAkGod.setText(sAkGod);

        sSatiPredavanja = oExtras.getString("satiPredavanja");
        oSatiPredavanja = (TextView)findViewById(R.id.satiPredavanja);
        oSatiPredavanja.setText(sSatiPredavanja);

        sSatiLV = oExtras.getString("satiLV");
        oSatiLV = (TextView)findViewById(R.id.satiLV);
        oSatiLV.setText(sSatiLV);


        oBtnPovratak = (Button)findViewById(R.id.btnPovratak);
        oBtnPovratak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent oPovratakNaPočetnu = new Intent(getApplicationContext(), PersonalInfoActivity.class);
                oPovratakNaPočetnu.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(oPovratakNaPočetnu);

            }
        });
    }
}