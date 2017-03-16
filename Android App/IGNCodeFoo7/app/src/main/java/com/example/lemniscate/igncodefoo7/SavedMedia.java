package com.example.lemniscate.igncodefoo7;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lemniscate.igncodefoo7.JSONPojos.ADatum;
import com.example.lemniscate.igncodefoo7.JSONPojos.VDatum;
import com.example.lemniscate.igncodefoo7.views.Fave.IGNArticleFaveView;
import com.example.lemniscate.igncodefoo7.views.Fave.IGNVideoFaveView;
import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;
import org.joda.time.Period;

import java.util.ArrayList;

/**
 * Created by user on 3/11/17.
 */

public class SavedMedia extends Activity {

    private ArrayList<ADatum> articles;
    private ArrayList<VDatum> videos;
    private IGNArticleFaveView tempArtRemove;
    private IGNVideoFaveView tempVidRemove;

    public void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorites);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            //load articles
            if (extras.get("savedArticles") != null)
                articles = (ArrayList<ADatum>) extras.get("savedArticles");
            //load videos
            if (extras.get("savedVideos") != null)
                videos = (ArrayList<VDatum>) extras.get("savedVideos");

        }
        loadVideos();
        loadArticles();
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,v,menuInfo);
        getMenuInflater().inflate(R.menu.context_menu_saved,menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.removeSavedContent:
                if(tempArtRemove!=null) {
                    LinearLayout artLay = (LinearLayout)findViewById(R.id.artLayout);
                    artLay.removeView(tempArtRemove);
                }

                else if(tempVidRemove!=null){
                    LinearLayout vidLay = (LinearLayout)findViewById(R.id.vidLayout);
                    vidLay.removeView(tempVidRemove);
                    }
            default:
                tempArtRemove=null;
                tempVidRemove=null;
        }

        return true;
    }

    private void loadVideos(){
        LinearLayout vidFav = (LinearLayout)findViewById(R.id.vidLayout);
        for(int y = 0;y<videos.size();y++) {

            final IGNVideoFaveView cur = new IGNVideoFaveView(this);
            vidFav.addView(cur);

            final VDatum data = videos.get(y);
            TextView title = (TextView) cur.findViewById(R.id.favVidTitle);


            title.setText(data.getVMetadata().getName());
            ImageView thumb = (ImageView) cur.findViewById(R.id.favVidThumb);
            Picasso.with(this.getApplicationContext()).load(data.getVThumbnails().get(2).getUrl())
                    .into(thumb);

            final String url = data.getVMetadata().getUrl();
            thumb.setClickable(true);
            thumb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent openUrl = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(openUrl);

                }
            });


            registerForContextMenu(cur);
            thumb.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View v) {
                    if (v == null)
                        return false;
                    else {
                        tempVidRemove = cur;
                        openContextMenu(v);
                        return true;
                    }
                }
            });
        }

    }

    private void loadArticles(){
        LinearLayout artFav = (LinearLayout)findViewById(R.id.artLayout);
        for(int x = 0; x < articles.size();x++){


            final IGNArticleFaveView cur = new IGNArticleFaveView(this);
            artFav.addView(cur);

            TextView title = (TextView)cur.findViewById(R.id.title);
            TextView published = (TextView)cur.findViewById(R.id.published);

            final ADatum data = articles.get(x);

            //process and set correct date
            DateTime curTime = DateTime.now();
            DateTime publishedDate = new DateTime(data.getAMetadata().getPublishDate());
            Period period = new Period(publishedDate,curTime);
            if(period.getMonths()>=1)
                published.setText(period.getMonths()+ " MONTHS AGO");
            else if(period.getWeeks()>=1)
                published.setText(period.getWeeks() + " WEEKS AGO");
            else if(period.getDays()>=1)
                published.setText(period.getDays() + " DAYS AGO");
            else if(period.getHours() >= 1) {
                published.setText(  period.getHours() + " HOURS AGO");
            }
            else if(period.getMinutes() >= 1) {
                published.setText(  period.getMinutes() + " MINUTES AGO");
            }
            else if(period.getSeconds() >= 1)
                published.setText( period.getSeconds() + " SECONDS AGO");

            title.setText(data.getAMetadata().getHeadline().toUpperCase());

            //process thumbnail image and set clickable to traverse URL
            ImageView thumb = (ImageView)cur.findViewById(R.id.thumbnail);
            Picasso.with(this.getApplicationContext())
                    .load((data
                            .getAThumbnails()
                            .get(2)).getUrl())
                    .into(thumb);


            getArticleUrl(publishedDate,data.getAMetadata().getSlug());
            final String url = getArticleUrl(publishedDate,data.getAMetadata().getSlug());
            thumb.setClickable(true);
            thumb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent openUrl = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(openUrl);
                }
            });

            registerForContextMenu(cur);
            thumb.setOnLongClickListener(new View.OnLongClickListener(){

                @Override
                public boolean onLongClick(View v){
                    if(v==null)
                        return false;
                    else {
                        tempArtRemove = cur;
                        openContextMenu(v);
                        return true;
                    }
                }

            });
        }
    }

    private String getArticleUrl(DateTime publishedDate, String slug) {
        String month;
        String day;
        String url;

        if(publishedDate.getMonthOfYear()<10)
            month = "0" + publishedDate.getMonthOfYear();
        else
            month = ""+publishedDate.getMonthOfYear();

        if(publishedDate.getDayOfMonth()<10)
            day = "0" + publishedDate.getDayOfMonth();
        else
            day = "" + publishedDate.getDayOfMonth();

        url = "http://www.ign.com/articles/"
                + publishedDate.getYear()
                + "/" + month
                +"/" + day
                + "/" + slug;

        return url;
    }
}
