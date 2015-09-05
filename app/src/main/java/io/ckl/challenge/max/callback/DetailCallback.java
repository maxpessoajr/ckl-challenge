package io.ckl.challenge.max.callback;

/**
 *
 * A callback interface that all activities containing the List Fragment must
 * implement. This mechanism allows activities to be notified of item
 * selections.
 *
 * Created by Max Jr on 04/09/2015.
 */
public interface DetailCallback {

    /**
     * Callback for when an item has been selected.
     */
    void onItemSelected(long id);

}