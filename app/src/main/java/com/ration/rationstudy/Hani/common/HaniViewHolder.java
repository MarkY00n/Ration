package com.ration.rationstudy.Hani.common;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class HaniViewHolder<T> extends RecyclerView.ViewHolder {

    public HaniViewHolder(@NonNull View itemView) {
        super(itemView);
    }
    public abstract void bindTo(Context context, T item);
}
