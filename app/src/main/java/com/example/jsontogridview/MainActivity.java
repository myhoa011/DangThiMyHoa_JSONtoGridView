package com.example.jsontogridview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.GridView;

import com.example.jsontogridview.adapter.MovieAdapter;
import com.example.jsontogridview.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    GridView gvMovie;
    ArrayList<Movie> list;
    MovieAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gvMovie = findViewById(R.id.gridView);
        list = new ArrayList<>();
        adapter = new MovieAdapter(this, R.layout.movie_item, list);
        gvMovie.setAdapter(adapter);

        new ReadJSON().execute("https://gist.githubusercontent.com/Urdzik/de477f8e3d7baf4366c9d797cfe63531/raw/38c6afa2937ef222323392cc34c8c8c77e02fc40/Movie.json");
    }

    private class ReadJSON extends AsyncTask<String, Void, String> {
        StringBuilder content = new StringBuilder();
        @Override
        protected String doInBackground(String... strings) {
            URL url = null;
            try {
                url = new URL(strings[0]);

                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());

                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String line = "";

                while ((line = bufferedReader.readLine()) != null){
                    content.append(line);
                }
                bufferedReader.close();

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONArray array = new JSONArray(s);

                for (int i = 0; i< array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    String tieuDe = object.getString("Title");
                    String nam = object.getString("Year");
                    String image = object.getString("Poster");

                    list.add(new Movie(tieuDe, nam, image));

                }
                adapter.notifyDataSetChanged();
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }
}