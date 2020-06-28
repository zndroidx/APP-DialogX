package com.zndroid.dialogx;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.zndroid.dialogx.R;
import com.zndroid.dialogx.core.BaseDialog;
import com.zndroid.dialogx.core.DialogSettings;
import com.zndroid.dialogx.model.TextInfo;
import com.zndroid.dialogx.view.BlurView;
import com.zndroid.dialogx.interfaces.OnBackClickListener;
import com.zndroid.dialogx.interfaces.OnDialogButtonClickListener;
import com.zndroid.dialogx.interfaces.OnDismissListener;
import com.zndroid.dialogx.interfaces.OnMenuItemClickListener;
import com.zndroid.dialogx.interfaces.OnShowListener;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class BottomMenuDialog extends BaseDialog {
    
    private BaseAdapter customAdapter;      //允许用户自定义 Menu 的 Adapter
    
    private List<String> menuTextList;
    private String title;
    private String cancelButtonText = DialogSettings.defaultCancelButtonText;
    private boolean showCancelButton = true;
    protected OnMenuItemClickListener onMenuItemClickListener;
    protected OnDialogButtonClickListener onCancelButtonClickListener;
    
    private TextInfo menuTitleTextInfo;
    private TextInfo cancelButtonTextInfo;
    
    private LinearLayout boxRoot;
    private LinearLayout boxBody;
    private RelativeLayout boxList;
    private TextView txtTitle;
    private RelativeLayout boxCustom;
    private ImageView titleSplitLine;
    private ListView listMenu;
    private ViewGroup boxCancel;
    private TextView btnCancel;
    private TextInfo menuTextInfo;
    private ImageView imgTab;
    private ImageView imgSplit;
    
    private BottomMenuDialog() {
    }
    
    public static BottomMenuDialog build(@NonNull AppCompatActivity context) {
        synchronized (BottomMenuDialog.class) {
            BottomMenuDialog bottomMenuDialog = new BottomMenuDialog();
            bottomMenuDialog.log("装载底部菜单: " + bottomMenuDialog.toString());
            bottomMenuDialog.context = new WeakReference<>(context);
            
            switch (bottomMenuDialog.style) {
                case STYLE_IOS:
                    bottomMenuDialog.build(bottomMenuDialog, R.layout.dialogx_layout_bottom_menu_ios);
                    break;
                case STYLE_SQUARE:
                    bottomMenuDialog.build(bottomMenuDialog, R.layout.dialogx_layout_bottom_menu_square);
                    break;
                case STYLE_MATERIAL:
                    bottomMenuDialog.build(bottomMenuDialog, R.layout.dialogx_layout_bottom_menu_material);
                    break;
            }
            return bottomMenuDialog;
        }
    }
    
    public static BottomMenuDialog show(@NonNull AppCompatActivity context, List<String> menuTextList, OnMenuItemClickListener onMenuItemClickListener) {
        BottomMenuDialog bottomMenuDialog = build(context);
        bottomMenuDialog.menuTextList = menuTextList;
        bottomMenuDialog.onMenuItemClickListener = onMenuItemClickListener;
        bottomMenuDialog.showDialog();
        return bottomMenuDialog;
    }
    
    public static BottomMenuDialog show(@NonNull AppCompatActivity context, BaseAdapter customAdapter, OnMenuItemClickListener onMenuItemClickListener) {
        BottomMenuDialog bottomMenuDialog = build(context);
        bottomMenuDialog.customAdapter = customAdapter;
        bottomMenuDialog.onMenuItemClickListener = onMenuItemClickListener;
        bottomMenuDialog.showDialog();
        return bottomMenuDialog;
    }
    
    public static BottomMenuDialog show(@NonNull AppCompatActivity context, String title, List<String> menuTextList, OnMenuItemClickListener onMenuItemClickListener) {
        BottomMenuDialog bottomMenuDialog = build(context);
        bottomMenuDialog.menuTextList = menuTextList;
        bottomMenuDialog.title = title;
        bottomMenuDialog.onMenuItemClickListener = onMenuItemClickListener;
        bottomMenuDialog.showDialog();
        return bottomMenuDialog;
    }
    
    public static BottomMenuDialog show(@NonNull AppCompatActivity context, String title, BaseAdapter customAdapter, OnMenuItemClickListener onMenuItemClickListener) {
        BottomMenuDialog bottomMenuDialog = build(context);
        bottomMenuDialog.customAdapter = customAdapter;
        bottomMenuDialog.title = title;
        bottomMenuDialog.onMenuItemClickListener = onMenuItemClickListener;
        bottomMenuDialog.showDialog();
        return bottomMenuDialog;
    }
    
    public static BottomMenuDialog show(@NonNull AppCompatActivity context, String[] menuTexts, OnMenuItemClickListener onMenuItemClickListener) {
        BottomMenuDialog bottomMenuDialog = build(context);
        List<String> list = new ArrayList<>();
        for (String s : menuTexts) {
            list.add(s);
        }
        bottomMenuDialog.menuTextList = list;
        bottomMenuDialog.onMenuItemClickListener = onMenuItemClickListener;
        bottomMenuDialog.showDialog();
        return bottomMenuDialog;
    }
    
    public static BottomMenuDialog show(@NonNull AppCompatActivity context, String title, String[] menuTexts, OnMenuItemClickListener onMenuItemClickListener) {
        BottomMenuDialog bottomMenuDialog = build(context);
        List<String> list = new ArrayList<>();
        for (String s : menuTexts) {
            list.add(s);
        }
        bottomMenuDialog.menuTextList = list;
        bottomMenuDialog.title = title;
        bottomMenuDialog.onMenuItemClickListener = onMenuItemClickListener;
        bottomMenuDialog.showDialog();
        return bottomMenuDialog;
    }
    
    private View rootView;
    
    @Override
    public void bindView(View rootView) {
        log("启动底部菜单 -> " + toString());
        this.rootView = rootView;
        if (boxCustom != null) boxCustom.removeAllViews();
        boxRoot = rootView.findViewById(R.id.box_root);
        boxBody = rootView.findViewById(R.id.box_body);
        imgTab = rootView.findViewById(R.id.img_tab);
        boxList = rootView.findViewById(R.id.box_list);
        txtTitle = rootView.findViewById(R.id.txt_title);
        boxCustom = rootView.findViewById(R.id.box_custom);
        titleSplitLine = rootView.findViewById(R.id.title_split_line);
        listMenu = rootView.findViewById(R.id.list_menu);
        boxCancel = rootView.findViewById(R.id.box_cancel);
        btnCancel = rootView.findViewById(R.id.btn_cancel);
        imgSplit = rootView.findViewById(R.id.img_split);
        
        switch (style) {
            case STYLE_MATERIAL:
                boxCancel.setVisibility(View.GONE);
                boxBody.setY(getRootHeight());
                boxBody.setVisibility(View.VISIBLE);
                boxBody.post(new Runnable() {
                    @Override
                    public void run() {
                        if (boxBody.getHeight() > getRootHeight() * 2 / 3) {
                            boxBody.setY(boxBody.getHeight());
                            boxBody.animate().setDuration(300).translationY(boxBody.getHeight() / 2);
                        } else {
                            boxBody.animate().setDuration(300).translationY(0);
                        }
                    }
                });
                listMenu.setOnTouchListener(listViewTouchListener);
                boxBody.setOnTouchListener(listViewTouchListener);
                
                if (theme == DialogSettings.THEME.LIGHT) {
                    boxBody.setBackgroundResource(R.drawable.dialogx_rect_bottom_dialog);
                    imgTab.setBackgroundResource(R.drawable.dialogx_rect_share_material_tab);
                    txtTitle.setTextColor(context.get().getResources().getColor(R.color.tipTextColor));
                } else {
                    boxBody.setBackgroundResource(R.drawable.dialogx_rect_bottom_dialog_dark);
                    imgTab.setBackgroundResource(R.drawable.dialogx_rect_share_material_tab_dark);
                    txtTitle.setTextColor(context.get().getResources().getColor(R.color.materialDarkTitleColor));
                }
                
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = dialog.get().getDialog().getWindow();
                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    dialog.get().getDialog().getWindow().setNavigationBarColor(Color.WHITE);
                    boxBody.setPadding(0, 0, 0, getNavigationBarHeight());
                }
                break;
            case STYLE_SQUARE:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = dialog.get().getDialog().getWindow();
                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setNavigationBarColor(Color.WHITE);
                    boxBody.setPadding(0, 0, 0, getNavigationBarHeight());
                    
                    if (theme == DialogSettings.THEME.LIGHT) {
                        boxRoot.setBackgroundColor(context.get().getResources().getColor(R.color.menuSplitSpaceSquare));
                        txtTitle.setBackgroundColor(context.get().getResources().getColor(R.color.white));
                        boxCustom.setBackgroundColor(context.get().getResources().getColor(R.color.white));
                        listMenu.setBackgroundColor(context.get().getResources().getColor(R.color.white));
                        boxCancel.setBackgroundColor(context.get().getResources().getColor(R.color.white));
                        imgSplit.setBackgroundColor(context.get().getResources().getColor(R.color.menuSplitSpaceSquare));
                        btnCancel.setTextColor(context.get().getResources().getColor(R.color.dark));
                        btnCancel.setBackgroundResource(R.drawable.dialogx_bg_button_menu_square);
                        txtTitle.setTextColor(context.get().getResources().getColor(R.color.tipTextColor));
                    } else {
                        boxRoot.setBackgroundColor(context.get().getResources().getColor(R.color.squareDarkBkgColor));
                        txtTitle.setBackgroundColor(context.get().getResources().getColor(R.color.materialDarkBackgroundColor));
                        boxCustom.setBackgroundColor(context.get().getResources().getColor(R.color.materialDarkBackgroundColor));
                        listMenu.setBackgroundColor(context.get().getResources().getColor(R.color.materialDarkBackgroundColor));
                        boxCancel.setBackgroundColor(context.get().getResources().getColor(R.color.materialDarkBackgroundColor));
                        imgSplit.setBackgroundColor(context.get().getResources().getColor(R.color.squareDarkBkgColor));
                        btnCancel.setTextColor(context.get().getResources().getColor(R.color.materialDarkTextColor));
                        btnCancel.setBackgroundResource(R.drawable.dialogx_button_menu_square_dark);
                        txtTitle.setTextColor(context.get().getResources().getColor(R.color.materialDarkTitleColor));
                    }
                    
                    //设置底部导航栏按钮暗色，无效，悬赏解决————
                    View decorView = window.getDecorView();
                    int vis = decorView.getSystemUiVisibility();
                    vis |= View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
                    vis |= WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS;
                    vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                    decorView.setSystemUiVisibility(vis);
                }
                break;
            case STYLE_IOS:
                final int bkgResId, blurFrontColor;
                if (theme == DialogSettings.THEME.LIGHT) {
                    bkgResId = R.drawable.dialogx_rect_menu_bkg_ios;
                    blurFrontColor = Color.argb(DialogSettings.blurAlpha, 244, 245, 246);
                    btnCancel.setBackgroundResource(R.drawable.dialogx_button_menu_ios_light);
                    listMenu.setDivider(new ColorDrawable(context.get().getResources().getColor(R.color.dialogSplitIOSLight)));
                    listMenu.setDividerHeight(1);
                    titleSplitLine.setBackgroundColor(context.get().getResources().getColor(R.color.dialogSplitIOSLight));
                } else {
                    bkgResId = R.drawable.dialogx_rect_menu_bkg_ios;
                    blurFrontColor = Color.argb(DialogSettings.blurAlpha + 10, 22, 22, 22);
                    btnCancel.setBackgroundResource(R.drawable.dialogx_button_menu_ios_dark);
                    listMenu.setDivider(new ColorDrawable(context.get().getResources().getColor(R.color.dialogSplitIOSDark)));
                    listMenu.setDividerHeight(1);
                    titleSplitLine.setBackgroundColor(context.get().getResources().getColor(R.color.dialogSplitIOSDark));
                }
                if (DialogSettings.isUseBlur) {
                    boxList.post(new Runnable() {
                        @Override
                        public void run() {
                            blurList = new BlurView(context.get(), null);
                            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, boxList.getHeight());
                            blurList.setOverlayColor(blurFrontColor);
                            blurList.setRadius(context.get(), 11, 11);
                            boxList.addView(blurList, 0, params);
                        }
                    });
                    boxCancel.post(new Runnable() {
                        @Override
                        public void run() {
                            blurCancel = new BlurView(context.get(), null);
                            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, boxCancel.getHeight());
                            blurCancel.setOverlayColor(blurFrontColor);
                            blurCancel.setRadius(context.get(), 11, 11);
                            boxCancel.addView(blurCancel, 0, params);
                        }
                    });
                } else {
                    boxList.setBackgroundResource(bkgResId);
                    boxCancel.setBackgroundResource(bkgResId);
                }
                break;
        }
        
        refreshView();
        if (onShowListener != null) onShowListener.onShow(this);
    }
    
    private BlurView blurList;
    private BlurView blurCancel;
    
    @Override
    public void refreshView() {
        if (cancelButtonTextInfo == null) cancelButtonTextInfo = menuTextInfo;
        if (menuTitleTextInfo == null) menuTitleTextInfo = DialogSettings.menuTitleInfo;
        if (menuTextInfo == null) menuTextInfo = DialogSettings.menuTextInfo;
        if (cancelButtonText == null) cancelButtonText = "取消";
        
        if (rootView != null) {
            btnCancel.setText(cancelButtonText);
            
            ((ViewGroup) boxBody.getParent()).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    doDismiss();
                }
            });
            
            switch (style) {
                case STYLE_MATERIAL:
                    if (customAdapter != null) {
                        menuArrayAdapter = customAdapter;
                    } else {
                        menuArrayAdapter = new NormalMenuArrayAdapter(context.get(), R.layout.dialogx_item_bottom_menu_material, menuTextList);
                    }
                    listMenu.setAdapter(menuArrayAdapter);
                    break;
                case STYLE_SQUARE:
                    if (showCancelButton) {
                        if (boxCancel != null) boxCancel.setVisibility(View.VISIBLE);
                    } else {
                        if (boxCancel != null) boxCancel.setVisibility(View.GONE);
                    }
                    if (customAdapter != null) {
                        menuArrayAdapter = customAdapter;
                    } else {
                        menuArrayAdapter = new NormalMenuArrayAdapter(context.get(), R.layout.dialogx_item_bottom_menu_square, menuTextList);
                    }
                    listMenu.setAdapter(menuArrayAdapter);
                    break;
                case STYLE_IOS:
                    if (showCancelButton) {
                        if (boxCancel != null) boxCancel.setVisibility(View.VISIBLE);
                    } else {
                        if (boxCancel != null) boxCancel.setVisibility(View.GONE);
                    }
                    if (customAdapter != null) {
                        menuArrayAdapter = customAdapter;
                    } else {
                        menuArrayAdapter = new IOSMenuArrayAdapter(context.get(), R.layout.dialogx_item_bottom_menu_ios, menuTextList);
                    }
                    listMenu.setAdapter(menuArrayAdapter);
                    
                    break;
            }
            if (customView != null) {
                boxCustom.removeAllViews();
                boxCustom.addView(customView);
                if (onBindView != null) onBindView.onBind(this, customView);
                boxCustom.setVisibility(View.VISIBLE);
                if (titleSplitLine != null) titleSplitLine.setVisibility(View.VISIBLE);
            } else {
                boxCustom.setVisibility(View.GONE);
            }
            
            if (!isNull(title)) {
                txtTitle.setText(title);
                txtTitle.setVisibility(View.VISIBLE);
                if (titleSplitLine != null) titleSplitLine.setVisibility(View.VISIBLE);
            }
            
            listMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (onMenuItemClickListener != null) {
                        if (customAdapter != null) {
                            onMenuItemClickListener.onClick(customAdapter.getItem(position).toString(), position);
                        } else {
                            onMenuItemClickListener.onClick(menuTextList.get(position), position);
                        }
                    }
                    doDismiss();
                }
            });
            
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onCancelButtonClickListener!=null){
                        if (!onCancelButtonClickListener.onClick(BottomMenuDialog.this,btnCancel)){
                            doDismiss();
                        }
                    }else {
                        doDismiss();
                    }
                }
            });
        }
        
        useTextInfo(txtTitle, menuTitleTextInfo);
        useTextInfo(btnCancel, cancelButtonTextInfo);
    }
    
    @Override
    public void show() {
        showDialog();
    }
    
    private BaseAdapter menuArrayAdapter;
    
    public class IOSMenuArrayAdapter extends NormalMenuArrayAdapter {
        
        public IOSMenuArrayAdapter(Context context, int resourceId, List<String> objects) {
            super(context, resourceId, objects);
        }
        
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                LayoutInflater mInflater = LayoutInflater.from(context);
                convertView = mInflater.inflate(resoureId, null);
                viewHolder.textView = convertView.findViewById(R.id.text);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            String text = objects.get(position);
            if (null != text) {
                viewHolder.textView.setText(text);
                
                useTextInfo(viewHolder.textView, menuTextInfo);
                
                if (objects.size() == 1) {
                    if (theme == DialogSettings.THEME.LIGHT) {
                        if (title != null && !title.trim().isEmpty()) {
                            viewHolder.textView.setBackgroundResource(R.drawable.dialogx_button_menu_ios_bottom_light);
                        } else {
                            if (boxCustom.getVisibility() == View.VISIBLE) {
                                viewHolder.textView.setBackgroundResource(R.drawable.dialogx_button_menu_ios_bottom_light);
                            } else {
                                viewHolder.textView.setBackgroundResource(R.drawable.dialogx_button_menu_ios_light);
                            }
                        }
                    } else {
                        if (title != null && !title.trim().isEmpty()) {
                            viewHolder.textView.setBackgroundResource(R.drawable.dialogx_button_menu_ios_bottom_dark);
                        } else {
                            if (boxCustom.getVisibility() == View.VISIBLE) {
                                viewHolder.textView.setBackgroundResource(R.drawable.dialogx_button_menu_ios_bottom_dark);
                            } else {
                                viewHolder.textView.setBackgroundResource(R.drawable.dialogx_button_menu_ios_dark);
                            }
                        }
                    }
                } else {
                    if (theme == DialogSettings.THEME.LIGHT) {
                        if (position == 0) {
                            if (title != null && !title.trim().isEmpty()) {
                                viewHolder.textView.setBackgroundResource(R.drawable.dialogx_button_menu_ios_center_light);
                            } else {
                                if (boxCustom.getVisibility() == View.VISIBLE) {
                                    viewHolder.textView.setBackgroundResource(R.drawable.dialogx_button_menu_ios_center_light);
                                } else {
                                    viewHolder.textView.setBackgroundResource(R.drawable.dialogx_button_menu_ios_top_light);
                                }
                            }
                        } else if (position == objects.size() - 1) {
                            viewHolder.textView.setBackgroundResource(R.drawable.dialogx_button_menu_ios_bottom_light);
                        } else {
                            viewHolder.textView.setBackgroundResource(R.drawable.dialogx_button_menu_ios_center_light);
                        }
                    } else {
                        if (position == 0) {
                            if (title != null && !title.trim().isEmpty()) {
                                viewHolder.textView.setBackgroundResource(R.drawable.dialogx_button_menu_ios_center_dark);
                            } else {
                                if (boxCustom.getVisibility() == View.VISIBLE) {
                                    viewHolder.textView.setBackgroundResource(R.drawable.dialogx_button_menu_ios_center_dark);
                                } else {
                                    viewHolder.textView.setBackgroundResource(R.drawable.dialogx_button_menu_ios_top_dark);
                                }
                            }
                        } else if (position == objects.size() - 1) {
                            viewHolder.textView.setBackgroundResource(R.drawable.dialogx_button_menu_ios_bottom_dark);
                        } else {
                            viewHolder.textView.setBackgroundResource(R.drawable.dialogx_button_menu_ios_center_dark);
                        }
                    }
                }
            }
            
            return convertView;
        }
    }
    
    public class NormalMenuArrayAdapter extends ArrayAdapter {
        public int resoureId;
        public List<String> objects;
        public Context context;
        
        public NormalMenuArrayAdapter(Context context, int resourceId, List<String> objects) {
            super(context, resourceId, objects);
            this.objects = objects;
            this.resoureId = resourceId;
            this.context = context;
        }
        
        public class ViewHolder {
            TextView textView;
        }
        
        @Override
        public int getCount() {
            return objects.size();
        }
        
        @Override
        public String getItem(int position) {
            return objects.get(position);
        }
        
        @Override
        public long getItemId(int position) {
            return position;
        }
        
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                LayoutInflater mInflater = LayoutInflater.from(context);
                convertView = mInflater.inflate(resoureId, null);
                viewHolder.textView = convertView.findViewById(R.id.text);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            String text = objects.get(position);
            if (null != text) {
                viewHolder.textView.setText(text);
                
                if (style == DialogSettings.STYLE.STYLE_SQUARE) {
                    if (theme == DialogSettings.THEME.LIGHT) {
                        viewHolder.textView.setTextColor(context.getResources().getColor(R.color.dark));
                    } else {
                        viewHolder.textView.setTextColor(context.getResources().getColor(R.color.materialDarkTextColor));
                    }
                }
                if (style == DialogSettings.STYLE.STYLE_MATERIAL) {
                    if (theme == DialogSettings.THEME.LIGHT) {
                        viewHolder.textView.setTextColor(context.getResources().getColor(R.color.notificationTipTextColorMaterial));
                    } else {
                        viewHolder.textView.setTextColor(context.getResources().getColor(R.color.materialDarkTextColor));
                    }
                }
                
                useTextInfo(viewHolder.textView, menuTextInfo);
            }
            
            return convertView;
        }
    }
    
    //其他设置
    public List<String> getMenuTextList() {
        return menuTextList;
    }
    
    public BottomMenuDialog setMenuTextList(List<String> menuTextList) {
        this.menuTextList = menuTextList;
        refreshView();
        return this;
    }
    
    public BottomMenuDialog setMenuTextList(String[] menuTexts) {
        List<String> list = new ArrayList<>();
        for (String s : menuTexts) {
            list.add(s);
        }
        this.menuTextList = list;
        refreshView();
        return this;
    }
    
    public String getTitle() {
        return title;
    }
    
    public BottomMenuDialog setTitle(String title) {
        this.title = title;
        refreshView();
        return this;
    }
    
    public BottomMenuDialog setTitle(int titleResId) {
        this.title = context.get().getString(titleResId);
        refreshView();
        return this;
    }
    
    public String getCancelButtonText() {
        return cancelButtonText;
    }
    
    public BottomMenuDialog setCancelButtonText(String cancelButtonText) {
        this.cancelButtonText = cancelButtonText;
        refreshView();
        return this;
    }
    
    public BottomMenuDialog setCancelButtonText(int cancelButtonTextResId) {
        this.cancelButtonText = context.get().getString(cancelButtonTextResId);
        refreshView();
        return this;
    }
    
    public boolean isShowCancelButton() {
        return showCancelButton;
    }
    
    public BottomMenuDialog setShowCancelButton(boolean showCancelButton) {
        this.showCancelButton = showCancelButton;
        refreshView();
        return this;
    }
    
    public OnMenuItemClickListener getOnMenuItemClickListener() {
        return onMenuItemClickListener;
    }
    
    public BottomMenuDialog setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        this.onMenuItemClickListener = onMenuItemClickListener;
        refreshView();
        return this;
    }
    
    public TextInfo getMenuTitleTextInfo() {
        return menuTitleTextInfo;
    }
    
    public BottomMenuDialog setMenuTitleTextInfo(TextInfo menuTitleTextInfo) {
        this.menuTitleTextInfo = menuTitleTextInfo;
        refreshView();
        return this;
    }
    
    public TextInfo getMenuTextInfo() {
        return menuTextInfo;
    }
    
    public BottomMenuDialog setMenuTextInfo(TextInfo menuTextInfo) {
        this.menuTextInfo = menuTextInfo;
        refreshView();
        return this;
    }
    
    public TextInfo getCancelButtonTextInfo() {
        return cancelButtonTextInfo;
    }
    
    public BottomMenuDialog setCancelButtonTextInfo(TextInfo cancelButtonTextInfo) {
        this.cancelButtonTextInfo = cancelButtonTextInfo;
        refreshView();
        return this;
    }
    
    public OnDismissListener getOnDismissListener() {
        return onDismissListener == null ? new OnDismissListener() {
            @Override
            public void onDismiss() {
            
            }
        } : onDismissListener;
    }
    
    public BottomMenuDialog setOnDismissListener(OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
        return this;
    }
    
    public OnShowListener getOnShowListener() {
        return onShowListener == null ? new OnShowListener() {
            @Override
            public void onShow(BaseDialog dialog) {
            
            }
        } : onShowListener;
    }
    
    public BottomMenuDialog setOnShowListener(OnShowListener onShowListener) {
        this.onShowListener = onShowListener;
        return this;
    }
    
    public View getCustomView() {
        return customView;
    }
    
    public BottomMenuDialog setCustomView(View customView) {
        this.customView = customView;
        refreshView();
        return this;
    }
    
    private OnBindView onBindView;
    
    public BottomMenuDialog setCustomView(int customViewLayoutId, OnBindView onBindView) {
        customView = LayoutInflater.from(context.get()).inflate(customViewLayoutId, null);
        this.onBindView = onBindView;
        refreshView();
        return this;
    }
    
    public interface OnBindView {
        void onBind(BottomMenuDialog bottomMenuDialog, View v);
    }
    
    public DialogSettings.STYLE getStyle() {
        return style;
    }
    
    public OnDialogButtonClickListener getOnCancelButtonClickListener() {
        return onCancelButtonClickListener;
    }
    
    public BottomMenuDialog setOnCancelButtonClickListener(OnDialogButtonClickListener onCancelButtonClickListener) {
        this.onCancelButtonClickListener = onCancelButtonClickListener;
        return this;
    }
    
    public BottomMenuDialog setStyle(DialogSettings.STYLE style) {
        if (isAlreadyShown) {
            error("必须使用 build(...) 方法创建时，才可以使用 setStyle(...) 来修改对话框主题或风格。");
            return this;
        }
        
        this.style = style;
        switch (this.style) {
            case STYLE_IOS:
                build(this, R.layout.dialogx_layout_bottom_menu_ios);
                break;
            case STYLE_SQUARE:
                build(this, R.layout.dialogx_layout_bottom_menu_square);
                break;
            case STYLE_MATERIAL:
                build(this, R.layout.dialogx_layout_bottom_menu_material);
                break;
        }
        
        return this;
    }
    
    public DialogSettings.THEME getTheme() {
        return theme;
    }
    
    public BottomMenuDialog setTheme(DialogSettings.THEME theme) {
        
        if (isAlreadyShown) {
            error("必须使用 build(...) 方法创建时，才可以使用 setTheme(...) 来修改对话框主题或风格。");
            return this;
        }
        
        this.theme = theme;
        refreshView();
        return this;
    }
    
    public BaseAdapter getCustomAdapter() {
        return customAdapter;
    }
    
    public BottomMenuDialog setCustomAdapter(BaseAdapter customAdapter) {
        this.customAdapter = customAdapter;
        return this;
    }
    
    private float boxBodyOldY;
    private int step;
    private boolean isTouchDown;
    private float touchDownY;
    
    private View.OnTouchListener listViewTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    isTouchDown = true;
                    touchDownY = event.getY();
                    boxBodyOldY = boxBody.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (isTouchDown) {
                        float deltaY = event.getY() - touchDownY;
                        float aimY = boxBody.getY() + deltaY;
                        if (aimY < 0) {
                            aimY = 0;
                        }
                        if (isListViewOnTop() && aimY != 0) {
                            boxBody.setY(aimY);
                            return true;
                        } else {
                            touchDownY = event.getY();
                            boxBody.setY(0);
                        }
                    }
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    if (isTouchDown) {
                        float deltaY = boxBody.getY() - boxBodyOldY;
                        
                        if (deltaY >= -dip2px(50) && deltaY <= dip2px(50)) {
                            //几乎没动，回到原来位置
                            boxBody.animate().setDuration(300).translationY(boxBodyOldY);
                            step = 0;
                        } else {
                            if (deltaY > dip2px(150)) {
                                //向下(重)
                                boxBody.animate().setDuration(300).translationY(boxBody.getHeight()).withEndAction(new Runnable() {
                                    @Override
                                    public void run() {
                                        doDismiss();
                                    }
                                });
                                v.onTouchEvent(MotionEvent.obtain(0, 0, MotionEvent.ACTION_CANCEL, 0, 0, 0));       //释放点击事件
                                return true;
                            } else if (deltaY > dip2px(50)) {
                                //向下(轻)
                                switch (step) {
                                    case 0:
                                        boxBody.animate().setDuration(300).translationY(boxBody.getHeight()).withEndAction(new Runnable() {
                                            @Override
                                            public void run() {
                                                doDismiss();
                                            }
                                        });
                                        break;
                                    case 1:
                                        boxBody.animate().setDuration(300).translationY(boxBody.getHeight() / 2);
                                        step = 0;
                                        break;
                                }
                                v.onTouchEvent(MotionEvent.obtain(0, 0, MotionEvent.ACTION_CANCEL, 0, 0, 0));       //释放点击事件
                                return true;
                            } else {
                                //向上
                                boxBody.animate().setDuration(300).translationY(0);
                                step = 1;
                                v.onTouchEvent(MotionEvent.obtain(0, 0, MotionEvent.ACTION_CANCEL, 0, 0, 0));       //释放点击事件
                                return true;
                            }
                        }
                    }
                    isTouchDown = false;
                    break;
            }
            if (v instanceof ListView) {
                return false;
            } else {
                return true;
            }
        }
    };
    
    private boolean isListViewOnTop() {
        View c = listMenu.getChildAt(0);
        if (c == null) {
            return false;
        }
        int firstVisiblePosition = listMenu.getFirstVisiblePosition();
        if (firstVisiblePosition != 0) {
            return false;
        }
        int top = c.getTop();
        return top == 0;
    }
    
    public BottomMenuDialog setCustomDialogStyleId(int customDialogStyleId) {
        if (isAlreadyShown) {
            error("必须使用 build(...) 方法创建时，才可以使用 setTheme(...) 来修改对话框主题或风格。");
            return this;
        }
        this.customDialogStyleId = customDialogStyleId;
        return this;
    }
    
    public String toString() {
        return getClass().getSimpleName() + "@" + Integer.toHexString(hashCode());
    }
    
    public OnBackClickListener getOnBackClickListener() {
        return onBackClickListener;
    }
    
    public BottomMenuDialog setOnBackClickListener(OnBackClickListener onBackClickListener) {
        this.onBackClickListener = onBackClickListener;
        return this;
    }
    
    public boolean getCancelable() {
        return cancelable == BOOLEAN.TRUE;
    }
    
    public BottomMenuDialog setCancelable(boolean enable) {
        this.cancelable = enable ? BOOLEAN.TRUE : BOOLEAN.FALSE;
        if (dialog != null) dialog.get().setCancelable(cancelable == BOOLEAN.TRUE);
        return this;
    }
}
