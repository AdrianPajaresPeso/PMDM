package android.example.e12pedidosconintents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
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