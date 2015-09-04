package io.ckl.challenge.max.task;

import android.os.AsyncTask;
import android.util.Log;

import com.bluelinelabs.logansquare.LoganSquare;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

import io.ckl.challenge.max.callback.DownloadDataCallback;
import io.ckl.challenge.max.entity.Article;

/**
 *
 * Async Task to download the JSON file with the Article's data.
 *
 * Created by Max Jr on 03/09/2015.
 */
public class GetArticles extends AsyncTask<String, Void, List<Article>> {
    private DownloadDataCallback<Article> responder;

    public GetArticles(DownloadDataCallback<Article> responder) {
        super();
        this.responder = responder;
    }

    @Override
    protected List<Article> doInBackground(String... params) {
        try {
            URL urlObj = new URL(params[0]);

            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

            InputStream is = conn.getInputStream();
            List<Article> listFromInputStream = LoganSquare.parseList(is, Article.class);
            is.close();
            conn.disconnect();

            return listFromInputStream;
        } catch(MalformedURLException mue) { //URL
            Log.d(getClass().getName(),"URL Exception: "+mue.getMessage());
        } catch(ProtocolException pe) { //RequestMethod
            Log.d(getClass().getName(),"Protocol Exception: "+pe.getMessage());
        } catch(IOException ioe) { //openConnection, connect
            Log.d(getClass().getName(),"IO Exception: "+ioe.getMessage());
        }

        return null;
    }

    @Override
    protected void onPostExecute(List<Article> result) {
        super.onPostExecute(result);

        if (result==null)
            responder.handleDownloadException();
        else
            responder.saveData(result);
    }

}
