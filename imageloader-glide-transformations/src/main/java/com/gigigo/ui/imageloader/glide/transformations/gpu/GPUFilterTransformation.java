package com.gigigo.ui.imageloader.glide.transformations.gpu;

import android.content.Context;
import android.graphics.Bitmap;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;
import jp.co.cyberagent.android.gpuimage.GPUImage;
import jp.co.cyberagent.android.gpuimage.GPUImageFilter;

public class GPUFilterTransformation implements Transformation<Bitmap> {

  private Context mContext;
  private BitmapPool mBitmapPool;

  private GPUImageFilter mFilter;

  public GPUFilterTransformation(Context context, GPUImageFilter filter) {
    this(context, Glide.get(context).getBitmapPool(), filter);
  }

  public GPUFilterTransformation(Context context, BitmapPool pool, GPUImageFilter filter) {
    mContext = context.getApplicationContext();
    mBitmapPool = pool;
    mFilter = filter;
  }

  @Override
  public Resource<Bitmap> transform(Resource<Bitmap> resource, int outWidth, int outHeight) {
    Bitmap source = resource.get();
    GPUImage gpuImage = new GPUImage(mContext);
    gpuImage.setImage(source);
    gpuImage.setFilter(mFilter);

    Bitmap bitmap = gpuImage.getBitmapWithFilterApplied();

    return BitmapResource.obtain(bitmap, mBitmapPool);
  }

  @Override public String getId() {
    return getClass().getSimpleName();
  }

  @SuppressWarnings("unchecked") public <T> T getFilter() {
    return (T) mFilter;
  }
}