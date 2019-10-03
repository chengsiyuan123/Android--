package com.example.ywang.diseaseidentification.adapter;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ywang.diseaseidentification.R;
import com.example.ywang.diseaseidentification.bean.baseData.DiseaseData;
import com.example.ywang.diseaseidentification.utils.WebUtil;

import java.util.List;

public class DiseasesAdapter extends RecyclerView.Adapter<DiseasesAdapter.ViewHolder> {

    private Context mContext;
    private List<DiseaseData> mDiseaseList;

    static class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        ImageView fruitView;
        TextView fruitName;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            fruitView= (ImageView) itemView.findViewById(R.id.fruit_image);
            fruitName = (TextView) itemView.findViewById(R.id.fruit_name);
        }
    }

    public DiseasesAdapter(List<DiseaseData> fruitList){
        mDiseaseList = fruitList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.disease_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                DiseaseData data = mDiseaseList.get(position);
                WebUtil.openWeb(mContext,data.getContent(),data.getLink());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DiseaseData data = mDiseaseList.get(position);
        holder.fruitName.setText(data.getContent());
        //使用Glide来加载图片
        Glide.with(mContext).load(data.getImageUrl()).into(holder.fruitView);
    }

    @Override
    public int getItemCount() {
        return mDiseaseList.size();
    }

}

