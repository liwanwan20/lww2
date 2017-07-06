package com.liwanwan.topnews;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    /**
     *横向滑动栏
     */
    @ViewInject(R.id.hsv_title)
    private HorizontalScrollView hsvTitle;

    /**
     * 横向标题布局。盛放一个个的textview
     */
    @ViewInject(R.id.ll_title)
    private LinearLayout llTitle;

    /**
     * 内容显示的控件
     */
    @ViewInject(R.id.vp_content)
    private ViewPager vpContent;

    /**
     * 放所有textview的集合
     */
    private List<String> titles;

//    private List<Fragment> fragments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        x.view().inject(this);


        //初始化数据
        initData();

        // 1. 向LinearLayout里面添加数据
        // 2. 向ViewPager里面添加Fragment
         addTitleAndFragment();


        //给viewPager里面设置适配器
        vpContent.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return ContentFragment.newInstance(titles.get(position));
            }

            @Override
            public int getCount() {
                return titles.size();
            }
        });

        // 给viewpager设置切换事件监听
        vpContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                // 选中切换变色
                changeTextColor(position);
                // 计算控件要滑动的距离
                // 共有多少margin
                int margins = 20 * position;
                // 定义一个textview宽度的综合
                int widthTotal = 0;
                // 遍历textview的宽度
                for (int j = 0; j < position; j++) {
                    TextView txtV = (TextView) llTitle.getChildAt(j);
                    widthTotal += txtV.getWidth();
                }
                // hsv计算滑动的位置
                hsvTitle.scrollTo((margins + widthTotal), 0);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
    /**
     * 切换或者点击的时候改变文字颜色
     * @param position
     */
    private void changeTextColor(int position) {
        for (int i = 0; i < titles.size(); i++) {
            // 拿到textview
            TextView txtview = (TextView) llTitle.getChildAt(i);
            // 如果被选中，就把文字变成红色
            if (position == i) {
                txtview.setTextColor(Color.RED);
            } else {
                txtview.setTextColor(Color.BLACK);
            }
        }
    }
    /**
     * 添加Textview和Fragment
     */
    private void addTitleAndFragment() {
        for (int i = 0; i < titles.size(); i++) {
            // 添加textview
            TextView txt = new TextView(this);
            txt.setText(titles.get(i));
//            txt.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            txt.setTextSize(14);
            // 设置默认颜色，第一个是默认选中，红色，其他的是黑色，不选中
            if (i == 0) {
                txt.setTextColor(Color.RED);
            } else {
                txt.setTextColor(Color.BLACK);
            }
            // 给textview设置一个id
            txt.setId(i + 1000);
            // 给textview设置一个点击事件
            txt.setOnClickListener(MainActivity.this);
            // 设置textview要添加进的ViewGroup的宽高
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);

            params.setMargins(10, 5, 10, 5);
            // 给textview设置一个相对父控件的参数
            txt.setLayoutParams(params);
            llTitle.addView(txt);
            // 添加fragment
//            Fragment fragment = ContentFragment.newInstance(titles.get(i));
//            fragments.add(fragment);
        }
    }

    /**
     * 初始化列表数据
     */
    private void initData() {
        titles = new ArrayList<>();
//        fragments = new ArrayList<>();
        titles.add("话题热门阿萨德");
        titles.add("推荐");
        titles.add("热点");
        titles.add("科技");
        titles.add("视频");
        titles.add("微视频");
        titles.add("新闻头题");
        titles.add("数码");
        titles.add("社会");
        titles.add("汽车");
        titles.add("娱乐");
        titles.add("电影");
        titles.add("问答");
        titles.add("话题热门");
        titles.add("北京");
        titles.add("图片");
        titles.add("体育");
        titles.add("财经");
        titles.add("军事");
        titles.add("国际");
    }

    //textview的监听事件
    @Override
    public void onClick(View v) {
        int id = v.getId();
        // 拿到textview的position
        int position = id - 1000;
        vpContent.setCurrentItem(position);

        // 点击变色
        changeTextColor(position);
    }
}
