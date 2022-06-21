package com.javacodegeeks.ccum;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;



import java.util.ArrayList;

public class CustomAdapterC extends BaseAdapter {

    private static ArrayList<usage> searchArrayList;
    private LayoutInflater mInflater;
    private Context mContext;
    private int lastPosition;


    //hold elements in view
    static class ViewHolder{
        TextView pcdate;
        TextView pcname;
        TextView amount;//tukar date
        TextView time;
    }

    public CustomAdapterC(Context context, ArrayList<usage> results){
        searchArrayList = results;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return searchArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return searchArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String pc_date = searchArrayList.get(position).getDateset();
        String pc_name = searchArrayList.get(position).getPcname();
        Double pc_amount = searchArrayList.get(position).getTotamount();
        Double pc_time = searchArrayList.get(position).getTottime();

        final View result;
        ViewHolder holder;

        if(convertView==null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.listitemc,parent,false);
            holder  =new ViewHolder();
            holder.pcdate= (TextView)convertView.findViewById(R.id.tv_list_item_pcdate);
            holder.pcname = (TextView)convertView.findViewById(R.id.tv_list_item_pcname);
            holder.amount = (TextView)convertView.findViewById(R.id.tv_list_item_amount);
            holder.time = (TextView)convertView.findViewById(R.id.tv_list_item_time);

            result = convertView;
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
            result = convertView;
        }
//        Animation animation = AnimationUtils.loadAnimation(mContext,(position > lastPosition) ? R.anim.load_down_anim);
        lastPosition = position;
        String totAmount = String.valueOf(pc_amount);
        String totTime = String.valueOf(pc_time);
        holder.pcdate.setText(pc_date);
        holder.pcname.setText(pc_name);
        holder.amount.setText("RM"+totAmount);
        holder.time.setText(totTime+" seconds");
        return convertView;
    }
}