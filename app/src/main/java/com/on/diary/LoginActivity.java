package com.on.diary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Administrator on 2018/3/9.
 */

public class LoginActivity extends Activity implements View.OnClickListener {
    public static String TAG = "LoginActivity";
    Context mContext;
    EditText username, password;
    Button login, forgive_pwd, register, bt_username_clear, bt_pwd_eye, bt_pwd_clear;
    public Boolean isOpen = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        mContext = LoginActivity.this;
        initView();
    }

    public void initView() {
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);
        forgive_pwd = (Button) findViewById(R.id.forgive_pwd);
        bt_username_clear = (Button) findViewById(R.id.bt_username_clear);
        bt_pwd_clear = (Button) findViewById(R.id.bt_pwd_clear);
        bt_pwd_eye = (Button) findViewById(R.id.bt_pwd_eye);
        bt_pwd_eye.setOnClickListener(this);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        forgive_pwd.setOnClickListener(this);
        bt_pwd_clear.setOnClickListener(this);
        bt_username_clear.setOnClickListener(this);
        //监听文本内容变化
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String user = username.getText().toString().trim();
                if (" ".equals(user)) {
                    bt_username_clear.setVisibility(View.INVISIBLE);
                } else {
                    bt_username_clear.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String pd = password.getText().toString().trim();
                if (" ".equals(pd)) {
                    bt_pwd_clear.setVisibility(View.INVISIBLE);
                } else {
                    bt_pwd_clear.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
              startActivity(new Intent(mContext,MainActivity.class));
                break;
            case R.id.register:
                break;
            case R.id.forgive_pwd:
                break;
            case R.id.bt_pwd_clear:
                password.setText(" ");
                break;
            case R.id.bt_username_clear:
                username.setText(" ");
                break;
            case R.id.bt_pwd_eye:
                if (isOpen) {
                    isOpen = false;
                } else {
                    isOpen = true;
                }
                // 默认isOpen是false,密码不可见
                changePwdOpenOrClose(isOpen);

                break;
            default:
                break;
        }
    }

    /**
     * 密码可见与不可见的切换
     *
     * @param flag
     */
    private void changePwdOpenOrClose(boolean flag) {
// 第一次过来是false，密码不可见
        if (flag) {
// 密码可见
            bt_pwd_eye.setBackgroundResource(R.mipmap.ic_visibility_black);
// 设置EditText的密码可见
            password.setTransformationMethod(HideReturnsTransformationMethod
                    .getInstance());
        } else {
// 密码不接见
            bt_pwd_eye.setBackgroundResource(R.mipmap.ic_visibility_off_black);
// 设置EditText的密码隐藏
            password.setTransformationMethod(PasswordTransformationMethod
                    .getInstance());
        }
    }


}
