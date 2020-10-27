package android.example.codelab1_10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG =
            MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void launchSecondActivity(View view) {
        Log.d(LOG_TAG, getString(R.string.LOG_MSG));
        Intent intent = new Intent(this, Activity2.class);
        startActivity(intent);
    }
}