package com.konifar.twipe.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.konifar.twipe.R;
import com.konifar.twipe.model.pojo.MediaModel;
import com.konifar.twipe.model.pojo.TweetModel;
import com.konifar.twipe.model.pojo.UserModel;
import com.konifar.twipe.util.DateUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TweetAdapter extends HeaderFooterRecyclerViewAdapter {

  private Context context;
  private List<TweetModel> items;

  public TweetAdapter(Context context) {
    this.context = context;
    this.items = new ArrayList<>();
  }

  public void add(TweetModel tweetModel) {
    items.add(tweetModel);
    notifyContentItemInserted(items.size());
  }

  public void addAll(Collection<TweetModel> tweetModels) {
    items.addAll(tweetModels);
    notifyContentItemRangeInserted(items.size() - tweetModels.size(), items.size());
  }

  private void bindData(TweetModel tweetModel, ViewHolder holder) {
    holder.mTxtDate.setText(DateUtil.getRelativeTime(tweetModel.getCreatedAt()));

    TweetModel retweet = tweetModel.getRetweetedStatus();
    if (retweet != null) {
      holder.mTxtRetweetedMsg.setVisibility(View.VISIBLE);
      String text = context.getString(R.string.retweeted_msg, tweetModel.getUser().getName());
      holder.mTxtRetweetedMsg.setText(text);
      tweetModel = retweet;
    } else {
      holder.mTxtRetweetedMsg.setVisibility(View.GONE);
    }

    bindUser(tweetModel, holder);
    bindTweet(tweetModel, holder);
  }

  private void bindTweet(TweetModel tweetModel, ViewHolder holder) {
    holder.mTxtTweet.setText(Html.fromHtml(tweetModel.getText()));
    Linkify.addLinks(holder.mTxtTweet, Linkify.ALL);

    if (tweetModel.getAttachment() != null) {
      Collection<MediaModel> mediaModels = tweetModel.getAttachment().getMedias();
      if (mediaModels != null && !mediaModels.isEmpty()) {
        holder.mImgTweetMedia.setVisibility(View.VISIBLE);
        MediaModel mediaModel = mediaModels.iterator().next();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
            .setUri(Uri.parse(mediaModel.getMediaUrl()))
            .setAutoPlayAnimations(true)
            .build();
        holder.mImgTweetMedia.setController(controller);
      } else {
        holder.mImgTweetMedia.setVisibility(View.GONE);
      }
    }

    if (tweetModel.getRetweetCount() > 0) {
      holder.mTxtRetweet.setText(String.valueOf(tweetModel.getRetweetCount()));
    } else {
      holder.mTxtRetweet.setText("");
    }

    if (tweetModel.getFavoriteCount() > 0) {
      holder.mTxtFavorite.setText(String.valueOf(tweetModel.getFavoriteCount()));
    } else {
      holder.mTxtFavorite.setText("");
    }
  }

  private void bindUser(TweetModel tweetModel, ViewHolder holder) {
    UserModel userModel = tweetModel.getUser();
    holder.mImgUser.setImageURI(Uri.parse(userModel.getProfileImageUrl()));
    holder.mTxtUserScreenName.setText(userModel.getScreenName());
    holder.mTxtUserName.setText(userModel.getName());
  }

  @Override protected int getHeaderItemCount() {
    return 0;
  }

  @Override protected int getFooterItemCount() {
    return 0;
  }

  @Override protected int getContentItemCount() {
    return items.size();
  }

  @Override
  protected RecyclerView.ViewHolder onCreateHeaderItemViewHolder(ViewGroup parent, int viewType) {
    return null;
  }

  @Override
  protected RecyclerView.ViewHolder onCreateFooterItemViewHolder(ViewGroup parent, int viewType) {
    return null;
  }

  @Override
  protected RecyclerView.ViewHolder onCreateContentItemViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.item_tweet, parent, false);
    return new ViewHolder(view);
  }

  @Override
  protected void onBindHeaderItemViewHolder(RecyclerView.ViewHolder holder, int position) {

  }

  @Override
  protected void onBindFooterItemViewHolder(RecyclerView.ViewHolder holder, int position) {

  }

  @Override
  protected void onBindContentItemViewHolder(RecyclerView.ViewHolder holder, int position) {
    TweetModel tweetModel = items.get(position);
    bindData(tweetModel, (ViewHolder) holder);
  }

  static class ViewHolder extends RecyclerView.ViewHolder {
    @InjectView(R.id.txt_retweeted_msg) TextView mTxtRetweetedMsg;
    @InjectView(R.id.img_user) SimpleDraweeView mImgUser;
    @InjectView(R.id.txt_user_screen_name) TextView mTxtUserScreenName;
    @InjectView(R.id.txt_user_name) TextView mTxtUserName;
    @InjectView(R.id.txt_date) TextView mTxtDate;
    @InjectView(R.id.txt_tweet) TextView mTxtTweet;
    @InjectView(R.id.img_tweet_media) SimpleDraweeView mImgTweetMedia;
    @InjectView(R.id.txt_retweet) TextView mTxtRetweet;
    @InjectView(R.id.txt_favorite) TextView mTxtFavorite;

    ViewHolder(View view) {
      super(view);
      ButterKnife.inject(this, view);
    }
  }
}
