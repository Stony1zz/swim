package com.donkeyhouse.donkyhouse.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.donkeyhouse.donkyhouse.bean.SwimmingPool;
import com.donkeyhouse.donkyhouse.R;
import com.donkeyhouse.donkyhouse.production.LittleLv;

import java.util.List;

import static android.content.ContentValues.TAG;

public class ProductionAdapter extends RecyclerView.Adapter<ProductionAdapter.ViewHolder> {
    private List<SwimmingPool> mproduction;
    private Context mContext;
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView Name;
        TextView renshu;
        TextView weizhi;
        TextView jieshao;
        CardView zhonglei_card;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            zhonglei_card=itemView.findViewById(R.id.zhonglei_card1);
            img = itemView.findViewById(R.id.imgid);
            renshu = itemView.findViewById(R.id.renshu);
            Name = itemView.findViewById(R.id.Name);
            weizhi = itemView.findViewById(R.id.number);
            jieshao=itemView.findViewById(R.id.jieshao);
        }
    }
    public ProductionAdapter(List<SwimmingPool> mproduction){this.mproduction = mproduction;}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, int i) {
        if(mContext == null){
            mContext = viewGroup.getContext();
        }
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.species,viewGroup,false);
        Log.e(TAG, "onCreateViewHolder: "+"fuck" );
        final ViewHolder holder = new ViewHolder(view);
        holder.zhonglei_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                switch (position){
                    case 0:
                        Intent intent = new Intent(mContext,LittleLv.class);
                        mContext.startActivity(intent);
                        break;
                    case 1:
                       // Intent intent1 = new Intent(mContext,MidLv.class);
                        //mContext.startActivity(intent1);
                        break;
                    case 2:
                       // Intent intent2 = new Intent(mContext,BigLv.class);
                       // mContext.startActivity(intent2);
                        break;
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        SwimmingPool production = mproduction.get(i);
        viewHolder.img.setImageResource(R.mipmap.swim);
        viewHolder.renshu.setText("12");
        viewHolder.Name.setText(production.getName());
        viewHolder.weizhi.setText(production.getAddress());
        viewHolder.jieshao.setText(production.getDetail());
    }

    @Override
    public int getItemCount() {
        return mproduction.size();
    }
}
