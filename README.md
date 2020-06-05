# DialogX Instruction

## 引入（Android Studios）

在项目根目录 `build.gradle` 下添加仓库地址

```sh
allprojects {
    repositories {
        ...
        maven { url '暂无' }
        //或者
        maven { url '暂无' }
        ...  
    }
}
```

在应用Module `app` 根目录 `build.gradle` 添加如下配置

```sh
implementation 'com.zndroid:DialogX:1.0.3'//（建议指定版本）
//或者
implementation 'com.zndroid:DialogX:latest.release'//保持最新版

//androidx 版本
implementation 'com.zndroidx:DialogX:1.0.3'
```

然后点击同步按钮进行代码同步

<img src=".\doc\imgs\image-20200604170920064.png" alt="image-20200604170920064" style="zoom:50%;" />

## 使用

例子中 `this`均指当前上下文 `AppCompatActivity  `类型（方便使用新特性DialogFragment），`view`为点击事件触发的 `View`，主题风格为 `IOS`风格。

### 初始化

在使用之前请先初始化对话框配置

```jav
DialogSettings.init();
```

可以根据所需进行初始化全局配置，当然也可以在使用之前再次通过设置 `DialogSettings`动态指定，以下是配置参数：

```java
DialogSettings.init(context);                           //初始化清空 BaseDialog 队列
DialogSettings.DEBUGMODE = (boolean);                   //是否允许打印日志
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
DialogSettings.blurAlpha = (int);                       //开启模糊后的透明度（0~255）
DialogSettings.systemDialogStyle = (styleResId);        //自定义系统对话框style，注意设置此功能会导致原对话框风格和动画失效
DialogSettings.dialogLifeCycleListener = (DialogLifeCycleListener);  //全局Dialog生命周期监听器
DialogSettings.defaultCancelButtonText = (String);      //设置 BottomMenu和ShareDialog 默认“取消”按钮的文字
DialogSettings.tipBackgroundResId = (drawableResId);    //设置 TipDialog和WaitDialog的背景资源
DialogSettings.tipTextInfo = (InputInfo);               //设置 TipDialog和WaitDialog文字样式
DialogSettings.autoShowInputKeyboard = (boolean);       //设置 InputDialog 是否自动弹出输入法
DialogSettings.okButtonDrawable = (drawable);           //设置确定按钮背景资源
DialogSettings.cancelButtonDrawable = (drawable);       //设置取消按钮背景资源
DialogSettings.otherButtonDrawable = (drawable);        //设置其他按钮背景资源

//检查 Renderscript 兼容性，若设备不支持，DialogSettings.isUseBlur 会自动关闭；
boolean renderscriptSupport = DialogSettings.checkRenderscriptSupport(context)
```

如果需要开启模糊效果，即 DialogSettings.isUseBlur = true; 需要进行额外 renderscript 配置，需要注意的是在部分低配置手机上此功能效率可能存在问题。

在 app 的 build.gradle 中添加以下代码：

```sh
android {
	...
	defaultConfig {
		...
		renderscriptTargetApi 17
		renderscriptSupportModeEnabled true
	}
}

//上述配置为全局配置，即在不进行特意定制的情况下，所有对话框组件默认按照此配置显示，如有特殊需求，可以通过各对话框组件的 build(...) 方法创建对话框后进行配置，最后使用 show() 方法执行显示即可。
```
### 提示框

用于一些操作提示，默认对话框会自动消失

```java
TipDialog.show(this, "取消失败", TipDialog.TYPE.ERROR);//目前支持类型：WARNING, SUCCESS, ERROR, OTHER
```

也可以使用 `TipDialog.build(AppCompatActivity context)`进行深度定制。

效果图：

<img src=".\doc\imgs\image-20200604173320223.png" alt="image-20200604173320223" style="zoom: 67%;" />

### 耗时操作框

用于一些耗时人机交互操作场景，在耗时操作完成后调用 `dismiss()`

```java
//该例子为组合形式
WaitDialog.show(this, "请稍等...");
        
view.postDelayed(new Runnable() {
     @Override
            public void run() {
                WaitDialog.dismiss();
                //模拟网络操作
                TipDialog.show(MainActivity.this, "成功！！！", TipDialog.TYPE.SUCCESS);
            }
}, 3000);
```

也可以使用 `WaitDialog.build(AppCompatActivity context)`进行深度定制。

效果图：

<img src=".\doc\imgs\image-20200604174220038.png" alt="image-20200604174220038" style="zoom:50%;" />

### 信息对话框

用于展示消息对话，并接收用户反馈的场景，最多支持三个按钮的交互

```java
MessageDialog.show(this, "温馨提示", "检测到系统更新，是否更新", "体验新版", "旧版凑活(不建议)")
    .setOkButton(new OnDialogButtonClickListener() {
        @Override
		public boolean onClick(BaseDialog baseDialog, View v) {
			toast("去更新");
			return false;//返回true 对话框不会消失，点击事件被消费了不会透传。
		}
	})
    .setCancelButton(new OnDialogButtonClickListener() {
	@Override
    public boolean onClick(BaseDialog baseDialog, View v) {
        toast("忍痛离开");
		return false;//同上
    }
});
```

也可以使用 `MessageDialog.build(AppCompatActivity context)`进行深度定制。

效果图:

<img src=".\doc\imgs\image-20200604180031542.png" alt="image-20200604180031542" style="zoom:50%;" />

### 底部弹出菜单框

用于弹出底部菜单的场景

