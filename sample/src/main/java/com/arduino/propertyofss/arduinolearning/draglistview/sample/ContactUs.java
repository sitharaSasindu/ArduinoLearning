package com.arduino.propertyofss.arduinolearning.draglistview.sample;

        import android.Manifest;
        import android.content.ComponentName;
        import android.content.Intent;
        import android.content.pm.PackageManager;
        import android.content.pm.ResolveInfo;
        import android.net.Uri;
        import android.support.v4.app.ActivityCompat;
        import android.support.v4.content.ContextCompat;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;

        import java.util.List;

public class ContactUs extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        //Disuru Call function Button
        Button btn1 =  (Button) findViewById(R.id.button5);
        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent dial = new Intent();
                dial.setAction("android.intent.action.DIAL");//set the number in dialpad
                dial.setData(Uri.parse("tel:0702731975"));
                startActivity(dial);
            }
        });


        //Isuru Call function Button
        Button btn2 =  (Button) findViewById(R.id.button6);
        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent dial = new Intent();
                dial.setAction("android.intent.action.DIAL");
                dial.setData(Uri.parse("tel:0766176470"));
                startActivity(dial);
            }
        });

        //Sithara Call function Button
        Button btn3 =  (Button) findViewById(R.id.button7);
        btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent dial = new Intent();
                dial.setAction("android.intent.action.DIAL");
                dial.setData(Uri.parse("tel:0779488970"));
                startActivity(dial);
            }
        });
        //Sanju Call function Button
        Button btn4 =  (Button) findViewById(R.id.button8);
        btn4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent dial = new Intent();
                dial.setAction("android.intent.action.DIAL");
                dial.setData(Uri.parse("tel:0765280685"));
                startActivity(dial);
            }
        });

        //Isuru Mail Function Button Link with the Email app
        Button btn5 =  (Button) findViewById(R.id.mail1);
        btn5.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View v){

                Intent myIntent = new Intent(Intent.ACTION_SEND);

                PackageManager pm = getPackageManager();
                Intent tempIntent = new Intent(Intent.ACTION_SEND);
                tempIntent.setType("*/*");
                List<ResolveInfo> resInfo = pm.queryIntentActivities(tempIntent, 0);
                for (int i = 0; i < resInfo.size(); i++) {
                    ResolveInfo ri = resInfo.get(i);
                    if (ri.activityInfo.packageName.contains("android.gm")) {
                        myIntent.setComponent(new ComponentName(ri.activityInfo.packageName, ri.activityInfo.name));
                        myIntent.setAction(Intent.ACTION_SEND);
                        myIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"isurunimantha@gmail.com"});
                        myIntent.setType("message/rfc822");
                        myIntent.putExtra(Intent.EXTRA_TEXT, "extra text");//Set text in the body part of the mail
                        myIntent.putExtra(Intent.EXTRA_SUBJECT, "Extra subject");//set tet in the subject part of the mail
                        myIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("uri://your/uri/string"));
                    }
                }
                startActivity(myIntent);

            }
        });

        //Disuru Mail Function Button Link with the Email app
        Button btn6 =  (Button) findViewById(R.id.mail2);
        btn6.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View v){

                Intent myIntent = new Intent(Intent.ACTION_SEND);

                PackageManager pm = getPackageManager();
                Intent tempIntent = new Intent(Intent.ACTION_SEND);
                tempIntent.setType("*/*");
                List<ResolveInfo> resInfo = pm.queryIntentActivities(tempIntent, 0);
                for (int i = 0; i < resInfo.size(); i++) {
                    ResolveInfo ri = resInfo.get(i);
                    if (ri.activityInfo.packageName.contains("android.gm")) {
                        myIntent.setComponent(new ComponentName(ri.activityInfo.packageName, ri.activityInfo.name));
                        myIntent.setAction(Intent.ACTION_SEND);
                        myIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"disururathnayake0120@gmail.com"});
                        myIntent.setType("message/rfc822");
                        myIntent.putExtra(Intent.EXTRA_TEXT, "extra text");
                        myIntent.putExtra(Intent.EXTRA_SUBJECT, "Extra subject");
                        myIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("uri://your/uri/string"));
                    }
                }
                startActivity(myIntent);

            }
        });

        //Sithara Mail Function Button Link with the Email app
        Button btn7 =  (Button) findViewById(R.id.mail3);
        btn7.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View v){

                Intent myIntent = new Intent(Intent.ACTION_SEND);

                PackageManager pm = getPackageManager();
                Intent tempIntent = new Intent(Intent.ACTION_SEND);
                tempIntent.setType("*/*");
                List<ResolveInfo> resInfo = pm.queryIntentActivities(tempIntent, 0);
                for (int i = 0; i < resInfo.size(); i++) {
                    ResolveInfo ri = resInfo.get(i);
                    if (ri.activityInfo.packageName.contains("android.gm")) {
                        myIntent.setComponent(new ComponentName(ri.activityInfo.packageName, ri.activityInfo.name));
                        myIntent.setAction(Intent.ACTION_SEND);
                        myIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"sasindu.sithara@gmail.com"});
                        myIntent.setType("message/rfc822");
                        myIntent.putExtra(Intent.EXTRA_TEXT, "extra text");
                        myIntent.putExtra(Intent.EXTRA_SUBJECT, "Extra subject");
                        myIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("uri://your/uri/string"));
                    }
                }
                startActivity(myIntent);

            }
        });

        //Sanju Mail Function Button Link with the Email app
        Button btn8 =  (Button) findViewById(R.id.mail4);
        btn8.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View v){

                Intent myIntent = new Intent(Intent.ACTION_SEND);

                PackageManager pm = getPackageManager();
                Intent tempIntent = new Intent(Intent.ACTION_SEND);
                tempIntent.setType("*/*");
                List<ResolveInfo> resInfo = pm.queryIntentActivities(tempIntent, 0);
                for (int i = 0; i < resInfo.size(); i++) {
                    ResolveInfo ri = resInfo.get(i);
                    if (ri.activityInfo.packageName.contains("android.gm")) {
                        myIntent.setComponent(new ComponentName(ri.activityInfo.packageName, ri.activityInfo.name));
                        myIntent.setAction(Intent.ACTION_SEND);
                        myIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"sanju.gaya@gmail.com"});
                        myIntent.setType("message/rfc822");
                        myIntent.putExtra(Intent.EXTRA_TEXT, "extra text");
                        myIntent.putExtra(Intent.EXTRA_SUBJECT, "Extra subject");
                        myIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("uri://your/uri/string"));
                    }
                }
                startActivity(myIntent);

            }
        });






    }
}

