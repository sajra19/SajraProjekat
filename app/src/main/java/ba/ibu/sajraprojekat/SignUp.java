package ba.ibu.sajraprojekat;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import ba.ibu.sajraprojekat.Model.User;

public class SignUp extends AppCompatActivity {

    MaterialEditText edtName, edtPhone, edtPassword;
    Button btnSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        edtPhone = (MaterialEditText)findViewById(R.id. edtPhone);
        edtName = (MaterialEditText)findViewById(R.id. edtName);
        edtPassword = (MaterialEditText)findViewById(R.id. edtPassword);
        btnSignUp = findViewById(R.id.btnSignUp);

        //init firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog mDialog = new ProgressDialog(SignUp.this);
                mDialog.setMessage("Please wait...");
                mDialog.show();


                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    //check if phone already exists
                    if(dataSnapshot.child(edtPhone.getText().toString()).exists()){

                        mDialog.dismiss();
                        Toast.makeText(SignUp.this, "Phone number registered", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        mDialog.dismiss();
                        User user = new User(edtName.getText().toString(),edtPassword.getText().toString());
                        table_user.child(edtPhone.getText().toString()).setValue(user);
                        Toast.makeText(SignUp.this, "Sign Up successful", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}
