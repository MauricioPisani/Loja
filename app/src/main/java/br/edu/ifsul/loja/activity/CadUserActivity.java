package br.edu.ifsul.loja.activity;

import android.app.assist.AssistStructure;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import br.edu.ifsul.loja.R;
import br.edu.ifsul.loja.model.User;
import br.edu.ifsul.loja.setup.AppSetup;

import static br.edu.ifsul.loja.setup.AppSetup.mAuth;


public class CadUserActivity extends AppCompatActivity {
    private static final String TAG = "cadUserActivity";
    private Switch switch_singUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_user);
        TextView tv_nome=findViewById(R.id.tv_nome_cadUser);
        TextView tv_email=findViewById(R.id.tv_email_cadUser);
        EditText ed_senha=findViewById(R.id.ed_senha_cadUser);


    }
    private void signUp(final String email, String senha) {
        mAuth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            cadastrarUser();
                            FirebaseUser fUser = AppSetup.user.getFirebaseUser();
                            FirebaseDatabase.getInstance().getReference()
                                    .child("vendas").child("users").child(fUser.getUid()).setValue(AppSetup.user);
                            sendEmailVerification();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Snackbar.make(findViewById(R.id.CadUserActivity), getString(R.string.toast_falha_autenticacao), Snackbar.LENGTH_LONG).show();
                        }
                    }
                });
    }
    private void sendEmailVerification() {
        final FirebaseUser user = mAuth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Snackbar.make(findViewById(R.id.CadUserActivity), "Email de verificação enviado para " + user.getEmail(), Snackbar.LENGTH_SHORT).show();
                        } else {
                            Log.e(TAG, "sendEmailVerification", task.getException());
                            Snackbar.make(findViewById(R.id.CadUserActivity),"Envio de email para verifiacão falhou.", Snackbar.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void cadastrarUser() {
        User user = new User();
        user.setFirebaseUser(mAuth.getCurrentUser());
        if (switch_singUp.isChecked()) {
            user.setFuncao("admin");
        } else {
            user.setFuncao("vendedor");
        }
        user.setEmail(mAuth.getCurrentUser().getEmail());
        FirebaseDatabase.getInstance().getReference().child("vendas/users")
                .child(user.getFirebaseUser().getUid())
                .setValue(user);
        AppSetup.user = user;
    }
}