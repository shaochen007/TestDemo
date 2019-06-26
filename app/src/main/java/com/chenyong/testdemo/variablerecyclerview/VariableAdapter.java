package com.chenyong.testdemo.variablerecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chenyong.testdemo.R;

import java.util.List;

public class VariableAdapter extends RecyclerView.Adapter<VariableAdapter.ViewHolder> {

    private Context mContext;

    //数据源
    private List<ScoreBean> mList;

    public VariableAdapter(Context context, List<ScoreBean> list) {
        mContext = context;
        mList = list;
    }

    //返回item个数
    @Override
    public int getItemCount() {
        return mList.size() ;
    }

    //创建ViewHolder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_variable, parent, false));
    }

    //填充视图
    @Override
    public void onBindViewHolder(@NonNull final VariableAdapter.ViewHolder holder, final int position) {
        if (position == 0) {
            holder.mLlDataBox.setVisibility(View.GONE);
            holder.mTvTitle.setVisibility(View.VISIBLE);
        } else {
            holder.mLlDataBox.setVisibility(View.VISIBLE);
            holder.mTvTitle.setVisibility(View.GONE);

            holder.mTvScore.setText(mList.get(position).score + "");
            holder.mTvGroup.setText(mContext.getString(R.string.text_group, mList.get(position).group));
            holder.mTvTimes.setText(mContext.getString(R.string.text_times, mList.get(position).times));
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout mLlDataBox;
        public TextView mTvTimes;
        public TextView mTvGroup;
        public TextView mTvScore;
        public TextView mTvTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            mLlDataBox = itemView.findViewById(R.id.ll_data_box);
            mTvTimes = itemView.findViewById(R.id.tv_times);
            mTvGroup = itemView.findViewById(R.id.tv_group);
            mTvScore = itemView.findViewById(R.id.tv_score);

            mTvTitle = itemView.findViewById(R.id.tv_title);
        }
    }
}

