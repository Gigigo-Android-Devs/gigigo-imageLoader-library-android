package com.gigigo.ui.imageloader.glide;

import android.content.Context;
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


}
