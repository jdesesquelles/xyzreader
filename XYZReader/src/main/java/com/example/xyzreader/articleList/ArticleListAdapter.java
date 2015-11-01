package com.example.xyzreader.articleList;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.View;
import android.view.ViewGroup;

import com.example.xyzreader.R;
import com.example.xyzreader.articleList.ArticleListActivity;
import com.example.xyzreader.data.ArticleLoader;
import com.example.xyzreader.data.ItemsContract;
import com.example.xyzreader.ui.ImageLoaderHelper;

/**
 * Created by ebal on 31/10/15.
 */
class ArticleListAdapter extends RecyclerView.Adapter<ArticleListActivity.ViewHolder> {
    private ArticleListActivity articleListActivity;
    private Cursor mCursor;

    public ArticleListAdapter(ArticleListActivity articleListActivity, Cursor cursor) {
        this.articleListActivity = articleListActivity;
        mCursor = cursor;
    }

    @Override
    public long getItemId(int position) {
        mCursor.moveToPosition(position);
        return mCursor.getLong(ArticleLoader.Query._ID);
    }

    @Override
    public ArticleListActivity.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = articleListActivity.getLayoutInflater().inflate(R.layout.list_item_article, parent, false);
        final ArticleListActivity.ViewHolder vh = new ArticleListActivity.ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                articleListActivity.startActivity(new Intent(Intent.ACTION_VIEW,
                        ItemsContract.Items.buildItemUri(getItemId(vh.getAdapterPosition()))));
            }
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(ArticleListActivity.ViewHolder holder, int position) {
        mCursor.moveToPosition(position);
        holder.titleView.setText(mCursor.getString(ArticleLoader.Query.TITLE));
        holder.subtitleView.setText(
                DateUtils.getRelativeTimeSpanString(
                        mCursor.getLong(ArticleLoader.Query.PUBLISHED_DATE),
                        System.currentTimeMillis(), DateUtils.HOUR_IN_MILLIS,
                        DateUtils.FORMAT_ABBREV_ALL).toString()
                        + " by "
                        + mCursor.getString(ArticleLoader.Query.AUTHOR));
        holder.thumbnailView.setImageUrl(
                mCursor.getString(ArticleLoader.Query.THUMB_URL),
                ImageLoaderHelper.getInstance(articleListActivity).getImageLoader());
//        holder.thumbnailView.setAspectRatio(mCursor.getFloat(ArticleLoader.Query.ASPECT_RATIO));

    }


    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }
}
