package ac.mz.sqlitecedoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText titulo, ano, autor, curso;
    private Button save, listar;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titulo = findViewById(R.id.titulo_ed);
        ano = findViewById(R.id.ano_ed);
        autor = findViewById(R.id.autor_ed);
        curso = findViewById(R.id.curso_ed);


        save = findViewById(R.id.gravar_btn);
        listar= findViewById(R.id.listar_btn);

        databaseHelper = new DatabaseHelper(this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ttl = titulo.getText().toString();
                int year = 0;
                String atr = autor.getText().toString();
                String crs = curso.getText().toString();

                if (ttl.isEmpty() || atr.isEmpty() || crs.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    year = Integer.parseInt(ano.getText().toString());
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Ano inválido", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean sucesso = databaseHelper.inserirLivro(ttl, year, atr, crs);
                if (sucesso) {
                    Toast.makeText(getApplicationContext(), "Dados salvos com sucesso", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Erro ao salvar os dados", Toast.LENGTH_SHORT).show();
                }


                titulo.setText("");
                ano.setText("");
                autor.setText("");
                curso.setText("");
            }





        });

        listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ListarActivity.class);
                startActivity(i);
            }
        });




    }
}