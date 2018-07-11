# IOSDialog
[![License](https://img.shields.io/badge/license-Apache%202-green.svg)](https://www.apache.org/licenses/LICENSE-2.0)
 [ ![Download](https://api.bintray.com/packages/freeleragain/maven/IOSDialog/images/download.svg) ](https://bintray.com/freeleragain/maven/IOSDialog/_latestVersion)

Android 仿IOS Dialog UI样式 ,通过纯代码实现 ,不用导入额外的图片 ,可定制化能力强

## Screenshot

![](https://github.com/freeler/IOSDialog/blob/master/screenshot/Screenshot_20180704.png)


## 使用
- 方式 1

```java
compile 'com.freeler:IOSDialog:#lastVersion#'
```

- 方式 2. 拷贝Libs工程里面的library到自己的工程里面

## 范例

- 使用Builder方式创建

```java
IOSDialog dialog = IOSDialog.Builder(this)
        .setTitle("我是Dialog")
        .setSubTitle("IOS")
        .setNegativeText("取消")
        .setPositiveText("确定")
        .setOnNegativeClickListener { v, dialog -> 	dialog.dismiss() }
        .setOnPositiveClickListener { v, dialog -> 	dialog.dismiss() }
        .setCancelable(false)
        .show()

//获取对象
AlertDialog alertDialog = dialog.getAlertDialog()
Window window = alertDialog.getWindow()

//设置自定义childView相关
TextView titleTextView = dialog.getView(R.id.tv_title)
dialog.setChildViewText(R.id.tv_title, "这是标题")
dialog.setChildViewColor(R.id.tv_title, R.color.colorAccent)
dialog.setChildViewSize(R.id.tv_title, 14)
dialog.setChildViewClick(R.id.tv_title, {
	Toast.makeText(this@MainActivity, "点击标题", Toast.LENGTH_SHORT).show()
})
```

- 属性说明

| Attribute                  | 属性含义                                     | 默认值     |
|:---------------------------|:--------------------------------------------|:----------|
| setTitle             		 | 设置标题文字                                 |  |
| setSubTitle       	     | 设置副标题文字(不设置则隐藏该栏)               |  |
| setNegativeText         	 | 设置取消按钮文字                             |  |
| setPositiveText  			 | 设置确定按钮文字                             |  |
| setTitleSize   			 | 设置标题的文字大小                           | 16sp |
| setSubTitleSize 			 | 设置副标题的文字大小                         | 14sp |
| setNegativeSize   		 | 设置取消按钮的文字大小                       | 15sp |
| setPositiveSize         	 | 设置确定按钮的文字大小                       | 15sp |
| setTitleColor      		 | 设置标题的文字颜色                           | #273238      |
| setSubTitleColor  		 | 设置副标题的文字颜色                         | #273238      |
| settNegativeColor   		 | 设置取消按钮的文字颜色                       | #273238     |
| setPositiveColor 			 | 设置确定按钮的文字颜色                       | #273238     |
| setContentView             | 设置自定义布局,控件id要一致 				|    |
| setDialogWidth  			 | 设置对话框的宽度                   		| 250dp      |
| setOnNegativeClickListener | 取消按钮的点击事件                         | |
| setOnPositiveClickListener | 确定按钮的点击事件                     	| |
| setOnDismissListener    	 | 对话框外部的点击事件                       | |
| setCancelable    			 | 是否允许点击外侧关闭对话框                  | false     |
| create    				 | 创建Dialog                                |      |
| show    					 | 创建Dialog并显示                           |    |


- 方法说明

| Attribute                  | 方法含义                                     |
|:---------------------------|:--------------------------------------------|
| setChildViewText           | 设置自定义TextView文字                      |  
| setChildViewColor          | 设置自定义TextView颜色                      |  
| setChildViewSize           | 设置自定义TextView大小                      |  
| setChildViewClick          | 设置自定义childView点击事件                  |  