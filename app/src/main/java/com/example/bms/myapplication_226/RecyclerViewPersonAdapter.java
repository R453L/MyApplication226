package com.example.bms.myapplication_226;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.bms.myapplication_226.Model.Person;

import java.util.ArrayList;

public class RecyclerViewPersonAdapter extends RecyclerView.Adapter<RecyclerViewPersonAdapter.PersonViewHolder>{
    ArrayList<Person> personList;

    public RecyclerViewPersonAdapter(ArrayList<Person> personList) {
        this.personList = personList;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.person_listitem,parent,false);
        return new PersonViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder personViewHolder, int i) {
        personViewHolder.tvPersonName.setText(personList.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    void addItem(Person person,int index){
        personList.add(person);
        notifyItemInserted(index);
    }

    void removeItem(int index){
        personList.remove(index);
    }

    class PersonViewHolder extends RecyclerView.ViewHolder{
        TextView tvPersonName;
        Button btnRemove;
        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPersonName = itemView.findViewById(R.id.tvPersonName);
            btnRemove = itemView.findViewById(R.id.btnRemovePerson);
            btnRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    personList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    Snackbar.make(v,tvPersonName.getText().toString()+" removed.",Snackbar.LENGTH_SHORT).show();
                }
            });
        }

    }

}