package android.example.e21pedidos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Integer countSolo = 0;
    private Integer countCortado = 0;
    private Integer countConLeche = 0;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.shoppingCart:
                sendToNextScreen();
                return true;
            case R.id.reset:
                resetViews();
                displayToast("Valores Reseteados");
                return true;
            default:

        }
        return super.onOptionsItemSelected(item);

    }

    private void sendToNextScreen() {

        if(countSolo == 0 && countCortado == 0 && countConLeche == 0){
            displayToast("Selecciona un producto antes de continuar");
        }else{
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
        }

    }

    /**
     * resetea los valores de los contadores a 0 y los views a 0
     */
    private void resetViews() {
        countConLeche = 0;
        countCortado = 0;
        countSolo = 0;
        setVistaCortado();
        displayCount(textView, 0);
        setVistaConLeche();
        displayCount(textView, 0);
        setVistaSolo();
        displayCount(textView, 0);
    }

    /*
     * infla el menu de la barra de herramientas
     * */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;

    }

    /**
     * Metodo para mostrar un Toast en caso de que sea necesario
     **/
    private void displayToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }


    /**
     * Resta un cafe solo si el contador es mayor que 0 y muestra por pantalla el resultado de la resta
     **/
    public void countLessSolo(View view) {
        //String.valueOf(view.getId());
        if (countSolo > 0) {
            countSolo--;
        }
        setVistaSolo();
        displayCount(textView, countSolo);
    }


    /**
     * suma uno al contador de cafes solos
     **/
    public void countPlusSolo(View view) {
        countSolo++;
        setVistaSolo();
        displayCount(textView, countSolo);

    }

    /**
     * resta 1 al contador de cafes cortados
     **/
    public void cuntLessCortado(View view) {
        if (countCortado > 0) {
            countCortado--;
        }
        setVistaCortado();
        displayCount(textView, countCortado);
    }

    /**
     * suma 1 al contador de cafe cortado
     */
    public void countPlusCortado(View view) {
        countCortado++;
        setVistaCortado();
        displayCount(textView, countCortado);
    }

    /**
     * resta 1 al contador de cafe con leche
     */
    public void countLessConLeche(View view) {
        if (countConLeche > 0) {
            countConLeche--;
        }
        setVistaConLeche();
        displayCount(textView, countConLeche);

    }

    /**
     * Suma 1 al contador de cafe con leche
     */
    public void countPlusConLeche(View view) {
        countConLeche++;
        setVistaConLeche();
        displayCount(textView, countConLeche);
    }

    /**
     * Sets para el textView
     **/
    private void setVistaCortado() {
        textView = findViewById(R.id.numCafeCortado);
    }

    private void setVistaSolo() {
        textView = findViewById(R.id.numCafeSolo);
    }

    private void setVistaConLeche() {
        textView = findViewById(R.id.numCafeConLeche);
    }

    /**
     * Este metodo es el general de muestreo de operacion se le pasan por parametros la vista y la cuenta ya realizada
     * lo unico que hace es mostrarla si la vista es diferente de null
     **/
    private void displayCount(TextView vista, Integer cuenta) {
        if (vista != null) {
            vista.setText(Integer.toString(cuenta));
        }
    }


}