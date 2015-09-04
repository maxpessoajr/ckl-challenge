package io.ckl.challenge.max.dao;

import com.raizlabs.android.dbflow.annotation.Database;
import com.raizlabs.android.dbflow.sql.language.Select;

import io.ckl.challenge.max.entity.Article;

/**
 *
 * Basic info of the database (Required for DBFlow)
 *
 * Created by Max Jr on 04/09/2015.
 */
@Database(name = ChallengeDB.NAME, version = ChallengeDB.VERSION)
public abstract class ChallengeDB {

    public static final String NAME = "challengedb";

    public static final int VERSION = 1;

    public static boolean hasData() {
        long c = new Select("id").count().from(Article.class).where().limit(1).count();
        return c!=0;
    }

}