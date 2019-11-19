package com.example.hw4birds;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Search extends AppCompatActivity implements View.OnClickListener{

    EditText editTextZIPCode;
    Button buttonSearch;
    TextView textViewResults;

    String ZIPCode, Results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        editTextZIPCode = findViewById(R.id.editTextZIPCode);
        buttonSearch = findViewById(R.id.buttonSearch);
        textViewResults = findViewById(R.id.textViewResults);

        buttonSearch.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        ZIPCode = editTextZIPCode.getText().toString();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("allsightings");

        switch  (view.getId()){
            case R.id.buttonSearch:

                Toast.makeText(Search.this, "Yep", Toast.LENGTH_SHORT).show();
                myRef.orderByChild("zipCode").equalTo(ZIPCode).addChildEventListener(new ChildEventListener() {

                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        Sighting foundSighting = dataSnapshot.getValue(Sighting.class);
                        String findBirdName = foundSighting.birdName;
                        String findPersonName = foundSighting.personName;

                        textViewResults.setText("The last bird found in " + ZIPCode + " was a " + findBirdName + " by " + findPersonName);

                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.itemReport) {
            Intent reportIntent = new Intent(this, Report.class);
            startActivity(reportIntent);
        } else if (item.getItemId() == R.id.itemSearch) {
            Toast.makeText(this, "Already in Search", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
