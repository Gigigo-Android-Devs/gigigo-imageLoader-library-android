package com.gigigo.ui.imageloader.glide.transformations.gpu;

import android.content.Context;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import jp.co.cyberagent.android.gpuimage.GPUImagePixelationFilter;

/**
 * Applies a Pixelation effect to the image.
 *
 * The pixel with a default of 10.0.
 */
public class PixelationFilterTransformation extends GPUFilterTransformation {

  private float mPixel;

  public PixelationFilterTransformation(Context context) {
    this(context, Glide.get(context).getBitmapPool());
  }

  public PixelationFilterTransformation(Context context, BitmapPool pool) {
    this(context, pool, 10f);
  }

  public PixelationFilterTransformation(Context context, float pixel) {
    this(context, Glide.get(context).getBitmapPool(), pixel);
  }

  public PixelationFilterTransformation(Context context, BitmapPool pool, float pixel) {
    super(context, pool, new GPUImagePixelationFilter());
    mPixel = pixel;
    GPUImagePixelationFilter filter = getFilter();
    filter.setPixel(mPixel);
  }

  @Override public String getId() {
    return "PixelationFilterTransformation(pixel=" + mPixel + ")";
  }
}