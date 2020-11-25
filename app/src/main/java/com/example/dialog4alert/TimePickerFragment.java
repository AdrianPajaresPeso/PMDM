package com.example.dialog4alert;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TimePickerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    /**
     * Creates the date picker dialog with the current date from Calendar.
     *
     * @param savedInstanceState    Saved instance state bundle
     * @return TimePickerDialog     The date picker dialog
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default date in the picker.
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MONTH);

        // Create a new instance of TimePickerDialog and return it.
        return new TimePickerDialog(
                getActivity(), this, hour, minute, true);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        MainActivity activity = (MainActivity) getActivity();
        activity.processTimePickerResult(hour,minute);
    }
}