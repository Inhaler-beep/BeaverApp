package com.inhaler.beaverapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class CourseAdapter extends FirebaseRecyclerAdapter<CourseModel,CourseAdapter.CourseViewHolder>
{

    private String level = "";
    private final Context mContext;


    public CourseAdapter(@NonNull FirebaseRecyclerOptions<CourseModel> options, String level, Context context) {
        super(options);
        this.level = level;
        this.mContext = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull CourseViewHolder holder, int position, @NonNull CourseModel model) {

        holder.LessonNumber.setText(model.getLessonnumber());
        holder.LessonName.setText(model.getLessontitle());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(level.equals("Beginner"))
                {
                    Toast.makeText(v.getContext(), "Beginner"+holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
                    mContext.startActivity(new Intent(mContext, CourseActivity.class));


                }
                else if(level.equals("Intermediate"))
                {

                    Toast.makeText(v.getContext(), "Intermediate", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }



    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_title,parent,false);
        if(level.equals("Intermediate"))
        {
            TextView textView = view.findViewById(R.id.lesson_number);
            textView.setBackgroundResource(R.color.primary_yello);
        }
        CourseViewHolder courseViewHolder = new CourseViewHolder(view);
        return courseViewHolder;
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder
    {

        TextView LessonNumber,LessonName;
        CardView cardView;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            LessonName = itemView.findViewById(R.id.lesson_title);
            LessonNumber = itemView.findViewById(R.id.lesson_number);
            cardView = itemView.findViewById(R.id.first_card_view);

        }
    }
}
