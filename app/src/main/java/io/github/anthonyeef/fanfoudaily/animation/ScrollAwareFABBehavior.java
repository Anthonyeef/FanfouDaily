package io.github.anthonyeef.fanfoudaily.animation;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

import io.github.anthonyeef.fanfoudaily.ui.UIHome;

/**
 * Created by anthonyeef on 11/4/15.
 */
public class ScrollAwareFABBehavior extends FloatingActionButton.Behavior{

    public ScrollAwareFABBehavior(Context context, AttributeSet attrs) {
        super();
    }
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout,
                                       FloatingActionButton child, View directTargetChild,
                                       View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL ||
                super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target,
                        nestedScrollAxes);
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child,
                               View target, int dxConsumed, int dyConsunmed, int dxUnconsumed,
                               int dyUnconsumed) {
        if (UIHome.viewPager.getCurrentItem() == 0) {
            if (dyConsunmed > 0 && child.getVisibility() == View.VISIBLE ) {
                child.hide();
            } else if (dyConsunmed < 0 && child.getVisibility() != View.VISIBLE) {
                child.show();
            }

        }
    }
}
