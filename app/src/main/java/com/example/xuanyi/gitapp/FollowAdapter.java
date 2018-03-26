package com.example.xuanyi.gitapp;

/**
 * Created by Administrator on 2018/3/25.
 */

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.net.URL;
import android.graphics.BitmapFactory;
import java.util.Random;


public class FollowAdapter extends ArrayAdapter<FollowInfo> {

    public FollowAdapter(Context context , int i, ArrayList<FollowInfo> users) {
        super(context, i, users);
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            Log.e("src",src);
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Log.e("Bitmap","returned");
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception",e.getMessage());
            return null;
        }
    }
    int globalcounter=1;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        FollowInfo user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.follower_item, parent, false);
        }
        // Lookup view for data population
        TextView follower_name = (TextView) convertView.findViewById(R.id.follower_username);
        ImageView follower_Image= (ImageView) convertView.findViewById(R.id.follower_Image);
        // Populate the data into the template view using the data object
        follower_name.setText(user.getFollow_username());
        Random rand=new Random();
        int n=globalcounter%20;
        //int n=rand.nextInt(20)+1;
        if (n==1){
            follower_Image.setImageResource(R.drawable.r1);}
        else if(n==2){
            follower_Image.setImageResource(R.drawable.r2);
        }
        else if(n==3){
            follower_Image.setImageResource(R.drawable.r3);
        }
        else if(n==4){
            follower_Image.setImageResource(R.drawable.r4);
        }
        else if(n==5){
            follower_Image.setImageResource(R.drawable.r5);
        }
        else if(n==6){
            follower_Image.setImageResource(R.drawable.r6);
        }
        else if(n==7){
            follower_Image.setImageResource(R.drawable.r7);
        }
        else if(n==8){
            follower_Image.setImageResource(R.drawable.r8);
        }
        else if(n==9){
            follower_Image.setImageResource(R.drawable.r9);
        }
        else if(n==10){
            follower_Image.setImageResource(R.drawable.r10);
        }
        else if(n==11){
            follower_Image.setImageResource(R.drawable.r11);
        }
        else if(n==12){
            follower_Image.setImageResource(R.drawable.r12);
        }
        else if(n==13){
            follower_Image.setImageResource(R.drawable.r13);
        }
        else if(n==14){
            follower_Image.setImageResource(R.drawable.r14);
        }
        else if(n==15){
            follower_Image.setImageResource(R.drawable.r15);
        }
        else if(n==16){
            follower_Image.setImageResource(R.drawable.r16);
        }
        else if(n==17){
            follower_Image.setImageResource(R.drawable.r17);
        }
        else if(n==18){
            follower_Image.setImageResource(R.drawable.r18);
        }
        else if(n==19){
            follower_Image.setImageResource(R.drawable.r19);
        }
        else if(n==20){
            follower_Image.setImageResource(R.drawable.r20);
        }
        else if(n==21){
            follower_Image.setImageResource(R.drawable.r21);
        }
        globalcounter++;
        //follower_Image.setImageBitmap(getBitmapFromURL(user.getImgurl()));
        //Return the completed view to render on screen
        return convertView;
    }
}
