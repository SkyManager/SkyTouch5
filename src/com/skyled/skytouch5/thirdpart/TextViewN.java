package com.skyled.skytouch5.thirdpart;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class TextViewN extends TextView{
	
	
	private Context c;
    public TextViewN(Context c) {
        super(c);
        this.c = c;
        Typeface tfs = Typeface.createFromAsset(c.getAssets(),
                "font/font.ttf");
        setTypeface(tfs);
        isInEditMode();
    }
    public TextViewN(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.c = context;
        Typeface tfs = Typeface.createFromAsset(c.getAssets(),
                "fonts/font.ttf");
        setTypeface(tfs);
        isInEditMode();
        // TODO Auto-generated constructor stub
    }

    public TextViewN(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.c = context;
        Typeface tfs = Typeface.createFromAsset(c.getAssets(),
                "fonts/font.ttf");
        setTypeface(tfs);

    }


}