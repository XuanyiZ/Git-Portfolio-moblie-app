package com.example.xuanyi.gitapp;
/**
 * Created by Administrator on 2018/3/11.
 * citation: https://stackoverflow.com/questions/13196234/simple-parse-json-from-url-on-android-and-display-in-listview/13196301#13196301
 * http://www.vetbossel.in/android-json-parsing-from-url/
 * https://stackoverflow.com/questions/13784825/how-can-i-parse-a-json-object-and-display-it-in-a-list-view
 * https://stackoverflow.com/questions/36221795/android-parse-json-data-from-a-web-server-and-display-on-listview
 */
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import utility.HttpHandler;



public class RepositoriesActivity extends AppCompatActivity {

    private String TAG = RepositoriesActivity.class.getSimpleName();

    private ProgressDialog pDialog;
    private ListView repoListView;

    // URL to get contacts JSON
    private static String url = "https://api.github.com/users/XuanyiZ/repos";

    ArrayList<HashMap<String, String>> repoList;
    ArrayList<RepoInfo> repoInfoList = new ArrayList<RepoInfo>();


    /**
     * create Repositories page
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositories);

        repoList = new ArrayList<>();

        repoListView = (ListView) findViewById(R.id.list);

        new GetRepoInfo().execute();
    }

    //below funcs: Parsing JSON
    /**
     * Async task class to get json by making HTTP call
     */
    private class GetRepoInfo extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(RepositoriesActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        /**
         * use httpHandler helper func to get necessary info online
         */
        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url, "GET");
            String FILENAME = "Store_Repo_info";

            FileOutputStream fos = null;
            try {
                fos = openFileOutput(FILENAME, Context.MODE_APPEND);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                fos.write(jsonStr.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONArray repoJsonList = new JSONArray(jsonStr);

                    for(int i=0;i<repoJsonList.length();i++){

                        JSONObject e = repoJsonList.getJSONObject(i);

                        String id = e.getString("id");
                        String html_url = e.getString("html_url");
                        String name = e.getString("name");
                        String full_name = e.getString("full_name");
                        JSONObject ownerObj = e.getJSONObject("owner");
                        String owner_name = ownerObj.getString("login");
                        String description = e.getString("description");


                        HashMap<String, String> repoInfo = new HashMap<String, String>();
                        repoInfo.put("id",  id);
                        repoInfo.put("html_url", html_url);
                        repoInfo.put("name", name);
                        repoInfo.put("full_name", full_name);
                        repoInfo.put("owner_name", owner_name);
                        repoInfo.put("description", description);

                        repoInfoList.add(new RepoInfo(name, owner_name, description, html_url));
                        repoList.add(repoInfo);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });
                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });
            }
            return null;
        }

        /**
         * set adaptor after getting jason file from api
         */
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            final UsersAdapter mAdapter = new UsersAdapter(RepositoriesActivity.this, R.layout.repo_item, repoInfoList);
            repoListView.setAdapter(mAdapter);
            //if the item is clicked, goto the git website
            repoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String url = mAdapter.getItem(position).getRepo_link();
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
            });
        }

    }
}
