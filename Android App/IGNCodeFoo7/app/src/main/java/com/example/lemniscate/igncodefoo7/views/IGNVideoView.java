package com.example.lemniscate.igncodefoo7.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lemniscate.igncodefoo7.R;

/**
 * Created by user on 3/7/17.
 */
public class IGNVideoView extends LinearLayout {

    ImageView thumbnail0;
    ImageView thumbnail1;
    ImageView thumbnail2;
    ImageView thumbnail3;


    TextView title0;
    TextView title1;
    TextView title2;
    TextView title3;
    LinearLayout rootView;

    public IGNVideoView(Context context) {
        super(context);
        init(context);
    }

    public IGNVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    private void init(Context context){
        rootView = (LinearLayout) inflate(context, R.layout.video_view,this);

        title0 = (TextView)findViewById(R.id.title0);
        title1 = (TextView)findViewById(R.id.title1);
        title2 = (TextView)findViewById(R.id.title2);
        title3 = (TextView)findViewById(R.id.title3);

        thumbnail0 = (ImageView) findViewById(R.id.thumbnail0);
        thumbnail1 = (ImageView) findViewById(R.id.thumbnail1);
        thumbnail2 = (ImageView) findViewById(R.id.thumbnail2);
        thumbnail3 = (ImageView) findViewById(R.id.thumbnail3);


    }
}
