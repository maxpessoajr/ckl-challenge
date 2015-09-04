package io.ckl.challenge.max.entity;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;

import io.ckl.challenge.max.dao.ChallengeDB;
import io.ckl.challenge.max.util.JsonDateConverter;

/**
 *
 * Entity class for Article.
 * Using annotations to define json's fields.
 * Using annotations to define dbflow fields.
 *
 * Created by Max Jr on 03/09/2015.
 */
@JsonObject
@Table(databaseName = ChallengeDB.NAME)
public class Article extends BaseModel {
    @Column
    @PrimaryKey(autoincrement = true)
    private long id;
    @JsonField
    @Column
    private String website;
    @JsonField
    @Column
    private String title;
    @JsonField(name = "image")
    @Column
    private String imageUrl;
    @JsonField
    @Column
    private String content;
    @JsonField
    @Column
    private String authors;
    @JsonField(typeConverter = JsonDateConverter.class)
    @Column
    private Date date;
    @Column
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
    public boolean getRead() {
        return read;
    }

    @Override
    public String toString() {
        return title;
    }

}
