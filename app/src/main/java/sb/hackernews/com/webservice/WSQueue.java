package sb.hackernews.com.webservice;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import sb.hackernews.com.App;


/**
 * Created by Nathaniel James Lim on 8/12/2016.
 */
public class WSQueue {

    private Context mContext;
    private RequestQueue mRequestQueue;

    private static WSQueue mInstance = new WSQueue();

    public static synchronized WSQueue getInstance() {
        return mInstance;
    }

    private WSQueue() {
        mContext = App.getAppContext();
        mRequestQueue = getRequestQueue();
    }

    public <T> void addRequest(Request<T> request){
        getRequestQueue().add(request);
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext);
        }
        return mRequestQueue;
    }

    public void cancellAll(){
        getRequestQueue().cancelAll(new RequestQueue.RequestFilter() {
            @Override
            public boolean apply(Request<?> request) {
                return true;
            }
        });
    }




}
