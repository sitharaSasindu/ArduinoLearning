package com.arduino.propertyofss.arduinolearning.draglistview.sample;

import android.os.Bundle;

import java.util.concurrent.TimeUnit;

public class ActionList extends BoardFragment{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public static void listOfActions() { //check the arraylist items in the arraylist
        //by comparing with their function names
        // if true that function will be written to arduino board using the bluetooth connection
        for (int x = 0; x < AddedFunctions.size(); x++) {
            if (AddedFunctions.get(x).second.equals("Go Straight")) {
                Bluetooth.mConnectedThread.write("1");
                try {
                    TimeUnit.MILLISECONDS.sleep(250);
                    System.out.println("forward done");
                    Bluetooth.mConnectedThread.write("5");
                    TimeUnit.MILLISECONDS.sleep(400);
                } catch (InterruptedException e) {
                    System.out.println("forward not done");
                    e.printStackTrace();
                }

            } else if (AddedFunctions.get(x).second.equals("Turn Left")) {
                Bluetooth.mConnectedThread.write("4");
                try {
                    TimeUnit.MILLISECONDS.sleep(660);
                    Bluetooth.mConnectedThread.write("5");
                    TimeUnit.MILLISECONDS.sleep(400);
                    System.out.println("left done");
                } catch (InterruptedException e) {
                    System.out.println("left not done");
                    e.printStackTrace();
                }


            } else if (AddedFunctions.get(x).second.equals("Turn Right")) {
                Bluetooth.mConnectedThread.write("3");
                try {
                    TimeUnit.MILLISECONDS.sleep(660);
                    System.out.println("right done");
                    Bluetooth.mConnectedThread.write("5");
                    TimeUnit.MILLISECONDS.sleep(400);
                } catch (InterruptedException e) {
                    System.out.println("right not done");
                    e.printStackTrace();
                }

            } else if (AddedFunctions.get(x).second.equals("Go Backwards")) {
                Bluetooth.mConnectedThread.write("2");
                try {
                    TimeUnit.MILLISECONDS.sleep(250);
                    System.out.println("backward done");
                    Bluetooth.mConnectedThread.write("5");
                    TimeUnit.MILLISECONDS.sleep(400);
                } catch (InterruptedException e) {
                    System.out.println("backward not done");
                    e.printStackTrace();
                }

            } else if (AddedFunctions.get(x).second.equals("Run 1 Seconds")) {
                try {
                    if(AddedFunctions.get(x - 1).second.equals("Go Straight")){
                        Bluetooth.mConnectedThread.write("1");
                    }else if (AddedFunctions.get(x - 1).second.equals("Go Backwards")){
                        Bluetooth.mConnectedThread.write("2");
                    }
                    TimeUnit.SECONDS.sleep(1);
                    Bluetooth.mConnectedThread.write("5");
                    TimeUnit.MILLISECONDS.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } else if (AddedFunctions.get(x).second.equals("Run 2 Seconds")) {
                try {
                    if(AddedFunctions.get(x - 1).second.equals("Go Straight")){
                        Bluetooth.mConnectedThread.write("1");
                    }else if (AddedFunctions.get(x - 1).second.equals("Go Backwards")){
                        Bluetooth.mConnectedThread.write("2");
                    }
                    TimeUnit.SECONDS.sleep(2);
                    Bluetooth.mConnectedThread.write("5");
                    TimeUnit.MILLISECONDS.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } else if (AddedFunctions.get(x).second.equals("Run 5 Seconds")) {
                try {
                    if(AddedFunctions.get(x - 1).second.equals("Go Straight")){
                        Bluetooth.mConnectedThread.write("1");
                    }else if (AddedFunctions.get(x - 1).second.equals("Go Backwards")){
                        Bluetooth.mConnectedThread.write("2");
                    }
                    TimeUnit.SECONDS.sleep(5);
                    Bluetooth.mConnectedThread.write("5");
                    TimeUnit.MILLISECONDS.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } else if (AddedFunctions.get(x).second.equals("Object Detection")) {
                Bluetooth.mConnectedThread.write("6");

            } else if (AddedFunctions.get(x).second.equals("Stop")) {
                Bluetooth.mConnectedThread.write("5");

            } else if (AddedFunctions.get(x).second.equals("Right Turn 180 Degrees")) {
                Bluetooth.mConnectedThread.write("3");
                try {
                    TimeUnit.MILLISECONDS.sleep(1320);
                    System.out.println("turn 180 done");
                    Bluetooth.mConnectedThread.write("5");
                    TimeUnit.MILLISECONDS.sleep(400);
                } catch (InterruptedException e) {
                    System.out.println("turn 180 not done");
                    e.printStackTrace();
                }

            } else if (AddedFunctions.get(x).second.equals("Left Turn 180 Degrees")) {
                Bluetooth.mConnectedThread.write("4");
                try {
                    TimeUnit.MILLISECONDS.sleep(1320);
                    System.out.println("turn 180 done");
                    Bluetooth.mConnectedThread.write("5");
                    TimeUnit.MILLISECONDS.sleep(400);
                } catch (InterruptedException e) {
                    System.out.println("turn 180 not done");
                    e.printStackTrace();
                }

            } else if (AddedFunctions.get(x).second.equals("Zig Zag")) {
                Bluetooth.mConnectedThread.write("3");
                try {
                    TimeUnit.MILLISECONDS.sleep(330);
                    Bluetooth.mConnectedThread.write("5");
                    TimeUnit.MILLISECONDS.sleep(400);
                    Bluetooth.mConnectedThread.write("1");
                    TimeUnit.MILLISECONDS.sleep(2000);
                    Bluetooth.mConnectedThread.write("5");
                    TimeUnit.MILLISECONDS.sleep(400);
                    Bluetooth.mConnectedThread.write("4");
                    TimeUnit.MILLISECONDS.sleep(660);
                    Bluetooth.mConnectedThread.write("5");
                    TimeUnit.MILLISECONDS.sleep(400);
                    Bluetooth.mConnectedThread.write("1");
                    TimeUnit.MILLISECONDS.sleep(2000);
                    Bluetooth.mConnectedThread.write("5");
                    TimeUnit.MILLISECONDS.sleep(400);
                    Bluetooth.mConnectedThread.write("3");
                    TimeUnit.MILLISECONDS.sleep(660);
                    Bluetooth.mConnectedThread.write("5");
                    TimeUnit.MILLISECONDS.sleep(400);
                    Bluetooth.mConnectedThread.write("1");
                    TimeUnit.MILLISECONDS.sleep(2000);
                    Bluetooth.mConnectedThread.write("5");
                    TimeUnit.MILLISECONDS.sleep(400);
                    System.out.println("Zig zac");
                } catch (InterruptedException e) {
                    System.out.println("Zig zac not done");
                    e.printStackTrace();
                }

            }
            else if (AddedFunctions.get(x).second.equals("Comeback")) {
                Bluetooth.mConnectedThread.write("1");
                try {
                    TimeUnit.MILLISECONDS.sleep(2000);
                    Bluetooth.mConnectedThread.write("5");
                    TimeUnit.MILLISECONDS.sleep(400);
                    Bluetooth.mConnectedThread.write("3");
                    TimeUnit.MILLISECONDS.sleep(1320);
                    Bluetooth.mConnectedThread.write("5");
                    TimeUnit.MILLISECONDS.sleep(400);
                    Bluetooth.mConnectedThread.write("1");
                    TimeUnit.MILLISECONDS.sleep(2000);
                    Bluetooth.mConnectedThread.write("5");
                    TimeUnit.MILLISECONDS.sleep(400);
                    Bluetooth.mConnectedThread.write("3");
                    TimeUnit.MILLISECONDS.sleep(1320);
                    Bluetooth.mConnectedThread.write("5");
                    TimeUnit.MILLISECONDS.sleep(400);
                    System.out.println("Comeback done");
                } catch (InterruptedException e) {
                    System.out.println("Comeback not done");
                    e.printStackTrace();
                }
            }
        }
    }

}
