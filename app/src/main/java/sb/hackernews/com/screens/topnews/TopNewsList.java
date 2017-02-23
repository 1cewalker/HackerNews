package sb.hackernews.com.screens.topnews;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.ArrayList;

import sb.hackernews.com.screens.comment.CommentList;
import sb.hackernews.com.screens.comment.CommentScreen;
import sb.hackernews.com.webservice.WSItemDetail;
import sb.hackernews.com.webservice.WSListener;
import sb.hackernews.com.webservice.WSQueue;
import sb.hackernews.com.webservice.WSTask;
import sb.hackernews.com.webservice.WSTopNews;
import sb.hackernews.com.webservice.model.RDItemDetails;
import sb.hackernews.com.webservice.model.RDTopNews;
import sb.hackernews.com.webservice.model.WSResponseData;

/**
 * Created by Nathaniel James Lim on 28/12/2016.
 */

public class TopNewsList extends ListFragment implements AbsListView.OnScrollListener {

    private TopNewsAdapter mAdapter;
    private ArrayList<String> mTopNewsIdList;
    private PullRefreshCallback mRefreshCallback;
    private int mCurrentIndex = 0;
    private int mMaxLoadCount = 0;
    private int mRequestCount = 10;

    private int mOffset = 5;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mAdapter = new TopNewsAdapter(getActivity());
        setListAdapter(mAdapter);
        setRetainInstance(true);
        getListView().setOnScrollListener(this);

        retrieveTopNewsIDList();
    }

    public void setPullRefreshCallback(PullRefreshCallback callback){
        mRefreshCallback = callback;
    }

    public void pullRefresh(){

        WSQueue.getInstance().cancellAll();
        retrieveTopNewsIDList();
    }

    private void retrieveTopNewsIDList(){

        setListShown(false);
        initializeCounter();

        WSTask task = new WSTopNews(new WSListener() {
            @Override
            public void onSuccess(WSResponseData data) {
                RDTopNews rdTopNews = (RDTopNews)data;
                mTopNewsIdList = rdTopNews.mNewsIdList;
                startRetrieveNewsDetail();
                if(mRefreshCallback!=null){
                    mRefreshCallback.updateDone();
                }
            }

            @Override
            public void onError() {

            }
        });
        task.start();
    }

    private void initializeCounter(){
        mCurrentIndex = 0;
        mMaxLoadCount = 0;
        mAdapter.clear();
    }

    private void increaseMaxCount(){
        mMaxLoadCount += mRequestCount;
    }

    private void startRetrieveNewsDetail(){
        increaseMaxCount();
        retrieveNewsDetail();
    }

    private void retrieveNewsDetail(){
       if(mCurrentIndex< mMaxLoadCount && mCurrentIndex<mTopNewsIdList.size()){

            WSTask task = new WSItemDetail(mTopNewsIdList.get(mCurrentIndex++),new WSListener(){

                @Override
                public void onSuccess(WSResponseData data) {
                    RDItemDetails rdItemDetails = (RDItemDetails) data;
                    mAdapter.add(rdItemDetails);
                    retrieveNewsDetail();
                }

                @Override
                public void onError() {

                }
            });
            task.start();
        } else {
           setListShown(true);
       }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(getActivity(),CommentScreen.class);
        intent.putExtra(CommentList.COMMENTID,mAdapter.getItem(position).mChildren);
        startActivity(intent);
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                         int totalItemCount) {

        setRefreshState();

        int position = firstVisibleItem+visibleItemCount;
        int limit = totalItemCount - mOffset;

        // Check if bottom has been reached
        if (position >= limit && totalItemCount > 0 && mAdapter.getCount()== mMaxLoadCount) {
            startRetrieveNewsDetail();
        }
    }

    private void setRefreshState(){
        boolean enable = false;
        if(getListView() != null && getListView().getChildCount() > 0){
            // check if the first item of the list is visible
            boolean firstItemVisible = getListView().getFirstVisiblePosition() == 0;
            // check if the top of the first item is visible
            boolean topOfFirstItemVisible = getListView().getChildAt(0).getTop() == 0;
            // enabling or disabling the refresh layout
            enable = firstItemVisible && topOfFirstItemVisible;
        }
        mRefreshCallback.setEnable(enable);
    }
}
