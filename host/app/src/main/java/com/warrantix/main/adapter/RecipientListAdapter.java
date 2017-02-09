package com.warrantix.main.adapter;

import android.app.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.warrantix.main.R;
import com.squareup.picasso.Picasso;
import com.warrantix.main.WarrantixApplication;

import java.util.ArrayList;
import java.util.Arrays;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecipientListAdapter extends ArrayAdapter<RecipientListAdapter.Recipient> implements AdapterView.OnItemClickListener {

    public static class Recipient {
        public String name;
        public int imageResource;   // temporary

        public Recipient() {

        }
    }

    private final Activity context;
    private ArrayList<Recipient> recipients;
    private OnRecipientItemSelectListener itemSelectListener = null;

    public RecipientListAdapter(Activity context, Recipient[] recipients) {
        super(context, R.layout.listview_recipient, recipients);

        this.context = context;
        if (recipients == null)
            this.recipients = new ArrayList<>();
        else {
            this.recipients = new ArrayList<>();
            for (int i=0; i<recipients.length; i++)
                this.recipients.add(recipients[i]);
        }
    }

    public void setRecipientItemSelectListener(OnRecipientItemSelectListener listener) {
        this.itemSelectListener = listener;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView;

        if ((recipients != null) && (position < recipients.size())) {
            rowView = inflater.inflate(R.layout.listview_recipient, null, true);
            TextView nameText = (TextView) rowView.findViewById(R.id.profileNameView);
            nameText.setText(recipients.get(position).name);

            ImageView profileImageView = (ImageView) rowView.findViewById(R.id.profileImageView);
            Picasso.with(this.context.getApplicationContext())
                    .load(recipients.get(position).imageResource)
                    .error(R.drawable.image_holder)
                    .placeholder(R.drawable.image_holder)
                    .into(profileImageView);
        }
        else {
            rowView = new View(context);
        }
        return rowView;
    }

    public void addRecipient(Recipient recipient) {
        if (this.recipients == null)
            this.recipients = new ArrayList<>();

        this.recipients.add(recipient);
    }

    public void removeRecipient(int position) {
        this.recipients.remove(position);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (recipients == null)
            return;

        if (position >= recipients.size())
            return;

        Recipient recipient = recipients.get(position);
        removeRecipient(position);
        RecipientListAdapter.this.notifyDataSetChanged();

        if (itemSelectListener != null)
            itemSelectListener.OnRecipientItemSelected(recipient);
    }

    //
    // public interface
    //

    public interface OnRecipientItemSelectListener {
        public void OnRecipientItemSelected(Recipient recipient);
    }
}