package ba.ibu.sajraprojekat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import ba.ibu.sajraprojekat.Common.Common;
import ba.ibu.sajraprojekat.Model.User;

public class SignIn extends AppCompatActivity {

    EditText edtPhone, edtPassword;
    Button btnSignIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtPhone = (MaterialEditText)findViewById(R.id. edtPhone);
        edtPassword = (MaterialEditText)findViewById(R.id. edtPassword);
        btnSignIn = findViewById(R.id.btnSignIn);

        //init firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog mDialog = new ProgressDialog(SignIn.this);
                mDialog.setMessage("Please wait...");
                mDialog.show();



                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        //check if user exist in database
                        if (dataSnapshot.child(edtPhone.getText().toString()).exists()) {
                            //get user info
                            mDialog.dismiss();

                            User user = dataSnapshot.child(edtPhone.getText().toString()).getValue(User.class);
                            user.setPhone(edtPhone.getText().toString());
                            if (user.getPass() != null && edtPassword.getText() != null && user.getPass().equals(edtPassword.getText().toString())) {
                                Intent homeIntent= new Intent(SignIn.this, Home.class);
                                Common.currentUse = user;
                                startActivity(homeIntent);
                                finish();
                            } else {

                                Toast.makeText(SignIn.this, "Sign in failed", Toast.LENGTH_SHORT).show();


                            }
                        }else{
                            mDialog.dismiss();
                            Toast.makeText(SignIn.this, "User Does Not exist", Toast.LENGTH_SHORT).show();
                        }
                }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                    ;
            });
        };
    });
    };
}
