package com.example.dsm2001.e_gorski;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.data.models.CheckSignal;
import com.example.data.models.Signal;
import com.example.dsm2001.e_gorski.common.fragments.LoadingFragment;
import com.example.dsm2001.e_gorski.common.helpers.AuthenticationHelper;
import com.example.dsm2001.e_gorski.common.helpers.DrawerFactory;
import com.example.dsm2001.e_gorski.common.helpers.SignalHelper;

import io.reactivex.functions.Consumer;

public class CheckSignalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_signal);
        this.setupDrawer();
        this.setupCheckSignalActivity();
    }

    protected void setupDrawer() {
        View drawerContainer = this.findViewById(R.id.container_drawer);
        if (drawerContainer != null) {

            Fragment drawerFragment = DrawerFactory.getFragment(4, this);

            this.getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container_drawer, drawerFragment)
                    .commit();
        }
    }

    protected void setupCheckSignalActivity(){
        Button checkBtn = (Button) this.findViewById(R.id.check_signal_btn_submit);
        final LinearLayout checkForm = (LinearLayout) this.findViewById(R.id.check_signal_form);
        final TextView answer = (TextView) this.findViewById(R.id.text_answer);
        final EditText etNumber = (EditText) this.findViewById(R.id.et_register_number);
        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final LoadingFragment loadingFragment = LoadingFragment.create(CheckSignalActivity.this, "Проверка...");
                loadingFragment.show();
                CheckSignal checkSignal = new CheckSignal(etNumber.getText().toString(), AuthenticationHelper.getCookie().getCookieText());
                SignalHelper.check(checkSignal).subscribe(new Consumer<Signal>() {
                    @Override
                    public void accept(Signal signal) throws Exception {
                        if(signal.getError() == null){
                            checkForm.removeAllViews();
                            checkForm.setVisibility(View.INVISIBLE);
                            checkForm.removeAllViewsInLayout();
                            answer.setText(signal.getAnswer());
                            loadingFragment.hide();
                        }else {
                            loadingFragment.hide();
                            Toast.makeText(CheckSignalActivity.this, "Възникна грешка при проверката", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
