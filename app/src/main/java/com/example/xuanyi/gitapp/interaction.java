//package com.example.xuanyi.gitapp;
//
///**
// * Created by Administrator on 2018/3/26.
// */
//import android.app.DownloadManager;
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.AsyncTask;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ImageView;
//import android.widget.ArrayAdapter;
//import android.widget.ListAdapter;
//import android.widget.ListView;
//import android.widget.SimpleAdapter;
//import android.widget.Toast;
//
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.HashMap;
//import utility.HttpHandler;
//import okhttp3.MediaType;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//import okhttp3.Response;
//
//import static android.content.ContentValues.TAG;
//
//public class interaction {
//
//
//    private class handleFollow extends AsyncTask<Void, Void, Void> {
//        String username;
//
//        private handleFollow(String username) {
//            this.username = username;
//        }
//
//        @Override
//        protected Void doInBackground(Void... arg0) {
//            OkHttpClient client = new OkHttpClient();
//            MediaType mediaType = MediaType.parse("multipart/form-data; boundary=----WebKitFormBoundaryf7d3cd1c4cfd0664d117c90c61f3bcf84f86ca92");
//            RequestBody body = RequestBody.create(mediaType, "------WebKitFormBoundaryf7d3cd1c4cfd0664d117c90c61f3bcf84f86ca92\r\nContent-Disposition: form-data; name=\"cs\"\r\n\r\nf7d3cd1c4cfd0664d117c90c61f3bcf84f86ca92\r\n------WebKitFormBoundaryf7d3cd1c4cfd0664d117c90c61f3bcf84f86ca92--");
//            Request request = new Request.Builder()
//                    .url("https://api.github.com/user/following/" + username)
//                    .put(body)
//                    .addHeader("authorization", "Bearer f7d3cd1c4cfd0664d117c90c61f3bcf84f86ca92")
//                    .addHeader("content-type", "multipart/form-data; boundary=----WebKitFormBoundaryf7d3cd1c4cfd0664d117c90c61f3bcf84f86ca92")
//                    .addHeader("cs", "f7d3cd1c4cfd0664d117c90c61f3bcf84f86ca92")
//                    .addHeader("cache-control", "no-cache")
//                    .addHeader("postman-token", "f7d3cd1c4cfd0664d117c90c61f3bcf84f86ca92")
//                    .build();
//            try {
//                Response response = client.newCall(request).execute();
//                Log.e(TAG, "Response: " + response.body().string());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//
//    }
//
//    private class handleUnfollow extends AsyncTask<Void, Void, Void> {
//        String username;
//
//        private handleUnfollow(String username) {
//            this.username = username;
//        }
//
//        @Override
//        protected Void doInBackground(Void... arg0) {
//            OkHttpClient client = new OkHttpClient();
//
//            MediaType mediaType = MediaType.parse("multipart/form-data; boundary=----WebKitFormBoundaryf7d3cd1c4cfd0664d117c90c61f3bcf84f86ca92");
//            RequestBody body = RequestBody.create(mediaType, "------WebKitFormBoundaryf7d3cd1c4cfd0664d117c90c61f3bcf84f86ca92\r\nContent-Disposition: form-data; name=\"cs\"\r\n\r\nf7d3cd1c4cfd0664d117c90c61f3bcf84f86ca92\r\n------WebKitFormBoundaryf7d3cd1c4cfd0664d117c90c61f3bcf84f86ca92--");
//            DownloadManager.Request request = new DownloadManager.Request.Builder()
//                    .url("https://api.github.com/user/following/" + username)
//                    .delete(body)
//                    .addHeader("authorization", "Bearer f7d3cd1c4cfd0664d117c90c61f3bcf84f86ca92")
//                    .addHeader("content-type", "multipart/form-data; boundary=----WebKitFormBoundaryf7d3cd1c4cfd0664d117c90c61f3bcf84f86ca92")
//                    .addHeader("cs", "f7d3cd1c4cfd0664d117c90c61f3bcf84f86ca92")
//                    .addHeader("cache-control", "no-cache")
//                    .addHeader("postman-token", "f7d3cd1c4cfd0664d117c90c61f3bcf84f86ca92")
//                    .build();
//            try {
//                Response response = client.newCall(request).execute();
//                Log.e(TAG, "Response: " + response.body().string());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            return null;
//        }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//}
