package com.httvc.androiddemo.ui.mvvm;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

import static android.animation.ObjectAnimator.ofFloat;

/**
 * Created by Android Studio
 * Project：AndroidDemo
 * Author：httvc
 * Email：jfjie2013@163.com
 * Date：2017/6/10.
 */

public class PM25Anim {
    public static Animator add(final View view, float paramFloat)
    {
        ObjectAnimator objectAnimator = ofFloat(view, "translationX", new float[] { paramFloat, 0.0F });
        objectAnimator.setDuration(1000L);
        objectAnimator.addListener(new Animator.AnimatorListener()
        {
            public void onAnimationCancel(Animator paramAnonymousAnimator) {}

            public void onAnimationEnd(Animator paramAnonymousAnimator) {}

            public void onAnimationRepeat(Animator paramAnonymousAnimator) {}

            public void onAnimationStart(Animator paramAnonymousAnimator)
            {
                view.setVisibility(View.VISIBLE);
            }
        });
        return objectAnimator;
    }

    public static Animator down(View view, float paramFloat)
    {
        ObjectAnimator objectAnimator = ofFloat(view, "translationY", new float[] { 0.0F, paramFloat });
        objectAnimator.setDuration(2000L);
        return objectAnimator;
    }

    public static Animator up(View view, float paramFloat)
    {
        ObjectAnimator objectAnimator = ofFloat(view, "translationY", new float[] {0.0F, paramFloat  });
        objectAnimator.setDuration(2000L);
        return objectAnimator;
    }

    public static Animator up(View view, float fromY, float toY)
    {
        ObjectAnimator objectAnimator = ofFloat(view, "translationY", new float[] { fromY, toY });
        objectAnimator.setDuration(2000L);
        return objectAnimator;
    }

    public static Animator upAndVanish(View paramView, float paramFloat)
    {
        AnimatorSet localAnimatorSet = new AnimatorSet();
        Animator[] arrayOfAnimator = new Animator[2];
        arrayOfAnimator[0]= ObjectAnimator.ofFloat(paramView, "translationY", new float[] { paramFloat, 3.0F * paramFloat });
        arrayOfAnimator[1]=ObjectAnimator.ofFloat(paramView, "alpha", new float[] { 1.0F, 0.0F });
        localAnimatorSet.playTogether(arrayOfAnimator);
        localAnimatorSet.setDuration(2000L);
        return localAnimatorSet;
    }
}
