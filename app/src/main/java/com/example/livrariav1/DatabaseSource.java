package com.example.livrariav1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseSource  extends SQLiteOpenHelper {

    private Context context;

    //Configuração do banco
    private static final String DATABASE_NAME = "livraria.db";
    private static final int DATABASE_VERSION = 1;

    //configuração da tabela
    private static final String TABLE_NAME = "livros";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITULO = "titulo";
    private static final String COLUMN_AUTOR = "autor";
    private static final String COLUMN_PAGINAS = "paginas";



    public DatabaseSource(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_TITULO + " TEXT, " +
                        COLUMN_AUTOR + " TEXT, " +
                        COLUMN_PAGINAS + " INTEGER);";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void adicionarLivro(Livro livro){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITULO, livro.getTitulo());
        cv.put(COLUMN_AUTOR, livro.getAutor());
        cv.put(COLUMN_PAGINAS, livro.getPaginas());

        long result = db.insert(TABLE_NAME, null, cv);

        if (result == -1){
            Toast.makeText(context, "Falha ao adicionar o registro", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Registro Adicionado com Sucesso", Toast.LENGTH_SHORT).show();
        }
    }
}
