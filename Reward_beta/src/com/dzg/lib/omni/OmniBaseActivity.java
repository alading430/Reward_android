/*
 * Copyright 1999-2012 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.dzg.lib.omni;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.Animator.AnimatorListener;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;



/**
 * 类OmniBaseActivity.java的实现描述：TODO 类实现描述 
 */
public class OmniBaseActivity extends FragmentActivity{
	public static final String TAG = "OmniBaseActivity";
	
	protected LayoutInflater mInflater;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mInflater = LayoutInflater.from(this);
		swipeLayout = new SwipeLayout(this);
	}
	
	/****************************滑动切换Activity*********************************/
	
	private SwipeLayout swipeLayout;
	private boolean swipeFinished = false;
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		swipeLayout.replaceLayer(this);
	}
	
	public static int getScreenWidth(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        manager.getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }
	
	class SwipeLayout extends FrameLayout {

        public SwipeLayout(Context context) {
            super(context);
        }

        public SwipeLayout(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public SwipeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        public void replaceLayer(Activity activity) {
            mActivity = activity;
            screenWidth = getScreenWidth(activity);
            setClickable(true);
            ViewGroup root = (ViewGroup) activity.getWindow().getDecorView();
            content = root.getChildAt(0);
            ViewGroup.LayoutParams params = content.getLayoutParams();
            root.removeView(content);
            this.addView(content);
            root.addView(this, params);
            sideWidth = (int) (sideWidthInDP * activity.getResources().getDisplayMetrics().density);
        }

        boolean canSwipe = false;
        View content;
        Activity mActivity;
        int sideWidthInDP = 20;
        int sideWidth = 40;
        int screenWidth = 1080;
        VelocityTracker tracker;

        @Override
        public boolean onInterceptTouchEvent(MotionEvent ev) {
            if (ev.getAction() == MotionEvent.ACTION_DOWN && ev.getX() < sideWidth) {
                canSwipe = true;
                tracker = VelocityTracker.obtain();
                return true;
            }
            return super.onInterceptTouchEvent(ev);
        }

        float downX;
        float downY;
        float lastX;
        float currentX;
        float currentY;

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            if (canSwipe) {
                tracker.addMovement(event);
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        downX = event.getX();
                        downY = event.getY();
                        currentX = downX;
                        currentY = downY;
                        lastX = downX;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        currentX = event.getX();
                        currentY = event.getY();
                        float dx = currentX - lastX;
                        if (content.getX() + dx < 0) {
                            content.setX(0);
                        } else {
                            content.setX(content.getX() + dx);
                        }
                        lastX = currentX;
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        tracker.computeCurrentVelocity(10000);
                        tracker.computeCurrentVelocity(1000, 20000);
                        canSwipe = false;
                        int mv = screenWidth / 200 * 1000;
                        if (Math.abs(tracker.getXVelocity()) > mv) {
                            animateFromVelocity(tracker.getXVelocity());
                        } else {
                            if (content.getX() > screenWidth / 2) {
                                animateFinish(false);
                            } else {
                                animateBack(false);
                            }
                        }
                        tracker.recycle();
                        break;
                    default:
                        break;
                }
            }
            return super.onTouchEvent(event);
        }

        ObjectAnimator animator;

        private void cancelPotentialAnimation() {
            if (animator != null) {
                animator.removeAllListeners();
                animator.cancel();
            }
        }

        private void animateBack(boolean withVel) {
            cancelPotentialAnimation();
            animator = ObjectAnimator.ofFloat(content, "x", content.getX(), 0);
            if (withVel) {
                animator.setDuration((long) (duration * content.getX() / screenWidth));
            } else {
                animator.setDuration(duration);
            }
            animator.start();
        }

        private void animateFinish(boolean withVel) {
            cancelPotentialAnimation();
            animator = ObjectAnimator.ofFloat(content, "x", content.getX(), screenWidth);
            if (withVel) {
                animator.setDuration((long) (duration * (screenWidth - content.getX()) / screenWidth));
            } else {
                animator.setDuration(duration);
            }
            animator.addListener(new AnimatorListener() {

                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    if (!mActivity.isFinishing()) {
                        swipeFinished = true;
                        mActivity.finish();
                    }
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }
            });
            animator.start();
        }

        private int duration = 200;

        private void animateFromVelocity(float v) {
            if (v > 0) {
                if (content.getX() < screenWidth / 2
                        && v * duration / 1000 + content.getX() < screenWidth) {
                    animateBack(false);
                } else {
                    animateFinish(true);
                }
            } else {
                if (content.getX() > screenWidth / 2
                        && v * duration / 1000 + content.getX() > screenWidth / 2) {
                    animateFinish(false);
                } else {
                    animateBack(true);
                }
            }

        }

//        @TargetApi(Build.VERSION_CODES.L)
//        public SwipeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//            super(context, attrs, defStyleAttr, defStyleRes);
//        }
    }
}
