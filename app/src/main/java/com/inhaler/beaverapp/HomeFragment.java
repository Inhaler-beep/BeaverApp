package com.inhaler.beaverapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Arrays;


public class HomeFragment extends Fragment {

  FirebaseAuth mAuth;
  Spinner spinner1;
  RecyclerView recyclerView;
  CourseAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        spinner1 = (Spinner) view.findViewById(R.id.difficulty_level);
        recyclerView = (RecyclerView) view.findViewById(R.id.course_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        String[] levels = {"Beginner","Intermediate","Advanced"};
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getContext(),R.layout.style_spinner, levels);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinner1.setAdapter(spinnerArrayAdapter);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 if(id == 1)
                {
                    Query query = FirebaseDatabase.getInstance()
                            .getReference()
                            .child("Levels")
                            .child("Intermediate");
                   SetRecyclerOptions(query,"Intermediate");
                }else if(id == 0)
                 {
                     Query query = FirebaseDatabase.getInstance()
                             .getReference()
                             .child("Levels")
                             .child("Beginner");
                     SetRecyclerOptions(query,"Beginner");
                 }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("Levels")
                .child("Intermediate");
        query.keepSynced(true);

        FirebaseRecyclerOptions<CourseModel> options =
                new FirebaseRecyclerOptions.Builder<CourseModel>()
                        .setQuery(query, CourseModel.class)
                        .build();

        adapter = new CourseAdapter(options,"Beginner");
        recyclerView.setAdapter(adapter);
        return view;
    }

    private void SetRecyclerOptions(Query query,String level)
    {
        query.keepSynced(true);
        FirebaseRecyclerOptions<CourseModel> options =
                new FirebaseRecyclerOptions.Builder<CourseModel>()
                        .setQuery(query, CourseModel.class)
                        .build();
        adapter = new CourseAdapter(options,level);
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}