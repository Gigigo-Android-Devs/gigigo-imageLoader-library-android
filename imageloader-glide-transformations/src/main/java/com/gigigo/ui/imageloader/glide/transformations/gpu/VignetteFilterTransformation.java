package com.gigigo.ui.imageloader.glide.transformations.gpu;


import android.content.Context;
import android.graphics.PointF;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import java.util.Arrays;
import jp.co.cyberagent.android.gpuimage.GPUImageVignetteFilter;

/**
 * Performs a vignetting effect, fading out the image at the edges
 * The directional intensity of the vignetting,
 * with a default of x = 0.5, y = 0.5, start = 0, end = 0.75
 */
public class VignetteFilterTransformation extends GPUFilterTransformation {

  private PointF mCenter;
  private float[] mVignetteColor;
  private float mVignetteStart;
  private float mVignetteEnd;

  public VignetteFilterTransformation(Context context) {
    this(context, Glide.get(context).getBitmapPool());
  }

  public VignetteFilterTransformation(Context context, BitmapPool pool) {
    this(context, pool, new PointF(0.5f, 0.5f), new float[] { 0.0f, 0.0f, 0.0f }, 0.0f, 0.75f);
  }

  public VignetteFilterTransformation(Context context, PointF center, float[] color, float start,
      float end) {
    this(context, Glide.get(context).getBitmapPool(), center, color, start, end);
  }

  public VignetteFilterTransformation(Context context, BitmapPool pool, PointF center,
      float[] color, float start, float end) {
    super(context, pool, new GPUImageVignetteFilter());
    mCenter = center;
    mVignetteColor = color;
    mVignetteStart = start;
    mVignetteEnd = end;
    GPUImageVignetteFilter filter = getFilter();
    filter.setVignetteCenter(mCenter);
    filter.setVignetteColor(mVignetteColor);
    filter.setVignetteStart(mVignetteStart);
    filter.setVignetteEnd(mVignetteEnd);
  }

  @Override public String getId() {
    return "VignetteFilterTransformation(center=" + mCenter.toString() +
        ",color=" + Arrays.toString(mVignetteColor) +
        ",start=" + mVignetteStart + ",end=" + mVignetteEnd + ")";
  }
}