package com.example.pmaapp;

import static android.util.Log.i;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static java.lang.StrictMath.log;


public class StudentInfoFragment extends Fragment implements
        Callback<CourseResponse>, AdapterView.OnItemSelectedListener  {

    private static final String TAG = "MyActivity";
    private CourseResponse courseResponse  = new CourseResponse();
    private ArrayList<Course> courses = new ArrayList<>();
    private ArrayList<String> predmeti = new ArrayList<>();
    private ArrayList<Instructor> instruktori = new ArrayList<>();
    private ArrayList<String> nazivi_instruktora = new ArrayList<>();
    private String ime;
    private String prezime;
    private String datum;

    private TextInputLayout oAkGod;
    private TextInputLayout oSatiPredavanja;
    private TextInputLayout oSatiLva;
    private Button oDaljeButton;
    private Spinner oSpPredmet;
    private Spinner oSpProfesor;

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
        oDaljeButton=view.findViewById(R.id.studentInfoButton);
        oAkGod=view.findViewById(R.id.inputAkGod);
        oSatiPredavanja=view.findViewById(R.id.inputSatiPredavanja);
        oSatiLva=view.findViewById(R.id.inputSatiLv);
        oSpPredmet = (Spinner)view.findViewById(R.id.spPredmet);
        oSpProfesor = (Spinner)view.findViewById(R.id.spProfesor);

        oAkGod.getEditText().addTextChangedListener(personalInfoWatcher);
        oSatiPredavanja.getEditText().addTextChangedListener(personalInfoWatcher);
        oSatiLva.getEditText().addTextChangedListener(personalInfoWatcher);

        oDaljeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateNewRecordActivity.setCurrentItem (2, true);
            }
        });

        ApiManager.getInstance().service().getCourses().enqueue(this);
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

    @Override
    public void onResponse(@NonNull Call<CourseResponse> call, @NonNull
            Response<CourseResponse> response) {
        if (response.isSuccessful() && response.body() != null){
            courseResponse = response.body();
            courses = courseResponse.getCourses();

            for(Course course : courses){
                if(course.getTitle() != null && !course.getTitle().matches("")){
                    predmeti.add(course.getTitle());
                    i(course.getTitle(), "sadržaj coursa "+course.getTitle());
                }
            }

            ArrayAdapter<String> adapterPredmet = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, predmeti);
            oSpPredmet.setAdapter(adapterPredmet);
            oSpPredmet.setOnItemSelectedListener(this);
        }
    }

    @Override
    public void onFailure(Call<CourseResponse> call, Throwable t) {
        t.printStackTrace();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(adapterView.getId() == R.id.spPredmet)
        {
            Log.d(TAG, "onItemSelected: " + oSpPredmet.getSelectedItem());

            for(Course course : courses){
                if(oSpPredmet.getSelectedItem() == course.getTitle()){
                    instruktori = course.getInstructors();

                    if(instruktori != null){
                        nazivi_instruktora.clear();

                        for(Instructor instructor : instruktori){
                            nazivi_instruktora.add(instructor.getName());

                            Log.d(TAG, "onResponse: " + instructor.getName());
                        }
                    }
                }
            }

            ArrayAdapter<String> adapterProfesor = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, nazivi_instruktora);
            oSpProfesor.setAdapter(adapterProfesor);
            oSpProfesor.setOnItemSelectedListener(this);

            predmet = oSpPredmet.getSelectedItem().toString();
            studentInfoListener.onStudentInfoSent(predmet, profesor, akGod, satiPredavanja, satiLva);
        }
        else if(adapterView.getId() == R.id.spProfesor)
        {
            profesor = oSpProfesor.getSelectedItem().toString();
            studentInfoListener.onStudentInfoSent(predmet, profesor, akGod, satiPredavanja, satiLva);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}
