package com.gigigo.ui.imageloader;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.bumptech.glide.DrawableRequestBuilder;

public interface ImageLoaderBuilder {

  void into(ImageView imageView);

  void into(ImageLoaderCallback imageLoaderCallback);

  void into(ImageLoaderCallback imageLoaderCallback, ImageView imageView);

  void clearPreviousData();

  ImageLoaderBuilder placeholder(int placeholder);

  ImageLoaderBuilder placeholder(Drawable placeholder);

  ImageLoaderBuilder error(int error);

  ImageLoaderBuilder error(Drawable error);

  ImageLoaderBuilder override(int width, int height);

  ImageLoaderBuilder transform(Object... bitmapTransformation);

  ImageLoaderBuilder centerCrop(Boolean centerCrop);

  ImageLoaderBuilder fitCenter(Boolean fitCenter);

  ImageLoaderBuilder rotate(float degrees);

  ImageLoaderBuilder animate(Boolean animate);

  ImageLoaderBuilder sizeMultiplier(float sizeMultiplier);

  ImageLoaderBuilder thumbnail(DrawableRequestBuilder<String> thumb);

  ImageLoaderBuilder thumbnail2(String s);
}
