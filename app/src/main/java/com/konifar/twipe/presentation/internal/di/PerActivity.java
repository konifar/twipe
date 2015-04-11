package com.konifar.twipe.presentation.internal.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Scope;

/**
 * A scoping annotation to permit objects whose lifetime should
 * conform to the life of the activity to be memoized in the correct component.
 */
@Scope @Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
