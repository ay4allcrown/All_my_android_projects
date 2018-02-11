package com.gbogboade.ayomide.eventstracker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

public class AddEventDialog extends DialogFragment {
    DatePicker eventDatePicker;
    EditText eventTitleEditText;
    String date;
    String eventTitle;

    public String getEventTitle() {
        return eventTitle;
    }

    public String getDate() {

        return date;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View addEventLayout = getActivity().getLayoutInflater().inflate(R.layout.activity_add_event_layout, null);
        eventTitleEditText = (EditText) addEventLayout.findViewById(R.id.event_title_editText);
        eventDatePicker = (DatePicker) addEventLayout.findViewById(R.id.event_datePicker);
        builder.setView(addEventLayout);
        builder.setTitle("Add event");
        builder.setPositiveButton("Add event", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int year = eventDatePicker.getYear();
                int month = eventDatePicker.getMonth();
                int day = eventDatePicker.getDayOfMonth();
                date = year + "-" + month + "-" + day;
                eventTitle = eventTitleEditText.getText().toString();
                Log.d("Add event", date + " " + eventTitleEditText);
            }
        });
        builder.setNegativeButton("Cancel", null);
        return builder.create();
    }

}














