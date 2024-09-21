package com.example.quizdemoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    private EditText name, email, password, cnfpassword;
    private Button signupbtn;
    private ImageView backbtn;
    private FirebaseAuth mAuth;

    private Dialog progressDiaglog;

    private TextView dialogtext;

    private String emailStr, passwordStr, cnfpasswordStr, nameStr;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name= findViewById(R.id.user_name);
        email= findViewById(R.id.email_id);
        password = findViewById(R.id.pswd);
        cnfpassword = findViewById(R.id.cnfpswd);
        backbtn = findViewById(R.id.backbtn);
        signupbtn = findViewById(R.id.signup_btn);
        progressDiaglog = new Dialog(SignUpActivity.this);
        progressDiaglog.setContentView(R.layout.dialog_layout);
        progressDiaglog.setCancelable(false);
        progressDiaglog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        dialogtext= progressDiaglog.findViewById(R.id.dialogtxt);
        dialogtext.setText("Registering User...");


        mAuth= FirebaseAuth.getInstance();


        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validate()) {
                    signupNewUser();
                }
            }
        });

    }

    private boolean validate(){
        nameStr = name.getText().toString().trim();
        emailStr = email.getText().toString().trim();
        passwordStr = password.getText().toString().trim();
        cnfpasswordStr = cnfpassword.getText().toString().trim();

        if (nameStr.isEmpty()){
            name.setError("Enter Your Name");
            return false;
        }

        if (emailStr.isEmpty()){
            email.setError("Enter Email Id");
            return false;
        }

        if (passwordStr.isEmpty()){
            password.setError("Enter password");
            return false;
        }

        if (cnfpasswordStr.isEmpty()){
            cnfpassword.setError("Enter Confirm password");
            return false;
        }

        if(passwordStr.compareTo(cnfpasswordStr)!=0){
            Toast.makeText(SignUpActivity.this, "Password & Confirm password Doesn't match", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
    private void signupNewUser(){
        progressDiaglog.show();

        mAuth.createUserWithEmailAndPassword(emailStr, passwordStr)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(SignUpActivity.this, "Sign Up Successfuly", Toast.LENGTH_SHORT).show();
                            progressDiaglog.dismiss();

                            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                            startActivity(intent);
                            SignUpActivity.this.finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            progressDiaglog.dismiss();
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}

//emai: demo@gmail.com & pass:123456//