package io.ckl.challenge.max;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import io.ckl.challenge.max.dao.ArticleDAO;
import io.ckl.challenge.max.entity.Article;
import io.ckl.challenge.max.util.JsonDateConverter;

/**
 * A fragment representing a single Article detail screen.
 * This fragment is either contained in a {@link ArticleListActivity}
 * in two-pane mode (on tablets) or a {@link ArticleDetailActivity}
 * on handsets.
 */
public class ArticleDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The Article content this fragment is presenting.
     */
    private Article mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ArticleDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the Article content specified by the fragment
            // arguments.
            mItem = ArticleDAO.getInstance().selectById(getArguments().getLong(ARG_ITEM_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_article_detail, container, false);

        // Show the Article content
        if (mItem != null) {
            Picasso.with(getActivity()).load(mItem.getImageUrl()).error(R.drawable.ckl_logo).placeholder(R.drawable.ckl_logo).into(((ImageView) rootView.findViewById(R.id.articleImageDetail)));

            ((TextView) rootView.findViewById(R.id.articleTitleDetail)).setText(mItem.getTitle());
            ((TextView) rootView.findViewById(R.id.articleAuthorDetail)).setText(mItem.getAuthors());
            ((TextView) rootView.findViewById(R.id.articleWebsiteDetail)).setText(mItem.getWebsite());
            String date = new JsonDateConverter().getDateFormat().format(mItem.getDate());
            ((TextView) rootView.findViewById(R.id.articleDateDetail)).setText(date);
            ((TextView) rootView.findViewById(R.id.articleContentDetail)).setText(mItem.getContent());

            FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fabRead);
            fab.setSelected(mItem.getRead());
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean val = !mItem.getRead();
                    mItem.setRead(val);

                    Snackbar.make(v, val ? R.string.snackbar_message_mark_read : R.string.snackbar_message_unmark_read, Snackbar.LENGTH_SHORT).show();
                    v.setSelected(val);

                    ArticleDAO.getInstance().updateReadState(mItem);
                }
            });
        }

        //Prevent from exception when toolbar is half-way collapsed and a touch is made on image
        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) rootView.findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        return rootView;
    }

}
