package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class Adapterforum extends BaseAdapter {
    Activity activity;
    List<Dataforum> items;
    private LayoutInflater inflater;

    public Adapterforum(Activity activity, List<Dataforum> items) {
        this.activity=activity;
        this.items=items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if (inflater==null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView==null) convertView = inflater.inflate(R.layout.listforum, null);

//        TextView idg = (TextView) convertView.findViewById(R.id.inIdg);
//        TextView usernameg = (TextView) convertView.findViewById(R.id.inUserg);
        TextView nama = (TextView) convertView.findViewById(R.id.nama);
        TextView nama2 = (TextView) convertView.findViewById(R.id.nama2);
        TextView nama3 = (TextView) convertView.findViewById(R.id.nama3);
        TextView nama4 = (TextView) convertView.findViewById(R.id.nama4);
//        TextView indikator2 = (TextView) convertView.findViewById(R.id.nama3);
//        TextView indikator3 = (TextView) convertView.findViewById(R.id.nama6);
//        TextView indikator4 = (TextView) convertView.findViewById(R.id.nama4);

        Dataforum data=items.get(position);

//        idg.setText(data.getIdg());
        nama.setText(data.getNamaf());
        nama2.setText(data.getJamf());
        nama3.setText(data.getChatf());
        nama4.setText(data.getTglf());

//        indikator1.setText(data.getIndikator1a());
//        indikator2.setText(data.getIndikator2a());
//        indikator3.setText(data.getIndikator3a());
//        indikator4.setText(data.getIndikator4a());


        return convertView;
    }
}


