package com.example.retard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.retard.dataClasses.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class RegistrationActivity extends AppCompatActivity {
    @BindView(R.id.Nickname) EditText nameET;
    @BindView(R.id.Email) EditText emailET;
    @BindView(R.id.Phone) EditText phoneET;
    @BindView(R.id.Password) EditText passwordET;
    @BindView(R.id.EnterButton) Button enterBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        ButterKnife.bind(this);

        enterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final User user = new User();
                user.RegisterNewUserInDatabase(
                        nameET.getText().toString(),
                        emailET.getText().toString(),
                        phoneET.getText().toString(),
                        passwordET.getText().toString(),
                        new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Intent intent = new Intent();
                                intent.putExtra("user", user);
                                setResult(RESULT_OK, intent);
                                finish();
                            }
                        },
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toasty.error(getApplicationContext(),
                                        "Нет соединения с сервером").show();
                            }
                        }
                );
            }
        });
    }

}
