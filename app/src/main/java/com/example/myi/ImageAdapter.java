package com.example.myi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyViewHolder> {
   ArrayList<ImageModel> list;
   Context ct;
   public ImageAdapter(ImageActivity imageActivity,ArrayList<ImageModel> mylist){
       this.ct=imageActivity;
       this.list=mylist;
   }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(ct).inflate(R.layout.image_row,viewGroup,false);
        MyViewHolder holder=new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        Picasso.with(ct).load(list.get(i).getImage_url()).into(myViewHolder.im);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
         ImageView im;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            im=itemView.findViewById(R.id.imagedp);
        }
    }
}
