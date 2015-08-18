package table.com.timetable;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private int[] colors = new int[]{
            Color.rgb(0xee, 0xff, 0xff),
            Color.rgb(0xf0, 0x96, 0x09),
            Color.rgb(0x8c, 0xbf, 0x26),
            Color.rgb(0x00, 0xab, 0xa9),
            Color.rgb(0x99, 0x6c, 0x33),
            Color.rgb(0x3b, 0x92, 0xbc),
            Color.rgb(0xd5, 0x4d, 0x34),
            Color.rgb(0xcc, 0xcc, 0xcc),
            Color.rgb(0x66, 0x66, 0x66)
    };

    private LinearLayout llClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        llClass = (LinearLayout) findViewById(R.id.llclass);
        for (int i = 0; i < 15; i++) {
            LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(dip2px(this, 50), dip2px(this, 50));
            TextView tv = new TextView(this);
            tv.setTextColor(colors[8]);
            tv.setGravity(Gravity.CENTER);
            if (i == 0) {
                tv.setBackgroundColor(Color.TRANSPARENT);
                tv.setText("");
                tv.setTextSize(dip2px(this, 15));
            } else {
                tv.setText(String.valueOf(i));
                tv.setLayoutParams(llp);
                tv.setTextSize(dip2px(this, 12));
            }
            llClass.addView(tv);
        }

        //分别表示周一到周日
        LinearLayout ll1 = (LinearLayout)findViewById(R.id.ll1);
        LinearLayout ll2 = (LinearLayout)findViewById(R.id.ll2);
        LinearLayout ll3 = (LinearLayout)findViewById(R.id.ll3);
        LinearLayout ll4 = (LinearLayout)findViewById(R.id.ll4);
        LinearLayout ll5 = (LinearLayout)findViewById(R.id.ll5);
        LinearLayout ll6 = (LinearLayout)findViewById(R.id.ll6);
        LinearLayout ll7 = (LinearLayout)findViewById(R.id.ll7);
        //每天的课程设置
        addClass(ll1, "", "", "", "", 2, 0);
        addClass(ll1, "windows编程实践", "国软  4-503", "1-9周，每一周", "9:50-11:25", 2, 1);
        addNoClass(ll1, 3, 0);
        addClass(ll1, "概率论与数理统计", "国软  4-304", "1-15周，每一周", "14:55-17:25", 3, 2);
        addNoClass(ll1, 1, 0);
        addClass(ll1, "人文化学", "一区 3-404", "3-13周，每一周", "19:00-20:30", 2, 4);
        addNoClass(ll1, 1, 0);

        addClass(ll2, "大学英语", "国软 4-302", "1-18周，每一周", "8:00-9:35", 2, 3);
        addClass(ll2, "计算机组织体系与结构", "国软 4-204", "1-15，每一周", "9:50-12:15", 3, 5);
        addNoClass(ll2, 3, 0);
        addClass(ll2, "团队激励和沟通", "国软 4-204", "1-9周，每一周", "15:45-17:25", 2, 6);
        addNoClass(ll2, 1, 0);
        addClass(ll2, "中国近现代史纲要", "3区 1-327", "1-9周，每一周", "19:00-21:25", 3, 1);

        addNoClass(ll3, 2, 0);
        addClass(ll3, "中国近现代史纲要", "3区 1-328", "1-9周，每一周", "9:50-12:15", 3, 1);
        addNoClass(ll3, 1, 0);
        addClass(ll3, "体育（网球）", "信息学部 操场", "6-18周，每一周", "14:00-15:40", 2, 2);
        addNoClass(ll3, 3, 0);
        addClass(ll3, "当代政治与经济", "3区 1-501", "1-7周，每一周", "19:00-21:25", 3, 3);

        addClass(ll4, "计算机组织体系与结构", "国软 4-204", "1-15，每一周", "8:00-9:35", 2, 5);
        addClass(ll4, "数据结构与算法", "国软 4-304", "1-18周，每一周", "9:50-12:15", 3, 4);
        addNoClass(ll4, 1, 0);
        addClass(ll4, "面向对象程序设计（JAVA）", "国软 1-103", "1-18周，每一周", "14:00-16:30", 3, 5);
        addNoClass(ll4, 2, 0);
        addNoClass(ll4, 3, 0);

        addClass(ll5, "c#程序设计", "国软 4-102", "1-9周，每一周", "8:00-9:35", 2, 6);
        addClass(ll5, "大学英语", "国软 4-302", "1-18周，每一周", "9:50-11:25", 2, 3);
        addNoClass(ll5, 2, 0);
        addClass(ll5, "基础物理", "国软 4-304", "1-18周，每一周", "14:00-16:30", 3, 1);
        addNoClass(ll5, 2, 0);
        addClass(ll5, "手机应用分析与创意", "1区 5-103", "1-7周，每一周", "19:00-21:2", 3, 2);

        addNoClass(ll6, 14, 0);

        addNoClass(ll7, 14, 0);
    }

    /**
     * 添加课程
     * @param ll
     * @param title
     * @param place
     * @param last
     * @param time
     * @param classes
     * @param color
     */
    private void addClass(LinearLayout ll, String title, String place, String last, String time, int classes, int color) {
        View view = LayoutInflater.from(this).inflate(R.layout.item, null);
        view.setMinimumHeight(classes*dip2px(this, 48));
        view.setBackgroundColor(colors[color]);
        ((TextView) view.findViewById(R.id.title)).setText(title);
        ((TextView) view.findViewById(R.id.place)).setText(place);
        ((TextView) view.findViewById(R.id.last)).setText(last);
        ((TextView) view.findViewById(R.id.time)).setText(time);
        view.setOnClickListener(this);
        //中间间隔
        TextView blank1 = new TextView(this);
        TextView blank2 = new TextView(this);
        blank1.setHeight(dip2px(this, classes));
        blank2.setHeight(dip2px(this, classes));
        ll.addView(blank1);
        ll.addView(view);
        ll.addView(blank2);
    }

    public void addNoClass(LinearLayout ll, int classes, int color){
        TextView blank = new TextView(this);
        if (color == 0){
            blank.setMinimumHeight(dip2px(this, classes*50));
        }
        blank.setBackgroundColor(colors[color]);
        ll.addView(blank);
//        ll.addView();
    }
    /**
     * dip转成px
     *
     * @param context
     * @param dip
     * @return
     */
    public int dip2px(Context context, int dip) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    @Override
    public void onClick(View v) {

    }
}
