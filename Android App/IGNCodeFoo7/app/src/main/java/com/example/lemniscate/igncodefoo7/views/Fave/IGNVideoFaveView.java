package com.example.lemniscate.igncodefoo7.views.Fave;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lemniscate.igncodefoo7.R;


/**
 * Created by user on 3/13/17.
 */

public class IGNVideoFaveView extends LinearLayout{

        ImageView favVidThumb;
        TextView favVidTitle;
        LinearLayout rootView;

        public IGNVideoFaveView(Context context) {
            super(context);
            init(context);
        }

        public IGNVideoFaveView(Context context, AttributeSet attrs) {
            super(context, attrs);
            init(context);
        }
        private void init(Context context){
            rootView = (LinearLayout) inflate(context, R.layout.fave_video_view,this);
            favVidTitle = (TextView)findViewById(R.id.favVidTitle);
            favVidThumb = (ImageView) findViewById(R.id.favVidThumb);
        }

}
