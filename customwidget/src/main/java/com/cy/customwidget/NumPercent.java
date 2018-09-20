package com.cy.customwidget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.ViewUtils;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 根据比例现实类似进度条
 *
 * @author psl
 * @version 1.0
 */
public class NumPercent extends View {

    private TextPaint mTextPaint;

    /** 进度条颜色*/
    private int mProgressBarColor = Color.parseColor("#BBF1F3");

    /** 字体大小*/
    private float mTextSize = 42;
    /** 字体颜色*/
    private int mTextColor = Color.parseColor("#000000");

    /** 标题字体大小——不设置时默认使用mTextSize*/
    private float mTitleTextSize;
    /** 标题字体颜色——不设置时默认使用mTextColor*/
    private int mTitleTextColor;

    /** 进度值字体大小——不设置时默认使用mTextSize*/
    private float mProgressValueTextSize;
    /** 进度值字体颜色——不设置时默认使用mTextColor*/
    private int mProgressValueTextColor;

    /** 总进度值*/
    private int mTotalValue;
    /** 进度值*/
    private int mProgressValue;
    /** 标题*/
    private String mTitle;
    /** 右边是否显示分数形式，默认true*/
    private boolean mShowFraction = true;

    public NumPercent(Context context) {
        super(context);
        init(null, 0);
    }

    public NumPercent(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public NumPercent(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.NumPercent, defStyle, 0);

        // 获取自定义属性值
        mProgressBarColor = a.getColor(R.styleable.NumPercent_progressBarColor, mProgressBarColor);

        mTextSize = a.getDimension(R.styleable.NumPercent_textSize, mTextSize);
        mTextColor = a.getColor(R.styleable.NumPercent_textColor, mTextColor);

        mTitleTextSize = a.getDimension(R.styleable.NumPercent_titleTextSize, mTextSize);
        mTitleTextColor = a.getColor(R.styleable.NumPercent_titleTextColor, mTextColor);

        mProgressValueTextSize = a.getDimension(R.styleable.NumPercent_progressValueTextSize, mTextSize);
        mProgressValueTextColor = a.getColor(R.styleable.NumPercent_progressValueTextColor, mTextColor);

        mTotalValue = a.getInteger(R.styleable.NumPercent_totalValue, 1);
        mTitle = a.getString(R.styleable.NumPercent_title);
        mProgressValue = a.getInteger(R.styleable.NumPercent_progressValue, -1);
        mShowFraction = a.getBoolean(R.styleable.NumPercent_showFraction, mShowFraction);

        a.recycle();

        // Set up a default TextPaint object
        mTextPaint = new TextPaint();
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.LEFT);
        mTextPaint.setStyle(Paint.Style.FILL);

    }

    private void invalidateTextPaintAndMeasurements() {
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // allocations per draw cycle.
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth();
        int contentHeight = getHeight();

        // 画进度条
        mTextPaint.setColor(mProgressBarColor);
        if (mTotalValue == 0) {
            throw new RuntimeException("The mTotalValue cannot be 0");
        }
        final float percent = (float) mProgressValue / mTotalValue;
        canvas.drawRect(0, 0, percent * contentWidth, contentHeight, mTextPaint);

        // 画标题
        mTextPaint.setColor(mTitleTextColor);
        mTextPaint.setTextSize(mTitleTextSize);
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        final float titleHeight = fontMetrics.bottom - fontMetrics.top;
        canvas.drawText(mTitle, paddingLeft, paddingTop + (contentHeight - titleHeight) / 2 - fontMetrics.top, mTextPaint);

        // 画进度值
        mTextPaint.setColor(mProgressValueTextColor);
        mTextPaint.setTextSize(mProgressValueTextSize);
        final String text = mShowFraction ? mProgressValue + "/" + mTotalValue : String.valueOf(mProgressValue == -1 ? "" : mProgressValue);
        final float valueWidth = mTextPaint.measureText(text);
        fontMetrics = mTextPaint.getFontMetrics();
        final float valueHeight = fontMetrics.bottom - fontMetrics.top;
        canvas.drawText(text, contentWidth - paddingRight - valueWidth, paddingTop + (contentHeight - valueHeight) / 2 - fontMetrics.top, mTextPaint);

    }

    /**
     * 获取总进度值
     *
     * @return  总进度值
     */
    public int getTotalValue() {
        return mTotalValue;
    }

    /**
     * 设置总进度值
     *
     * @param totalValue  总进度值
     */
    public void setTotalValue(int totalValue) {
        this.mTotalValue = totalValue;
        invalidate();
    }

    /**
     * 设置进度值
     *
     * @return  进度值
     */
    public int getProgressValue() {
        return mProgressValue;
    }

    /**
     * 设置进度值
     *
     * @param progressValue  进度值
     */
    public void setProgressValue(int progressValue) {
        this.mProgressValue = progressValue;
        invalidate();
    }

    /**
     * 获取标题
     *
     * @return  标题
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * 设置标题
     *
     * @param title  标题
     */
    public void setTitle(final String title) {
        mTitle = title;
        invalidate();
    }
}
