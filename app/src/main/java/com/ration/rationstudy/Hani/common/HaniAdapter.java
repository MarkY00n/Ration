package com.ration.rationstudy.Hani.common;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public abstract class HaniAdapter<T, S extends HaniViewHolder> extends RecyclerView.Adapter<S> {

    public ArrayList<T> arrayList;
    private Context context;
    private int resId;

    public HaniAdapter(Context context, int resId){
        this.context = context;
        this.resId = resId;
        arrayList = new ArrayList<>();
    }

    public Context getContext() {
        return context;
    }

    public void setItemList(ArrayList<T> array) {
        arrayList = array;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public S onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(resId, viewGroup, false);
        return viewHolder(view);
    }

    public abstract S viewHolder(View view);

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public ArrayList<T> getArrayList() {
        return arrayList;
    }

}
