package com.deliver.any.value.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.deliver.any.value.R;
import com.deliver.any.value.holder.ServicesViewHolder;
import com.deliver.any.value.model.ServiceData;

import java.util.Collections;
import java.util.List;

public class ServicesAdapter
        extends RecyclerView.Adapter<ServicesViewHolder> {

    List<ServiceData> list;
    Context context;

    public ServicesAdapter(List<ServiceData> list,
                           Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ServicesViewHolder onCreateViewHolder(ViewGroup parent,
                                                 int viewType) {

        Context context
                = parent.getContext();
        LayoutInflater inflater
                = LayoutInflater.from(context);

        // Inflate the layout

        View photoView
                = inflater
                .inflate(R.layout.service_card,
                        parent, false);

        return new ServicesViewHolder(photoView);
    }

    @Override
    public void
    onBindViewHolder(final ServicesViewHolder viewHolder,
                     final int position) {
        viewHolder.serviceName
                .setText(list.get(position).serviceName);
        viewHolder.price
                .setText(list.get(position).price);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(
            @NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


}