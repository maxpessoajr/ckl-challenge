package io.ckl.challenge.max.entity;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.util.Date;

import io.ckl.challenge.max.util.JsonDateConverter;

/**
 *
 * Entity class for Article.
 * Using annotations to define json's fields.
 *
 * Created by Max Jr on 03/09/2015.
 */
@JsonObject
public class Article {

    private long id;
    @JsonField
    private String website;
    @JsonField
    private String title;
    @JsonField(name = "image")
    private String imageUrl;
    @JsonField
    private String content;
    @JsonField
    private String authors;
    @JsonField(typeConverter = JsonDateConverter.class)
    private Date date;
    private boolean read;

    public Article() {
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setWebsite(String website) {
        this.website = website;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setAuthors(String authors) {
        this.authors = authors;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public void setRead(boolean read) {
        this.read = read;
    }

    public long getId() {
        return id;
    }
    public String getWebsite() {
        return website;
    }
    public String getTitle() {
        return title;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public String getContent() {
        return content;
    }
    public String getAuthors() {
        return authors;
    }
    public Date getDate() {
        return date;
    }
    public boolean isRead() {
        return read;
    }

    @Override
    public String toString() {
        return title;
    }

}