```java
BottomMenuDialog.show(this,
                      "请选择性别",
                      new String[]{"男", "女"}, new OnMenuItemClickListener() {
	@Override
    public void onClick(String text, int index) {
        toast(text);
    }
});
```

也可以使用 `BottomMenuDialog.build(AppCompatActivity context)`进行深度定制。

效果图：

<img src=".\doc\imgs\image-20200604175104861.png" alt="image-20200604175104861" style="zoom:50%;" />

### 底部分享框

我们的应用如果有分享功能，可以直接使用该功能

```java
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
```

也可以使用 `ShareDialog.build(AppCompatActivity context)`进行深度定制。

效果图：

<img src=".\doc\imgs\image-20200604180414834.png" alt="image-20200604180414834" style="zoom:50%;" />

### 通知栏

用于模拟通知的对话框

```java
NotificationDialog.show(this, 
                        "您收到一条新的消息",
                        "今晚吃什么", 
                        R.mipmap.ic_launcher, NotificationDialog.DURATION_TIME.LONG)
    .showNotification();
```

也可以使用 `NotificationDialog.build(AppCompatActivity context)`进行深度定制。

效果图：

<img src=".\doc\imgs\image-20200604180907472.png" alt="image-20200604180907472" style="zoom:50%;" />

### 带输入的对话框

用于采用对话框的形式接收用户输入，默认一个输入框

```java
DialogSettings.autoShowInputKeyboard = false;//设置不自动弹出软键盘

InputDialog.show(this, "备注", "请输入描述", "确定", "取消")
    .setOkButton(new OnInputDialogButtonClickListener(){
        @Override
        public boolean onClick(BaseDialog baseDialog, View v, String inputStr) {
            toast(inputStr);
            return false;
        }
})
.setHintText("不超过20个字，仅测试使用，无意义");
```

也可以使用 `InputDialog.build(AppCompatActivity context)`进行深度定制。

效果图：

<img src=".\doc\imgs\image-20200604181135369.png" alt="image-20200604181135369" style="zoom:50%;" />

### 全屏对话框

用于全屏场景下的对话框（具有层叠效果）

```java
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
```

也可以使用 `FullDialog.build(AppCompatActivity context)`进行深度定制。

效果图：

<img src=".\doc\imgs\image-20200604181621838.png" alt="image-20200604181722136" style="zoom:67%;" />

### 自定义对话框

支持完全自定义对话框，默认会处理显示与消失

```java
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

CustomDialog.show(this, R.layout.layout_custom_dialog, new CustomDialogView());
```

也可以使用 `CustomDialog.build(AppCompatActivity context)`进行深度定制。

效果图：

<img src=".\doc\imgs\image-20200604182204876.png" alt="image-20200604182204876" style="zoom:50%;" />

### 广告弹框

默认实现了广告弹框样式，支持横屏和竖屏两种方式，支持进出动画定制、背景透明度+颜色定制、广告背景圆角、弹性动画速度、多广告显示、多广告滑动动画定制等，详细参照API。

```java
public void ad_dialog(View view) {
	List<AdInfo> list = new CopyOnWriteArrayList<>();
	AdInfo info1 = new AdInfo();
    info1.setTitle("ad 1");
    info1.setActivityImg("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1591190972921&di=39b1331da11282a89e5768136dbf1289&imgtype=0&src=http%3A%2F%2Fa3.att.hudong.com%2F14%2F75%2F01300000164186121366756803686.jpg");
    
    AdInfo info2 = new AdInfo();
	info2.setTitle("ad 2");
    info2.setActivityImg("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1591190972921&di=f80c1ee10bf1a16a7c0fdcb09f39005d&imgtype=0&src=http%3A%2F%2Fp2.so.qhimgs1.com%2Ft01dfcbc38578dac4c2.jpg");
    
    list.add(info1);
    list.add(info2);

	adDialog = new ADDialog(this, list);
	adDialog.setAnimBackViewTransparent(true)
        .setCornersRadius(20f)
        .setOnImageClickListener(new ADDialog.OnImageClickListener() {
            @Override
            public void onImageClick(View view, AdInfo advInfo) {
                toast(advInfo.getTitle() + "被点击");
            }
        })
        .showAdDialog(ADConstant.ANIM_DOWN_TO_UP);


        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                adDialog.dismissAdDialog();//广告自动消失
            }
        }, 6000);
    }

@Override
public void onBackPressed() {//用于处理物理返回键，如果广告弹框存在，可以通过物理返回键将其消失
	if (adDialog.isShowing())
		adDialog.dismissAdDialog();
    else
        super.onBackPressed();
}
```

效果图：

<img src=".\doc\imgs\image-20200604183150960.png" alt="image-20200604183150960" style="zoom:50%;" />

<img src=".\doc\imgs\image-20200604183253819.png" alt="image-20200604183253819" style="zoom:50%;" />

## Q&A

1. 横竖屏切换

   横竖屏切换时如果要保持对话框状态，可以在当前Activity xml中添加如下配置，防止对话框重绘，但是广告弹框关闭按钮将被隐藏（临时可以参照范例添加自动销毁功能 TODO bug）

```java
android:configChanges="orientation|keyboardHidden|screenSize"
```

2. 物理返回键

   广告弹框怎么拦截物理返回键，让广告弹框消失

```java
@Override
public void onBackPressed() {
    if (adDialog.isShowing())
        adDialog.dismissAdDialog();
    else
        super.onBackPressed();
}
```