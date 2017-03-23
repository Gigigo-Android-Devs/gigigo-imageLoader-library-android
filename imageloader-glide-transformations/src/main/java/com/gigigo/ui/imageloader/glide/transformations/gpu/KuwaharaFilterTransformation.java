package com.gigigo.ui.imageloader.glide.transformations.gpu;

import android.content.Context;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import jp.co.cyberagent.android.gpuimage.GPUImageKuwaharaFilter;

/**
 * Kuwahara all the colors in the image.
 *
 * The radius to sample from when creating the brush-stroke effect, with a default of 25.
 * The larger the radius, the slower the filter.
 */
public class KuwaharaFilterTransformation extends GPUFilterTransformation {

  private int mRadius;

  public KuwaharaFilterTransformation(Context context) {
    this(context, Glide.get(context).getBitmapPool());
  }

  public KuwaharaFilterTransformation(Context context, BitmapPool pool) {
    this(context, pool, 25);
  }

  public KuwaharaFilterTransformation(Context context, int radius) {
    this(context, Glide.get(context).getBitmapPool(), radius);
  }

  public KuwaharaFilterTransformation(Context context, BitmapPool pool, int radius) {
    super(context, pool, new GPUImageKuwaharaFilter());
    mRadius = radius;
    GPUImageKuwaharaFilter filter = getFilter();
    filter.setRadius(mRadius);
  }

  @Override public String getId() {
    return "KuwaharaFilterTransformation(radius=" + mRadius + ")";
  }
}