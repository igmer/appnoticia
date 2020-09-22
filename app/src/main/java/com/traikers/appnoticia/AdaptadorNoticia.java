package com.traikers.appnoticia;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.traikers.appnoticia.models.Noticia;

import java.util.List;

public class AdaptadorNoticia extends BaseAdapter {
    Noticia noticia;
    Context context;

    public AdaptadorNoticia(Noticia noticia, Context context) {
        this.noticia = noticia;
        this.context = context;
    }

    @Override
    public int getCount() {
        return noticia.getArticles().size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        TextView titulo,autor;
        View v = inflater.inflate(R.layout.fila_noticia, viewGroup, false);
        CardView cardView = v.findViewById(R.id.card_view_noticia);
        titulo = v.findViewById(R.id.titulo);
        autor = v.findViewById(R.id.autor);
        titulo.setText(noticia.getArticles().get(i).getTitle());
        autor.setText(noticia.getArticles().get(i).getAuthor());
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,DetalleActivity.class);
                intent.putExtra("url", noticia.getArticles().get(i).getUrl());
                context.startActivity(intent);
            }
        });
        return v;
    }
}
