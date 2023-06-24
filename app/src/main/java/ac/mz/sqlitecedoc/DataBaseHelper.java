package ac.mz.sqlitecedoc;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DatabaseHelper extends SQLiteOpenHelper {
    public  static final String DATABASE_NAME = "livros.db";
    public  static final String TABLE_NAME = "livros";
    public  static final String COL_ID = "id";
    public static final String COL_TITULO = "titulo";
    public  static final String COL_ANO = "ano";
    public  static final String COL_AUTOR = "autor";
    public  static final String COL_CURSO = "curso";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + "(" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_TITULO + " TEXT, " +
                COL_ANO + " INTEGER, " +
                COL_AUTOR + " TEXT, " +
                COL_CURSO + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean inserirLivro(String titulo, int ano, String autor, String curso) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_TITULO, titulo);
        contentValues.put(COL_ANO, ano);
        contentValues.put(COL_AUTOR, autor);
        contentValues.put(COL_CURSO, curso);

        long resultado = db.insert(TABLE_NAME, null, contentValues);
        return resultado != -1;
    }

    public Cursor obterTodosLivros() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    public boolean excluirLivro(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int resultado = db.delete(TABLE_NAME, COL_ID + "=?", new String[]{String.valueOf(id)});
        return resultado > 0;

    }

}
