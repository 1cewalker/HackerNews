package sb.hackernews.com;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import sb.hackernews.com.utils.Helper;
import sb.hackernews.com.webservice.model.RDItemDetails;
import sb.hackernews.com.webservice.model.RDTopNews;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTest {

    @Test
    public void checkTopNewsParser() throws Exception {

        String response = "[13272840,13272610,13274562,13273461,13274064,13272521,13274931,13273901,13274545,13274512,13271588,13271844,13273960,13269990,13273770,13273668,13272112,13274192,13273530,13273437,13271650,13271887,13272561,13271522,13258826,13273658,13273626,13272254,13270979,13271833,13269761,13270346,13271525,13271934,13273776,13270556,13271586,13271108,13271220,13273490,13274753,13272057,13272195,13271183,13274642,13273838,13270121,13274806,13269974,13268580,13269272,13271535,13268781,13273168,13274646,13264054,13273106,13271884,13268692,13268447,13270027,13272951,13272935,13273410,13268335,13270116,13272532,13273449,13267108,13268288,13272474,13270328,13271346,13264952,13274304,13266695,13269687,13273744,13268328,13270418,13271359,13265758,13263038,13269818,13264258,13271641,13270665,13263894,13268309,13274107,13266599,13264136,13258029,13266845,13271048,13270917,13270946,13266664,13265959,13264850,13269533,13259760,13260239,13266238,13270435,13259399,13263897,13273404,13261849,13268145,13264064,13267883,13262752,13266265,13259429,13260688,13267652,13268673,13267724,13272297,13263936,13257970,13264102,13266656,13260184,13257252,13264784,13269630,13258631,13266386,13267298,13259000,13259880,13264296,13272495,13258142,13263124,13271424,13266186,13272414,13259504,13266598,13255910,13263765,13258078,13258037,13271125,13265376,13261324,13267531,13271250,13271330,13272076,13254401,13262953,13271981,13254520,13260284,13270652,13264079,13257683,13256092,13264383,13261218,13254342,13271077,13259944,13262504,13268284,13260887,13271483,13264027,13258039,13271370,13268263,13261646,13271323,13260293,13256962,13263119,13254262,13258056,13261656,13259424,13269899,13260234,13258636,13258155,13254029,13265256,13258254,13266132,13259435,13268786,13259686,13259675,13262591,13263541,13260476,13265751,13269236,13268701,13256222,13257424,13257204,13262586,13262285,13268079,13266506,13269289,13262768,13265474,13257345,13258063,13254871,13263968,13265881,13259438,13261128,13266930,13256676,13269435,13261486,13264464,13256709,13256098,13265453,13264649,13256330,13269864,13260569,13259590,13256606,13257866,13271174,13266913,13255159,13262042,13256872,13255650,13267244,13268344,13259449,13268861,13266247,13255581,13258925,13256285,13270543,13255021,13263153,13261300,13260266,13261234,13256142,13258104,13262544,13256666,13266873,13267966,13257199,13255409,13258615,13269098,13260728,13262196,13269050,13255073,13268175,13264001,13268133,13254797,13261738,13258247,13255868,13257889,13264393,13255433,13261275,13258476,13263606,13268944,13255149,13254051,13265990,13254762,13260302,13256406,13265723,13254195,13263945,13268272,13254438,13258127,13259467,13254983,13254249,13267979,13265216,13269980,13258402,13264734,13257981,13257484,13255215,13255200,13255199,13253932,13260487,13259644,13267133,13263434,13267056,13268795,13260194,13267763,13255125,13258635,13256714,13261829,13259106,13263745,13261663,13266346,13256266,13259133,13263650,13263604,13266210,13263136,13262338,13260789,13263402,13259415,13270145,13260722,13264083,13256921,13258882,13254006,13255676,13262659,13258234,13254354,13256778,13264045,13254579,13255293,13267238,13255400,13256640,13254993,13256492,13262986,13260321,13255465,13254378,13258599,13262524,13271033,13260527,13262276,13260747,13260580,13255255,13259437,13260357,13258658,13256911,13260213,13258571,13258812,13256488,13258095,13267156,13261065,13261046,13258803,13267688,13265462,13256309,13264237,13255801,13256191,13255739,13257455,13262016,13257244,13256289,13256159,13255405,13257823,13255261,13254769,13255040,13266072,13254902,13254108,13260112,13255903,13270753,13253919,13270573,13268768,13269930,13271544,13271820,13271678,13272102,13253988,13270271,13271021,13264205,13255334,13254593,13255900]";

        RDTopNews rdTopNews = new RDTopNews();
        rdTopNews.parse(response);

        assertEquals(416,rdTopNews.mNewsIdList.size());
        assertEquals("13272840",rdTopNews.mNewsIdList.get(0));
        assertEquals("13272610",rdTopNews.mNewsIdList.get(1));
        assertEquals("13254593",rdTopNews.mNewsIdList.get(414));
        assertEquals("13255900",rdTopNews.mNewsIdList.get(415));

    }

    //Story
    @Test
    public void checkItemDetailParser1() throws Exception {

        String response = getTestResponse("item13272840.json");

        RDItemDetails rdItemDetails = new RDItemDetails();
        rdItemDetails.parse(response);

        assertEquals("leeny",rdItemDetails.mUser);
        assertEquals("13272840",rdItemDetails.mId);
        assertEquals(35,rdItemDetails.mChildren.size());
        assertEquals(1482952639,rdItemDetails.mTime);
        assertEquals("Lessons from 3,000 technical interviews",rdItemDetails.mTitle);
        assertEquals("http://blog.interviewing.io/lessons-from-3000-technical-interviews/",rdItemDetails.mUrl);
        assertEquals(RDItemDetails.ItemType.story,rdItemDetails.mType);

    }

    //Time Ago
    @Test
    public void checkTimeParser() throws Exception {

        System.out.println(Helper.getCommentedTime(1482950886));
    }

    //Comments
    @Test
    public void checkItemDetailParser2() throws Exception {

        String response = getTestResponse("item13273768.json");

        RDItemDetails rdItemDetails = new RDItemDetails();
        rdItemDetails.parse(response);

        assertEquals("forrestbrazeal",rdItemDetails.mUser);
        assertEquals("13273768",rdItemDetails.mId);
        assertEquals(6,rdItemDetails.mChildren.size());
        assertEquals("13272840",rdItemDetails.mParentId);
        assertEquals("The author draws a hard distinction between Udacity&#x2F;Coursera MOOCs (good) and traditional master&#x27;s degrees (bad). I&#x27;ll interject that with Georgia Tech&#x27;s Online Master&#x27;s in Computer Science program [0], which is delivered via Udacity and insanely cheap [1], you can get the best of both! (Their &quot;Computability, Complexity and Algorithms&quot; class is one of the top Udacity courses cited in the article.)<p>Keep in mind that a traditional degree program does have a huge advantage over a strict MOOC: accountability. It sounds good to say that anybody can go push themselves through one of these courses. Try pushing yourself through ten, and actually writing all the papers and implementing all the code, while working full time and having a family. That grade looming at the end of the semester really does wonders for your motivation. Plus you can get help from live professors and TAs, and the Piazza forums for OMSCS are full of smart, curious students who love talking about the subject at hand. There&#x27;s a richness to the degree experience that I don&#x27;t think you get with scattered classes.<p>(Obvious disclaimer: I&#x27;m a current OMSCS student)<p>[0] <a href=\"http:&#x2F;&#x2F;omscs.gatech.edu\" rel=\"nofollow\">http:&#x2F;&#x2F;omscs.gatech.edu</a>\n[1] <a href=\"https:&#x2F;&#x2F;www.omscs.gatech.edu&#x2F;program-info&#x2F;cost-payment-schedule\" rel=\"nofollow\">https:&#x2F;&#x2F;www.omscs.gatech.edu&#x2F;program-info&#x2F;cost-payment-sched...</a>",rdItemDetails.mText);
        assertEquals(1482960067,rdItemDetails.mTime);
        assertEquals(RDItemDetails.ItemType.comment,rdItemDetails.mType);

    }

    private String getTestResponse(String file){
        return getStringFromInputStream(getFileFromPath(this,file));
    }

    private InputStream getFileFromPath(Object obj, String fileName) {
        ClassLoader classLoader = obj.getClass().getClassLoader();
        return classLoader.getResourceAsStream(fileName);
    }

    private String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }
}