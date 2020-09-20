package com.example.movie_app;

import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie_app.adapter.MoviesAdapter;
import com.example.movie_app.api.Client;
import com.example.movie_app.api.Service;
import com.example.movie_app.model.Movie;
import com.example.movie_app.model.MovieResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MoviesAdapter adapter;
    List<Movie> movieList;

    ProgressDialog progressDialog;
    public static final String Log_tag=MoviesAdapter.class.getName();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initviews();



    }

    private void initviews() {
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("FetchingMovies");
        progressDialog.show();
        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        movieList=new ArrayList<>();
        adapter=new MoviesAdapter(this,movieList);
        if(getApplicationContext().getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT){
            recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        }else{
            recyclerView.setLayoutManager(new GridLayoutManager(this,4));
        }
        recyclerView.setAdapter(adapter);
        loadJson();
    }
    private void loadJson(){
              try {

                 if(BuildConfig.API_KEY.isEmpty()){
                     Toast.makeText(getApplicationContext(),"empty key",Toast.LENGTH_SHORT).show();
                     progressDialog.dismiss();
                     return;

                 }
                  Client Client=new Client();
                 Service apiservice=Client.getClient().create(Service.class);
                  Call<MovieResponse>call=apiservice.getpopularMovies(BuildConfig.API_KEY);
                  call.enqueue(new Callback<MovieResponse>() {
                      @Override
                      public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                          List<Movie>movies=response.body().getResults();
                          recyclerView.setAdapter(new MoviesAdapter(getApplicationContext(),movies));

                          progressDialog.dismiss();
                      }
                      @Override
                      public void onFailure(Call<MovieResponse> call, Throwable t) {
                      //  Log.d("Error",t.getMessage());
                          progressDialog.dismiss();
                          Toast.makeText(getApplicationContext(),"error in fetching data",Toast.LENGTH_SHORT).show();
                      }
                  });
              }catch (Exception e){
                  Log.d("Error",e.getMessage());

                  Toast.makeText(getApplicationContext(),"Error Total Fetching Data",Toast.LENGTH_SHORT).show();

              }





    }




}