package com.example.android.feedback;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    String agenciesRes = "";
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = (Spinner) findViewById(R.id.spinner1);
//        agenciesRes = HttpHandler.doGet("http://bestlab.us:8080/agencies");

        new LoadAgencies().execute();

    }

    private class LoadAgencies extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            agenciesRes = HttpHandler.doGet("http://bestlab.us:8080/agencies");
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Gson gson = new Gson();
            Agency[] agencies = (Agency[]) gson.fromJson(agenciesRes, Agency[].class);

            ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_dropdown_item_1line, agencies);
            spinner.setAdapter(adapter);
        }
    }

}
