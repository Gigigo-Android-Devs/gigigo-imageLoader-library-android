package com.example.imageloader_glide_blur;

import android.view.View;
import android.widget.ImageView;

/**
 * Created by pablo.rojas on 17/3/17.
 */

public interface BlurManager {

  BlurManager blurWithRunnable(View view, ImageView image, int radius);

  BlurManager blurWithTreeObserver(View view, ImageView image, int radius);

}
