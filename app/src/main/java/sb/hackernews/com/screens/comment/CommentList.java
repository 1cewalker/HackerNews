package sb.hackernews.com.screens.comment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.widget.AbsListView;

import java.util.ArrayList;

import sb.hackernews.com.webservice.WSItemDetail;
import sb.hackernews.com.webservice.WSListener;
import sb.hackernews.com.webservice.WSTask;
import sb.hackernews.com.webservice.model.RDItemDetails;
import sb.hackernews.com.webservice.model.WSResponseData;

/**
 * Created by Nathaniel James Lim on 28/12/2016.
 */

public class CommentList extends ListFragment implements AbsListView.OnScrollListener {

    public static final String COMMENTID="COMMENT_ID_LIST";

    private CommentAdapter mAdapter;
    private ArrayList<String> mCommentList;
    private ArrayList<ArrayList<String>> mReplyList = new ArrayList<>();

    private int mCurrentIndex = 0;
    private int mCurrentReplyIndex=0;
    private int mMaxLoadCount = 0;
    private int mRequestCount = 10;
    private int mReplyLimit = 1;

    private int mOffset = 5;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mAdapter = new CommentAdapter(getActivity());
        setListAdapter(mAdapter);
        setRetainInstance(true);
        getListView().setOnScrollListener(this);
    }

    public void startFragmentProcess(ArrayList<String> commentIdList){

        mCommentList = commentIdList;
        setListShown(false);
        startRetrieveCommentItem();
    }

    private void increaseMaxCount(){
        mMaxLoadCount += mRequestCount;
    }

    private void startRetrieveCommentItem(){
        increaseMaxCount();
        retrieveCommentItem();
    }


    private void retrieveCommentItem(){

        if(mCurrentIndex<mMaxLoadCount && mCurrentIndex<mCommentList.size()){
            WSTask task = new WSItemDetail(mCommentList.get(mCurrentIndex), new WSListener() {
                @Override
                public void onSuccess(WSResponseData data) {
                    RDItemDetails rdItemDetails = (RDItemDetails) data;
                    rdItemDetails.mReply=false;
                    mAdapter.addData(rdItemDetails);
                    mReplyList.add(rdItemDetails.mChildren);
                    mCurrentReplyIndex=0;
                    retrieveReplyItem();
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

    private void retrieveReplyItem(){
        if(mCurrentReplyIndex<mReplyList.get(mCurrentIndex).size() &&
                mCurrentReplyIndex<mReplyLimit){
            WSTask task = new WSItemDetail(mReplyList.get(mCurrentIndex).get(mCurrentReplyIndex++), new WSListener() {
                @Override
                public void onSuccess(WSResponseData data) {
                    RDItemDetails rdItemDetails = (RDItemDetails) data;
                    rdItemDetails.mReply=true;
                    mAdapter.addData(rdItemDetails);
                    retrieveReplyItem();
                }

                @Override
                public void onError() {

                }
            });
            task.start();
        } else {
            mCurrentIndex++;
            retrieveCommentItem();
        }

    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                         int totalItemCount) {
        int position = firstVisibleItem+visibleItemCount;
        int limit = totalItemCount - mOffset;

        // Check if bottom has been reached
        if (position >= limit && totalItemCount > 0 && mCurrentIndex== mMaxLoadCount) {
            startRetrieveCommentItem();
        }
    }
}
