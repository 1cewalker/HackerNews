package sb.hackernews.com.webservice;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by Nathaniel James Lim on 8/12/2016.
 */

public abstract class WSTask {

    private static final String URLBASE = "https://hacker-news.firebaseio.com/v0/";
    protected static final String POSTURL = ".json";
    protected String mURL;
    protected WSListener mCallback;

    protected WSTask(WSListener callback){
        mURL = URLBASE;
        mCallback = callback;
    }

    public void start(){

        setUrl();
        StringRequest jsObjRequest = new StringRequest
                (Request.Method.GET, mURL, new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        onSuccess(response);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        onError();

                    }
                });

        WSQueue.getInstance().addRequest(jsObjRequest);
    }

    protected abstract void setUrl();
    protected abstract void onSuccess(String response);
    protected abstract void onError();
}