package com.gigigo.ui.imageloader_picasso;

import android.content.Context;
import android.widget.ImageView;
import com.gigigo.ui.imageloader.ImageLoader;
import com.gigigo.ui.imageloader.ImageLoaderCallback;
import com.google.gson.Gson;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import java.io.IOException;
import java.util.Map;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PicassoImageLoaderImp implements ImageLoader {

  private final Picasso picasso;
  private PicassoCircleTransformation circleTransformation;

  public PicassoImageLoaderImp(Context context) {
    this.picasso = Picasso.with(context);
  }

  public PicassoImageLoaderImp(Context context, PicassoCircleTransformation circleTransformation) {
    this.picasso = Picasso.with(context);
    this.circleTransformation = circleTransformation;
  }

  @Override public void load(int resourceId, ImageView imageView) {
    picasso.load(resourceId).placeholder(resourceId).error(resourceId).into(imageView);
  }

  @Override public void load(String url, ImageView imageView) {
    picasso.load(url).into(imageView);
  }

  @Override public void load(String url, ImageView imageView, int placeholder) {
    picasso.load(url).placeholder(placeholder).error(placeholder).into(imageView);
  }

  @Override
  public void load(String url, ImageView imageView, int placeholder, int width, int height) {
    picasso.load(url)
        .placeholder(placeholder)
        .error(placeholder)
        .resize(width, height)
        .into(imageView);
  }

  @Override public void load(String url, ImageView imageView, int placeholder,
      final ImageLoaderCallback imageLoaderCallback) {
    picasso.load(url).placeholder(placeholder).error(placeholder).into(imageView, new Callback() {
      @Override public void onSuccess() {
        imageLoaderCallback.onFinish(true);
      }

      @Override public void onError() {
        imageLoaderCallback.onFinish(false);
      }
    });
  }

  @Override
  public void load(String url, ImageView imageView, int placeholder, int width, int height,
      final ImageLoaderCallback imageLoaderCallback) {
    picasso.load(url)
        .placeholder(placeholder)
        .error(placeholder)
        .resize(width, height)
        .into(imageView, new Callback() {
          @Override public void onSuccess() {
            imageLoaderCallback.onFinish(true);
          }

          @Override public void onError() {
            imageLoaderCallback.onFinish(false);
          }
        });
  }

  @Override public void loadCircleImage(int resourceId, ImageView imageView) {
    if (circleTransformation != null) {
      picasso.load(resourceId).transform(circleTransformation).into(imageView);
    }
    else {
      picasso.load(resourceId).into(imageView);
    }
  }

  @Override public void loadCircleImage(String url, ImageView imageView) {
    if (circleTransformation != null) {
      picasso.load(url).transform(circleTransformation).into(imageView);
    }
    else {
      picasso.load(url).into(imageView);
    }
  }

  @Override public void loadCircleImage(String url, ImageView imageView, int placeholder) {
    if (circleTransformation != null) {
      picasso.load(url).placeholder(placeholder).transform(circleTransformation).into(imageView);
    }
    else {
      picasso.load(url).placeholder(placeholder).into(imageView);
    }
  }

  @Override
  public void loadCircleImage(String url, final Map<String, String> params, ImageView imageView,
      int placeholder) {
    OkHttpClient okHttpClient = new OkHttpClient();
    okHttpClient.interceptors().add(new Interceptor() {
      @Override public Response intercept(Interceptor.Chain chain) throws IOException {
        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        String requestJsonBody = new Gson().toJson(params);

        RequestBody body = RequestBody.create(JSON, requestJsonBody);
        final Request original = chain.request();
        final Request.Builder requestBuilder = original.newBuilder().post(body);
        return chain.proceed(requestBuilder.build());
      }
    });

    if (circleTransformation != null) {
      picasso.load(url).transform(circleTransformation).into(imageView);
    }
    else {
      picasso.load(url).transform(circleTransformation).into(imageView);
    }
  }
}
