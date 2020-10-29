package android.example.e12pedidosconintents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {

/**atributos de la clase para utilizar **/
    private TextView msgTotalPrice;
    private TextView msgEmail;
    private TextView msgOrder;
    private String name;
    private String price;
    private  String email;
    private  String number;
    private String productos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        getInfoIntent();
        
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, R.string.toastCorrection, Toast.LENGTH_SHORT).show();
    }

    /**
     * Función que vuelve a la actividad principal mostrando un toast
     * */
    public void returnToMain(View view){
        super.onBackPressed();

    }

    /**
     * Funcion que se encarga de mandar la informacion a un intent implicito para
     * poder enviar la confirmación de pedido por correo
     * */
    public void sendEmail(View view){

        String[] addresses =  {email};
        String msg = msgOrder.getText().toString()+"\n"+msgTotalPrice.getText().toString();
        String subject = getString(R.string.subjectMailto);

        Intent sendIntent = new Intent();

        //ponemos todos los datos
        sendIntent.setAction(Intent.ACTION_SENDTO);
        sendIntent.setData(Uri.parse("mailto:"));
        sendIntent.putExtra(Intent.EXTRA_TEXT, msg);
        sendIntent.putExtra(Intent.EXTRA_EMAIL, addresses);
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, subject);

        if(sendIntent.resolveActivity(getPackageManager()) != null){
            startActivity(sendIntent);
        }else{
            Toast.makeText(this, R.string.toastEmailFailed, Toast.LENGTH_SHORT).show();
        }

    }
    /**
     * Obtiene toda la informacion del activity main.
     * Esta función es auxiliar al oncreate
     * para dejar esa función lo mas limpia posible
     * */
    private void getInfoIntent(){
        Intent intent = getIntent();

        //recogemos los datos en los atributos
        name = intent.getStringExtra("EXTRA_NAME");
        price = intent.getStringExtra("EXTRA_PRICE");
        number = intent.getStringExtra("EXTRA_NUMBER");
        productos = intent.getStringExtra("EXTRA_PRODUCTS");
        email = intent.getStringExtra("EXTRA_EMAIL");

        //asignamos los valores a los mensajes para el email
        msgEmail = findViewById(R.id.msgEmail);
        msgOrder = findViewById(R.id.msgOrder);
        msgTotalPrice = findViewById(R.id.msgTotalPrice);


        //generamos los mensajes completos
        String msg = name + getString(R.string.youOrdered) + number + getString(R.string.coffeWith) + productos + ".";
        String msg2 = getString(R.string.totalPrice) + price + "€";
        String msg3 = getString(R.string.yourMail)+email;

        //mostramos los mensajes por los textView
        msgOrder.setText(msg);
        msgTotalPrice.setText(msg2);
        msgEmail.setText(msg3);

    }

}