package com.gigigo.ui.imageloader_demoapp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.gigigo.ui.imageloader.glide.transformations.ColorFilterTransformation;
import com.gigigo.ui.imageloader.glide.transformations.CropCircleTransformation;
import com.gigigo.ui.imageloader.glide.transformations.CropSquareTransformation;
import com.gigigo.ui.imageloader.glide.transformations.CropTransformation;
import com.gigigo.ui.imageloader.glide.transformations.GrayscaleTransformation;
import com.gigigo.ui.imageloader.glide.transformations.MaskTransformation;
import com.gigigo.ui.imageloader.glide.transformations.gpu.BrightnessFilterTransformation;
import com.gigigo.ui.imageloader.glide.transformations.gpu.ContrastFilterTransformation;
import com.gigigo.ui.imageloader.glide.transformations.gpu.InvertFilterTransformation;
import com.gigigo.ui.imageloader.glide.transformations.gpu.KuwaharaFilterTransformation;
import com.gigigo.ui.imageloader.glide.transformations.gpu.PixelationFilterTransformation;
import com.gigigo.ui.imageloader.glide.transformations.gpu.SepiaFilterTransformation;
import com.gigigo.ui.imageloader.glide.transformations.gpu.SketchFilterTransformation;
import com.gigigo.ui.imageloader.glide.transformations.gpu.SwirlFilterTransformation;
import com.gigigo.ui.imageloader.glide.transformations.gpu.ToonFilterTransformation;
import com.gigigo.ui.imageloader.glide.transformations.gpu.VignetteFilterTransformation;
import com.gigigo.ui.imageloader.glide.transformations.BlurTransformation;

