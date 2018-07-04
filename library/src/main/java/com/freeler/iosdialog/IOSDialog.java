package com.freeler.iosdialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.ColorRes;
import android.support.annotation.LayoutRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Dialog样式,和ios一致
 * Dialog在建造者模式中充当Produce角色
 *
 * @author xuzeyang
 */
public class IOSDialog {

    private Context context;

    private CharSequence strTitle;      //标题内容
    private CharSequence strSubTitle;   //副标题内容
    private CharSequence strNegative;   //取消按钮内容
    private CharSequence strPositive;   //确定按钮内容

    private int sizeTitle;              //标题文字大小
    private int sizeSubTitle;           //副标题文字大小
    private int sizeNegative;           //取消按钮文字大小
    private int sizePositive;           //确定按钮文字大小

    @ColorRes
    private int colorTitle;             //标题内容颜色
    @ColorRes
    private int colorSubTitle;          //副标题内容颜色
    @ColorRes
    private int colorNegative;          //取消按钮文字颜色
    @ColorRes
    private int colorPositive;          //确定按钮文字颜色

    private OnClickNegativeListener onClickNegativeListener;    //取消按钮点击监听
    private OnClickPositiveListener onClickPositiveListener;    //确定按钮点击监听
    private OnDismissListener onDismissListener;                //dialog弹框外点击监听
    private boolean cancelable;     //是否允许点击外侧关闭对话框,默认关闭
    @LayoutRes
    private int layId;              //布局id
    private int width;              //dialog宽度,单位dp

    private TextView tvTitle;
    private TextView tvSubTitle;
    private TextView tvNegative;
    private TextView tvPositive;
    private AlertDialog dialog;

    private IOSDialog(Builder builder) {
        this.context = builder.context;

        this.strTitle = builder.strTitle;
        this.strSubTitle = builder.strSubTitle;
        this.strNegative = builder.strNegative;
        this.strPositive = builder.strPositive;

        this.sizeTitle = builder.sizeTitle;
        this.sizeSubTitle = builder.sizeSubTitle;
        this.sizeNegative = builder.sizeNegative;
        this.sizePositive = builder.sizePositive;

        this.colorTitle = builder.colorTitle;
        this.colorSubTitle = builder.colorSubTitle;
        this.colorNegative = builder.colorNegative;
        this.colorPositive = builder.colorPositive;

        this.onClickNegativeListener = builder.onClickNegativeListener;
        this.onClickPositiveListener = builder.onClickPositiveListener;
        this.onDismissListener = builder.onDismissListener;
        this.cancelable = builder.cancelable;
        this.layId = builder.layId;
        this.width = builder.width;
        initView();
    }

    public static class Builder {
        private Context context;
        private CharSequence strTitle;          //标题内容
        private CharSequence strSubTitle;       //副标题内容
        private CharSequence strNegative;       //取消按钮内容
        private CharSequence strPositive;       //确定按钮内容

        private int sizeTitle;                  //标题文字大小
        private int sizeSubTitle;               //副标题文字大小
        private int sizeNegative;               //取消按钮文字大小
        private int sizePositive;               //确定按钮文字大小

        @ColorRes
        private int colorTitle;                 //标题内容颜色
        @ColorRes
        private int colorSubTitle;              //副标题内容颜色
        @ColorRes
        private int colorNegative;              //取消按钮文字颜色
        @ColorRes
        private int colorPositive;              //确定按钮文字颜色

        private OnClickNegativeListener onClickNegativeListener;    //取消按钮点击监听
        private OnClickPositiveListener onClickPositiveListener;    //确定按钮点击监听
        private OnDismissListener onDismissListener;                //dialog弹框外点击监听
        private boolean cancelable = true;      //是否允许点击外侧关闭对话框,默认关闭
        @LayoutRes
        private int layId;                       //布局id
        private int width;                      //dialog宽度,单位dp

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setTitle(CharSequence sequence) {
            this.strTitle = sequence;
            return this;
        }

        public Builder setSubTitle(CharSequence sequence) {
            this.strSubTitle = sequence;
            return this;
        }

