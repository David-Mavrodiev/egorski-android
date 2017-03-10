package com.example.dsm2001.e_gorski;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.dsm2001.e_gorski.common.fragments.DrawerFragment;
import com.example.dsm2001.e_gorski.common.helpers.AuthenticationHelper;
import com.example.dsm2001.e_gorski.common.helpers.DrawerFactory;
import com.example.dsm2001.e_gorski.common.models.DrawerItemInfo;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        this.setupDrawer();
    }

    protected void setupDrawer() {
        View drawerContainer = this.findViewById(R.id.container_drawer);
        if (drawerContainer != null) {

            Fragment drawerFragment = DrawerFactory.getFragment(2, this);

            this.getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container_drawer, drawerFragment)
                    .commit();
        }
    }
}
