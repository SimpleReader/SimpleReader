package com.umeng.soexample.ui.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.umeng.soexample.R;

/**
 * Description:自定义输入框
 * Created by chenggong on 2017/5/5.
 */

public class ClearEditText extends AppCompatEditText implements View.OnTouchListener,View.OnFocusChangeListener,TextWatcher {
    private Drawable mClearTextIcon;//图标
    private OnFocusChangeListener mOnFocusChangeListener;//焦点改变监听
    private OnTouchListener mOnTouchListener;//触摸监听


    public ClearEditText(final Context context) {
        super(context);
        init(context);
    }

    public ClearEditText(final Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ClearEditText(final Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(final Context context){
        final Drawable drawable= ContextCompat.getDrawable(context, R.drawable.icon_delete_32);//得到删除按钮的图片
        final Drawable wrappedDrawable= DrawableCompat.wrap(drawable);//自适应
        DrawableCompat.setTint(wrappedDrawable,getCurrentHintTextColor());
        mClearTextIcon=wrappedDrawable;
        mClearTextIcon.setBounds(0,0,mClearTextIcon.getIntrinsicHeight(),mClearTextIcon.getIntrinsicHeight());
        setClearIconVisible(false);
        super.setOnTouchListener(this);
        super.setOnFocusChangeListener(this);
        addTextChangedListener(this);
    }

    /**
     * 焦点改变
     * @param view
     * @param b
     */
    @Override
    public void onFocusChange(View view, boolean b) {
        if(hasFocus()){//如果还有焦点
            setClearIconVisible(getText().length()>0);
        }else{
            setClearIconVisible(false);
        }
        if(mOnFocusChangeListener !=null){
            mOnFocusChangeListener.onFocusChange(view,hasFocus());
        }
    }

    /**
     * 触摸事件
     * @param view
     * @param motionEvent
     * @return
     */
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        final int x= (int) motionEvent.getX();//触摸点相对于按钮的坐标
        //getIntrinsicWidth()---getIntrinsicHeight()取得Drawable的固有的宽度和高度
        /**
         * 实际的绘图区域在距离他右边 paddingRight 远的位置开始算
         * 比如一个view 他的左上坐标是0，0，正常你放个viewchild上去，如果不指定坐标，就是放在了0，0点，
         * 如果设置了paddingleft是20，那么viewchild就会放在20,0的位置，左边就会出现20像素单位的空区域
         */
        if(/*mOnTouchListener!=null && */x>getWidth() - getPaddingRight() - mClearTextIcon.getIntrinsicWidth()){
            if(motionEvent.getAction() == MotionEvent.ACTION_UP){//用户抬起了手指
                //setError("null");
                setText("");
            }
            return true;
        }
        return mOnTouchListener !=null && mOnTouchListener.onTouch(view,motionEvent);
    }

    /**
     * 清除图标是否可见
     */
    private void setClearIconVisible(final boolean visible){
        mClearTextIcon.setVisible(visible,false);
        final Drawable[] compoundDrawables=getCompoundDrawables();//该方法返回包含控件左,上,右,下四个位置的Drawable的数组
        setCompoundDrawables(
                compoundDrawables[0],
                compoundDrawables[1],
                visible?mClearTextIcon:null,
                compoundDrawables[3]
        );
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    /**
     * 输入文字的时候
     * @param text
     * @param start
     * @param lengthBefore
     * @param lengthAfter
     */
    @Override
    public final void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        if(isFocused()){//焦点
            setClearIconVisible(text.length()>0);//取决于输入框文字的长度
        }
    }

    @Override
    public void setOnTouchListener(OnTouchListener l) {
        this.mOnTouchListener=l;
    }

    @Override
    public void setOnFocusChangeListener(OnFocusChangeListener l) {
        this.mOnFocusChangeListener=l;
    }

}