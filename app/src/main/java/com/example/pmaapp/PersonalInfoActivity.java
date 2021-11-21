package com.example.pmaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.google.android.material.textfield.TextInputEditText;

public class PersonalInfoActivity extends AppCompatActivity {

    private String sImeStudenta;
    private String sPrezimeStudenta;
    private String sDatumRodenjaStudenta;

    private TextInputEditText oTietUpisiIme;
    private TextInputEditText oTietUpisiPrezime;
    private EditText oTietUpisiDatumRodenja;

    private Button oBtnUpisi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        oTietUpisiIme = (TextInputEditText)findViewById(R.id.tietImeStudenta);
        oTietUpisiPrezime = (TextInputEditText)findViewById(R.id.tietPrezimeStudenta);
        oTietUpisiDatumRodenja = (EditText)findViewById(R.id.plain_text_input);


        oBtnUpisi = (Button)findViewById(R.id.btnUpisi);
        oBtnUpisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sImeStudenta = oTietUpisiIme.getText().toString();
                sPrezimeStudenta = oTietUpisiPrezime.getText().toString();
                sDatumRodenjaStudenta = oTietUpisiDatumRodenja.getText().toString();

                Intent oUpisiPersonalInfoStudentaIntent = new Intent(getApplicationContext(), StudentInfoActivity.class);

                oUpisiPersonalInfoStudentaIntent.putExtra("imeStudenta", sImeStudenta);
                oUpisiPersonalInfoStudentaIntent.putExtra("prezimeStudenta", sPrezimeStudenta);
                oUpisiPersonalInfoStudentaIntent.putExtra("datumRodenjaStudenta", sDatumRodenjaStudenta);
                startActivity(oUpisiPersonalInfoStudentaIntent);
            }
        });
    }
}