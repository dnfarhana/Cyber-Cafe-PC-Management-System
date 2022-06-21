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

public class CustomAdapterSaleA extends BaseAdapter {

    private static ArrayList<usage> salelist;
    private LayoutInflater mInflater;
    private Context mContext;
    private int lastPosition;

    //hold elements in view
    static class ViewHolder{
        TextView date;
        TextView matrix;
        TextView pcname;
        TextView pcamount;
    }

    public CustomAdapterSaleA(Context context, ArrayList<usage> results){
        salelist = results;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return salelist.size();
    }

    @Override
    public Object getItem(int position) {
        return salelist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String pc_date = salelist.get(position).getDateset();
        String pc_matrix = salelist.get(position).getMatrix();
        String pc_name = salelist.get(position).getPcname();
        Double amount = salelist.get(position).getTotamount();

        final View result;
        ViewHolder holder;

        if(convertView==null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.listitemsalea,parent,false);
            holder  =new ViewHolder();
            holder.date= (TextView)convertView.findViewById(R.id.saledate);
            holder.matrix = (TextView)convertView.findViewById(R.id.salematrix);
            holder.pcname = (TextView)convertView.findViewById(R.id.salepcname);
            holder.pcamount = (TextView)convertView.findViewById(R.id.saleamount);

            result = convertView;
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
            result = convertView;
        }
//        Animation animation = AnimationUtils.loadAnimation(mContext,(position > lastPosition) ? R.anim.load_down_anim);
        lastPosition = position;
        String totAmount = String.valueOf(amount);
        holder.date.setText(pc_date);
        holder.matrix.setText(pc_matrix);
        holder.pcname.setText(pc_name);
        holder.pcamount.setText("RM"+totAmount);
        return convertView;
    }


}