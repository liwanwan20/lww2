package com.liwanwan.topnews;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by 李婉婉 on 2017/7/5.20:45
 */
public class ContentFragment extends Fragment{
    private static final String KEY="arg";

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_content,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView txt=(TextView)view.findViewById(R.id.txt_content);
        String str=(String)getArguments().get(KEY);
        txt.setText(str);
    }
    /**
     * 静态传值
     */

    public static Fragment newInstance(String str){
        ContentFragment fragment=new ContentFragment();
        Bundle bundle=new Bundle();
        bundle.putString(KEY,str);
        fragment.setArguments(bundle);
        return fragment;
    }
}