        public Builder setNegativeText(CharSequence sequence) {
            this.strNegative = sequence;
            return this;
        }

        public Builder setPositiveText(CharSequence sequence) {
            this.strPositive = sequence;
            return this;
        }

        public Builder setTitleSize(int size) {
            this.sizeTitle = size;
            return this;
        }

        public Builder setSubTitleSize(int size) {
            this.sizeSubTitle = size;
            return this;
        }

        public Builder setNegativeSize(int size) {
            this.sizeNegative = size;
            return this;
        }

        public Builder setPositiveSize(int size) {
            this.sizePositive = size;
            return this;
        }

        public Builder setTitleColor(@ColorRes int color) {
            this.colorTitle = color;
            return this;
        }

        public Builder setSubTitleColor(@ColorRes int color) {
            this.colorSubTitle = color;
            return this;
        }

        public Builder settNegativeColor(@ColorRes int color) {
            this.colorNegative = color;
            return this;
        }

        public Builder setPositiveColor(@ColorRes int color) {
            this.colorPositive = color;
            return this;
        }

        public Builder setContentView(@LayoutRes int layId) {
            this.layId = layId;
            return this;
        }

        public Builder setDialogWidth(int width) {
            this.width = width;
            return this;
        }

        public Builder setOnNegativeClickListener(OnClickNegativeListener onClickFirstListener) {
            this.onClickNegativeListener = onClickFirstListener;
            return this;
        }

        public Builder setOnPositiveClickListener(OnClickPositiveListener onClickSecondListener) {
            this.onClickPositiveListener = onClickSecondListener;
            return this;
        }

        public Builder setOnDismissListener(OnDismissListener onDismissListener) {
            this.onDismissListener = onDismissListener;
            return this;
        }

        /**
         * 是否允许点击外侧关闭对话框
         *
         * @param cancelable true:关闭 false:不关闭
         */
        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        /**
         * 此处相当于Produce
         *
         * @return Produce
         */
        public IOSDialog create() {
            return new IOSDialog(this);
        }

        /**
         * 此处相当于Produce
         *
         * @return Produce
         */
        public IOSDialog show() {
            return create();
        }

    }

    private void initView() {
        dismiss();
        dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        setOnDismissListener();
        setCancelable(cancelable);
        Window window = dialog.getWindow();
        if (window != null) {
            if (layId == 0) {
                window.setContentView(R.layout.dialog_default);
            } else {
                window.setContentView(layId);
            }

            WindowManager.LayoutParams params = window.getAttributes();
            if (width == 0) {
                params.width = DensityUtils.dip2px(context, 250);
            } else {
                params.width = DensityUtils.dip2px(context, width);
            }
            window.setBackgroundDrawableResource(android.R.color.transparent);
            window.setAttributes(params);

            tvTitle = window.findViewById(R.id.tv_title);
            tvSubTitle = window.findViewById(R.id.tv_content);
            tvNegative = window.findViewById(R.id.tv_negative);
            tvPositive = window.findViewById(R.id.tv_positive);

            setTitle(strTitle);
            setTitleColor(colorTitle);
            setTitleSize(sizeTitle);

            setSubTitle(strSubTitle);
            setSubTitleColor(colorSubTitle);
            setSubTitleSize(sizeSubTitle);

            setNegative(strNegative);
            setNegativeColor(colorNegative);
            setNegativeSize(sizeNegative);
            setNegativeClick();

            setPositive(strPositive);
            setPositiveColor(colorPositive);
            setPositiveSize(sizePositive);
            setPositiveClick();
        }
    }

    /**
     * 是否允许点击外侧关闭对话框
     *
     * @param cancelable true:关闭 false:不关闭
     */
    private void setCancelable(boolean cancelable) {
        dialog.setCancelable(cancelable);
    }

