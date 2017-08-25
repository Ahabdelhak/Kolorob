package demo.kolorob.kolorobdemoversion.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.adapters.Comment_layout_adapter;
import demo.kolorob.kolorobdemoversion.adapters.DefaultAdapter;
import demo.kolorob.kolorobdemoversion.database.CommentTable;
import demo.kolorob.kolorobdemoversion.fragment.MapFragmentRouteOSM;
import demo.kolorob.kolorobdemoversion.helpers.Helpes;
import demo.kolorob.kolorobdemoversion.model.CommentItem;
import demo.kolorob.kolorobdemoversion.model.NGO.NGONewDBModel;
import demo.kolorob.kolorobdemoversion.utils.AlertMessage;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;
import demo.kolorob.kolorobdemoversion.utils.AppUtils;
import demo.kolorob.kolorobdemoversion.utils.SharedPreferencesHelper;
import demo.kolorob.kolorobdemoversion.utils.ToastMessageDisplay;

/**
 * Created by zahidul.islam
 */

public class DetailsInfoActivityNGO extends AppCompatActivity {

    Dialog dialog;
    LinearLayout upperHand, upperText, left_way, middle_phone, right_email, bottom_bar;
    ImageView left_image, middle_image, right_image, email_btn;
    ArrayList<CommentItem> commentItems;
    ImageView comments;
    int inc;

    int width, height;
    TextView ups_text;

    Context con;

    String[] key;
    String[] value;
    int increment = 0;
    ListView alldata;
    RatingBar ratingBar;
    String username = "kolorobapp";
    String password = "2Jm!4jFe3WgBZKEN";
    NGONewDBModel ngoServiceProviderItemNew;
    //ArrayList<NGODetailsItem> ngoDetailsItems;


    private TextView ratingText;
    private ImageView distance_left, feedback;
    RadioGroup feedRadio;
    RadioButton rb1;
    String status = "", phone_num = "", uname = "";


