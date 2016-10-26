package com.gigigo.ui.imageloader_glide;

import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.integration.okhttp.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.signature.StringSignature;
import com.gigigo.ui.imageloader.ImageLoader;
import com.gigigo.ui.imageloader.ImageLoaderCallback;
import com.google.gson.Gson;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.UUID;

public class GlideImageLoaderImp implements ImageLoader {

  private final RequestManager glide;
  private GlideCircleTransformation circleTransformation;

  public GlideImageLoaderImp(RequestManager glide, GlideCircleTransformation circleTransformation) {
    this.glide = glide;
    this.circleTransformation = circleTransformation;
  }

  public GlideImageLoaderImp(RequestManager glide) {
    this.glide = glide;
  }

  @Override public void load(int resourceId, ImageView imageView) {
    glide.load(resourceId).placeholder(resourceId).error(resourceId).into(imageView);
  }

  @Override public void load(String url, ImageView imageView) {
    glide.load(url).into(imageView);
  }

  @Override public void load(String url, ImageView imageView, int placeholder) {
    glide.load(url).placeholder(placeholder).error(placeholder).into(imageView);
  }

  @Override
  public void load(String url, ImageView imageView, int placeholder, int width, int height) {
    glide.load(url)
        .placeholder(placeholder)
        .error(placeholder)
        .override(width, height)
        .into(imageView);
  }

  @Override public void load(String url, final ImageView imageView, int placeholder,
      final ImageLoaderCallback imageLoaderCallback) {

    glide.load(url)
        .placeholder(placeholder)
        .error(placeholder)
        .listener(new RequestListener<String, GlideDrawable>() {
          @Override
          public boolean onException(Exception e, String model, Target<GlideDrawable> target,
              boolean isFirstResource) {
            imageLoaderCallback.onFinish(false);
            return true;
          }

          @Override public boolean onResourceReady(GlideDrawable resource, String model,
              Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
            imageView.setImageDrawable(resource);
            imageLoaderCallback.onFinish(true);
            return true;
          }
        })
        .into(imageView);
  }

  @Override
  public void load(String url, final ImageView imageView, int placeholder, int width, int height,
      final ImageLoaderCallback imageLoaderCallback) {

    glide.load(url)
        .placeholder(placeholder)
        .error(placeholder)
        .override(width, height)
        .listener(new RequestListener<String, GlideDrawable>() {
          @Override
          public boolean onException(Exception e, String model, Target<GlideDrawable> target,
              boolean isFirstResource) {
            imageLoaderCallback.onFinish(false);
            return true;
          }

          @Override public boolean onResourceReady(GlideDrawable resource, String model,
              Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
            imageView.setImageDrawable(resource);
            imageLoaderCallback.onFinish(true);
            return true;
          }
        })
        .into(imageView);
  }

  @Override public void loadCircleImage(int resourceId, ImageView imageView) {
    if (circleTransformation != null) {
      glide.load(resourceId).transform(circleTransformation).into(imageView);
    } else {
      load(resourceId, imageView);
    }
  }

  @Override public void loadCircleImage(String url, ImageView imageView) {
    if (circleTransformation != null) {
      glide.load(url).transform(circleTransformation).into(imageView);
    } else {
      load(url, imageView);
    }
  }

  @Override public void loadCircleImage(String url, ImageView imageView, int placeholder) {
    if (circleTransformation != null) {
      glide.load(url)
          .placeholder(placeholder)
          .error(placeholder)
          .transform(circleTransformation)
          .into(imageView);
    } else {
      load(url, imageView, placeholder);
    }
  }

  @Override
  public void loadCircleImage(String url, final Map<String, String> params, ImageView imageView,
      int placeholder) {

    OkHttpClient okHttpClient = new OkHttpClient();
    okHttpClient.interceptors().add(new Interceptor() {
      @Override public Response intercept(Chain chain) throws IOException {
        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        String requestJsonBody = new Gson().toJson(params);

        RequestBody body = RequestBody.create(JSON, requestJsonBody);
        final Request original = chain.request();
        final Request.Builder requestBuilder = original.newBuilder().post(body);
        return chain.proceed(requestBuilder.build());
      }
    });

    Glide.get(imageView.getContext())
        .register(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(okHttpClient));

    if (circleTransformation != null) {
      glide.load(url)
          .placeholder(placeholder)
          .signature(new StringSignature(UUID.randomUUID().toString()))
          .error(placeholder)
          .transform(circleTransformation)
          .into(imageView);
    } else {
      glide.load(url)
          .placeholder(placeholder)
          .signature(new StringSignature(UUID.randomUUID().toString()))
          .error(placeholder)
          .into(imageView);
    }
  }
}
