package com.venky97vp.android.dietjanitor;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        TaskFragment.OnFragmentInteractionListener,
        UserInfoFragment.OnFragmentInteractionListener,
        EditUserFragment.OnFragmentInteractionListener,
        AddFragment.OnFragmentInteractionListener{

    DatabaseReference mDatabase;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    User value;
    TextView txtProfileName,emailId;
    AlertDialog internetDialog;

    public boolean isInternetAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(isInternetAvailable()){
            displayInternetDialog();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(internetDialog!=null){
            internetDialog.dismiss();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


//        if(InternetCheck.isInternetAvailable()){
//            InternetCheck.noInternet(this);
//        }

        mAuth = FirebaseAuth.getInstance();
        mDatabase = database.getReference().child("users").child(mAuth.getCurrentUser().getUid());

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                    value = dataSnapshot.getValue(User.class);
                    //Log.d(TAG, "Value is: " + value);
                if(value!=null) {
                    txtProfileName.setText(value.name);
                    emailId.setText(mAuth.getCurrentUser().getEmail());
                }
                else{
                    startActivity(new Intent(getApplicationContext(),UserInfoActivity.class));
                    finish();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        txtProfileName = (TextView) navigationView.getHeaderView(0).findViewById(R.id.user_name);
        emailId = (TextView) navigationView.getHeaderView(0).findViewById(R.id.user_email);
        displaySelectedScreen(R.id.nav_progress);
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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    private FirebaseAuth mAuth;

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        displaySelectedScreen(item.getItemId());

        return true;
    }

    void displayInternetDialog(){
        internetDialog = new AlertDialog.Builder(this)
                .setTitle("No Internet")
                .setMessage("Please Check your internet connection")
                .setPositiveButton("settings", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                })
                .setNegativeButton("ok", null)
                .show();
    }

    private void displaySelectedScreen(int itemId) {

        Fragment fragment = null;

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.nav_user:
                fragment = new UserInfoFragment();
                break;
            case R.id.nav_add_food:
                fragment = new AddFragment();
                break;
            case R.id.nav_progress:
                fragment = new TaskFragment();
                break;
            case R.id.nav_signout:
                new AlertDialog.Builder(this)
                        .setTitle("Sign out")
                        .setMessage("Do you really want to Sign out from your account?")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mAuth.signOut();
                                Toast.makeText(getApplicationContext(),"Signed out",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(),SigninActivity.class));
                                finish();
                            }
                        })
                        .setNegativeButton("no", null)
                        .show();

                break;
        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
