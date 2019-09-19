package com.donkeyhouse.donkyhouse.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.donkeyhouse.donkyhouse.R;
import com.donkeyhouse.donkyhouse.bean.Natatorium;
import com.donkeyhouse.donkyhouse.production.SelfDialog;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class LittleAdapter extends RecyclerView.Adapter<LittleAdapter.ViewHolder> {
    private List<Natatorium> mlittle;
    private Context mContext;
    private SelfDialog selfDialog;
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView picihao;
        TextView pound;
        TextView location;
        TextView jinlan;
        TextView shichang;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.swim_card);
            picihao = itemView.findViewById(R.id.swim_poolid);
            pound = itemView.findViewById(R.id.swim_deep);
            location = itemView.findViewById(R.id.swim_number);
            jinlan = itemView.findViewById(R.id.swim_tep);
            shichang = itemView.findViewById(R.id.swim_time);

        }
    }
    public LittleAdapter(List<Natatorium> mlittle){this.mlittle=mlittle;}


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(mContext == null){
            mContext = viewGroup.getContext();
        }
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.lv_item,viewGroup,false);
       final ViewHolder holder=new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                switch (position){
                    case 0:
                        selfDialog = new SelfDialog(mContext);
                        selfDialog.setYesOnclickListener("修改",new SelfDialog.onYesOnclickListener(){
                            @Override
                            public void onYesClick() {
                                Toast.makeText(mContext,"修改成功",Toast.LENGTH_SHORT).show();
                                selfDialog.dismiss();
                            }
                        });
                        selfDialog.setNoOnclickListener("取消", new SelfDialog.onNoOnclickListener() {
                            @Override
                            public void onNoClick() {
                                Toast.makeText(mContext,"取消修改",Toast.LENGTH_SHORT).show();
                                selfDialog.dismiss();
                            }
                        });
                        selfDialog.show();
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Natatorium little = mlittle.get(i);
        viewHolder.picihao.setText(little.getSwimmingPoolId());
        viewHolder.pound.setText(String.valueOf(little.getWaterLevel()));
        Log.e(TAG, "hhhhhhhhhhh: "+ viewHolder.pound.getText().toString().trim());
        //viewHolder.location.setText(little.getLocation());
        viewHolder.jinlan.setText(little.getTime());
        viewHolder.shichang.setText("100");
    }

    @Override
    public int getItemCount() {

        return mlittle.size();
    }
}