    EditText feedback_comment;
    String datevalue, datevaluebn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_info_ngo);
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
        con = this;


        Intent intent = getIntent();


        if (null != intent) {
            ngoServiceProviderItemNew = (NGONewDBModel) intent.getSerializableExtra(AppConstants.KEY_DETAILS_NGO);

        }


        upperHand = (LinearLayout) findViewById(R.id.upper_part);
        upperText = (LinearLayout) findViewById(R.id.upperText);
        left_way = (LinearLayout) findViewById(R.id.left_go_process);
        middle_phone = (LinearLayout) findViewById(R.id.middle_phone);
        right_email = (LinearLayout) findViewById(R.id.right_email);
        left_image = (ImageView) findViewById(R.id.distance_left);
        bottom_bar = (LinearLayout) findViewById(R.id.bottom_bar);
        middle_image = (ImageView) findViewById(R.id.phone_middl);
        right_image = (ImageView) findViewById(R.id.right_side_email);

        ratingText = (TextView) findViewById(R.id.ratingText);
        feedback = (ImageView) findViewById(R.id.feedback);

        key = new String[600];

        value = new String[600];
        alldata = (ListView) findViewById(R.id.allData);

        LinearLayout.LayoutParams feedbacks = (LinearLayout.LayoutParams) feedback.getLayoutParams();
        feedbacks.height = width / 8;
        feedbacks.width = width / 8;
        feedback.setLayoutParams(feedbacks);


        ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) alldata
                .getLayoutParams();

        mlp.setMargins(width / 100, 0, width / 990, width / 8);



        SharedPreferences settings = DetailsInfoActivityNGO.this.getSharedPreferences("prefs", 0);
        Date date2 = new Date(settings.getLong("time", 0));
        Date today = new Date();
        long diffInMillisec = today.getTime() - date2.getTime();

        long diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMillisec);
        if (diffInDays == 0) datevalue = " আজকের তথ্য";
        else {
            datevaluebn = English_to_bengali_number_conversion(String.valueOf(diffInDays));
            datevalue = "" + datevaluebn + " দিন আগের তথ্য";
        }
        ToastMessageDisplay.setText(this, datevalue);
        ToastMessageDisplay.showText(this);
        distance_left = (ImageView) findViewById(R.id.distance_left);
        email_btn = (ImageView) findViewById(R.id.right_side_email);

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        setRatingBar();




        middle_image.getLayoutParams().height = width / 8;
        middle_image.getLayoutParams().width = width / 8;



        right_image.getLayoutParams().height = width / 8;
        right_image.getLayoutParams().width = width / 8;

        left_image.getLayoutParams().height = width / 8;
        left_image.getLayoutParams().width = width / 8;




        ups_text = (TextView) findViewById(R.id.ups_text);

        ups_text.setTextSize(23);
        ups_text.setText(ngoServiceProviderItemNew.getNamebn());
        ratingText.setTextSize(23);




        CheckConcate("প্রতিষ্ঠানের  ধরণ", ngoServiceProviderItemNew.getServicetype());
        CheckConcate("সেবাসমূহ", ngoServiceProviderItemNew.getServices());
        CheckConcate("যাদের জন্য প্রযোজ্য", ngoServiceProviderItemNew.getServices_for());
        CheckConcate("অন্যান্য সেবা", ngoServiceProviderItemNew.getServices_others());
        CheckConcate("অবস্থানের সময়", ngoServiceProviderItemNew.getDrop_time());
        CheckConcate("ফি", ngoServiceProviderItemNew.getNgo_fee());


        CheckConcate("রাস্তা", English_to_bengali_number_conversion(ngoServiceProviderItemNew.getRoad()));
        CheckConcate("ব্লক", English_to_bengali_number_conversion(ngoServiceProviderItemNew.getBlock()));
        CheckConcate("এলাকা", ngoServiceProviderItemNew.getAreabn());
        CheckConcate("ওয়ার্ড", English_to_bengali_number_conversion(ngoServiceProviderItemNew.getWard()));
        // CheckConcate("পোস্ট অফিস", educationNewItem.getp());
        CheckConcate("পুলিশ স্টেশন", ngoServiceProviderItemNew.getPolicestation());

        CheckConcate("বাড়ির নাম্বার", English_to_bengali_number_conversion(ngoServiceProviderItemNew.getHouseno()));

        CheckConcate("যোগাযোগ", ngoServiceProviderItemNew.getNode_contact());

        CheckConcate("ইমেইল", ngoServiceProviderItemNew.getNode_email());

        timeProcessing("খোলার সময়", ngoServiceProviderItemNew.getOpeningtime());
        timeProcessing("বন্ধের সময়", ngoServiceProviderItemNew.getClosetime());

        CheckConcate("কবে বন্ধ থাকে", ngoServiceProviderItemNew.getOffday());


        CheckConcate("অন্যান্য তথ্য ", ngoServiceProviderItemNew.getOtherinfo());


        DefaultAdapter defaultAdapter = new DefaultAdapter(this, key, value, increment);
        alldata.setAdapter(defaultAdapter);





        comments = (ImageView) findViewById(R.id.comments);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width / 8, width / 8);
        lp.setMargins(width / 24, 0, 0, 0);
        comments.setLayoutParams(lp);
        CommentTable commentTable = new CommentTable(DetailsInfoActivityNGO.this);


        commentItems = commentTable.getAllFinancialSubCategoriesInfo(String.valueOf(ngoServiceProviderItemNew.getNgoid()));
        int size = commentItems.size();
        String[] phone = new String[size];
        String[] date = new String[size];
        String[] comment = new String[size];
        final String[] rating = new String[size];


        for (CommentItem commentItem : commentItems) {
            Log.d("Rating", "$$$$$$" + commentItem.getRating());

            if (!commentItem.getRating().equals("")) {
                phone[inc] = commentItem.getUser_name();
                if (commentItem.getComment().equals("")) date[inc] = "কমেন্ট করা হয় নি ";
                else {
                    date[inc] = commentItem.getComment();
                }
                comment[inc] = English_to_bengali_number_conversion(commentItem.getDate());
                rating[inc] = commentItem.getRating();
                inc++;
            }

        }


        final Comment_layout_adapter comment_layout_adapter = new Comment_layout_adapter(this, phone, date, comment, rating);


        comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SharedPreferencesHelper.getifcommentedalready(DetailsInfoActivityNGO.this, String.valueOf(ngoServiceProviderItemNew.getNgoid()), uname).equals("yes") || inc > 0) {
                    if (SharedPreferencesHelper.getifcommentedalready(DetailsInfoActivityNGO.this, String.valueOf(ngoServiceProviderItemNew.getNgoid()), uname).equals("yes") && inc == 0) {
                        AlertMessage.showMessage(con, "দুঃখিত",
                                "কমেন্ট দেখতে দয়া করে তথ্য আপডেট করুন");

                    } else {
                        if (SharedPreferencesHelper.getifcommentedalready(DetailsInfoActivityNGO.this, String.valueOf(ngoServiceProviderItemNew.getNgoid()), uname).equals("yes")) {
                            ToastMessageDisplay.setText(con,
                                    "আপনার করা কমেন্ট দেখতে দয়া করে তথ্য আপডেট করুন");
                            ToastMessageDisplay.showText(con);
                        }
                        LayoutInflater layoutInflater = LayoutInflater.from(DetailsInfoActivityNGO.this);
                        final View promptView = layoutInflater.inflate(R.layout.comment_popup, null);
                        final Dialog alertDialog = new Dialog(DetailsInfoActivityNGO.this);
                        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        alertDialog.setContentView(promptView);
                        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        alertDialog.show();
                        Log.d("Value of Inc1", "======");


//                    final TextView textView=(TextView)promptView.findViewById(R.id.header);
                        final ListView listView = (ListView) promptView.findViewById(R.id.comment_list);

                        final ImageView close = (ImageView) promptView.findViewById(R.id.closex);
                        // ratingBars = (RatingBar)promptView.findViewById(R.id.ratingBar_dialogue);
                        final TextView review = (TextView) promptView.findViewById(R.id.review);

                        final ImageView ratingbarz = (ImageView) promptView.findViewById(R.id.ratingBarz);

                        try {
                            int ratings = Integer.parseInt(ngoServiceProviderItemNew.getRatings());

                            if (ratings == 1) {
                                ratingbarz.setBackgroundResource(R.drawable.one);
                            } else if (ratings == 2)
                                ratingbarz.setBackgroundResource(R.drawable.two);

                            else if (ratings == 3)
                                ratingbarz.setBackgroundResource(R.drawable.three);

                            else if (ratings == 4)
                                ratingbarz.setBackgroundResource(R.drawable.four);

                            else if (ratings == 5)
                                ratingbarz.setBackgroundResource(R.drawable.five);
                        } catch (Exception e) {

                        }

                        review.setText(English_to_bengali_number_conversion(Integer.toString(inc)) + " রিভিউ");
                        Double screenSize = AppUtils.ScreenSize(DetailsInfoActivityNGO.this);
                        if (screenSize > 6.5) {
                            review.setTextSize(20);
                        } else {
                            review.setTextSize(16);


                        }


                        listView.setAdapter(comment_layout_adapter);
//                    textView.setVisibility(View.GONE);

                        alertDialog.getWindow().setLayout((width * 5) / 6, (height * 2) / 3);

                        close.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alertDialog.dismiss();
                            }
                        });


                        alertDialog.setCancelable(false);


                        alertDialog.show();

                    }
                } else if (inc == 0) {
                    LayoutInflater layoutInflater = LayoutInflater.from(DetailsInfoActivityNGO.this);
                    View promptView = layoutInflater.inflate(R.layout.verify_reg_dialog, null);
                    final Dialog alertDialog = new Dialog(DetailsInfoActivityNGO.this);
                    alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    alertDialog.setContentView(promptView);
                    alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    alertDialog.show();
                    final ImageView yes = (ImageView) promptView.findViewById(R.id.yes);
                    final ImageView no = (ImageView) promptView.findViewById(R.id.no);
                    final TextView textAsk = (TextView) promptView.findViewById(R.id.textAsk);
                    String text = "এই সেবা সম্পর্কে কেউ এখনো মন্তব্য করেনি " + "\n" + "আপনি কি আপনার মতামত জানাতে চান?";
                    textAsk.setText(text);
                    alertDialog.getWindow().setLayout((width * 5) / 6, WindowManager.LayoutParams.WRAP_CONTENT);

                    if (SharedPreferencesHelper.isTabletDevice(DetailsInfoActivityNGO.this))
                        textAsk.setTextSize(23);
                    else
                        textAsk.setTextSize(17);
                    //  alertDialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                    yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.cancel();
                            String register = SharedPreferencesHelper.getNumber(DetailsInfoActivityNGO.this);
                            phone_num = register;

                            if (register.equals("")) {
                                requestToRegister();
                            } else {

                                feedBackAlert();
                                //  sendReviewToServer();
                            }

                        }
                    });
                    no.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.cancel();
                        }
                    });
                    //   setup a dialog window
                    alertDialog.setCancelable(false);
                    alertDialog.show();
                }


            }
        });


        right_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ngoServiceProviderItemNew.getNode_email().equals("null") || ngoServiceProviderItemNew.getNode_email().equals("")) {
                    AlertMessage.showMessage(con, "ই মেইল করা সম্ভব হচ্ছে না",
                            "ই মেইল আই ডি পাওয়া যায়নি");
                } else {
                    Helpes.sendEmail(DetailsInfoActivityNGO.this, ngoServiceProviderItemNew.getNode_email());
                }
            }
        });
