package br.edu.ifsul.loja.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import br.edu.ifsul.loja.R;


public class CadUserActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_user);
        TextView tv_nome=findViewById(R.id.tv_nome_cadUser;
        TextView tv_email=findViewById(R.id.tv_email_cadUser;
        EditText ed_senha=findViewById(R.id.ed_senha_cadUser;


    }

}
