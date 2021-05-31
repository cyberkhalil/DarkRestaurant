package com.example.products.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.products.R;
import com.example.products.model.Purchase;

public class RegisterActivity extends AppCompatActivity {

    EditText et_full_name, et_email, et_birthdate, et_pass, et_re_pass, et_username, et_phone;
    Spinner sp_country;
    CheckBox cb_is_admin;
    RadioGroup rg_gender;
    Button btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences dsp = PreferenceManager.getDefaultSharedPreferences(this);
        if (dsp.getBoolean("logged", false)) {
            Intent intent = new Intent(this, MainListActivity.class);
            startActivity(intent);
            this.finish();
        }

        setContentView(R.layout.activity_register);

        et_full_name = findViewById(R.id.register_et_full_name);
        et_email = findViewById(R.id.register_et_email);
        et_birthdate = findViewById(R.id.register_et_birthdate);
        et_pass = findViewById(R.id.register_et_pass);
        et_re_pass = findViewById(R.id.register_et_re_pass);
        et_username = findViewById(R.id.register_et_username);
        et_phone = findViewById(R.id.register_et_phone);

        sp_country = findViewById(R.id.register_sp_country);

        cb_is_admin = findViewById(R.id.register_cb_is_admin);

        rg_gender = findViewById(R.id.register_rg_gender);

        btn_save = findViewById(R.id.register_btn_save);

        btn_save.setOnClickListener(v -> {
            if (canRegister()) {
                SharedPreferences.Editor edit = dsp.edit();
                edit.putString("full_name", et_full_name.getText().toString());
                edit.putString("email", et_email.getText().toString());
                edit.putString("birthdate", et_birthdate.getText().toString());
                edit.putString("pass", et_pass.getText().toString());
                edit.putString("username", et_username.getText().toString());
                edit.putString("phone", et_phone.getText().toString());
                edit.putString("country", sp_country.getSelectedItem().toString());
                edit.putBoolean("is_admin", cb_is_admin.isChecked());

                int checked_gender_id = rg_gender.getCheckedRadioButtonId();
                RadioButton checked_gender_rb = findViewById(checked_gender_id);
                edit.putString("gender", checked_gender_rb.getText().toString());
                edit.apply();

                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public boolean canRegister() {
        String registrationErrors = "";
        if (et_username.getText().toString().trim().isEmpty()) {
            registrationErrors += "Username can't be empty.";
        }
        if (et_pass.getText().toString().trim().isEmpty()) {
            registrationErrors += "\nPassword can't be empty.";
        }
        if (!et_pass.getText().toString().equals(et_re_pass.getText().toString())) {
            registrationErrors += "\nPassword and Re doesn't match.";
        }
        if (rg_gender.getCheckedRadioButtonId() == -1) {
            registrationErrors += "\nGender is not selected.";
        }
        if (registrationErrors.isEmpty()) return true;
        else {
            Toast.makeText(this, registrationErrors.trim(), Toast.LENGTH_LONG).show();
            return false;
        }
    }
}
