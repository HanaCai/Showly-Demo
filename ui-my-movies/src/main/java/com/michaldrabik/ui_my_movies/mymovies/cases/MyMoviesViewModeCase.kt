package com.michaldrabik.ui_my_movies.mymovies.cases

import com.michaldrabik.common.Config
import com.michaldrabik.repository.settings.SettingsRepository
import com.michaldrabik.ui_base.common.ListViewMode
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class MyMoviesViewModeCase @Inject constructor(
  private val settingsRepository: SettingsRepository,
) {

  fun setNextViewMode(): ListViewMode {
    val viewModes = ListViewMode.values()
    val index = viewModes.indexOf(getListViewMode()) + 1
    val nextIndex = if (index >= viewModes.size) 0 else index
    settingsRepository.viewMode.myMoviesViewMode = viewModes[nextIndex].name
    return viewModes[nextIndex]
  }

  fun getListViewMode(): ListViewMode {
    if (!settingsRepository.isPremium) {
      return ListViewMode.valueOf(Config.DEFAULT_LIST_VIEW_MODE)
    }
    val viewMode = settingsRepository.viewMode.myMoviesViewMode
    return ListViewMode.valueOf(viewMode)
  }
}
