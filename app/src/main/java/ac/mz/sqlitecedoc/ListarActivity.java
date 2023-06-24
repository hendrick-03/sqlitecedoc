package ac.mz.sqlitecedoc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

public class ListarActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);



        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        Cursor cursor = databaseHelper.obterTodosLivros();
        adapter = new Adapter(cursor, databaseHelper);

        recyclerView.setAdapter(adapter);

        adapter.setOnClickListener(new Adapter.OnClickListener() {
            @Override
            public void onItemClick(int position) {
                databaseHelper.excluirLivro(position);
                adapter.atualizarDados();

                Toast.makeText(ListarActivity.this, "Removido com sucesso", Toast.LENGTH_SHORT).show();
            }
        });


    }
}