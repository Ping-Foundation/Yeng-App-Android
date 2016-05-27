package hsm.yeng.syllabus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import hsm.yeng.R;

/**
 * Created by Hm on 5/26/2016.
 */
public class SyllabusAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<syllabusDatamodel> mList;
    LayoutInflater minflater;

    SyllabusAdapter(Context context,ArrayList<syllabusDatamodel> list){
        mContext=context;
        mList=list;
        minflater= (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView=minflater.inflate(R.layout.syllabuslistview,null);
        }
        TextView s_code,s_name;
        s_code= (TextView) convertView.findViewById(R.id.subject_code);
        s_name= (TextView) convertView.findViewById(R.id.subject_name);
        s_code.setText(mList.get(position).getSubject_code());
        s_name.setText(mList.get(position).getSubject_name());
        return convertView;
    }
}
