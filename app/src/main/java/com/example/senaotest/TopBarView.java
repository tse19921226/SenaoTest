package com.example.senaotest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class TopBarView extends ConstraintLayout {
    private String TAG = getClass().getSimpleName();
    public static int SERACH_TYPE = 1111;
    public static int DETAIL_TYPE = 2222;
    private int TOPBAR_VIEW_TYPE = 1111;

    private ImageView ivBack;
    private TextView tvTitle;
    private SearchView svSearch;
    public TopBarView(Context context) {
        super(context);
        initView(context, null);
    }

    public TopBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public TopBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView(Context context, AttributeSet attrs){
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.top_bar_view, this, false);
        this.addView(view);
        ivBack = view.findViewById(R.id.iv_back);
        tvTitle = view.findViewById(R.id.tv_title);
        svSearch = view.findViewById(R.id.sv_search);
        ivBack.setOnClickListener(onClickListener);
    }

    public void setViewType(int Type){
        TOPBAR_VIEW_TYPE = Type;
    }

    public void setup(){
        if (TOPBAR_VIEW_TYPE == SERACH_TYPE) {
            ivBack.setVisibility(GONE);
            tvTitle.setVisibility(GONE);
            svSearch.setVisibility(VISIBLE);
        } else {
            ivBack.setVisibility(VISIBLE);
            tvTitle.setVisibility(VISIBLE);
            svSearch.setVisibility(GONE);
        }
    }

    private View.OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
}
