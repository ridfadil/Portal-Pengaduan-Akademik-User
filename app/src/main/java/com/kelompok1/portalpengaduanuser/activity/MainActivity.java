package com.kelompok1.portalpengaduanuser.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.kelompok1.portalpengaduanuser.adapter.AdapterPager;
import com.kelompok1.portalpengaduanuser.R;
import com.kelompok1.portalpengaduanuser.session.SessionManager;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final Integer[] Gambar = {R.drawable.coba, R.drawable.coba2, R.drawable.coba3, R.drawable.coba4};
    private static ViewPager mPager;
    private static int currentPage = 0;
    SessionManager session;

    private ArrayList<Integer> GambarArray = new ArrayList<Integer>();

    private void init() {
        for (int i = 0; i < Gambar.length; i++)
            GambarArray.add(Gambar[i]);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new AdapterPager(MainActivity.this, GambarArray));
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == Gambar.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 5000, 5000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

/*        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
/*        if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_fasilitas) {
            // Aksi Pindah Activity
            Intent i = new Intent(MainActivity.this, FasilitasActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_organisasi) {
            Intent i = new Intent(MainActivity.this, OrganisasiActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_dosen) {
            Intent i = new Intent(MainActivity.this, DosenActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_administrasi) {
            Intent i = new Intent(MainActivity.this, AdministrasiActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_list_dosen) {
            Intent i = new Intent(MainActivity.this, ListDataDosenActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_ganti_password) {
            Intent i = new Intent(MainActivity.this, GantiPasswordActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_about) {
            Intent i = new Intent(MainActivity.this, TentangKamiActivity.class);
            startActivity(i);
        }
        else if (id == R.id.nav_logout) {
            session = new SessionManager(getApplicationContext());
            session.logoutUser();
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
