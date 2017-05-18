package com.httvc.androiddemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.httvc.androiddemo.adapter.MainAdapter;
import com.httvc.androiddemo.beanjson.MainJson;
import com.httvc.androiddemo.ui.ListActivity;
import com.httvc.androiddemo.ui.NotificationActivity;
import com.httvc.androiddemo.ui.UpdateVersionActivity;
import com.httvc.androiddemo.utils.UIHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainAdapter.OnItemClickLitener {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initRecyclerView();
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        //layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutManager);
        MainAdapter adapter=new MainAdapter(this,getData());
        recyclerview.setAdapter(adapter);
        adapter.setmOnItemClickLitener(this);

    }

    private ArrayList<MainJson> getData() {
        String[] stringArray = getResources().getStringArray(R.array.array);
        ArrayList<MainJson> list = new ArrayList<>();
        for (int i = 0; i < stringArray.length; i++) {
            list.add(new MainJson(stringArray[i]));
        }
        return list;
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (position){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                UIHelper.openActivity(this, NotificationActivity.class);
                break;
            case 6:
                UIHelper.openActivity(this, UpdateVersionActivity.class);
                break;
            case 7:
                UIHelper.openActivity(this, ListActivity.class);
                break;
        }

    }

    @Override
    public void onItemLongClick(View view, int position) {

    }


}
