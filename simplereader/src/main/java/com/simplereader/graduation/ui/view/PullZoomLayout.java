package com.simplereader.graduation.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.OverScroller;

import com.orhanobut.logger.Logger;
import com.simplereader.simplereader.R;

/**
 * Created by gxj on 2017/4/26.
 */

public class PullZoomLayout extends LinearLayout {
    private OverScroller mScroller;
    private int mTouchSLop;
    private int mMaximumVelocity;
    private int mMinmumVelocity;
    private View mHeaderLayout;
    private View mMainLayout;
    private int mScreenWidth;
    private VelocityTracker mVelocityTtacker;
    private float mLastY;
    private boolean mDragging;
    private int mScreenHeight;
    private LinearLayout mRootView;
    private int mOverscrollDistance;

    public PullZoomLayout(Context context) {
        this(context, null);
    }

    public PullZoomLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PullZoomLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScreenWidth = context.getResources().getDisplayMetrics().widthPixels;
        mScreenHeight = context.getResources().getDisplayMetrics().heightPixels;
        mScroller = new OverScroller(context);
        mTouchSLop = ViewConfiguration.get(context).getScaledTouchSlop();
        mMaximumVelocity = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
        mMinmumVelocity = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
        mOverscrollDistance = ViewConfiguration.get(context).getScaledOverscrollDistance();
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PullZoomLayout);
        int headerRes = a.getResourceId(R.styleable.PullZoomLayout_pzHeader, -1);
        int mainRes = a.getResourceId(R.styleable.PullZoomLayout_pzMain, -1);
        a.recycle();

        mRootView = new LinearLayout(context);
        mRootView.setOrientation(VERTICAL);
        mRootView.setGravity(Gravity.CENTER_HORIZONTAL);
        if (headerRes == -1 || mainRes == -1) {
            return;
        }
        mHeaderLayout = LayoutInflater.from(context).inflate(headerRes, mRootView, true);
        mMainLayout = LayoutInflater.from(context).inflate(mainRes, mRootView, true);
        addView(mRootView);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void measureChild(View child, int parentWidthMeasureSpec, int parentHeightMeasureSpec) {
        ViewGroup.LayoutParams lp = child.getLayoutParams();
        int childWidthMeasureSpec;
        int childHeightMeasureSpec;
        childWidthMeasureSpec = getChildMeasureSpec(parentWidthMeasureSpec, getPaddingLeft() + getPaddingRight(), lp.width);
        childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
    }

    @Override
    protected void measureChildWithMargins(View child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        final MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
        final int childWidthMeasureSpec = getChildMeasureSpec(parentWidthMeasureSpec, getPaddingLeft() + getPaddingRight() + lp.leftMargin + lp.rightMargin + widthUsed, lp.width);
        final int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(lp.topMargin + lp.bottomMargin, MeasureSpec.UNSPECIFIED);
        child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
    }

    private void initVelocityTrackerNotExist() {
        if (mVelocityTtacker == null) {
            mVelocityTtacker = VelocityTracker.obtain();
        }
    }

    private void recycleVelocityTracker() {
        if (mVelocityTtacker != null) {
            mVelocityTtacker.recycle();
            mVelocityTtacker = null;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        initVelocityTrackerNotExist();
        mVelocityTtacker.addMovement(event);
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                mLastY = y;
                return true;
            case MotionEvent.ACTION_MOVE:
                float dy = mLastY - y;
                if (!mDragging && Math.abs(dy) > mTouchSLop) {
                    //正在准备滑动
                    mDragging = true;
                }
                if (mDragging) {
                    Logger.i("-dy : " + dy);
                    scrollBy(0, (int) dy);
                }
                if (dy < 0 && getScrollY() <= 0) {
                    //下拉
                    Logger.i("下拉了");
                }
                mLastY = y;
                break;
            case MotionEvent.ACTION_CANCEL:
                mDragging = false;
                recycleVelocityTracker();
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_UP:
                mDragging = false;
                mVelocityTtacker.computeCurrentVelocity(1000, mMaximumVelocity);
                int velocityY = (int) mVelocityTtacker.getYVelocity();
                if (Math.abs(velocityY) > mMinmumVelocity) {
                    fling(-velocityY);
                }
                recycleVelocityTracker();
        }
        return super.onTouchEvent(event);
    }

    public void fling(int velocityY) {
        mScroller.fling(0, getScrollY(), 0, velocityY, 0, 0, 0, getScrollRange());
        invalidate();
    }

    @Override
    public void scrollTo(int x, int y) {
        if (y < 0) {
            y = 0;
        }
        if (y > getScrollRange()) {
            y = getScrollRange();
        }
        if (y != getScrollY()) {
            super.scrollTo(x, y);
        }
    }

    private int getScrollRange() {
        int scrollRange = 0;
        if (getChildCount() > 0) {
            Logger.i("mRootView.getHeight():" + mRootView.getHeight() + "-----getHeight():" + getHeight());
            scrollRange = Math.max(0, mRootView.getHeight() - (getHeight() - getPaddingBottom() - getPaddingTop()));
        }
        return scrollRange;
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(0, mScroller.getCurrY());
            invalidate();
        }
    }
}
