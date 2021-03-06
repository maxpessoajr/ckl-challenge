package io.ckl.challenge.max.control;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import java.util.List;

import io.ckl.challenge.max.ArticleListActivity;
import io.ckl.challenge.max.R;
import io.ckl.challenge.max.callback.DownloadDataCallback;
import io.ckl.challenge.max.dao.ArticleDAO;
import io.ckl.challenge.max.dao.ChallengeDB;
import io.ckl.challenge.max.entity.Article;
import io.ckl.challenge.max.task.GetArticles;

/**
 *
 * Splash screen. Verifies if the data exist. Downloads the data if necessary.
 *
 * Created by Max Jr on 03/09/2015.
 */
public class SplashActivity extends Activity implements DownloadDataCallback<Article> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_splash);

        //If the database is empty then Download the Articles
        if (!ChallengeDB.hasData())
            new GetArticles(this).execute(getString(R.string.url_article_data));
        else {
            Intent main = new Intent(this, ArticleListActivity.class);
            startActivity(main);
            finish();
        }
    }

    @Override
    public void saveData(List<Article> list) {
        ArticleDAO.getInstance().saveModelList(list);

        Intent main = new Intent(this, ArticleListActivity.class);
        startActivity(main);
        finish();
    }

    @Override
    public void handleDownloadException() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dialog_title_download_error);
        builder.setMessage(R.string.dialog_message_download_error);
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.button_try_again, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                new GetArticles(SplashActivity.this).execute(getString(R.string.url_article_data));
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(R.string.button_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                finish();
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.setCanceledOnTouchOutside(false);
        alert.show();
    }

}