package cu.xkoders.presentationcard.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import cu.xkoders.presentationcard.datasource.Script;
import cu.xkoders.presentationcard.models.ContactoBK;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "bitKard.db";
    private static final int DB_VERSION = 1;



    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Script.SCRIPT_CREAR_TABLA);
        int length = Script.SCRIPT_INSERT_CREATE.length;
        for (int i = 0; i < length; i++) {
            String sql = Script.SCRIPT_INSERT_CREATE[i];
            db.execSQL(sql);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertCall(ContactoBK contactoBK) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues mContentValues = new ContentValues();
        try {
            /*mContentValues.put(PHONE_NUMBER, contactoBK.getPhone_list()[0]);
            mContentValues.put(NAME, contactoBK.getNombre());
            db.insert(TABLE_NAME, null, mContentValues);*/
            return true;
        } catch (Exception e) {
            return false;
        }
    }

  /*  public ArrayList<ContactoBK> getAllContacts() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<ContactoBK> mContacts = new ArrayList<ContactoBK>();

        try {
            Cursor c = db.rawQuery("select * from " + TABLE_NAME, null);
            c.moveToFirst();
            while (c.isAfterLast() == false) {
                ContactoBK contactoBK = new ContactoBK();
                //contactoBK.setPhone_list(c.getString(c.getColumnIndex("phoneNumber")));
                contactoBK.setNombre(c.getString(c.getColumnIndex("name")));

                mContacts.add(contactoBK);
                c.moveToNext();
            }
            return mContacts;
        } catch (Exception e) {
            return null;
        }

    }*/

}
