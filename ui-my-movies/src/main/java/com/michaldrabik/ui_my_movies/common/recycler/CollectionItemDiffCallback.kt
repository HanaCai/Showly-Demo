package com.michaldrabik.ui_my_movies.common.recycler

import androidx.recyclerview.widget.DiffUtil

class CollectionItemDiffCallback : DiffUtil.ItemCallback<CollectionListItem>() {

  override fun areItemsTheSame(oldItem: CollectionListItem, newItem: CollectionListItem): Boolean {
    val areMovies = oldItem is CollectionListItem.MovieItem && newItem is CollectionListItem.MovieItem
    val areFilters = oldItem is CollectionListItem.FiltersItem && newItem is CollectionListItem.FiltersItem

    return when {
      areMovies -> areItemsTheSame(
        (oldItem as CollectionListItem.MovieItem),
        (newItem as CollectionListItem.MovieItem)
      )
      areFilters -> true
      else -> false
    }
  }

  override fun areContentsTheSame(oldItem: CollectionListItem, newItem: CollectionListItem): Boolean {
    return when (oldItem) {
      is CollectionListItem.MovieItem -> areContentsTheSame(oldItem, (newItem as CollectionListItem.MovieItem))
      is CollectionListItem.FiltersItem -> areContentsTheSame(oldItem, (newItem as CollectionListItem.FiltersItem))
    }
  }

  private fun areItemsTheSame(
    oldItem: CollectionListItem.MovieItem,
    newItem: CollectionListItem.MovieItem,
  ): Boolean {
    return oldItem.movie.traktId == newItem.movie.traktId
  }

  private fun areContentsTheSame(
    oldItem: CollectionListItem.MovieItem,
    newItem: CollectionListItem.MovieItem,
  ): Boolean {
    return oldItem.image == newItem.image &&
      oldItem.isLoading == newItem.isLoading &&
      oldItem.translation == newItem.translation &&
      oldItem.sortOrder == newItem.sortOrder &&
      oldItem.userRating == newItem.userRating
  }

  private fun areContentsTheSame(
    oldItem: CollectionListItem.FiltersItem,
    newItem: CollectionListItem.FiltersItem,
  ): Boolean {
    return oldItem.isUpcoming == newItem.isUpcoming &&
      oldItem.sortOrder == newItem.sortOrder &&
      oldItem.sortType == newItem.sortType
  }
}
