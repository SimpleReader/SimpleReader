package com.simplereader.graduation.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.simplereader.graduation.MyApplication;

import java.io.File;
import java.util.concurrent.ExecutionException;

/**
 * Created by gxj on 2017/4/4.
 */

public class ImageLoadUtil {
    /**
     * 网络图片加载
     *
     * @param url       图片路径
     * @param resId     默认占位符
     * @param imageView 图片容器
     */
    public static void loadImage(String url, int resId, ImageView imageView) {
        if (imageView != null) {
            Glide.with(MyApplication.getContext())
                    .load(url)
                    .placeholder(resId)
                    .error(resId)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        }
    }

    /**
     * 网络图片加载
     *
     * @param context           上下文
     * @param url               图片路径
     * @param resId             默认占位符
     * @param imageView         图片容器
     * @param diskCacheStrategy 缓存策略
     *                          all:缓存源资源和转换后的资源
     *                          none:不作任何磁盘缓存
     *                          source:缓存源资源
     *                          result：缓存转换后的资源
     */
    public static void loadImage(Context context, String url, int resId, ImageView imageView, DiskCacheStrategy diskCacheStrategy) {
        if (context != null && imageView != null) {
            if (context instanceof Activity) {
                Activity activity = (Activity) context;
                if (activity.isFinishing()) {
                    return;
                }
            }
            Glide.with(context)
                    .load(url)
                    .placeholder(resId)
                    .error(resId)
                    .diskCacheStrategy(diskCacheStrategy)
                    .into(imageView);
        }
    }

    /**
     * 网络图片加载
     *
     * @param context   上下文
     * @param resId     默认占位符
     * @param imageView 图片容器
     */
    public static void loadGifImage(Context context, int resId, ImageView imageView) {
        if (context != null && imageView != null) {
            if (context instanceof Activity) {
                Activity activity = (Activity) context;
                if (activity.isFinishing()) {
                    return;
                }
            }
            Glide.with(context)
                    .load(resId)
                    .asGif()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        }
    }

    /**
     * 网络图片加载(没有占位图片)
     *
     * @param url
     * @param imageView
     */
    public static void loadImage(String url, ImageView imageView) {
        loadImage(url, 0, imageView);
    }

    /**
     * 加载mipmap中图片
     *
     * @param context
     * @param resId
     * @param imageView
     */
    public static void loadImage(Context context, int resId, ImageView imageView) {
        if (context != null && imageView != null) {
            if (context instanceof Activity) {
                Activity activity = (Activity) context;
                if (activity.isFinishing()) {
                    return;
                }
            }
            Glide.with(context)
                    .load(resId)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(resId)
                    .error(resId)
                    .into(imageView);
        }
    }

    /**
     * 图片加载返回bitmap格式
     *
     * @param context
     * @param url
     * @param width
     * @param height
     * @return
     */
    public static Bitmap loadBitmap(Context context, String url, int width, int height) {
        Bitmap myBitmap = null;
        try {
            myBitmap = Glide.with(context)
                    .load(url)
                    .asBitmap() //必须
                    .into(width, height)
                    .get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return myBitmap;
    }

    /**
     * 加载圆形头像
     *
     * @param context
     * @param url
     * @param resId
     * @param imageView
     */
    public static void loadCircleImage(Context context, String url, int resId, ImageView imageView) {
        if (context != null && imageView != null) {
            if (context instanceof Activity) {
                Activity activity = (Activity) context;
                if (activity.isFinishing()) {
                    return;
                }
            }
            Glide.with(context)
                    .load(url)
                    .placeholder(resId)
                    .error(resId)
                    .transform(new CircleTransform(context))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        }
    }

    /**
     * 圆形图片加载(String)(没有默认图片)
     *
     * @param context
     * @param url
     */
    public static void loadCircleImage(Context context, String url, ImageView imageView) {
        loadCircleImage(context, url, 0, imageView);
    }

    public static void loadCircleImage(Context context, String url, SimpleTarget<Bitmap> simpleTarget) {
        if (context != null) {
            if (context instanceof Activity) {
                Activity activity = (Activity) context;
                if (activity.isFinishing()) {
                    return;
                }
            }
            Glide.with(context)
                    .load(url)
                    .asBitmap()
                    .transform(new CircleTransform(context))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(simpleTarget);
        }
    }

    public static void loadCircleImage(Context context, int resId, SimpleTarget<Bitmap> simpleTarget) {
        if (context != null) {
            if (context instanceof Activity) {
                Activity activity = (Activity) context;
                if (activity.isFinishing()) {
                    return;
                }
            }
            Glide.with(context)
                    .load(resId)
                    .asBitmap()
                    .transform(new CircleTransform(context))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(simpleTarget);
        }
    }

    /**
     * 加载本地file圆形图片
     *
     * @param context
     * @param file
     * @param imageView
     */
    public static void loadCircleImage(Context context, File file, ImageView imageView) {
        if (context != null && file != null && imageView != null) {
            if (context instanceof Activity) {
                Activity activity = (Activity) context;
                if (activity.isFinishing()) {
                    return;
                }
            }
            Glide.with(context)
                    .load(file)
                    .transform(new CircleTransform(context))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        }
    }


    /**
     * 自定义圆形头像转换
     */
    public static class CircleTransform extends BitmapTransformation {
        public CircleTransform(Context context) {
            super(context);
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            return circleCrop(pool, toTransform);
        }

        private static Bitmap circleCrop(BitmapPool pool, Bitmap source) {
            if (source == null) {
                return null;
            }

            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            // TODO this could be acquired from the pool too
            Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);
            Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
            if (result == null) {//如果bitmaopool为空那么久创建一个
                result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
            }

            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);
            return result;
        }

