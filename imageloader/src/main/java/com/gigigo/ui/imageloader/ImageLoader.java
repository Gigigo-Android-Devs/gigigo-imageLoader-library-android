package com.gigigo.ui.imageloader;

/**
 * Created by rui.alonso on 20/9/16.
 */
public interface ImageLoader {

  ImageLoaderBuilder load(int resourceId);

  ImageLoaderBuilder load(String url);

}
