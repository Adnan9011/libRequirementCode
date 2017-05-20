package ir.baselibrary.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DefaultDB extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "databasedefault";

	// Contacts table name
	private static final String TABLE_PREF = "de";

	// Contacts Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_KEY = "key";
	private static final String KEY_VALUE = "value";

	public DefaultDB(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public DefaultDB(Context context, String name,
                     CursorFactory factory, int version) {
		super(context, name, factory, version);

	}

	public void onCreate(SQLiteDatabase db) {
		String CREATE_PREF_TABLE = "CREATE TABLE " + TABLE_PREF + "(" + KEY_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_KEY + " TEXT"
				+ "," + KEY_VALUE + " TEXT " + ")";
		db.execSQL(CREATE_PREF_TABLE);
		// db.close();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE " + TABLE_PREF);
		onCreate(db);

	}

	public void addDB(String key, String value) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put(KEY_KEY, key);
		values.put(KEY_VALUE, value);

		db.insert(TABLE_PREF, null, values);
		db.close();
	}

	public String getDB(String key) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_PREF, new String[] { KEY_VALUE },
				KEY_KEY + "=?", new String[] { String.valueOf(key) }, null,
				null, null, null);
		if (cursor != null)
			cursor.moveToFirst();
		String s = cursor.getString(0);
		cursor.close();
		db.close();
		return s;
	}

	public void deletAllDB() {
		SQLiteDatabase db = getWritableDatabase();
		// db = this.getWritableDatabase();
		db.delete(TABLE_PREF, null, null);
		db.close();
	}

	// Updating single contact
	public int updateDB(String key, String value) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_VALUE, value);

		int result = db.update(TABLE_PREF, values, KEY_KEY + " = ?",
				new String[] { String.valueOf(key) });
		db.close();
		return result;
	}

	// Count
	public int getDBCount() {
		String countQuery = "SELECT * FROM " + TABLE_PREF;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		int count = cursor.getCount();

		cursor.close();
		db.close();
		return count;
	}
	
//	//Get All
//	public List<Alarm> getAllDB() {
//		List<Alarm> alarmList = new ArrayList<Alarm>();
//		String selectQuery = "SELECT * FROM " + TABLE_ALARM + " ORDER BY "
//+ KEY_TIME;
//		SQLiteDatabase db = this.getWritableDatabase();
//		Cursor cursor = db.rawQuery(selectQuery, null);
//		if (cursor.moveToFirst()) {
//			do {
//				Alarm alarm = new Alarm();
//
//				alarm.setId(Integer.parseInt(cursor.getString(0)));
//				alarm.setTime(Long.parseLong(cursor.getString(1)));
//				alarm.setName(cursor.getString(2));
//				alarm.setDescription(cursor.getString(3));
//				alarm.setRepeat(Integer.parseInt(cursor.getString(4)));
//				alarm.setBefore(Integer.parseInt(cursor.getString(5)));
//				alarm.setState(Boolean.parseBoolean(cursor.getString(6)));
//				alarm.setContact_name(cursor.getString(7));
//				alarm.setContact_number(cursor.getString(8));
//				alarm.setSms_deliver(cursor.getString(9));
//
//				alarmList.add(alarm);
//			} while (cursor.moveToNext());
//		}
//		//
//		cursor.close();
//		db.close();
//
//		return alarmList;
//	}

}
