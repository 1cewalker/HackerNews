package sb.hackernews.com.screens.comment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import sb.hackernews.com.R;
import sb.hackernews.com.webservice.WSQueue;

public class CommentScreen extends AppCompatActivity {

    private boolean isStart=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_screen);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    protected void onResume() {
        super.onResume();

        if(isStart){
            isStart = false;
//            Bundle args = new Bundle();
//            args.putStringArrayList(CommentList.COMMENTID,getIntent().getStringArrayListExtra(CommentList.COMMENTID));
            CommentList fragment = (CommentList)getSupportFragmentManager().findFragmentById(R.id.fragment_comment_list);
            fragment.startFragmentProcess(getIntent().getStringArrayListExtra(CommentList.COMMENTID));
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                WSQueue.getInstance().cancellAll();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {

        WSQueue.getInstance().cancellAll();

        super.onDestroy();
    }
}
