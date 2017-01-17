package com.gigigo.ui.imageloader.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;
import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.gigigo.ui.imageloader.ImageLoader;
import com.gigigo.ui.imageloader.ImageLoaderCallback;

public class GlideImageLoaderImp implements ImageLoader {

  private final Context context;
  private final RequestManager glide;

  private int resourceId;
  private String url;

  private Drawable placeholder;
  private Drawable error;

  private int width;
  private int height;

  private ImageLoaderCallback imageLoaderCallback;

  private BitmapTransformation bitmapTransformation;

  private ImageView imageview;

  public GlideImageLoaderImp(Context context) {
    this.context = context;
    this.glide = Glide.with(context);
  }

  @Override public ImageLoader load(int resourceId) {
    this.resourceId = resourceId;
    return this;
  }

  @Override public ImageLoader load(String url) {
    this.url = url;
    return this;
  }

  @Override public ImageLoader into(ImageView imageView) {
    this.imageview = imageView;
    return this;
  }

  @Override public ImageLoader placeholder(int placeholder) {
    placeholder(context.getResources().getDrawable(placeholder));
    return this;
  }

  @Override public ImageLoader placeholder(Drawable placeholder) {
    this.placeholder = placeholder;
    return this;
  }

  @Override public ImageLoader error(int error) {
    error(context.getResources().getDrawable(error));
    return this;
  }

  @Override public ImageLoader error(Drawable error) {
    this.error = error;
    return this;
  }

  @Override public ImageLoader override(int width, int height) {
    this.width = width;
    this.height = height;
    return this;
  }

  @Override public ImageLoader transform(Object bitmapTransformation) {
    if (bitmapTransformation instanceof BitmapTransformation) {
      this.bitmapTransformation = (BitmapTransformation) bitmapTransformation;
    }
    return this;
  }

  @Override public ImageLoader loaderCallback(ImageLoaderCallback imageLoaderCallback) {
    this.imageLoaderCallback = imageLoaderCallback;
    return this;
  }

  @Override public void build() {
    DrawableTypeRequest drawableTypeRequest;

    if (!TextUtils.isEmpty(url)) {
      drawableTypeRequest = glide.load(url);
    } else if (resourceId != 0) {
      drawableTypeRequest = glide.load(resourceId);
    } else {
      return;
    }

    DrawableRequestBuilder drawableRequestBuilder = drawableTypeRequest.clone();

    if (placeholder != null) {
      drawableRequestBuilder = drawableRequestBuilder.placeholder(placeholder);
    }

    if (error != null) {
      drawableRequestBuilder = drawableRequestBuilder.error(error);
    }

    if (width > 0 && height > 0) {
      drawableRequestBuilder = drawableRequestBuilder.override(width, height);
    }

    if (bitmapTransformation != null) {
      drawableRequestBuilder = drawableRequestBuilder.transform(bitmapTransformation);
    }

    if (imageview != null) {
      drawableRequestBuilder.into(imageview);
      
    } else if (imageLoaderCallback != null) {

      drawableRequestBuilder.into(new SimpleTarget<GlideBitmapDrawable>() {

        @Override public void onLoadStarted(Drawable placeholder) {
          super.onLoadStarted(placeholder);
          imageLoaderCallback.onLoading();
        }

        @Override public void onLoadFailed(Exception e, Drawable errorDrawable) {
          super.onLoadFailed(e, errorDrawable);
          imageLoaderCallback.onError(errorDrawable);
        }

        @Override public void onResourceReady(GlideBitmapDrawable resource,
            GlideAnimation<? super GlideBitmapDrawable> glideAnimation) {
          imageLoaderCallback.onSuccess(resource.getBitmap());
        }
      });
    }
  }
}
