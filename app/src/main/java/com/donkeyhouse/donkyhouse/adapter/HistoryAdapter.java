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

import com.donkeyhouse.donkyhouse.R;
import com.donkeyhouse.donkyhouse.bean.RHistory;
import com.donkeyhouse.donkyhouse.bean.UserHistory;
import com.donkeyhouse.donkyhouse.production.SelfDialogHistory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder>{
    private List<UserHistory> mrhitory;
    private Context mContext;
    private SelfDialogHistory selfDialogHistory;
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView picihao;
        TextView donkeyid;
        TextView rfid;
        TextView size;
        TextView time;
        TextView pid;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            picihao = itemView.findViewById(R.id.history_picihao);
            donkeyid = itemView.findViewById(R.id.history_donkeyid);
            rfid = itemView.findViewById(R.id.history_rfid);
            size = itemView.findViewById(R.id.history_size);
            time = itemView.findViewById(R.id.history_time);
            pid = itemView.findViewById(R.id.history_pid);
            cardView = itemView.findViewById(R.id.history_card);
        }
    }
    public HistoryAdapter(List<UserHistory> mrhistory) {
        this.mrhitory = mrhistory;
    }

    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(mContext == null){
            mContext = viewGroup.getContext();
        }
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.history_item,viewGroup,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                switch (position){
                    case 0:
                        selfDialogHistory = new SelfDialogHistory(mContext);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                OkHttpClient client =new OkHttpClient();
                                Request.Builder builder = new Request.Builder();
                                Request request = builder.get().url("http://192.168.0.101:8080/rfid/listUserHis").build();
                                try {
                                    Response response = client.newCall(request).execute();
                                    Log.e("22222222222222222222", "run: ");
                                    String responseData = response.body().string();
                                    Log.e("11111111111111111111", "run: "+responseData);
                                    JSONObject obj = new JSONObject(responseData);
                                    JSONArray data1 = obj.getJSONArray("data");
                                    JSONObject row = null;
                                    row = data1.getJSONObject(0);
                                    Log.e("33333333333333333333", "run: "+row );
                                    selfDialogHistory.setPicihao(row.getString("donkeyId"));
                                    selfDialogHistory.setKahao(row.getString("RFIDInfo"));
                                    selfDialogHistory.setShijian(row.getString("pId"));
                                    selfDialogHistory.setWeizhi(row.getString("homeId"));
                                    selfDialogHistory.setPound(row.getString("size"));
                                    selfDialogHistory.setXingbie(row.getString("gender"));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                        selfDialogHistory.show();
                }
            }
        });
        return holder;
    }
    public void showResponse(){

    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        UserHistory rHistory = mrhitory.get(i);
        viewHolder.picihao.setText(rHistory.getTime());
        viewHolder.donkeyid.setText(String.valueOf(rHistory.getUserId()));
        viewHolder.rfid.setText(rHistory.getSensorId());
        viewHolder.size.setText(rHistory.getWaterPressure());
        viewHolder.time.setText(rHistory.getRFIDInfo());
        viewHolder.pid.setText(String.valueOf(rHistory.getUserDataId()));
    }

    @Override
    public int getItemCount() {
        return mrhitory.size();
    }
}
