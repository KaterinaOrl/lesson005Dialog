package com.example.zz.lesson005dialog;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by ZZ on 22.10.2014.
 */
public class MyCustomView extends RelativeLayout{


    Context mycontext;
    RelativeLayout root;
    TextView textView;
    ImageView imgMenu;

    public MyCustomView(Context context) {
        super(context);
        mycontext = context;
        if(!isInEditMode())
            initBtntext();

    }

    public MyCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mycontext = context;
        if(!isInEditMode())
            initBtntext();

    }


    public MyCustomView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mycontext = context;
        if(!isInEditMode())
            initBtntext();

    }

    private void initBtntext() {

        LayoutInflater layoutInflater;
        layoutInflater = (LayoutInflater)mycontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        root = (RelativeLayout) layoutInflater.inflate(R.layout.my_custom_view, this);
        textView = (TextView) root.findViewById(R.id.textView);
        imgMenu = (ImageView) root.findViewById(R.id.imgMenu);

    }

    public void setText (int value){
        if (value != 0) {
            textView.setVisibility(VISIBLE);
            textView.setText(String.valueOf(value));
        }
        else textView.setVisibility(GONE);
    }

    public void showMenu(final View layout){
        imgMenu.setOnClickListener( new OnClickListener() {
            @Override
            public void onClick(View view) {

                if (layout.getX()!=200){
                ObjectAnimator animShow = ObjectAnimator.ofFloat(layout, "x" , 0, 200);
                animShow.setDuration(2000);
                animShow.start();}
            }
        });
    }

}
