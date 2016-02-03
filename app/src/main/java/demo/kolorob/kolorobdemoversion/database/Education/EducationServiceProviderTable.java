package demo.kolorob.kolorobdemoversion.database.Education;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.database.DatabaseManager;
import demo.kolorob.kolorobdemoversion.model.Education.EducationServiceProviderItem;
import demo.kolorob.kolorobdemoversion.utils.Lg;



/**
 * Created by Yeakub Hassan Rafi on 27-Dec-15.
 */

public class EducationServiceProviderTable  {

    private static final String TAG = EducationServiceProviderTable.class.getSimpleName();
    private static final String TABLE_NAME = DatabaseHelper.EDU_PROVIDER_TABLE;

    private static final String KEY_IDENTIFIER_ID = "_identifier_id"; // 0 -integer
    private static final String KEY_SERVICE_PROVIDER_ID = "_service_provider_id"; // 1 - text
    private static final String KEY_EDU_SUBCATEGORY_ID  = "_edu_subcategory_id"; // 2 - text*/
    private static final String KEY_CATEGORY_ID = "_category_id";
    private static final String KEY_EDU_NAME_ENG = "_edu_name_eng"; //
    private static final String KEY_EDU_NAME_BANG = "_edu_name_bang"; //
    private static final String KEY_EDU_TYPE  = "_ed_type"; //
    private static final String KEY_HOSTEL_FACILITY = "_hostel_facility"; //
    private static final String KEY_TRANSPORT_FACILITY= "_transport_facility"; //
    private static final String KEY_PLAYGROUND = "_playground"; //
    private static final String KEY_CONTACT_PERSON_DESIGNATION = "_contact_person_designation"; //
    private static final String KEY_CONTACT_NO= "_contact_no"; //
    private static final String KEY_EMAIL_ADDRESS  = "_email_address"; //
    private static final String KEY_WEBSITE_LINK = "_website_link"; //
    private static final String KEY_FB_LINK = "_fb_link"; //
    private static final String KEY_REGISTERED_WITH  = "_registered_with"; //
    private static final String KEY_REGISTRATION_NO  = "_registration_no"; //
    private static final String KEY_TOTAL_STUDENTS = "_total_students"; //
    private static final String KEY_TOTAL_CLASSES = "_total_classes"; //
    private static final String KEY_TOTAL_TEACHERS = "_total_teachers"; //
    private static final String KEY_COURSE_PROVIDED  = "_course_provided"; //
    private static final String KEY_SHIFT = "_shift"; //
    private static final String KEY_CANTEEN_FACILITY = "_canteen_facility"; //
    private static final String KEY_ADDITIONAL_INFO = "_additional_info"; //
    private static final String KEY_AREA = "_area"; //
    private static final String KEY_ADDRESS = "_address"; //
    private static final String KEY_LATITUDE= "_latitude"; //
    private static final String KEY_LONGITUDE = "_longitude"; //


    private Context tContext;

    public EducationServiceProviderTable(Context context) {
        tContext = context;
        createTable();
    }

