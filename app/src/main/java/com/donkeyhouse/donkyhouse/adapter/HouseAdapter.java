package com.donkeyhouse.donkyhouse.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.donkeyhouse.donkyhouse.R;
import com.donkeyhouse.donkyhouse.bean.House;
import com.donkeyhouse.donkyhouse.house.DataVisual;

import java.util.List;

public class HouseAdapter extends RecyclerView.Adapter<HouseAdapter.ViewHolder>{
    private List<House> mhouse;
    private Context mContext;
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView house_num;
        TextView house_shuliang;
        TextView house_wendu;
        TextView house_shidu;
        TextView house_nongdu;
        ImageView house_warning;
        CardView house_card;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            house_num = itemView.findViewById(R.id.house_num);
            house_shuliang = itemView.findViewById(R.id.house_shuliang);
            house_wendu = itemView.findViewById(R.id.house_wendu);
            house_shidu = itemView.findViewById(R.id.house_shidu);
            house_nongdu = itemView.findViewById(R.id.house_nongdu);
            house_warning = itemView.findViewById(R.id.house_warning);
            house_card = itemView.findViewById(R.id.house_card);
        }
    }
    public HouseAdapter(List<House> mhouse){this.mhouse = mhouse;}
    @NonNull
    @Override
    public HouseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        if(mContext == null){
            mContext = viewGroup.getContext();
        }
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.house_item,viewGroup,false);
        final ViewHolder holder = new ViewHolder(view);
        //字体以及警告标志显示
        holder.house_warning.setVisibility(View.INVISIBLE);
        holder.house_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                switch (position){
                    case 0:
                        Intent intent = new Intent(mContext,DataVisual.class);
                        mContext.startActivity(intent);
                        break;
                }
            }
        });
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull HouseAdapter.ViewHolder viewHolder, int i) {
        House house = mhouse.get(i);
        viewHolder.house_num.setText(house.getHouse_num());
        viewHolder.house_shuliang.setText(house.getHouse_shuliang());
        viewHolder.house_wendu.setText(house.getHouse_wendu());
        viewHolder.house_shidu.setText(house.getHouse_shidu());
        viewHolder.house_nongdu.setText(house.getHouse_nongdu());
        viewHolder.house_warning.setVisibility(View.INVISIBLE);
        //湿度
        if (Float.parseFloat(house.getHouse_shidu())  <  35.0){
            viewHolder.house_shidu.setTextColor(Color.RED);
            viewHolder.house_warning.setVisibility(View.VISIBLE);
        }
        //温度
        if (Float.parseFloat(house.getHouse_wendu())  >  60.0){
            viewHolder.house_wendu.setTextColor(Color.RED);
            viewHolder.house_warning.setVisibility(View.VISIBLE);
        }
        //光照
        if (Float.parseFloat(house.getHouse_nongdu())  <  400.0){
            viewHolder.house_nongdu.setTextColor(Color.RED);
            viewHolder.house_warning.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mhouse.size();
    }
}
