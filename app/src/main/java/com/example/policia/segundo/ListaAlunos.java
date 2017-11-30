package com.example.policia.segundo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by policia on 30/10/2017.
 */

public class ListaAlunos extends Activity {

    private ListView lista;
    private Aluno aluno;

    private int idd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listagem_alunos);

        lista = (ListView) findViewById(R.id.lista);

        registerForContextMenu(lista);


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {

                Aluno alunoClicado = (Aluno) adapter.getItemAtPosition(position);

              //  Toast.makeText(ListaAlunos.this,"aluno :" + alunoClicado,Toast.LENGTH_SHORT).show();
                Intent irParaFormulario = new Intent(ListaAlunos.this,Formulario.class);
                irParaFormulario.putExtra("alunoSelecionado",alunoClicado);

                startActivity(irParaFormulario);


                //aluno.getNome();

               // Toast.makeText(ListaAlunos.this," : "+aluno.getId(),Toast.LENGTH_SHORT).show();
            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {

                aluno = (Aluno) adapter.getItemAtPosition(position);
                idd = position;
                return false;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
      //  menu.add("Ligar ");
      //  menu.add("Enviar sms");
        MenuItem deletar =  menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                AlunoDAO dao = new AlunoDAO(ListaAlunos.this);

                dao.deletar(aluno);

                dao.close();
                Toast.makeText(ListaAlunos.this," : "+aluno,Toast.LENGTH_SHORT).show();

                carregaLista();
                return false;
            }
        });
       // menu.add("Ver no Mapa");

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    protected void onResume() {
        super.onResume();

        carregaLista();


    }

    private void carregaLista(){
        AlunoDAO dao  = new AlunoDAO(this);
        List<Aluno> alunos = dao.getLista();
        dao.close();

        int layout = android.R.layout.simple_list_item_1;

        ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this,layout,alunos);

        lista.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_app,menu);


        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemClicado  = item.getItemId();

        switch(itemClicado){
            case R.id.novo:
                Intent irParaFormulario = new Intent(this,Formulario.class);
                startActivity(irParaFormulario);
                break;

            default:
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
