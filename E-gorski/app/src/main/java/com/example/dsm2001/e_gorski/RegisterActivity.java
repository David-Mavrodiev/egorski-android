package com.example.dsm2001.e_gorski;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.data.models.AuthenticationCookie;
import com.example.data.models.User;
import com.example.dsm2001.e_gorski.common.activities.BaseActivity;
import com.example.dsm2001.e_gorski.common.fragments.LoadingFragment;
import com.example.dsm2001.e_gorski.common.helpers.AuthenticationHelper;

import io.reactivex.functions.Consumer;

public class RegisterActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.setupRegisterActivity();
    }

    private void setupRegisterActivity(){
        Button loginBtn = (Button) this.findViewById(R.id.btn_register_submit);
        final EditText etUsername = (EditText)this.findViewById(R.id.et_register_username);
        final EditText etPassword = (EditText)this.findViewById(R.id.et_register_password);
        Button loginRedirect = (Button) this.findViewById(R.id.login_button);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final LoadingFragment loadingFragment = LoadingFragment.create(RegisterActivity.this, "Регистриране...");
                loadingFragment.show();
                User user = new User(etUsername.getText().toString(), etPassword.getText().toString());
                AuthenticationHelper.register(user).subscribe(new Consumer<AuthenticationCookie>() {
                    @Override
                    public void accept(AuthenticationCookie cookie) throws Exception {
                        if(cookie.getError() != null){
                            Toast.makeText(RegisterActivity.this, "Възникна грешка при регистрацията", Toast.LENGTH_SHORT).show();
                            loadingFragment.hide();
                        }else {
                            Toast.makeText(RegisterActivity.this, "Успешна регистрация", Toast.LENGTH_SHORT).show();
                            AuthenticationHelper.saveCookie(cookie);
                            loadingFragment.hide();
                            Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                            startActivity(intent);
                        }
                    }
                });
            }
        });

        loginRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
