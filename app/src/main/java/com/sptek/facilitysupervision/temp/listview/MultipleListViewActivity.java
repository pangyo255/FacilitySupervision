package com.sptek.facilitysupervision.temp.listview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.ListView;

import com.sptek.facilitysupervision.R;

public class MultipleListViewActivity extends AppCompatActivity {
    ListView listView;
    MultipleListViewAdapter listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_list_view);

        listView = findViewById(R.id.list_multiple);
        listViewAdapter = new MultipleListViewAdapter();
        listView.setAdapter(listViewAdapter);

        // 첫 번째 아이템 추가.
        listViewAdapter.addItem("The First Item", "this is the first item. Two TextView are used for title and desc.") ;
        // 두 번째 아이템 추가.
        listViewAdapter.addItem(ContextCompat.getDrawable(this, R.mipmap.ic_launcher),
                "2nd : Account Box Black 36dp") ;
        // 세 번째 아이템 추가.
        listViewAdapter.addItem("The Third Item", "this is the third item. Two TextView are used for title and desc.") ;
        // 네 번째 아이템 추가
        listViewAdapter.addItem(ContextCompat.getDrawable(this, R.mipmap.ic_launcher),
                "4th : Account Circle Black 36dp") ;
        // 다섯 번째 아이템 추가.
        listViewAdapter.addItem(ContextCompat.getDrawable(this, R.mipmap.ic_launcher),
                "5th : Assignment Ind Black 36dp") ;


    }
}
