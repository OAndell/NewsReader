import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Oscar on 2016-06-30.
 */
public class RedditScraper {

    private String address;
    private List<String> outList = new ArrayList<String>();
    private List<String> linkList = new ArrayList<String>();
    private String outputString = "";

    public RedditScraper(String subbreddit){
        this.address = "https://www.reddit.com/r/" + subbreddit;

    }

    public boolean scrape(){
        try {
            Document doc = Jsoup.connect(address)
                    .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.21 (KHTML, like Gecko) Chrome/19.0.1042.0 Safari/535.21")
                    .timeout(10000)
                    .get();
            doc.select("br").append("\\n");
            doc.select("p").prepend("\\n\\n");
            Elements content = doc.getElementsByClass("title");
            Elements links = doc.getElementsByClass("first").select("a[href]");
            for (Element link : links) {
                linkList.add(link.attr("href"));
            }
           for (int i = 0; i < content.select("a").size(); i++){
               if((i%2)==0){ //if even
                   outList.add(content.select("a").get(i).text()); //Saves the headlines in a list format
                   outputString = outputString +content.select("a").get(i).text() + "\n" + "\n";
               }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<String> getOutList(){
        return outList;
    }

    public List<String> getLinkList(){
        return linkList;
    }
}
