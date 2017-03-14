package com.gigigo.imageloader_demoapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.gigigo.ui.imageloader.ImageLoader;
import com.gigigo.ui.imageloader.ImageLoaderCallback;
import com.gigigo.ui.imageloader.glide.GlideImageLoaderImp;
import com.gigigo.ui.imageloader.glide.transformations.GlideCircleTransformation;
import com.gigigo.ui.imageloader.picasso.PicassoImageLoaderImp;

public class MenuActivity extends AppCompatActivity {

  private Button btnGlideInto, btnGlideCallback, btnGlideError, btnPicassoInto, btnPicassoCallback,
      btnPicassoError, btnGlideResource, btnPicassoResource, btnGlideCenterCrop, btnPicassoCenterCrop;
  private ImageLoader imageLoader;
  private ImageView imageView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_menu);

    btnGlideCallback = (Button) findViewById(R.id.button_glide_callback);
    btnGlideInto = (Button) findViewById(R.id.button_glide_into);
    btnGlideError = (Button) findViewById(R.id.button_glide_error);
    btnGlideResource = (Button) findViewById(R.id.button_glide_resource);
    btnGlideCenterCrop = (Button) findViewById(R.id.button_glide_center_crop);

    btnPicassoCallback = (Button) findViewById(R.id.button_picasso_callback);
    btnPicassoInto = (Button) findViewById(R.id.button_picasso_into);
    btnPicassoError = (Button) findViewById(R.id.button_picasso_error);
    btnPicassoResource = (Button) findViewById(R.id.button_picasso_resource);
    btnPicassoCenterCrop = (Button) findViewById(R.id.button_picasso_center_crop);

    imageView = (ImageView) findViewById(R.id.imageview2);

    btnGlideInto.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {

        setGlideImageLoader();
        String url = DataGenerator.generateRandomImageUrl();

        Snackbar.make(btnGlideInto, url, Snackbar.LENGTH_SHORT).show();

        //TODO: Download more transformations: https://github.com/wasabeef/glide-transformations
        imageLoader.load(url)
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.errorimage)
            //.transform(new GlideCircleTransformation(MenuActivity.this, 12,
              //  getResources().getColor(android.R.color.black)))
            //.transform(new RoundedCornersTransformation(MainActivity.this, 20, 20))
            .into(imageView)
            .build();
      }
    });

    btnGlideCallback.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {

        setGlideImageLoader();
        String url = DataGenerator.generateRandomImageUrl();

        Snackbar.make(btnGlideCallback, url, Snackbar.LENGTH_SHORT).show();

        //TODO: Download more transformations: https://github.com/wasabeef/glide-transformations
        imageLoader.load(url)
            .transform(new GlideCircleTransformation(MenuActivity.this, 12,
                getResources().getColor(android.R.color.black)))
            //.transform(new RoundedCornersTransformation(MainActivity.this, 20, 20))

            .loaderCallback(new ImageLoaderCallback() {
              @Override public void onSuccess(Bitmap bitmap) {
                Snackbar.make(btnGlideCallback, "image Glide loaded!", Snackbar.LENGTH_SHORT)
                    .show();
                imageView.setImageBitmap(bitmap);
              }

              @Override public void onError(Drawable errorDrawable) {
                Snackbar.make(btnGlideCallback, "image Glide error :(", Snackbar.LENGTH_SHORT)
                    .show();
                imageView.setImageResource(R.drawable.errorimage);
              }

              @Override public void onLoading() {
                imageView.setImageResource(R.drawable.ic_loading);
              }
            })
            .build();
      }
    });

    btnGlideResource.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        setGlideImageLoader();

        Snackbar.make(btnGlideCallback, "Image loaded from resource", Snackbar.LENGTH_SHORT).show();

        imageLoader.load(R.drawable.resourceimage)
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.errorimage)
            .into(imageView)
            .build();
      }
    });

    btnGlideCenterCrop.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {

        setGlideImageLoader();
        String url = DataGenerator.generateRandomImageUrl();

        Snackbar.make(btnGlideCenterCrop, url, Snackbar.LENGTH_SHORT).show();

        //TODO: Download more transformations: https://github.com/wasabeef/glide-transformations
        imageLoader.load(url)
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.errorimage)
            .centerCrop(Boolean.TRUE)
            //.transform(new GlideCircleTransformation(MenuActivity.this, 12,
            //    getResources().getColor(android.R.color.black)))
            //.transform(new RoundedCornersTransformation(MainActivity.this, 20, 20))
            .into(imageView)
            .build();
      }
    });

    btnGlideError.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        setGlideImageLoader();
        String url = "Error";

        Snackbar.make(btnGlideCallback, url, Snackbar.LENGTH_SHORT).show();

        imageLoader.load(url)
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.errorimage)
            .transform(new GlideCircleTransformation(MenuActivity.this, 12,
                getResources().getColor(android.R.color.black)))
            .into(imageView)
            .build();
      }
    });


    btnPicassoInto.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {

        setPicassoImageLoader();
        String url = DataGenerator.generateRandomImageUrl();
        Snackbar.make(btnPicassoInto, url, Snackbar.LENGTH_SHORT).show();

        //TODO: test it with circleimage
        imageLoader.load(url).placeholder(R.drawable.ic_loading).error(R.drawable.errorimage)
            .into(imageView)

            .build();

      }
    });

    btnPicassoCallback.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {

        setPicassoImageLoader();
        String url = DataGenerator.generateRandomImageUrl();
        Snackbar.make(btnPicassoCallback, url, Snackbar.LENGTH_SHORT).show();

        //TODO: test it with circleimage
        imageLoader.load(url)
            .loaderCallback(new ImageLoaderCallback() {
              @Override public void onSuccess(Bitmap bitmap) {
                Snackbar.make(btnPicassoCallback, "image Picasso loaded!", Snackbar.LENGTH_SHORT)
                    .show();
                imageView.setImageBitmap(bitmap);
              }

              @Override public void onError(Drawable errorDrawable) {
                Snackbar.make(btnPicassoCallback, "image Picasso error :(", Snackbar.LENGTH_SHORT)
                    .show();
                imageView.setImageResource(R.drawable.errorimage);
              }

              @Override public void onLoading() {
                imageView.setImageResource(R.drawable.ic_loading);
              }
            })
            .build();
      }
    });

    btnPicassoResource.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {

        setPicassoImageLoader();

        imageLoader.load(R.drawable.resourceimage)
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.errorimage)
            .into(imageView)
            .build();

      }
    });


    btnPicassoCenterCrop.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {

        setPicassoImageLoader();
        String url = DataGenerator.generateRandomImageUrl();
        Snackbar.make(btnPicassoInto, url, Snackbar.LENGTH_SHORT).show();

        //TODO: test it with circleimage
        imageLoader.load(url).placeholder(R.drawable.ic_loading).error(R.drawable.errorimage)
            .into(imageView)
            .centerCrop(Boolean.TRUE)
            .override(200,200)
            .build();


      }
    });

    btnPicassoError.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {

        setPicassoImageLoader();
        String url = "Error";
        Snackbar.make(btnPicassoCallback, url, Snackbar.LENGTH_SHORT).show();

        imageLoader.load(url)
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.errorimage)
            .into(imageView)
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
