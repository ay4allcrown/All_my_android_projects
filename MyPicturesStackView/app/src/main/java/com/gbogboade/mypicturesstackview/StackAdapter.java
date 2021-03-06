package com.gbogboade.mypicturesstackview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by DELL on 12/15/2017.
 */
public class StackAdapter extends BaseAdapter {

    ArrayList arrayList;
    LayoutInflater inflater;
    ViewHolder holder = null;

    public StackAdapter(Context context, ArrayList arrayList){
        this.arrayList = arrayList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public StackItems getItem(int pos) {
        return arrayList.get(pos);
    }

    @Override
    public long getItemId(int pos){
        return pos;
    }

    @Override
    public View getView(int pos, View view, ViewGroup parent){
        if (view == null){
            view = inflater.inflate(R.layout.item, parent, false);
            holder = new ViewHolder();
            holder.image = (ImageView) view.findViewById(R.id.image);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.image.setBackgroundResource(arrayList.get(pos).getImage());

        return view;
    }

    public class ViewHolder{
        ImageView image;
    }
}





















