package com.gigigo.ui.imageloader.picasso;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;
import com.gigigo.ui.imageloader.ImageLoader;
import com.gigigo.ui.imageloader.ImageLoaderBuilder;
import com.gigigo.ui.imageloader.ImageLoaderCallback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Target;
import com.squareup.picasso.Transformation;

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
