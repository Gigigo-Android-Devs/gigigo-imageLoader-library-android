package com.gigigo.ui.imageloader.glide.transformations;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.ColorInt;
import android.support.annotation.DimenRes;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

public class GlideCircleTransformation implements Transformation<Bitmap> {

  private final BitmapPool pool;
  private final float dimenBorder;
  private final int color;

  public GlideCircleTransformation(Context context, float dimenBorder, int color) {
    this(Glide.get(context).getBitmapPool(), dimenBorder, color);
  }

  public GlideCircleTransformation(BitmapPool pool, float dimenBorder, int color) {
    this.pool = pool;
    this.dimenBorder = dimenBorder;
    this.color = color;
  }

  /**
   * Crop circular. El bitmap origen ha de ser cuadrado.
   */
  public static Bitmap cropToCircle(BitmapPool pool, Bitmap bitmap) {

    if (bitmap == null) return null;

    int size = Math.min(bitmap.getWidth(), bitmap.getHeight());
    int x = (bitmap.getWidth() - size) / 2;
    int y = (bitmap.getHeight() - size) / 2;

    Bitmap squared = Bitmap.createBitmap(bitmap, x, y, size, size);

    Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
    if (result == null) {
      result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
    }
    //todo falta hacerle un blur al borde
    Canvas canvas = new Canvas(result);
    Paint paint = new Paint();
    paint.setShader(
        new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
    paint.setAntiAlias(true);
           /*  paint.setFilterBitmap(true);
            paint.setDither(true);*/
    float r = size / 2f;
    canvas.drawCircle(r, r, r, paint);
    return result;
  }

  /**
   * Añade un anillo blanco alrededor, con el ancho pasado por parámetro. Se presupone que la imagen
   * ha de ser redonda
   *
   * @param bitmap imagen redonda.
   */
  private static Bitmap addColoredFrame(Bitmap bitmap, float width, int color) {

    int w = bitmap.getWidth();
    int h = bitmap.getHeight();

    int radius = Math.min(h / 2, w / 2);
    Bitmap output = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);

    Paint p = new Paint();
    p.setAntiAlias(true);

    Canvas c = new Canvas(output);
    c.drawARGB(0, 0, 0, 0);
    p.setStyle(Paint.Style.FILL);

    c.drawCircle((w / 2), (h / 2), radius, p);

    p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

    c.drawBitmap(bitmap, 0, 0, p);
    p.setXfermode(null);
    p.setStyle(Paint.Style.STROKE);
    p.setColor(color);
    p.setStrokeWidth(width);
    c.drawCircle((w / 2), (h / 2), radius - (width / 2), p);

    return output;
  }

  @Override public String getId() {
    return "GlideCircleTransformation";
  }

  @Override
  public Resource<Bitmap> transform(Resource<Bitmap> resource, int outWidth, int outHeight) {
    Bitmap myTransformedBitmap = cropToCircle(pool, resource.get());
    myTransformedBitmap =
        addColoredFrame(myTransformedBitmap, dimenBorder, color);
    return BitmapResource.obtain(myTransformedBitmap, pool);
  }
}
