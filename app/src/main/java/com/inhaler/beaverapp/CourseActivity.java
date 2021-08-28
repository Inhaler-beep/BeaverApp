package com.inhaler.beaverapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.jean.jcplayer.JcPlayerManagerListener;
import com.example.jean.jcplayer.general.JcStatus;
import com.example.jean.jcplayer.model.JcAudio;
import com.example.jean.jcplayer.view.JcPlayerView;

import java.util.ArrayList;

public class CourseActivity extends AppCompatActivity {

    private JcPlayerView jcPlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        jcPlayerView = (JcPlayerView) findViewById(R.id.jcplayer);
        ArrayList<JcAudio> jcAudios = new ArrayList<>();
        //JcAudio jcAudio1 = JcAudio.createFromURL("title","https://firebasestorage.googleapis.com/v0/b/beaver-education.appspot.com/o/Audios%2FPodcast%206%20Final.mp3?alt=media&token=b0175959-7a43-4383-9abc-3ae9cd050563");
        jcAudios.add(JcAudio.createFromURL("Podcast Audio", "https://firebasestorage.googleapis.com/v0/b/beaver-education.appspot.com/o/Audios%2FPodcast%206%20Final.mp3?alt=media&token=b0175959-7a43-4383-9abc-3ae9cd050563"));
//        jcPlayerView.playAudio(jcAudio1);
        jcPlayerView.createNotification();

        jcPlayerView.initPlaylist(jcAudios, new JcPlayerManagerListener() {
             @Override
             public void onPreparedAudio(@NonNull JcStatus jcStatus) {

             }

             @Override
             public void onCompletedAudio() {

             }

             @Override
             public void onPaused(@NonNull JcStatus jcStatus) {
             }

             @Override
             public void onContinueAudio(@NonNull JcStatus jcStatus) {

             }

             @Override
             public void onPlaying(@NonNull JcStatus jcStatus) {
             }

             @Override
             public void onTimeChanged(@NonNull JcStatus jcStatus) {

             }

             @Override
             public void onStopped(@NonNull JcStatus jcStatus) {

             }

             @Override
             public void onJcpError(@NonNull Throwable throwable) {

             }
         });





    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        jcPlayerView.pause();
        jcPlayerView.kill();
        Intent homeIntent = new Intent(CourseActivity.this,MainActivity.class);
        startActivity(homeIntent);
    }


}