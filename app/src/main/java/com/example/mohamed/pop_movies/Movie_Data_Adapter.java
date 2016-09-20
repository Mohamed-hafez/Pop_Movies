package com.example.mohamed.pop_movies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.mohamed.pop_movies.data.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;


public class Movie_Data_Adapter extends BaseAdapter
{

     LayoutInflater inflater;
    private List<Movie> Movie_data;
    private Context Member_Context;
    public Movie_Data_Adapter(Context c, List<Movie> Movie_data){
        Member_Context =c;
        this.Movie_data = Movie_data;
        inflater= (LayoutInflater) Member_Context.getSystemService(Member_Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return Movie_data.size();
    }

    @Override
    public Object getItem(int position) {
        return Movie_data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View contentView, ViewGroup viewGroup) {
        // first way
        View  view=inflater.inflate(R.layout.image_view_layout,null);
        ImageView imageView= (ImageView) view.findViewById(R.id.Movie_Image);
         imageView.setPadding(1,1,1,1);
        Movie movie;
        //getting the position  from Movie_data
        movie= Movie_data.get(position);
        //using picasso -by giving it the  the base URl+PosterPath from Results class in Movies_data
        Picasso.with(Member_Context).load("http://image.tmdb.org/t/p/w185/"+ movie.getPosterPath()).into(imageView);

        return view;

 //second way
      /*  ImageView imageView;
        if (contentView == null) {
            // if it's not recycled, initialize some attributes
    imageView = new ImageView(Member_Context);
    imageView.setLayoutParams(new GridView.LayoutParams(900,1200));
//           imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
} else {
        imageView = (ImageView) contentView;
        }
        Results results;
        results= Movie_data.get(position);
        Picasso.with(Member_Context).load("http://image.tmdb.org/t/p/w185/"+ results.getPosterPath()).into(imageView);

        return imageView;*/

        }
}


