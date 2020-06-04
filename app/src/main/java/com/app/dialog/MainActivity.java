package com.app.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zndroid.dialogx.ADDialog;
import com.zndroid.dialogx.BottomMenuDialog;
import com.zndroid.dialogx.CustomDialog;
import com.zndroid.dialogx.FullScreenDialog;
import com.zndroid.dialogx.InputDialog;
import com.zndroid.dialogx.MessageDialog;
import com.zndroid.dialogx.NotificationDialog;
import com.zndroid.dialogx.ShareDialog;
import com.zndroid.dialogx.TipDialog;
import com.zndroid.dialogx.WaitDialog;
import com.zndroid.dialogx.ad.ADConstant;
import com.zndroid.dialogx.core.BaseDialog;
import com.zndroid.dialogx.core.DialogSettings;
import com.zndroid.dialogx.interfaces.OnDialogButtonClickListener;
import com.zndroid.dialogx.interfaces.OnInputDialogButtonClickListener;
import com.zndroid.dialogx.interfaces.OnMenuItemClickListener;
import com.zndroid.dialogx.model.AdInfo;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MainActivity extends AppCompatActivity {
    private ADDialog adManagerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void init() {
        //初始化对话框整体配置
        DialogSettings.init();
        DialogSettings.DEBUGMODE = true;
        DialogSettings.checkRenderscriptSupport(this);
        DialogSettings.isUseBlur = true;
        DialogSettings.autoShowInputKeyboard = true;
        DialogSettings.style = DialogSettings.STYLE.STYLE_IOS;
        DialogSettings.theme = DialogSettings.THEME.LIGHT;

        /**以下是具体内容说明
         DialogSettings.isUseBlur = (boolean);                   //是否开启模糊效果，默认关闭
         DialogSettings.modalDialog = (boolean);                 //是否开启模态窗口模式，一次显示多个对话框将以队列形式一个一个显示，默认关闭
         DialogSettings.style = (DialogSettings.STYLE);          //全局主题风格，提供三种可选风格，STYLE_MATERIAL, STYLE_SQUARE, STYLE_IOS
         DialogSettings.theme = (DialogSettings.THEME);          //全局对话框明暗风格，提供两种可选主题，LIGHT, DARK
         DialogSettings.tipTheme = (DialogSettings.THEME);       //全局提示框明暗风格，提供两种可选主题，LIGHT, DARK
         DialogSettings.titleTextInfo = (TextInfo);              //全局对话框标题文字样式
         DialogSettings.menuTitleInfo = (TextInfo);              //全局菜单标题文字样式
         DialogSettings.menuTextInfo = (TextInfo);               //全局菜单列表文字样式
         DialogSettings.contentTextInfo = (TextInfo);            //全局正文文字样式
         DialogSettings.buttonTextInfo = (TextInfo);             //全局默认按钮文字样式
         DialogSettings.buttonPositiveTextInfo = (TextInfo);     //全局焦点按钮文字样式（一般指确定按钮）
         DialogSettings.inputInfo = (InputInfo);                 //全局输入框文本样式
         DialogSettings.backgroundColor = (ColorInt);            //全局对话框背景颜色，值0时不生效
         DialogSettings.cancelable = (boolean);                  //全局对话框默认是否可以点击外围遮罩区域或返回键关闭，此开关不影响提示框（TipDialog）以及等待框（TipDialog）
         DialogSettings.cancelableTipDialog = (boolean);         //全局提示框及等待框（WaitDialog、TipDialog）默认是否可以关闭
         DialogSettings.DEBUGMODE = (boolean);                   //是否允许打印日志
         DialogSettings.blurAlpha = (int);                       //开启模糊后的透明度（0~255）
         DialogSettings.systemDialogStyle = (styleResId);        //自定义系统对话框style，注意设置此功能会导致原对话框风格和动画失效
         DialogSettings.dialogLifeCycleListener = (DialogLifeCycleListener);  //全局Dialog生命周期监听器
         DialogSettings.defaultCancelButtonText = (String);      //设置 BottomMenu 和 ShareDialog 默认“取消”按钮的文字
         DialogSettings.tipBackgroundResId = (drawableResId);    //设置 TipDialog 和 WaitDialog 的背景资源
         DialogSettings.tipTextInfo = (InputInfo);               //设置 TipDialog 和 WaitDialog 文字样式
         DialogSettings.autoShowInputKeyboard = (boolean);       //设置 InputDialog 是否自动弹出输入法
         DialogSettings.okButtonDrawable = (drawable);           //设置确定按钮背景资源
         DialogSettings.cancelButtonDrawable = (drawable);       //设置取消按钮背景资源
         DialogSettings.otherButtonDrawable = (drawable);        //设置其他按钮背景资源

         //检查 Renderscript 兼容性，若设备不支持，DialogSettings.isUseBlur 会自动关闭；
         boolean renderscriptSupport = DialogSettings.checkRenderscriptSupport(context)

         DialogSettings.init(context);                           //初始化清空 BaseDialog 队列

         如果需要开启模糊效果，即 DialogSettings.isUseBlur = true; 需要进行额外 renderscript 配置，需要注意的是在部分低配置手机上此功能效率可能存在问题。

         在 app 的 build.gradle 中添加以下代码：

         android {
         ...
         defaultConfig {
         ...

         renderscriptTargetApi 17
         renderscriptSupportModeEnabled true
         }
         }

         上述配置为全局配置，即在不进行特意定制的情况下，所有对话框组件默认按照此配置显示，如有特殊需求，可以通过各对话框组件的 build(...) 方法创建对话框后进行配置，最后使用 show() 方法执行显示即可。

         */
    }

    public void tip_dialog(View view) {
        TipDialog.show(this, "取消失败", TipDialog.TYPE.ERROR);
    }

    public void bottom_dialog(View view) {
        BottomMenuDialog.show(this, "请选择性别", new String[]{"男", "女"}, new OnMenuItemClickListener() {
            @Override
            public void onClick(String text, int index) {
                toast(text);
            }
        });
    }

    public void wait_dialog(View view) {
        WaitDialog.show(this, "请稍等...");
        WaitDialog.dismiss(3000);
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                //模拟网络操作
                TipDialog.show(MainActivity.this, "成功！！！", TipDialog.TYPE.SUCCESS);
            }
        }, 3000);
    }

    public void share_dialog(View view) {
        List<ShareDialog.Item> list = new CopyOnWriteArrayList<>();
        ShareDialog.Item item1 = new ShareDialog.Item(this, R.mipmap.img_qq_ios, "QQ");
        ShareDialog.Item item2 = new ShareDialog.Item(this, R.mipmap.ico_wechat, "微信");
        ShareDialog.Item item3 = new ShareDialog.Item(this, R.mipmap.img_weibo_ios, "微博");
        list.add(item1);
        list.add(item2);
        list.add(item3);
        ShareDialog.show(this, list, new ShareDialog.OnItemClickListener() {
            @Override
            public boolean onClick(ShareDialog shareDialog, int index, ShareDialog.Item item) {
                toast(item.getText());
                return false;
            }
        });
    }

    public void notification_dialog(View view) {
        NotificationDialog.show(this, "您收到一条新的消息", "今晚吃什么", R.mipmap.ic_launcher, NotificationDialog.DURATION_TIME.LONG).showNotification();
    }

    public void message_dialog(View view) {
        MessageDialog.show(this, "温馨提示", "检测到系统更新，是否更新", "体验新版", "旧版凑活(不建议)")
                .setOkButton(new OnDialogButtonClickListener() {
                    @Override
                    public boolean onClick(BaseDialog baseDialog, View v) {
                        toast("去更新");
                        return false;
                    }
                }).setCancelButton(new OnDialogButtonClickListener() {
            @Override
            public boolean onClick(BaseDialog baseDialog, View v) {
                toast("忍痛离开");
                return false;
            }
        });
    }

    public void input_dialog(View view) {
        DialogSettings.autoShowInputKeyboard = false;//可以设置是否自动弹出软键盘
        InputDialog.show(this, "备注", "请输入描述", "确定", "取消")
                .setOkButton(new OnInputDialogButtonClickListener(){

                    @Override
                    public boolean onClick(BaseDialog baseDialog, View v, String inputStr) {
                        toast(inputStr);
                        return false;
                    }
                }).setHintText("不超过20个字，仅测试使用，无意义");
    }


    private class FullDialogView implements FullScreenDialog.OnBindView {

        @Override
        public void onBind(FullScreenDialog dialog, View rootView) {
            Button button = rootView.findViewById(R.id.btn_full);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toast("全屏状态，按钮被点击");
                }
            });
        }
    }

    public void full_dialog(View view) {
        FullScreenDialog.show(this, R.layout.activity_main, new FullDialogView());
    }

    private class CustomDialogView implements CustomDialog.OnBindView {

        @Override
        public void onBind(CustomDialog dialog, View v) {
            v.findViewById(R.id.tv_cus).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toast("自定义对话框内容被点击了");
                }
            });
        }
    }
    public void custom_dialog(View view) {
        CustomDialog.show(this, R.layout.layout_custom_dialog, new CustomDialogView());
    }

    public void ad_dialog(View view) {
        List<AdInfo> list = new CopyOnWriteArrayList<>();
        AdInfo info1 = new AdInfo();
        info1.setTitle("ad 1");
        info1.setActivityImg("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1591190972921&di=39b1331da11282a89e5768136dbf1289&imgtype=0&src=http%3A%2F%2Fa3.att.hudong.com%2F14%2F75%2F01300000164186121366756803686.jpg");
        list.add(info1);

        AdInfo info2 = new AdInfo();
        info2.setTitle("ad 2");
        info2.setActivityImg("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1591190972921&di=f80c1ee10bf1a16a7c0fdcb09f39005d&imgtype=0&src=http%3A%2F%2Fp2.so.qhimgs1.com%2Ft01dfcbc38578dac4c2.jpg");
        list.add(info2);

        adManagerDialog = new ADDialog(this, list);
        adManagerDialog.setAnimBackViewTransparent(true);
        adManagerDialog.showAdDialog(ADConstant.ANIM_DOWN_TO_UP);
        adManagerDialog.setOnImageClickListener(new ADDialog.OnImageClickListener() {
            @Override
            public void onImageClick(View view, AdInfo advInfo) {
                toast(advInfo.getTitle() + "被点击");
            }
        });

        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                adManagerDialog.dismissAdDialog();
            }
        }, 6000);
    }

    @Override
    public void onBackPressed() {
        if (adManagerDialog.isShowing())
            adManagerDialog.dismissAdDialog();
        else
            super.onBackPressed();
    }
}
