package lamvc.db;

import java.util.HashMap;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by IntelliJ IDEA.
 * User: Norton
 * Date: 4/22/11
 * Time: 11:13 PM
 */
public class DB {
  protected static String _databaseName;
  protected static int _databaseVersion;

  private static HashMap<String, DBTable> _tableMap;

  private static DB _db;

  private DB(Context context) {
     CreateDB create = new CreateDB(context);

     SQLiteDatabase sqlLiteDb = create.getWritableDatabase();

    _databaseName = "Sqlite";
    _databaseVersion = 1;

    _tableMap = new HashMap<String, DBTable>();
  }

  public static DB getInstance(Context context, String databaseName, int databaseVersion) {
    if (null == _db) {
      _db = new DB(context);
    }

    _databaseName = databaseName;
    _databaseVersion = databaseVersion;

    return _db;
  }

  public DBTable getTable(String tableName) {
    return _tableMap.get(tableName);
  }

  public void addTable(String tableName, DBTable table) {
    _tableMap.put(tableName, table);
  }

  class CreateDB extends SQLiteOpenHelper {

    CreateDB(Context context) {
      super(context, _databaseName, null, _databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      for (String key : _tableMap.keySet()) {
        _tableMap.get(key).create();
      }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
      // Changes that need to be made to the db on an upgrade
    }
  }
}