    private void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_IDENTIFIER_ID + "  INTEGER , " // 0 - int
                + KEY_SERVICE_PROVIDER_ID + "  INTEGER, "              // 1 - text
                + KEY_EDU_SUBCATEGORY_ID + " INTEGER, "
                + KEY_CATEGORY_ID + " INTEGER, "// 2 - text
                + KEY_EDU_NAME_ENG + " TEXT, "
                + KEY_EDU_NAME_BANG + " TEXT, "
                + KEY_EDU_TYPE + " TEXT, "
                + KEY_HOSTEL_FACILITY + " TEXT, "
                + KEY_TRANSPORT_FACILITY + " TEXT, "
                + KEY_PLAYGROUND + " TEXT, "
                + KEY_CONTACT_PERSON_DESIGNATION + " TEXT, "
                + KEY_CONTACT_NO + " TEXT, "
                + KEY_EMAIL_ADDRESS + " TEXT, "
                + KEY_WEBSITE_LINK+ " TEXT, "
                + KEY_FB_LINK + " TEXT, "
                + KEY_REGISTERED_WITH + " TEXT, "
                + KEY_REGISTRATION_NO  + " TEXT, "
                + KEY_TOTAL_STUDENTS + " TEXT, "
                + KEY_TOTAL_CLASSES + " TEXT, "
                + KEY_TOTAL_TEACHERS + " TEXT, "
                + KEY_COURSE_PROVIDED + " TEXT, "
                + KEY_SHIFT + " TEXT, "
                + KEY_CANTEEN_FACILITY + " TEXT, "
                + KEY_ADDITIONAL_INFO + " TEXT, "
                + KEY_AREA + " TEXT, "
                + KEY_ADDRESS + " TEXT, "
                + KEY_LATITUDE + " TEXT, "
                + KEY_LONGITUDE + " TEXT, PRIMARY KEY(" + KEY_CATEGORY_ID + ", " + KEY_EDU_SUBCATEGORY_ID + ", " + KEY_SERVICE_PROVIDER_ID + "))";
        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }

    private SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }

    private void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
    }
    public long insertItem(EducationServiceProviderItem educationServiceProviderItem){
        return insertItem(
                educationServiceProviderItem.getIdentifierId(),
                educationServiceProviderItem.getServiceProviderId(),
                educationServiceProviderItem.getEduSubCategoryId(),
                educationServiceProviderItem.getCategoryId(),
                educationServiceProviderItem.getEduNameEng(),
                educationServiceProviderItem.getEduNameBan(),
                educationServiceProviderItem.getEduType(),
                educationServiceProviderItem.getHostelFacility(),
                educationServiceProviderItem.getTransportFacility(),
                educationServiceProviderItem.getPlayground(),
                educationServiceProviderItem.getContactPersonDesignation(),
                educationServiceProviderItem.getContactNo(),
                educationServiceProviderItem.getEmailAddress(),
                educationServiceProviderItem.getWebsiteLink(),
                educationServiceProviderItem.getFbLink(),
                educationServiceProviderItem.getRegisteredWith(),
                educationServiceProviderItem.getRegistrationNo(),
                educationServiceProviderItem.getTotalStudents(),
                educationServiceProviderItem.getTotalClasses(),
                educationServiceProviderItem.getTotalTeachers(),
                educationServiceProviderItem.getCourseProvided(),
                educationServiceProviderItem.getShift(),
                educationServiceProviderItem.getCanteenFacility(),
                educationServiceProviderItem.getAdditionalInfo(),
                educationServiceProviderItem.getArea(),
                educationServiceProviderItem.getAddress(),
                educationServiceProviderItem.getLatitude(),
                educationServiceProviderItem.getLongitude()
        );
    }

    public long insertItem(String identifierId,
                           String serviceProviderId,
                           int eduSubCategoryId,
                           int categoryId,
                           String eduNameEng,
                           String eduNameBan,
                           String eduType,
                           String hostelFacility,
                           String transportFacility,
                           String playground,
                           String contactPersonDesignation,
                           String contactNo,
                           String emailAddress,
                           String websiteLink,
                           String fbLink,
                           String registeredWith,
                           String registrationNo,
                           String totalStudents,
                           String totalClasses,
                           String totalTeachers,
                           String courseProvided,
                           String shift,
                           String canteenFacility,
                           String additionalInfo,
                           String area,
                           String address,
                           String latitude,
                           String longitude) {
        if (isFieldExist(identifierId,categoryId,eduSubCategoryId)) {
            return updateItem(
                    identifierId,
                    serviceProviderId,
                    eduSubCategoryId,
                    categoryId,
                    eduNameEng,
                    eduNameBan,
                    eduType,
                    hostelFacility,
                    transportFacility,
                    playground,
                    contactPersonDesignation,
                    contactNo,
                    emailAddress,
                    websiteLink,
                    fbLink,
                    registeredWith,
                    registrationNo,
                    totalStudents,
                    totalClasses,
                    totalTeachers,
                    courseProvided,
                    shift,
                    canteenFacility,
                    additionalInfo,
                    area,
                    address,
                    latitude,
                    longitude);
        }
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_IDENTIFIER_ID , identifierId);
        rowValue.put(KEY_SERVICE_PROVIDER_ID , serviceProviderId);
        rowValue.put(KEY_EDU_SUBCATEGORY_ID  , eduSubCategoryId);
        rowValue.put(KEY_CATEGORY_ID,categoryId);
        rowValue.put(KEY_EDU_NAME_ENG , eduNameEng);
        rowValue.put(KEY_EDU_NAME_BANG ,eduNameBan );
        rowValue.put(KEY_EDU_TYPE  , eduType);
        rowValue.put(KEY_HOSTEL_FACILITY , hostelFacility);
        rowValue.put(KEY_TRANSPORT_FACILITY,transportFacility);
        rowValue.put(KEY_PLAYGROUND , playground);
        rowValue.put(KEY_CONTACT_PERSON_DESIGNATION ,contactPersonDesignation);
        rowValue.put(KEY_CONTACT_NO, contactNo);
        rowValue.put(KEY_EMAIL_ADDRESS  , emailAddress);
        rowValue.put(KEY_WEBSITE_LINK , websiteLink);
        rowValue.put(KEY_FB_LINK , fbLink);
        rowValue.put(KEY_REGISTERED_WITH  , registeredWith);
        rowValue.put(KEY_REGISTRATION_NO  , registrationNo);
        rowValue.put(KEY_TOTAL_STUDENTS , totalStudents);
        rowValue.put(KEY_TOTAL_CLASSES , totalClasses);
        rowValue.put(KEY_TOTAL_TEACHERS , totalTeachers);
        rowValue.put(KEY_COURSE_PROVIDED  , courseProvided);
        rowValue.put(KEY_SHIFT , shift);
        rowValue.put(KEY_CANTEEN_FACILITY , canteenFacility);
        rowValue.put(KEY_ADDITIONAL_INFO , additionalInfo);
        rowValue.put(KEY_AREA, area);
        rowValue.put(KEY_ADDRESS, address);
        rowValue.put(KEY_LATITUDE,latitude);
        rowValue.put(KEY_LONGITUDE,longitude);

        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);

        closeDB();
        return ret;
    }

    public boolean isFieldExist(String id,int cat_id,int sub_cat_id) {
        //Lg.d(TAG, "isFieldExist : inside, id=" + id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                if (id.equals(cursor.getString(0))&&Integer.parseInt(cursor.getString(3))==cat_id&&Integer.parseInt(cursor.getString(2))==sub_cat_id) {
                    cursor.close();
                    closeDB();
                    return true;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return false;
    }

    private long updateItem(String identifierId,
                            String serviceProviderId,
                            int eduSubCategoryId,
                            int categoryId,
                            String eduNameEng,
                            String eduNameBan,
                            String eduType,
                            String hostelFacility,
                            String transportFacility,
                            String playground,
                            String contactPersonDesignation,
                            String contactNo,
                            String emailAddress,
                            String websiteLink,
                            String fbLink,
                            String registeredWith,
                            String registrationNo,
                            String totalStudents,
                            String totalClasses,
                            String totalTeachers,
                            String courseProvided,
                            String shift,
                            String canteenFacility,
                            String additionalInfo,
                            String area,
                            String address,
                            String latitude,
                            String longitude) {
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_IDENTIFIER_ID , identifierId);
        rowValue.put(KEY_SERVICE_PROVIDER_ID , serviceProviderId);
        rowValue.put(KEY_EDU_SUBCATEGORY_ID  , eduSubCategoryId);
        rowValue.put(KEY_CATEGORY_ID,categoryId);
        rowValue.put(KEY_EDU_NAME_ENG , eduNameEng);
        rowValue.put(KEY_EDU_NAME_BANG ,eduNameBan );
        rowValue.put(KEY_EDU_TYPE  , eduType);
        rowValue.put(KEY_HOSTEL_FACILITY , hostelFacility);
        rowValue.put(KEY_TRANSPORT_FACILITY,transportFacility);
        rowValue.put(KEY_PLAYGROUND , playground);
        rowValue.put(KEY_CONTACT_PERSON_DESIGNATION ,contactPersonDesignation);
        rowValue.put(KEY_CONTACT_NO, contactNo);
        rowValue.put(KEY_EMAIL_ADDRESS  , emailAddress);
        rowValue.put(KEY_WEBSITE_LINK , websiteLink);
        rowValue.put(KEY_FB_LINK , fbLink);
        rowValue.put(KEY_REGISTERED_WITH  , registeredWith);
        rowValue.put(KEY_REGISTRATION_NO  , registrationNo);
        rowValue.put(KEY_TOTAL_STUDENTS , totalStudents);
        rowValue.put(KEY_TOTAL_CLASSES , totalClasses);
        rowValue.put(KEY_TOTAL_TEACHERS , totalTeachers);
        rowValue.put(KEY_COURSE_PROVIDED  , courseProvided);
        rowValue.put(KEY_SHIFT , shift);
        rowValue.put(KEY_CANTEEN_FACILITY , canteenFacility);
        rowValue.put(KEY_ADDITIONAL_INFO , additionalInfo);
        rowValue.put(KEY_AREA,area);
        rowValue.put(KEY_ADDRESS,address);
        rowValue.put(KEY_LATITUDE,latitude);
        rowValue.put(KEY_LONGITUDE,longitude);

        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_IDENTIFIER_ID + " = ? AND "+KEY_EDU_SUBCATEGORY_ID + " = ? AND "+KEY_CATEGORY_ID + " = ? ",
                new String[]{identifierId + "",eduSubCategoryId+"",categoryId+""});
        closeDB();
        return ret;
    }


    public ArrayList<EducationServiceProviderItem> getAllEducationSubCategoriesInfo(int cat_id,int sub_cat_id) {
        ArrayList<EducationServiceProviderItem> subCatList = new ArrayList<>();
        //System.out.println(cat_id+"  "+sub_cat_id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE "+KEY_CATEGORY_ID+"="+cat_id+" AND "+KEY_EDU_SUBCATEGORY_ID+"="+sub_cat_id, null);

        if (cursor.moveToFirst()) {
            do {
                //System.out.println("abc="+cursor.getString(4));
                subCatList.add(cursorToSubCatList(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return subCatList;
    }

    public ArrayList<EducationServiceProviderItem> getAllEducationSubCategoriesInfo(int cat_id) {
        ArrayList<EducationServiceProviderItem> subCatList = new ArrayList<>();

        //System.out.println(cat_id+"  "+sub_cat_id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE "+KEY_CATEGORY_ID+"="+cat_id, null);

        if (cursor.moveToFirst()) {
            do {


                //System.out.println("abc="+cursor.getString(4));
                subCatList.add(cursorToSubCatList(cursor));

            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return subCatList;
    }


    public ArrayList<EducationServiceProviderItem> Edunames(int cat_id,String head,String a,String place) {
        String subcatnames=null;
       subcatnames=a;

        ArrayList<EducationServiceProviderItem> nameslist=new ArrayList<>();

        SQLiteDatabase db = openDB();

            Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_CATEGORY_ID + "=" + cat_id
                    + " AND "+KEY_AREA+" = '"+place+"'"  + " AND "+ KEY_EDU_SUBCATEGORY_ID + "=" + "(SELECT _sub_cat_id from " + DatabaseHelper.SUB_CATEGORY + " WHERE _sub_cat_name = '"+subcatnames+"')", null);


            if (cursor.moveToFirst()) {
                do {


                    nameslist.add(cursorToSubCatList(cursor));

                } while (cursor.moveToNext());
            }
            cursor.close();
        closeDB();
        return  nameslist;
    }
    public ArrayList<EducationServiceProviderItem> getAllEducationSubCategoriesInfoWithHead(int cat_id,String header) {
        ArrayList<EducationServiceProviderItem> subCatList = new ArrayList<>();
        //System.out.println(cat_id+"  "+sub_cat_id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE "+KEY_CATEGORY_ID+"="+cat_id
                + " AND " +KEY_EDU_SUBCATEGORY_ID + " in (SELECT _sub_cat_id from "+ DatabaseHelper.SUB_CATEGORY + " WHERE _sub_cat_header = '"+header+"')", null);

        if (cursor.moveToFirst()) {
            do {
                //System.out.println("abc="+cursor.getString(4));
                subCatList.add(cursorToSubCatList(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return subCatList;
    }

    private EducationServiceProviderItem cursorToSubCatList(Cursor cursor) {
        String _identifierId=cursor.getString(0);
        String _serviceProviderId=cursor.getString(1);
        int _eduSubCategoryId=cursor.getInt(2);
        int _categoryId=cursor.getInt(3);
        String _eduNameEng=cursor.getString(4);
        String _eduNameBan=cursor.getString(5);
        String _eduType=cursor.getString(6);
        String _hostelFacility=cursor.getString(7);
        String _transportFacility=cursor.getString(8);
        String _playground=cursor.getString(9);
        String _contactPersonDesignation=cursor.getString(10);
        String _contactNo=cursor.getString(11);
        String _emailAddress=cursor.getString(12);
        String _websiteLink=cursor.getString(13);
        String _fbLink=cursor.getString(14);
        String _registeredWith=cursor.getString(15);
        String _registrationNo=cursor.getString(16);
        String _totalStudents=cursor.getString(17);
        String _totalClasses=cursor.getString(18);
        String _totalTeachers=cursor.getString(19);
        String _courseProvided=cursor.getString(20);
        String _shift=cursor.getString(21);
        String _canteenFacility=cursor.getString(22);
        String _additionalInfo=cursor.getString(23);
        String _area=cursor.getString(24);
        String _address=cursor.getString(25);
        String _latitude = cursor.getString(26);
        String _longitude = cursor.getString(27);

        return new EducationServiceProviderItem(
                _identifierId,
                _serviceProviderId,
                _eduSubCategoryId,
                _categoryId,
                _eduNameEng,
                _eduNameBan,
                _eduType,
                _hostelFacility,
                _transportFacility,
                _playground,
                _contactPersonDesignation,
                _contactNo,
                _emailAddress,
                _websiteLink,
                _fbLink,
                _registeredWith,
                _registrationNo,
                _totalStudents,
                _totalClasses,
                _totalTeachers,
                _courseProvided,
                _shift,
                _canteenFacility,
                _additionalInfo,
                _area,_address,
                _latitude,
                _longitude);
    }

    public void dropTable() {
        SQLiteDatabase db = openDB();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        createTable();
        Lg.d(TAG, "Table dropped and recreated.");
        closeDB();
    }

}
