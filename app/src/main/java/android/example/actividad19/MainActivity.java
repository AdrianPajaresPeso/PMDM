package android.example.actividad19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int numberOfProducts = 0;
    private TextView mShowCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowCount = (TextView) findViewById(R.id.numeroQuantity);
    }


    public void makeOrder(View view) {

        Intent intent = new Intent(this, Activity2.class);
        CheckBox nata  = findViewById(R.id.whippedCream);
        CheckBox chocolate  = findViewById(R.id.chocolate);
        TextView x = (TextView) findViewById(R.id.numeroQuantity);

        Integer numeroCafes = Integer.parseInt(String.valueOf(x.getText()));
        double price = (double) numeroCafes*1.50;
        TextView nombre = (TextView) findViewById(R.id.inputName);


        if(nata.isChecked()){
            price = price + 0.5;
        }
        if(chocolate.isChecked()){
            price = price + 0.5;
        }
        String productos;
        if(nata.isChecked() && chocolate.isChecked()){
            productos= "con Nata Montada y Chocolate";
        }else if(nata.isChecked()){
            productos = "con Nata Montada";
        }else if(chocolate.isChecked()){
            productos = "con Chocolate";
        }else{
            if(numeroCafes>1){
                productos ="solos";

            }else{
                productos ="solo";

            }
        }
        intent.putExtra("EXTRA_PRODUCTS", productos);
        intent.putExtra("EXTRA_NAME", String.valueOf(nombre.getText()));
        intent.putExtra("EXTRA_PRICE", String.valueOf(price));
        intent.putExtra("EXTRA_NUMBER", String.valueOf(numeroCafes));
        startActivity(intent);
    }

    public void countUp(View view) {
        ++numberOfProducts;
        if ( mShowCount!= null) {
            mShowCount.setText(Integer.toString(numberOfProducts));
        }
    }
    public void countDown(View view) {
        ++numberOfProducts;
        if (mShowCount != null) {
            mShowCount.setText(Integer.toString(numberOfProducts));
        }
    }
}