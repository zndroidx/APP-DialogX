# DialogX Instruction 

怎么拦截物理返回键，让广告弹框消失
```java
@Override
public void onBackPressed() {
    if (adManagerDialog.isShowing())
        adManagerDialog.dismissAdDialog();
    else
        super.onBackPressed();
}
```