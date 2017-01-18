package com.gigigo.imageloader_demoapp;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.gigigo.ui.imageloader.ImageLoader;
import com.gigigo.ui.imageloader.ImageLoaderCallback;
import com.gigigo.ui.imageloader.glide.GlideCircleTransformation;
import com.gigigo.ui.imageloader.glide.GlideImageLoaderImp;
import com.gigigo.ui.imageloader.glide.RoundedCornersTransformation;
import com.gigigo.ui.imageloader.picasso.PicassoImageLoaderImp;

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

        Snackbar.make(buttonGlide, url, Snackbar.LENGTH_SHORT).show();

        //TODO: Download more transformation: https://github.com/wasabeef/glide-transformations

        imageLoader.load(url)
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.ic_loading)
            //.transform(new GlideCircleTransformation(MainActivity.this, 12, getResources().getColor(android.R.color.black)))
            .transform(new RoundedCornersTransformation(MainActivity.this, 20, 20))
            .into(imageView)
            //.loaderCallback(new ImageLoaderCallback() {
            //  @Override public void onSuccess(Bitmap bitmap) {
            //    Snackbar.make(buttonGlide, "image Glide loaded!", Snackbar.LENGTH_SHORT).show();
            //    imageView.setImageBitmap(bitmap);
            //  }
            //
            //  @Override public void onError(Drawable errorDrawable) {
            //    Snackbar.make(buttonGlide, "image Glide error :(", Snackbar.LENGTH_SHORT).show();
            //    imageView.setImageResource(R.drawable.ic_loading);
            //  }
            //
            //  @Override public void onLoading() {
            //    imageView.setImageResource(R.drawable.ic_loading);
            //  }
            //})
            .build();
      }
    });

    buttonPicasso.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        setPicassoImageLoader();
        String url = DataGenerator.generateRandomImageUrl();
        Snackbar.make(buttonPicasso, url, Snackbar.LENGTH_SHORT).show();

        //TODO: test it with circleimage
        imageLoader.load(url)
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.ic_loading)
            //.into(imageView)
            .loaderCallback(new ImageLoaderCallback() {
              @Override public void onSuccess(Bitmap bitmap) {
                Snackbar.make(buttonGlide, "image Picasso loaded!", Snackbar.LENGTH_SHORT).show();
                imageView.setImageBitmap(bitmap);
              }

              @Override public void onError(Drawable errorDrawable) {
                Snackbar.make(buttonGlide, "image Picasso error :(", Snackbar.LENGTH_SHORT)
                    .show();
                imageView.setImageResource(R.drawable.ic_loading);
              }

              @Override public void onLoading() {
                imageView.setImageResource(R.drawable.ic_loading);
              }
            })
            .build();
      }
    });
  }

  private void setGlideImageLoader() {
    imageLoader = new GlideImageLoaderImp(this);
  }

  private void setPicassoImageLoader() {
    imageLoader = new PicassoImageLoaderImp(this);
  }
}
