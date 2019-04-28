package com.freeler.iosdialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.ColorRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.SparseArray;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Dialog样式,和ios一致
 * Dialog在建造者模式中充当Produce角色
 *
 * @author xuzeyang
 */
public class IOSDialog {

    /**
     * Views indexed with their IDs
     */
    private final SparseArray<View> views;

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
    @ColorRes
    private int colorDivider;              //分割线颜色

    private OnClickNegativeListener onClickNegativeListener;    //取消按钮点击监听
    private OnClickPositiveListener onClickPositiveListener;    //确定按钮点击监听
    private OnDismissListener onDismissListener;                //dialog弹框外点击监听
    private boolean cancelable;     //是否允许点击外侧关闭对话框,默认关闭
    @LayoutRes
    private int layId;              //布局id
    private int width;              //dialog宽度,单位dp

    private AlertDialog dialog;

    private IOSDialog(Builder builder) {
        this.views = new SparseArray<>();
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
        this.colorDivider = builder.colorDivider;

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
        @ColorRes
        private int colorDivider;              //分割线颜色

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

        public Builder setDividerColor(@ColorRes int color) {
            this.colorDivider = color;
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
         * 和create()相比,多了显示
         *
         * @return Produce
         */
        public IOSDialog show() {
            IOSDialog iosDialog = create();
            iosDialog.show();
            return iosDialog;
        }

    }

    private void initView() {
        dismiss();
        dialog = new AlertDialog.Builder(context).create();
        dialog.setView(new EditText(context));
        dialog.show();
        dialog.dismiss();
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
                params.width = dip2px(context, 250);
            } else {
                params.width = dip2px(context, width);
            }
            window.setBackgroundDrawableResource(android.R.color.transparent);
            window.setAttributes(params);

            TextView tvTitle = window.findViewById(R.id.tv_title);
            TextView tvSubTitle = window.findViewById(R.id.tv_content);
            TextView tvNegative = window.findViewById(R.id.tv_negative);
            TextView tvPositive = window.findViewById(R.id.tv_positive);
            View divider = window.findViewById(R.id.divider);
            View dividerCenter = window.findViewById(R.id.dividerCenter);

            DialogHelper.setText(tvTitle, strTitle);
            DialogHelper.setText(tvSubTitle, strSubTitle);
            DialogHelper.setText(tvNegative, strNegative);
            DialogHelper.setText(tvPositive, strPositive);

            DialogHelper.setTextSize(tvTitle, sizeTitle);
            DialogHelper.setTextSize(tvSubTitle, sizeSubTitle);
            DialogHelper.setTextSize(tvNegative, sizeNegative);
            DialogHelper.setTextSize(tvPositive, sizePositive);

            DialogHelper.setTextColor(context, tvTitle, colorTitle);
            DialogHelper.setTextColor(context, tvSubTitle, colorSubTitle);
            DialogHelper.setTextColor(context, tvNegative, colorNegative);
            DialogHelper.setTextColor(context, tvPositive, colorPositive);
            DialogHelper.setBackgroundColor(context, divider, colorDivider);
            DialogHelper.setBackgroundColor(context, dividerCenter, colorDivider);

            setNegativeClick(tvNegative);
            setPositiveClick(tvPositive);
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
     * 设置第一按钮点击事件
     */
    private void setNegativeClick(TextView view) {
        if (view != null) {
            if (onClickNegativeListener != null) {
                view.setOnClickListener(v -> onClickNegativeListener.onClick(v, dialog));
            }
        }
    }

    /**
     * 设置第二按钮点击事件
     */
    private void setPositiveClick(TextView view) {
        if (view != null) {
            if (onClickPositiveListener != null) {
                view.setOnClickListener(v -> onClickPositiveListener.onClick(v, dialog));
            }
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

    /**
     * 获取原始Dialog对象,可以对dialog执行更多原生方法操作
     *
     * @return dialog
     */
    public AlertDialog getAlertDialog() {
        return dialog;
    }

    /**
     * 设置自定义控件View的点击事件
     *
     * @param id       view的id
     * @param listener 点击监听接口
     */
    public void setChildViewClick(@IdRes int id, View.OnClickListener listener) {
        View view = getView(id);
        if (view != null) {
            if (!view.isClickable()) {
                view.setClickable(true);
            }
            view.setOnClickListener(listener);
        }
    }

    /**
     * 设置自定义控件TextView的文字
     *
     * @param id       view的id
     * @param sequence 内容字符串
     */
    public void setChildViewText(@IdRes int id, CharSequence sequence) {
        View view = getView(id);
        if (view != null) {
            if (view instanceof TextView) {
                ((TextView) view).setText(sequence);
            }
        }
    }

    /**
     * 设置自定义控件TextView的文字颜色
     *
     * @param id    view的id
     * @param color 颜色资源id
     */
    public void setChildViewColor(@IdRes int id, @ColorRes int color) {
        View view = getView(id);
        if (view != null) {
            if (view instanceof TextView) {
                ((TextView) view).setTextColor(ContextCompat.getColor(context.getApplicationContext(), color));
            }
        }
    }

    /**
     * 设置自定义控件TextView的文字大小
     *
     * @param id   view的id
     * @param size 文字大小 单位sp
     */
    public void setChildViewSize(@IdRes int id, int size) {
        View view = getView(id);
        if (view != null) {
            if (view instanceof TextView) {
                ((TextView) view).setTextSize(size);
            }
        }
    }

    public boolean isShowing() {
        return dialog != null && dialog.isShowing();
    }

    public void show() {
        if (dialog != null) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            dialog.show();
        }
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


    public interface OnDismissListener {
        void onDismiss(DialogInterface dialog);
    }

    @Nullable
    @SuppressWarnings("unchecked")
    public <T extends View> T getView(@IdRes int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            Window window = dialog.getWindow();
            if (window != null) {
                view = window.findViewById(viewId);
                views.put(viewId, view);
            }
        }
        return (T) view;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    static class DialogHelper {
        private DialogHelper() {
            throw new IllegalStateException("No instances!");
        }

        public static void setText(TextView view, CharSequence charSequence) {
            if (view != null) {
                if (charSequence != null) {
                    view.setText(charSequence);
                    view.setVisibility(View.VISIBLE);
                }
            }
        }

        public static void setTextColor(Context context, TextView view, @ColorRes int id) {
            if (view != null) {
                if (id != 0) {
                    view.setTextColor(ContextCompat.getColor(context.getApplicationContext(), id));
                }
            }
        }

        public static void setBackgroundColor(Context context, View view, @ColorRes int id) {
            if (view != null) {
                if (id != 0) {
                    view.setBackgroundColor(ContextCompat.getColor(context.getApplicationContext(), id));
                }
            }
        }

        public static void setTextSize(TextView view, int size) {
            if (view != null) {
                if (size != 0) {
                    view.setTextSize(size);
                }
            }
        }

    }

    static class ObjectHelper {
        private ObjectHelper() {
            throw new IllegalStateException("No instances!");
        }

        public static <T> T requireNonNull(T object, String message) {
            if (object == null) {
                throw new NullPointerException(message);
            }
            return object;
        }
    }

}
