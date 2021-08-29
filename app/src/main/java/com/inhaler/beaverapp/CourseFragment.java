package com.inhaler.beaverapp;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moos.library.HorizontalProgressView;

public class CourseFragment extends Fragment {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course, container, false);

        HorizontalProgressView horizontalProgressView = (HorizontalProgressView) view.findViewById(R.id.progressView_horizontal);
        horizontalProgressView.setStartProgress(0);
        horizontalProgressView.setEndProgress(80);
        horizontalProgressView.setStartColor(Color.parseColor("#78e08f"));
        horizontalProgressView.setEndColor(Color.parseColor("#38ada9"));
        horizontalProgressView.setTrackWidth(30);
        horizontalProgressView.setProgressTextColor(Color.parseColor("#0a3d62"));
        horizontalProgressView.setProgressDuration(2000);
        horizontalProgressView.setTrackEnabled(true);
        horizontalProgressView.setProgressCornerRadius(20);
        horizontalProgressView.setProgressTextPaddingBottom(12);
        horizontalProgressView.startProgressAnimation();

        HorizontalProgressView horizontalProgressViewInter = (HorizontalProgressView) view.findViewById(R.id.progressView_horizontal_inter);
        horizontalProgressViewInter.setStartProgress(0);
        horizontalProgressViewInter.setEndProgress(40);
        horizontalProgressViewInter.setStartColor(Color.parseColor("#fad390"));
        horizontalProgressViewInter.setEndColor(Color.parseColor("#fa983a"));
        horizontalProgressViewInter.setTrackWidth(30);
        horizontalProgressViewInter.setProgressTextColor(Color.parseColor("#0a3d62"));
        horizontalProgressViewInter.setProgressDuration(2000);
        horizontalProgressViewInter.setTrackEnabled(true);
        horizontalProgressViewInter.setProgressCornerRadius(20);
        horizontalProgressViewInter.setProgressTextPaddingBottom(12);
        horizontalProgressViewInter.startProgressAnimation();



        return view;

    }
}