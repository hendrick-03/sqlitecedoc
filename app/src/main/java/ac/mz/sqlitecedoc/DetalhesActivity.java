package ac.mz.sqlitecedoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetalhesActivity extends AppCompatActivity {
    private TextView ttl_dtlh, ano_dtlh, autor_dtlh, curso_dtlh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        ttl_dtlh = findViewById(R.id.ttl_detalhes_Tv);
        ano_dtlh = findViewById(R.id.ano_detalhes_tv);
        autor_dtlh = findViewById(R.id.autor_detalhes_tv);
        curso_dtlh = findViewById(R.id.curso_detalhes_tv);

        Intent i = getIntent();
        String titulo = i.getStringExtra("titulo");
        int ano = i.getIntExtra("ano", 0);
        String autor = i.getStringExtra("autor");
        String curso = i.getStringExtra("curso");

        ttl_dtlh.setText(titulo);
        ano_dtlh.setText(String.valueOf(ano));
        autor_dtlh.setText(autor);
        curso_dtlh.setText(curso);


    }
}