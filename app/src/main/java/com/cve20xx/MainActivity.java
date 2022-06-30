package com.cve20xx;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.method.ScrollingMovementMethod;
import android.view.View;

import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;
    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mContext = this;
        txt = findViewById(R.id.textview);
        txt.setMovementMethod(new ScrollingMovementMethod());

        findViewById(R.id.button).setOnClickListener(this);
    }

    private String[] cmds = {"id", "pwd", "echo CVE20XX"};
    private int cnt = 0;
    @Override
    public void onClick(View v) {
        txt.setText(txt.getText() + "\n" + Util.runCmd(getCmd()) + "\n" + add(50, 30));
    }

    private String getCmd() {
        String s = cmds[cnt++];
        if (cnt == cmds.length) cnt = 0;
        return s;
    }

    private int add(int x , int y ) {
        return x + y;
    }
}