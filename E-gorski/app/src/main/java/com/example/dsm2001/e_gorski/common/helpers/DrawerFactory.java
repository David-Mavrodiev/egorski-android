package com.example.dsm2001.e_gorski.common.helpers;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;

import com.example.dsm2001.e_gorski.CheckSignalActivity;
import com.example.dsm2001.e_gorski.HelpActivity;
import com.example.dsm2001.e_gorski.HomeActivity;
import com.example.dsm2001.e_gorski.LoginActivity;
import com.example.dsm2001.e_gorski.SendSignalActivity;
import com.example.dsm2001.e_gorski.common.fragments.DrawerFragment;
import com.example.dsm2001.e_gorski.common.models.DrawerItemInfo;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;

public class DrawerFactory {
    public DrawerFactory(){

    }

    public static Fragment getFragment(int primaryId, final Context context){
        ArrayList<DrawerItemInfo> items = new ArrayList<>();

        items.add(new DrawerItemInfo(1, "Начало"));
        items.add(new DrawerItemInfo(2, "Помощ"));
        items.add(new DrawerItemInfo(3, "Сигнализирай"));
        items.add(new DrawerItemInfo(4, "Провери"));
        items.add(new DrawerItemInfo(5, "Изход"));

        Fragment drawerFragment =
                DrawerFragment.createFragment(items, primaryId, AuthenticationHelper.getCookie(), new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Intent intent;
                        switch ((int) drawerItem.getIdentifier()) {
                            case 1:
                                intent = new Intent(context.getApplicationContext(), HomeActivity.class);
                                context.startActivity(intent);
                                break;
                            case 2:
                                intent = new Intent(context.getApplicationContext(), HelpActivity.class);
                                context.startActivity(intent);
                                break;
                            case 3:
                                intent = new Intent(context.getApplicationContext(), SendSignalActivity.class);
                                context.startActivity(intent);
                                break;
                            case 4:
                                intent = new Intent(context.getApplicationContext(), CheckSignalActivity.class);
                                context.startActivity(intent);
                                break;
                            case 5:
                                AuthenticationHelper.logout();
                                intent = new Intent(context.getApplicationContext(), LoginActivity.class);
                                context.startActivity(intent);
                                break;
                        }

                        return true;
                    }
                });
        return drawerFragment;
    }
}
