package com.example.hp.yoganews;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NewsListCustomAdapter extends BaseAdapter
{
    List<News> items;
    Context myContext;

    @Override
    public int getCount() {
        return items.size();
    }

    public NewsListCustomAdapter(List<News> items, Context context) {
        this.items = items;
        this.myContext = context;
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<News>data) {
        items.addAll(data);
        this.notifyDataSetChanged();
    }

    @Override
    public News getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View x = convertView;
        if (x == null) {
            x = View.inflate(myContext, R.layout.news_item, null);
        }
        TextView section = (TextView) x.findViewById(R.id.news_sectionName);
        section.setText(items.get(position).getSectionName());

        TextView title = (TextView) x.findViewById(R.id.news_header);
        title.setText(items.get(position).getHeader());

        TextView date = (TextView) x.findViewById(R.id.news_date);
        date.setText(formatDate(items.get(position).getDate()));

        ImageView thumbnail = (ImageView)x.findViewById(R.id.news_img);
        Glide.with(myContext).load(items.get(position).getThumbnail()).into(thumbnail);
        return x;
    }

    /**
     * Return the formatted date string
     */
    private String formatDate(String date) {
       String[] Dates = date.split("T");
        String finalDate="";
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd");
        try {
           Date my_date = dateFormat.parse(Dates[0]);
            SimpleDateFormat timeFormat = new SimpleDateFormat("dd-MM-yyyy");
            finalDate = timeFormat.format(my_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return finalDate;

    }




}
