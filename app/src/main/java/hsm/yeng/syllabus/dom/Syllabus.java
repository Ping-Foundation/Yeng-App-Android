package hsm.yeng.syllabus.dom;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by nikhil on 28/8/17.
 */

public class Syllabus {
    @SerializedName("files")
    private ArrayList<String> files;
    @SerializedName("children")
    private ArrayList<String> childrens;
    Syllabus(){
        files=new ArrayList<>();
        childrens=new ArrayList<>();
    }

    public ArrayList<String> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<String> files) {
        this.files = files;
    }

    public ArrayList<String> getChildrens() {
        return childrens;
    }

    public void setChildrens(ArrayList<String> childrens) {
        this.childrens = childrens;
    }
}
