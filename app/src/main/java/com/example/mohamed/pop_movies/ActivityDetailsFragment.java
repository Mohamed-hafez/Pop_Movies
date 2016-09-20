package com.example.mohamed.pop_movies;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.mohamed.pop_movies.data.Movie;
import com.example.mohamed.pop_movies.data.ReviewsContainer;
import com.example.mohamed.pop_movies.data.Trailer;
import com.example.mohamed.pop_movies.data.TrailersContainer;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

/**
 * A placeholder fragment containing a simple view.
 */
public class ActivityDetailsFragment extends Fragment {
    Details_Adapter details_adapter;
    ExpandableListView expListView;
    private Movie mMovie;
    private TextView Movie_Time, Movie_Title, Movie_year, Rate, Description;
    private ImageView imageView;

    public ActivityDetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View header = inflater.inflate(R.layout.fragment_activity_details, container, false);
        View view = inflater.inflate(R.layout.content_details, container, false);
        expListView = (ExpandableListView) view.findViewById(R.id.content_details);
        Movie_Title = (TextView) header.findViewById(R.id.Movie_Title);
        Movie_year = (TextView) header.findViewById(R.id.Movie_year);
        Movie_Time = (TextView) header.findViewById(R.id.Movie_Time);
        Rate = (TextView) header.findViewById(R.id.Movie_Rate);
        Description = (TextView) header.findViewById(R.id.description_text);
        imageView = (ImageView) header.findViewById(R.id._Details_Movie_Image);
        expListView.addHeaderView(header);
        details_adapter = new Details_Adapter(getActivity(), null, null);
        expListView.setAdapter(details_adapter);
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView
                    , View view
                    , int groupPosition, int childPosition, long l) {
                String string = "jjj";
                Trailer trailer = (Trailer) view.getTag();
                if (trailer != null) {

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://m.youtube.com/watch?v=" +
                            trailer.getKey()));
                    startActivity(intent);
                }

                return false;
            }
        });
        if(getArguments()!=null){
        mMovie = (Movie) getArguments().getSerializable(Movie.KEY);
        movieData(mMovie);}
        return view;

    }

    public void movieData(Movie movie) {
        mMovie = movie;
        Movie_Title.setText(movie.getOriginalTitle());
        String s = movie.getReleaseDate().substring(0, 4);
        Movie_year.setText(s);
        Rate.setText(movie.getVoteAverage() + "/10");
        Description.setText(movie.getOverview());
        Picasso.with(this.getActivity()).load("http://image.tmdb.org/t/p/w185/" + movie.getPosterPath()).into(imageView);
        prepareListData();

    }

    private void prepareListData() {

        details_adapter.removeAll();
        Movie_Trailers_Request();
        Movie_Reviews_Request();
        Full_Movie_Request();
    }

    private void Full_Movie_Request() {
        // request for more data
        //using Volley
        final StringRequest stringRequest =
                new StringRequest("http://api.themoviedb.org/3/movie/" + mMovie.getMovie_id() + "?api_key=915ca7161ac0a2af75cf01fc3ae93a4c"
                        , new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        //using Gson
                        Gson gson = new Gson();
                        mMovie = gson.fromJson(response, Movie.class);
                        String movie_min = mMovie.getRuntime() + "min";
                        Movie_Time.setText(movie_min);
                        //
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Please Connect To The Internet", Toast.LENGTH_SHORT).show();
                    }
                });
        Queue.getRequestQueue(getActivity()).add(stringRequest);
    }

    private void Movie_Trailers_Request() {

        //using Volley
        final StringRequest stringRequest =
                new StringRequest("http://api.themoviedb.org/3/movie/" + mMovie.getMovie_id()
                        + "/videos?api_key=915ca7161ac0a2af75cf01fc3ae93a4c"
                        , new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //using Gson
                        Gson gson = new Gson();
                        TrailersContainer container = gson.fromJson(response, TrailersContainer.class);

//
                        details_adapter.addTrailers(container.getTrailers());
                        Log.v("Trailer", response);
                        //    Toast.makeText(ActivityDetailsFragment.this.getActivity(), response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Please Connect To The Internet", Toast.LENGTH_SHORT).show();
                    }
                });
        Queue.getRequestQueue(getActivity()).add(stringRequest);
    }

    private void Movie_Reviews_Request() {
        final StringRequest stringRequest =
                new StringRequest("http://api.themoviedb.org/3/movie/" + mMovie.getMovie_id()
                        + "/reviews?api_key=915ca7161ac0a2af75cf01fc3ae93a4c"
                        , new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        //using Gson
                        Gson gson = new Gson();
                        ReviewsContainer container = gson.fromJson(response, ReviewsContainer.class);
                        details_adapter.addReviews(container.getReviews());

                        Log.v("Reviews", response);
                        //
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Please Connect To The Internet", Toast.LENGTH_SHORT).show();
                    }
                });
        Queue.getRequestQueue(getActivity()).add(stringRequest);
    }

}
