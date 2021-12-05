package com.example.pmaapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import static android.app.Activity.RESULT_OK;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;


public class PersonalInfoFragment extends Fragment {
    private static final int REQUEST_IMAGE_CAPTURE = 1;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ImageView imageView;
    private TextInputLayout oIme;
    private TextInputLayout oPrezime;
    private EditText oDatum;
    private Button oButton;

    private String ime;
    private String prezime;
    private String datum;
    private PersonalInfoListener personalInfoListener;

    public interface PersonalInfoListener {
        void onPersonalInfoSent(String ime, String prezime, String datum);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_personal_info,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageView = view.findViewById(R.id.imageView);
        oIme = (TextInputLayout)view.findViewById(R.id.inputIme);
        oPrezime = (TextInputLayout)view.findViewById(R.id.inputPrezime);
        oDatum = (EditText)view.findViewById(R.id.inputDatum);
        oButton = (Button)view.findViewById(R.id.personaInfoButton);

        oIme.getEditText().addTextChangedListener(personalInfoWatcher);
        oPrezime.getEditText().addTextChangedListener(personalInfoWatcher);
        oDatum.addTextChangedListener(personalInfoWatcher);

        oButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateNewRecordActivity.setCurrentItem (1, true);
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
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
            ime = oIme.getEditText().getText().toString();
            prezime = oPrezime.getEditText().getText().toString();
            datum = oDatum.getText().toString();

            personalInfoListener.onPersonalInfoSent(ime, prezime, datum);
        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof  PersonalInfoListener) {
            personalInfoListener = (PersonalInfoListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        personalInfoListener = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }
}
