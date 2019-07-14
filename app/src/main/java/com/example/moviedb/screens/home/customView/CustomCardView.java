package com.example.moviedb.screens.home.customView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.cardview.widget.CardView;

import com.example.moviedb.R;

import butterknife.ButterKnife;

public class CustomCardView extends CardView {

    public CustomCardView(Context context) {
        super(context);
        init(context);
    }

    public CustomCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        View v = LayoutInflater.from(context).inflate(R.layout.custom_card_view, this, true);
        ButterKnife.bind(this, v);
    }
}
