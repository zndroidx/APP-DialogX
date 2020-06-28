package com.zndroid.dialogx.interfaces;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.view.View;


/**
 * Created by lzy on 2020/6/28.
 */
public interface OnBottomSheetCallback {
    void onStateChanged(@NonNull View bottomSheet, @BottomSheetBehavior.State int newState);
    void onSlide(@NonNull View bottomSheet, float slideOffset);
}
