package com.arduino.propertyofss.arduinolearning.draglistview.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class Remote extends AppCompatActivity {

    private Button Forward; //declaring button to go forward
    private Button Backward; //declaring button to go backward
    private Button Left; //declaring button to go Left
    private Button Right; //declaring button to go Right
    private ToggleButton toggleOD; //declaring button to go switch on-off object Detection

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote);

        //initializing button with declared button widget
        Forward = (Button) findViewById(R.id.forward);
        Backward = (Button) findViewById(R.id.backward);
        Left = (Button) findViewById(R.id.left);
        Right = (Button) findViewById(R.id.right);
        toggleOD = (ToggleButton) findViewById(R.id.objDetector);

        //setting a touch listener to move the vehicle while holding the button and stop when release the button
        Forward.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //Button pressed
                    Bluetooth.mConnectedThread.write("1"); //sending data through the bluetooth serial write
                    return true;
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    //Button released
                    Bluetooth.mConnectedThread.write("5"); //sending data through the bluetooth serial write
                    return true;
                }
                return false;
            }
        });

        Backward.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //Button pressed
                    Bluetooth.mConnectedThread.write("2"); //sending data through the bluetooth serial write
                    return true;
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    //Button released
                    Bluetooth.mConnectedThread.write("5"); //sending data through the bluetooth serial write
                    return true;
                }
                return false;
            }
        });

        Left.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //Button pressed
                    Bluetooth.mConnectedThread.write("4"); //sending data through the bluetooth serial write
                    return true;
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    //Button released
                    Bluetooth.mConnectedThread.write("5"); //sending data through the bluetooth serial write
                    return true;
                }
                return false;
            }
        });

        Right.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //Button pressed
                    Bluetooth.mConnectedThread.write("3"); //sending data through the bluetooth serial write
                    return true;
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    //Button released
                    Bluetooth.mConnectedThread.write("5"); //sending data through the bluetooth serial write
                    return true;
                }
                return false;
            }
        });

        //setting a toggle button to turn switch on-off the object detection
        toggleOD.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Bluetooth.mConnectedThread.write("6"); //sending data through the bluetooth serial write
                    //disable the buttons while object detection is on
                    Forward.setEnabled(false);
                    Backward.setEnabled(false);
                    Left.setEnabled(false);
                    Right.setEnabled(false);
                    System.out.println("Sent");
                } else {
                    Bluetooth.mConnectedThread.write("5"); //sending data through the bluetooth serial write
                    //enable the buttons while object detection is off
                    Forward.setEnabled(true);
                    Backward.setEnabled(true);
                    Left.setEnabled(true);
                    Right.setEnabled(true);
                    System.out.println("Retrieved");
                }
            }
        });

    }
}
