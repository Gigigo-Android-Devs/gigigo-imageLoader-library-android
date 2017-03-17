package com.gigigo.imageloader_demoapp;

import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import com.example.imageloader_glide_blur.BlurManager;
import com.example.imageloader_glide_blur.BlurManagerImp;
import jp.wasabeef.blurry.Blurry;

public class BlurActivity extends AppCompatActivity {
  private View view;
  private ImageView imageBackground;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_blur);

    view = findViewById(R.id.blurView);
    imageBackground = (ImageView) findViewById(R.id.imageBackground);

    //blurWithRunnable();
    //blurWithTreeObserver();
    BlurManagerImp bm = new BlurManagerImp();
    bm.blurWithRunnable(view,imageBackground,50);

  }



}