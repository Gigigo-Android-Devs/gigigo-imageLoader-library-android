package com.gigigo.ui.imageloader;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public interface ImageLoaderCallback {

  void onSuccess(Bitmap bitmap);
  void onError(Drawable errorDrawable);
  void onLoading();
}
