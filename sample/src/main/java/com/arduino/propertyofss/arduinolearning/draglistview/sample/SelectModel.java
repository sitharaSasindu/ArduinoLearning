package com.arduino.propertyofss.arduinolearning.draglistview.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

//to select the model user want to function
public class SelectModel extends Activity implements AdapterView.OnItemSelectedListener {

    private Button backButton;
    private Button nextButton;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_model);

        backButton = (Button) findViewById(R.id.backbutton);
        nextButton = (Button) findViewById(R.id.nextButton);


        // Spinner element
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Button button = (Button) findViewById(R.id.nextButton);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Car: 4 Wheel");
        categories.add("Car: 3 Wheel");
        categories.add("4 Axis Quadcopter");
        categories.add("Build My Own");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SelectModel.this, MainActivity.class);
                intent.putExtra("data", String.valueOf(spinner.getSelectedItem()));
                startActivity(intent);
            }
        });


        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(SelectModel.this, InitialView.class));
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

    }

    // if nothing selected in spinner
    public void onNothingSelected(AdapterView<?> arg0) {



    }

    // button back selected
    @Override
    public void onBackPressed() {
        startActivity(new Intent(SelectModel.this, InitialView.class));
        finish();
    }


}