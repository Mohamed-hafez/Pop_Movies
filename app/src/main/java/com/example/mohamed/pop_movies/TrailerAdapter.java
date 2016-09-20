package com.example.mohamed.pop_movies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.mohamed.pop_movies.data.Trailer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class TrailerAdapter extends BaseAdapter {
    private List<Trailer> mData;

    public TrailerAdapter(List<Trailer> trailers) {
        if (trailers == null)
            mData = new ArrayList<>();
        else
            mData = trailers;
    }

    public TrailerAdapter() {
        this(null);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null)
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.trailer_layout, viewGroup, false);
        TextView textView = (TextView) view.findViewById(R.id.trailer_Name);
        textView.setText(mData.get(i).getName());

        return view;
    }

    public void addAll(Collection<Trailer> trailers) {
        mData.addAll(trailers);
        notifyDataSetChanged();
    }
}
