package com.example.android.feedback;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import static com.example.android.feedback.R.id.btnSend;

public class MainActivity extends AppCompatActivity {

    String agenciesRes = "";
    String feedbackRes = "";
    Spinner spinner;
    EditText txtFeedback;
    Agency selectedAgency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = (Spinner) findViewById(R.id.spinner1);

        new LoadAgencies().execute();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String seleted = (String) adapterView.getSelectedItem();
                selectedAgency.name = seleted;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        txtFeedback = (EditText) findViewById(R.id.txtFeedback);

        Button btnSend = (Button) findViewById(R.id.btnSend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SendFeedback().execute(new Feedback(selectedAgency, txtFeedback.getText()+""));
            }
        });
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

    private class SendFeedback extends AsyncTask<Feedback, Void, Void> {
        @Override
        protected Void doInBackground(Feedback... feedbacks) {
            feedbackRes = HttpHandler.doPost("http://bestlab.us:8080/feedbacks", feedbacks[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (!feedbackRes.equals("")) {
                Toast.makeText(MainActivity.this, "Feedback is sent", Toast.LENGTH_SHORT).show();
            }
        }
    }



}
