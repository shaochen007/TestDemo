package com.chenyong.testdemo;

import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;

import com.chenyong.testdemo.variablerecyclerview.VariableRecyclerActivity;
import com.cy.customwidget.NumPercent;
import com.example.chenyong.testdemo.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int mProgressValue = 1;
    private int mTotalValue = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final NumPercent numPercent = findViewById(R.id.num_percent);
        numPercent.setTotalValue(mTotalValue);
        numPercent.setProgressValue(mProgressValue);

        findViewById(R.id.btn_progress).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numPercent.setProgressValue(++mProgressValue);
            }
        });

        findViewById(R.id.btn_total).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numPercent.setTotalValue(++mTotalValue);
            }
        });

        String json3 = "{'page':1,'size':10,'total':2,'data':[{'id':1,'name':'eric'},{'id':2,'name':'john'}]}";

        Page<User> page = fromJson(json3, new TypeToken<Page<User>>(){}.getType());
        Log.d("", "onCreate: " + page);

        final SeekBar seekBar = findViewById(R.id.seekBar);
        setAttrValue(seekBar, "mMaxHeight", 4);
        setAttrValue(seekBar, "mMinHeight", 4);

        final AppCompatSeekBar seekBar2 = findViewById(R.id.progress2);
        final CheckBox checkBox2 = findViewById(R.id.checkBox2);
        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // 动态设置SeekBar背景时，maxHeight等属性会失效，通过保存、重设的方式保持原属性
                Rect rounds = seekBar2.getProgressDrawable().getBounds();
                if (isChecked) {
                    seekBar2.setThumb(getResources().getDrawable(R.mipmap.btn_light_c));
                    seekBar2.setProgressDrawable(getResources().getDrawable(R.drawable.seekbar_progress_h));
                } else {
                    seekBar2.setThumb(getResources().getDrawable(R.mipmap.btn_light_d));
                    seekBar2.setProgressDrawable(getResources().getDrawable(R.drawable.seekbar_progress_normal));
                }

                seekBar2.getProgressDrawable().setBounds(rounds);
            }
        });

        findViewById(R.id.btn_variable).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, VariableRecyclerActivity.class));
            }
        });
    }

    /**
     * 反射设置控件属性值
     *
     * @param view  控件
     * @param attrName  属性
     * @param value  值
     */
    private void setAttrValue(View view, String attrName, Object value) {
        Class<?> superclass = view.getClass();
        while (superclass != null) {
            try {
                Field mMaxHeight = superclass.getDeclaredField(attrName);
                mMaxHeight.setAccessible(true);
                mMaxHeight.set(view, value);

                break;
            } catch (NoSuchFieldException e) {
                // 还未获取到该属性，再从父类找
                superclass = superclass.getSuperclass();
            } catch (IllegalAccessException e) {
                // 设置属性异常
                e.printStackTrace();
            }
        }
    }

    public static <T>T fromJson(String json,Class<T> type){
        Gson gson = new Gson();
        return gson.fromJson(json,type);
    }

    public static <T>T fromJson(String json,Type type){
        Gson gson = new Gson();
        return gson.fromJson(json, type);
    }

    public class Page<T> {

        private int page;

        private int size;

        private int total;

        private List<T> data;

        @Override
        public String toString() {
            return "User [page=" + page + ", size=" + size + ", total=" + total + ", data=" + data + "]";
        }

    }

    public class User {

        private int id;

        private String name;

        @Override
        public String toString() {
            return "User [id=" + id + ", name=" + name + "]";
        }

    }
}
