package com.example.white.ibookclient;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import com.example.white.androidbasic.beans.IBookInfo;
import com.example.white.androidbasic.db.DBManager;
import com.example.white.androidbasic.db.MyAdapter;

import java.util.ArrayList;

/*
 * 这个类主要包括五个点击事件，分别为
 * 1，ListView的长按点击事件，用来AlertDialog来判断是否删除数据。
 * 2，ListView的点击事件，跳转到第二个界面，用来修改数据
 * 3，新建便签按钮的点击事件，跳转到第二界面，用来新建便签
 * 4，menu里的退出事件，用来退出程序
 * 5，menu里的新建事件，用来新建便签
 */
public class MainActivity extends AppCompatActivity {

    Button btn_add;
    ListView listView_txt;
    LayoutInflater inflater;
    ArrayList<IBookInfo> array;
    DBManager mgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView_txt = (ListView) findViewById(R.id.listView_txt);
        btn_add = (Button) findViewById(R.id.btn_add);
        inflater = getLayoutInflater();

        mgr = new DBManager(this);
        array = mgr.getArray();
        MyAdapter adapter = new MyAdapter(inflater, array);
        listView_txt.setAdapter(adapter);
        /*
		 * 点击listView里面的item,进入到第二个页面，用来修改日记
		 */
        listView_txt.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(getApplicationContext(), SecondAtivity.class);
                intent.putExtra("ids", array.get(position).getIds());
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
		/*
		 * 长点后来判断是否删除数据
		 */
        listView_txt.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           final int position, long id) {
                //AlertDialog,来判断是否删除日记。
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("删除")
                        .setMessage("是否删除笔记")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub

                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mgr.toDelete(array.get(position).getIds());
                                array = mgr.getArray();
                                MyAdapter adapter = new MyAdapter(inflater, array);
                                listView_txt.setAdapter(adapter);
                            }
                        })
                        .create()
                        .show();
                return true;
            }
        });
		/*
		 * 按钮点击事件，用来新建日记
		 */
        btn_add.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SecondAtivity.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Intent intent = new Intent(getApplicationContext(), SecondAtivity.class);
                startActivity(intent);
                this.finish();
                break;
            case R.id.item2:
                this.finish();
                break;
            default:
                break;
        }
        return true;
    }
}
