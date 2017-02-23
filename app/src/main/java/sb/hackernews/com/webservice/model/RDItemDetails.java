package sb.hackernews.com.webservice.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Nathaniel James Lim on 29/12/2016.
 */

public class RDItemDetails implements WSResponseData{

    private static final String SCORE = "score";
    private static final String BY = "by";
    private static final String ID = "id";
    private static final String KIDS = "kids";
    private static final String PARENT = "parent";
    private static final String TEXT = "text";
    private static final String TIME = "time";
    private static final String TITLE = "title";
    private static final String TYPE = "type";
    private static final String URL = "url";
    private static final String DESCENDANTS = "descendants";

    public enum ItemType{
        job,
        story,
        comment,
        poll,
        pollopt
    }

    public int mScore;
    public String mUser="";
    public String mId="";
    public ArrayList<String> mChildren = new ArrayList<>();
    public String mParentId="";
    public String mText="";
    public long mTime;
    public String mTitle="";
    public ItemType mType;
    public String mUrl="";
    public int mCommentCount;
    public boolean mReply;


    @Override
    public void parse(String webServiceResponse) {
        JSONObject jsonResponse = null;

        try {
            jsonResponse = new JSONObject(webServiceResponse);
            if(jsonResponse.has(SCORE)){
                mScore = jsonResponse.getInt(SCORE);
            }

            if(jsonResponse.has(BY)){
                mUser = jsonResponse.getString(BY);
            }
            if(jsonResponse.has(ID)){
                mId = String.valueOf(jsonResponse.getLong(ID));
            }
            if(jsonResponse.has(KIDS)){
                JSONArray kidsList = jsonResponse.getJSONArray(KIDS);

                for(int i=0; i<kidsList.length(); i++){
                    mChildren.add(String.valueOf(kidsList.getInt(i)));
                }

            }
            if(jsonResponse.has(PARENT)){
                mParentId = String.valueOf(jsonResponse.getLong(PARENT));
            }
            if(jsonResponse.has(TEXT)){
                mText = jsonResponse.getString(TEXT);
            }
            if(jsonResponse.has(TIME)){
                mTime = jsonResponse.getLong(TIME);
            }
            if(jsonResponse.has(TITLE)){
                mTitle = jsonResponse.getString(TITLE);
            }
            if(jsonResponse.has(TYPE)){
                mType = ItemType.valueOf(jsonResponse.getString(TYPE));
            }
            if(jsonResponse.has(URL)){
                mUrl = jsonResponse.getString(URL);
            }

            if(jsonResponse.has(DESCENDANTS)){
                mCommentCount = jsonResponse.getInt(DESCENDANTS);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
