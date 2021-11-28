package com.example.pmaapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class SummaryFragment extends Fragment {

    private String sIme;
    private String sPrezime;
    private String sDatum;
    private String sPredmet;
    private String sProfesor;
    private String sAkGod;
    private String sSatiPredavanja;
    private String sSatiLva;

    private TextView oIme;
    private TextView oPrezime;
    private TextView oDatum;
    private TextView oPredmet;
    private TextView oProfesor;
    private TextView oAkGod;
    private TextView oSatiPredavanja;
    private TextView oSatiLva;
    private Button oSumButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_summary,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        oIme = (TextView)view.findViewById(R.id.labelIme);
        oPrezime = (TextView)view.findViewById(R.id.labelPrezime);
        oDatum = (TextView)view.findViewById(R.id.labelDatum);
        oPredmet = (TextView)view.findViewById(R.id.labelPredmet);
        oProfesor = (TextView)view.findViewById(R.id.labelProfesor);
        oAkGod = (TextView)view.findViewById(R.id.labelAkGod);
        oSatiPredavanja = (TextView)view.findViewById(R.id.labelSatiPredavanja);
        oSatiLva = (TextView)view.findViewById(R.id.labelSatiLv);
        oSumButton = (Button)view.findViewById(R.id.summaryButton);

        oSumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDataStorage myDataStorage = MyDataStorage.getInstance();
                Student student = new Student(sIme, sPrezime, sPredmet);
                myDataStorage.setStudents(student);
                Intent intent = new Intent(getActivity(), StartActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    public void updatePersonalInfo(String ime, String prezime, String datum){
        sIme = ime;
        sPrezime = prezime;

        oIme.setText(ime);
        oPrezime.setText(prezime);
        oDatum.setText(datum);

    }

    public void updateStudentInfo(String predmet, String ime_profesora, String akademska_godina, String sati_predavanja, String sati_LV) {
        sPredmet = predmet;

        oPredmet.setText(predmet);
        oProfesor.setText(ime_profesora);
        oAkGod.setText(akademska_godina);
        oSatiPredavanja.setText(sati_predavanja);
        oSatiLva.setText(sati_LV);
    }
}