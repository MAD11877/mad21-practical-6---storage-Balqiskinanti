package sg.edu.np.mad.madpractical;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;


public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "userDB.db";
    public static final String TABLE_USER = "user";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_FOLLOWED = "followed";

    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER +
                "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME + " TEXT," +
                COLUMN_DESCRIPTION + " TEXT," +
                COLUMN_FOLLOWED + " INTEGER" +
                ")";
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    private int getRandomInt() {
        int randomInt = new Random().nextInt();
        return randomInt;
    }

    private boolean getRandomBool() {
        boolean randomBoolean = new Random().nextBoolean();
        return randomBoolean;
    }

    public void addUser() {
        ContentValues values = new ContentValues();
        for (int i = 0; i < 20; i++) {
            String randomName = "Name" + getRandomInt();
            String randomDesc = "Description:" + " " + Integer.toString(getRandomInt());
            boolean randomIsFoll = getRandomBool();

            values.put(COLUMN_NAME, randomName);
            values.put(COLUMN_DESCRIPTION, randomDesc);
            values.put(COLUMN_FOLLOWED, randomIsFoll);
            SQLiteDatabase db = this.getWritableDatabase();

            db.insert(TABLE_USER, null, values);
            db.close();
        }
    }

    public ArrayList<User> getUsers() {
        ArrayList<User> userList = new ArrayList<>();
        String USER_SELECT_QUERY = String.format("SELECT * FROM " + TABLE_USER);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(USER_SELECT_QUERY, null);
        if (cursor.moveToFirst()) {
            do {
                boolean isFollowed;
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                String desc = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
                Integer id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                Integer follow = cursor.getInt(cursor.getColumnIndex(COLUMN_FOLLOWED));
                if (follow == 1) {
                    isFollowed = true;
                }
                else {
                    isFollowed = false;
                }
                User user = new User(name, desc, id, isFollowed);
                userList.add(user);
            }
            while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return userList;
    }

    public int getRowCount(){
        int rowCount = 0;

        String USER_SELECT_COUNT_QUERRY = "SELECT COUNT(*) FROM " + TABLE_USER;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(USER_SELECT_COUNT_QUERRY,null);

        if(cursor.getCount()>0){
            cursor.moveToFirst();
            rowCount = cursor.getInt(0);
        }
        db.close();
        return rowCount;
    }

    public boolean updateUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        int follow;
        boolean isFollowed = user.isFollowed();
        if (isFollowed) {
            follow = 1;
        }
        else {
            follow = 0;
        }
        contentValues.put(COLUMN_FOLLOWED, follow);
        db.update(TABLE_USER, contentValues, COLUMN_ID + " = ? " ,
                    new String[]{Integer.toString(user.getId())}
                );

        return true;
    }

}
