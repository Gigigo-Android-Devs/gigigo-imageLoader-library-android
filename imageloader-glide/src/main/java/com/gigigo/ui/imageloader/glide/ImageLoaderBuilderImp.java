package com.gigigo.ui.imageloader.glide;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;
import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.GifRequestBuilder;
import com.bumptech.glide.GifTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.gigigo.ui.imageloader.ImageLoaderBuilder;
import com.gigigo.ui.imageloader.ImageLoaderCallback;
import java.util.ArrayList;

class ImageLoaderBuilderImp implements ImageLoaderBuilder {

  private final Context context;
  private final RequestManager glide;
  boolean isGifImage = false;

  int resourceId;
  String url;

  private Drawable placeholder;
  private Drawable error;

  private int width;
  private int height;

  private float degrees;

  private float sizeMultiplier;

  private DrawableRequestBuilder<String> thumb;

  DrawableTypeRequest<byte[]> thumbByte;

  private ImageLoaderCallback imageLoaderCallback;

  private ArrayList<Transformation> bitmapTransformation;

  private ImageView imageview;

  private boolean centerCrop;

  private boolean fitCenter;

  private boolean animate;

  GifRequestBuilder mGifRequest = null;

  ImageLoaderBuilderImp(Context context) {
    this.context = context;
    this.glide = Glide.with(context);
  }

  @Override public ImageLoaderBuilder placeholder(int placeholder) {
    placeholder(context.getResources().getDrawable(placeholder));
    return this;
  }

  @Override public ImageLoaderBuilder placeholder(Drawable placeholder) {
    this.placeholder = placeholder;
    return this;
  }

  @Override public ImageLoaderBuilder error(int error) {
    error(context.getResources().getDrawable(error));
    return this;
  }

  @Override public ImageLoaderBuilder error(Drawable error) {
    this.error = error;
    return this;
  }

  @Override public ImageLoaderBuilder override(int width, int height) {
    this.width = width;
    this.height = height;
    return this;
  }

  @Override public ImageLoaderBuilder transform(Object... bitmapTransformations) {
    bitmapTransformation = new ArrayList<>();
    for (int i = 0; i < bitmapTransformations.length; i++) {
      if (bitmapTransformations[i] instanceof Transformation) {
        bitmapTransformation.add((Transformation) bitmapTransformations[i]);
      }
    }
    return this;
  }

  @Override public ImageLoaderBuilder centerCrop(Boolean center) {
    this.centerCrop = center;
    return this;
  }

  @Override public ImageLoaderBuilder fitCenter(Boolean fitCenter) {
    this.fitCenter = fitCenter;
    return this;
  }

  @Override public ImageLoaderBuilder rotate(float degrees) {
    this.degrees = degrees;
    return this;
  }

  @Override public ImageLoaderBuilder animate(Boolean animate) {
    this.animate = animate;
    return this;
  }

  @Override public ImageLoaderBuilder thumbnail(String s) {
    DrawableRequestBuilder<String> thumbRequest = Glide.with(context).load(s);
    this.thumb = thumbRequest;
    return this;
  }

  @Override public ImageLoaderBuilder thumbnailByte(byte[] s) {
    DrawableTypeRequest<byte[]> drawableTypeRequest = Glide.with(context).load(s);

    this.thumbByte = drawableTypeRequest;

    return this;
  }

  @Override public ImageLoaderBuilder sizeMultiplier(float sizeMultiplier) {
    this.sizeMultiplier = sizeMultiplier;
    return this;
  }

  @Override public void into(ImageView imageView) {
    into(null, imageView);
  }

  @Override public void into(ImageLoaderCallback imageLoaderCallback) {
    into(imageLoaderCallback, null);
  }

