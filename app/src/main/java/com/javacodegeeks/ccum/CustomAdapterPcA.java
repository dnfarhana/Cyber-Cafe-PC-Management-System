package com.javacodegeeks.ccum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapterPcA extends BaseAdapter {

    private static ArrayList<usage> pclist;
    private LayoutInflater mInflater;
    private Context mContext;
    private int lastPosition;

    //hold elements in view
    static class ViewHolder{
        TextView date;
        TextView matrix;
        TextView pcname;
        TextView tottime;
    }

    public CustomAdapterPcA(Context context, ArrayList<usage> results){
        pclist = results;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return pclist.size();
    }

    @Override
    public Object getItem(int position) {
        return pclist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String pc_date = pclist.get(position).getDateset();
        String pc_matrix = pclist.get(position).getMatrix();
        String pc_name = pclist.get(position).getPcname();
        Double totaltime = pclist.get(position).getTottime();

        final View result;
        ViewHolder holder;

        if(convertView==null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.listitempca,parent,false);
            holder  =new ViewHolder();
            holder.date= (TextView)convertView.findViewById(R.id.pcaDate);
            holder.matrix = (TextView)convertView.findViewById(R.id.pcaMatrix);
            holder.pcname = (TextView)convertView.findViewById(R.id.pcaPcname);
            holder.tottime = (TextView)convertView.findViewById(R.id.pcaTime);

            result = convertView;
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
            result = convertView;
        }
//        Animation animation = AnimationUtils.loadAnimation(mContext,(position > lastPosition) ? R.anim.load_down_anim);
        lastPosition = position;
        String totTime = String.valueOf(totaltime);
        holder.date.setText(pc_date);
        holder.matrix.setText(pc_matrix);
        holder.pcname.setText(pc_name);
        holder.tottime.setText(totTime+" seconds");
        return convertView;
    }
}