package com.example.mybook;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    // Base URL for Books API.
    private static final String BOOK_BASE_URL = "https://www.googleapis.com/books/v1/volumes";
    // Parameter for the search string.
    private static final String QUERY_PARAM = "q";
    // Parameter that limits search results.
    private static final String MAX_RESULTS = "maxResults";
    // Parameter to filter by print type.
    private static final String PRINT_TYPE = "printType";
    TextView titleText;
    TextView authorsText;
    EditText query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleText = findViewById(R.id.tv_title);
        authorsText = findViewById(R.id.tv_authors);
        query = findViewById(R.id.edit_query);
    }

    public void submit(View view) {
        String queryString = query.getText().toString();
        if (queryString == null) {
            displayToast(getString(R.string.enter_field));
        } else {
            RequestQueue queue = Volley.newRequestQueue(this);
            Uri buildUri = Uri.parse(BOOK_BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, queryString)
                    .appendQueryParameter(MAX_RESULTS, "10")
                    .appendQueryParameter(PRINT_TYPE, "books")
                    .build();
            String url = buildUri.toString();
            System.out.println(url);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (
                            Request.Method.GET, url, (String) null,
                            response -> parseJSONResponse(response),
                            error -> titleText.setText(getString(R.string.not_working))
                    );
            queue.add(jsonObjectRequest);
        }
    }

    private void parseJSONResponse(JSONObject response) {
        try {
            JSONArray itemsArray = response.getJSONArray("items");
            int i = 0;
            String title = null;
            String authors = null;
            while (i < itemsArray.length() && (authors == null && title == null)) {
                JSONObject book = itemsArray.getJSONObject(i);
                JSONObject volumeInfo = book.getJSONObject("volumeInfo");
                System.out.println(book);
                try {
                    title = volumeInfo.getString("title");
                    authors = volumeInfo.getString("authors");
                }catch (Exception e){
                    e.printStackTrace();
                }

                i++;
            }
            if(title != null && authors != null){
                titleText.setText(title);
                authorsText.setText(authors);
            }else{
                titleText.setText(getString(R.string.no_results));
            }
        }catch (Exception e){
            titleText.setText(getString(R.string.no_results));
            e.printStackTrace();
        }
    }

    private void displayToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show();
    }
}