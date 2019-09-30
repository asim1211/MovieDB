package com.example.moviedb.screens.home.customView;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.ViewGroup;


import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.moviedb.R;

import static com.example.moviedb.R2.style.CardView;


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
        inflate(context, R.layout.custom_card_view, this);

        Resources res = getResources();

        int dp8 = res.getDimensionPixelOffset(R.dimen.margin_card_view);
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.topMargin = dp8;
        params.bottomMargin = dp8;
        params.setMarginEnd(dp8 * 2);
        params.setMarginStart(dp8 * 2);
        setLayoutParams(params);

        setCardElevation(res.getDimensionPixelSize(R.dimen.elevation_card_view));
        setRadius(res.getDimensionPixelSize(R.dimen.radius_card_view));
    }
}
