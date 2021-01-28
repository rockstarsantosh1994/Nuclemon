package com.nucleonai.nuclemon.activity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.navigation.NavigationView;
import com.nucleonai.nuclemon.R;
import com.nucleonai.nuclemon.constants.LocalConstatns;
import com.nucleonai.nuclemon.fragment.AllServersFragment;
import com.nucleonai.nuclemon.fragment.DashBoardFragment;
import com.nucleonai.nuclemon.fragment.NotificationFragment;
import com.nucleonai.nuclemon.retrofit.APIInterface;
import com.nucleonai.nuclemon.utils.Utility;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import me.ibrahimsn.lib.SmoothBottomBar;
import retrofit2.Retrofit;

public class DashboardActivity extends AppCompatActivity {

    @BindView(R.id.bottomBar)
    SmoothBottomBar smoothBottomBar;

    private DrawerLayout mDrawer;
    public static Toolbar toolbar;
    private NavigationView nvDrawer;
    // Make sure to be using androidx.appcompat.app.ActionBarDrawerToggle version.
    private NavigationView navigationView;
    private Utility utility = new Utility();
    private APIInterface apiInterface;
    private Retrofit retrofitClient;
    private ActionBarDrawerToggle drawerToggle;
    public static CircleImageView imgprofile;
    public static TextView txtName, txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);

        mInitComp();
    }

    @SuppressLint("RestrictedApi")
    void mInitComp() {
        // Set a Toolbar to replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // This will display an Up icon (<-), we will replace it with hamburger later
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Find our drawer view
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();

        // Setup toggle to display hamburger icon with nice animation
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();

        // Tie DrawerLayout events to the ActionBarToggle
        mDrawer.addDrawerListener(drawerToggle);
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        navigationView = (NavigationView) findViewById(R.id.nvView);
        navigationView.setItemIconTintList(null);
        // Inflate the header view at runtime
        View headerLayout = navigationView.inflateHeaderView(R.layout.nav_header);
        // We can now look up items within the header if needed
        imgprofile = headerLayout.findViewById(R.id.imgprofile);
        txtName = headerLayout.findViewById(R.id.txtName);

        txtName.setText(utility.getPreferences(LocalConstatns.LOGIN_NAME));
        //txtEmail.setText(utility.getPreferences(LocalConstatns.LOGIN_EMAIL));

        // Setup drawer view
        setupDrawerContent(nvDrawer);

        mLoadFragment(new DashBoardFragment());
        /*if(utility.getPreferences(LocalConstatns.FROM).equals("splash") || utility.getPreferences(LocalConstatns.FROM).equals("outlet"))
        {
            mLoadFragment(new OutletFragment());
        }else if(utility.getPreferences(LocalConstatns.FROM).equals("info")){
            mLoadFragment(new MyInfoMainFragment());
        }else if(utility.getPreferences(LocalConstatns.FROM).equals("loadout")){
            mLoadFragment(new LoadoutsFragment());
        }else{
            mLoadFragment(new OutletFragment());
        }*/

        smoothBottomBar.setItemActiveIndex(0);

        //Bottom navigation transaction...
        smoothBottomBar.setOnItemSelectedListener(i -> {
            switch (i){
                case 0:
                    mLoadFragment(new DashBoardFragment());
                    break;

                case 1:
                    mLoadFragment(new AllServersFragment());
                    break;

                case 2:
                    mLoadFragment(new NotificationFragment());
                    break;

                case 3:

                    break;
            }
            return false;
        });
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        //  selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open, R.string.drawer_close);
    }

    void mLoadFragment(Fragment fragment) {
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.flContent, fragment);
        tx.commit();
    }
}
//https://programmer.help/blogs/using-viewpager-to-realize-3d-gallery-effect-and-optimize-image-loading.html