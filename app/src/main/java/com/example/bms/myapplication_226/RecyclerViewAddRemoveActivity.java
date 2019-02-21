package com.example.bms.myapplication_226;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.bms.myapplication_226.Model.Person;

import java.util.ArrayList;

public class RecyclerViewAddRemoveActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    EditText edtPersonName;
    Button btnAddPerson;
    ArrayList<Person> personList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_add_remove);

        recyclerView = findViewById(R.id.personRecyclerView);
        adapter = new RecyclerViewPersonAdapter(personList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        edtPersonName = findViewById(R.id.edtPersonName);
        btnAddPerson = findViewById(R.id.btnAddPerson);

        btnAddPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtPersonName.getText().toString().trim();
                if(!name.equals("")){
                    Snackbar.make(v,name+" added.",Snackbar.LENGTH_SHORT).show();
                    personList.add(new Person(name));
                    adapter.notifyDataSetChanged();
                    recyclerView.scrollToPosition(adapter.getItemCount()-1);
                }else{
                    Snackbar.make(v,"Type a name!",Snackbar.LENGTH_SHORT).show();
                }
            }
        });
        //TODO EDIT ITEM
    }
}
