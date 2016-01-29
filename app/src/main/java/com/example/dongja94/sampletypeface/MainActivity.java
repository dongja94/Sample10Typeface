package com.example.dongja94.sampletypeface;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    AppCompatDelegate mDelegate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT < 11) {
            getLayoutInflater().setFactory(this);
        } else {
            getLayoutInflater().setFactory2(this);
        }
        mDelegate = getDelegate();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

//        TextView textView = (TextView)findViewById(R.id.text_namun);
//        textView.setTypeface(FontManager.getInstance().getTypeface(this, FontManager.FONT_NAME_NANUM));
//
//        textView = (TextView)findViewById(R.id.text_noto);
//        textView.setTypeface(FontManager.getInstance().getTypeface(this, FontManager.FONT_NAME_NOTO));
//
//        textView = (TextView)findViewById(R.id.text_roboto);
//        textView.setTypeface(FontManager.getInstance().getTypeface(this, FontManager.FONT_NAME_ROBOTO));
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
