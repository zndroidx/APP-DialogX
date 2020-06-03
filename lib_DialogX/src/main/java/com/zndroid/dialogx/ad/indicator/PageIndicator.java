package com.zndroid.dialogx.ad.indicator;

import androidx.viewpager.widget.ViewPager;

public interface PageIndicator extends ViewPager.OnPageChangeListener {
    /** bind ViewPager */
    void setViewPager(ViewPager vp);

    /** for special viewpager,such as LooperViewPager */
    void setViewPager(ViewPager vp, int realCount);

    void setCurrentItem(int item);
}

