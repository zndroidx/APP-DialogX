package com.zndroid.dialogx;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.LayoutRes;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;


import com.zndroid.dialogx.interfaces.OnBottomSheetCallback;

import java.lang.ref.WeakReference;

/**
 * Created by lzy on 2020/6/18.
 *
 * 随手势滑动的dialog
 *
 * GridView与BottomSheetDialog的手势有冲突。
 */
public class BottomBehaviorDialog {
    private static WeakReference<BottomSheetDialog> bottomSheetDialog;
    private static BottomSheetBehavior bottomSheetBehavior;
    private static WeakReference<Context> mContext;

    private static int heightPixels;

    public enum PEEK {
        AUTO(-2f),                  // warp
        QUARTERS(0.25f),            // 1/4
        TWO_FIFTHS(0.4f),           // 2/5
        HALF(0.5f),                 // 1/2
        THREE_FIFTHS(0.6f),         // 3/5
        THREE_QUARTERS(0.75f),      // 3/4
        FULL(-1f)                   // match
        ;

        float value;
        PEEK(float value) {
            this.value = value;
        }

        public float getValue() {
            return value;
        }
    }

    public static BottomBehaviorDialog build(@NonNull AppCompatActivity context) {
        BottomBehaviorDialog bottomBehaviorDialog = new BottomBehaviorDialog();

        mContext = new WeakReference<Context>(context);
        bottomSheetDialog = new WeakReference<>(new BottomSheetDialog(context));

        WindowManager w = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display d = w.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        d.getMetrics(metrics);
        heightPixels = metrics.heightPixels;

        return bottomBehaviorDialog;
    }

    public static void show(@NonNull AppCompatActivity context, View view) {
        build(context);

        bottomSheetDialog.get().setContentView(view);
//        bottomSheetBehavior = BottomSheetBehavior.from(view);

        showDefault(context, bottomSheetDialog.get(), true, true);
    }

    public static void show(@NonNull AppCompatActivity context, View view, final OnBottomSheetCallback bottomSheetCallback) {
        show(context, view);
//        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
//            @Override
//            public void onStateChanged(@NonNull View bottomSheet, int newState) {
//                bottomSheetCallback.onStateChanged(bottomSheet, newState);
//            }
//
//            @Override
//            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
//                bottomSheetCallback.onSlide(bottomSheet, slideOffset);
//            }
//        });
    }

    public static void show(@NonNull AppCompatActivity context, @LayoutRes int id) {
        build(context);

        bottomSheetDialog.get().setContentView(id);
//        bottomSheetBehavior = BottomSheetBehavior.from(context.getLayoutInflater().inflate(id, null));

        showDefault(context, bottomSheetDialog.get(), true, true);
    }

    private static void showDefault(@NonNull AppCompatActivity context, BottomSheetDialog bottomSheetDialog, boolean canceledOnTouchOutside, boolean cancelable) {
        bottomSheetDialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
        bottomSheetDialog.setCancelable(cancelable);
        bottomSheetDialog.getDelegate().findViewById(R.id.design_bottom_sheet)
                .setBackgroundColor(context.getResources().getColor(android.R.color.transparent));

        bottomSheetDialog.show();
    }

    public BottomBehaviorDialog setContentView(View view) {
        bottomSheetDialog.get().setContentView(view);
        bottomSheetBehavior = BottomSheetBehavior.from(view);
        return this;
    }

    public BottomBehaviorDialog setContentView(@LayoutRes int layoutResID) {
        bottomSheetDialog.get().setContentView(layoutResID);
        bottomSheetBehavior = BottomSheetBehavior.from(LayoutInflater.from(mContext.get()).inflate(layoutResID, null));
        return this;
    }

    public BottomBehaviorDialog setPeekHeight(int peekHeight) {
        if (null != bottomSheetBehavior)
            bottomSheetBehavior.setPeekHeight(peekHeight);
        return this;
    }

