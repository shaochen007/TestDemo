package com.example.chenyong.testdemo.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chenyong.testdemo.R;

import java.util.List;

public class RecycleViewAdapter<E> extends RecyclerView.Adapter<RecycleViewAdapter.ViewHodler>  {

    private Context context;
    private List<E> dataList;
    private OnItemClickListener onItemClickListener;


    public RecycleViewAdapter(Context context,List<E> dataList,OnItemClickListener onItemClickListener){
        this.context = context;
        this.dataList = dataList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public RecycleViewAdapter.ViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_text_one_layout,parent,false);
        ViewHodler viewHodler = new ViewHodler(view);
        return viewHodler;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecycleViewAdapter.ViewHodler holder, final int position) {
        holder.textView.setText(dataList.get(position).toString());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener!=null){
//                    int pos = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(v,position);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }



    class ViewHodler extends RecyclerView.ViewHolder{
        TextView textView;
        public ViewHodler(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_content);
        }
    }

}
