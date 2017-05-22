package com.httvc.androiddemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.httvc.androiddemo.R;
import com.httvc.androiddemo.adapter.CommonAdapter;
import com.httvc.androiddemo.adapter.ViewHolder;
import com.httvc.androiddemo.wigdet.ListViewUtils;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //MyListView listview = (MyListView) findViewById(R.id.listView);
        ListView listview= (ListView) findViewById(R.id.listView);
        listview.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        List<String> list = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            list.add("i = " + i);
        }

        CommonAdapter adapter=new CommonAdapter<String>(this,list,R.layout.item_main_layout){

            @Override
            public void convert(ViewHolder helper, String item) {
                helper.setText(R.id.main_tv,item.toString());
            }
        };

        listview.setAdapter(adapter);
        ListViewUtils.setListViewHeightBasedOnChildren(listview);
    }
}
