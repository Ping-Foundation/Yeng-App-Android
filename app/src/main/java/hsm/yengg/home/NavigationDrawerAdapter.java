package hsm.yengg.home;

import android.support.v7.internal.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by nikhil on 7/9/15.
 */
public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.ViewHolder>{
    private String mtitles[];
    private int micons[];

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;

    ViewHolder(View itemView,int viewType){
        super(itemView);
        textView=(TextView)itemView.findViewById(R.id.rowText);
        imageView=(ImageView)itemView.findViewById(R.id.rowIcon);

    }
    }

    NavigationDrawerAdapter(String titles[],int icons[]){
        this.mtitles=titles;
        this.micons=icons;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.navigation_row,viewGroup,false);
        ViewHolder viewHolder=new ViewHolder(v,i);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(mtitles[position]);
        holder.imageView.setImageResource(micons[position]);
    }


    @Override
    public int getItemCount() {
        return mtitles.length;
    }

}
