package com.konifar.twipe.presentation.view.adapter;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.konifar.twipe.R;
import com.konifar.twipe.domain.model.MediaModel;
import com.konifar.twipe.domain.model.TweetModel;
import com.konifar.twipe.domain.model.UserModel;
import com.konifar.twipe.util.DateUtil;
import com.twitter.sdk.android.tweetui.internal.util.AspectRatioImageView;
import java.util.Collection;
import java.util.List;

public class TweetAdapter extends ArrayAdapter<TweetModel> {

  public TweetAdapter(Context context) {
    super(context, R.layout.item_tweet);
  }

  @Override
  public View getView(final int pos, View view, final ViewGroup parent) {
    ViewHolder holder;
    TweetModel tweetModel = getItem(pos);

    if (view == null || view.getTag() == null) {
      view = LayoutInflater.from(getContext()).inflate(R.layout.item_tweet, parent, false);
      holder = new ViewHolder(view);
    } else {
      holder = (ViewHolder) view.getTag();
    }

    bindData(tweetModel, holder);
    view.setTag(holder);

    return view;
  }

  private void bindData(TweetModel tweetModel, ViewHolder holder) {
    holder.mTxtDate.setText(DateUtil.getRelativeTime(tweetModel.getCreatedAt()));

    TweetModel retweet = tweetModel.getRetweetedStatus();
    if (retweet != null) {
      holder.mTxtRetweetedMsg.setVisibility(View.VISIBLE);
      String text = getContext().getString(R.string.retweeted_msg, tweetModel.getUser().getName());
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
        //MediaEntity media = mediaModels.get(0);
        //ImageLoader.getInstance().displayImage(media.mediaUrl, holder.mImgTweetMedia);
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
    //ImageLoader.getInstance().displayImage(userModel.profileImageUrl, holder.mImgUser);
    holder.mTxtUserScreenName.setText(userModel.getScreenName());
    holder.mTxtUserName.setText(userModel.getName());
  }

  public void addAll(List<TweetModel> tweetModels) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
      super.addAll(tweetModels);
    } else {
      for (TweetModel tweetModel : tweetModels) {
        add(tweetModel);
      }
    }
  }

  static class ViewHolder {
    @InjectView(R.id.txt_retweeted_msg) TextView mTxtRetweetedMsg;
    @InjectView(R.id.img_user) ImageView mImgUser;
    @InjectView(R.id.txt_user_screen_name) TextView mTxtUserScreenName;
    @InjectView(R.id.txt_user_name) TextView mTxtUserName;
    @InjectView(R.id.txt_date) TextView mTxtDate;
    @InjectView(R.id.txt_tweet) TextView mTxtTweet;
    @InjectView(R.id.img_tweet_media) AspectRatioImageView mImgTweetMedia;
    @InjectView(R.id.txt_retweet) TextView mTxtRetweet;
    @InjectView(R.id.txt_favorite) TextView mTxtFavorite;

    ViewHolder(View view) {
      ButterKnife.inject(this, view);
    }
  }
}
