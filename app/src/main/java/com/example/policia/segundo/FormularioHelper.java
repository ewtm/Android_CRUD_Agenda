package com.example.policia.segundo;

import android.widget.EditText;
import android.widget.RatingBar;

/**
 * Created by policia on 14/11/2017.
 */

public class FormularioHelper {

    private EditText editNome;
    private EditText editSite;
    private EditText editTelefone;
    private EditText editEndereco;
    private RatingBar ratingNota;

    public FormularioHelper(Formulario formulario){

        editNome  = (EditText) formulario.findViewById(R.id.nome);
        editSite  = (EditText) formulario.findViewById(R.id.site);
        editTelefone  = (EditText) formulario.findViewById(R.id.telefone);
        editEndereco  = (EditText) formulario.findViewById(R.id.endereco);
        ratingNota = (RatingBar) formulario.findViewById(R.id.nota);
    }

    public Aluno pegaAlunoDoFormulario(){

        Aluno aluno = new Aluno();

        aluno.setNome(editNome.getText().toString());
        aluno.setSite(editSite.getText().toString());
        aluno.setEndereco(editEndereco.getText().toString());
        aluno.setTelefone(editTelefone.getText().toString());
        aluno.setNota(Double.valueOf(ratingNota.getRating()));

        return aluno;
    }

    public void colocaAlunoNoFormulario(Aluno  aluno){
        editNome.setText(aluno.getNome());
        editSite.setText(aluno.getSite());
        editEndereco.setText(aluno.getEndereco());
        editTelefone.setText(aluno.getTelefone());
        ratingNota.setRating(aluno.getNota().floatValue());
    }

}
