package sb.hackernews.com.screens.comment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import sb.hackernews.com.R;
import sb.hackernews.com.utils.Helper;
import sb.hackernews.com.webservice.model.RDItemDetails;

/**
 * Created by Nathaniel James Lim on 29/12/2016.
 */

public class CommentAdapter extends ArrayAdapter<RDItemDetails> {

    private Context mContext;

    static class ViewHolder {
        @BindView(R.id.llPadding)
        LinearLayout llPadding;
        @BindView(R.id.tvUser)
        TextView tvUser;
        @BindView(R.id.tvTime)
        TextView tvTime;
        @BindView(R.id.tvComments)
        TextView tvComments;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public CommentAdapter(Context context) {
        super(context, R.layout.comment_item_row);
        mContext = context;
    }

    public void addData(RDItemDetails detail){
        add(detail);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if(convertView != null){
            holder = (ViewHolder) convertView.getTag();
        } else {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.comment_item_row, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        if(getCount()>0){
            RDItemDetails item = this.getItem(position);

            if(item.mReply) {
                holder.llPadding.setVisibility(View.VISIBLE);
            }else {
                holder.llPadding.setVisibility(View.GONE);
            }
            holder.tvUser.setText(item.mUser);
            holder.tvTime.setText(Helper.getCommentedTime(item.mTime));
            holder.tvComments.setText(fromHtml(item.mText));
        }

        return convertView;
    }

    @SuppressWarnings("deprecation")
    public Spanned fromHtml(String html){
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html,Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }
}
