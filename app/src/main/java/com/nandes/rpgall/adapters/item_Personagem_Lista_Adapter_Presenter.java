package com.nandes.rpgall.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;

import com.nandes.rpgall.R;
import com.nandes.rpgall.activities.ComentariosActivity;
import com.nandes.rpgall.activities.PersonagemActivity;
import com.nandes.rpgall.interfaces.Iitem_Personagem_Lista_Adapter_Presenter;
import com.nandes.rpgall.interfaces.Iitem_Personagem_Lista_Adapter_View;
import com.nandes.rpgall.modelDAOs.ClassesDAO;
import com.nandes.rpgall.modelDAOs.MesasDAO;
import com.nandes.rpgall.modelDAOs.PersonagensDAO;
import com.nandes.rpgall.modelDAOs.SituacaoDAO;
import com.nandes.rpgall.models.Personagens;

import java.util.List;

public class item_Personagem_Lista_Adapter_Presenter implements Iitem_Personagem_Lista_Adapter_Presenter {
    Iitem_Personagem_Lista_Adapter_View view;
    Context context;
    private PersonagensDAO personagensDAO;
    private MesasDAO mesasDAO;
    private SituacaoDAO situacaoDAO;
    private ClassesDAO classesDAO;
    private Personagens pj;

    public item_Personagem_Lista_Adapter_Presenter (Iitem_Personagem_Lista_Adapter_View view, Context context){
        this.view = view;
        this.context = context;
        personagensDAO = new PersonagensDAO(context);
        mesasDAO = new MesasDAO(context);
        situacaoDAO = new SituacaoDAO(context);
        classesDAO = new ClassesDAO(context);
    }

    @Override
    public void getPJDetails(List<Personagens> list, int position) {
         pj = list.get(position);

        String nome = context.getString(R.string.nome_label, pj.getNome());
        String nivel = context.getString(R.string.nivel_label, pj.getNivel());
        String mesa = context.getString(R.string.mesa_label, mesasDAO.getMesaNomeByID(pj.getMesa()));
        String situacao = context.getString(R.string.situacao_label, situacaoDAO.getSituacaoNomeByID(pj.getSituacao()));
        String classe = context.getString(R.string.classe_label, classesDAO.getClasseNomeByID(pj.getClasse()));
        view.showPJDetails(nome, nivel, mesa, situacao, classe);
    }

    @Override
    public void onItemClick() {
        Intent intent = new Intent(context, PersonagemActivity.class);
        intent.putExtra(Item_personagens_lista_adapter.PERSONAGEM, pj);
        context.startActivity(intent);
    }

    @Override
    public void onDeleteClick(List<Personagens> list, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.deletar_mensagem)
                .setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        personagensDAO.apagarPersonagem(pj);
                        list.remove(position);
                        view.notifyRecyclerViewDelete(list, position);
                    }
                })
                .setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(
                                        context,
                                        "Cancelado",
                                        Toast.LENGTH_SHORT)
                                .show();
                    }
                })
                .show();
    }

    @Override
    public void onComentsClick() {
        Intent intent = new Intent(context, ComentariosActivity.class);
        intent.putExtra(Item_personagens_lista_adapter.PERSONAGEM, pj);
        context.startActivity(intent);
    }

    @Override
    public void onDestroy() {
        view = null;
    }
}