    public BottomBehaviorDialog setPeekHeight(PEEK peek) {
        int peekHeight;
        if (peek == PEEK.FULL)
            peekHeight = heightPixels;
        else if (peek == null || peek == PEEK.AUTO)
            peekHeight = BottomSheetBehavior.PEEK_HEIGHT_AUTO;
        else
            peekHeight = (int) (heightPixels * peek.getValue());

        if (null != bottomSheetBehavior)
            bottomSheetBehavior.setPeekHeight(peekHeight);
        return this;
    }

    public BottomBehaviorDialog addBottomSheetCallback(final OnBottomSheetCallback bottomSheetCallback) {
        if (null != bottomSheetBehavior)
            bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    bottomSheetCallback.onStateChanged(bottomSheet, newState);
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                    bottomSheetCallback.onSlide(bottomSheet, slideOffset);
                }
            });

        return this;
    }

    public BottomBehaviorDialog setCancelable(boolean cancelable) {
        if (null != bottomSheetBehavior)
            bottomSheetDialog.get().setCancelable(cancelable);
        return this;
    }

    public BottomBehaviorDialog setCanceledOnTouchOutside(boolean cancel) {
        if (null != bottomSheetBehavior)
            bottomSheetDialog.get().setCanceledOnTouchOutside(cancel);
        return this;
    }

    public BottomBehaviorDialog setOnCancelListener(DialogInterface.OnCancelListener cancelListener) {
        if (null != bottomSheetBehavior)
            bottomSheetDialog.get().setOnCancelListener(cancelListener);
        return this;
    }

    public BottomBehaviorDialog setOnShowListener(DialogInterface.OnShowListener showListener) {
        if (null != bottomSheetBehavior)
            bottomSheetDialog.get().setOnShowListener(showListener);
        return this;
    }

    public BottomBehaviorDialog setOnDismissListener(DialogInterface.OnDismissListener dismissListener) {
        if (null != bottomSheetBehavior)
            bottomSheetDialog.get().setOnDismissListener(dismissListener);
        return this;
    }

    public enum STATUS {
        /** The bottom sheet is dragging. */
        STATE_DRAGGING,
        /** The bottom sheet is settling. */
        STATE_SETTLING,
        /** The bottom sheet is expanded. */
        STATE_EXPANDED,
        /** The bottom sheet is collapsed. */
        STATE_COLLAPSED,
        /** The bottom sheet is hidden. */
        STATE_HIDDEN,
        /** The bottom sheet is half-expanded (used when mFitToContents is false). */
        STATE_HALF_EXPANDED
    }

    public BottomBehaviorDialog setStatus(STATUS status) {
        int state;
        switch (status) {
            case STATE_HIDDEN:
                state = BottomSheetBehavior.STATE_HIDDEN;
                break;
            case STATE_SETTLING:
                state = BottomSheetBehavior.STATE_SETTLING;
                break;
            case STATE_DRAGGING:
                state = BottomSheetBehavior.STATE_DRAGGING;
                break;
            case STATE_HALF_EXPANDED:
                state = BottomSheetBehavior.STATE_HALF_EXPANDED;
                break;
            case STATE_EXPANDED:
                state = BottomSheetBehavior.STATE_EXPANDED;
                break;
            default:
                state = BottomSheetBehavior.STATE_COLLAPSED;
                break;
        }

        if (null != bottomSheetBehavior)
            bottomSheetBehavior.setState(state);
        return this;
    }

    public void show() {
        if (null != bottomSheetDialog.get() && !bottomSheetDialog.get().isShowing()) {
            bottomSheetDialog.get().getDelegate().findViewById(R.id.design_bottom_sheet)
                    .setBackgroundColor(mContext.get().getResources().getColor(android.R.color.transparent));
            bottomSheetDialog.get().show();
        }
    }

    public void dismiss() {
        if (null != bottomSheetDialog.get() && bottomSheetDialog.get().isShowing()) {
            bottomSheetDialog.get().dismiss();
        }
    }

}
