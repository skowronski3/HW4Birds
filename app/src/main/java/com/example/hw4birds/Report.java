package com.example.hw4birds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Report extends AppCompatActivity implements View.OnClickListener{

    EditText editTextBirdName, editTextZipCode, editTextPersonName;
    Button buttonSubmit;

    String birdName, zipCode, personName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextBirdName = findViewById(R.id.editTextBirdName);
        editTextZipCode = findViewById(R.id.editTextZIPCode);
        editTextPersonName = findViewById(R.id.editTextPersonName);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        buttonSubmit.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View view) {

        birdName = editTextBirdName.getText().toString();
        zipCode = editTextZipCode.getText().toString();
        personName = editTextPersonName.getText().toString();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("allsightings");



        switch  (view.getId()){
            case R.id.buttonSubmit:
                Sighting newSighting = new Sighting(birdName,zipCode,personName);
                myRef.push().setValue(newSighting);
                Toast.makeText(this, "Sighting recorded!", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(this, "Already in Report", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.itemSearch) {
            Intent searchIntent = new Intent(this, Search.class);
            startActivity(searchIntent);
        }

        return super.onOptionsItemSelected(item);
    }
}
