package hsm.yeng.syllabus;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import hsm.yeng.R;
import hsm.yeng.syllabus.dom.Syllabus;

/**
 * Created by nikhil on 28/8/17.
 */

public class SyllabusAdapter extends RecyclerView.Adapter<SyllabusAdapter.SyllabusHolder> {

    private Syllabus syllabus;
    private Context context;
    private int rowLayout;
    public SyllabusAdapter(Syllabus syllabus, Context context,int rowLayout){
        this.syllabus=syllabus;
        this.context=context;
        this.rowLayout=rowLayout;
    }

    @Override
    public SyllabusHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(rowLayout,parent,false);
        return new SyllabusHolder(view);
    }

    @Override
    public void onBindViewHolder(SyllabusHolder holder, int position) {
        String title="";
        if(position>=syllabus.getChildrens().size()){
            title=syllabus.getFiles().get(position);
            holder.itemIcon.setImageResource(R.mipmap.files);
        }else{
            title=syllabus.getChildrens().get(position);
            holder.itemIcon.setImageResource(R.mipmap.folder);
        }
        if(title.contains("_")){
            String title_split[]=title.split("_");
            title=title_split[(title_split.length-1)];
        }
        holder.itemTitle.setText(title);
    }

    @Override
    public int getItemCount() {
        int size=(syllabus.getChildrens().size()+syllabus.getFiles().size());
        return size;
    }

    public static class SyllabusHolder extends RecyclerView.ViewHolder{
        TextView itemTitle;
        ImageView itemIcon;
        public SyllabusHolder(View itemView) {
            super(itemView);
            itemTitle=(TextView)itemView.findViewById(R.id.syllabus_item_title);
            itemIcon=(ImageView)itemView.findViewById(R.id.syllabus_item_icon);
        }
    }
}
