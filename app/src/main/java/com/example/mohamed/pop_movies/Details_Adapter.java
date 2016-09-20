package com.example.mohamed.pop_movies;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.mohamed.pop_movies.data.Review;
import com.example.mohamed.pop_movies.data.Trailer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;


public class Details_Adapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> list_header_titles;
    private HashMap<String, List<Object>> list_child;

    public Details_Adapter(Context context, List<Object> trailers, List<Object> reviews) {
        this.context = context;
        list_header_titles = new ArrayList<>();
        list_header_titles.add(context.getString(R.string.trailers));
        list_header_titles.add(context.getString(R.string.reviews));

        list_child = new HashMap<>();
        if (trailers != null)
            list_child.put(list_header_titles.get(0), trailers);
        else
            list_child.put(list_header_titles.get(0), new ArrayList<>());
        if (reviews != null)
            list_child.put(list_header_titles.get(1), reviews);
        else
            list_child.put(list_header_titles.get(1), new ArrayList<>());
    }

    public void removeAll() {
        list_child = new HashMap<>();
        list_child.put(list_header_titles.get(0), new ArrayList<>());
        list_child.put(list_header_titles.get(1), new ArrayList<>());
        notifyDataSetChanged();
    }

    public void addTrailer(Trailer trailer) {
        list_child.get(list_header_titles.get(0)).add(trailer);
        notifyDataSetChanged();
    }

    public void addTrailers(Collection<Trailer> trailers) {
        list_child.get(list_header_titles.get(0)).addAll(trailers);
        notifyDataSetChanged();
    }

    public void addReview(Review review) {
        list_child.get(list_header_titles.get(1)).add(review);
        notifyDataSetChanged();
    }

    public void addReviews(Collection<Review> reviews) {
        list_child.get(list_header_titles.get(1)).addAll(reviews);
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return this.list_header_titles.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.list_child.get(this.list_header_titles.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.list_header_titles.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.list_child.get(this.list_header_titles.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean b, View view, ViewGroup viewGroup) {
        String headerTitle = (String) getGroup(groupPosition);
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_group_header, viewGroup, false);
        }

        TextView List_group_header = (TextView) view
                .findViewById(R.id.List_group_header);
        List_group_header.setTypeface(null, Typeface.BOLD);
        List_group_header.setText(headerTitle);

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean b, View view, ViewGroup viewGroup) {
        String string = list_header_titles.get(groupPosition);

        if (string.equals(context.getString(R.string.trailers))) {

            view = LayoutInflater.from(context).inflate(R.layout.trailer_layout, viewGroup, false);
            TextView trailer_Name = (TextView) view.findViewById(R.id.trailer_Name);
            Trailer trailer = (Trailer) getChild(groupPosition, childPosition);
            trailer_Name.setText(trailer.getName());
            view.setTag(trailer);
        } else if (string.equals(context.getString(R.string.reviews))) {
            view = LayoutInflater.from(context).inflate(R.layout.reviews_layout, viewGroup, false);
            Review review = (Review) getChild(groupPosition, childPosition);
            TextView Author_Name = (TextView) view.findViewById(R.id.Author_Name);
            Author_Name.setText(review.getAuthor());
            TextView Reviews_content_text = (TextView) view.findViewById(R.id.Reviews_content_text);
            Reviews_content_text.setText(review.getContent());

        }
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
