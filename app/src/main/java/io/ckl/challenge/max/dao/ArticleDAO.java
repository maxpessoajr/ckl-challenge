package io.ckl.challenge.max.dao;

import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.List;

import io.ckl.challenge.max.entity.Article;
import io.ckl.challenge.max.entity.Article$Table;

/**
 *
 * Provides data access to Article's table, like specific "select" functions.
 *
 * Created by Max Jr on 04/09/2015.
 */
public class ArticleDAO extends BaseDAO<Article> {
    private static ArticleDAO instance = new ArticleDAO();

    private ArticleDAO() {
    }

    public static ArticleDAO getInstance() {
        return instance;
    }

    public Article selectById(long id) {
        return new Select().all().from(Article.class).where(Condition.column(Article$Table.ID).eq(id)).querySingle();
    }

    public List<Article> selectAll(String orderBy) {
        return new Select(Article$Table.ID,Article$Table.TITLE,Article$Table.DATE,Article$Table.AUTHORS,Article$Table.IMAGEURL).from(Article.class).where().orderBy(true, orderBy).queryList();
    }

}
