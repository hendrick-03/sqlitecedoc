package ac.mz.sqlitecedoc;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private Cursor cursor;
    private static OnClickListener listener;

    public DatabaseHelper databaseHelper;

    public void atualizarDados() {
        Cursor newCursor = databaseHelper.obterTodosLivros();
        swapCursor(newCursor);
    }

    public interface OnClickListener {
        void onItemClick(int position);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        listener = onClickListener;
    }

    public Adapter(Cursor cursor, DatabaseHelper databaseHelper) {
        this.cursor = cursor;
        this.databaseHelper = databaseHelper;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.rowitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (!cursor.moveToPosition(position)) {
            return;
        }

        @SuppressLint("Range") String titulo = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TITULO));
        @SuppressLint("Range") String autor = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AUTOR));

        holder.tituloTextView.setText(titulo);
        holder.autorTextView.setText(autor);
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        if (cursor != null) {
            cursor.close();
        }

        cursor = newCursor;

        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tituloTextView;
        public TextView autorTextView;
        private ImageButton excluirbtn;
        private TextView ttulo_dt, ano_dt, autor_dt, curso_dt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tituloTextView = itemView.findViewById(R.id.ttl_tv_row);
            autorTextView = itemView.findViewById(R.id.autor_tv_row);
            excluirbtn = itemView.findViewById(R.id.deleteIb);

            ttulo_dt = itemView.findViewById(R.id.ttl_detalhes_Tv);
            ano_dt = itemView.findViewById(R.id.ano_detalhes_tv);
            autor_dt = itemView.findViewById(R.id.autor_detalhes_tv);
            curso_dt = itemView.findViewById(R.id.curso_detalhes_tv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    abrirActivity();
                }
            });

            excluirbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }

        public void abrirActivity() {
            Intent i = new Intent(itemView.getContext(), DetalhesActivity.class);
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION && cursor != null) {
                cursor.moveToPosition(position);
                @SuppressLint("Range") String titulo = cursor.getString(cursor.getColumnIndex("titulo"));
                @SuppressLint("Range") int ano = cursor.getInt(cursor.getColumnIndex("ano"));
                @SuppressLint("Range") String autor = cursor.getString(cursor.getColumnIndex("autor"));
                @SuppressLint("Range") String curso = cursor.getString(cursor.getColumnIndex("curso"));

                i.putExtra("titulo", titulo);
                i.putExtra("ano", ano);
                i.putExtra("autor", autor);
                i.putExtra("curso", curso);

                itemView.getContext().startActivity(i);
            }
        }
        public void swapCursor(Cursor newCursor){
            if(cursor != null){
                cursor.close();

            }
            cursor = newCursor;
            notifyDataSetChanged();
        }


    }
}

