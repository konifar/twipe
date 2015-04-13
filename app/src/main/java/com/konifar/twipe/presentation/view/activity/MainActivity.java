package com.konifar.twipe.presentation.view.activity;

import android.os.Bundle;
import com.konifar.twipe.R;

public class MainActivity extends BaseActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    LoginActivity.start(this);
  }
}
