package com.gigigo.ui.imageloader.glide.transformations.gpu;

import android.content.Context;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import jp.co.cyberagent.android.gpuimage.GPUImageSepiaFilter;

/**
 * Applies a simple sepia effect.
 *
 * The intensity with a default of 1.0.
 */
public class SepiaFilterTransformation extends GPUFilterTransformation {

  private float mIntensity;

  public SepiaFilterTransformation(Context context) {
    this(context, Glide.get(context).getBitmapPool());
  }

  public SepiaFilterTransformation(Context context, BitmapPool pool) {
    this(context, pool, 1.0f);
  }

  public SepiaFilterTransformation(Context context, float intensity) {
    this(context, Glide.get(context).getBitmapPool(), intensity);
  }

  public SepiaFilterTransformation(Context context, BitmapPool pool, float intensity) {
    super(context, pool, new GPUImageSepiaFilter());
    mIntensity = intensity;
    GPUImageSepiaFilter filter = getFilter();
    filter.setIntensity(mIntensity);
  }

  @Override public String getId() {
    return "SepiaFilterTransformation(intensity=" + mIntensity + ")";
  }
}