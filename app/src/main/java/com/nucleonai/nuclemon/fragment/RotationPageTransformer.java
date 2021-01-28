package com.nucleonai.nuclemon.fragment;

import android.view.View;

import androidx.viewpager.widget.ViewPager;

public class RotationPageTransformer implements ViewPager.PageTransformer {

    private static final float MIN_SCALE=0.85f;

    @Override
    public void transformPage(View page, float position) {
        float scaleFactor = Math.max(MIN_SCALE,1 - Math.abs(position));
        float rotate = 10 * Math.abs(position);
        //When the position is less than or equal to 1, the page is already on the left-most side of the central item.
        //At this point set to the minimum zoom rate and the maximum rotation degree.
        if (position <= -1){
            page.setScaleX(MIN_SCALE);
            page.setScaleY(MIN_SCALE);
            page.setRotationY(rotate);
        }//position changes from 0 to - 1, and page slides to the left
        else if (position < 0){
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
            page.setRotationY(rotate);
        }//position changes from 0 to 1, and page slides gradually to the right
        else if (position >=0 && position < 1){
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
            page.setRotationY(-rotate);
        }//When position is greater than or equal to 1, the page is already on the far right side of the central item.
        else if (position >= 1){
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
            page.setRotationY(-rotate);
        }
    }
}