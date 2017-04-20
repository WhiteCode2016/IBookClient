package com.example.white.ibookclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import com.example.white.ibookclient.beans.IBookInfo;
import com.example.white.ibookclient.db.DBManager;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
 *用来编辑日记
 *主要包括一个方法，isSave()用来保存数据;
 */
public class SecondAtivity extends AppCompatActivity {

    EditText edt_title, edt_content;
    Button btn_save;
    DBManager mgr;
    IBookInfo cun;
    int ids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        edt_title = (EditText) findViewById(R.id.edt_title);
        edt_content = (EditText) findViewById(R.id.edt_content);
        btn_save = (Button) findViewById(R.id.btn_save);
        mgr = new DBManager(this);

        Intent intent = this.getIntent();
        ids = intent.getIntExtra("ids", 0);
        //默认为0，不为0,则为修改数据时跳转过来的
        if (ids != 0) {
            cun = mgr.getTiandCon(ids);
            edt_title.setText(cun.getTitle());
            edt_content.setText(cun.getContent());
        }
        //保存按钮的点击事件，他和返回按钮是一样的功能，所以都调用isSave()方法；
        btn_save.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                isSave();
            }
        });
    }

    /*
     * 返回按钮调用的方法。
     * @see android.app.Activity#onBackPressed()
     */
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd  HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String times = formatter.format(curDate);
        String title = edt_title.getText().toString();
        String content = edt_content.getText().toString();
        //是要修改数据
        if (ids != 0) {
            cun = new IBookInfo(title, ids, content, times);
            mgr.toUpdate(cun);
            Intent intent = new Intent(SecondAtivity.this, MainActivity.class);
            startActivity(intent);
            SecondAtivity.this.finish();
        }
        //新建日记
        else {
            if (title.equals("") && content.equals("")) {
                Intent intent = new Intent(SecondAtivity.this, MainActivity.class);
                startActivity(intent);
                SecondAtivity.this.finish();
            } else {
                cun = new IBookInfo(title, content, times);
                mgr.toInsert(cun);
                Intent intent = new Intent(SecondAtivity.this, MainActivity.class);
                startActivity(intent);
                SecondAtivity.this.finish();
            }

        }
    }

    private void isSave() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd  HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String times = formatter.format(curDate);
        String title = edt_title.getText().toString();
        String content = edt_content.getText().toString();
        //是要修改数据
        if (ids != 0) {
            cun = new IBookInfo(title, ids, content, times);
            mgr.toUpdate(cun);
            Intent intent = new Intent(SecondAtivity.this, MainActivity.class);
            startActivity(intent);
            SecondAtivity.this.finish();
        }
        //新建日记
        else {
            cun = new IBookInfo(title, content, times);
            mgr.toInsert(cun);
            Intent intent = new Intent(SecondAtivity.this, MainActivity.class);
            startActivity(intent);
            SecondAtivity.this.finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.second_ativity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "标题：" + edt_title.getText().toString() + "    内容：" + edt_content.getText().toString());
                startActivity(intent);
                break;

            default:
                break;
        }
        return false;
    }
}
