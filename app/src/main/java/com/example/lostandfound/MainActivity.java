package com.example.lostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
public class MainActivity extends AppCompatActivity {
    private SignInButton signBtn;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private GoogleSignInClient mGoogleSignInClient;
    private final int RC_SIGN_IN=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {//TODO Проаверка что уже авторизирован
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        signBtn = findViewById(R.id.sign_in_button);
        signBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(ProgressBar.VISIBLE);
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken("13014954881-6gft53rgeru7qfidf6uhtu9r29ce59s0.apps.googleusercontent.com")
                        .requestEmail()
                        .build();
                mGoogleSignInClient = GoogleSignIn.getClient(MainActivity.this, gso);
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
                //GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this); Проверка что уже авторизированаы
            }
        });
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //FirebaseAuth.getInstance().signOut(); Выход из аккаунта
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);//Ошибка
            firebaseAuthWithGoogle(account.getIdToken());
        } catch (ApiException e) {
            Log.w( "TAG", "signInResult:failed code=" + e.getStatusCode());

        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("TAG", "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(MainActivity.this,"Signed",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(ProgressBar.INVISIBLE);
                            startActivity(new Intent(MainActivity.this, FoundActivity.class));
                        } else {
                            progressBar.setVisibility(ProgressBar.INVISIBLE);
                            Log.w("TAG", "signInWithCredential:failure", task.getException());
                            Toast.makeText(MainActivity.this,"Failed ",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}