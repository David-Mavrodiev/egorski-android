package com.example.dsm2001.e_gorski;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.example.data.models.Signal;
import com.example.data.models.SuccessSignalCreation;
import com.example.dsm2001.e_gorski.common.fragments.ConfirmDialogFragment;
import com.example.dsm2001.e_gorski.common.fragments.LoadingFragment;
import com.example.dsm2001.e_gorski.common.helpers.AuthenticationHelper;
import com.example.dsm2001.e_gorski.common.helpers.DrawerFactory;
import com.example.dsm2001.e_gorski.common.helpers.SignalHelper;
import com.example.dsm2001.e_gorski.common.utils.GPSTracker;

import java.util.ArrayList;

import io.reactivex.functions.Consumer;

public class SendSignalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_signal);
        this.setupDrawer();
        this.setupSpinner();
        this.setupSendSignalActivity();
    }

    protected void setupSendSignalActivity(){
        final EditText etFirstname = (EditText) this.findViewById(R.id.et_firstname);
        final EditText etLastname = (EditText) this.findViewById(R.id.et_firstname);
        final EditText etNumber = (EditText) this.findViewById(R.id.et_number);
        final Spinner descriptionList = (Spinner) this.findViewById(R.id.description_list);
        Button sendSignal = (Button) this.findViewById(R.id.btn_send_signal_submit);

        sendSignal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final LoadingFragment loadingFragment = LoadingFragment.create(SendSignalActivity.this, "Изпращане...");
                loadingFragment.show();
                String firstname = etFirstname.getText().toString();
                String lastname = etLastname.getText().toString();
                String number = etNumber.getText().toString();
                String description = descriptionList.getSelectedItem().toString();
                GPSTracker gps = new GPSTracker(SendSignalActivity.this);
                String lat = "0";
                String lng = "0";

                if(gps.canGetLocation()) {
                    lat = Double.toString(gps.getLatitude());
                    lng = Double.toString(gps.getLongitude());
                } else {
                    gps.showSettingsAlert();
                }

                Signal signal = new Signal(firstname, lastname, description, number, lat, lng);
                signal.setCookieText(AuthenticationHelper.getCookie().getCookieText());

                SignalHelper.create(signal).subscribe(new Consumer<SuccessSignalCreation>() {
                    @Override
                    public void accept(SuccessSignalCreation successSignalCreation) throws Exception {
                        final SuccessSignalCreation res = successSignalCreation;
                        if(successSignalCreation.getError() == null){
                            ConfirmDialogFragment dialog = ConfirmDialogFragment.create(SendSignalActivity.this, "Входящ номер", "Сигналът е приет под номер " + successSignalCreation.getCount(), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(SendSignalActivity.this, "Сигнал №" + res.getCount(), Toast.LENGTH_SHORT).show();
                                    loadingFragment.hide();
                                    Intent intent = new Intent(SendSignalActivity.this, HomeActivity.class);
                                    startActivity(intent);
                                }
                            });

                            dialog.show();
                        }else {
                            Toast.makeText(SendSignalActivity.this, "Cannot send", Toast.LENGTH_SHORT).show();
                            loadingFragment.hide();
                        }
                    }
                });
            }
        });
    }

    protected void setupSpinner(){
        ArrayList<String> items = new ArrayList<>();
        items.add("Незаконна сеч");
        items.add("Възникнал горски пожар");
        items.add("Отпадъци и/или нерегламентирани сметища");
        items.add("Транспортиране и съхранение на дървесина без разрешение");
        items.add("Лов и риболов извън сезона");
        items.add("Други нарушения");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner descriptionList = (Spinner) this.findViewById(R.id.description_list);
        descriptionList.setAdapter(adapter);
    }

    protected void setupDrawer() {
        View drawerContainer = this.findViewById(R.id.container_drawer);
        if (drawerContainer != null) {

            Fragment drawerFragment = DrawerFactory.getFragment(3, this);

            this.getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container_drawer, drawerFragment)
                    .commit();
        }
    }
}
