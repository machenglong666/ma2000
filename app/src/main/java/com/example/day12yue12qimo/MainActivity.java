package com.example.day12yue12qimo;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText name;
    private EditText password;
    private Button denglv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        name = (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.password);
        denglv = (Button) findViewById(R.id.denglv);

        denglv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.denglv:
                String name = this.name.getText().toString();
                String password = this.password.getText().toString();
                if (name.equals("H2003xs")&&password.equals("H2003")){
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(this, "用户名或者密码错误！！！", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


}
