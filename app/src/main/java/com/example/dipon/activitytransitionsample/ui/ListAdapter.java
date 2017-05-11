package com.example.dipon.activitytransitionsample.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dipon.activitytransitionsample.R;
import com.example.dipon.activitytransitionsample.data.SampleData;

import java.util.ArrayList;

/**
 * Created by Dipon on 5/9/2017.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListHolder>{

    private ArrayList<SampleData> sampleDataList;
    private Context context;
    private ClickCallback clickCallback;


    public ListAdapter(ArrayList<SampleData> sampleDataList, Context context) {
        this.sampleDataList = sampleDataList;
        this.context = context;

    }

    public ArrayList<SampleData> getSampleDataList() {
        return sampleDataList;
    }

    public void setSampleDataList(ArrayList<SampleData> sampleDataList) {
        this.sampleDataList = sampleDataList;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ClickCallback getClickCallback() {
        return clickCallback;
    }

    public void setClickCallback(ClickCallback clickCallback) {
        this.clickCallback = clickCallback;
    }

    @Override
    public ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate (R.layout.item_list, null);
        ListAdapter.ListHolder listHolder = new ListAdapter.ListHolder(view);
        return listHolder;
    }

    @Override
    public void onBindViewHolder(ListHolder holder, int position) {
        SampleData sampleData = sampleDataList.get(position);
        holder.itemName.setText(sampleData.getItemName());
        holder.icon.setColorFilter(sampleData.getColor());
    }

    @Override
    public int getItemCount() {
        return sampleDataList.size();
    }

    public class ListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private View container;
        private ImageView icon;
        private TextView itemName;


        public ListHolder(View itemView) {
            super(itemView);
            container = (View) itemView.findViewById(R.id.sample_container);
            container.setOnClickListener(this);
            icon =(ImageView) itemView.findViewById(R.id.sample_icon);
            itemName = (TextView) itemView.findViewById(R.id.sample_name);
        }

        @Override
        public void onClick(View v) {
            clickCallback.onClickCallback(v,getAdapterPosition());
        }
    }
}
