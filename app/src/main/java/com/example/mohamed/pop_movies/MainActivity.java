package com.example.mohamed.pop_movies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mohamed.pop_movies.data.Movie;

public class MainActivity extends AppCompatActivity implements Detail_interface {
    private ActivityDetailsFragment mDetailsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_main_container, new MainActivityFragment())
                .commit();
        mDetailsFragment = (ActivityDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_details);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(getApplicationContext(), Sorting_Movies_Settings.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public void Data_transmission(Movie movie) {
        if (mDetailsFragment != null) {
            mDetailsFragment.movieData(movie);
        } else {
            ActivityDetailsFragment fragment = new ActivityDetailsFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable(Movie.KEY, movie);
            fragment.setArguments(bundle);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_main_container, fragment)
                    .commit();
        }


    }
}
