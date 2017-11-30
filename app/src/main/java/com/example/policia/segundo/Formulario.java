package com.example.policia.segundo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

/**
 * Created by policia on 30/10/2017.
 */

public class Formulario extends Activity {

    private FormularioHelper helper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario);

        Intent intent = getIntent();
        final Aluno alunoParaSerAlterado = (Aluno) intent.getSerializableExtra("alunoSelecionado");
      //  Log.d("tag","aluno :" + alunoParaSerAlterado.getEndereco());
        helper = new FormularioHelper(this);

        Button  botao =  (Button) findViewById(R.id.botao);

        if(alunoParaSerAlterado != null){
            botao.setText("Alterar");
            helper.colocaAlunoNoFormulario(alunoParaSerAlterado);
        }

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Aluno aluno = helper.pegaAlunoDoFormulario();

                AlunoDAO dao = new AlunoDAO(Formulario.this);

                if(alunoParaSerAlterado == null){
                    dao.salva(aluno);
                }else{
                    aluno.setNome(alunoParaSerAlterado.getNome());
                    dao.altera(aluno);
                }

                dao.close();

                finish();

            }
        });
    }
}
