package com.gigigo.ui.imageloader.glide;

import android.content.Context;
import android.view.View;
import com.bumptech.glide.Glide;
import com.gigigo.ui.imageloader.ImageLoader;
import com.gigigo.ui.imageloader.ImageLoaderBuilder;

public class GlideImageLoaderImp extends ImageLoaderBuilderImp implements ImageLoader {
  Context context;

  public GlideImageLoaderImp(Context context) {
    super(context);
    this.context = context;
  }

  @Override public ImageLoaderBuilder load(int resourceId) {
    clearPreviousData();
    this.isGifImage = false;
    this.resourceId = resourceId;
    return this;
  }

  @Override public ImageLoaderBuilder load(String url) {
    clearPreviousData();
    this.isGifImage = false;
    this.url = url;
    return this;
  }

  @Override public void pauseRequests() {
    Glide.with(context).pauseRequestsRecursive();
  }

  @Override public void resumeRequests() {
    Glide.with(context).resumeRequests();
  }

  @Override public void clear(View view){
    Glide.clear(view);
  }
}
