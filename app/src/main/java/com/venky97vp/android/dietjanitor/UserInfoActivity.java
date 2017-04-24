package com.venky97vp.android.dietjanitor;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener{

    EditText uName,uHeight,uWeight,uDob;
    DatePickerDialog dobDialog;
    Spinner uActivity,uGender;
    FloatingActionButton save;
    String userId;
    private FirebaseAuth mAuth;
    int age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        mAuth = FirebaseAuth.getInstance();

        userId = mAuth.getCurrentUser().getUid();

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        save = (FloatingActionButton) findViewById(R.id.save_info_button);
        uName = (EditText) findViewById(R.id.name_edit);
        uHeight = (EditText) findViewById(R.id.height_edit);
        uWeight = (EditText) findViewById(R.id.weight_edit);
        uActivity = (Spinner) findViewById(R.id.activity_spinner);
        uGender = (Spinner) findViewById(R.id.gender_spinner);
        uDob = (EditText) findViewById(R.id.dob_picker);
        uDob.setInputType(InputType.TYPE_NULL);
        uDob.requestFocus();

        Calendar newCalendar = Calendar.getInstance();
        dobDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                uDob.setText(dateFormatter.format(newDate.getTime()));
                age=AgeCalculation.ageCalc(year,monthOfYear,dayOfMonth);
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        uDob.setOnClickListener(this);
        save.setOnClickListener(this);
    }

    private SimpleDateFormat dateFormatter;
    User user;

    void createDB(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mDatabase = database.getReference();

        user = new User();
        user.name = uName.getText().toString();
        user.height = Double.parseDouble(uHeight.getText().toString());
        user.weight = Double.parseDouble(uWeight.getText().toString());
        user.gender = uGender.getSelectedItemPosition();
        user.activity = uActivity.getSelectedItemPosition();
        user.uid = userId;
        user.age = age;
        user.calculateBMI();
        user.calculateBMR();

        mDatabase.child("users").child(userId).setValue(user);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.save_info_button){
            if(!uName.getText().toString().matches("") && !uName.getText().toString().matches("") && !uName.getText().toString().matches("")) {
                new AlertDialog.Builder(this)
                        .setTitle("Save")
                        .setMessage("Are you sure about updating your profile?")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                createDB();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();
                            }
                        })
                        .show();
            }else {
                Toast.makeText(getApplicationContext(),"Enter all the values appropriately",Toast.LENGTH_SHORT).show();
            }
        }else{
            dobDialog.show();
        }
    }
}
