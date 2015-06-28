package com.konifar.twipe.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setRetainInstance(true);
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.reset(this);
  }
}
