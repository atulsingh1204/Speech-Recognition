package com.sumago.speechrecognition;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_result = findViewById(R.id.tv_result);
    }

    public void getSpeech(View view) {


        Intent ii = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        ii.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        ii.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());


        if (ii.resolveActivity(getPackageManager()) != null) {

            startActivityForResult(ii, 1);

        }else {
            Toast.makeText(this, "Your Device is not Supporting Speech Recognition!", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        switch (requestCode){

            case 1:
                if (resultCode == RESULT_OK && data!= null){
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    tv_result.setText(result.get(0));
                }
        }


    }
}