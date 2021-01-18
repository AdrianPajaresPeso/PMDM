package android.example.e22peliculas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddModifyFilm extends AppCompatActivity implements View.OnClickListener {

    private Integer keyFilm;
    private EditText name;
    private EditText gender;
    private EditText urlWeb;
    private EditText urlImage;
    private Film recivedFilm;
    private Button submit;
    private Integer position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_modify_film);
        chargeVars();
        reciveInfo();
    }

    private void chargeVars() {

        name = findViewById(R.id.inputName);
        gender = findViewById(R.id.inputGender);
        urlWeb = findViewById(R.id.inputURLweb);
        urlImage = findViewById(R.id.urlImageInput);
        submit = findViewById(R.id.buttonSubmit);
        submit.setOnClickListener(this);
    }

    private void reciveInfo() {
        Intent info = getIntent();
        recivedFilm = new Film(info.getStringExtra("EXTRA_FILM_NAME"), info.getStringExtra("EXTRA_FILM_IMAGE"), info.getStringExtra("EXTRA_FILM_TYPE"), info.getStringExtra("EXTRA_FILM_WEB"));

        if (recivedFilm != null) {

            name.setText(recivedFilm.getName());
            gender.setText(recivedFilm.getType());
            urlWeb.setText(recivedFilm.getUrlWeb());
            urlImage.setText(recivedFilm.getUrlImg());
            position = info.getIntExtra("EXTRA_POSITION",0);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSubmit:

                Intent enviarInfo;
                Film film = new Film(String.valueOf(name.getText()), String.valueOf(urlImage.getText()), String.valueOf(gender.getText()), String.valueOf(urlWeb.getText()));

                enviarInfo = new Intent(this, MainActivity.class);
                enviarInfo.putExtra("EXTRA_POSITION", position);
                enviarInfo.putExtra("EXTRA_FILM_ID", keyFilm);
                enviarInfo.putExtra("EXTRA_FILM_NAME", film.getName());
                enviarInfo.putExtra("EXTRA_FILM_TYPE", film.getType());
                enviarInfo.putExtra("EXTRA_FILM_IMAGE", film.getUrlImg());
                enviarInfo.putExtra("EXTRA_FILM_WEB", film.getUrlWeb());
                setResult(RESULT_OK, enviarInfo);
                finish();
                break;
        }
    }
}