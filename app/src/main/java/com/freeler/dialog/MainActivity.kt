package com.freeler.dialog

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toast
import com.freeler.iosdialog.IOSDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var dialog: IOSDialog? = null
    private var dialogCreate: IOSDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * 创建并显示Dialog
         */
        mBtn.setOnClickListener {
            dialog = IOSDialog.Builder(this)
                    .setTitle("我是Dialog")
                    .setSubTitle("IOS")
                    .setNegativeText("取消")
                    .setPositiveText("确定")
                    .setTitleColor(R.color.colorAccent)
                    .setSubTitleColor(R.color.colorAccent)
                    .setPositiveColor(R.color.colorAccent)
                    .settNegativeColor(R.color.colorAccent)
                    .setDividerColor(R.color.colorAccent)
                    .setOnNegativeClickListener { v, dialog -> dialog?.dismiss() }
                    .setOnPositiveClickListener { v, dialog -> dialog?.dismiss() }
                    .setCancelable(false)
                    .show()

            //获取对象
            val alertDialog = dialog?.alertDialog
            val window = alertDialog?.window

            //设置自定义childView相关
            val titleTextView = dialog?.getView<TextView>(R.id.tv_title)
            dialog?.setChildViewText(R.id.tv_title, "这是标题")
            dialog?.setChildViewColor(R.id.tv_title, R.color.colorAccent)
            dialog?.setChildViewSize(R.id.tv_title, 14)
            dialog?.setChildViewClick(R.id.tv_title) {
                Toast.makeText(this@MainActivity, "点击标题", Toast.LENGTH_SHORT).show()
            }

        }

        /**
         * 创建但不显示Dialog
         */
        mBtnCreate.setOnClickListener {
            dialogCreate = IOSDialog.Builder(this)
                    .setTitle("显示Dialog")
                    .setSubTitle("IOS")
                    .setNegativeText("取消")
                    .setPositiveText("确定")
                    .setOnNegativeClickListener { v, dialog -> dialog?.dismiss() }
                    .setOnPositiveClickListener { v, dialog -> dialog?.dismiss() }
                    .setCancelable(false)
                    .create()

            dialogCreate?.apply {
                Toast.makeText(this@MainActivity, "Dialog创建成功", Toast.LENGTH_SHORT).show()
            }
        }

        /**
         * 显示Dialog
         */
        mBtnShow.setOnClickListener {
            dialogCreate?.show()
        }

        /**
         * 销毁Dialog对象
         */
        mBtnDestroy.setOnClickListener {
            dialogCreate?.dismiss()
            dialogCreate = null

        }

    }
}
