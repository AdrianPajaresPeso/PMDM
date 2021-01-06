package android.example.e22peliculas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_CODE = 1;
    public final LinkedList<Film> mFilmList = new LinkedList<Film>();
    private RecyclerView mRecyclerView;
    private FilmListAdapter mFilmAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chargeVars();
    }

    private void chargeVars() {
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        setClickListenerOnFloatingButton(fab);

        generateDefaultFilms();

        mRecyclerView = findViewById(R.id.recyclerView);
        mFilmAdapter = new FilmListAdapter(this, mFilmList);
        mRecyclerView.setAdapter(mFilmAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setClickListenerOnFloatingButton(FloatingActionButton fab) {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newFilm = new Intent(getApplicationContext(), AddModifyFilm.class);
                startActivityForResult(newFilm, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int filmID = data.getIntExtra("EXTRA_FILM_ID", -1);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            Film film = new Film(data.getStringExtra("EXTRA_FILM_NAME"), data.getStringExtra("EXTRA_FILM_IMAGE"), data.getStringExtra("EXTRA_FILM_TYPE"), data.getStringExtra("EXTRA_FILM_WEB"));

                if(filmID != -1){
                    mFilmList.set(filmID, film);
                    mFilmAdapter.notifyItemChanged(data.getIntExtra("EXTRA_POSITION",0));
                }else{
                    mFilmList.add(film);
                    mFilmAdapter.notifyDataSetChanged();

                }

        }
    }

    private void generateDefaultFilms() {
        mFilmList.add(new Film( "Cadena Perpetua", "https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_UX182_CR0,0,182,268_AL_.jpg", getString(R.string.dramaType), getString(R.string.url_cadena_perpetua)));
        mFilmList.add(new Film( "Matrix", "https://m.media-amazon.com/images/M/MV5BNzQzOTk3OTAtNDQ0Zi00ZTVkLWI0MTEtMDllZjNkYzNjNTc4L2ltYWdlXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_UX182_CR0,0,182,268_AL_.jpg", getString(R.string.actionType) + ", " + getString(R.string.scifiType), getString(R.string.matrixUrl)));
        mFilmList.add(new Film( "El club de los poetas muertos", "https://m.media-amazon.com/images/M/MV5BOGYwYWNjMzgtNGU4ZC00NWQ2LWEwZjUtMzE1Zjc3NjY3YTU1XkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_UX182_CR0,0,182,268_AL_.jpg", getString(R.string.comedyType) + ", " + getString(R.string.dramaType), getString(R.string.url_club_poetas_muertos)));
        mFilmList.add(new Film( "La milla verde", "https://m.media-amazon.com/images/M/MV5BMTUxMzQyNjA5MF5BMl5BanBnXkFtZTYwOTU2NTY3._V1_UX182_CR0,0,182,268_AL_.jpg", getString(R.string.crimeType) + ", " + getString(R.string.dramaType) + ", " + getString(R.string.fantasyType), "https://www.imdb.com/title/tt0120689/"));
        mFilmList.add(new Film("Avatar ","https://m.media-amazon.com/images/M/MV5BMTYwOTEwNjAzMl5BMl5BanBnXkFtZTcwODc5MTUwMw@@._V1_UX182_CR0,0,182,268_AL_.jpg","Action, Adventure, Fantasy","https://www.imdb.com/title/tt0499549/"));
        mFilmList.add(new Film("Figuras ocultas","https://m.media-amazon.com/images/M/MV5BMzg2Mzg4YmUtNDdkNy00NWY1LWE3NmEtZWMwNGNlMzE5YzU3XkEyXkFqcGdeQXVyMjA5MTIzMjQ@._V1_UX182_CR0,0,182,268_AL_.jpg","Biography, Drama, History","https://www.imdb.com/title/tt4846340/"));
        mFilmList.add(new Film("Interstellar","https://m.media-amazon.com/images/M/MV5BZjdkOTU3MDktN2IxOS00OGEyLWFmMjktY2FiMmZkNWIyODZiXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_UX182_CR0,0,182,268_AL_.jpg"," Adventure, Drama, Sci-Fi ","https://www.imdb.com/title/tt0816692/"));
        mFilmList.add(new Film("Joker","https://m.media-amazon.com/images/M/MV5BNGVjNWI4ZGUtNzE0MS00YTJmLWE0ZDctN2ZiYTk2YmI3NTYyXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_UX182_CR0,0,182,268_AL_.jpg","Crime, Drama, Thriller","https://www.imdb.com/title/tt7286456/"));
        mFilmList.add(new Film("El hombre bicentenario","https://m.media-amazon.com/images/M/MV5BYTU4Nzg5YmItNzE0Yy00Y2VmLWI3OTYtNTFjODEzMDE0YTI4XkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_UX182_CR0,0,182,268_AL_.jpg","Comedy, Drama, Sci-Fi","https://www.imdb.com/title/tt0182789/"));
        mFilmList.add(new Film("El gran dictador","https://m.media-amazon.com/images/M/MV5BMmExYWJjNTktNGUyZS00ODhmLTkxYzAtNWIzOGEyMGNiMmUwXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_UX182_CR0,0,182,268_AL_.jpg","Comedy, Drama, War","https://www.imdb.com/title/tt0032553/"));
        mFilmList.add(new Film("Tiempos modernos","https://m.media-amazon.com/images/M/MV5BYjJiZjMzYzktNjU0NS00OTkxLWEwYzItYzdhYWJjN2QzMTRlL2ltYWdlL2ltYWdlXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_UX182_CR0,0,182,268_AL_.jpg","Comedy, Drama, Family","https://www.imdb.com/title/tt0027977/"));
        mFilmList.add(new Film("El viaje de Chihiro","https://m.media-amazon.com/images/M/MV5BMjlmZmI5MDctNDE2YS00YWE0LWE5ZWItZDBhYWQ0NTcxNWRhXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_UX182_CR0,0,182,268_AL_.jpg","Animation, Adventure, Family","https://www.imdb.com/title/tt0245429/"));
        mFilmList.add(new Film("Nôgêmu nôraifu: Zero","https://m.media-amazon.com/images/M/MV5BMzkwY2E0NjItZGE2MS00MmFlLTlhOGUtZTAyNTI3MDg4YWZkXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_UY268_CR4,0,182,268_AL_.jpg","Animation, Adventure, Comedy","https://www.imdb.com/title/tt5914996/"));
        //mFilmList.add(new Film());
    }


}