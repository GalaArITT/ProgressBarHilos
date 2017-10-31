package com.example.oliver.progressbarhilos;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {

    int leer;
    Button button1,button2,button3;
    ProgressBar progressBar1,progressBar2,progressBar3;
    EditText editText1,editText2,editText3;
    AsyncTask_load hiloConectar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);

        progressBar1 = (ProgressBar) findViewById(R.id.progressBar);
        progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
        progressBar3 = (ProgressBar) findViewById(R.id.progressBar3);

        editText1 = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);

        eventosBotones();
    }

    public void eventosBotones(){
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar1.setProgress(0);
                leer = parseInt(editText1.getText().toString());
                hiloConectar = new AsyncTask_load(progressBar1,leer);
                hiloConectar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar2.setProgress(0);
                leer = parseInt(editText2.getText().toString());
                hiloConectar = new AsyncTask_load(progressBar2,leer);
                hiloConectar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar3.setProgress(0);
                leer = parseInt(editText3.getText().toString());
                hiloConectar = new AsyncTask_load(progressBar3,leer);
                hiloConectar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        });
    }

    private void UnSegundo() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public  class AsyncTask_load extends AsyncTask<Void,Integer, Boolean> {

        ProgressBar barraProgreso;
        int valorMaximo;
        public AsyncTask_load(ProgressBar barraProgreso,int valorMaximo) {
            this.barraProgreso = barraProgreso;
            this.valorMaximo =valorMaximo;
        }
        @Override
        protected Boolean doInBackground(Void... voids) {
            for (int i=1; i<=valorMaximo; i++){
                UnSegundo();
                publishProgress(i);
                if (isCancelled()){
                    break;
                }
            }
            return true;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            barraProgreso.setMax(valorMaximo);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean){
                Toast.makeText(getApplicationContext(),"Tarea finaliza AsyncTask "+barraProgreso.getProgress(),Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            barraProgreso.setProgress(values[0]);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Toast.makeText(getApplicationContext(),"Tarea NO finaliza AsyncTask", Toast.LENGTH_SHORT).show();
        }
    }
}
