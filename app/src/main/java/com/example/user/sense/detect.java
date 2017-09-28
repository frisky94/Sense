package com.example.user.sense;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class detect extends AppCompatActivity {

    TextToSpeech txtSpeech;
    RelativeLayout imgBtn;
    TextView textStatus;
    ImageView imageStatus;
    private EditText textCommand;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detect);

        imageStatus=(ImageView)findViewById(R.id.imgStatus);
        textStatus=(TextView)findViewById(R.id.txtStatus);
        imgBtn=(RelativeLayout)findViewById(R.id.body);
        textCommand = (EditText) this.findViewById(R.id.txtcommand);

        //Text-to-Speech Module
        txtSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener(){
            @Override
            public void onInit(int status){
                if(status != TextToSpeech.ERROR){
                    txtSpeech.setLanguage(Locale.ENGLISH);
                }
            }
        });

        //Delay Text-to-Speech Module Startup
        new Timer().schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        txtSpeech.speak(textStatus.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
                    }
                },
                100
        );

        //Clickable Image Body
        imgBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String toSpeak = textStatus.getText().toString();
                Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
                txtSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        //Value Identifier
        textCommand.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Random rand = new Random();
                int num = rand.nextInt(10)+1;
                String txtC = textCommand.getText().toString();

                switch (txtC){

                    case "1":
                        textStatus.setText("There is an ascending staircase at "+num+" in front");
                        imageStatus.setImageResource(R.drawable.stop);
                        txtSpeech.speak(textStatus.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
                        break;
                    case "2":
                        textStatus.setText("There is an descending staircase at "+num+" in front");
                        imageStatus.setImageResource(R.drawable.stop);
                        txtSpeech.speak(textStatus.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
                        break;
                    case "3":
                        textStatus.setText("There is an ascending ramp at "+num+" in front");
                        imageStatus.setImageResource(R.drawable.stop);
                        txtSpeech.speak(textStatus.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
                        break;
                    case "4":
                        textStatus.setText("There is an descending ramp at "+num+" in front");
                        imageStatus.setImageResource(R.drawable.stop);
                        txtSpeech.speak(textStatus.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
                        break;
                    case "5":
                        textStatus.setText("There is an pothole at "+num+" in front");
                        imageStatus.setImageResource(R.drawable.stop);
                        txtSpeech.speak(textStatus.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
                        break;
                    case "6":
                        textStatus.setText("There is an drainage at "+num+" in front");
                        imageStatus.setImageResource(R.drawable.stop);
                        txtSpeech.speak(textStatus.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
                        break;
                    case "7":
                        textStatus.setText("There is an loose surface at "+num+" in front");
                        imageStatus.setImageResource(R.drawable.stop);
                        txtSpeech.speak(textStatus.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
                        break;
                    case "stop":
                        textStatus.setText("Your device has stop detect");
                        imageStatus.setImageResource(R.drawable.cancel);
                        txtSpeech.speak(textStatus.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
                        new Timer().schedule(
                                new TimerTask() {
                                    @Override
                                    public void run() {
                                        finish();
                                    }
                                },
                                2000
                        );
                    default:
                        textStatus.setText("You are clear to go");
                        imageStatus.setImageResource(R.drawable.clear);
                        txtSpeech.speak(textStatus.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
                        Toast.makeText(detect.this, "default case", Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        });
    }

    public void onPause(){
        if(txtSpeech != null){
            txtSpeech.stop();
            txtSpeech.shutdown();
        }
        super.onPause();
    }

    public void detectStop(View view){
        textStatus.setText("Your device has stop detect");
        imageStatus.setImageResource(R.drawable.cancel);
        txtSpeech.speak(textStatus.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
        new Timer().schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        finish();
                    }
                },
                3000
        );
    }

}