//

        middle_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent1 = new Intent(Intent.ACTION_CALL);
                if (!ngoServiceProviderItemNew.getNode_contact().equals("null")) {
                    callIntent1.setData(Uri.parse("tel:" + ngoServiceProviderItemNew.getNode_contact()));
                    if (checkPermission())
                        startActivity(callIntent1);
                    else {
                        AlertMessage.showMessage(con, "ফোনে কল দেয়া সম্ভব হচ্ছে না",
                                "ফোন নম্বর পাওয়া যায়নি");

                    }
                } else {

                    AlertMessage.showMessage(con, "ফোনে কল দেয়া সম্ভব হচ্ছে না",
                            "ফোন নম্বর পাওয়া যায়নি");

                }
            }
        });

        distance_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AppUtils.isNetConnected(getApplicationContext()) && AppUtils.displayGpsStatus(getApplicationContext())) {


                    String lat = ngoServiceProviderItemNew.getLat().toString();
                    // double latitude = Double.parseDouble(lat);
                    String lon = ngoServiceProviderItemNew.getLon().toString();
                    // double longitude = Double.parseDouble(lon);
                    String name = ngoServiceProviderItemNew.getNamebn().toString();
                    String node = String.valueOf(ngoServiceProviderItemNew.getNgoid());
                    boolean fromornot = true;
                    SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("Latitude", lat);
                    editor.putString("Longitude", lon);
                    editor.putString("Name", name);
                    editor.putBoolean("Value", fromornot);
                    editor.putString("nValue", node);

                    editor.commit();


                    String Longitude = pref.getString("Longitude", null);
                    String Latitude = pref.getString("Latitude", null);

                    if (Latitude != null && Longitude != null) {
                        Double Lon = Double.parseDouble(Longitude);
                        Double Lat = Double.parseDouble(Latitude);
                        // implementFragment();
                        //username and password are present, do your stuff
                    }


                    Intent intentJ = new Intent(DetailsInfoActivityNGO.this, MapFragmentRouteOSM.class);
                    startActivity(intentJ);

                } else if (!AppUtils.displayGpsStatus(getApplicationContext())) {

                    AppUtils.showMessage(con, "জিপিএস বন্ধ করা রয়েছে!",
                            "আপনি কি আপনার মোবাইলের জিপিএস টি চালু করতে চান?");

                } else {

                    AlertMessage.showMessage(con, "দুঃখিত আপনার ইন্টারনেট সংযোগটি সচল নয়।",
                            "দিকনির্দেশনা দেখতে চাইলে অনুগ্রহপূর্বক ইন্টারনেট সংযোগটি চালু করুন।  ");


                }


            }
        });
    }


    public void setRatingBar() {
        try {
            ratingBar.setRating(Float.parseFloat(ngoServiceProviderItemNew.getRatings()));
        } catch (Exception e) {

        }
    }

    public void verifyRegistration(View v) {

        String register = SharedPreferencesHelper.getNumber(DetailsInfoActivityNGO.this);
        phone_num = register;

        if (register.equals("")) {
            requestToRegister();
        } else {

            feedBackAlert();
            //  sendReviewToServer();
        }


    }

    public void feedBackAlert() {

        LayoutInflater layoutInflater = LayoutInflater.from(DetailsInfoActivityNGO.this);
        final View promptView = layoutInflater.inflate(R.layout.give_feedback_dialogue, null);
        final Dialog alertDialog = new Dialog(DetailsInfoActivityNGO.this);
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setContentView(promptView);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();


        final Button submit = (Button) promptView.findViewById(R.id.submit);
        final Button close = (Button) promptView.findViewById(R.id.btnclose);


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedback_comment = (EditText) promptView.findViewById(R.id.feedback_comment);
                feedRadio = (RadioGroup) promptView.findViewById(R.id.feedRadio);
                int selected = feedRadio.getCheckedRadioButtonId();
                rb1 = (RadioButton) promptView.findViewById(selected);
                status = rb1.getText().toString();
                //  declareRadiobutton();
                if (AppUtils.isNetConnected(getApplicationContext())) {
                    sendReviewToServer();
                    alertDialog.cancel();
                } else {
                    ToastMessageDisplay.setText(DetailsInfoActivityNGO.this, "দয়া করে ইন্টারনেট চালু করুন।");
//                    Toast.makeText(this, "আপনার ফোনে ইন্টারনেট সংযোগ নেই। অনুগ্রহপূর্বক ইন্টারনেট সংযোগটি চালু করুন। ...",
//                            Toast.LENGTH_LONG).show();
                    ToastMessageDisplay.showText(DetailsInfoActivityNGO.this);
                }

            }
        });
        alertDialog.setCancelable(false);


        alertDialog.show();
    }


    private void breakTimeProcessing(String value1, String value2) {
        if (!value2.equals("null") || !value2.equals(", ")) {
            String timeInBengali = "";

            try {
                value2 = value2 + ",";

                String[] breakTIme = value2.split(",");


                String[] realTIme = breakTIme[0].split("-");


                value2 = timeConverter(realTIme[0]) + " থেকে " + timeConverter(realTIme[1]);
                CheckConcate(value1, value2);
            } catch (Exception e) {

            }
        }
    }

    public void sendReviewToServer() {
        int rating;
        if (status.equals(getString(R.string.feedback1)))
            rating = 1;
        else if (status.equals(getString(R.string.feedback2)))
            rating = 2;
        else if (status.equals(getString(R.string.feedback3)))
            rating = 3;
        else if (status.equals(getString(R.string.feedback4)))
            rating = 4;
        else
            rating = 5;


        String comment = "", comment2 = "";
        comment = feedback_comment.getText().toString().trim();
        String uname2 = SharedPreferencesHelper.getUname(DetailsInfoActivityNGO.this);
        uname = uname2.replace(' ', '+');
        ;
        try {
            comment2 = URLEncoder.encode(comment.replace(" ", "%20"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = "http://kolorob.net/kolorob-new-demo/api/sp_rating/" + ngoServiceProviderItemNew.getNgoid() + "?" + "phone=" + phone_num + "&name=" + uname + "&review=" + comment2 + "&rating=" + rating + "&username=" + username + "&password=" + password + "";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("========", "status " + response);
                        try {


                            if (response.equals("true")) {
                                SharedPreferencesHelper.setifcommentedalready(DetailsInfoActivityNGO.this, String.valueOf(ngoServiceProviderItemNew.getNgoid()), uname, "yes");

                                AlertMessage.showMessage(DetailsInfoActivityNGO.this, "মতামতটি গ্রহন করা হয়েছে",
                                        "মতামত প্রদান করার জন্য আপনাকে ধন্যবাদ ");
                            } else
                                AlertMessage.showMessage(DetailsInfoActivityNGO.this, "মতামতটি গ্রহন করা হয় নি",
                                        "অনুগ্রহ পূর্বক পুনরায় চেস্টা করুন।");


                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DetailsInfoActivityNGO.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();

                return params;
            }

        };


        RequestQueue requestQueue = Volley.newRequestQueue(DetailsInfoActivityNGO.this);
        requestQueue.add(stringRequest);
    }


    public void requestToRegister() {
        LayoutInflater layoutInflater = LayoutInflater.from(DetailsInfoActivityNGO.this);
        View promptView = layoutInflater.inflate(R.layout.verify_reg_dialog, null);


        final Dialog alertDialog = new Dialog(DetailsInfoActivityNGO.this);
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setContentView(promptView);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();


        final ImageView yes = (ImageView) promptView.findViewById(R.id.yes);
        final ImageView no = (ImageView) promptView.findViewById(R.id.no);
        final TextView textAsk = (TextView) promptView.findViewById(R.id.textAsk);
        String text = "  মতামত দেয়ার আগে আপনাকে" + "\n" + "       রেজিস্ট্রেশন করতে হবে" + "\n" + "আপনি কি রেজিস্ট্রেশন করতে চান?";
        textAsk.setText(text);
        if (SharedPreferencesHelper.isTabletDevice(DetailsInfoActivityNGO.this))
            textAsk.setTextSize(23);
        else
            textAsk.setTextSize(17);
        alertDialog.getWindow().setLayout((width * 5) / 6, WindowManager.LayoutParams.WRAP_CONTENT);


        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentPhoneRegistration = new Intent(DetailsInfoActivityNGO.this, PhoneRegActivity.class);
                alertDialog.cancel();
                startActivity(intentPhoneRegistration);

            }
        });


        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();

            }
        });
        //   setup a dialog window
        alertDialog.setCancelable(false);


        alertDialog.show();
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        if (result == PackageManager.PERMISSION_GRANTED) {

            return true;

        } else {

            return false;

        }
    }

    private String English_to_bengali_number_conversion(String english_number) {
        if (english_number.equals("null") || english_number.equals(""))
            return english_number;
        int v = english_number.length();
        String concatResult = "";
        for (int i = 0; i < v; i++) {
            if (english_number.charAt(i) == '1')
                concatResult = concatResult + "১";
            else if (english_number.charAt(i) == '2')
                concatResult = concatResult + "২";
            else if (english_number.charAt(i) == '3')
                concatResult = concatResult + "৩";
            else if (english_number.charAt(i) == '4')
                concatResult = concatResult + "৪";
            else if (english_number.charAt(i) == '5')
                concatResult = concatResult + "৫";
            else if (english_number.charAt(i) == '6')
                concatResult = concatResult + "৬";
            else if (english_number.charAt(i) == '7')
                concatResult = concatResult + "৭";
            else if (english_number.charAt(i) == '8')
                concatResult = concatResult + "৮";
            else if (english_number.charAt(i) == '9')
                concatResult = concatResult + "৯";
            else if (english_number.charAt(i) == '0')
                concatResult = concatResult + "০";
            else if (english_number.charAt(i) == '.')
                concatResult = concatResult + ".";
            else if (english_number.charAt(i) == '/')
                concatResult = concatResult + "/";
            else if (english_number.charAt(i) == '-')
                concatResult = concatResult + "-";
            else {
                return english_number;
            }

        }
        return concatResult;
    }

    private String timeConverter(String time) {


        String timeInBengali = "";

        try {

            String[] separated = time.split(":");


            int hour = Integer.valueOf(separated[0]);
            int times = Integer.valueOf(separated[1]);
            if (hour == 0 && times == 0)
                timeInBengali = "রাত ১২";
            else if (hour >= 6 && hour < 12)
                timeInBengali = "সকাল " + English_to_bengali_number_conversion(String.valueOf(hour));
            else if (hour == 12)
                timeInBengali = "দুপুর  " + English_to_bengali_number_conversion(String.valueOf(hour));
            else if (hour > 12 && hour < 16)
                timeInBengali = "দুপুর  " + English_to_bengali_number_conversion(String.valueOf(hour - 12));
            else if (hour > 15 && hour < 18)
                timeInBengali = "বিকেল " + English_to_bengali_number_conversion(String.valueOf(hour - 12));
            else if (hour > 17 && hour < 20)
                timeInBengali = "সন্ধ্যা " + English_to_bengali_number_conversion(String.valueOf(hour - 12));
            else if (hour > 20 && hour < 24)
                timeInBengali = "রাত " + English_to_bengali_number_conversion(String.valueOf(hour - 12));
            if (times != 0)
                timeInBengali = timeInBengali + " টা " + English_to_bengali_number_conversion(String.valueOf(times)) + " মিনিট";
            else
                timeInBengali = timeInBengali + " ";
        } catch (Exception e) {

        }

        return timeInBengali;

    }





    private void timeProcessing(String value1, String value2) {
        if (!value2.equals("null") || value2.equals("")) {

            String GetTime = timeConverter(value2);
            CheckConcate(value1, GetTime);

        }
    }

    private void CheckConcate(String value1, String value2) {


        Log.e("","Value1: "+value1+" Value2: "+value2);
        if (!value2.equals("null") && !value2.equals("") && !value2.equals(" টাকা") && !value2.equals(" ০")) {
            key[increment] = value1;
            value[increment] = value2;
            increment++;

        }


    }
}