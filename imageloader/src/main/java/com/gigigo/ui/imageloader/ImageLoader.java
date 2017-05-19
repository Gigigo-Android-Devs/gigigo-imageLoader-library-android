package com.gigigo.ui.imageloader;

import android.app.Activity;
import com.bumptech.glide.DrawableRequestBuilder;

/**
 * Created by rui.alonso on 20/9/16.
 */
public interface ImageLoader {

  ImageLoaderBuilder load(int resourceId);

  ImageLoaderBuilder load(String url);

  DrawableRequestBuilder<String> getThumbnail(Activity a, String s);
}
