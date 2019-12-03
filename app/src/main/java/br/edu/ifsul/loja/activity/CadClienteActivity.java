package br.edu.ifsul.loja.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.edu.ifsul.loja.R;
import br.edu.ifsul.loja.model.Cliente;

public class CadClienteActivity extends AppCompatActivity {


    private static final int RC_GALERIA_IMAGE_PICK =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_cliente);

        TextView tv_cadCliente_nome=findViewById(R.id.tv_cadCliente_nome);
        TextView tv_cadCliente_sobrenome=findViewById(R.id.tv_cadCliente_sobrenome);
        TextView tv_cadCliente_cpf=findViewById(R.id.tv_cadCliente_cpf);
        EditText ed_cadCliente_codBarras=findViewById(R.id.ed_cadCliente_codBarras);
        ImageView iv_cadCliente_foto=findViewById(R.id.iv_cadCliente_foto);
        String codBarras=ed_cadCliente_codBarras.toString();
        long barras=Long.parseLong(codBarras);
        String nome=tv_cadCliente_nome.toString();
        String sobrenome=tv_cadCliente_sobrenome.toString();
        String cpf=tv_cadCliente_cpf.toString();

        iv_cadCliente_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cria uma Intent
                //primeiro argumento: ação ACTION_PICK "pegar algum dado"
                //segundo argumento: refina a ação para arquivos de fotoProduto, na galeria do dispositivo, retornando um URI
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                //inicializa uma Activity esperando o seu resultado. Neste caso, uma que forneca acesso a galeria de imagens do dispositivo.
                startActivityForResult(Intent.createChooser(intent,getString(R.string.titulo_janela_galeria_imagens)), RC_GALERIA_IMAGE_PICK);
            }
        });

String foto=iv_cadCliente_foto.toString();
ClientesAdminActivity admc=new ClientesAdminActivity();
admc.cadastrar(nome,sobrenome,cpf,barras,foto);
    }
}
