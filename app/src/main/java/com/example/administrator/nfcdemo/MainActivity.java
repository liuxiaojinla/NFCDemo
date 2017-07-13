package com.example.administrator.nfcdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static Handler dataHandler;
    public static StringBuilder sb = new StringBuilder();
    private TextView data;
    private EditText dataedit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = (TextView) findViewById(R.id.data_tv);
        dataedit = (EditText) findViewById(R.id.data_edt);
        dataedit.setText(AccountStorage.GetAccount(this));
        dataedit.addTextChangedListener(new AccountUpdater());
        dataHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    data.setText(sb.toString());
                }
                super.handleMessage(msg);
            }
        };
    }

    private class AccountUpdater implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // Not implemented.
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Not implemented.
        }

        @Override
        public void afterTextChanged(Editable s) {
            String account = s.toString();
            AccountStorage.SetAccount(MainActivity.this, account);
        }
    }
}
