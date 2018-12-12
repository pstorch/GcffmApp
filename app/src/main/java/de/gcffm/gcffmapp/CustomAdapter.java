package de.gcffm.gcffmapp;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Adapter for ListView of GcEvents
 */
public class CustomAdapter extends ArrayAdapter<GcEvent> {

    private final int resourceLayout;
    private final Context mContext;

    public CustomAdapter(final Context context, final int resource, final List<GcEvent> events) {
        super(context, R.layout.item, events);

        this.resourceLayout = resource;
        this.mContext = context;
    }

    @NonNull
    @Override
    public View getView(final int position, final View convertView, @NonNull final ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            final LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            view = vi.inflate(resourceLayout, null);
        }

        if (position % 2 == 1) {
            view.setBackgroundResource(R.drawable.item_list_backgroundcolor);
        } else {
            view.setBackgroundResource(R.drawable.item_list_backgroundcolor2);
        }

        final GcEvent p = getItem(position);

        if (p != null) {
            final TextView date = view.findViewById(R.id.eventDate);
            final TextView coord = view.findViewById(R.id.eventCoord);
            final TextView name = view.findViewById(R.id.eventName);

            if (date != null) {
                date.setText(SimpleDateFormat.getDateInstance(DateFormat.SHORT).format(p.getDatum()));
            }

            if (coord != null) {
                coord.setText(p.getCoords());
            }

            if (name != null) {
                if (Build.VERSION.SDK_INT >= 24) {
                    name.setText(Html.fromHtml(p.getName() , Html.FROM_HTML_MODE_LEGACY));
                } else {
                    name.setText(Html.fromHtml(p.getName()));
                }
            }

        }

        return view;
    }

}
