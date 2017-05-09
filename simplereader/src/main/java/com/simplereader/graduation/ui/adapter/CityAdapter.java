package com.simplereader.graduation.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simplereader.simplereader.R;

import java.util.List;

/**
 * Description:省份Adapter
 * Created by chenggong on 2017/5/6.
 */

public class CityAdapter extends BaseQuickAdapter<String> {

    public CityAdapter(List<String> data) {
        super(R.layout.item_city, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String proName) {
        baseViewHolder.setText(R.id.item_city,proName);
    }


}
