package Atelier;

import java.util.Date;

public class detaySayfa {

    String title;
    String category;
    String link;
    String summary;
    String source;
    String  date;


    public detaySayfa(String title, String category, String summary, String link, String source, String date) {
        this.title = title;
        this.category = category;
        this.summary=summary;
        this.link = link;
        this.source=source;
        this.date=date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }


    public void setCategory(String category) {
        this.category = category;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "\n"+"Haber :\n" +
                "TITLE='" + title + '\'' +"\n" +
                "CATEGORY='" + category + '\''+"\n" +
                "LINK='" + link + '\''+"\n" +
                "SUMMARY='" + summary + '\''+"\n" +
                "SOURCE='" + source + '\''+"\n" +
                "DATE='" + date + '\''+"\n";
    }
}