        @Override
        public String getId() {
            return getClass().getName();
        }
    }

    /**
     * 正六边形图片加载
     *
     * @param context
     * @param url
     * @param resId
     * @param imageView
     */
    public static void loadHexagonImage(Context context, String url, int resId, ImageView imageView) {
        if (context != null && imageView != null) {
            if (context instanceof Activity) {
                Activity activity = (Activity) context;
                if (activity.isFinishing()) {
                    return;
                }
            }
            Glide.with(context)
                    .load(url)
                    .error(resId)
                    .transform(new HexagonTransform(context))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        }
    }

    /**
     * 绘制正六边形
     */
    public static class HexagonTransform extends BitmapTransformation {
        //正n边型
        private static int count = 6;
        //角度（360/count）
        private static float angle = (float) (Math.PI * 2 / count);
        //圆的半径
        private static float r = 100;
        //绘制点的坐标
        private static float x;
        private static float y;

        public HexagonTransform(Context context) {
            super(context);
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            return hexagonCrop(pool, toTransform);
        }

        public static Bitmap hexagonCrop(BitmapPool pool, Bitmap bitmap) {
            if (bitmap == null) {
                return null;
            }
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
            if (width <= height) {
                r = width / 2;
                top = 0;
                left = 0;
                bottom = width;
                right = width;
                height = width;
                dst_left = 0;
                dst_top = 0;
                dst_right = width;
                dst_bottom = width;
            } else {
                r = height / 2;
                float clip = (width - height) / 2;
                left = clip;
                right = width - clip;
                top = 0;
                bottom = height;
                width = height;
                dst_left = 0;
                dst_top = 0;
                dst_right = height;
                dst_bottom = height;
            }

            Bitmap output = pool.get(width, height, Bitmap.Config.ARGB_8888);
            if (output == null) {//如果bitmaopool为空那么久创建一个
                output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            }

            Canvas canvas = new Canvas(output);
            final Paint paint = new Paint();
            final Rect src = new Rect((int) left, (int) top, (int) right, (int) bottom);
            final Rect dst = new Rect((int) dst_left, (int) dst_top, (int) dst_right, (int) dst_bottom);

            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(Color.WHITE);
            paint.setAntiAlias(true);
            paint.setStrokeWidth(4);
            drawPolygon(canvas);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(bitmap, src, dst, paint);
            return output;
        }

        private static void drawPolygon(Canvas canvas) {
            Path mPath = new Path();
            Paint mPaint = new Paint();
            mPaint.setColor(Color.parseColor("#FFBB33"));
            //去绘制
            for (int i = 1; i <= count; i++) {
                //求相应的坐标
                x = (float) (Math.sin(i * angle) * r);
                y = (float) (Math.cos(i * angle) * r);

                if (i == 1) {
                    //当i为1时，该点为起点
                    mPath.moveTo(x + r, y + r);
                } else {
                    mPath.lineTo(x + r, y + r);
                }
            }
            mPath.close();
            canvas.drawPath(mPath, mPaint);
        }

        @Override
        public String getId() {
            return getClass().getName();
        }
    }
}
