package demo.kolorob.kolorobdemoversion.activity;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Vector;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.interfaces.VolleyApiCallback;
import demo.kolorob.kolorobdemoversion.utils.AlertMessage;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;

import static demo.kolorob.kolorobdemoversion.parser.VolleyApiParser.getRequest;

public class PlaceChoiceActivity2 extends AppCompatActivity implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;

    LinearLayout first,second,third,menubar,SearchBar,SearchIcon,imgbau,imgpar;
    int width,height;
    EditText Searchall;
    private static final int DELAY_PLACE_DETAILS_LAUNCH_ANIM = 500;
    Vector vector= new Vector();
    Vector compare= new Vector();
    Vector vectorHel= new Vector();
    Vector vectorEnt= new Vector();
    Vector vectorEdu= new Vector();
    Vector vectorFin= new Vector();
    Vector vectorLeg= new Vector();
    String app_ver;
    NotificationManager manager;
    Notification myNotication;

    private Context con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_choice2);


        con = this;
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        height= displayMetrics.heightPixels-32;
        width=displayMetrics.widthPixels-32;

        Log.d("...>>>","Layout width"+width);

        try
        {
            app_ver = this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName;
        }


        catch (PackageManager.NameNotFoundException e) {
           // Log.e(tag, e.getMessage());

        }

        Log.d(">>>","Application Version: "+app_ver);


        checkVersion(Double.parseDouble(app_ver));

            first=(LinearLayout)findViewById(R.id.top_section);
        second= (LinearLayout)findViewById(R.id.bauniabad_section);
        third = (LinearLayout)findViewById(R.id.parisRoad_section);
       // menubar=(LinearLayout)findViewById(R.id.menuBar);
       // SearchBar=(LinearLayout)findViewById(R.id.SearchBar);
       // SearchIcon=(LinearLayout)findViewById(R.id.SearchIcon);
        imgbau=(LinearLayout)findViewById(R.id.imageBau);
        imgpar=(LinearLayout)findViewById(R.id.imagePar);

       //LayoutParams = first.getLayoutParams();
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) first.getLayoutParams();
        params.height = height/6;
        params.width = width;
        first.setLayoutParams(params);


        LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) second.getLayoutParams();
        params2.height = height/5;
        params2.width = width;
        second.setLayoutParams(params2);

        LinearLayout.LayoutParams params3 = (LinearLayout.LayoutParams) third.getLayoutParams();
        params3.height = height/5;
        params3.width = width;
        third.setLayoutParams(params3);





//        LinearLayout.LayoutParams paramsMenue = (LinearLayout.LayoutParams) menubar.getLayoutParams();
//        paramsMenue.height = height/16;
//        paramsMenue.width = (width*2)/14;
//
//        Log.d("...>>>", "Munue Width" + (width * 2) / 14);
//        menubar.setLayoutParams(paramsMenue);
//
//        LinearLayout.LayoutParams paramsSearch = (LinearLayout.LayoutParams) SearchBar.getLayoutParams();
//        paramsSearch.height = height/16;
//        paramsSearch.width = (width*10)/14;
//        Log.d("...>>>", "SearchBar Width" + (width * 10) / 14);
//        SearchBar.setLayoutParams(paramsSearch);
//
//        LinearLayout.LayoutParams paramsSearchIcon = (LinearLayout.LayoutParams) SearchIcon.getLayoutParams();
//        paramsSearchIcon.height = height/16;
//        paramsSearchIcon.width = (width*2)/14;
//        Log.d("...>>>", "Search Icon" + (width * 2) / 14);
//        SearchIcon.setLayoutParams(paramsSearchIcon);


        LinearLayout.LayoutParams paramsBau = (LinearLayout.LayoutParams) imgbau.getLayoutParams();

        paramsBau.width = (width*2)/3;
        imgbau.setLayoutParams(paramsBau);

Searchall=(EditText)findViewById(R.id.searchall);
        Searchall.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                Intent i=new Intent(getApplicationContext(),SearchActivity.class);
                startActivity(i);

                return false;
            }
        });
        LinearLayout.LayoutParams paramsPar = (LinearLayout.LayoutParams) imgpar.getLayoutParams();

        paramsPar.width = (width*2)/3;
        imgpar.setLayoutParams(paramsPar);

        Searchall.setOnClickListener((View.OnClickListener) this);
        imgbau.setOnClickListener((View.OnClickListener) this);
        imgpar.setOnClickListener((View.OnClickListener) this);

//       if(height>1000)
       toolbar = (Toolbar) findViewById(R.id.toolbar);
