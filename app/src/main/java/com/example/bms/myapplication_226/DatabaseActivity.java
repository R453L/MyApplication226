package com.example.bms.myapplication_226;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DatabaseActivity extends AppCompatActivity {

    DatabaseHelper mDB;
    EditText edtName, edtSurname, edtMarks, edtID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        mDB = new DatabaseHelper(this);

        edtID = findViewById(R.id.editText_id);
        edtName = findViewById(R.id.editText_name);
        edtMarks = findViewById(R.id.editText_Marks);
        edtSurname = findViewById(R.id.editText_surname);

        findViewById(R.id.button_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddData();
            }
        });
        findViewById(R.id.button_viewAll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewAllData();
            }
        });
        findViewById(R.id.button_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateData();
            }
        });
        findViewById(R.id.button_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteData();
            }
        });
    }

    private void DeleteData() {
        Integer deletedRows = mDB.deleteData(edtID.getText().toString().trim());
        if(deletedRows > 0)
            Toast.makeText(DatabaseActivity.this,"Data deleted",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(DatabaseActivity.this,"Data not deleted",Toast.LENGTH_SHORT).show();
    }

    private void UpdateData() {
        boolean isUpdated = mDB.updateData(
                edtID.getText().toString().trim(),
                edtName.getText().toString().trim(),
                edtSurname.getText().toString().trim(),
                edtMarks.getText().toString().trim()
        );
        if(isUpdated)
            Toast.makeText(DatabaseActivity.this,"Data updated",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(DatabaseActivity.this,"Data not updated",Toast.LENGTH_SHORT).show();

    }

    private void ViewAllData() {
        Cursor res = mDB.getAllData();
        if (res.getCount() == 0){
            showMessage("Error","Nothing found!");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()){
            buffer.append("ID : "+res.getString(0)+"\n");
            buffer.append("Name : "+res.getString(1)+" ");
            buffer.append(res.getString(2)+"\n");
            buffer.append("Marks: "+res.getString(3)+"\n\n");
        }
        showMessage("Data",buffer.toString());
    }

    private void AddData() {
        boolean isInserted = mDB.insertData(
                edtName.getText().toString().trim(),
                edtSurname.getText().toString().trim(),
                edtMarks.getText().toString().trim()
        );
        if(isInserted)
            Toast.makeText(DatabaseActivity.this,"Data Inserted",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(DatabaseActivity.this,"Data not Inserted",Toast.LENGTH_SHORT).show();
    }

    private void showMessage(String title, String message) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setCancelable(true);
        adb.setTitle(title);
        adb.setMessage(message);
        adb.show();
    }
}