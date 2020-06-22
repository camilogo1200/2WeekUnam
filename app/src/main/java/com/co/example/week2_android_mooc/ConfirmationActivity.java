package com.co.example.week2_android_mooc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.co.example.week2_android_mooc.data.User;

import java.util.Date;

public class ConfirmationActivity extends AppCompatActivity {
    private User mUser = null;
    private TextView mName;
    private TextView mEmail;
    private TextView mPhone;
    private TextView mDescription;
    private TextView mBirthDate;
    private Button mBtnEditarDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        Bundle extras = getIntent().getExtras();
        if (extras != null && !extras.isEmpty()) {
            mUser = extras.getParcelable(getString(R.string.user_intent_payload));
            init();
        }

    }

    private void init() {
        mName = (TextView) findViewById(R.id.tv_name);
        mBirthDate = (TextView) findViewById(R.id.tv_fecha_nacimiento);
        mDescription = (TextView) findViewById(R.id.tv_descripcion);
        mEmail = (TextView) findViewById(R.id.tv_email);
        mPhone = (TextView) findViewById(R.id.tv_telefono);
        mBtnEditarDatos = (Button) findViewById(R.id.btn_editar_datos);

        mBtnEditarDatos.setOnClickListener(v -> goBack());

        if (mUser != null) {
            mName.setText(mUser.getNombre());
            Date bd = new Date(mUser.getBirthDay());
            java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("dd/MM/yyyy");
            mBirthDate.setText(format.format(bd));
            mPhone.setText(mUser.getTelefono());
            mEmail.setText(mUser.getEmail());
            mDescription.setText(mUser.getDescripcion());
        }
    }

    private void goBack() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(getString(R.string.user_intent_payload), mUser);
        startActivity(intent);
        finish();
    }
}