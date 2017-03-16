package hsm.yeng.updates;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import hsm.yeng.R;

/**
 * Created by sibi on 15/2/17.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    ArrayList<NewsDataModel> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtHeader;
        public TextView txtFooter;
        public TextView txtContent;

        public ViewHolder(View v) {
            super(v);
            txtHeader = (TextView) v.findViewById(R.id.news_head);
            txtContent= (TextView) v.findViewById(R.id.news_content);
            txtFooter = (TextView) v.findViewById(R.id.news_date);
        }
    }

 /*   public void add(int position, String item) {
        mDataset.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(String item) {
        int position = mDataset.indexOf(item);
        mDataset.remove(position);
        notifyItemRemoved(position);
    }*/

    // Provide a suitable constructor (depends on the kind of dataset)
    public NewsAdapter(ArrayList<NewsDataModel> myDataset) {
        mDataset = myDataset;
        Log.e("oncreate view..........", "constructor"+mDataset.size());
    }

    // Create new views (invoked by the layout manager)
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_updates_single_view, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Log.e("onbind","dddddddddddddd");
        NewsDataModel newsDataModel = mDataset.get(position);
        holder.txtHeader.setText(newsDataModel.getNewsHead());

        holder.txtFooter.setText(newsDataModel.getNewsDate());
        holder.txtContent.setText(newsDataModel.getNewsContent());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount()
    {
        Log.e("oncreate view..........", "getitem count"+mDataset.size());

        return mDataset.size();
    }

}
