package io.ckl.challenge.max.callback;

import java.util.List;

/**
 *
 * Basic methods to use the Download Async Task
 *
 * Created by Max Jr on 03/09/2015.
 */
public interface DownloadDataCallback<E> {

    void saveData(List<E> list);

    void handleDownloadException();

}
