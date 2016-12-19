package com.gigigo.ui.imageloader.picasso;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;

/**
 * Created by rui.alonso on 20/9/16.
 */
public class PicassoCircleTransformation implements com.squareup.picasso.Transformation {
  private final int radius;
  private final int borderId;
  private final int colorId;

  public PicassoCircleTransformation(int radius, int borderId, int colorId) {
    this.radius = radius;
    this.borderId = borderId;
    this.colorId = colorId;
  }

  @Override public Bitmap transform(final Bitmap source) {
    final Paint paint = new Paint();
    paint.setAntiAlias(true);
    paint.setShader(new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));

    Bitmap output =
        Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(output);
    canvas.drawRoundRect(
        new RectF(borderId, borderId, source.getWidth() - borderId, source.getHeight() - borderId),
        radius, radius, paint);

    if (source != output) {
      source.recycle();
    }

    Paint paint1 = new Paint();
    paint1.setColor(colorId);
    paint1.setStyle(Paint.Style.STROKE);
    paint1.setAntiAlias(true);
    paint1.setStrokeWidth(2);
    canvas.drawCircle((source.getWidth() - borderId) / 2, (source.getHeight() - borderId) / 2,
        radius - 2, paint1);

    return output;
  }

  @Override public String key() {
    return "rounded";
  }
}
