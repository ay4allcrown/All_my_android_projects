package com.gbogboade.ayomide.eventstracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class EventsListAdapter extends BaseAdapter {
    Context context;
    String events[];
    String eventsDate[];
    int eventsIcon[];
    LayoutInflater inflater;

    /**
     * @param applicationContext
     * @param events
     * @param eventsDate
     * @param eventsIcon
     */
    public EventsListAdapter(Context applicationContext, String[] events,
                             String[] eventsDate, int[] eventsIcon) {
        if (applicationContext != null) {
            this.context = applicationContext;
        }
        if (events != null) {
            this.events = events;
        }
        if (eventsDate != null) {
            this.eventsDate = eventsDate;
        }
        if (eventsIcon != null) {
            this.eventsIcon = eventsIcon;
        }
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return events.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.activity_events_listview, null);
        ImageView eventIconImageView = (ImageView) view.findViewById(R.id.event_icon_imageview);
        TextView eventTextView = (TextView) view.findViewById(R.id.event_textView);
        TextView eventDateTextView = (TextView) view.findViewById(R.id.eventdate_textView);
        try {
            eventIconImageView.setImageResource(eventsIcon[i]);
            eventTextView.setText(events[i]);
            eventDateTextView.setText(eventsDate[i]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }
}