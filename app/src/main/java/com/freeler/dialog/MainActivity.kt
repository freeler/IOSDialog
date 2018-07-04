package com.freeler.dialog

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.freeler.iosdialog.IOSDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBtn.setOnClickListener {
            IOSDialog.Builder(this)
                    .setTitle("我是Dialog")
                    .setSubTitle("IOS")
                    .setNegativeText("取消")
                    .setPositiveText("确定")
                    .setOnNegativeClickListener { v, dialog -> dialog?.dismiss() }
                    .setOnPositiveClickListener { v, dialog -> dialog?.dismiss() }
                    .setCancelable(false)
                    .show()
        }
    }
}
