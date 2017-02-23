package sb.hackernews.com.webservice;

import sb.hackernews.com.webservice.model.RDItemDetails;

/**
 * Created by Nathaniel James Lim on 29/12/2016.
 */

public class WSItemDetail extends WSTask {

    private static final String TASK = "item";
    private String mId;

    public WSItemDetail(String id, WSListener callback) {
        super(callback);

        mId = id;
    }

    @Override
    protected void setUrl() {
        mURL += TASK + "/" + mId + POSTURL;
    }

    @Override
    protected void onSuccess(String response) {
        RDItemDetails rdItemDetails = new RDItemDetails();
        rdItemDetails.parse(response);

        mCallback.onSuccess(rdItemDetails);
    }

    @Override
    protected void onError() {
        mCallback.onError();
    }
}
