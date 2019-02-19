package com.example.bms.myapplication_226;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bms.myapplication_226.Model.Student;
import com.example.bms.myapplication_226.ViewHolder.StudentViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.Random;

public class RecyclerViewActivity extends AppCompatActivity {

    //View view;

    private static final String TAG = "HomeFragment";
    private FirebaseAuth mAuth;

    EditText studentName, studentReg;
    ImageView addStudent;
    Student student;

    DatabaseReference studentRef;

    RecyclerView studentRecyclerView;

    FirebaseRecyclerOptions<Student> studentOptions;
    FirebaseRecyclerAdapter<Student, StudentViewHolder> studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);


        mAuth = FirebaseAuth.getInstance();

        addStudent = findViewById(R.id.add_student);
        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createStudent();
            }
        });

        studentRecyclerView = findViewById(R.id.recycler_view_student);
        //studentRecyclerView.setHasFixedSize(true);

        Query userQuery = FirebaseDatabase.getInstance()
                .getReference()
                .child("students")
                //.orderByChild("timestamp/created_at")
                .limitToLast(50);

        studentOptions = new FirebaseRecyclerOptions.Builder<Student>()
                .setQuery(userQuery,Student.class)
                .build();

        studentAdapter = new FirebaseRecyclerAdapter<Student, StudentViewHolder>(studentOptions) {
            @Override
            protected void onBindViewHolder(@NonNull final StudentViewHolder holder, int position, @NonNull final Student model) {
                Picasso.get().load(model.getsImg()).into(holder.sImg, new Callback() {
                    @Override
                    public void onSuccess() {
                        Log.d(TAG, "onBindViewHolder: studentAdapter: Picasso success");
                    }

                    @Override
                    public void onError(Exception e) {
                        Log.d(TAG, "onBindViewHolder: studentAdapter: Picasso error"+e.getMessage());

                    }
                });

                holder.sName.setText(model.getsName());
                holder.sReg.setText(model.getsReg());
            }

            @NonNull
            @Override
            public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.student_listitem, parent, false);

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(TAG, "onBindViewHolder: studentAdapter: clicked on view");
                    }
                });
                return new StudentViewHolder(view);
            }
        };

        final LinearLayoutManager studentLayoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL,true);
        studentLayoutManager.setStackFromEnd(true);

        studentRecyclerView.setLayoutManager(studentLayoutManager);
        studentRecyclerView.setAdapter(studentAdapter);

//        // Scroll to bottom
//        studentAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
//            @Override
//            public void onItemRangeInserted(int positionStart, int itemCount) {
//                studentLayoutManager.smoothScrollToPosition(studentRecyclerView, null, studentAdapter.getItemCount());
//            }
//        });


        studentRef = FirebaseDatabase.getInstance().getReference("students");
        studentRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if(dataSnapshot.hasChild(mAuth.getCurrentUser().getUid())){
//                    Log.d("MainActivity","user exists.");
//                }else{
//                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        if(studentAdapter!=null) studentAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        if(studentAdapter!=null) studentAdapter.stopListening();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(studentAdapter!=null) studentAdapter.startListening();
    }

    public void createStudent(){
        studentName = findViewById(R.id.student_name_et);
        studentReg = findViewById(R.id.student_reg_et);
        final String sName = studentName.getText().toString().trim();
        final String sReg = studentReg.getText().toString().trim();
        final DatabaseReference studentRef = FirebaseDatabase.getInstance().getReference("students");

        if(!sName.isEmpty()){
            final String id = studentRef.push().getKey();

            String studentImageUrl = mAuth.getCurrentUser().getPhotoUrl().toString();

            studentImageUrl = studentImageUrl.replace("s96-c", "s400-c");


            student = new Student(id,
                    sName,
                    studentImageUrl,
                    sReg
                    );

            studentRef.child(id).setValue(student).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    final DatabaseReference mRef = FirebaseDatabase.getInstance()
                            .getReference("students").child(id);
                    mRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    studentName.setText("");
                    studentReg.setText("");
                    Toast.makeText(getApplicationContext(),
                            "student "+sName+" created.", Toast.LENGTH_SHORT).show();
                }
            });

        }else {
            Toast.makeText(getApplicationContext(),
                    "enter a name", Toast.LENGTH_SHORT).show();
        }
    }
}
