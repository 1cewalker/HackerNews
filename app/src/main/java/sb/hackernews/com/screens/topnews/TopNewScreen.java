package sb.hackernews.com.screens.topnews;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import butterknife.BindView;
import butterknife.ButterKnife;
import sb.hackernews.com.R;
import sb.hackernews.com.webservice.WSQueue;

public class TopNewScreen extends AppCompatActivity{

//    @BindView(R.id.fragment_news_list)
    TopNewsList lfNewsList;

    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout srlSwipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_news);
        ButterKnife.bind(this);

        lfNewsList = (TopNewsList)getSupportFragmentManager().findFragmentById(R.id.fragment_news_list);
        lfNewsList.setPullRefreshCallback(new PullRefreshCallback() {
            @Override
            public void updateDone() {
                srlSwipeRefresh.setRefreshing(false);
            }

            @Override
            public void setEnable(boolean isEnable) {
                srlSwipeRefresh.setEnabled(isEnable);
            }
        });
        srlSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                lfNewsList.pullRefresh();
            }
        });

    }

    @Override
    protected void onDestroy() {

        WSQueue.getInstance().cancellAll();

        super.onDestroy();
    }




}
