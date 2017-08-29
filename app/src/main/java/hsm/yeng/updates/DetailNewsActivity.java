package hsm.yeng.updates;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import hsm.yeng.R;

public class DetailNewsActivity extends AppCompatActivity {

    TextView heading;
    TextView content;
    TextView date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        String id=getIntent().getStringExtra("id");
      heading= (TextView) findViewById(R.id.newsH);
        content= (TextView) findViewById(R.id.newsCon);
        content.setMovementMethod(new ScrollingMovementMethod());
        date= (TextView) findViewById(R.id.newsDt);


        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        pDialog.show();
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://www.yengapp.com/getspecificnews/"+id, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                pDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(new String(responseBody));
                    heading.setText(jsonObject.getString("Tittle"));
                    content.setText(jsonObject.getString("News"));
                    date.setText(jsonObject.getString("DisplayDate").substring(0,10));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                pDialog.dismiss();
                Toast.makeText(DetailNewsActivity.this, "Please check your Internet connection", Toast.LENGTH_SHORT).show();
            }
        });


        }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

