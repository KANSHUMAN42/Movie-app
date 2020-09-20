package com.example.movie_app.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie_app.DetailActivity;
import com.example.movie_app.R;
import com.example.movie_app.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {



   private Context context;
   private List<Movie>movielist;
   public MoviesAdapter(Context context,List<Movie> movielist){
       this.context=context;
       this.movielist=movielist;

   }


    @NonNull
    @Override
    public MoviesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_card,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       holder.titlex.setText(movielist.get(position).getOriginalTitle());
       String vote=Double.toString(movielist.get(position).getVoteAverage());
       holder.userratingx.setText(vote);

        Picasso.get().load("https://image.tmdb.org/t/p/w500"+movielist.get(position).getPosterPath())
                .noFade()
                 .into(holder.thumnailx);

    }

    @Override
    public int getItemCount() {

       return movielist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
       public  TextView titlex,userratingx;
        public ImageView thumnailx;
        public MyViewHolder(@NonNull View view) {
            super(view);
            titlex=view.findViewById(R.id.titlex);
            userratingx=view.findViewById(R.id.userratingcardx);
            thumnailx=view.findViewById(R.id.thumbnailx);

            thumnailx.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=getAdapterPosition();
                    if(pos!=RecyclerView.NO_POSITION){
                        Movie movielisted=movielist.get(pos);
                        Intent i =new Intent(context, DetailActivity.class);
                        i.putExtra("original_title",movielisted.getOriginalTitle());
                        i.putExtra("poster_path",movielisted.getPosterPath());
                        i.putExtra("overview",movielisted.getOverview());
                        i.putExtra("vote_average",movielisted.getVoteAverage().toString());
                        i.putExtra("release_date",movielisted.getReleaseDate());
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(i);
                        Toast.makeText(context,"You clicked",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(context,"No further process",Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }
    }
}

















