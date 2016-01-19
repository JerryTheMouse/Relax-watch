package org.jerrycode.relaxwatch.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.jerrycode.relaxwatch.Models.Trailer;
import org.jerrycode.relaxwatch.R;

/**
 * Created by Shady Atef (shadyoatef@gmail.com) on 1/19/16.
 */
public class TrailerAdapter extends ArrayAdapter<Trailer> {
    private LayoutInflater inflater;

    public TrailerAdapter(Context context, int resource) {
        super(context, resource);
        inflater = LayoutInflater.from(context);


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.trailer_item, parent, false);
        }
        Trailer t = getItem(position);
        TextView name_tv = (TextView) convertView.findViewById(R.id.trailer_name);
        name_tv.setText("Trailer #".concat(String.valueOf(position+1)));
        return convertView;
    }

}
