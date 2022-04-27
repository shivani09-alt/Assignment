package com.example.assignment;

import android.content.Context;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter {
     Context context;
     ArrayList<Model> arrayList;
    Adapter(Context context, ArrayList<Model> arrayList)
    {
        this.context=context;
        this.arrayList=arrayList;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.recyclerview_layout,parent,false);

        return new ViewHolderClass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        try {
            ViewHolderClass viewHolderClass = (ViewHolderClass) holder;




            viewHolderClass.imageView.setImageBitmap(arrayList.get(position).getLogo());
            viewHolderClass.textView.setText(arrayList.get(position).getStationName());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }



    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    private static class ViewHolderClass extends RecyclerView.ViewHolder{
         ImageView imageView;
         TextView textView;
        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);
            textView=itemView.findViewById(R.id.textView);

        }
    }
}
