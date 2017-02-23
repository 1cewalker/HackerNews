package sb.hackernews.com.webservice.model;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by Nathaniel James Lim on 29/12/2016.
 */

public class RDTopNews implements WSResponseData{

    public ArrayList<String> mNewsIdList;

    @Override
    public void parse(String webServiceResponse) {

        mNewsIdList = new ArrayList<>();

        try {
            JSONArray idList = new JSONArray(webServiceResponse);
            for(int i=0; i<idList.length();i++){
                mNewsIdList.add(String.valueOf(idList.getInt(i)));
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
    }
}
