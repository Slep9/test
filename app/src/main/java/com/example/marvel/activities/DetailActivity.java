package com.example.marvel.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.marvel.R;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        Bundle extras = getIntent().getExtras();

        String name = extras.getString("name");

        ((TextView) findViewById(R.id.name)).setText(name);
        ((TextView) findViewById(R.id.description)).setText(extras.getString("description"));
        Picasso.get().load(extras.getString("url")).into((ImageView) findViewById(R.id.detail_image));

        setTitle(name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}