package com.ttp.ziaratarbaeen.classes;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ttp.ziaratarbaeen.R;

import java.util.ArrayList;

public class VirtualPilgrimageAdapter extends RecyclerView.Adapter<VirtualPilgrimageAdapter.MyViewHolder>{


    Context context;
    ArrayList<VirtualPilgrimageModel> arrayList;

    public VirtualPilgrimageAdapter(Context context, ArrayList<VirtualPilgrimageModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;

        this.arrayList=new ArrayList<>();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());

        return new MyViewHolder(inflater.inflate(R.layout.rv_virtual_pilgrimage_item,parent));
    }



    @Override
    public void onBindViewHolder(VirtualPilgrimageAdapter.MyViewHolder holder, int position) {

        holder.fill(arrayList.get(position).getName(),arrayList.get(position).getPhotoId());
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView ivPhoto;
        TextView tvName;

        public MyViewHolder(View itemView) {
            super(itemView);

            ivPhoto=itemView.findViewById(R.id.iv_photo);
            tvName=itemView.findViewById(R.id.tv_name);

        }

        public void fill(String name,int photoId){

            ivPhoto.setBackgroundColor(Color.RED);
            tvName.setText("aaa");

        }
    }
}
