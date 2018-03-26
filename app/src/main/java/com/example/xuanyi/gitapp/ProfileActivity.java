package com.example.xuanyi.gitapp;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }


    public void gotoRepositories(View view) {
        Intent intent = new Intent(this, RepositoriesActivity.class);
        startActivity(intent);
    }



    public void gotoFollowers(View view) {
        Intent intent = new Intent(this, FollowersActivity.class);
        startActivity(intent);
    }


    public void gotoFollowings(View view) {
        Intent intent = new Intent(this, FollowingsActivity.class);
        startActivity(intent);
    }

    public void gotoLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
