package com.gigigo.imageloader_demoapp;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.gigigo.ui.imageloader.ImageLoader;
import com.gigigo.ui.imageloader.ImageLoaderCallback;
import com.gigigo.ui.imageloader_glide.GlideImageLoaderImp;
import com.gigigo.ui.imageloader_picasso.PicassoCircleTransformation;
import com.gigigo.ui.imageloader_picasso.PicassoImageLoaderImp;

public class MainActivity extends AppCompatActivity {

  private ImageLoader imageLoader;

  private ImageView imageView;
  private Button buttonGlide;
  private Button buttonPicasso;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    initView();
  }

  private void initView() {
    imageView = (ImageView) findViewById(R.id.imageview);
    buttonGlide = (Button) findViewById(R.id.button_glide);
    buttonPicasso = (Button) findViewById(R.id.button_picasso);

    buttonGlide.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        setGlideImageLoader();
        String url = DataGenerator.generateRandomImageUrl();
        Snackbar.make(buttonGlide, "Glide ImageLoader! "+url, Snackbar.LENGTH_SHORT).show();
        imageLoader.load(url, imageView, R.drawable.ic_loading, new ImageLoaderCallback() {
          @Override public void onFinish(boolean isSuccess) {

            if(isSuccess) {
              Snackbar.make(buttonGlide, "image loaded!", Snackbar.LENGTH_SHORT).show();
            }
            else {
              Snackbar.make(buttonGlide, "image error :(", Snackbar.LENGTH_SHORT).show();
            }
          }
        });
      }
    });

    buttonPicasso.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        setPicassoImageLoader();
        String url = DataGenerator.generateRandomImageUrl();
        Snackbar.make(buttonPicasso, "Picasso ImageLoader! "+url, Snackbar.LENGTH_SHORT).show();
        imageLoader.load(url, imageView, R.drawable.ic_loading, new ImageLoaderCallback() {
          @Override public void onFinish(boolean isSuccess) {

            if(isSuccess) {
              Snackbar.make(buttonGlide, "image loaded!", Snackbar.LENGTH_SHORT).show();
            }
            else {
              Snackbar.make(buttonGlide, "image error :(", Snackbar.LENGTH_SHORT).show();
            }
          }
        });
      }
    });
  }

  private void setGlideImageLoader() {
    RequestManager requestManager = Glide.with(this);
    imageLoader = new GlideImageLoaderImp(requestManager);
  }

  private void setPicassoImageLoader() {
    imageLoader = new PicassoImageLoaderImp(this, new PicassoCircleTransformation(20,4,R.color.colorPrimary));
  }
}
