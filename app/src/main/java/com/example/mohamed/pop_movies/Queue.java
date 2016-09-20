package com.example.mohamed.pop_movies;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by mohamed on 9/9/2016.
 */
public class Queue  {
    private static RequestQueue requestQueue;
    private Queue(Context context){


    }
    public static RequestQueue getRequestQueue(Context context){
        if (requestQueue==null){
            requestQueue= Volley.newRequestQueue(context.getApplicationContext());

        }return requestQueue;
    }
}
