package io.ckl.challenge.max;

import android.app.ListFragment;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.PopupMenu;

import java.util.List;

import io.ckl.challenge.max.callback.DetailCallback;
import io.ckl.challenge.max.control.ArticleItemAdapter;
import io.ckl.challenge.max.dao.ArticleDAO;
import io.ckl.challenge.max.entity.Article;
import io.ckl.challenge.max.entity.Article$Table;

/**
 * A list fragment representing a list of Articles. This fragment
 * also supports tablet devices by allowing list items to be given an
 * 'activated' state upon selection. This helps indicate which item is
 * currently being viewed in a {@link ArticleDetailFragment}.
 * <p/>
 * Activities containing this fragment MUST implement the {@link DetailCallback}
 * interface.
 */
public class ArticleListFragment extends ListFragment implements PopupMenu.OnMenuItemClickListener {
    private int currentSort;

    /**
     * The serialization (saved instance state) Bundle key representing the
     * activated item position. Only used on tablets.
     */
    private static final String STATE_ACTIVATED_POSITION = "activated_position";

    /**
     * The fragment's current callback object, which is notified of list item
     * clicks.
     */
    private DetailCallback detailCallback;

    /**
     * The current activated item position. Only used on tablets.
     */
    private int mActivatedPosition = ListView.INVALID_POSITION;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ArticleListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.detailCallback = (DetailCallback)getActivity();

        this.currentSort = R.id.contextSortTitle;
        List<Article> list = ArticleDAO.getInstance().selectAll(Article$Table.TITLE);

        setListAdapter(new ArticleItemAdapter(getActivity(),list));
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Restore the previously serialized activated item position.
        if (savedInstanceState != null
                && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
            setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION));
        }
    }

    /*@Override
    public void onAttach(Context activity) {
        super.onAttach(activity);

        // Activities containing this fragment must implement its callbacks.
        if (!(activity instanceof DetailCallback)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        }

        this.detailCallback = (DetailCallback) activity;
    } */

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);

        // Notify the active callbacks interface (the activity, if the
        // fragment is attached to one) that an item has been selected.
        Article article = (Article)getListAdapter().getItem(position);
        detailCallback.onItemSelected(article.getId());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mActivatedPosition != ListView.INVALID_POSITION) {
            // Serialize and persist the activated item position.
            outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
        }
    }

    /**
     * Turns on activate-on-click mode. When this mode is on, list items will be
     * given the 'activated' state when touched.
     */
    public void setActivateOnItemClick(boolean activateOnItemClick) {
        // When setting CHOICE_MODE_SINGLE, ListView will automatically
        // give items the 'activated' state when touched.
        getListView().setChoiceMode(activateOnItemClick
                ? ListView.CHOICE_MODE_SINGLE
                : ListView.CHOICE_MODE_NONE);
    }

    private void setActivatedPosition(int position) {
        if (position == ListView.INVALID_POSITION) {
            getListView().setItemChecked(mActivatedPosition, false);
        } else {
            getListView().setItemChecked(position, true);
        }

        mActivatedPosition = position;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (this.currentSort!=item.getItemId()) {
            List<Article> list = null;
            this.currentSort = item.getItemId();
            switch (item.getItemId()) {
                case R.id.contextSortTitle:
                     list = ArticleDAO.getInstance().selectAll(Article$Table.TITLE);
                    break;
                case R.id.contextSortAuthor:
                    list = ArticleDAO.getInstance().selectAll(Article$Table.AUTHORS);
                    break;
                case R.id.contextSortDate:
                    list = ArticleDAO.getInstance().selectAll(Article$Table.DATE);
                    break;
                case R.id.contextSortWebsite:
                    list = ArticleDAO.getInstance().selectAll(Article$Table.WEBSITE);
                    break;
                default:
                    return super.onContextItemSelected(item);
            }
            setListAdapter(new ArticleItemAdapter(getActivity(), list));
        }
        Snackbar.make(getListView(), R.string.snackbar_message_done, Snackbar.LENGTH_SHORT).show();
        return true;
    }

}