    /**
     * 设置标题文字
     *
     * @param charSequence 文字内容
     */
    private void setTitle(CharSequence charSequence) {
        if (tvTitle != null && charSequence != null) {
            tvTitle.setText(charSequence);
            tvTitle.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置标题文字颜色
     *
     * @param id 文字颜色
     */
    private void setTitleColor(@ColorRes int id) {
        if (tvTitle != null && id != 0) {
            tvTitle.setTextColor(ContextCompat.getColor(context, id));
        }
    }

    /**
     * 设置标题文字大小
     *
     * @param size 文字大小
     */
    private void setTitleSize(int size) {
        if (tvTitle != null && size != 0) {
            tvTitle.setTextSize(size);
        }
    }

    /**
     * 设置副标题文字
     *
     * @param charSequence 文字内容
     */
    private void setSubTitle(CharSequence charSequence) {
        if (tvSubTitle != null && charSequence != null) {
            tvSubTitle.setText(charSequence);
            tvSubTitle.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置副标题文字颜色
     *
     * @param id 文字颜色
     */
    private void setSubTitleColor(@ColorRes int id) {
        if (tvSubTitle != null && id != 0) {
            tvSubTitle.setTextColor(ContextCompat.getColor(context, id));
        }
    }

    /**
     * 设置副标题文字大小
     *
     * @param size 文字大小
     */
    private void setSubTitleSize(int size) {
        if (tvSubTitle != null && size != 0) {
            tvSubTitle.setTextSize(size);
        }
    }

    /**
     * 设置第一按钮文字
     *
     * @param charSequence 文字内容
     */
    private void setNegative(CharSequence charSequence) {
        if (tvNegative != null && charSequence != null) {
            tvNegative.setText(charSequence);
            tvNegative.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置第一按钮文字颜色
     *
     * @param color 文字颜色
     */
    private void setNegativeColor(@ColorRes int color) {
        if (tvNegative != null && color != 0) {
            tvNegative.setTextColor(ContextCompat.getColor(context, color));
        }
    }

    /**
     * 设置第一按钮文字大小
     *
     * @param size 文字大小
     */
    private void setNegativeSize(int size) {
        if (tvNegative != null && size != 0) {
            tvNegative.setTextSize(size);
        }
    }

    /**
     * 设置第二按钮文字
     *
     * @param charSequence 文字内容
     */
    private void setPositive(CharSequence charSequence) {
        if (tvPositive != null && charSequence != null) {
            tvPositive.setText(charSequence);
            tvPositive.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置第二按钮文字颜色
     *
     * @param color 文字颜色
     */
    private void setPositiveColor(@ColorRes int color) {
        if (tvPositive != null && color != 0) {
            tvPositive.setTextColor(ContextCompat.getColor(context, color));
        }
    }

    /**
     * 设置第二按钮文字大小
     *
     * @param size 文字大小
     */
    private void setPositiveSize(int size) {
        if (tvPositive != null && size != 0) {
            tvPositive.setTextSize(size);
        }
    }

    /**
     * 设置第一按钮点击事件
     */
    private void setNegativeClick() {
        if (tvNegative != null && onClickNegativeListener != null) {
            tvNegative.setOnClickListener(v -> onClickNegativeListener.onClick(v, dialog));
        }
    }

    /**
     * 设置第二按钮点击事件
     */
    private void setPositiveClick() {
        if (tvPositive != null && onClickPositiveListener != null) {
            tvPositive.setOnClickListener(v -> onClickPositiveListener.onClick(v, dialog));
        }
    }

    /**
     * dialog消失时候监听
     */
    private void setOnDismissListener() {
        if (dialog != null && onDismissListener != null) {
            dialog.setOnDismissListener(dialog -> onDismissListener.onDismiss(dialog));
        }
    }

    public boolean isShowing() {
        return dialog.isShowing();
    }

    public void dismiss() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    public interface OnClickNegativeListener {
        void onClick(View v, AlertDialog dialog);
    }

    public interface OnClickPositiveListener {
        void onClick(View v, AlertDialog dialog);
    }

    /**
     * Interface used to allow the creator of a dialog to run some code when the
     * dialog is dismissed.
     */
    public interface OnDismissListener {
        /**
         * This method will be invoked when the dialog is dismissed.
         *
         * @param dialog the dialog that was dismissed will be passed into the
         *               method
         */
        void onDismiss(DialogInterface dialog);
    }


}