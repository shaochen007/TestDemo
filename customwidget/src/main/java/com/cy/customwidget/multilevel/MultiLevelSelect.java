package com.cy.customwidget.multilevel;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.cy.customwidget.R;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * 仿淘宝多级联动选择
 */
public class MultiLevelSelect extends LinearLayout {

    private Context mContext;
    private OptionBean mData;
    private OptionBean mCurSelectData;
    /** 当前处于选择状态的主干数据位置*/
    private int mCurSelectPosOfTrunk = -1;
    private List<OptionBean> mTrunkDataList = new ArrayList<>();
    private List<OptionBean> mCurOptions = new ArrayList<>();

    private RecyclerView mRvTrunk;
    private RecyclerView mRvOptions;
    private TextView mTvOptionTips;

    private BaseQuickAdapter<OptionBean, BaseViewHolder> mTrunkAdapter;
    private BaseQuickAdapter<OptionBean, BaseViewHolder> mOptionsAdapter;

    /** 主干列表当前激活——需用不同颜色表示*/
    private TextView mTvTrunkCurActive;

    /** 选择完成监听*/
    private OnCompleteListener mOnCompleteListener;

    public MultiLevelSelect(Context context) {
        super(context);
        init(context, null, 0);
    }

    public MultiLevelSelect(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public MultiLevelSelect(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.layout_multi_level_select, this);
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.MultiLevelSelect, defStyle, 0);
        a.recycle();

        mRvTrunk = findViewById(R.id.rv_trunk);
        mRvOptions = findViewById(R.id.rv_options);
        mTvOptionTips = findViewById(R.id.tv_tips_options);

        mRvTrunk.setLayoutManager(new LinearLayoutManager(context));
        mRvOptions.setLayoutManager(new LinearLayoutManager(context));

        mTrunkAdapter = new BaseQuickAdapter<OptionBean, BaseViewHolder>(
                R.layout.layout_multi_level_select_item, mTrunkDataList
        ) {
            @Override
            protected void convert(@NonNull final BaseViewHolder helper, final OptionBean item) {
                helper.setText(R.id.tv_item, item == null ? "请选择" + mCurSelectData.getRemark() : item.getName());

                TextView textView = helper.getView(R.id.tv_item);
                if (item == mTrunkDataList.get(mCurSelectPosOfTrunk)) {
                    textView.setTextColor(ContextCompat.getColor(mContext, android.R.color.holo_orange_dark));
                    mTvTrunkCurActive = textView;
                } else {
                    textView.setTextColor(ContextCompat.getColor(mContext, android.R.color.black));
                }
            }
        };

        mOptionsAdapter = new BaseQuickAdapter<OptionBean, BaseViewHolder>(
                R.layout.layout_multi_level_select_item, mCurOptions
        ) {
            @Override
            protected void convert(@NonNull final BaseViewHolder helper, final OptionBean item) {
                Log.d("mOptionsAdapter.convert", item.toString());
                helper.setText(R.id.tv_item, item.getName());
            }
        };

        mRvTrunk.setAdapter(mTrunkAdapter);
        mRvOptions.setAdapter(mOptionsAdapter);

        mRvTrunk.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
                // 点击当前正激活的，直接返回
                if (view == mTvTrunkCurActive) {
                    return;
                }

                final OptionBean selectData = mTrunkDataList.get(position);
                // 将父节点设置为当前所选数据
                mCurSelectData = selectData.getParent();
                resetOptionList(mCurSelectData);
                mOptionsAdapter.notifyDataSetChanged();

                final TextView textView = (TextView) view;
                textView.setTextColor(ContextCompat.getColor(mContext, android.R.color.holo_orange_light));
                mTvTrunkCurActive.setTextColor(ContextCompat.getColor(mContext, android.R.color.black));
                mTvTrunkCurActive = textView;

                mTvOptionTips.setText("请选择" + selectData.getRemark());
                mCurSelectPosOfTrunk = position;
            }

            @Override
            public void onSimpleItemChildClick(final BaseQuickAdapter adapter, final View view, final int position) {

            }
        });

        mRvOptions.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
                // 点击当前已选中的，不做任何处理
                final OptionBean optionBean = mCurOptions.get(position);
                if (optionBean == mTrunkDataList.get(mCurSelectPosOfTrunk)) {
                    return;
                }

                mTrunkDataList.remove(mCurSelectPosOfTrunk);
                mTrunkDataList.add(mCurSelectPosOfTrunk, optionBean);
                // 设置父节点，用户后面重选
                optionBean.setParent(mCurSelectData);

                // 循环删除主干当前选择项后面的数据
                while (mTrunkDataList.size() > mCurSelectPosOfTrunk + 1) {
                    mTrunkDataList.remove(mCurSelectPosOfTrunk + 1);
                }

                // 当所选的子列表为空时，表示已经选到当前分支最末级，直接更新主干列表后返回
                if (optionBean.getList() == null || optionBean.getList().isEmpty()) {
                    if (mOnCompleteListener != null) {
                        mOnCompleteListener.complete(mTrunkDataList);
                        return;
                    }
                    mTrunkAdapter.notifyDataSetChanged();
                    return;
                }
                // 重置当前选择数据必须在上面的 if 语句后面，否则会出错
                mCurSelectData = optionBean;

                // 添加末尾置空项
                mTrunkDataList.add(null);

                mCurSelectPosOfTrunk++;
                resetOptionList(mCurSelectData);

                mTrunkAdapter.notifyDataSetChanged();
                mOptionsAdapter.notifyDataSetChanged();
                mTvOptionTips.setText("请选择" + mCurSelectData.getRemark());
            }

            @Override
            public void onSimpleItemChildClick(final BaseQuickAdapter adapter, final View view, final int position) {
            }
        });
    }

    private void resetOptionList(final OptionBean curSelectData) {
        mCurOptions.clear();
        mCurOptions.addAll(curSelectData.getList());
    }

    public OptionBean getData() {
        return mData;
    }

    public void setData(final OptionBean data) {
        mData = data;
        mCurSelectData = data;
        mTrunkDataList.add(null);
        mCurSelectPosOfTrunk = 0;

        resetOptionList(data);

        mTrunkAdapter.notifyDataSetChanged();
        mOptionsAdapter.notifyDataSetChanged();
    }

    public void setOnCompleteListener(OnCompleteListener listener) {
        this.mOnCompleteListener = listener;
    }

    interface OnCompleteListener {
        public void complete(List<OptionBean> list);
    }
}
