package hsm.yeng.updates;

/**
 * Created by sibi on 15/2/17.
 */

public class NewsDataModel {

    private String newsHead;
    private String newsContent;
    private String newsDate;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNewsHead() {
        return newsHead;
    }

    public void setNewsHead(String newsHead) {
        this.newsHead = newsHead;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public String getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(String newsDate) {
        this.newsDate = newsDate;
    }
}
