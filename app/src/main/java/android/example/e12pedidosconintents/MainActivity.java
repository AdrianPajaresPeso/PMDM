package android.example.e12pedidosconintents;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int numberOfProducts = 0;
    private TextView mShowCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowCount = (TextView) findViewById(R.id.numeroQuantity);
    }


    /**
     * Metodo que se encarga de recoger toda la información
     * de la actividad principal y pasarla a la actividad secundaria
     * */
    public void makeOrder(View view) {

        //rescursos que se van a utilizar
        Intent intent = new Intent(this, Activity2.class);
        CheckBox nata  = findViewById(R.id.whippedCream);
        CheckBox chocolate  = findViewById(R.id.chocolate);
        TextView x = (TextView) findViewById(R.id.numeroQuantity);

        //valores para calculo
        Integer numeroCafes = Integer.parseInt(String.valueOf(x.getText()));
        double price = (double) numeroCafes*1.50;
        TextView nombre = (TextView) findViewById(R.id.inputName);
        TextView e_mail = findViewById(R.id.editTextTextEmailAddress);

        //si la nata está checada se le añade 0.5 por café pedido
        if(nata.isChecked()){
            price += (0.5*numeroCafes);
        }
        //si el chocolate está checada se le añade 0.5 por cafe pedido
        if(chocolate.isChecked()){
            price += (0.5*numeroCafes);
        }

        //se define el string dependiendo de los toppings elegidos
        String productos;
        if(nata.isChecked() && chocolate.isChecked()){
            productos= getString(R.string.withCreamAndChocolate);
        }else if(nata.isChecked()){
            productos = getString(R.string.withCream);
        }else if(chocolate.isChecked()){
            productos = getString(R.string.withChocolate);
        }else{
            if(numeroCafes>1){
                productos =getString(R.string.without);

            }else{
                productos =getString(R.string.without_single);

            }
        }

        //se preparan los datos y se pasan a la siguiente actividad
        intent.putExtra("EXTRA_EMAIL",e_mail.getText().toString());
        intent.putExtra("EXTRA_PRODUCTS", productos);
        intent.putExtra("EXTRA_NAME", String.valueOf(nombre.getText()));
        intent.putExtra("EXTRA_PRICE", String.valueOf(price));
        intent.putExtra("EXTRA_NUMBER", String.valueOf(numeroCafes));
        startActivity(intent);
    }

    /**
     * Funcion que hace que el contador de cafes suba
     * */
    public void countUp(View view) {
        ++numberOfProducts;
        if ( mShowCount!= null) {
            mShowCount.setText(Integer.toString(numberOfProducts));
        }
    }


    /**
     * Funcion que hace que el contador de cafes baje
     * */
    public void countDown(View view) {
        --numberOfProducts;
        if (mShowCount != null) {
            mShowCount.setText(Integer.toString(numberOfProducts));
        }
    }
}