  public void into(final ImageLoaderCallback imageLoaderCallback, final ImageView imageView) {
    this.imageview = imageView;
    DrawableRequestBuilder drawableRequestBuilder =
        build().diskCacheStrategy(DiskCacheStrategy.NONE);

    drawableRequestBuilder.into(new SimpleTarget<Object>() {

      @Override public void onLoadStarted(Drawable placeholder) {
        super.onLoadStarted(placeholder);
        if (imageLoaderCallback != null) {
          imageLoaderCallback.onLoading();
        }
      }

      @Override public void onLoadFailed(Exception e, Drawable errorDrawable) {
        super.onLoadFailed(e, errorDrawable);
        if (imageLoaderCallback != null) {
          imageLoaderCallback.onError(errorDrawable);
        }
      }

      @Override
      public void onResourceReady(Object resource, GlideAnimation<? super Object> glideAnimation) {
        if (imageLoaderCallback == null) {
          if (resource instanceof GifDrawable) {
            mGifRequest.into(imageView);
          } else if (resource instanceof GlideBitmapDrawable) {
            imageView.setImageDrawable((GlideBitmapDrawable) resource);
          }
        } else if (imageView == null) {
          if (resource instanceof GifDrawable) {
            imageLoaderCallback.onSuccess(((GifDrawable) resource).getFirstFrame());
          } else if (resource instanceof GlideBitmapDrawable) {

            imageLoaderCallback.onSuccess(((GlideBitmapDrawable) resource).getBitmap());
          }
        } else {
          if (resource instanceof GifDrawable) {
            mGifRequest.into(imageView);
            imageLoaderCallback.onSuccess(((GifDrawable) resource).getFirstFrame());
          } else if (resource instanceof GlideBitmapDrawable) {
            imageView.setImageBitmap(((GlideBitmapDrawable) resource).getBitmap());
            imageLoaderCallback.onSuccess(((GlideBitmapDrawable) resource).getBitmap());
          }
        }
      }
    });
  }

  private DrawableRequestBuilder build() {
    DrawableTypeRequest drawableTypeRequest;

    glide.resumeRequestsRecursive();

    if (!TextUtils.isEmpty(url)) {
      drawableTypeRequest = glide.load(url);
    } else if (resourceId != 0) {
      drawableTypeRequest = glide.load(resourceId);
    } else {
      return null;
    }
    GifTypeRequest gifTypeRequest = drawableTypeRequest.asGif();
    mGifRequest = gifTypeRequest.clone();

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
      Transformation[] bitmapTransformationsAux = new Transformation[bitmapTransformation.size()];
      for (int i = 0; i < bitmapTransformation.size(); i++) {
        bitmapTransformationsAux[i] = bitmapTransformation.get(i);
      }
      drawableRequestBuilder = drawableRequestBuilder.bitmapTransform(bitmapTransformationsAux);
    }
    if (centerCrop) {
      drawableRequestBuilder = drawableRequestBuilder.centerCrop();
    }
    if (fitCenter) {
      drawableRequestBuilder = drawableRequestBuilder.fitCenter();
    }
    if (animate) {
      drawableRequestBuilder = drawableRequestBuilder.animate(android.R.anim.slide_in_left);
    }
    if (sizeMultiplier > 0) {
      drawableRequestBuilder = drawableRequestBuilder.sizeMultiplier(sizeMultiplier);
    }
    if (thumb != null) {
      drawableRequestBuilder = drawableRequestBuilder.thumbnail(thumb);
    }

    if (thumbByte != null) {
      drawableRequestBuilder = drawableRequestBuilder.thumbnail(thumbByte);
    }

    return drawableRequestBuilder;
  }

  @Override public void clearPreviousData() {
    resourceId = 0;
    url = null;

    placeholder = null;
    error = null;

    width = 0;
    height = 0;

    imageLoaderCallback = null;

    bitmapTransformation = null;

    imageview = null;

    centerCrop = false;

    fitCenter = false;

    animate = false;

    degrees = 0;

    sizeMultiplier = 0;

    thumb = null;

    thumbByte = null;
  }
}
