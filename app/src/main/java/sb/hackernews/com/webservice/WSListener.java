package sb.hackernews.com.webservice;

import sb.hackernews.com.webservice.model.WSResponseData;

/**
 * Created by Nathaniel James Lim on 8/12/2016.
 */

public interface WSListener {

    public void onSuccess(WSResponseData data);
    public void onError();

}
