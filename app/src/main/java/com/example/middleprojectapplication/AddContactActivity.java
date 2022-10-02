package com.example.middleprojectapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AddContactActivity extends AppCompatActivity {

    ImageView profileImage;
    String date;
    String time;
    ActivityResultLauncher<Intent> imageResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result!=null && result.getResultCode() == RESULT_OK){
                if (result.getData() != null){
                    Bitmap bitmap = result.getData().getParcelableExtra("data");
                    profileImage.setImageBitmap(bitmap);
                }
            }
        }
    });
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact_layout);
        profileImage = findViewById(R.id.image_input);
        Button takePictureBtn = findViewById(R.id.take_pic_btn);
        takePictureBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            imageResult.launch(intent);
        });

        TextView birthDateTv = findViewById(R.id.birth_date_input);
        Calendar calendar  = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        Button pickBirthDateBtn = findViewById(R.id.pick_birth_date_btn);
        pickBirthDateBtn.setOnClickListener(view -> {

            DatePickerDialog dpd = new DatePickerDialog(this, (DatePickerDialog.OnDateSetListener) (datePicker, i, i1, i2) -> {
                i1++;
                date = i2+"/"+i1+"/"+i;
                birthDateTv.setText(date);
            },year,month,day);
            dpd.show();
        });

        TextView timeToCallTv = findViewById(R.id.time_to_call_input);
        Button pickTimeToCallBtn = findViewById(R.id.pick_time_to_call_btn);
        pickTimeToCallBtn.setOnClickListener(view -> {
            TimePickerDialog tpd = new TimePickerDialog(this, (TimePickerDialog.OnTimeSetListener) (timePicker, i, i1) -> {
                time = i+":"+i1;
                timeToCallTv.setText(time);
            },hour,minutes,true);
        tpd.show();
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_contact_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.back_item){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
