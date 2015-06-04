package com.damianvela.nytimeslistviewexample;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.damianvela.nytimeslistviewexample.model.NYTItem;

import java.util.List;

/**
 * Created by Android1 on 6/3/2015.
 */
public class CustomAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<NYTItem> nytItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomAdapter(Activity activity, List<NYTItem> nytItems) {
        this.activity = activity;
        this.nytItems = nytItems;
    }

    @Override
    public int getCount() {
        return nytItems.size();
    }

    @Override
    public Object getItem(int location) {
        return nytItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumb = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);
        TextView title = (TextView) convertView.findViewById(R.id.title);
        NYTItem nytElement = nytItems.get(position);
        thumb.setImageUrl(nytElement.getThumbnailUrl(), imageLoader);
        title.setText(nytElement.getTitle());

        return convertView;
    }

}
