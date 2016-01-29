package com.example.dongja94.sampletypeface;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * Created by dongja94 on 2016-01-29.
 */
public class FontChangeActivity extends AppCompatActivity {
    AppCompatDelegate mDelegate;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        if (Build.VERSION.SDK_INT < 11) {
            getLayoutInflater().setFactory(this);
        } else {
            getLayoutInflater().setFactory2(this);
        }
        mDelegate = getDelegate();
        super.onCreate(savedInstanceState, persistentState);
    }

    int[] ids = {android.R.attr.fontFamily};

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view = super.onCreateView(name, context, attrs);
        if (view == null) {
            view = mDelegate.createView(null, name, context, attrs);
        }
        if (view == null && name.equals("TextView")) {
            view = new TextView(context, attrs);
        }
        if (view != null && view instanceof TextView) {
            TypedArray ta = context.obtainStyledAttributes(attrs, ids);
            String fontName = ta.getString(0);
            Typeface typeface = FontManager.getInstance().getTypeface(context, fontName);
            if (typeface != null) {
                ((TextView) view).setTypeface(typeface);
            }
        }
        return view;
    }

}
