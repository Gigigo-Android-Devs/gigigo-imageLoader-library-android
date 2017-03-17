package com.example.imageloader_glide_blur;

import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import jp.wasabeef.blurry.Blurry;

/**
 * Created by pablo.rojas on 17/3/17.
 */

public class BlurManagerImp implements BlurManager {

  @Override public BlurManager blurWithRunnable(final View view, final ImageView image, final int radius) {
    view.post(new Runnable() {
      @Override public void run() {
        view.getHeight(); //height is ready
        Log.v("height", "---after--" + view.getHeight());
        Blurry.with(view.getContext())
            .radius(radius)
            .async()
            .capture(image)
            .into(image);
      }
    });

    return this;
  }

  @Override public BlurManager blurWithTreeObserver(final View view, final ImageView image, final int radius) {
    view.getViewTreeObserver()
        .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
          @Override public void onGlobalLayout() {
            view.getHeight(); //height is ready
            Blurry.with(view.getContext())
                .radius(radius)
                .async()
                .capture(image)
                .into(image);
          }
        });

    return this;
  }
}
