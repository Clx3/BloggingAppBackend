package fi.tuni.bloggingapp.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue
    private long id;

    @Column(name="postDate")
    private LocalDateTime date;

    @Column(name="author")
    private String author;

    @Column(name="content")
    private String content;

    @Column(name="postId")
    private long postId;

    public Comment() {}

    public Comment(String author, String content, long postId) {
        this.date = LocalDateTime.now();
        this.author = author;
        this.content = content;
        this.postId = postId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getPostId(){
        return this.postId;
    }

    public void setPostId(long postId){
        this.postId = postId;
    }
}
