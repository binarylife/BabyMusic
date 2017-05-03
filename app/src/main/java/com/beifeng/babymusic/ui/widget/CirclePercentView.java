package com.beifeng.babymusic.ui.widget;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import com.beifeng.babymusic.R;
import com.beifeng.babymusic.util.UIUtils;

/**
 * Created by beifeng on 2017/5/2.
 * 环形百分比进度条
 */

public class CirclePercentView extends View {
  private float mCurPercent;
  private int smallColor;
  private int textSize;
  private int textColor;
  private float loopWidth;
  private float circleRadius;
  private int loopColor;
  private int circleColor;
  private int x;//  圆心坐标
  private int y;//  圆心坐标
  private int mWidth;
  private int mHeight;
  private Paint bigCirclePaint;
  private Paint sectorPaint;
  private RectF rectF;
  private TypedArray typedArray;
  private float mEndAngle;
  private Paint smallCirclePaint;
  private Paint textPaint;
  public static final String PROGRESS_PROPERTY = "progress";

  public CirclePercentView(Context context) {
    this(context, null);
  }

  public CirclePercentView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public CirclePercentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    //   加载自定义view中的自定义参数
    typedArray =
        context.obtainStyledAttributes(attrs, R.styleable.CirclePercentView, defStyleAttr, 0);
    loopWidth = typedArray.getDimension(R.styleable.CirclePercentView_stripeWidth, 10);//  环形半径
    circleRadius =
        typedArray.getDimension(R.styleable.CirclePercentView_radius, UIUtils.dp2px(100));//  背景圆的半径
    loopColor = typedArray.getColor(R.styleable.CirclePercentView_loopColor, Color.BLUE);//  圆环颜色
    circleColor =
        typedArray.getColor(R.styleable.CirclePercentView_cricleColor, Color.RED);//  圆背景颜色
    smallColor =
        typedArray.getColor(R.styleable.CirclePercentView_cricleColor, Color.BLACK);//  小圆 扇形扫过的背景颜色
    textSize =
        typedArray.getDimensionPixelOffset(R.styleable.CirclePercentView_textSize, 15);//  字体大小
    textColor =
        typedArray.getDimensionPixelOffset(R.styleable.CirclePercentView_cricleColor, 0);//  字体颜色
    mCurPercent = typedArray.getInteger(R.styleable.CirclePercentView_percent, 0);//  字体颜色

    initPaint();
  }

  /**
   * 重写测量模式
   */
  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    //获取宽高的测量模式
    int widthMode = MeasureSpec.getMode(widthMeasureSpec);
    int heightMode = MeasureSpec.getMode(heightMeasureSpec);

    //  获取测量宽高
    int width = MeasureSpec.getSize(widthMeasureSpec);
    int height = MeasureSpec.getSize(heightMeasureSpec);

    //  宽高都为精确测量时
    if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) {
      circleRadius = width / 2;
      x = width / 2;
      y = height / 2;
      mWidth = width;
      mHeight = height;
    }
    //  宽高都为wrapconent测量时  由半径决定view大小
    if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
      x = (int) circleRadius;
      y = (int) circleRadius;
      mWidth = (int) (circleRadius * 2);
      mHeight = (int) (circleRadius * 2);
    }
    setMeasuredDimension(mWidth, mHeight);
  }

  /**
   * 绘制文字及进度条
   */
  @Override protected void onDraw(Canvas canvas) {
    //  当前角度
    mEndAngle = mCurPercent * (float) 3.6;
    //绘制大圆

    bigCirclePaint = new Paint();
    bigCirclePaint.setAntiAlias(true);
    bigCirclePaint.setColor(circleColor);
    canvas.drawCircle(x, y, circleRadius, bigCirclePaint);
    //绘制扇形
    sectorPaint = new Paint();
    sectorPaint.setAntiAlias(true);
    sectorPaint.setColor(smallColor);
    rectF = new RectF(0, 0, mWidth, mHeight);
    canvas.drawArc(rectF, 270, mEndAngle, true, sectorPaint);
    //绘制小圆
    //  初始化小圆
    smallCirclePaint = new Paint();
    smallCirclePaint.setAntiAlias(true);
    smallCirclePaint.setColor(circleColor);
    canvas.drawCircle(x, y, circleRadius - loopWidth, smallCirclePaint);
    ////绘制进度文字 百分比
    textPaint = new Paint();
    int percent = (int) mCurPercent;
    String text = percent + "%";
    textPaint.setTextSize(textSize);
    textPaint.setColor(Color.WHITE);
    float textLength = textPaint.measureText(text);//  文字的宽度
    canvas.drawText(text, x - textLength / 2, y, textPaint);
  }

  /**
   * 初始化画笔
   */
  private void initPaint() {
    //  初始化大圆

    //  初始化并状圆

    //
    ////  初始化文字
    //textPaint = new Paint();
  }

  //public void setProgress(float progress) {
  //  mCurPercent = (int) progress;
  //  invalidate();// UI thread
  //  // postInvalidate();//non-UI thread.
  //}

  /**
   * 进度动画
   */
  public void dodo(int progress) {
    //mCurPercent = progress;

    //也可使用3.0的AnimationSet实现
    //      AnimationSet set = new AnimationSet(true);
    //      set.setRepeatCount(AnimationSet.INFINITE);
    //      set.setInterpolator(new AccelerateDecelerateInterpolator());
    //      set.start();
    //      this.setAnimation(set);

    //4.0以上，在AnimationSet基础上封装的，遗憾的是没有Repeat

    ValueAnimator progressAnimation = ObjectAnimator.ofFloat(0f, progress);
    progressAnimation.setDuration(700);// 动画执行时间
    progressAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override public void onAnimationUpdate(ValueAnimator animation) {
        mCurPercent = (float) animation.getAnimatedValue();
        invalidate();
      }
    });
        /*
         * AccelerateInterpolator　　　　　                  加速，开始时慢中间加速
         * DecelerateInterpolator　　　 　　                 减速，开始时快然后减速
         * AccelerateDecelerateInterolator　                     先加速后减速，开始结束时慢，中间加速
         * AnticipateInterpolator　　　　　　                 反向 ，先向相反方向改变一段再加速播放
         * AnticipateOvershootInterpolator　                 反向加超越，先向相反方向改变，再加速播放，会超出目的值然后缓慢移动至目的值
         * BounceInterpolator　　　　　　　                        跳跃，快到目的值时值会跳跃，如目的值100，后面的值可能依次为85，77，70，80，90，100
         * CycleIinterpolator　　　　　　　　                   循环，动画循环一定次数，值的改变为一正弦函数：Math.sin(2 *
         * mCycles * Math.PI * input) LinearInterpolator　　　 线性，线性均匀改变
         * OvershottInterpolator　　　　　　                  超越，最后超出目的值然后缓慢改变到目的值
         * TimeInterpolator　　　　　　　　　                        一个接口，允许你自定义interpolator，以上几个都是实现了这个接口
         */
    progressAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
    progressAnimation.start();//动画同时执行,可以做多个动画
  }
}
