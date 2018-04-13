package com.example.viet.danhba.CustomContact;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.viet.danhba.R;
import com.example.viet.danhba.model.Contact;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Contact>{
    private Context context;
    private int resource;
    private List<Contact> arrayContact;
    public CustomAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super( context, resource, objects );
        this.context = context;
        this.resource = resource;
        this.arrayContact = objects;
        }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from( context ).inflate( R.layout.custom_listview, parent, false );
            viewHolder.imageAvatar = convertView.findViewById( R.id.img_avatar );
            viewHolder.tvName = convertView.findViewById( R.id.tv_name );
            viewHolder.tvNumber = convertView.findViewById( R.id.tv_number );
            convertView.setTag(viewHolder);
        } else viewHolder = (ViewHolder) convertView.getTag();

        Contact contact = arrayContact.get( position );
        viewHolder.tvName.setText( contact.getmName() );
        viewHolder.tvNumber.setText( contact.getmNumber() );

        if (contact.isMale()) {
            viewHolder.imageAvatar.setBackgroundResource( R.drawable.male );
        }else viewHolder.imageAvatar.setBackgroundResource( R.drawable.female );
        return convertView;
    }

    public class ViewHolder {
        ImageView imageAvatar;
        TextView tvName;
        TextView tvNumber;

    }
}
