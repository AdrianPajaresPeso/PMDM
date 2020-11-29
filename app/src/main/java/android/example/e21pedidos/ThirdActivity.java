package android.example.e21pedidos;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ArrayAdapter adapter;
    private final Integer[] tipoCafes = new Integer[3]; //{solo, cortado, con leche}
    private final String[] modoEntrega = new String[3];// {fecha, hora, tipo}
    private final String[] infoComprador = new String[4]; //{nombre, direccion, telefono, tipo de telefono}
    private AlertDialog.Builder alerta;
    private String msg;
    private final String destinatario = "pedidos@mitienda.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        recibirInfo();
        cargarAlerta();
        generarSpinner();
    }

    /***
     * Carga la alerta de confirmación de pedido
     * */
    private void cargarAlerta() {
        alerta = new AlertDialog.Builder(this);
        alerta.setMessage("¿Está seguro de que quiere hacer el pedido?");

        //crea el boton de cancelacion
        alerta.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                displayToast("Revise el Pedido");
            }
        });

        //Crea el boton de confirmacion
        alerta.setPositiveButton("SÍ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                enviarMail();
            }
        });
    }

    /**
     * Metodo que se encarga de enviar la informacion al nuevo intent
     */
    private void enviarMail() {
        Intent mail = new Intent();
        String[] address = {destinatario};
        constructMsg();
        String asunto = "Pedido de " + infoComprador[0];
        //ponemos todos los datos
        mail.setAction(Intent.ACTION_SENDTO);
        mail.setData(Uri.parse("mailto:"));
        mail.putExtra(Intent.EXTRA_TEXT, msg);
        mail.putExtra(Intent.EXTRA_EMAIL, address);
        mail.putExtra(Intent.EXTRA_SUBJECT, asunto);

        //si existe una app en el dispositivo le mandamos los datos¡
        if (mail.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(mail, 1);
        } else {
            //si no existe le mostramos un toast
            displayToast("No existe cliente de Correo");
        }
    }

    private void constructMsg() {
        String numeroSolos = "";
        String numeroLeche = "";
        String numeroCortado = "";

        if (tipoCafes[0] != 0) {
            numeroSolos = tipoCafes[0] + " Cafés solos";
        }
        if (tipoCafes[1] != 0) {
            numeroCortado = tipoCafes[1] + " Cafés Cortados";
        }

        if (tipoCafes[2] != 0) {
            numeroLeche = tipoCafes[2] + " Cafés con Leche";
        }

        msg = "Mi pedido es: \n" +
                numeroLeche + "\n" +
                numeroSolos + "\n" +
                numeroCortado + "\nLa fecha de entrega es "
                + modoEntrega[0] + " y la hora de entrega " +
                modoEntrega[1] + "\nModo de entrega: " + modoEntrega[2] + "\nDireccion: " +
                infoComprador[1] + "\nNumero de Telefono: " + infoComprador[2] + " (" + infoComprador[3] + ")";

    }

    /**
     * Metodo que se encarga de generar el spinner
     **/
    private void generarSpinner() {
        adapter = ArrayAdapter.createFromResource(this, R.array.phone_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = findViewById(R.id.number_tipo);
        if (spinner != null) {
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(this);
        }
    }


    /***
     * Metodo que se encarga de recoger toda la información de la actividad anterior
     * */
    public void recibirInfo() {
        Intent info = getIntent();
        tipoCafes[0] = info.getIntExtra("EXTRA_SOLO", 0);
        tipoCafes[1] = info.getIntExtra("EXTRA_CORTADO", 0);
        tipoCafes[2] = info.getIntExtra("EXTRA_CONLECHE", 0);
        modoEntrega[0] = info.getStringExtra("EXTRA_DELIVERYDATE");
        modoEntrega[1] = info.getStringExtra("EXTRA_DELIVERYTIME");
        modoEntrega[2] = info.getStringExtra("EXTRA_DELIVERYTIPE");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.third_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.confirm_order:
                checkInfo();
                return true;
            default:

        }


        return super.onOptionsItemSelected(item);
    }

    /**
     * Este metodo no te deja confirmar el pedido a no ser que toda la información
     * haya sido introducida
     */
    private void checkInfo() {
        getInfoComprador();

        if (!infoComprador[0].equals("")) {
            if (!infoComprador[1].equals("")) {
                if (!infoComprador[2].equals("")) {
                    alerta.show();
                } else {
                    displayToast("No has introducido el numero de telefono");
                }
            } else {
                displayToast("No has introducido la direccion");
            }
        } else {
            displayToast("No has introducido el nombre");
        }
    }

    /**
     * obtiene y almacena la informacion del comprador de esta actividad
     */
    private void getInfoComprador() {
        EditText nombre = findViewById(R.id.name_input);
        EditText direccion = findViewById(R.id.address_input);
        EditText telefono = findViewById(R.id.phoneNumber_input);


        infoComprador[0] = nombre.getText().toString();
        infoComprador[1] = direccion.getText().toString();
        infoComprador[2] = telefono.getText().toString();

    }

    private void displayToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String spinnerItem = parent.getItemAtPosition(position).toString();
        infoComprador[3] = spinnerItem;
        //displayToast(spinnerItem);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        String spinnerItem = parent.getItemAtPosition(1).toString();
        infoComprador[3] = spinnerItem;
        //displayToast(spinnerItem);
    }
}