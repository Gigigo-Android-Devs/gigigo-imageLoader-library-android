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
        imageLoader.load(getRandomImageUrl(), imageView);
      }
    });

    buttonPicasso.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        setPicassoImageLoader();
        imageLoader.load(getRandomImageUrl(), imageView);
      }
    });
  }

  private String getRandomImageUrl() {
    String url = "";

    return url;
  }

  private void setGlideImageLoader() {
    RequestManager requestManager = Glide.with(this);
    imageLoader = new GlideImageLoaderImp(requestManager);

    Snackbar.make(buttonGlide, "Glide ImageLoader!", Snackbar.LENGTH_SHORT).show();
  }

  private void setPicassoImageLoader() {
    imageLoader = new PicassoImageLoaderImp(this, new PicassoCircleTransformation(1,1,R.color.colorPrimary));

    Snackbar.make(buttonPicasso, "Picasso ImageLoader!", Snackbar.LENGTH_SHORT).show();
  }
}
