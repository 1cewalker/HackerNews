package sb.hackernews.com;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import sb.hackernews.com.webservice.WSTopNews;
import sb.hackernews.com.webservice.WSListener;
import sb.hackernews.com.webservice.WSTask;
import sb.hackernews.com.webservice.model.RDTopNews;
import sb.hackernews.com.webservice.model.WSResponseData;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class WebServiceTest {
    RDTopNews mTopNews;

    @Test
    public void checkRetrieveTopNews() throws Exception {

        final Object mLock = new Object();

        WSTask task = new WSTopNews(new WSListener() {
            @Override
            public void onSuccess(WSResponseData data) {

                mTopNews = (RDTopNews) data;
                synchronized (mLock) {
                    mLock.notify();
                }
            }

            @Override
            public void onError() {
                synchronized (mLock) {
                    mLock.notify();
                }
            }
        });
        task.start();

        synchronized (mLock) {
            mLock.wait();
        }

        assertTrue(0 < mTopNews.mNewsIdList.size());
    }


}