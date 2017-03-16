package com.example.lemniscate.igncodefoo7.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lemniscate.igncodefoo7.R;

import static com.example.lemniscate.igncodefoo7.R.id.rootView;

/**
 * Created by user on 3/8/17.
 */

public class IGNPageView extends LinearLayout{

    LinearLayout rootView;
    IGNArticleView article0;
    IGNArticleView article1;
    IGNArticleView article2;
    IGNArticleView article3;
    IGNArticleView article4;
    IGNArticleView article5;
    IGNArticleView article6;
    IGNArticleView article7;
    IGNArticleView article8;
    IGNArticleView article9;


    IGNVideoView video0;
    IGNVideoView video1;
    IGNVideoView video2;
    IGNVideoView video3;
    IGNVideoView video4;


    public IGNPageView(Context context, AttributeSet attrs) {
        super(context,attrs);
        init(context);
    }
    private void init(Context context){
        rootView = (LinearLayout)inflate(context, R.layout.page_view,this);
        article0 = (IGNArticleView)findViewById(R.id.article0);
        article1 = (IGNArticleView)findViewById(R.id.article1);
        article2 = (IGNArticleView)findViewById(R.id.article2);
        article3 = (IGNArticleView)findViewById(R.id.article3);
        article4 = (IGNArticleView)findViewById(R.id.article4);
        article5 = (IGNArticleView)findViewById(R.id.article5);
        article6 = (IGNArticleView)findViewById(R.id.article6);
        article7 = (IGNArticleView)findViewById(R.id.article7);
        article8 = (IGNArticleView)findViewById(R.id.article8);
        article9 = (IGNArticleView)findViewById(R.id.article9);

        video0 = (IGNVideoView)findViewById(R.id.video0);
        video1 = (IGNVideoView)findViewById(R.id.video1);
        video2 = (IGNVideoView)findViewById(R.id.video2);
        video3 = (IGNVideoView)findViewById(R.id.video3);
        video4 = (IGNVideoView)findViewById(R.id.video4);


    }
}