import com.gigigo.ui.imageloader.ImageLoader;
import com.gigigo.ui.imageloader.ImageLoaderCallback;
import com.gigigo.ui.imageloader.glide.GlideImageLoaderImp;
import com.gigigo.ui.imageloader.glide.transformations.CircleTransformation;
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
  private Button btnGlideBlur;
  private Button btnGlideCrop;
  private Button btnGlideCropSquare;
  private Button btnGlideCropCircle;
  private Button btnGlideColorFilter;
  private Button btnGlideGrayscale;
  private Button btnGlideMask;
  private Button btnGlideGPUToon;
  private Button btnGlideGPUSepia;
  private Button btnGLideGPUContrast;
  private Button btnGLideGPUInvert;
  private Button btnGLideGPUPixel;
  private Button btnGLideGPUSketch;
  private Button btnGLideGPUSwirl;
  private Button btnGLideGPUBrightness;
  private Button btnGLideGPUKuwahara;
  private Button btnGLideGPUVignette;
  private Button btnTest;

  private ImageLoader imageLoader;
  private ImageView imageView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_menu);

    btnTest = (Button) findViewById(R.id.button_test);

    btnGlideCallback = (Button) findViewById(R.id.button_glide_callback);
    btnGlideInto = (Button) findViewById(R.id.button_glide_into);
    btnGlideResource = (Button) findViewById(R.id.button_glide_resource);
    btnGlideCenterCrop = (Button) findViewById(R.id.button_glide_center_crop);
    btnGlideOverride = (Button) findViewById(R.id.button_glide_override);
    btnGlideFitCenter = (Button) findViewById(R.id.button_glide_fit_center);
    btnGlideAnimate = (Button) findViewById(R.id.button_glide_animate);
    btnGlideSizeMulptiplier = (Button) findViewById(R.id.button_glide_size_multiplier);
    btnGlideError = (Button) findViewById(R.id.button_glide_error);

    btnGlideBlur = (Button) findViewById(R.id.button_glide_blur);
    btnGlideCrop = (Button) findViewById(R.id.button_glide_crop);
    btnGlideCropSquare = (Button) findViewById(R.id.button_glide_crop_square);
    btnGlideCropCircle = (Button) findViewById(R.id.button_glide_crop_circle);
    btnGlideColorFilter = (Button) findViewById(R.id.button_glide_color_filter);
    btnGlideColorFilter = (Button) findViewById(R.id.button_glide_color_filter);
    btnGlideGrayscale = (Button) findViewById(R.id.button_glide_grayscale);
    btnGlideMask = (Button) findViewById(R.id.button_glide_mask);

    btnGlideGPUToon = (Button) findViewById(R.id.button_glide_gpu_toon_filter);
    btnGlideGPUSepia = (Button) findViewById(R.id.button_glide_gpu_sepia_filter);
    btnGLideGPUContrast = (Button) findViewById(R.id.button_glide_gpu_contrast_filter);
    btnGLideGPUInvert = (Button) findViewById(R.id.button_glide_gpu_invert_filter);
    btnGLideGPUPixel = (Button) findViewById(R.id.button_glide_gpu_pixel_filter);
    btnGLideGPUSketch = (Button) findViewById(R.id.button_glide_gpu_sketch_filter);
    btnGLideGPUSwirl = (Button) findViewById(R.id.button_glide_gpu_swirl_filter);
    btnGLideGPUBrightness = (Button) findViewById(R.id.button_glide_gpu_brightness_filter);
    btnGLideGPUKuwahara = (Button) findViewById(R.id.button_glide_gpu_kuwahara_filter);
    btnGLideGPUVignette = (Button) findViewById(R.id.button_glide_gpu_vignette_filter);

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

    btnTest.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        setGlideImageLoader();
        String url =
            "http://68.media.tumblr.com/5721ca911597778e6f22c3d401851b20/tumblr_on7vkip12r1s9y3qio3_400.gif";
        //DataGenerator.generateRandomImageUrl();
        //https://upload-assets.vice.com/files/2016/07/06/1467830836GOT_ep_3_A_girl_has_no_name.gif
        imageLoader.load(url)
            .placeholder(R.drawable.ic_loading)
            //.transform(new KuwaharaFilterTransformation(MainActivity.this, 25),
            //    new SepiaFilterTransformation(MainActivity.this),
            //    new GlideCircleTransformation(MainActivity.this, 12,
            //        getResources().getColor(android.R.color.black)))
            .into(new ImageLoaderCallback() {
          @Override public void onSuccess(Bitmap bitmap) {
            //imageView.setImageBitmap(bitmap);

          }

          @Override public void onError(Drawable errorDrawable) {

          }

          @Override public void onLoading() {

          }
        },imageView);

        dialog.show();
      }
    });

    btnGlideInto.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        setGlideImageLoader();

        String url = DataGenerator.generateRandomImageUrl();

        Snackbar.make(btnGlideInto, url, Snackbar.LENGTH_SHORT).show();

        //TODO: Download more transformations: https://github.com/wasabeef/glide-transformations
        imageLoader.load(url)
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.errorimage)
            .into(imageView);
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
            .transform(new CircleTransformation(MainActivity.this, 12,
                getResources().getColor(android.R.color.black)))
            //.transform(new RoundedCornersTransformation(MainActivity.this, 20, 20))

            .into(new ImageLoaderCallback() {
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
            });
      }
    });

    btnGlideResource.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        setGlideImageLoader();

        Snackbar.make(btnGlideCallback, "Image loaded from resource", Snackbar.LENGTH_SHORT).show();

        imageLoader.load(R.drawable.resourceimage)
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.errorimage)
            .into(imageView);
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
            .centerCrop(Boolean.TRUE).into(imageView);
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
            .into(imageView);
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
            .fitCenter(Boolean.TRUE)
            .into(imageView);
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
            .animate(Boolean.TRUE)
            .into(imageView);
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
            .sizeMultiplier(0.3f)

            .into(imageView);
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
            .transform(new CircleTransformation(MainActivity.this, 12,
                getResources().getColor(android.R.color.black)))
            .into(imageView);
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
            .into(imageView);
        dialog.show();
      }
    });

    btnPicassoCallback.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {

        setPicassoImageLoader();
        String url = DataGenerator.generateRandomImageUrl();
        Snackbar.make(btnPicassoCallback, url, Snackbar.LENGTH_SHORT).show();

        //TODO: test it with circleimage
        imageLoader.load(url).into(new ImageLoaderCallback() {
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
        });
        dialog.show();
      }
    });

    btnPicassoResource.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {

        setPicassoImageLoader();

        imageLoader.load(R.drawable.resourceimage)
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.errorimage)
            .into(imageView);
        dialog.show();
      }
    });

    btnPicassoCenterCrop.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {

        setPicassoImageLoader();
        String url = DataGenerator.generateRandomImageUrl();
        Snackbar.make(btnPicassoInto, url, Snackbar.LENGTH_SHORT).show();

        //TODO: test it with circleimage
        imageLoader.load(url).placeholder(R.drawable.ic_loading).error(R.drawable.errorimage)

            .centerCrop(Boolean.TRUE).override(200, 200).into(imageView);
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
            .into(imageView);
        dialog.show();
      }
    });

    btnPicassoFitCenter.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {

        setPicassoImageLoader();
        String url = DataGenerator.generateRandomImageUrl();

        Snackbar.make(btnPicassoFitCenter, url, Snackbar.LENGTH_SHORT).show();
        imageLoader.load(url).placeholder(R.drawable.ic_loading).error(R.drawable.errorimage)

            .fitCenter(Boolean.TRUE).override(500, 500).into(imageView);
        dialog.show();
      }
    });

    btnPicassoRotate.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        setPicassoImageLoader();
        String url = DataGenerator.generateRandomImageUrl();

        Snackbar.make(btnPicassoRotate, url, Snackbar.LENGTH_SHORT).show();
        imageLoader.load(url).placeholder(R.drawable.ic_loading).error(R.drawable.errorimage)

            .rotate(90).into(imageView);
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
            .into(imageView);
        dialog.show();
      }
    });

    btnGlideBlur.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {

        setGlideImageLoader();
        String url = DataGenerator.generateRandomImageUrl();

        Snackbar.make(btnGlideBlur, url, Snackbar.LENGTH_SHORT).show();

        //TODO: Download more transformations: https://github.com/wasabeef/glide-transformations
        imageLoader.load(url)
            .transform(new BlurTransformation(MainActivity.this, 40))
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.errorimage)
            .into(imageView);
        dialog.show();
      }
    });

    btnGlideGPUToon.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        setGlideImageLoader();
        String url = DataGenerator.generateRandomImageUrl();

        Snackbar.make(btnGlideGPUToon, url, Snackbar.LENGTH_SHORT).show();

        imageLoader.load(url)
            .transform(new ToonFilterTransformation(MainActivity.this))
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.errorimage)
            .into(imageView);
        dialog.show();
      }
    });

    btnGlideGPUSepia.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        setGlideImageLoader();
        String url = DataGenerator.generateRandomImageUrl();

        Snackbar.make(btnGlideGPUSepia, url, Snackbar.LENGTH_SHORT).show();

        imageLoader.load(url)
            .transform(new SepiaFilterTransformation(MainActivity.this))
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.errorimage)
            .into(imageView);
        dialog.show();
      }
    });

    btnGLideGPUContrast.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        setGlideImageLoader();
        String url = DataGenerator.generateRandomImageUrl();

        Snackbar.make(btnGLideGPUContrast, url, Snackbar.LENGTH_SHORT).show();

        imageLoader.load(url)
            .transform(new ContrastFilterTransformation(MainActivity.this, 2.0f))
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.errorimage)
            .into(imageView);
        dialog.show();
      }
    });

    btnGLideGPUInvert.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        setGlideImageLoader();
        String url = DataGenerator.generateRandomImageUrl();

        Snackbar.make(btnGLideGPUInvert, url, Snackbar.LENGTH_SHORT).show();

        imageLoader.load(url)
            .transform(new InvertFilterTransformation(MainActivity.this))
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.errorimage)
            .into(imageView);
        dialog.show();
      }
    });

    btnGLideGPUPixel.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        setGlideImageLoader();
        String url = DataGenerator.generateRandomImageUrl();

        Snackbar.make(btnGLideGPUPixel, url, Snackbar.LENGTH_SHORT).show();

        imageLoader.load(url)
            .transform(new PixelationFilterTransformation(MainActivity.this, 20))
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.errorimage)
            .into(imageView);
        dialog.show();
      }
    });

    btnGLideGPUSketch.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        setGlideImageLoader();
        String url = DataGenerator.generateRandomImageUrl();

        Snackbar.make(btnGLideGPUPixel, url, Snackbar.LENGTH_SHORT).show();

        imageLoader.load(url)
            .transform(new SketchFilterTransformation(MainActivity.this))
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.errorimage)
            .into(imageView);
        dialog.show();
      }
    });

    btnGLideGPUSwirl.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        setGlideImageLoader();
        String url = DataGenerator.generateRandomImageUrl();

        Snackbar.make(btnGLideGPUSwirl, url, Snackbar.LENGTH_SHORT).show();

        imageLoader.load(url)
            .transform(new SwirlFilterTransformation(MainActivity.this, 0.5f, 1.0f,
                new PointF(0.5f, 0.5f)))
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.errorimage)
            .into(imageView);
        dialog.show();
      }
    });

    btnGLideGPUBrightness.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        setGlideImageLoader();
        String url = DataGenerator.generateRandomImageUrl();

        Snackbar.make(btnGLideGPUBrightness, url, Snackbar.LENGTH_SHORT).show();

        imageLoader.load(url)
            .transform(new BrightnessFilterTransformation(MainActivity.this, 0.5f))
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.errorimage)
            .into(imageView);
        dialog.show();
      }
    });

    btnGLideGPUKuwahara.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        setGlideImageLoader();
        String url = DataGenerator.generateRandomImageUrl();

        Snackbar.make(btnGLideGPUKuwahara, url, Snackbar.LENGTH_SHORT).show();

        imageLoader.load(url)
            .transform(new KuwaharaFilterTransformation(MainActivity.this, 25))
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.errorimage)
            .into(imageView);
        dialog.show();
      }
    });

    btnGLideGPUVignette.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        setGlideImageLoader();
        String url = DataGenerator.generateRandomImageUrl();

        Snackbar.make(btnGLideGPUVignette, url, Snackbar.LENGTH_SHORT).show();

        imageLoader.load(url)
            .transform(new VignetteFilterTransformation(MainActivity.this, new PointF(0.5f, 0.5f),
                new float[] { 0.0f, 0.0f, 0.0f }, 0f, 0.75f))
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.errorimage)
            .into(imageView);
        dialog.show();
      }
    });

    btnGlideCrop.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        setGlideImageLoader();
        String url = DataGenerator.generateRandomImageUrl();

        Snackbar.make(btnGlideCrop, url, Snackbar.LENGTH_SHORT).show();

        imageLoader.load(url)
            .transform(new CropTransformation(MainActivity.this, 100, 100))
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.errorimage)
            .into(imageView);
        dialog.show();
      }
    });

    btnGlideCropSquare.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        setGlideImageLoader();
        String url = DataGenerator.generateRandomImageUrl();

        Snackbar.make(btnGlideCropSquare, url, Snackbar.LENGTH_SHORT).show();

        imageLoader.load(url)
            .transform(new CropSquareTransformation(MainActivity.this))
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.errorimage)
            .into(imageView);
        dialog.show();
      }
    });

    btnGlideCropCircle.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        setGlideImageLoader();
        String url = DataGenerator.generateRandomImageUrl();

        Snackbar.make(btnGlideCropCircle, url, Snackbar.LENGTH_SHORT).show();

        imageLoader.load(url)
            .transform(new CropCircleTransformation(MainActivity.this))
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.errorimage)
            .into(imageView);
        dialog.show();
      }
    });

    btnGlideColorFilter.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        setGlideImageLoader();
        String url = DataGenerator.generateRandomImageUrl();

        Snackbar.make(btnGlideColorFilter, url, Snackbar.LENGTH_SHORT).show();

        int color = Color.argb(255, 255, 175, 64);

        imageLoader.load(url)
            .transform(new ColorFilterTransformation(MainActivity.this, color))
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.errorimage)
            .into(imageView);
        dialog.show();
      }
    });

    btnGlideGrayscale.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        setGlideImageLoader();
        String url = DataGenerator.generateRandomImageUrl();

        Snackbar.make(btnGlideGrayscale, url, Snackbar.LENGTH_SHORT).show();

        imageLoader.load(url)
            .transform(new GrayscaleTransformation(MainActivity.this))
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.errorimage)
            .into(imageView);
        dialog.show();
      }
    });

    btnGlideMask.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        setGlideImageLoader();
        String url = DataGenerator.generateRandomImageUrl();

        Snackbar.make(btnGlideMask, url, Snackbar.LENGTH_SHORT).show();

        imageLoader.load(url)
            .transform(new MaskTransformation(MainActivity.this, R.drawable.ic_loading))
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.errorimage)
            .into(imageView);
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
