package br.edu.ifsul.loja.activity;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.edu.ifsul.loja.R;
import br.edu.ifsul.loja.model.Cliente;

public class ClientesAdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes_admin);
    }

    public void cadastrar(String nome,String sobrenome,String cpf,long barras,String foto){
        Cliente c=new Cliente();
        c.setNome(nome);
        c.setSobrenome(sobrenome);
        c.setCpf(cpf);
        c.setCodigoDeBarras(barras);
        c.setUrl_foto(foto);
        final DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("vendas/clientes/");
myRef.push().setValue(c);
    }
    public void excluiCliente(){
        Cliente c=new Cliente();
        final DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("vendas/clientes/");
        myRef.child(c.getKey()).child("situacao").setValue(false);
        Snackbar.make(findViewById(R.id.container_cliente_admin), R.string.snack_cliente_excluido, Snackbar.LENGTH_LONG).show();

    }
}
