package io.ckl.challenge.max.control;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import io.ckl.challenge.max.R;
import io.ckl.challenge.max.entity.Article;
import io.ckl.challenge.max.util.JsonDateConverter;

/**
 *
 * ArrayAdapter to list the articles, showing basic info and image.
 *
 * Created by Max Jr on 05/09/2015.
 */
public class ArticleItemAdapter extends ArrayAdapter<Article> {
    private JsonDateConverter dateConverter;
    private List<Article> items;

    public ArticleItemAdapter(Context parent, List<Article> items) {
        super(parent, R.layout.item_article,items);
        this.items = items;
        this.dateConverter = new JsonDateConverter();
    }

    @Override
    public Article getItem(int position) {
        return items.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.item_article, null);
        }

        Article article = items.get(position);

        TextView title = (TextView)v.findViewById(R.id.articleTitle);
        if (title!=null)
            title.setText(article.getTitle());

        TextView authors = (TextView)v.findViewById(R.id.articleAuthor);
        if (authors != null)
            authors.setText(article.getAuthors());

        TextView date = (TextView)v.findViewById(R.id.articleDate);
        if (date != null)
            date.setText(dateConverter.getDateFormat().format(article.getDate()));

        ImageView image = (ImageView)v.findViewById(R.id.articleImage);
        if (image!=null)
            Picasso.with(getContext()).load(article.getImageUrl()).error(R.drawable.ckl_logo).placeholder(R.drawable.ckl_logo).into(image);

        return v;
    }

}