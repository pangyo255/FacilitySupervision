package com.sptek.facilitysupervision.temp.listview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sptek.facilitysupervision.R;

import java.util.ArrayList;

/**
 * Created by skchoi on 2020-02-12
 */
public class MultipleListViewAdapter extends BaseAdapter {
    private static final int ITEM_VIEW_TYPE_STRS = 0 ;
    private static final int ITEM_VIEW_TYPE_IMGS = 1 ;
    private static final int ITEM_VIEW_TYPE_MAX = 2 ;

    // 아이템 데이터 리스트.
    private ArrayList<MultipleListViewItem> listViewItemList = new ArrayList<MultipleListViewItem>() ;

    public MultipleListViewAdapter() {

    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return listViewItemList.size() ;
    }

    @Override
    public int getViewTypeCount() {
        return ITEM_VIEW_TYPE_MAX ;
    }

    // position 위치의 아이템 타입 리턴.
    @Override
    public int getItemViewType(int position) {
        return listViewItemList.get(position).getType() ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();
        int viewType = getItemViewType(position) ;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

            // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
            MultipleListViewItem listViewItem = listViewItemList.get(position);

            switch (viewType) {
                case ITEM_VIEW_TYPE_STRS:
                    convertView = inflater.inflate(R.layout.listview_item01,
                            parent, false);
                    TextView titleTextView = (TextView) convertView.findViewById(R.id.title     ) ;
                    TextView descTextView = (TextView) convertView.findViewById(R.id.desc) ;

                    titleTextView.setText(listViewItem.getTitle());
                    descTextView.setText(listViewItem.getDesc());
                    break;
                case ITEM_VIEW_TYPE_IMGS:
                    convertView = inflater.inflate(R.layout.listview_item02,
                            parent, false);

                    ImageView iconImageView = (ImageView) convertView.findViewById(R.id.image) ;
                    TextView nameTextView = (TextView) convertView.findViewById(R.id.name) ;

                    iconImageView.setImageDrawable(listViewItem.getIcon());
                    nameTextView.setText(listViewItem.getName());
                    break;
            }
        }

        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position) ;
    }

    // 첫 번째 아이템 추가를 위한 함수.
    public void addItem(String title, String desc) {
        MultipleListViewItem item = new MultipleListViewItem() ;

        item.setType(ITEM_VIEW_TYPE_STRS) ;
        item.setTitle(title) ;
        item.setDesc(desc) ;

        listViewItemList.add(item) ;
    }

    // 두 번째 아이템 추가를 위한 함수.
    public void addItem(Drawable icon, String text) {
        MultipleListViewItem item = new MultipleListViewItem() ;

        item.setType(ITEM_VIEW_TYPE_IMGS) ;
        item.setIcon(icon);
        item.setName(text);

        listViewItemList.add(item);
    }
}
