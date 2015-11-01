package com.example.xyzreader.articleDetail;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by ebal on 01/11/15.
 */
public class DynamicHeightImageView extends ImageView {
    private float mAspectRatio = 1.5f;

    public DynamicHeightImageView(Context context) {
        super(context);
    }

    public void setAspectRatio(float aspectRatio) {
        mAspectRatio = aspectRatio;
        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredWidth = getMeasuredWidth();
        setMeasuredDimension(measuredWidth, (int) (measuredWidth / mAspectRatio));
    }


}
