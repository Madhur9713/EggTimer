package com.example.android.timerdemo;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    TextView textView;
    Button button;
    CountDownTimer countDownTimer;

    Boolean counterIsActive = false;

    public void updateTimer(int secondsLeft){
        int minutes = (int) secondsLeft/60;
        int seconds = secondsLeft - minutes*60;
        String secondsString = Integer.toString(seconds);
        if(seconds/10==0){
            secondsString = "0" + seconds;
        }

        textView.setText(Integer.toString(minutes)+ ":" + secondsString);

    }

    public void resetTimer(){
        counterIsActive=false;
        seekBar.setEnabled(true);
        countDownTimer.cancel();
        button.setText("Go!");
        seekBar.setProgress(30);
        textView.setText("0:30");

    }

    public void controlTimer(View view){

        if(counterIsActive==false){
            counterIsActive = true;
            seekBar.setEnabled(false);
            button.setText("Stop");

        countDownTimer = new CountDownTimer(seekBar.getProgress()*1000+100,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                updateTimer((int) millisUntilFinished/1000);

            }

            @Override
            public void onFinish() {
                textView.setText("0:00");
                MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                mediaPlayer.start();
                resetTimer();
            }
        }.start();
        }else {
            resetTimer();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         button = (Button)findViewById(R.id.controllerButton);

        seekBar = (SeekBar) findViewById(R.id.timerSeekbar);
        seekBar.setMax(600);
        seekBar.setProgress(30);

        textView = (TextView) findViewById(R.id.timerTextView);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
