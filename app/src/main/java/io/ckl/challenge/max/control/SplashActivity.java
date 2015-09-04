package io.ckl.challenge.max.control;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import java.util.List;

import io.ckl.challenge.max.ArticleListActivity;
import io.ckl.challenge.max.R;
import io.ckl.challenge.max.callback.DownloadDataCallback;
import io.ckl.challenge.max.entity.Article;
import io.ckl.challenge.max.task.GetArticles;

/**
 * Created by Max Jr on 03/09/2015.
 */
public class SplashActivity extends Activity implements DownloadDataCallback<Article> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_splash);

        //Download the Articles
        new GetArticles(this).execute(getString(R.string.url_article_data));
    }

    @Override
    public void saveData(List<Article> list) {
        for (Article a : list)
            Log.d("ARTICLE",a.getTitle());

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