package com.co.example.week2_android_mooc;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.UserHandle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.co.example.week2_android_mooc.data.User;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button mBtnSiguiente;
    TextInputEditText mNombreCompleto;
    TextInputEditText mTelefono;
    TextInputEditText mEmail;
    TextInputEditText mDescripcion;
    DatePicker mDatePicker;
    User mUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mBtnSiguiente = (Button) findViewById(R.id.siguiente_btn);
        mBtnSiguiente.setOnClickListener(v -> {
            nextActivity();
        });
        mNombreCompleto = (TextInputEditText) findViewById(R.id.tiet_nombrecompleto);
        mDescripcion = (TextInputEditText) findViewById(R.id.tiet_descripcion);
        mTelefono = (TextInputEditText) findViewById(R.id.tiet_telefono);
        mEmail = (TextInputEditText) findViewById(R.id.tiet_email);
        mDatePicker = (DatePicker) findViewById(R.id.dp_birthday);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && !bundle.isEmpty()) {
            mUser = bundle.getParcelable(getString(R.string.user_intent_payload));
            mEmail.setText(mUser.getEmail());
            mTelefono.setText(mUser.getTelefono());
            mDescripcion.setText(mUser.getDescripcion());
            mNombreCompleto.setText(mUser.getNombre());
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date(mUser.getBirthDay()));
            mDatePicker.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), (view, year, monthOfYear, dayOfMonth) -> {
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, monthOfYear);
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                mUser.setBirthDay(cal.getTimeInMillis());
            });
        } else {
            mUser = new User();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                mDatePicker.setOnDateChangedListener((view, year, monthOfYear, dayOfMonth) -> {
                    Calendar cal = Calendar.getInstance();
                    cal.set(Calendar.YEAR, year);
                    cal.set(Calendar.MONTH, monthOfYear);
                    cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                    mUser.setBirthDay(cal.getTimeInMillis());
                    Log.d("DATE_CHANGED", cal.toString());
                });
            }
        }
    }

    private void nextActivity() {
        String nombreCompleto = (mNombreCompleto.getText() != null) ? mNombreCompleto.getText().toString() : null;
        String telefono = (mTelefono.getText() != null) ? mTelefono.getText().toString() : null;
        String email = (mEmail.getText() != null) ? mEmail.getText().toString() : null;
        String descripcion = (mDescripcion.getText() != null) ? mDescripcion.getText().toString() : null;

        mUser.setNombre(nombreCompleto);
        mUser.setTelefono(telefono);
        mUser.setEmail(email);
        mUser.setDescripcion(descripcion);

        Intent intent = new Intent(this, ConfirmationActivity.class);
        intent.putExtra(getString(R.string.user_intent_payload), mUser);
        startActivity(intent);
        finish();
    }
}
