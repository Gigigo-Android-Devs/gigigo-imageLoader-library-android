package com.gigigo.ui.imageloader.glide.transformations.gpu;

import android.content.Context;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import jp.co.cyberagent.android.gpuimage.GPUImageContrastFilter;

/**
 * contrast value ranges from 0.0 to 4.0, with 1.0 as the normal level
 */
public class ContrastFilterTransformation extends GPUFilterTransformation {

  private float mContrast;

  public ContrastFilterTransformation(Context context) {
    this(context, Glide.get(context).getBitmapPool());
  }

  public ContrastFilterTransformation(Context context, BitmapPool pool) {
    this(context, pool, 1.0f);
  }

  public ContrastFilterTransformation(Context context, float contrast) {
    this(context, Glide.get(context).getBitmapPool(), contrast);
  }

  public ContrastFilterTransformation(Context context, BitmapPool pool, float contrast) {
    super(context, pool, new GPUImageContrastFilter());
    mContrast = contrast;
    GPUImageContrastFilter filter = getFilter();
    filter.setContrast(mContrast);
  }

  @Override public String getId() {
    return "ContrastFilterTransformation(contrast=" + mContrast + ")";
  }
}