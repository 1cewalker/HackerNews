package sb.hackernews.com.screens.topnews;

/**
 * Created by Nathaniel James Lim on 29/12/2016.
 */

public interface PullRefreshCallback {

    public void updateDone();
    public void setEnable(boolean isEnable);
}
