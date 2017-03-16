package com.example.lemniscate.igncodefoo7.views.Fave;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lemniscate.igncodefoo7.R;

/**
 * Created by user on 3/7/17.
 */
public class IGNArticleFaveView extends LinearLayout {

    TextView title;
    TextView published;
    LinearLayout rootView;
    ImageView thumbnail;


    public IGNArticleFaveView(Context context, AttributeSet attrs) {
        super(context,attrs);
        init(context);
    }

    public IGNArticleFaveView(Context context) {
        super(context);
        init(context);
    }
    private void init(Context context){
        rootView = (LinearLayout) inflate(context,R.layout.fave_article_view,this);
        title = (TextView)findViewById(R.id.title);
        published = (TextView)findViewById(R.id.published);
        thumbnail = (ImageView)findViewById(R.id.thumbnail);

    }
}
