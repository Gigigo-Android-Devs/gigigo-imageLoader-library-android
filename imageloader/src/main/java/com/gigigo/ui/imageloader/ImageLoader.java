package com.gigigo.ui.imageloader;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import java.util.Map;

/**
 * Created by rui.alonso on 20/9/16.
 */
public interface ImageLoader {

  ImageLoader load(int resourceId);

  ImageLoader load(String url);

  ImageLoader into(ImageView imageView);

  ImageLoader placeholder(int placeholder);

  ImageLoader placeholder(Drawable placeholder);

  ImageLoader error(int error);

  ImageLoader error(Drawable error);

  ImageLoader override(int width, int height);

  ImageLoader loaderCallback(ImageLoaderCallback imageLoaderCallback);

  void build();
}
