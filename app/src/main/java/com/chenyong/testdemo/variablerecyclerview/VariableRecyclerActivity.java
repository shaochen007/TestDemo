package com.chenyong.testdemo.variablerecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.example.chenyong.testdemo.R;

import java.util.ArrayList;
import java.util.List;

public class VariableRecyclerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_variable_recycler);

        final EditText editText = findViewById(R.id.et_count);
        findViewById(R.id.btn_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = editText.getText().toString().isEmpty() ? 0 : Integer.parseInt(editText.getText().toString());
                if (count <= 0) {
                    // 显示未分组视图
                    return;
                }

                final List<ScoreBean> list = initData(count);
                RecyclerView recyclerView = findViewById(R.id.recyclerView);
                recyclerView.setAdapter(new VariableAdapter(VariableRecyclerActivity.this, list));
                GridLayoutManager layoutManager = new GridLayoutManager(VariableRecyclerActivity.this, 3);
                recyclerView.setLayoutManager(layoutManager);
                layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        if (list.size() <= 2) {
                            // 只有一个分组，“小组得分”占两个单元，分组数据占一个单元
                            if (position == 0) {
                                return 2;
                            } else {
                                return 1;
                            }
                        } else {
                            // 有多个分组：第一排各占一个单元，后面排第一个元素占两个单元，第二个元素占一个单元
                            switch (position) {
                                case 0:
                                case 1:
                                case 2:
                                    return 1;
                                default:
                                    if (position % 2 == 0) {
                                        // 奇数下标，都占一个单元
                                        return 1;
                                    } else if (position == list.size() - 1) {
                                        // 如果偶数下边为最后一个元素，则占三个单元
                                        return 3;
                                    } else {
                                        // 如果偶数下边不为最后一个元素，则占两个单元
                                        return 2;
                                    }
                            }
                        }
                    }
                });
            }
        });
    }

    private List<ScoreBean> initData(int count) {
        List<ScoreBean> list = new ArrayList<>();
        list.add(new ScoreBean(0, 0, 0));

        for (int i = 0; i < count; i++) {
            list.add(new ScoreBean(i, (int) (Math.random() * 10), (int) (Math.random() * 100)));
        }
        return list;
    }
}
