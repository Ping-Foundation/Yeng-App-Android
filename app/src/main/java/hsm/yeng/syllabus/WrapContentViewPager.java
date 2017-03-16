package hsm.yeng.syllabus;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
//import android.support.v7.internal.VersionUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by Hm on 6/2/2016.
 */
public class WrapContentViewPager extends ViewPager {

    private int mCurrentPagePosition = 0;

    public WrapContentViewPager(Context context) {
        super(context);
    }

    public WrapContentViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        try {
            View child = getChildAt(mCurrentPagePosition);
            if (child != null) {
                child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                int h = child.getMeasuredHeight();
                heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(h, View.MeasureSpec.EXACTLY);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void reMeasureCurrentPage(int position) {
        mCurrentPagePosition = position;
        requestLayout();
    }
}
/*
public class WrapContentViewPager extends ViewPager {

    private Boolean mAnimStarted = false;

    public WrapContentViewPager(Context context) {
        super(context);
    }

    public WrapContentViewPager(Context context, AttributeSet attrs){
        super(context, attrs);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if(!mAnimStarted && null != getAdapter()) {
            int height = 0;
            View child = ((FragmentPagerAdapter) getAdapter()).getItem(getCurrentItem()).getView();
            if (child != null) {
                child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
                height = child.getMeasuredHeight();
                    height = getMinimumHeight();

            }

            // Not the best place to put this animation, but it works pretty good.
            int newHeight = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
            if (getLayoutParams().height != 0 && heightMeasureSpec != newHeight) {
                final int targetHeight = height;
                final int currentHeight = getLayoutParams().height;
                final int heightChange = targetHeight - currentHeight;

                Animation a = new Animation() {
                    @Override
                    protected void applyTransformation(float interpolatedTime, Transformation t) {
                        if (interpolatedTime >= 1) {
                            getLayoutParams().height = targetHeight;
                        } else {
                            int stepHeight = (int) (heightChange * interpolatedTime);
                            getLayoutParams().height = currentHeight + stepHeight;
                        }
                        requestLayout();
                    }

                    @Override
                    public boolean willChangeBounds() {
                        return true;
                    }
                };

                a.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        mAnimStarted = true;
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        mAnimStarted = false;
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });

                a.setDuration(1000);
                startAnimation(a);
                mAnimStarted = true;
            } else {
                heightMeasureSpec = newHeight;
            }
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}*/
