package com.konifar.twipe.dao;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import com.konifar.twipe.dao.datastore.UserDataStore;
import com.konifar.twipe.dao.datastore.UserDataStoreFactory;
import com.konifar.twipe.dao.executer.JobExecuter;
import com.konifar.twipe.dao.executer.ThreadExecuter;
import com.konifar.twipe.model.pojo.UserModel;
import de.greenrobot.event.EventBus;

public class UserDaoImpl implements UserDao {

  private static UserDaoImpl instance;
  private final ThreadExecuter threadExecutor;
  private final UserDataStoreFactory userDataStoreFactory;

  private UserDaoImpl(@NonNull Context context) {
    this.threadExecutor = JobExecuter.getInstance();
    this.userDataStoreFactory = UserDataStoreFactory.getInstance(context);
  }

  public static UserDaoImpl getInstance(@NonNull Context context) {
    if (instance == null) {
      instance = new UserDaoImpl(context);
    }
    return instance;
  }

  @Override public void getUser(final Long userId, final String tag) {
    final UserDataStore userDataStore = this.userDataStoreFactory.create();
    threadExecutor.execute(new Runnable() {
      @Override public void run() {
        userDataStore.getUser(userId, new UserDataStore.UserCallback() {
          @Override public void onUserLoaded(UserModel userModel) {
            Log.e("hogehoge", userModel.getName() + "");
            EventBus.getDefault().post(new UserDao.OnUserLoadedEvent(userModel, tag));
          }

          @Override public void onError(Exception exception) {
            EventBus.getDefault().post(new OnUserErrorEvent(exception, tag));
          }
        });
      }
    });
  }
}
