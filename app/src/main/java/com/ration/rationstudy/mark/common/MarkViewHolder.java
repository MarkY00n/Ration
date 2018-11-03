package com.ration.rationstudy.mark.common;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class MarkViewHolder<T> extends RecyclerView.ViewHolder {

    public MarkViewHolder(@NonNull View itemView) {
        super(itemView);
    }
    public abstract void bindTo(Context context, T item);
}
