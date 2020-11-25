package com.example.dialog4alert;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final static String[] opciones = {"ACDC","Iron Maiden","Motionless In White","Guns & Roses"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OnClickShowAlert(View view){
        AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(MainActivity.this);

        myAlertBuilder.setTitle("Alert");
        //myAlertBuilder.setMessage("Click OK to continue, or Cancel to Stop");
        myAlertBuilder.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), opciones[which]+" selected",Toast.LENGTH_SHORT).show();
            }
        });


        myAlertBuilder.show();
    }

}