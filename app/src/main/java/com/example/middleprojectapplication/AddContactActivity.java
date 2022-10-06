package com.example.middleprojectapplication;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class AddContactActivity extends AppCompatActivity {


    ImageView profileImage;
    String date;
    String time;
    Bitmap bitmap;
    EditText fullNameEt;
    EditText numberPhoneEt;
    EditText emailAddressEt;
    EditText homeAddressEt;
    EditText webAddressEt;
    TextView birthDateTv;
    TextView timeToCallTv;
    TextView bestDaysTv;
    ArrayList<String> daysChosen;
    boolean [] booleans;
    ActivityResultLauncher<Intent> imageResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result!=null && result.getResultCode() == RESULT_OK){
                if (result.getData() != null){
                    bitmap = result.getData().getParcelableExtra("data");
                    profileImage.setImageBitmap(bitmap);
                }
            }
        }
    });
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact_layout);

        fullNameEt = findViewById(R.id.full_name_input);
        numberPhoneEt = findViewById(R.id.number_phone_input);
        emailAddressEt = findViewById(R.id.email_address_input);
        homeAddressEt = findViewById(R.id.home_address_input);
        webAddressEt = findViewById(R.id.web_address_input);

        profileImage = findViewById(R.id.image_input);
        Button takePictureBtn = findViewById(R.id.take_pic_btn);
        takePictureBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            imageResult.launch(intent);
        });

        birthDateTv = findViewById(R.id.birth_date_input);
        Calendar calendar  = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        Button pickBirthDateBtn = findViewById(R.id.pick_birth_date_btn);
        pickBirthDateBtn.setOnClickListener(view -> {

            DatePickerDialog dpd = new DatePickerDialog(this, (datePicker, i, i1, i2) -> {
                i1++;
                date = i2+"/"+i1+"/"+i;
                birthDateTv.setText(date);
            },year,month,day);
            dpd.show();
        });

        timeToCallTv = findViewById(R.id.time_to_call_input);
        Button pickTimeToCallBtn = findViewById(R.id.pick_time_to_call_btn);
        pickTimeToCallBtn.setOnClickListener(view -> {
            TimePickerDialog tpd = new TimePickerDialog(this, (timePicker, i, i1) -> {
                if (i1<10){
                    time = i+":0"+i1;
                }
                else {
                    time = i+":"+i1;
                }
                timeToCallTv.setText(time);
            },hour,minutes,true);
            tpd.show();
        });

        bestDaysTv = findViewById(R.id.best_days_input);
        booleans =  new boolean[]{false,false,false,false,false,false,false};
        daysChosen = new ArrayList<>();
        Button pickBestDaysBtn = findViewById(R.id.pick_best_days_btn);
        pickBestDaysBtn.setOnClickListener(view -> {

            String[] days = getResources().getStringArray(R.array.days);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.chose_days);
            builder.setCancelable(false);
            builder.setMultiChoiceItems(days, booleans, (dialogInterface, i, b) -> {
                booleans[i] = b;
                if (b){
                    daysChosen.add(days[i]);
                }
                else {

                    daysChosen.remove(days[i]);
                }

            });
            builder.setPositiveButton(R.string.finish, (dialogInterface, i) ->
                    bestDaysTv.setText(daysChosen.toString())
            );
            builder.show();
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_contact_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save_item){

            if (fullNameEt.getText().toString().equals("")
                    || numberPhoneEt.getText().toString().equals("")
                    || emailAddressEt.getText().toString().equals("")
                    || homeAddressEt.getText().toString().equals("")
                    || webAddressEt.getText().toString().equals("")
                    || birthDateTv.getText().toString().equals("")
                    || timeToCallTv.getText().toString().equals("")
                    || bestDaysTv.getText().toString().equals("")
                    || bitmap == null
            ){
                Toast.makeText(this, R.string.please_enter_all_fields, Toast.LENGTH_SHORT).show();
            }
            else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.save_contact).setMessage(R.string.please_choose).setCancelable(false);
                builder.setPositiveButton(R.string.yes, (dialogInterface, i) -> {

                    Contact contact = new Contact(
                            fullNameEt.getText().toString(),
                            numberPhoneEt.getText().toString(),
                            emailAddressEt.getText().toString(),
                            homeAddressEt.getText().toString(),
                            webAddressEt.getText().toString(),
                            date,
                            time,
                            daysChosen,
                            bitmap
                    );

                    ContactManager manager = ContactManager.getInstance(this);
                    manager.addContact(contact);
                    fullNameEt.setText("");
                    numberPhoneEt.setText("");
                    emailAddressEt.setText("");
                    homeAddressEt.setText("");
                    webAddressEt.setText("");
                    birthDateTv.setText("");
                    timeToCallTv.setText("");
                    daysChosen = new ArrayList<>();
                    booleans =  new boolean[]{false,false,false,false,false,false,false};
                    bestDaysTv.setText("");
                    bitmap = null;
                    profileImage.setImageBitmap(null);


                }).setNegativeButton(R.string.no,null);
                builder.show();
            }
        }
        if (item.getItemId() == R.id.back_item){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
