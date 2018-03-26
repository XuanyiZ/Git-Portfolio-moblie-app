package com.example.xuanyi.gitapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import utility.HttpHandler;

public class LoginActivity extends AppCompatActivity {
    private ProgressDialog pDialog;
    private ProgressDialog pDialog2;
    private String TAG = LoginActivity.class.getSimpleName();
    public TextView Oauth;
    /**
     * create Repositories page
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Button login = (Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetOauth().execute();

            }


    });

    }

    private class GetOauth extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(LoginActivity.this);
            pDialog.setMessage("Waiting for Authorization...\nAuthroization Success!");
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String Str = sh.makeServiceCall("https://api.github.com/user?access_token=f7d3cd1c4cfd0664d117c90c61f3bcf84f86ca92","GET");
            Log.e(TAG, "Response from url: " + Str);

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            setContentView(R.layout.activity_profile);
            pDialog.dismiss();
            pDialog = new ProgressDialog(LoginActivity.this);
            pDialog.setMessage("Authroization Success!");

            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            pDialog.dismiss();



            return;
        }

    }

}










































