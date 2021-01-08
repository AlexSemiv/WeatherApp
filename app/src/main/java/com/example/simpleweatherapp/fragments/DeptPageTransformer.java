package com.example.simpleweatherapp.fragments;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

public class DeptPageTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.75f;
    @Override
    public void transformPage(@NonNull View page, float position) {
        int pageWidth = page.getWidth();

        if(position < -1){
            page.setAlpha(0f); // is way off-screen to the left
        }else if(position <= 0){
            page.setAlpha(1f);
            page.setTranslationX(0f);
            page.setScaleX(1f);
            page.setScaleY(1f);
        }else if(position <= 1){
            page.setAlpha(1-position);
            page.setTranslationX(pageWidth* -position);

            float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
        }else{
            page.setAlpha(0f);
        }
    }
}
