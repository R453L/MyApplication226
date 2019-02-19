package com.example.bms.myapplication_226.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.bms.myapplication_226.R;
import com.mikhaellopez.circularimageview.CircularImageView;

public class StudentViewHolder extends RecyclerView.ViewHolder {
    public CircularImageView sImg;
    public TextView sName, sReg;

    public StudentViewHolder(@NonNull View itemView){
        super(itemView);
        sImg = itemView.findViewById(R.id.student_image_view);
        sName = itemView.findViewById(R.id.student_name_view);
        sReg = itemView.findViewById(R.id.student_reg_view);
    }
}
