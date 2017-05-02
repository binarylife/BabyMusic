package com.beifeng.babymusic.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.beifeng.babymusic.R;
import com.beifeng.babymusic.util.UIUtils;

/**
 * Created by beifeng on 2017/5/2.
 * 环形百分比进度条
 */

public class CirclePercentView extends View {
  private int mCurPercent;
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
    String text = mCurPercent + "%";
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

  //外部设置百分比数
  //public void setPercent(int percent) {
  //  if (percent > 100) {
  //    throw new IllegalArgumentException("percent must less than 100!");
  //  }
  //
  //  setCurPercent(percent);
  //}

  //内部设置百分比 用于动画效果
  //private void setCurPercent(int percent) {
  //
  //  mCurPercent = percent;
  //
  //  new Thread(new Runnable() {
  //    @Override public void run() {
  //      int sleepTime = 1;
  //      for (int i = 0; i < mCurPercent; i++) {
  //        if (i % 20 == 0) {
  //          sleepTime += 2;
  //        }
  //        try {
  //          Thread.sleep(sleepTime);
  //        } catch (InterruptedException e) {
  //          e.printStackTrace();
  //        }
  //        mCurPercent = i;
  //        CirclePercentView.this.postInvalidate();
  //      }
  //    }
  //  }).start();
  //}
}
