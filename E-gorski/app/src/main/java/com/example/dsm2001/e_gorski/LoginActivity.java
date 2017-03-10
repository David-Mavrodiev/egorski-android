package com.example.dsm2001.e_gorski;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.setupLoginActivity();
    }

    private void setupLoginActivity(){
        Button loginBtn = (Button) this.findViewById(R.id.btn_login_submit);
        final EditText etUsername = (EditText)this.findViewById(R.id.et_login_username);
        final EditText etPassword = (EditText)this.findViewById(R.id.et_login_password);
        Button registerRedirect = (Button) this.findViewById(R.id.register_button);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final LoadingFragment loadingFragment = LoadingFragment.create(LoginActivity.this, "Влизане...");
                loadingFragment.show();
                User user = new User(etUsername.getText().toString(), etPassword.getText().toString());
                AuthenticationHelper.login(user).subscribe(new Consumer<AuthenticationCookie>() {
                    @Override
                    public void accept(AuthenticationCookie cookie) throws Exception {
                        if(cookie.getError() != null){
                            Toast.makeText(LoginActivity.this, "Грешна парола или потребителско име", Toast.LENGTH_SHORT).show();
                            loadingFragment.hide();
                        }else {
                            Toast.makeText(LoginActivity.this, "Успешен вход", Toast.LENGTH_SHORT).show();
                            AuthenticationHelper.saveCookie(cookie);
                            loadingFragment.hide();
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);
                        }
                    }
                });
            }
        });

        registerRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
