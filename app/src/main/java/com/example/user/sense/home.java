package com.example.user.sense;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class home extends AppCompatActivity {

    TextToSpeech txtSpeech;
    RelativeLayout imgBtn;
    TextView textStatus;
    ImageView imageStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        imageStatus=(ImageView)findViewById(R.id.imgStatus);
        textStatus=(TextView)findViewById(R.id.txtStatus);
        imgBtn=(RelativeLayout)findViewById(R.id.body);

        //Text-to-Speech Module
        txtSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener(){
            @Override
            public void onInit(int status){
                if(status != TextToSpeech.ERROR){
                    txtSpeech.setLanguage(Locale.ENGLISH);
                }
            }
        });

        //Clickable Image Body
        imgBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String toSpeak = textStatus.getText().toString();
                Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
                txtSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }

    public void detectStart(View view){
        startActivity(new Intent(home.this, detect.class));
    }

    public void onPause(){
        if(txtSpeech != null){
            txtSpeech.stop();
            txtSpeech.shutdown();
        }
        super.onPause();
    }
}