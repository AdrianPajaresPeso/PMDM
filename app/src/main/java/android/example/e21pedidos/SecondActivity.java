package android.example.e21pedidos;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class SecondActivity extends AppCompatActivity {
    private Integer[] countTipoCafes = new Integer[3]; //{solo, cortado, con leche}
    private TextView datePickerText;
    private TextView timePickerText;
    private Calendar calendar;
    private String deliveryHour;
    private String deliveryDate;
    private RadioButton entregaCasa;
    private String deliveryTipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initializeAllViewSources();//carga todos los recursos
        recibirInfo();//recibe la info de la actividad anterior
        datePickerText.setText(getCurrentDateToString());
        timePickerText.setText(getCurrentTimeToString());

    }

    /**
     * Retorna un string con la hora y minutos en las que se ha creado la actividad.
     * */
    private String getCurrentTimeToString() {
        String currentTime;
        return currentTime = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY))+":"+calendar.get(Calendar.MINUTE);
    }

    /**
     * Retorna un string con la fecha actual para inicializar la vista del datePicker con la fecha actual del sistema
     */
    private String getCurrentDateToString() {
        String curDate;
        Integer month = calendar.get(Calendar.MONTH);
        return curDate = calendar.get(Calendar.DAY_OF_MONTH) + "/" + (month + 1) + "/" + calendar.get(Calendar.YEAR);
    }


    /**
     * Inicializa las variables necesarias de la clase, este metodo está extraido del metodo
     * onCreate para dejar mas limpio el codigo.
     */
    private void initializeAllViewSources() {
        datePickerText = findViewById(R.id.delivery_date);
        timePickerText = findViewById(R.id.delivery_hour);
        calendar = Calendar.getInstance();
    }


    /**
     * Cuando se haya elegido la fecha se cambiará en la vista y se almacenará
     */
    public void procesarResultadoDatepicker(int anio, int mes, int dia) {
        String mesStr = Integer.toString(mes + 1);
        String diaStr = Integer.toString(dia);
        String anioStr = Integer.toString(anio);

        String newDate = diaStr + "/" + mesStr + "/" + anioStr;
        deliveryDate = newDate;
        datePickerText.setText(newDate);
    }

    /**
     * Muestra en el appbar default el menu de entrega
     **/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.delivery_menu, menu);

        return true;
    }

    /**
     * Dependiendo del item seleccionado ejecutará una accion u otra
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        /**
         * Aun habiendo uno solo, ponemos el switch por si en un futuro hace falta poner más items
         * así tener facil la refactorizacion del código
         * */

        switch (item.getItemId()) {
            case R.id.delivery_ok:
                startNextActivity();
                return true;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Carga toda la informacion para la siguiente actividad
     **/
    private void startNextActivity() {
        ckecRadioButtons();


        Intent thirdActivity = new Intent(this, ThirdActivity.class);
        thirdActivity.putExtra("EXTRA_SOLO", countTipoCafes[0]);
        thirdActivity.putExtra("EXTRA_CORTADO", countTipoCafes[1]);
        thirdActivity.putExtra("EXTRA_CONLECHE", countTipoCafes[2]);
        thirdActivity.putExtra("EXTRA_DELIVERYDATE", deliveryDate);
        thirdActivity.putExtra("EXTRA_DELIVERYTIME", deliveryHour);
        thirdActivity.putExtra("EXTRA_DELIVERYTIPE", deliveryTipe);

        startActivity(thirdActivity);

    }

    /**
     * Al haber dos metodos de entrega solo hacemos una comprobación
     * simpre tiene que haber un metodo de entrega seleccionado
     * */
    private void ckecRadioButtons() {
        entregaCasa = findViewById(R.id.home_delivery);
        if(entregaCasa.isChecked()){
            deliveryTipe= "A Domicilio";
        }else{
            deliveryTipe = "Recoger en local";
        }
    }

    /**
     * ALmacena la información recibida de la anterior actividad
     */
    private void recibirInfo() {
        Intent infoRecibida = getIntent();
        countTipoCafes[0] = infoRecibida.getIntExtra("EXTRA_SOLO", 1);
        countTipoCafes[1] = infoRecibida.getIntExtra("EXTRA_CORTADO", 1);
        countTipoCafes[2] = infoRecibida.getIntExtra("EXTRA_CONLECHE", 1);
    }

    /**
     * Muestra un toast con el mensaje que se le pasa por parametro
     */
    private void displayToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }


    /***
     * inicia el proceso para seleccionar una fecha
     * **/
    public void showDatePicker(View view) {
        DialogFragment datePicker = new DatePickerFragment();
        datePicker.show(getSupportFragmentManager(), "DatePicker");
    }

    /**
     * Procesa la fecha que se pondrá en el timePicker
     * **/
    public void processTimePickerResult(int hour, int minute) {
        String hour_string = Integer.toString(hour);
        String minute_string = Integer.toString(minute);
        String dateMessage = (hour_string +
                ":" + minute_string);

        deliveryHour = dateMessage;
        timePickerText.setText(dateMessage);
    }


    /**
     * inicia el proceso para seleccionar una hora
     * **/
    public void showTimePicker(View view) {
        DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(), "TimePicker");
    }

}