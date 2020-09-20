package com.example.movie_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    TextView nameofMovie,plotSynopsys,userrating,releasedate,plot,rating;
    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

     imageView =findViewById(R.id.imgviewdetails);
     nameofMovie=(TextView)findViewById(R.id.title);
      plotSynopsys=findViewById(R.id.plotsynopsys);
      plot=findViewById(R.id.plot);
      userrating=findViewById(R.id.userrating);
      releasedate=findViewById(R.id.releasedate);
      rating=findViewById(R.id.rating);
        Intent intent=getIntent();
        if(intent.hasExtra("original_title")) {
            String thumbnail = intent.getExtras().getString("poster_path");
            String movieName = intent.getExtras().getString("original_title");
            String synopsis = intent.getExtras().getString("overview");
            String rating = intent.getExtras().getString("vote_average");
            String dateofrelease =intent.getExtras().getString("release_date");
            Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w500"+thumbnail)
                    .placeholder(R.drawable.loading)
                    .into(imageView);

            nameofMovie.setText(movieName);
            plotSynopsys.setText(synopsis);
            userrating.setText(rating);
            releasedate.setText(dateofrelease);

        }else{
            Toast.makeText(this,"No Api data fetched",Toast.LENGTH_SHORT).show();
        }

    }


}
