package com.deliver.any.value.holder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.deliver.any.value.R;

public class ServicesViewHolder
        extends RecyclerView.ViewHolder {
    public TextView serviceName;
    public TextView price;
    View view;

    public ServicesViewHolder(View itemView) {
        super(itemView);
        serviceName
                = (TextView) itemView
                .findViewById(R.id.serviceName);
        price
                = (TextView) itemView
                .findViewById(R.id.price);
        view = itemView;
    }
}