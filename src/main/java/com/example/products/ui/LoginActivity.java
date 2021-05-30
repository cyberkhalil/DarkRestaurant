package com.example.products.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.products.R;

public class LoginActivity extends AppCompatActivity {

    EditText et_username, et_pass;
    Button btn_register, btn_login;
    CheckBox cb_remember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // get needed views by id
        et_username = findViewById(R.id.login_et_username);
        et_pass = findViewById(R.id.login_et_pass);

        btn_register = findViewById(R.id.login_btn_register);
        btn_login = findViewById(R.id.login_btn_login);

        cb_remember = findViewById(R.id.login_cb_remember);

        // fill edit texts from default shared preferences
        SharedPreferences dsp = PreferenceManager.getDefaultSharedPreferences(this);
        et_username.setText(dsp.getString("username", ""));
        et_pass.setText(dsp.getString("pass", ""));

        // set listeners actions
        btn_register.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });

        btn_login.setOnClickListener(v -> {
            if (login(et_username.getText().toString(), et_pass.getText().toString())) {
                SharedPreferences.Editor edit = dsp.edit();
                edit.putBoolean("logged", cb_remember.isChecked());
                edit.apply();

                Intent intent = new Intent(this, MainListActivity.class);
                startActivity(intent);
            }
        });
    }

    public boolean login(String username, String password) {
        SharedPreferences dsp = PreferenceManager.getDefaultSharedPreferences(this);
        boolean logged = username.equals(dsp.getString("username", "")) &&
                dsp.getString("pass", "").equals(password);
        if (!logged) {
            Toast.makeText(this, "Username or Password is incorrect.", Toast.LENGTH_LONG).show();
        }
        return logged;
    }
}