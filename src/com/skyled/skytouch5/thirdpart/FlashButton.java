package com.skyled.skytouch5.thirdpart;


import com.skyled.skytouch5.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;

public class FlashButton extends ImageButton{

    public enum FlashEnum {
        MONO, OFF, RGB
    }

    public interface FlashListener {
        void onOff();
		void onMono();
		void onRgb();
    }

    private FlashEnum mState;
    private FlashListener mFlashListener;

    public FlashButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                int next = ((mState.ordinal() + 1) % FlashEnum.values().length);
                setState(FlashEnum.values()[next]);
                performFlashClick();
            }
        });
        //Sets initial state
        setState(FlashEnum.OFF);
    }

    private void performFlashClick() {
        if(mFlashListener == null)return;
        switch (mState) {
            case MONO:
                mFlashListener.onMono();
                break;
            case OFF:
                mFlashListener.onOff();
                break;
            case RGB:
                mFlashListener.onRgb();
                break;
        }
    }

    private void createDrawableState() {
        switch (mState) {
            case MONO:
                setImageResource(R.drawable.ic_flash_mono);
                break;
            case OFF:
                setImageResource(R.drawable.ic_flash_off);
                break;
            case RGB:
                setImageResource(R.drawable.ic_flash_rgb);
                break;
        }
    }


    public FlashEnum getState() {
        return mState;
    }

    public void setState(FlashEnum state) {
        if(state == null)return;
        this.mState = state;
        createDrawableState();

    }

    public FlashListener getFlashListener() {
        return mFlashListener;
    }

    public void setFlashListener(FlashListener flashListener) {
        this.mFlashListener = flashListener;
    }

}
