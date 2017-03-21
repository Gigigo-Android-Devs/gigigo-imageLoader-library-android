package com.gigigo.ui.imageloader.glide.transformations.gpu;

import android.content.Context;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import jp.co.cyberagent.android.gpuimage.GPUImageColorInvertFilter;

/**
 * Invert all the colors in the image.
 */
public class InvertFilterTransformation extends GPUFilterTransformation {

  public InvertFilterTransformation(Context context) {
    this(context, Glide.get(context).getBitmapPool());
  }

  public InvertFilterTransformation(Context context, BitmapPool pool) {
    super(context, pool, new GPUImageColorInvertFilter());
  }

  @Override public String getId() {
    return "InvertFilterTransformation()";
  }
}