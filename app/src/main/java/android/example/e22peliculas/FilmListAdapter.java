package android.example.e22peliculas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class FilmListAdapter extends RecyclerView.Adapter<FilmListAdapter.FilmViewHolder> {
    private final LinkedList<Film> filmList;
    private final LayoutInflater inflater;
    private Context context;
    private final int REQUEST_CODE = 1;
    /**
     * Constructor
     */
    public FilmListAdapter(Context context, LinkedList<Film> filmList) {
        this.filmList = filmList;
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.film_list, parent, false);

        return new FilmViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmViewHolder holder, int position) {
        Film film = filmList.get(position);
        holder.filmTitle.setText(film.getName());
        holder.filmType.setText(film.getType());
        Glide.with(this.context).load(film.getUrlImg()).into(holder.filmThumbnail);
    }

    @Override
    public int getItemCount() {
        return filmList.size();
    }

    public class FilmViewHolder extends RecyclerView.ViewHolder implements View

            .OnClickListener {
        public final ConstraintLayout constraintLayout;
        public final TextView filmTitle;
        public final TextView filmType;
        public final ImageView filmThumbnail;
        public ImageView delete;
        public ImageView viewFilm;

        public FilmViewHolder(@NonNull View itemView, FilmListAdapter adapter) {
            super(itemView);
            this.constraintLayout = itemView.findViewById(R.id.constraintFilm);
            this.filmTitle = itemView.findViewById(R.id.FilmTitle);
            this.filmThumbnail = itemView.findViewById(R.id.filmThumbnail);
            this.filmType = itemView.findViewById(R.id.FilmCategory);
            this.delete = itemView.findViewById(R.id.deleteFilmImage);
            this.viewFilm = itemView.findViewById(R.id.webViewImage);

            delete.setOnClickListener(this);
            viewFilm.setOnClickListener(this);
            filmThumbnail.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Integer position = getLayoutPosition();
            Film film = filmList.get(position);
            Uri web = Uri.parse(film.getUrlWeb());
            switch (v.getId()) {
                case R.id.webViewImage:

                    go2WebActivity(v, web);
                    break;
                case R.id.deleteFilmImage:

                    removeFilm(v, position, film);
                    break;
                case R.id.filmThumbnail:
                    go2SecondActivity(v, film, position);
                    break;
                default:

                    break;
            }
        }

        private void removeFilm(View v, Integer position, Film film) {
            //Glide.with(v.getContext()).clear(v);
            filmList.remove(film);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, filmList.size());
        }

        private void go2SecondActivity(View v, Film film, Integer position) {
            Intent intent = new Intent(context, AddModifyFilm.class);
            Activity actividad = ((Activity) context);


            intent.putExtra("EXTRA_FILM_NAME", film.getName());
            intent.putExtra("EXTRA_FILM_TYPE", film.getType());
            intent.putExtra("EXTRA_FILM_IMAGE", film.getUrlImg());
            intent.putExtra("EXTRA_FILM_WEB", film.getUrlWeb());
            removeFilm(v, position, film);
            actividad.startActivityForResult(intent, REQUEST_CODE);
        }



        private void go2WebActivity(@NotNull View v, Uri web) {
            Intent webView = new Intent(Intent.ACTION_VIEW, web);
            if (webView.resolveActivity(v.getContext().getPackageManager()) != null) {
                v.getContext().startActivity(webView);
            } else {
                Snackbar.make(v, "No es posible abrir el enlace", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }
    }

}
