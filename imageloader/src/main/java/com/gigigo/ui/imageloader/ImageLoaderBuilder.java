package com.gigigo.ui.imageloader;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public interface ImageLoaderBuilder {

  ImageLoaderBuilder into(ImageView imageView);

  ImageLoaderBuilder placeholder(int placeholder);

  ImageLoaderBuilder placeholder(Drawable placeholder);

  ImageLoaderBuilder error(int error);

  ImageLoaderBuilder error(Drawable error);

  ImageLoaderBuilder override(int width, int height);

  ImageLoaderBuilder transform(Object bitmapTransformation);

  ImageLoaderBuilder loaderCallback(ImageLoaderCallback imageLoaderCallback);

  ImageLoaderBuilder centerCrop(Boolean centerCrop);

  void build();

  void clearPreviousData();
}
