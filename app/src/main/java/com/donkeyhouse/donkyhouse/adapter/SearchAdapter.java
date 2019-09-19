package com.donkeyhouse.donkyhouse.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.donkeyhouse.donkyhouse.R;
import com.donkeyhouse.donkyhouse.bean.SearchItem;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private List<SearchItem> msearch;
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView pici;
        TextView zhongliang;
        TextView weizhi;
        TextView shijian;
        TextView leixing;
        TextView shichang;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pici = itemView.findViewById(R.id.swim_poolid);
            zhongliang = itemView.findViewById(R.id.swim_deep);
            weizhi = itemView.findViewById(R.id.swim_number);
            leixing = itemView.findViewById(R.id.swim_tep);
            //  leixing = itemView.findViewById(R.id.lv_leixing);
            shichang = itemView.findViewById(R.id.swim_time);

        }
    }

    public SearchAdapter(List<SearchItem> msearch){this.msearch = msearch;}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from((viewGroup.getContext()))
                .inflate(R.layout.lv_item,viewGroup,false);
        SearchAdapter.ViewHolder holder = new SearchAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        SearchItem searchItem = msearch.get(i);
        viewHolder.pici.setText(searchItem.getPici());
        viewHolder.zhongliang.setText(searchItem.getZhongliang());
        viewHolder.weizhi.setText(searchItem.getWeizhi());
        viewHolder.shijian.setText(searchItem.getShijian());
        viewHolder.leixing.setText(searchItem.getLeixing());
        viewHolder.shichang.setText(searchItem.getShichang());
    }

    @Override
    public int getItemCount() {
        return msearch.size();
    }


}
