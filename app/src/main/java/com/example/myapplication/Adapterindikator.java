package com.example.myapplication;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class Adapterindikator extends BaseAdapter {
    Activity activity;
    List<Dataindikator> items;
    private LayoutInflater inflater;

    public Adapterindikator(Activity activity, List<Dataindikator> items) {
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

        if (convertView==null) convertView = inflater.inflate(R.layout.indikator_list, null);

//        TextView idg = (TextView) convertView.findViewById(R.id.inIdg);
//        TextView usernameg = (TextView) convertView.findViewById(R.id.inUserg);
        TextView id = (TextView) convertView.findViewById(R.id.id);
        TextView nama = (TextView) convertView.findViewById(R.id.nama);
//        TextView alamat = (TextView) convertView.findViewById(R.id.alamat);
//        TextView alamat = (TextView) convertView.findViewById(R.id.alamat);
//        TextView alamat = (TextView) convertView.findViewById(R.id.alamat);

        Dataindikator data=items.get(position);

//        idg.setText(data.getIdg());
        nama.setText(data.getKetindi());
        id.setText(data.getId());


        return convertView;
    }
}

