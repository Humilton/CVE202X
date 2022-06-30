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

    private String[] cmds = {"id", "pwd", "whoami"};
    private int cnt=0;
    @Override
    public void onClick(View v) {
        String s = Util.runCmd(cmds[cnt++]);
        txt.setText(txt.getText() + "\n" + s);
        if (cnt == cmds.length) cnt = 0;
        Toast.makeText(mContext, "Clicked!", Toast.LENGTH_SHORT).show();
    }
}