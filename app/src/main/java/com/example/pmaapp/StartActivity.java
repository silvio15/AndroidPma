package com.example.pmaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import java.util.List;

public class StartActivity extends AppCompatActivity {

    private Button oBtnKreirajStudenta;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        mRecyclerView = (RecyclerView) findViewById(R.id.popisStudenata);


        MyDataStorage spremnik = MyDataStorage.getInstance();
        List<Object> students = spremnik.getStudents();
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new StudentAdapter(students);
        mRecyclerView.setAdapter(mAdapter);

        oBtnKreirajStudenta = (Button)findViewById(R.id.btnKreiraj);
        oBtnKreirajStudenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent oUpisiNazivPredmetaIntent = new Intent(getApplicationContext(), PersonalInfoActivity.class);
                startActivity(oUpisiNazivPredmetaIntent);
            }
        });
    }


}