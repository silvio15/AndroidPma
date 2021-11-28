package com.example.pmaapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class StudentInfoFragment extends Fragment {

    private static final String TAG = "MyActivity";
    private String ime;
    private String prezime;
    private String datum;

    private TextInputLayout oPredmet;
    private TextInputLayout oProfesor;
    private TextInputLayout oAkGod;
    private TextInputLayout oSatiPredavanja;
    private TextInputLayout oSatiLva;
    private Button oDaljeButton;


    private String predmet;
    private String profesor;
    private String akGod;
    private String satiPredavanja;
    private String satiLva;
    private StudentInfoListener studentInfoListener;

    public interface StudentInfoListener {
        void onStudentInfoSent(String predmet, String ime_profesora, String akademska_godina, String sati_predavanja, String sati_LV);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_student_info,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        oPredmet = view.findViewById(R.id.inputPredmet);
        oProfesor = view.findViewById(R.id.inputProfesor);
        oAkGod=view.findViewById(R.id.inputAkGod);
        oSatiPredavanja=view.findViewById(R.id.inputSatiPredavanja);
        oSatiLva=view.findViewById(R.id.inputSatiLv);
        oDaljeButton=view.findViewById(R.id.studentInfoButton);

        oPredmet.getEditText().addTextChangedListener(personalInfoWatcher);
        oProfesor.getEditText().addTextChangedListener(personalInfoWatcher);
        oAkGod.getEditText().addTextChangedListener(personalInfoWatcher);
        oSatiPredavanja.getEditText().addTextChangedListener(personalInfoWatcher);
        oSatiLva.getEditText().addTextChangedListener(personalInfoWatcher);

        oDaljeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateNewRecordActivity.setCurrentItem (2, true);
            }
        });
    }

    private TextWatcher personalInfoWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            predmet = oPredmet.getEditText().getText().toString();
            profesor = oProfesor.getEditText().getText().toString();
            akGod = oAkGod.getEditText().getText().toString();
            satiPredavanja = oSatiPredavanja.getEditText().getText().toString();
            satiLva = oSatiLva.getEditText().getText().toString();

            studentInfoListener.onStudentInfoSent(predmet, profesor, akGod, satiPredavanja, satiLva);
        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof  StudentInfoListener) {
            studentInfoListener = (StudentInfoListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        studentInfoListener = null;
    }
}