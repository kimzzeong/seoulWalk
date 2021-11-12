package com.example.seoulwalk.Activity;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class ExampleBehavior  extends CoordinatorLayout.Behavior<FloatingActionButton> {

    public ExampleBehavior() {
    }

    public ExampleBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 레이아웃에 트리거가 발생하면 불러집니다.
     * true 를 리턴하면 onDependentViewChanged 를 부릅니다.
     */
    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent,
                                   @NonNull FloatingActionButton child,
                                   @NonNull View dependency) {

        //Log.d("MyTag","dependency : " + dependency.getClass());
        return dependency instanceof Snackbar.SnackbarLayout;
    }

    /**
     * dependency 의 View 의 변화가 있을때 이벤트가 들어옵니다.
     */
    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent,
                                          @NonNull FloatingActionButton child,
                                          @NonNull View dependency) {

        //스낵바에 따라 움직임
//        float translationY = Math.min(0, dependency.getTranslationY() - dependency.getHeight());
//        child.setTranslationY(translationY);



        float translationY =  Math.min(0, dependency.getTranslationY() - dependency.getHeight());
        float percentComplete = -translationY / dependency.getHeight();
        float scaleFactor = 1 - percentComplete;

        child.setScaleX(scaleFactor);
        child.setScaleY(scaleFactor);
        return false;
    }
}
