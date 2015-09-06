package io.ckl.challenge.max;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import io.ckl.challenge.max.callback.DetailCallback;
import io.ckl.challenge.max.control.AboutActivity;

/**
 * An activity representing a list of Articles. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ArticleDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p/>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link ArticleListFragment} and the item details
 * (if present) is a {@link ArticleDetailFragment}.
 * <p/>
 * This activity also implements the required
 * {@link DetailCallback} interface
 * to listen for item selections.
 */
public class ArticleListActivity extends Activity implements DetailCallback {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    private ArticleListFragment listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);

        this.listFragment = ((ArticleListFragment) getFragmentManager().findFragmentById(R.id.article_list));

        if (findViewById(R.id.article_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            listFragment.setActivateOnItemClick(true);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu items for use in the actionbar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the actionbar items
        switch (item.getItemId()) {
            case R.id.actionSort:
                View menuItemView = findViewById(item.getItemId());
                PopupMenu popupMenu = new PopupMenu(this, menuItemView);
                popupMenu.setOnMenuItemClickListener(listFragment);
                popupMenu.inflate(R.menu.context_sort);
                popupMenu.show();
                break;
            case R.id.actionAbout:
                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    /**
     * Callback method from {@link DetailCallback}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(long id) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putLong(ArticleDetailFragment.ARG_ITEM_ID, id);
            ArticleDetailFragment fragment = new ArticleDetailFragment();
            fragment.setArguments(arguments);
            getFragmentManager().beginTransaction()
                    .replace(R.id.article_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, ArticleDetailActivity.class);
            detailIntent.putExtra(ArticleDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }

}
