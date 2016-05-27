package hsm.yeng.util;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by nikhil on 23/8/15.
 */
public class Util {
    public String loadJSONFromAsset(Context context,String filename) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
