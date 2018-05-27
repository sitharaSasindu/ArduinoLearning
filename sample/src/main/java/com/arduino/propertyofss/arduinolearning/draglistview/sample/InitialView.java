package com.arduino.propertyofss.arduinolearning.draglistview.sample;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InitialView extends AppCompatActivity {

    private Button bluetoothConnect;
    private Button remote;
    private Button contactUs;
    private Button build;
    private Button manual;
    private Button aboutUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //call the initail_view.xml file
        setContentView(R.layout.activity_initial_view);

        //call the buttons from the .xml file
        bluetoothConnect = (Button) findViewById(R.id.bluetoothConnect);
        remote = (Button) findViewById(R.id.remote);
        contactUs = (Button) findViewById(R.id.contactUs);
        build = (Button) findViewById(R.id.build);
        aboutUs = (Button) findViewById(R.id.aboutus);
        manual = (Button) findViewById(R.id.manual);

        //connect the remote button to the remote page
        //connect the remote button to the remote page
        remote.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(Bluetooth.connectionStatus) {
                    startActivity(new Intent(InitialView.this, Remote.class));
                }else{
                    ShowAlert();
                }
            }
        });

        //connect the contact us button to the contact page
        contactUs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(InitialView.this, ContactUs.class));
            }
        });

        //connect the builld button to the build page
        build.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(Bluetooth.connectionStatus) {
                    startActivity(new Intent(InitialView.this, SelectModel.class));
                }else {
                    ShowAlert();
                }
            }
        });
        //connect the about us button to the about us page
        aboutUs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(InitialView.this, AboutUs.class));
            }
        });

        //connect the manual button to the manual page
        manual.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(InitialView.this, Manual.class));
            }
        });

        //connect the bluetooth button to the bluetooth page
        bluetoothConnect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(InitialView.this, Bluetooth.class));
            }
        });

    }
    public void ShowAlert(){
        AlertDialog alertDialog = new AlertDialog.Builder(
                InitialView.this).create();

        // Setting Dialog Title
        alertDialog.setTitle("Alert");

        // Setting Dialog Message
        alertDialog.setMessage("Please Connect a Device First..!!!");

        // Setting Icon to Dialog
        // alertDialog.setIcon(R.drawable.tick);

        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed
                //Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }
}