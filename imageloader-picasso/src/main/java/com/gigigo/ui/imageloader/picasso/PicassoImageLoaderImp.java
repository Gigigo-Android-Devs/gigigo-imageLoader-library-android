package com.gigigo.ui.imageloader.picasso;

import android.content.Context;
import com.gigigo.ui.imageloader.ImageLoader;
import com.gigigo.ui.imageloader.ImageLoaderBuilder;

public class PicassoImageLoaderImp extends ImageLoaderBuilderImp implements ImageLoader {


  public PicassoImageLoaderImp(Context context) {
    super(context);
  }

  @Override public ImageLoaderBuilder load(int resourceId) {
    clearPreviousData();
    this.resourceId = resourceId;
    return this;
  }

  @Override public ImageLoaderBuilder load(String url) {
    clearPreviousData();
    this.url = url;
    return this;
  }

}
