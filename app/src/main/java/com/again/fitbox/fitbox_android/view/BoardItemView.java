package com.again.fitbox.fitbox_android.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.again.fitbox.fitbox_android.R;
import com.again.fitbox.fitbox_android.util.CustomViewUtils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;


/**
 * Created by jeong on 2016. 12. 12..
 */

public class BoardItemView extends LinearLayout {

    Context mContext;
    TextView tvBoardTitle;
    ImageView ivBoardImage;

    public BoardItemView(Context context) {
        super(context);
        init(context);
        this.mContext = context;
    }

    public BoardItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    private void init(Context context){
        mContext = context;

        Integer screenWidth = CustomViewUtils.getScreenWidth(mContext);
        Integer height = screenWidth / 16 * 9;

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.board_list_item,this,true);

        tvBoardTitle = (TextView) findViewById(R.id.tv_boardtitle);

        ivBoardImage = (ImageView) findViewById(R.id.iv_boardimage);


    }

    public void setBoardTitle(String boardTitle) { tvBoardTitle.setText(boardTitle); }


    public void setBoardImage(final String imageUrl){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Target target = new Target(){

                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {

                        ivBoardImage.setImageBitmap(bitmap);
                       // loadingPanel.setVisibility(View.GONE);

                    }

                    @Override
                    public void onBitmapFailed(Drawable drawable) {
                        Log.e("muglau", "Menu image bitmap load failed");


                    }

                    @Override
                    public void onPrepareLoad(Drawable drawable) {

                    }

                };
                ivBoardImage.setTag(target);
                Picasso.with(mContext).load(imageUrl).resize(300,300).onlyScaleDown().centerCrop().into(target);
            }
        };

        Handler handler = new Handler();
        handler.post(runnable);


    }

    public void setMenuBackgroundImageFromBitmap(final Bitmap bitmap){
        (new Handler()).post(new Runnable() {
            @Override
            public void run() {
                ivBoardImage.setImageBitmap(bitmap);
               // loadingPanel.setVisibility(View.GONE);
            }
        });
    }
}
