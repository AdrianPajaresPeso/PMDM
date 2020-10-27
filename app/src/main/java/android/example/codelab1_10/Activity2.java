package android.example.codelab1_10;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Activity2 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        String name = intent.getStringExtra("EXTRA_NAME");
        String price = intent.getStringExtra("EXTRA_PRICE");
        String number = intent.getStringExtra("EXTRA_NUMBER");
        String productos = intent.getStringExtra("EXTRA_PRODUCTS");
        TextView showMsg;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        showMsg = (TextView) findViewById(R.id.msg);
        

        String msg = name + ", has pedido " + number + " cafés con " + productos + ".\n El precio total es " + price + "€";
        showMsg.setText(msg);
    }

}