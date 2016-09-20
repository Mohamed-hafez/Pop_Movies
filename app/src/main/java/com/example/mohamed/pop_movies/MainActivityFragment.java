package com.example.mohamed.pop_movies;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mohamed.pop_movies.data.Data;
import com.example.mohamed.pop_movies.data.Movie;
import com.google.gson.Gson;


public class MainActivityFragment extends Fragment {
    public SharedPreferences sharedPrefs;
    private RequestQueue requestQueue;
    private GridView gridView;
    private Detail_interface detail_interface;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        detail_interface = (Detail_interface) getActivity();
        final View root_view = inflater.inflate(R.layout.fragment_main, container, false);
        // getting grad_view  from xml to GridView
        gridView = (GridView) root_view.findViewById(R.id.grad_view);
        // put listener to the grid view when click the movie_image on the gridView
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                detail_interface.Data_transmission((Movie) gridView.getAdapter().getItem(position));
            }
        });

        requestQueue = Volley.newRequestQueue(getActivity());


        return root_view;
    }

    private void refreshData() {
        //using SharedPreferences
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String Movie_Sort_type = sharedPrefs.getString(
                getString(R.string.pref_Sorting_key),
                getString(R.string.pref_Sorting_Popular_Movies_value));
        //using Volley
        final StringRequest stringRequest =
                new StringRequest("http://api.themoviedb.org/3" + Movie_Sort_type + "?api_key=915ca7161ac0a2af75cf01fc3ae93a4c&page=1"
                        , new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        //using Gson
                        Gson gson = new Gson();
                        Data d = gson.fromJson(response, Data.class);
                        gridView.setAdapter(new Movie_Data_Adapter(getActivity(), d.getMovies()));
                        //
                      /*  ActiveAndroid.beginTransaction();
                        try {
                            for (Movie movie : d.getMovies()) {
                                movie.save();

                            }
                            ActiveAndroid.setTransactionSuccessful();

                        } finally {
                            ActiveAndroid.endTransaction();
                        }
new Thread(new Runnable() {
    @Override
    public void run() {
        List<Movie> list=new Select().from(Movie.class).execute();
        for (Movie movie:list){
            Log.v("",movie.toString());
        }
    }
}).start();*/
                        //
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Please Connect To The Internet", Toast.LENGTH_SHORT).show();
                    }
                });
      //  requestQueue.add(stringRequest);
        Queue.getRequestQueue(getActivity()).add(stringRequest);
    }

    @Override
    public void onResume() {
        super.onResume();

        refreshData();
    }
}



