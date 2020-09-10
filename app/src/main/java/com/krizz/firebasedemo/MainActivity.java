package com.krizz.firebasedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        myRef.setValue("Message value");

        DatabaseReference nameRef = FirebaseDatabase.getInstance().getReference("name");
        nameRef.setValue("Anandhakrishnan");

        String profileNode = "Profile";
        DatabaseReference profileRef = FirebaseDatabase.getInstance().getReference(profileNode);
        String id = profileRef.push().getKey();
        profileRef.child("name").setValue("Sreeraj");

        nameRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.getValue(String.class);
                textView.setText(name);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("onCancelled","Failed to read value");
            }
        });
    }
}