package android.example.e12pedidosconintents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {
    private TextView msgTotalPrice;
    private TextView msgEmail;
    private TextView msgOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        getInfoIntent();
        
    }
    private void getInfoIntent(){
        Intent intent = getIntent();
        String name = intent.getStringExtra("EXTRA_NAME");
        String price = intent.getStringExtra("EXTRA_PRICE");
        String number = intent.getStringExtra("EXTRA_NUMBER");
        String productos = intent.getStringExtra("EXTRA_PRODUCTS");
        String email = intent.getStringExtra("EXTRA_EMAIL");


        msgEmail = findViewById(R.id.msgEmail);
        msgOrder = findViewById(R.id.msgOrder);
        msgTotalPrice = findViewById(R.id.msgTotalPrice);



        String msg = name + ", has pedido " + number + " cafés con " + productos + ".";
        String msg2 = "El precio total es " + price + "€";
        String msg3 = "Tu email es "+email;

        msgOrder.setText(msg);
        msgTotalPrice.setText(msg2);
        msgEmail.setText(msg3);

    }

}