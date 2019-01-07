package com.agtechnosolution.leadsapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    private EditText name,email,mobile,comment;
    private String emailPattern="[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
    private String nameStr,emailStr,mobileStr,commentStr;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db=new DatabaseHandler(this);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        mobile=findViewById(R.id.mobile);
        comment=findViewById(R.id.comment);

    }

    public boolean checkEmail(String mail){
        if(!mail.matches(emailPattern) && !(mail.length()==0)){
            email.setError("Invalid Email id");
            return false;
        }else
            return true;
    }

    public boolean checkMobile(String phone){
        if(phone.length()!=10){
            mobile.setError("Invalid Mobile no.");
            return false;
        }else
            return true;
    }

    public boolean isNotEmpty(String text){
        if(text.length()<=0){
            return false;
        }else
            return true;
    }

    public void submitFunc(View view) {
        nameStr=name.getText().toString().trim();
        emailStr=email.getText().toString().trim();
        mobileStr=mobile.getText().toString().trim();
        commentStr=comment.getText().toString().trim();
        if(isNotEmpty(nameStr) && isNotEmpty(commentStr) && checkEmail(emailStr) && checkMobile(mobileStr)){
            db.addLead(new Lead(nameStr,emailStr,mobileStr,commentStr));
            Toast.makeText(this,"Added Successfully",Toast.LENGTH_LONG).show();
            clearFields();
        }
    }

    public void clearFields(){
        name.setText("");
        email.setText("");
        mobile.setText("");
        comment.setText("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(db!=null)
            db.close();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.homeAsUp:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
