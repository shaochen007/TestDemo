package com.cy.customwidget.multilevel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.cy.customwidget.R;
import com.google.gson.Gson;

import java.util.List;

public class SampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        OptionBean multiLevelSelectData = new Gson().fromJson("{\n" +
                "    \"list\": [\n" +
                "        {\n" +
                "            \"id\": \"402884ef6c656508016c69fd075c0065\",\n" +
                "            \"name\": \"科汇金谷一区\",\n" +
                "            \"remark\": \"教学楼\",\n" +
                "            \"status\": 1,\n" +
                "            \"list\": [\n" +
                "                {\n" +
                "                    \"list\": [\n" +
                "                        {\n" +
                "                            \"list\": [\n" +
                "                                {\n" +
                "                                    \"list\": [\n" +
                "                                        {\n" +
                "                                            \"id\": \"402884ef6c656508016c89b3be920096\",\n" +
                "                                            \"name\": \"102\",\n" +
                "                                            \"status\": 1\n" +
                "                                        },\n" +
                "                                        {\n" +
                "                                            \"id\": \"402884ef6c656508016c6a0cb29d006f\",\n" +
                "                                            \"name\": \"一区2-101\",\n" +
                "                                            \"status\": 1\n" +
                "                                        },\n" +
                "                                        {\n" +
                "                                            \"id\": \"402884ef6c656508016c6a0d3efa0070\",\n" +
                "                                            \"name\": \"一区2-201\",\n" +
                "                                            \"status\": 1\n" +
                "                                        }\n" +
                "                                    ]\n" +
                "                                }\n" +
                "                            ],\n" +
                "                            \"name\": \"1F\",\n" +
                "                            \"remark\": \"教室\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"list\": [\n" +
                "                                {\n" +
                "                                    \"list\": [\n" +
                "                                        {\n" +
                "                                            \"id\": \"402884ef6c656508016c89b583420097\",\n" +
                "                                            \"name\": \"203\",\n" +
                "                                            \"status\": 1\n" +
                "                                        },\n" +
                "                                        {\n" +
                "                                            \"id\": \"402884ef6c656508016c6a0d99af0071\",\n" +
                "                                            \"name\": \"一区2-201\",\n" +
                "                                            \"status\": 1\n" +
                "                                        }\n" +
                "                                    ]\n" +
                "                                }\n" +
                "                            ],\n" +
                "                            \"name\": \"2F\",\n" +
                "                            \"remark\": \"教室\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"list\": [\n" +
                "                                {\n" +
                "                                    \"list\": [\n" +
                "                                        {\n" +
                "                                            \"id\": \"402884ef6c656508016c6a11198b0072\",\n" +
                "                                            \"name\": \"一区2-301\",\n" +
                "                                            \"status\": 1\n" +
                "                                        }\n" +
                "                                    ]\n" +
                "                                }\n" +
                "                            ],\n" +
                "                            \"name\": \"3F\",\n" +
                "                            \"remark\": \"教室\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"list\": [\n" +
                "                                {\n" +
                "                                    \"list\": [\n" +
                "                                        {\n" +
                "                                            \"id\": \"402884ef6c656508016c6a11c73f0073\",\n" +
                "                                            \"name\": \"一区2-401\",\n" +
                "                                            \"status\": 1\n" +
                "                                        }\n" +
                "                                    ]\n" +
                "                                }\n" +
                "                            ],\n" +
                "                            \"name\": \"4F\",\n" +
                "                            \"remark\": \"教室\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"list\": [],\n" +
                "                            \"name\": \"5F\",\n" +
                "                            \"remark\": \"教室\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"list\": [],\n" +
                "                            \"name\": \"6F\",\n" +
                "                            \"remark\": \"教室\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"list\": [],\n" +
                "                            \"name\": \"7F\",\n" +
                "                            \"remark\": \"教室\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"id\": \"402884ef6c656508016c69feb0f90067\",\n" +
                "                    \"name\": \"科汇金谷一区2栋\",\n" +
                "                    \"remark\": \"楼层\",\n" +
                "                    \"status\": 1\n" +
                "                },\n" +
                "                {\n" +
                "                    \"list\": [\n" +
                "                        {\n" +
                "                            \"list\": [\n" +
                "                                {\n" +
                "                                    \"list\": [\n" +
                "                                        {\n" +
                "                                            \"id\": \"402884ef6c656508016c6a0b03e6006c\",\n" +
                "                                            \"name\": \"一区1-101\",\n" +
                "                                            \"status\": 1\n" +
                "                                        },\n" +
                "                                        {\n" +
                "                                            \"id\": \"402884ef6c656508016c6a0bf631006d\",\n" +
                "                                            \"name\": \"一区1-102\",\n" +
                "                                            \"status\": 1\n" +
                "                                        },\n" +
                "                                        {\n" +
                "                                            \"id\": \"402884ef6c656508016c6a0c61af006e\",\n" +
                "                                            \"name\": \"一区1-103\",\n" +
                "                                            \"status\": 1\n" +
                "                                        }\n" +
                "                                    ]\n" +
                "                                }\n" +
                "                            ],\n" +
                "                            \"name\": \"1F\",\n" +
                "                            \"remark\": \"教室\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"list\": [],\n" +
                "                            \"name\": \"2F\",\n" +
                "                            \"remark\": \"教室\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"list\": [],\n" +
                "                            \"name\": \"3F\",\n" +
                "                            \"remark\": \"教室\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"list\": [],\n" +
                "                            \"name\": \"4F\",\n" +
                "                            \"remark\": \"教室\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"list\": [],\n" +
                "                            \"name\": \"5F\",\n" +
                "                            \"remark\": \"教室\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"list\": [],\n" +
                "                            \"name\": \"6F\",\n" +
                "                            \"remark\": \"教室\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"list\": [],\n" +
                "                            \"name\": \"7F\",\n" +
                "                            \"remark\": \"教室\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"id\": \"402884ef6c656508016c69ff17100068\",\n" +
                "                    \"name\": \"科汇金谷一区1栋\",\n" +
                "                    \"remark\": \"楼层\",\n" +
                "                    \"status\": 1\n" +
                "                },\n" +
                "                {\n" +
                "                    \"list\": [\n" +
                "                        {\n" +
                "                            \"list\": [],\n" +
                "                            \"name\": \"1F\",\n" +
                "                            \"remark\": \"教室\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"list\": [],\n" +
                "                            \"name\": \"2F\",\n" +
                "                            \"remark\": \"教室\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"list\": [],\n" +
                "                            \"name\": \"3F\",\n" +
                "                            \"remark\": \"教室\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"id\": \"402884ef6c656508016c6a07cb660069\",\n" +
                "                    \"name\": \"科汇金谷一区3栋\",\n" +
                "                    \"remark\": \"楼层\",\n" +
                "                    \"status\": 1\n" +
                "                },\n" +
                "                {\n" +
                "                    \"list\": [\n" +
                "                        {\n" +
                "                            \"list\": [],\n" +
                "                            \"name\": \"1F\",\n" +
                "                            \"remark\": \"教室\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"list\": [],\n" +
                "                            \"name\": \"2F\",\n" +
                "                            \"remark\": \"教室\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"list\": [],\n" +
                "                            \"name\": \"3F\",\n" +
                "                            \"remark\": \"教室\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"id\": \"402884ef6c656508016c89b8a4190099\",\n" +
                "                    \"name\": \"科汇金谷一区1栋\",\n" +
                "                    \"remark\": \"楼层\",\n" +
                "                    \"status\": 1\n" +
                "                },\n" +
                "                {\n" +
                "                    \"list\": [\n" +
                "                        {\n" +
                "                            \"list\": [],\n" +
                "                            \"name\": \"1F\",\n" +
                "                            \"remark\": \"教室\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"list\": [],\n" +
                "                            \"name\": \"2F\",\n" +
                "                            \"remark\": \"教室\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"list\": [],\n" +
                "                            \"name\": \"3F\",\n" +
                "                            \"remark\": \"教室\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"id\": \"402884ef6c656508016c89b8075c0098\",\n" +
                "                    \"name\": \"科汇金谷一区1栋\",\n" +
                "                    \"remark\": \"楼层\",\n" +
                "                    \"status\": 1\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"402884ef6c656508016c6570940a0000\",\n" +
                "            \"name\": \"绿地中央\",\n" +
                "            \"remark\": \"教学楼\",\n" +
                "            \"status\": 1,\n" +
                "            \"tlist\": []\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"402884ef6c618ebd016c650dc0d30000\",\n" +
                "            \"name\": \"科汇金谷\",\n" +
                "            \"remark\": \"教学楼\",\n" +
                "            \"status\": 1,\n" +
                "            \"tlist\": [\n" +
                "                {\n" +
                "                    \"list\": [\n" +
                "                        {\n" +
                "                            \"list\": [],\n" +
                "                            \"name\": \"1F\",\n" +
                "                            \"remark\": \"教室\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"list\": [],\n" +
                "                            \"name\": \"2F\",\n" +
                "                            \"remark\": \"教室\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"list\": [],\n" +
                "                            \"name\": \"3F\",\n" +
                "                            \"remark\": \"教室\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"list\": [],\n" +
                "                            \"name\": \"4F\",\n" +
                "                            \"remark\": \"教室\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"id\": \"402884ef6c656508016c89afbd750094\",\n" +
                "                    \"name\": \"二街9号\",\n" +
                "                    \"remark\": \"楼层\",\n" +
                "                    \"status\": 1\n" +
                "                },\n" +
                "                {\n" +
                "                    \"list\": [\n" +
                "                        {\n" +
                "                            \"list\": [],\n" +
                "                            \"name\": \"1F\",\n" +
                "                            \"remark\": \"教室\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"list\": [],\n" +
                "                            \"name\": \"2F\",\n" +
                "                            \"remark\": \"教室\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"list\": [\n" +
                "                                {\n" +
                "                                    \"list\": [\n" +
                "                                        {\n" +
                "                                            \"id\": \"402884ef6c656508016c89b074090095\",\n" +
                "                                            \"name\": \"300\",\n" +
                "                                            \"status\": 1\n" +
                "                                        },\n" +
                "                                        {\n" +
                "                                            \"id\": \"402884ef6c618ebd016c65136f010005\",\n" +
                "                                            \"name\": \"301\",\n" +
                "                                            \"status\": 1\n" +
                "                                        },\n" +
                "                                        {\n" +
                "                                            \"id\": \"402884ef6c656508016c657172820002\",\n" +
                "                                            \"name\": \"302\",\n" +
                "                                            \"status\": 1\n" +
                "                                        }\n" +
                "                                    ]\n" +
                "                                }\n" +
                "                            ],\n" +
                "                            \"name\": \"3F\",\n" +
                "                            \"remark\": \"教室\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"id\": \"402884ef6c618ebd016c651187af0001\",\n" +
                "                    \"name\": \"二街11号\",\n" +
                "                    \"remark\": \"楼层\",\n" +
                "                    \"status\": 1\n" +
                "                },\n" +
                "                {\n" +
                "                    \"list\": [\n" +
                "                        {\n" +
                "                            \"list\": [],\n" +
                "                            \"name\": \"1F\",\n" +
                "                            \"remark\": \"教室\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"list\": [],\n" +
                "                            \"name\": \"2F\",\n" +
                "                            \"remark\": \"教室\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"list\": [],\n" +
                "                            \"name\": \"3F\",\n" +
                "                            \"remark\": \"教室\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"list\": [],\n" +
                "                            \"name\": \"4F\",\n" +
                "                            \"remark\": \"教室\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"list\": [],\n" +
                "                            \"name\": \"5F\",\n" +
                "                            \"remark\": \"教室\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"id\": \"402884ef6c656508016c6570fdd00001\",\n" +
                "                    \"name\": \"二街10号\",\n" +
                "                    \"remark\": \"楼层\",\n" +
                "                    \"status\": 1\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"402884ef6c656508016c69fd85960066\",\n" +
                "            \"name\": \"科汇金谷二区\",\n" +
                "            \"remark\": \"教学楼\",\n" +
                "            \"status\": 1,\n" +
                "            \"tlist\": [\n" +
                "                {\n" +
                "                    \"list\": [\n" +
                "                        {\n" +
                "                            \"list\": [],\n" +
                "                            \"name\": \"1F\",\n" +
                "                            \"remark\": \"教室\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"list\": [],\n" +
                "                            \"name\": \"2F\",\n" +
                "                            \"remark\": \"教室\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"list\": [],\n" +
                "                            \"name\": \"3F\",\n" +
                "                            \"remark\": \"教室\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"list\": [],\n" +
                "                            \"name\": \"4F\",\n" +
                "                            \"remark\": \"教室\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"list\": [],\n" +
                "                            \"name\": \"5F\",\n" +
                "                            \"remark\": \"教室\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"id\": \"402884ef6c656508016c6a085860006a\",\n" +
                "                    \"name\": \"科汇金谷二区1栋\",\n" +
                "                    \"remark\": \"楼层\",\n" +
                "                    \"status\": 1\n" +
                "                },\n" +
                "                {\n" +
                "                    \"list\": [\n" +
                "                        {\n" +
                "                            \"list\": [],\n" +
                "                            \"name\": \"1F\",\n" +
                "                            \"remark\": \"教室\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"list\": [],\n" +
                "                            \"name\": \"2F\",\n" +
                "                            \"remark\": \"教室\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"list\": [],\n" +
                "                            \"name\": \"3F\",\n" +
                "                            \"remark\": \"教室\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"id\": \"402884ef6c656508016c6a0884d2006b\",\n" +
                "                    \"name\": \"科汇金谷二区2栋\",\n" +
                "                    \"remark\": \"楼层\",\n" +
                "                    \"status\": 1\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"402884ef6c656508016c757d7525008c\",\n" +
                "            \"name\": \"校区test\",\n" +
                "            \"remark\": \"教学楼\",\n" +
                "            \"status\": 1,\n" +
                "            \"tlist\": []\n" +
                "        }\n" +
                "    ],\n" +
                "    \"remark\": \"校区\"\n" +
                "}", OptionBean.class);

        ((MultiLevelSelect) findViewById(R.id.multiLeverSelect)).setData(multiLevelSelectData);
        ((MultiLevelSelect) findViewById(R.id.multiLeverSelect)).setOnCompleteListener(new MultiLevelSelect.OnCompleteListener() {
            @Override
            public void complete(final List<OptionBean> list) {
                Toast.makeText(SampleActivity.this, list.toString(), Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });
    }
}
