package demo.kolorob.kolorobdemoversion.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.adapters.HealthDetailsAdapter;
import demo.kolorob.kolorobdemoversion.adapters.HealthSpecialistAdapter;
import demo.kolorob.kolorobdemoversion.adapters.HealthVaccineAdapter;
import demo.kolorob.kolorobdemoversion.database.Health.HealthPharmacyTable;
import demo.kolorob.kolorobdemoversion.database.Health.HealthSpecialistTable;
import demo.kolorob.kolorobdemoversion.database.Health.HealthVaccinesTable;
import demo.kolorob.kolorobdemoversion.helpers.Helpes;
import demo.kolorob.kolorobdemoversion.model.Health.HealthPharmacyItem;
import demo.kolorob.kolorobdemoversion.model.Health.HealthServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Health.HealthSpecialistItem;
import demo.kolorob.kolorobdemoversion.model.Health.HealthVaccinesItem;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;

public class DetailsInfoActivityHealthNew extends Activity {
    Dialog dialog;
    LinearLayout upperHand,upperText,left_way,middle_phone,right_email,bottom_bar,linearLayout;
    ImageView left_image,middle_image,right_image;
    TextView address_text,phone_text,email_text,itemopeningTime;
    int width,height;
    TextView ups_text;
    private ImageView close_button;
    ListView navlist,navlist1,navlist2;
    String TAG= "nothing";
    HealthServiceProviderItem healthServiceProviderItem;
    HealthPharmacyItem healthPharmacyItem;
    HealthVaccinesItem healthVaccinesItem;
    ArrayList<HealthVaccinesItem> healthVaccinesItemArrayList;
    ArrayList<HealthSpecialistItem> healthSpecialistItems;
    ArrayList<HealthPharmacyItem> healthPharmacyItems;
    private LinearLayout ll1;
    private LinearLayout ll2;
    private LinearLayout ll3,scrollingPart;
    private int k;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_info_activity_health_new);

        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        height= displayMetrics.heightPixels;
        width=displayMetrics.widthPixels;
        Intent intent = getIntent();


        if (null != intent)
        {
            healthServiceProviderItem = (HealthServiceProviderItem)intent.getSerializableExtra(AppConstants.KEY_DETAILS_HEALTH);

        }




        linearLayout=(LinearLayout)findViewById(R.id.lll);
        upperHand=(LinearLayout)findViewById(R.id.upper_part);
        upperText=(LinearLayout)findViewById(R.id.upperText);
        left_way=(LinearLayout)findViewById(R.id.left_go_process);
        middle_phone=(LinearLayout)findViewById(R.id.middle_phone);
        right_email=(LinearLayout)findViewById(R.id.right_email);
        left_image=(ImageView)findViewById(R.id.distance_left);
        bottom_bar=(LinearLayout)findViewById(R.id.bottom_bar);
        middle_image=(ImageView)findViewById(R.id.phone_middl);
        right_image=(ImageView)findViewById(R.id.right_side_email);
        address_text=(TextView)findViewById(R.id.address_text);
        phone_text=(TextView)findViewById(R.id.phone_text);
        email_text=(TextView)findViewById(R.id.email_text);
        close_button=(ImageView)findViewById(R.id.close_button);
        navlist = (ListView) findViewById(R.id.listView2);
        navlist1 = (ListView) findViewById(R.id.listView3);
        navlist2 = (ListView) findViewById(R.id.listView4);
        ll1=(LinearLayout)findViewById(R.id.second_list);
        ll2=(LinearLayout)findViewById(R.id.third_list);
        ll3=(LinearLayout)findViewById(R.id.fourth_list);
        scrollingPart=(LinearLayout)findViewById(R.id.scrollingPart);
        itemopeningTime=(TextView)findViewById(R.id.opening_time);






        LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) upperHand.getLayoutParams();
        int upperhad_height=params2.height = height/6;
        upperHand.setLayoutParams(params2);

        LinearLayout.LayoutParams params_upperText = (LinearLayout.LayoutParams) upperText.getLayoutParams();
        int  vd=params_upperText.height = height/24;
        params_upperText.width = width;
        upperText.setLayoutParams(params_upperText);

        LinearLayout.LayoutParams params_left_way = (LinearLayout.LayoutParams) left_way.getLayoutParams();
        int lett_img=params_left_way.height = (height*3)/24;
        int right_img=params_left_way.width = width/3;
        left_way.setLayoutParams(params_left_way);


        left_image.getLayoutParams().height= (lett_img*2)/3;
        left_image.getLayoutParams().width=right_img/2;


        LinearLayout.LayoutParams params_middle_phone = (LinearLayout.LayoutParams) middle_phone.getLayoutParams();
        int  vx=params_middle_phone.height = (height*3)/24;
        params_middle_phone.width = width/3;
        middle_phone.setLayoutParams(params_middle_phone);

        middle_image.getLayoutParams().height= (lett_img*2)/3;
        middle_image.getLayoutParams().width=right_img/2;

        right_image.getLayoutParams().height= (lett_img*2)/3;
        right_image.getLayoutParams().width=right_img/2;

        LinearLayout.LayoutParams params_right_email = (LinearLayout.LayoutParams) right_email.getLayoutParams();
        int  vc=params_right_email.height = (height*3)/24;
        params_right_email.width = width/3;
        right_email.setLayoutParams(params_right_email);

        ups_text=(TextView)findViewById(R.id.ups_text);
        ups_text.setTextSize(width/25);
        ups_text.setText(healthServiceProviderItem.getNameBn());
        phone_text.setText(healthServiceProviderItem.getNodeContact());
        email_text.setText(healthServiceProviderItem.getNodeEmail());


        RelativeLayout.LayoutParams params_bottom_bar = (RelativeLayout.LayoutParams) bottom_bar.getLayoutParams();
        int  vcc=params_bottom_bar.height = height/13;
        params_bottom_bar.width = width;
        bottom_bar.setLayoutParams(params_bottom_bar);

        LinearLayout.LayoutParams expnlist = (LinearLayout.LayoutParams) scrollingPart.getLayoutParams();

        expnlist.setMargins(0,0,0,vcc);


        HealthSpecialistTable healthSpecialistTable =new HealthSpecialistTable(DetailsInfoActivityHealthNew.this);
        HealthPharmacyTable healthPharmacyTable1 =new HealthPharmacyTable(DetailsInfoActivityHealthNew.this);




        HealthVaccinesTable healthVaccinesTable=new HealthVaccinesTable(DetailsInfoActivityHealthNew.this);
        healthVaccinesItemArrayList=healthVaccinesTable.getVaccinesforNode(healthServiceProviderItem.getNodeId());
        healthPharmacyItems=healthPharmacyTable1.getPharmacyforNode(healthServiceProviderItem.getNodeId());

        healthSpecialistItems=healthSpecialistTable.getSpecialistforNode(healthServiceProviderItem.getNodeId());


        if(healthPharmacyItems!=null) {
            String lat="";
            int k=0;
            int f= healthPharmacyItems.size();

            String[] doc_id_list=new String[f];
            String[] Phermacy_doc_list=new String[f];
            String[] doc_fee_list=new String[f];
            String[] pharmacy_time_list=new String[f];
            String[] pharmacy_no_degree_list=new String[f];
            String[] Pharmacy_lmaf_list=new String[f];
            String[] Pharmacy_mbbs_list=new String[f];
            String[] pharmacy_speciallist_list=new String[f];
            String[] pharmacy_remarks=new String[f];
            String[] pharmacy_docremarks=new String[f];
            for (HealthPharmacyItem et : healthPharmacyItems) {
                ll1.setVisibility(View.VISIBLE);


                int docId=et.getDocId();
                String docString = String.valueOf(docId);




                doc_id_list[k]=(docString);
                Phermacy_doc_list[k]=et.getPharmacyDoctorName();
                doc_fee_list[k]=et.getPharmacyFee();
                pharmacy_time_list[k]= et.getPharmacyTime();
                pharmacy_no_degree_list[k]=et.getPharmacyNoDegree();
                Pharmacy_lmaf_list[k]=et.getPharmacyLMAF();
                Pharmacy_mbbs_list[k]=et.getPharmacyMBBS();
                pharmacy_speciallist_list[k]= et.getPharmacySpecialist();
                pharmacy_remarks[k]=et.getRemarks();
                pharmacy_docremarks[k]=et.getpharmacyDocRemarks();
                //  lat = lat+"\n"+ " Node_id: "+et.getNodeId()+"\n Doctor_id: "+ et.getDocId() + "\nPhermacy Fee:" + et.getPharmacyFee() + "\n Doctor Name: " +et.getPharmacyDoctorName()+"\n";
                // phermacy.setText("Doc id"+et.getDocId()+"Pharmacy Fee"+et.getPharmacyFee()+"Doctor_name"+et.getPharmacyDoctorName());
                k++;
            }
            HealthDetailsAdapter adapter=new HealthDetailsAdapter(this,doc_id_list,Phermacy_doc_list,doc_fee_list,
                    pharmacy_time_list,pharmacy_no_degree_list,Pharmacy_lmaf_list,
                    Pharmacy_mbbs_list,pharmacy_speciallist_list,pharmacy_remarks,pharmacy_docremarks );

            navlist.setAdapter(adapter);

            Helpes.getListViewSize(navlist);



            // phermacy.setText(lat);

        }


        if(healthVaccinesItemArrayList!=null) {

            int g= healthVaccinesItemArrayList.size();
            String[] vaccine_name=new String[g];
            String[] vaccine_fee=new String[g];
            String[] vaccine_remark=new String[g];
            k=0;

            for (HealthVaccinesItem et : healthVaccinesItemArrayList) {

                vaccine_name[k]=et.getVaccinename();
                vaccine_fee[k]=et.getVaccinefee();
                vaccine_remark[k]=et.getVaccineremarks();
                ll2.setVisibility(View.VISIBLE);


                k++;
            }


            HealthVaccineAdapter adapter=new HealthVaccineAdapter(this,vaccine_name,vaccine_fee,vaccine_remark );
            navlist1.setAdapter(adapter);
            Helpes.getListViewSize(navlist1);
        }


        if(healthSpecialistItems!=null) {

            int g= healthSpecialistItems.size();
            String[] specialist_name=new String[g];
            String[] specialist_fee=new String[g];
            String[] remarks=new String[g];


            k=0;
            for (HealthSpecialistItem et : healthSpecialistItems) {

                specialist_name[k]=et.getSpecialisttype();
                specialist_fee[k]=et.getSpecialistfees();
                remarks[k]=et.getSpecialistremarks();
                k++;

                ll3.setVisibility(View.VISIBLE);
            }


            HealthSpecialistAdapter adapter=new HealthSpecialistAdapter(this,specialist_name,specialist_fee,remarks );
            navlist2.setAdapter(adapter);
            Helpes.getListViewSize(navlist2);


        }




    }
}