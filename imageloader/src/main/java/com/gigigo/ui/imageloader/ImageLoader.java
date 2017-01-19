package com.gigigo.ui.imageloader;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import java.util.Map;

/**
 * Created by rui.alonso on 20/9/16.
 */
public interface ImageLoader {

  ImageLoaderBuilder load(int resourceId);

  ImageLoaderBuilder load(String url);
}
