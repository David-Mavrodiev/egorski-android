package com.example.dsm2001.e_gorski;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dsm2001.e_gorski.common.activities.BaseActivity;
import com.example.dsm2001.e_gorski.common.helpers.AuthenticationHelper;
import com.example.dsm2001.e_gorski.common.helpers.DrawerFactory;


public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.setupHomeActivity();
    }

    private void setupHomeActivity(){
        LinearLayout authScreen = (LinearLayout) this.findViewById(R.id.auth_screen);
        LinearLayout notAuthScreen = (LinearLayout) this.findViewById(R.id.not_auth_screen);

        if(AuthenticationHelper.getCookie() == null){
            notAuthScreen.setVisibility(View.VISIBLE);
            authScreen.setVisibility(View.INVISIBLE);
            authScreen.removeAllViewsInLayout();
            this.setupNotAuth();
        }else {
            notAuthScreen.setVisibility(View.INVISIBLE);
            notAuthScreen.removeAllViewsInLayout();
            authScreen.setVisibility(View.VISIBLE);
            this.setupAuth();
        }
    }

    private void setupNotAuth(){
        Button redirectToLoginAndRegister = (Button) this.findViewById(R.id.loginAndRegister_redirect_button);

        redirectToLoginAndRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setupAuth(){
        TextView title = (TextView) this.findViewById(R.id.title_username_home);
        title.setText("Привет, " + AuthenticationHelper.getCookie().getUsername()  + "!");

        Button logout = (Button) this.findViewById(R.id.logout_button);
        Button helpRedirect = (Button) this.findViewById(R.id.help_redirect_button);
        Button sendSignalRedirect = (Button) this.findViewById(R.id.send_signal_redirect_button);
        Button checkSignalRedirect = (Button) this.findViewById(R.id.check_signal_redirect_button);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthenticationHelper.logout();
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        helpRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, HelpActivity.class);
                startActivity(intent);
            }
        });

        sendSignalRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SendSignalActivity.class);
                startActivity(intent);
            }
        });

        checkSignalRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, CheckSignalActivity.class);
                startActivity(intent);
            }
        });

        setupDrawer();
    }

    protected void setupDrawer() {
        View drawerContainer = this.findViewById(R.id.container_drawer);
        if (drawerContainer != null) {

            Fragment drawerFragment = DrawerFactory.getFragment(1, this);

            this.getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container_drawer, drawerFragment)
                    .commit();
        }
    }
}
