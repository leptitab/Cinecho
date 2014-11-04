package ca.qc.cstj.android.cinecho.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by 1240755 on 2014-10-24.
 */
public abstract class GenericArrayAdapter<T> extends ArrayAdapter<T> {

    private LayoutInflater mInflater;
    private int mLatourRes;

    public GenericArrayAdapter(Context context, int layoutRes, ArrayList<T> liste) {
        super(context, layoutRes, liste);
        mLatourRes = layoutRes;
        init(context);
    }

    public abstract void update(TextView textview, T object);

    private void init(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final GenericViewHolder viewHolder;

        if(convertView == null) {

            convertView = mInflater.inflate(mLatourRes,parent, false);
            viewHolder = new GenericViewHolder(convertView);
            convertView.setTag(viewHolder);

        } else {

            viewHolder = (GenericViewHolder) convertView.getTag();
        }

        update(viewHolder.textView,getItem(position));

        return convertView;
    }

    private static class GenericViewHolder {
        private TextView textView;

        private GenericViewHolder(View rootView) {
            textView = (TextView) rootView.findViewById(android.R.id.text1);
        }
    }

}
