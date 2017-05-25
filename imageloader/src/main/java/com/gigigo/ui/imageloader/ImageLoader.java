package com.gigigo.ui.imageloader;

import android.view.View;

/**
 * Created by rui.alonso on 20/9/16.
 */
public interface ImageLoader {

  ImageLoaderBuilder load(int resourceId);

  ImageLoaderBuilder load(String url);


  void pauseRequests();

  void resumeRequests();

  void clear(View view);

}
