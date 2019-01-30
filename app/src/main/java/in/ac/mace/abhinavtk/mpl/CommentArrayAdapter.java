package in.ac.mace.abhinavtk.mpl;


import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ramotion.expandingcollection.ECCardContentListItemAdapter;
import in.ac.mace.abhinavtk.mpl.pojo.Comment;

import java.util.List;

@SuppressLint({"SetTextI18n", "InflateParams"})
public class CommentArrayAdapter extends ECCardContentListItemAdapter<Comment> {

    public CommentArrayAdapter(@NonNull Context context, @NonNull List<Comment> objects) {
        super(context, R.layout.list_element, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        View rowView = convertView;
        final Comment objectItem = getItem(position);

        if (rowView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            if(objectItem.getTeamno()==0)
                rowView = inflater.inflate(R.layout.list_element, null);
            else if(objectItem.getTeamno()==1)
                rowView = inflater.inflate(R.layout.list_element_invert, null);
            // configure view holder
            viewHolder = new ViewHolder();
            viewHolder.date =  rowView.findViewById(R.id.firstLineDate);
            viewHolder.line1 = rowView.findViewById(R.id.firstLine);
            viewHolder.line2 = rowView.findViewById(R.id.secondLine);
            viewHolder.icon =  rowView.findViewById(R.id.icon);
            rowView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) rowView.getTag();
        }

        if (objectItem != null) {
            viewHolder.line1.setText(objectItem.getCommentPersonName() + ":");
            viewHolder.line2.setText(objectItem.getCommentText());
            viewHolder.date.setText(objectItem.getCommentDate());
            viewHolder.icon.setImageResource(objectItem.getCommentPersonPictureRes());
        }

        // Removing item by tap on comment icon
//        viewHolder.icon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                CommentArrayAdapter.this.remove(objectItem);
//                CommentArrayAdapter.this.notifyDataSetChanged();
//            }
//        });

        return rowView;
    }

    static class ViewHolder {
        TextView date;
        TextView line1;
        TextView line2;
        ImageView icon;
    }

}
