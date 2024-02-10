package com.example.bmi;

import androidx.appcompat.app.AppCompatActivity;

import androidx.core.content.res.ResourcesCompat;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
private static final  String CHANNEL_ID= "Message";
    private static final  int NOTIFICATION_ID= 100;
    @SuppressLint({"MissingInflatedId", "ResourceAsColor"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //BMI section

        EditText editweight, editheight, editheight1;
        Button button;
        TextView result;


        editweight= findViewById(R.id.editweight);
        editheight= findViewById(R.id.editheight);
        editheight1= findViewById(R.id.editheight1);
        button= findViewById(R.id.button);
        result= findViewById(R.id.result);


        button.setOnClickListener(v -> {
            int wt = Integer.parseInt(editweight.getText().toString());
            int ht= Integer.parseInt(editheight.getText().toString());
            int ht1= Integer.parseInt(editheight1.getText().toString());

            int totalIn= ht*12+ht1;

            double totalCm= totalIn*2.53;


            double totalM = totalCm/100;

            double bmi= wt/(totalM*totalM);

            if (bmi>25) {
                result.setText(R.string.you_are_overweight);


            } else if (bmi<18){
                result.setText(R.string.you_are_underweight);

            }else {
                 result.setText(R.string.you_are_healthy);

            }

        });

          //BMI ends
        //notification starts


        Drawable drawable= ResourcesCompat.getDrawable(getResources(), R.drawable.new_icon,null);

        //bitmap is class which stores images whenever we need images
        BitmapDrawable  bitmapDrawable = (BitmapDrawable) drawable;
        Bitmap LargeIcon = bitmapDrawable.getBitmap();

        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notification = new Notification.Builder(this)
                    .setLargeIcon(LargeIcon) //whenever use bitmap use PNG images
                    .setSmallIcon(R.drawable.new_icon)
                    .setContentText("New Message")
                    .setSubText("New Message from me")
                    .setChannelId(CHANNEL_ID)  //if the android version is more than oreo channels are used to avoid notification and make channels by this certain notifications are blocked but when we put if condition they check if the version is less or more than the criteria
                    .build();
            nm.createNotificationChannel(new NotificationChannel(CHANNEL_ID,"New Channel",NotificationManager.IMPORTANCE_HIGH));
        }else {
            notification = new Notification.Builder(this)
                    .setLargeIcon(LargeIcon) //whenever use bitmap use PNG images
                    .setSmallIcon(R.drawable.new_icon)
                    .setContentText("Welcome")
                    .setSubText("New Message from me")
                     //if the android version is more than oreo channels are used to avoid notification and make channels by this certain notifications are blocked but when we put if condition they check if the version is less or more than the criteria
                    .build();

        } //notification ends
        nm.notify(NOTIFICATION_ID, notification);//write this statement where you want to see notification

    }
}