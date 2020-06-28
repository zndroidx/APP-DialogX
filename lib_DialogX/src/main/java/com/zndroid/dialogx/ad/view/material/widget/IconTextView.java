package com.zndroid.dialogx.ad.view.material.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;


import com.zndroid.dialogx.R;
import com.zndroid.dialogx.ad.view.material.Iconify;

public class IconTextView extends AppCompatTextView {

    public IconTextView(Context context) {
        super(context);
        init(null);
    }

    public IconTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public IconTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        boolean hackyPreview = false;
        TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.IconTextView, 0, 0);
        try {
            hackyPreview = a.getBoolean(R.styleable.IconTextView_hacky_preview, false);
        } finally {
            a.recycle();
        }

        if (!isInEditMode())
            Iconify.addIcons(this);
        else if (hackyPreview) {
            Iconify.addIconsEditMode(this);
        } else {
            this.setText(this.getText());
        }
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(Iconify.compute(text), type);
    }

}
