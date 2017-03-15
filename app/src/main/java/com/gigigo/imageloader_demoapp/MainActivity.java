package com.gigigo.imageloader_demoapp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.gigigo.ui.imageloader.ImageLoader;
import com.gigigo.ui.imageloader.ImageLoaderCallback;
import com.gigigo.ui.imageloader.glide.GlideImageLoaderImp;
import com.gigigo.ui.imageloader.glide.transformations.GlideCircleTransformation;
import com.gigigo.ui.imageloader.picasso.PicassoImageLoaderImp;

public class MainActivity extends AppCompatActivity {

  private Button btnGlideInto;
  private Button btnGlideCallback;
  private Button btnGlideError;
  private Button btnPicassoInto;
  private Button btnPicassoCallback;
  private Button btnPicassoError;
  private Button btnGlideResource;
  private Button btnPicassoResource;
  private Button btnGlideCenterCrop;
  private Button btnPicassoCenterCrop;
  private Button btnGlideOverride;
  private Button btnPicassoOverride;
  private Button btnGlideFitCenter;
  private Button btnPicassoFitCenter;
  private Button btnPicassoRotate;
  private Button btnGlideAnimate;
  private Button btnGlideSizeMulptiplier;

  private ImageLoader imageLoader;
  private ImageView imageView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_menu);

    btnGlideCallback = (Button) findViewById(R.id.button_glide_callback);
    btnGlideInto = (Button) findViewById(R.id.button_glide_into);
    btnGlideResource = (Button) findViewById(R.id.button_glide_resource);
    btnGlideCenterCrop = (Button) findViewById(R.id.button_glide_center_crop);
    btnGlideOverride = (Button) findViewById(R.id.button_glide_override);
    btnGlideFitCenter = (Button) findViewById(R.id.button_glide_fit_center);
    btnGlideAnimate = (Button) findViewById(R.id.button_glide_animate);
    btnGlideSizeMulptiplier = (Button) findViewById(R.id.button_glide_size_multiplier);
    btnGlideError = (Button) findViewById(R.id.button_glide_error);

    btnPicassoCallback = (Button) findViewById(R.id.button_picasso_callback);
    btnPicassoInto = (Button) findViewById(R.id.button_picasso_into);
    btnPicassoError = (Button) findViewById(R.id.button_picasso_error);
    btnPicassoResource = (Button) findViewById(R.id.button_picasso_resource);
    btnPicassoCenterCrop = (Button) findViewById(R.id.button_picasso_center_crop);
    btnPicassoOverride = (Button) findViewById(R.id.button_picasso_override);
    btnPicassoFitCenter = (Button) findViewById(R.id.button_picasso_fit_center);
    btnPicassoRotate = (Button) findViewById(R.id.button_picasso_rotate);

    LayoutInflater inflater = getLayoutInflater();
    View view = inflater.inflate(R.layout.dialog_image, null);
    imageView = (ImageView) view.findViewById(R.id.imageview2);

    final Dialog dialog = onCreateDialog(view);

    btnGlideInto.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        setGlideImageLoader();

        String url = DataGenerator.generateRandomImageUrl();

        Snackbar.make(btnGlideInto, url, Snackbar.LENGTH_SHORT).show();

        //TODO: Download more transformations: https://github.com/wasabeef/glide-transformations
        imageLoader.load(url)
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.errorimage)
            .into(imageView)
            .build();
        dialog.show();
      }
    });

    btnGlideCallback.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {

        setGlideImageLoader();
        String url = DataGenerator.generateRandomImageUrl();

        Snackbar.make(btnGlideCallback, url, Snackbar.LENGTH_SHORT).show();

        //TODO: Download more transformations: https://github.com/wasabeef/glide-transformations
        imageLoader.load(url)
            .transform(new GlideCircleTransformation(MainActivity.this, 12,
                getResources().getColor(android.R.color.black)))
            //.transform(new RoundedCornersTransformation(MainActivity.this, 20, 20))

            .loaderCallback(new ImageLoaderCallback() {
              @Override public void onSuccess(Bitmap bitmap) {
                Snackbar.make(btnGlideCallback, "image Glide loaded!", Snackbar.LENGTH_SHORT)
                    .show();
                imageView.setImageBitmap(bitmap);
                dialog.show();
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
        dialog.show();
      }
    });

    btnGlideCenterCrop.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {

        setGlideImageLoader();
        String url = DataGenerator.generateRandomImageUrl();

        Snackbar.make(btnGlideCenterCrop, url, Snackbar.LENGTH_SHORT).show();

        //TODO: Download more transformations: https://github.com/wasabeef/glide-transformations
        imageLoader.load(url).placeholder(R.drawable.ic_loading).error(R.drawable.errorimage)

            //.transform(new GlideCircleTransformation(MainActivity.this, 12,
            //    getResources().getColor(android.R.color.black)))
            //.transform(new RoundedCornersTransformation(MainActivity.this, 20, 20))
            .into(imageView).centerCrop(Boolean.TRUE).build();
        dialog.show();
      }
    });

    btnGlideOverride.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        setGlideImageLoader();
        String url = DataGenerator.generateRandomImageUrl();

        Snackbar.make(btnGlideOverride, url, Snackbar.LENGTH_SHORT).show();
        imageLoader.load(url)
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.errorimage)
            .override(50, 50)
            .into(imageView)
            .build();
        dialog.show();
      }
    });

    btnGlideFitCenter.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {

        setGlideImageLoader();
        String url = DataGenerator.generateRandomImageUrl();

        Snackbar.make(btnGlideFitCenter, url, Snackbar.LENGTH_SHORT).show();
        imageLoader.load(url)
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.errorimage)
            .into(imageView)
            .fitCenter(Boolean.TRUE)
            .build();
        dialog.show();
      }
    });


    btnGlideAnimate.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {

        setGlideImageLoader();
        String url = DataGenerator.generateRandomImageUrl();

        Snackbar.make(btnGlideAnimate, url, Snackbar.LENGTH_SHORT).show();
        imageLoader.load(url)
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.errorimage)
            .into(imageView)
            .animate(Boolean.TRUE)
            .build();
        dialog.show();
      }
    });

    btnGlideSizeMulptiplier.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {

        setGlideImageLoader();
        String url = DataGenerator.generateRandomImageUrl();

        Snackbar.make(btnGlideSizeMulptiplier, url, Snackbar.LENGTH_SHORT).show();
        imageLoader.load(url)
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.errorimage)
            .into(imageView)
            .sizeMultiplier(0.3f)
            .build();
        dialog.show();
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
            .transform(new GlideCircleTransformation(MainActivity.this, 12,
                getResources().getColor(android.R.color.black)))
            .into(imageView)
            .build();
        dialog.show();
      }
    });

    btnPicassoInto.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {

        setPicassoImageLoader();
        String url = DataGenerator.generateRandomImageUrl();
        Snackbar.make(btnPicassoInto, url, Snackbar.LENGTH_SHORT).show();

        //TODO: test it with circleimage
        imageLoader.load(url)
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.errorimage)
            .into(imageView)
            .build();
        dialog.show();
      }
    });

    btnPicassoCallback.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {

        setPicassoImageLoader();
        String url = DataGenerator.generateRandomImageUrl();
        Snackbar.make(btnPicassoCallback, url, Snackbar.LENGTH_SHORT).show();

        //TODO: test it with circleimage
        imageLoader.load(url).loaderCallback(new ImageLoaderCallback() {
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
        }).build();
        dialog.show();
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
        dialog.show();
      }
    });

    btnPicassoCenterCrop.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {

        setPicassoImageLoader();
        String url = DataGenerator.generateRandomImageUrl();
        Snackbar.make(btnPicassoInto, url, Snackbar.LENGTH_SHORT).show();

        //TODO: test it with circleimage
        imageLoader.load(url)
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.errorimage)
            .into(imageView)
            .centerCrop(Boolean.TRUE)
            .override(200, 200)
            .build();
        dialog.show();
      }
    });

    btnPicassoOverride.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        setPicassoImageLoader();
        String url = DataGenerator.generateRandomImageUrl();

        Snackbar.make(btnPicassoOverride, url, Snackbar.LENGTH_SHORT).show();
        imageLoader.load(url)
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.errorimage)
            .override(50, 50)
            .into(imageView)
            .build();
        dialog.show();
      }
    });

    btnPicassoFitCenter.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {

        setPicassoImageLoader();
        String url = DataGenerator.generateRandomImageUrl();

        Snackbar.make(btnPicassoFitCenter, url, Snackbar.LENGTH_SHORT).show();
        imageLoader.load(url)
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.errorimage)
            .into(imageView)
            .fitCenter(Boolean.TRUE)
            .override(500,500)
            .build();
        dialog.show();
      }
    });

    btnPicassoRotate.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        setPicassoImageLoader();
        String url = DataGenerator.generateRandomImageUrl();

        Snackbar.make(btnPicassoRotate, url, Snackbar.LENGTH_SHORT).show();
        imageLoader.load(url)
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.errorimage)
            .into(imageView)
            .rotate(90)
            .build();
        dialog.show();
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
        dialog.show();
      }
    });

    //End onCreate
  }

  public Dialog onCreateDialog(View view) {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    // Get the layout inflater

    // Inflate and set the layout for the dialog
    // Pass null as the parent view because its going in the dialog layout
    builder.setView(view)
        // Add action buttons
        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int id) {

          }
        });
    return builder.create();

  }

  private void setGlideImageLoader() {
    imageLoader = new GlideImageLoaderImp(this);
  }

  private void setPicassoImageLoader() {
    imageLoader = new PicassoImageLoaderImp(this);
  }
}
