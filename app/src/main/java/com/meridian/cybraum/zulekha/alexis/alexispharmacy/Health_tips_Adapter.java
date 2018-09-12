package com.meridian.cybraum.zulekha.alexis.alexispharmacy;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

/**
 * Created by Ansal on 9/22/2017.
 */

public class Health_tips_Adapter extends RecyclerView.Adapter<Health_tips_Adapter.ViewHolder> {

    ArrayList<Health_tips_Model> faqArrayList;
    Context mcontext;
    int position;


    public Health_tips_Adapter(ArrayList<Health_tips_Model> mArrayList, Context context) {
        faqArrayList = mArrayList;
        mcontext=context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {

        viewHolder.tip_title.setText(faqArrayList.get(i).gettip_title());

            position=i;
            if (position%2==0){
                viewHolder.text_count.setBackgroundResource(R.drawable.round_copy);
            }
            else {
                viewHolder.text_count.setBackgroundResource(R.drawable.roundcopy);
            }
            int a=position+1;
            String no= String.valueOf(a);
            viewHolder.text_count.setText(no);
        String pish = "<html><head><style type=\"text/css\">@font-face {font-family: MyFont;src: url(\"file:///android_asset/fonts/Raleway-Regular.ttf\")}body {font-family: MyFont;font-size:13px;color:#000000;text-align: justify;}</style></head><body>";
        String myHtmlString = pish + faqArrayList.get(i).gettip_content();
        viewHolder.tip_content.loadDataWithBaseURL(null, myHtmlString, "text/html", "UTF-8", null);

        Glide.with(mcontext)
                .load(faqArrayList.get(i).getimage())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })

                .into(viewHolder.profile_image);
        viewHolder.tip_content.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;
                    case MotionEvent.ACTION_UP:
                        //Allow ScrollView to intercept touch events once again.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;
                }
                // Handle RecyclerView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return faqArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView text_count,tip_title;
        ImageView profile_image;
        WebView tip_content;
        public ViewHolder(View view) {
            super(view);
            profile_image=(ImageView)view.findViewById(R.id.img);
            tip_title=(TextView)view.findViewById(R.id.tip_title);
            tip_content=(WebView)view.findViewById(R.id.tip_content);
            text_count=(TextView)view.findViewById(R.id.text_count);


        }

    }

}