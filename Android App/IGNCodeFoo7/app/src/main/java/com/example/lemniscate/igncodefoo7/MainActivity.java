//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.example.lemniscate.igncodefoo7;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lemniscate.igncodefoo7.JSONPojos.ADatum;
import com.example.lemniscate.igncodefoo7.JSONPojos.JSONArticle;
import com.example.lemniscate.igncodefoo7.JSONPojos.JSONVideo;
import com.example.lemniscate.igncodefoo7.JSONPojos.VDatum;
import com.example.lemniscate.igncodefoo7.views.IGNArticleView;
import com.example.lemniscate.igncodefoo7.views.IGNPageView;
import com.example.lemniscate.igncodefoo7.views.IGNVideoView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;
import org.joda.time.Period;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static final String ARTICLE_URL1 = "http://ign-apis.herokuapp.com/articles?startIndex=";
    private static final String ARTICLE_URL2 = "\\u0026count=10";
    private static final String VIDEO_URL = "http://ign-apis.herokuapp.com/videos?startIndex=";
    private static final String VIDEO_URL2 = "\\u0026count=10";
    LinkedList<VDatum> vidDatumList = new LinkedList();

    private int pageScalerViewId=0;
    private int pageScalerArticleIndex=0;
    private int pageScalerVidIndex=0;
    private int curPage=0;

    private ArrayList<ADatum> savedArticles = new ArrayList<>();
    private ArrayList<VDatum> savedVideos = new ArrayList<>();

    private ADatum tempFaveArticle;
    private VDatum tempFaveVideo;

    final private String pageId = "page" + 0;
    private Gson gson;
    private RequestQueue requestQueue;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        gson = new Gson();
        requestQueue = Volley.newRequestQueue(this.getApplicationContext());



        loadPage(curPage);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }


    //Logic for toolbar buttons
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.home:
                loadPage(0);
                curPage=0;
                return true;

            case R.id.nextPage:
                if(curPage!=300) {
                    loadPage(curPage + 1);
                    curPage++;
                }


                return true;

            case R.id.prevPage:
                if(curPage!=0) {
                    loadPage(curPage - 1);
                    curPage--;
                }
                
                return true;

            case R.id.favorites:
                //load favorites layout
                Intent intent = new Intent(this,SavedMedia.class);
               intent.putExtra("savedArticles",savedArticles);
               intent.putExtra("savedVideos",savedVideos);
                startActivity(intent);

            default:

                return super.onOptionsItemSelected(item);
        }

    }

    //method to initiate the volley request for the next URL in line(based on pageScalerArticleIndex) to grab JSON data
    private void getArticleApiForPage() {
        String url = "http://ign-apis.herokuapp.com/articles?startIndex=" + pageScalerArticleIndex + "\\u0026count=030";
        StringRequest request = new StringRequest(0, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                volleyCallbackArt(response);
            }

        },
        new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error){
                Log.d("Error.Response",error.toString());
            }
        });


        requestQueue.add(request);
    }


    //Volley Callback to load JSON data from article apis into corresponding media objects
    private void volleyCallbackArt(String response) {
        JSONArticle gTemp = gson.fromJson(response, JSONArticle.class);
        DateTime curTime = DateTime.now();
        System.out.println(curTime.toLocalTime());
        IGNPageView page = (IGNPageView)findViewById(getResources().getIdentifier(pageId,"id",getPackageName()));

        List<ADatum> datumList = gTemp.getData();

        for(int x = 0; x < datumList.size(); x++) {
            String artId = "article" + (x+pageScalerViewId);
            final IGNArticleView cur = (IGNArticleView) page.findViewById(this.getResources().getIdentifier(artId, "id", this.getPackageName()));

            TextView title = (TextView)cur.findViewById(R.id.title);
            TextView published = (TextView)cur.findViewById(R.id.published);

            final ADatum data = datumList.get(x);

            //process and set correct date
            DateTime publishedDate = new DateTime(datumList.get(x).getAMetadata().getPublishDate());
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


            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

            ImageView thumb = (ImageView)cur.findViewById(R.id.thumbnail);
            Picasso.with(this.getApplicationContext())
                    .load(((datumList
                            .get(x)).getAThumbnails()
                            .get(2)).getUrl()).resize(displayMetrics.widthPixels,700)
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
                        tempFaveArticle = data;
                        openContextMenu(v);
                        return true;
                    }
                }

            });

        }

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,v,menuInfo);
        getMenuInflater().inflate(R.menu.context_menu_main,menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.saveContent:
                if(tempFaveArticle!=null)
                    if(!savedArticles.contains(tempFaveArticle))
                        savedArticles.add(tempFaveArticle);
                if(tempFaveVideo!=null)
                    if(!savedVideos.contains(tempFaveVideo))
                        savedVideos.add(tempFaveVideo);
        }

        return true;
    }

    //Method to convert URL slug and date of article into a URL for corresponding article
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

    //method to initiate the volley request for the next URL in line(based on pageScalerVideoIndex) to grab JSON data
    //for videos
    private void getVideoApiForPage() {
        String urlVid = "http://ign-apis.herokuapp.com/videos?startIndex=" + pageScalerVidIndex + "\\u0026count=10";
        StringRequest request = new StringRequest(0, urlVid, new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                volleyCallbackVid1(response);
            }

        },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Log.d("Error.Response",error.toString());
                    }
                });

        requestQueue.add(request);




        urlVid = "http://ign-apis.herokuapp.com/videos?startIndex=" + (pageScalerVidIndex + 10)+"\\u0026count=10";
        request = new StringRequest(0, urlVid, new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                volleyCallbackVid2(response);
            }

        },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Log.d("Error.Response",error.toString());
                    }
                });

        requestQueue.add(request);



    }



    //VolleyCallback method to grab JSON data and load that data into the first half of video views for the page
    private void volleyCallbackVid1(String response) {
        JSONVideo temp = this.gson.fromJson(response, JSONVideo.class);
        IGNPageView page = (IGNPageView)findViewById(getResources().getIdentifier(pageId,"id",getPackageName()));
        int y;
        String vidId;

        //load list
        for (y = 0; y < temp.getData().size(); y++) {
            vidDatumList.addLast(temp.getData().get(y));
        }

        vidId = "video" + 0;
        IGNVideoView cur = (IGNVideoView) page.findViewById(this.getResources().getIdentifier(vidId, "id", this.getPackageName()));

        for (y = 0; y < 4; y++) {
            setVidContent(y, vidDatumList, cur);
        }

        vidId = "video" + 1;
        cur = (IGNVideoView) this.findViewById(this.getResources().getIdentifier(vidId, "id", this.getPackageName()));

        for (y = 0; y < 4; y++) {
            setVidContent(y, vidDatumList, cur);
        }

        vidId = "video" + 2;
        cur = (IGNVideoView) this.findViewById(this.getResources().getIdentifier(vidId, "id", this.getPackageName()));

        for (y = 0; y < 2; y++) {
            setVidContent(y, vidDatumList, cur);
        }

    }

    //VolleyCallback method to grab JSON data and load that data into the second half of video views for the page
    private void volleyCallbackVid2(String response) {
        JSONVideo temp = this.gson.fromJson(response, JSONVideo.class);
        IGNPageView page = (IGNPageView)findViewById(getResources().getIdentifier(pageId,"id",getPackageName()));


        int y;
        for (y = 0; y < temp.getData().size(); y++) {
            vidDatumList.addLast(temp.getData().get(y));
        }

        String vidId = "video" + 2;
        IGNVideoView cur = (IGNVideoView) page.findViewById(this.getResources().getIdentifier(vidId, "id", this.getPackageName()));
        

        for (y = 2; y < 4; y++) {
            setVidContent(y, vidDatumList, cur);
        }

        vidId = "video" + 3;
        cur = (IGNVideoView) this.findViewById(this.getResources().getIdentifier(vidId, "id", this.getPackageName()));

        for (y = 0; y < 4; y++) {
            setVidContent(y, vidDatumList, cur);
        }

        vidId = "video" + 4;
        cur = (IGNVideoView) this.findViewById(this.getResources().getIdentifier(vidId, "id", this.getPackageName()));

        for (y = 0; y < 4; y++) {
            setVidContent(y, vidDatumList, cur);

        }


    }

    //Method to load data into video views
    private void setVidContent(int y, LinkedList<VDatum> vidDatumList,final IGNVideoView cur) {
        final VDatum data = vidDatumList.getFirst();
        vidDatumList.removeFirst();
        String titleId = "title" + y;
        String thumbId = "thumbnail" + y;
        TextView title = (TextView) cur.findViewById(this.getResources().getIdentifier(titleId, "id", this.getPackageName()));
        String sTitle = data.getVMetadata().getName();
        if (sTitle.length() > 45) {
            sTitle = sTitle.substring(0, sTitle.length() - 20);
            sTitle = sTitle + "...";
        }


        title.setText(sTitle );
        ImageView thumb = (ImageView) cur.findViewById(this.getResources().getIdentifier(thumbId, "id", this.getPackageName()));
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
        thumb.setOnLongClickListener(new View.OnLongClickListener(){

            @Override
            public boolean onLongClick(View v){
                if(v==null)
                    return false;
                else {
                    tempFaveVideo = data;
                    openContextMenu(v);
                    return true;
                }
            }
        });

    }

    //Main driver method for page, loads an URL with an index at the given page
    public void loadPage(int page){
        pageScalerVidIndex=page*10;
        pageScalerArticleIndex=page*10;
        getArticleApiForPage();
        getVideoApiForPage();

    }
}
