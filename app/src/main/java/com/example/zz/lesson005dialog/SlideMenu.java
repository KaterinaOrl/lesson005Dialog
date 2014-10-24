package com.example.zz.lesson005dialog;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by ZZ on 23.10.2014.
 */
public class SlideMenu extends LinearLayout {

    Context mycontext;
    LinearLayout root;

    public SlideMenu(Context context) {
        super(context);
        mycontext = context;
        if(!isInEditMode())
            initSlideMenu();
    }



    public SlideMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        mycontext = context;
        if(!isInEditMode())
            initSlideMenu();
    }

    public SlideMenu(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mycontext = context;
        if(!isInEditMode())
            initSlideMenu();
    }

    private void initSlideMenu() {
            LayoutInflater layoutInflater;
            layoutInflater = (LayoutInflater)mycontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            root = (LinearLayout) layoutInflater.inflate(R.layout.slide_menu, this);

        }
 }


