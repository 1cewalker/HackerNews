package sb.hackernews.com.webservice;

import sb.hackernews.com.webservice.model.RDTopNews;

/**
 * Created by Nathaniel James Lim on 29/12/2016.
 */

public class WSTopNews extends WSTask {

    private static final String TASK = "topstories";

    public WSTopNews(WSListener callback) {
        super(callback);
    }

    @Override
    protected void setUrl() {
        mURL += TASK + POSTURL;
    }

    @Override
    protected void onSuccess(String response) {
        RDTopNews topNews = new RDTopNews();
        topNews.parse(response);

        mCallback.onSuccess(topNews);
    }

    @Override
    protected void onError() {
        mCallback.onError();
    }
}
