package com.example.xuanyi.gitapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import utility.HttpHandler;

public class FollowingsActivity extends AppCompatActivity {

    private String TAG = FollowingsActivity.class.getSimpleName();

    private ProgressDialog pDialog;
    private ListView followerListView;

    // URL to get contacts JSON
    private static String url = "https://api.github.com/users/XuanyiZ/following";

    ArrayList<HashMap<String, String>> followerList;
    ArrayList<FollowInfo> followInfoList = new ArrayList<FollowInfo>();


    /**
     * create Repositories page
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followings);

        followerList = new ArrayList<>();

        followerListView = (ListView) findViewById(R.id.list3);

        new GetFollowInfo().execute();
//        new DownloadImageTask((ImageView) findViewById(R.id.follower_Image))
//                .execute("https://avatars3.githubusercontent.com/u/23559202?v=4");
    }


    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

    //below funcs: Parsing JSON
    /**
     * Async task class to get json by making HTTP call
     */
    private class GetFollowInfo extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(FollowingsActivity.this);
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
            String FILENAME = "Store_Following_list";

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
                        String avatar_url = e.getString("avatar_url");
                        String username = e.getString("login");



                        HashMap<String, String> followinfo = new HashMap<String, String>();
                        followinfo.put("id",  id);
                        followinfo.put("html_url", html_url);
                        followinfo.put("avatar_url", avatar_url);
                        followinfo.put("username", username);


                        followInfoList.add(new FollowInfo(username, avatar_url, html_url));
                        followerList.add(followinfo);
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
            final FollowingAdapter mAdapter = new FollowingAdapter(FollowingsActivity.this, R.layout.following_item, followInfoList);
            followerListView.setAdapter(mAdapter);
            //if the item is clicked, goto the git website
            followerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String url = mAdapter.getItem(position).getFollow_link();
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
            });
        }

    }


}










































