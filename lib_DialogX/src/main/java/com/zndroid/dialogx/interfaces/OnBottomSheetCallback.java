package com.zndroid.dialogx.interfaces;

import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

/**
 * Created by lzy on 2020/6/28.
 */
public interface OnBottomSheetCallback {
    void onStateChanged(@NonNull View bottomSheet, @BottomSheetBehavior.State int newState);
    void onSlide(@NonNull View bottomSheet, float slideOffset);
}
