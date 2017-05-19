package com.gigigo.ui.imageloader.glide;

import android.app.Activity;
import android.content.Context;
import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.gigigo.ui.imageloader.ImageLoader;
import com.gigigo.ui.imageloader.ImageLoaderBuilder;

public class GlideImageLoaderImp extends ImageLoaderBuilderImp implements ImageLoader {


  public GlideImageLoaderImp(Context context) {
    super(context);

  }

  @Override public ImageLoaderBuilder load(int resourceId) {
    clearPreviousData();
    this.isGifImage=false;
    this.resourceId = resourceId;
    return this;
  }

  @Override public ImageLoaderBuilder load(String url) {
    clearPreviousData();
    this.isGifImage=false;
    this.url = url;
    return this;
  }

  @Override public DrawableRequestBuilder<String> getThumbnail(Activity a, String s) {
    DrawableRequestBuilder<String> thumbRequest = Glide.with(a)
        .load("https://dummyimage.com/200x200/333/fff.png&text=cats");
    return thumbRequest;
  }
}
