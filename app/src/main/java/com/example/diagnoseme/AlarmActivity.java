package com.example.diagnoseme;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.style.TtsSpan;
import android.view.View;
import android.widget.DigitalClock;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.text.DateFormat;
import java.util.Calendar;

public class AlarmActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    private EditText mEditText;
    private EditText mEditText2;
    private DigitalClock mDigitalClock;
    private TextView mTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        mEditText = (EditText) findViewById(R.id.textAlarmTitle);
        mEditText2 = (EditText) findViewById(R.id.editDescrip);
        mDigitalClock = (DigitalClock) findViewById(R.id.digitalClock);
        mDigitalClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment timePicker = new DialogFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);
        
        updateTimeText(c);
        startAlarm(c);

    }

    private void updateTimeText(Calendar c) {
        DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
        mEditText.getText();

        DateFormat.getTimeInstance(DateFormat.MEDIUM).format(c.getTime());
        mEditText2.getText();

//        String Title = new String(mEditText.getText().toString());
//        String Description = new String(mEditText2.getText().toString());
//
//        Intent intent = new Intent();
//        intent.putExtra(Intent.EXTRA_TITLE, Title);
//        intent.putExtra(Intent.EXTRA_TEXT, Description);
    }

    private void startAlarm(Calendar c) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        if (c.before(Calendar.getInstance())){
            c.add(Calendar.DATE, 1);
        }

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
    }
}
