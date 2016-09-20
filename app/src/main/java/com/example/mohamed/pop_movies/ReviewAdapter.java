package com.example.mohamed.pop_movies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mohamed.pop_movies.data.Review;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class ReviewAdapter extends BaseAdapter {
    private List<Review> mData;

    public ReviewAdapter(List<Review> reviews) {
        if (reviews == null)
            mData = new ArrayList<>();
        else
            mData = reviews;
    }

    public ReviewAdapter() {
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
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.reviews_layout, viewGroup, false);
        TextView Author_Name = (TextView) view.findViewById(R.id.Author_Name);
        Author_Name.setText(mData.get(i).getAuthor());
        TextView Reviews_content_text = (TextView) view.findViewById(R.id.Reviews_content_text);
        Reviews_content_text.setText(mData.get(i).getContent());

        return view;
    }

   public  void addAll(Collection<Review> reviews){
       mData.addAll(reviews);
       notifyDataSetChanged();

   }
}
