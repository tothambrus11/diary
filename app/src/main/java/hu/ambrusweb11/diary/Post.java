package hu.ambrusweb11.diary;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "posts")
public class Post {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String date;
    private String content;
    private String title;

    public Post(String date, String content, String title) {
        this.date = date;
        this.content = content;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "\n\ntitle: \"" + title + "\";\nid: \"" + id + "\" \ncontent: \"" + content + "\" \ndate: \"" + date + "\"";
    }
}
