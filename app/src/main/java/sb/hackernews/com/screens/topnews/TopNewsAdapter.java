package sb.hackernews.com.screens.topnews;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import sb.hackernews.com.R;
import sb.hackernews.com.utils.Helper;
import sb.hackernews.com.webservice.model.RDItemDetails;

/**
 * Created by Nathaniel James Lim on 29/12/2016.
 */

public class TopNewsAdapter extends ArrayAdapter<RDItemDetails> {

    private Context mContext;

    static class ViewHolder {
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvURL)
        TextView tvURL;
        @BindView(R.id.tvMoreInfo)
        TextView tvMoreInfo;
        @BindView(R.id.tvComments)
        TextView tvCommentCount;
        @BindView(R.id.btnLink)
        Button btnLink;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public TopNewsAdapter(Context context) {
        super(context, R.layout.news_item_row);
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if(convertView != null){
            holder = (ViewHolder) convertView.getTag();
        } else {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.news_item_row, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        if(getCount()>0){
            final RDItemDetails item = this.getItem(position);

            holder.tvTitle.setText(item.mTitle);
            holder.tvURL.setText(item.mUrl);
            holder.tvMoreInfo.setText(getMoreInfo(item));
            holder.tvCommentCount.setText(item.mCommentCount + " " + mContext.getString(R.string.comments));
            holder.btnLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.mUrl));
                    mContext.startActivity(browserIntent);
                }
            });
        }

        return convertView;
    }

    private String getMoreInfo(RDItemDetails item){
        return String.format("%s "+mContext.getString(R.string.points_by)+" %s %s",
                item.mScore,item.mUser, Helper.getCommentedTime(item.mTime));
    }
}