//        else
//           toolbar = (Toolbar) findViewById(R.id.toolbars);

        // toolbar.setBackgroundResource(android.R.color.transparent);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.menu_icon);
        ab.setDisplayHomeAsUpEnabled(true);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //  getSupportActionBar().setTitle("Navigation!");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                // getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toggle.setDrawerIndicatorEnabled(true);
        drawer.setDrawerListener(toggle);
        //toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }


   public void checkVersion(final double current_version)
    {
        getRequest(PlaceChoiceActivity2.this, "http://kolorob.net/app_version.json", new VolleyApiCallback() {
                    @Override
                    public void onResponse(int status, String apiContent) {
                        Log.d(">>>","Start Json Parsing "+apiContent);
                            try {
                                JSONObject jo = new JSONObject(apiContent);
                                Log.d(">>>","JsonObject: "+jo);
                                Double remote_version = jo.getDouble("version");

                                if(remote_version>current_version)
                                {
                                    Toast.makeText(PlaceChoiceActivity2.this, "You must update the App =)",
                                            Toast.LENGTH_LONG).show();
                                    generateNotification();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                    }
                }
        );
    }



    public void generateNotification()
    {
        String url = "https://play.google.com/store/apps/details?id=demo.kolorob.kolorobdemoversion&hl=en";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        // i.setData(Uri.parse("package:demo.kolorob.kolorobdemoversion"));

        //Intent intent = new Intent(this,NotificationView.class);

        manager.cancel(11);
        PendingIntent pendingIntent = PendingIntent.getActivity(PlaceChoiceActivity2.this, 0, i, 0);

        Notification.Builder builder = new Notification.Builder(PlaceChoiceActivity2.this);

        builder.setAutoCancel(false);
        builder.setTicker("New Version of Kolorob is Available");
        builder.setContentTitle("Update kolorob");
      //  builder.setContentText("To update click here.");
        builder.setSmallIcon(R.drawable.kolorob_logo_first_page);
        builder.setContentIntent(pendingIntent);
        builder.setOngoing(true);
      //  builder.setSubText("Click here to update");   //API level 16
        builder.setNumber(100);
     //   builder.build();

         builder.setContentTitle("Update kolorob").setContentText("New Version of Kolorob is Available")
                .setSmallIcon(R.drawable.kolorob_logo_first_page).getNotification();

        myNotication = builder.getNotification();
        manager.notify(11, myNotication);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.imageBau:

                gotoPlaceDetailsView(AppConstants.PLACE_BAUNIABADH);
                break;

            case R.id.imagePar:

                gotoPlaceDetailsView(AppConstants.PLACE_PARIS_ROAD);
                break;
            case R.id.searchall:
                Intent i=new Intent(this,SearchActivity.class);
                startActivity(i);


            default:
                break;

        }

    }



    private void gotoPlaceDetailsView(final int placeId) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(PlaceChoiceActivity2.this, PlaceDetailsActivityNew.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(AppConstants.KEY_PLACE, placeId);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
               // startActivity(intent);

            }
        }, DELAY_PLACE_DETAILS_LAUNCH_ANIM);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Close")
                .setMessage("Are you sure you want to close Kolorob")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_place_choice_activity2, menu);
        return true;
    }


    @Override
    protected void onStart() {
        // TODO Auto-generated method stub

        super.onStart();

        System.out.println("----main activity---onStart---");
        this.overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.phone_reg) {
            // Handle the camera action
            Intent em = new Intent(this, PhoneRegActivity.class);
            startActivity(em);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        } else if (id == R.id.info_change) {

            Intent em = new Intent(this, Information_UpdateActivity.class);
            startActivity(em);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        } else if (id == R.id.emergency_info) {

          //  Toast.makeText(con,"emergency",Toast.LENGTH_LONG).show();
            Intent em = new Intent(this, NewEmergency.class);
            startActivity(em);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        } else if (id == R.id.local_representative) {

           // Toast.makeText(con,"It will be added in next version.",Toast.LENGTH_LONG).show();
            AlertMessage.showMessage(con, "Representative", "It will be added in next version.");

        } else if (id == R.id.adv_info) {
          //  Toast.makeText(con,"It will be added in next version.",Toast.LENGTH_LONG).show();

            AlertMessage.showMessage(con,"Advertisement","It will be added in next version.");
        } else if (id == R.id.adv) {
            AlertMessage.showMessage(con,"Ads Information","It will be added in next version.");
        }

//        else if (id == R.id.nav_share) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }
}
