package com.example.policia.segundo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by policia on 14/11/2017.
 */

public class AlunoDAO extends SQLiteOpenHelper {

    private static final String DATABASE = "Cadastro";
    private static int VERSAO1=1;
    private static int VERSAO2=2;

    //private String ddl2;

    public AlunoDAO(Context context){
        super(context,DATABASE, null,VERSAO1);
    }

    public void salva(Aluno aluno){
        ContentValues values = new ContentValues();

        values.put("nome",aluno.getNome());
        values.put("site",aluno.getSite());
        values.put("endereco",aluno.getEndereco());
        values.put("nota",aluno.getNota());
        values.put("foto",aluno.getFoto());
        values.put("telefone",aluno.getTelefone());
        
        getWritableDatabase().insert("Alunos",null,values);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String ddl = "Create table Alunos(id primary key ," +
                "nome text unique not null,telefone text,endereco text,site text," +
                "foto text,nota real);";

        String ddl2 = "Create table Alunos(id primary key AUTOINCREMENT," +
                "nome text unique not null,site text,endereco text,nota text," +
                "foto text,nota real);";

        db.execSQL(ddl2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String ddl = "drop table if exists Alunos";
        db.execSQL(ddl);

       this.onCreate(db);






    }

    public List<Aluno> getLista(){
        String[] colunas = {"id","nome","site","telefone","endereco","foto","nota"};
        Cursor cursor = getWritableDatabase().query("Alunos",colunas,null,null,null,null,null);

        ArrayList<Aluno>  alunos = new ArrayList<Aluno>();

        while(cursor.moveToNext()){
            Aluno aluno = new Aluno();

            aluno.setId(cursor.getLong(0));
            aluno.setNome(cursor.getString(1));
            aluno.setSite(cursor.getString(2));
            aluno.setTelefone(cursor.getString(3));
            aluno.setEndereco(cursor.getString(4));
            aluno.setFoto(cursor.getString(5));
            aluno.setNota(cursor.getDouble(6));

            alunos.add(aluno);
        }
        return alunos;
    }

    public void deletar(Aluno aluno){
        String  []args = {aluno.getNome()};
        getWritableDatabase().delete("Alunos","nome=?",args);


    }

    public void altera(Aluno aluno){

        ContentValues values = new ContentValues();

        values.put("nome",aluno.getNome());
        values.put("site",aluno.getSite());
        values.put("endereco",aluno.getEndereco());
        values.put("nota",aluno.getNota());
        values.put("foto",aluno.getFoto());
        values.put("telefone",aluno.getTelefone());

        String  []args = {aluno.getNome().toString()};

        getWritableDatabase().update("Alunos",values,"nome=?",args);

    }
}
