package com.konifar.twipe.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public abstract class HeaderFooterRecyclerViewAdapter
    extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private static final int VIEW_TYPE_MAX_COUNT = 1000;
  private static final int HEADER_VIEW_TYPE_OFFSET = 0;
  private static final int FOOTER_VIEW_TYPE_OFFSET = HEADER_VIEW_TYPE_OFFSET + VIEW_TYPE_MAX_COUNT;
  private static final int CONTENT_VIEW_TYPE_OFFSET = FOOTER_VIEW_TYPE_OFFSET + VIEW_TYPE_MAX_COUNT;

  private int headerItemCount;
  private int contentItemCount;
  private int footerItemCount;

  @Override
  public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (viewType >= HEADER_VIEW_TYPE_OFFSET
        && viewType < HEADER_VIEW_TYPE_OFFSET + VIEW_TYPE_MAX_COUNT) {
      return onCreateHeaderItemViewHolder(parent, viewType - HEADER_VIEW_TYPE_OFFSET);
    } else if (viewType >= FOOTER_VIEW_TYPE_OFFSET
        && viewType < FOOTER_VIEW_TYPE_OFFSET + VIEW_TYPE_MAX_COUNT) {
      return onCreateFooterItemViewHolder(parent, viewType - FOOTER_VIEW_TYPE_OFFSET);
    } else if (viewType >= CONTENT_VIEW_TYPE_OFFSET
        && viewType < CONTENT_VIEW_TYPE_OFFSET + VIEW_TYPE_MAX_COUNT) {
      return onCreateContentItemViewHolder(parent, viewType - CONTENT_VIEW_TYPE_OFFSET);
    } else {
      throw new IllegalStateException();
    }
  }

  @Override public final void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
    if (headerItemCount > 0 && position < headerItemCount) {
      onBindHeaderItemViewHolder(viewHolder, position);
    } else if (contentItemCount > 0 && position - headerItemCount < contentItemCount) {
      onBindContentItemViewHolder(viewHolder, position - headerItemCount);
    } else {
      onBindFooterItemViewHolder(viewHolder, position - headerItemCount - contentItemCount);
    }
  }

  @Override public final int getItemCount() {
    headerItemCount = getHeaderItemCount();
    contentItemCount = getContentItemCount();
    footerItemCount = getFooterItemCount();
    return headerItemCount + contentItemCount + footerItemCount;
  }

  @Override public final int getItemViewType(int position) {
    if (headerItemCount > 0 && position < headerItemCount) {
      return validateViewType(getHeaderItemViewType(position)) + HEADER_VIEW_TYPE_OFFSET;
    } else if (contentItemCount > 0 && position - headerItemCount < contentItemCount) {
      return validateViewType(getContentItemViewType(position - headerItemCount))
          + CONTENT_VIEW_TYPE_OFFSET;
    } else {
      return validateViewType(getFooterItemViewType(position - headerItemCount - contentItemCount))
          + FOOTER_VIEW_TYPE_OFFSET;
    }
  }

  private int validateViewType(int viewType) {
    if (viewType < 0 || viewType >= VIEW_TYPE_MAX_COUNT) {
      throw new IllegalStateException("viewType must be between 0 and " + VIEW_TYPE_MAX_COUNT);
    }
    return viewType;
  }

  public final void notifyHeaderItemInserted(int position) {
    int newHeaderItemCount = getHeaderItemCount();
    if (position < 0 || position >= newHeaderItemCount) {
      throw new IndexOutOfBoundsException("The given position "
          + position
          + " is not within the position bounds for header items [0 - "
          + (newHeaderItemCount - 1)
          + "].");
    }
    notifyItemInserted(position);
  }

  public final void notifyHeaderItemRangeInserted(int positionStart, int itemCount) {
    int newHeaderItemCount = getHeaderItemCount();
    if (positionStart < 0 || itemCount < 0 || positionStart + itemCount > newHeaderItemCount) {
      throw new IndexOutOfBoundsException("The given range ["
          + positionStart
          + " - "
          + (positionStart + itemCount - 1)
          + "] is not within the position bounds for header items [0 - "
          + (newHeaderItemCount - 1)
          + "].");
    }
    notifyItemRangeInserted(positionStart, itemCount);
  }

  public final void notifyHeaderItemChanged(int position) {
    if (position < 0 || position >= headerItemCount) {
      throw new IndexOutOfBoundsException("The given position "
          + position
          + " is not within the position bounds for header items [0 - "
          + (headerItemCount - 1)
          + "].");
    }
    notifyItemChanged(position);
  }

  public final void notifyHeaderItemRangeChanged(int positionStart, int itemCount) {
    if (positionStart < 0 || itemCount < 0 || positionStart + itemCount >= headerItemCount) {
      throw new IndexOutOfBoundsException("The given range ["
          + positionStart
          + " - "
          + (positionStart + itemCount - 1)
          + "] is not within the position bounds for header items [0 - "
          + (headerItemCount - 1)
          + "].");
    }
    notifyItemRangeChanged(positionStart, itemCount);
  }

  public void notifyHeaderItemMoved(int fromPosition, int toPosition) {
    if (fromPosition < 0
        || toPosition < 0
        || fromPosition >= headerItemCount
        || toPosition >= headerItemCount) {
      throw new IndexOutOfBoundsException("The given fromPosition "
          + fromPosition
          + " or toPosition "
          + toPosition
          + " is not within the position bounds for header items [0 - "
          + (headerItemCount - 1)
          + "].");
    }
    notifyItemMoved(fromPosition, toPosition);
  }

  public void notifyHeaderItemRemoved(int position) {
    if (position < 0 || position >= headerItemCount) {
      throw new IndexOutOfBoundsException("The given position "
          + position
          + " is not within the position bounds for header items [0 - "
          + (headerItemCount - 1)
          + "].");
    }
    notifyItemRemoved(position);
  }

  public void notifyHeaderItemRangeRemoved(int positionStart, int itemCount) {
    if (positionStart < 0 || itemCount < 0 || positionStart + itemCount > headerItemCount) {
      throw new IndexOutOfBoundsException("The given range ["
          + positionStart
          + " - "
          + (positionStart + itemCount - 1)
          + "] is not within the position bounds for header items [0 - "
          + (headerItemCount - 1)
          + "].");
    }
    notifyItemRangeRemoved(positionStart, itemCount);
  }

  public final void notifyContentItemInserted(int position) {
    int newHeaderItemCount = getHeaderItemCount();
    int newContentItemCount = getContentItemCount();
    if (position < 0 || position >= newContentItemCount) {
      throw new IndexOutOfBoundsException("The given position "
          + position
          + " is not within the position bounds for content items [0 - "
          + (newContentItemCount - 1)
          + "].");
    }
    notifyItemInserted(position + newHeaderItemCount);
  }

  public final void notifyContentItemRangeInserted(int positionStart, int itemCount) {
    int newHeaderItemCount = getHeaderItemCount();
    int newContentItemCount = getContentItemCount();
    if (positionStart < 0 || itemCount < 0 || positionStart + itemCount > newContentItemCount) {
      throw new IndexOutOfBoundsException("The given range ["
          + positionStart
          + " - "
          + (positionStart + itemCount - 1)
          + "] is not within the position bounds for content items [0 - "
          + (newContentItemCount - 1)
          + "].");
    }
    notifyItemRangeInserted(positionStart + newHeaderItemCount, itemCount);
  }

  public final void notifyContentItemChanged(int position) {
    if (position < 0 || position >= contentItemCount) {
      throw new IndexOutOfBoundsException("The given position "
          + position
          + " is not within the position bounds for content items [0 - "
          + (contentItemCount - 1)
          + "].");
    }
    notifyItemChanged(position + headerItemCount);
  }

  public final void notifyContentItemRangeChanged(int positionStart, int itemCount) {
    if (positionStart < 0 || itemCount < 0 || positionStart + itemCount > contentItemCount) {
      throw new IndexOutOfBoundsException("The given range ["
          + positionStart
          + " - "
          + (positionStart + itemCount - 1)
          + "] is not within the position bounds for content items [0 - "
          + (contentItemCount - 1)
          + "].");
    }
    notifyItemRangeChanged(positionStart + headerItemCount, itemCount);
  }

  public final void notifyContentItemMoved(int fromPosition, int toPosition) {
    if (fromPosition < 0
        || toPosition < 0
        || fromPosition >= contentItemCount
        || toPosition >= contentItemCount) {
      throw new IndexOutOfBoundsException("The given fromPosition "
          + fromPosition
          + " or toPosition "
          + toPosition
          + " is not within the position bounds for content items [0 - "
          + (contentItemCount - 1)
          + "].");
    }
    notifyItemMoved(fromPosition + headerItemCount, toPosition + headerItemCount);
  }

  public final void notifyContentItemRemoved(int position) {
    if (position < 0 || position >= contentItemCount) {
      throw new IndexOutOfBoundsException("The given position "
          + position
          + " is not within the position bounds for content items [0 - "
          + (contentItemCount - 1)
          + "].");
    }
    notifyItemRemoved(position + headerItemCount);
  }

  public final void notifyContentItemRangeRemoved(int positionStart, int itemCount) {
    if (positionStart < 0 || itemCount < 0 || positionStart + itemCount > contentItemCount) {
      throw new IndexOutOfBoundsException("The given range ["
          + positionStart
          + " - "
          + (positionStart + itemCount - 1)
          + "] is not within the position bounds for content items [0 - "
          + (contentItemCount - 1)
          + "].");
    }
    notifyItemRangeRemoved(positionStart + headerItemCount, itemCount);
  }

  public final void notifyFooterItemInserted(int position) {
    int newHeaderItemCount = getHeaderItemCount();
    int newContentItemCount = getContentItemCount();
    int newFooterItemCount = getFooterItemCount();
    if (position < 0 || position >= newFooterItemCount) {
      throw new IndexOutOfBoundsException("The given position "
          + position
          + " is not within the position bounds for footer items [0 - "
          + (newFooterItemCount - 1)
          + "].");
    }
    notifyItemInserted(position + newHeaderItemCount + newContentItemCount);
  }

  public final void notifyFooterItemRangeInserted(int positionStart, int itemCount) {
    int newHeaderItemCount = getHeaderItemCount();
    int newContentItemCount = getContentItemCount();
    int newFooterItemCount = getFooterItemCount();
    if (positionStart < 0 || itemCount < 0 || positionStart + itemCount > newFooterItemCount) {
      throw new IndexOutOfBoundsException("The given range ["
          + positionStart
          + " - "
          + (positionStart + itemCount - 1)
          + "] is not within the position bounds for footer items [0 - "
          + (newFooterItemCount - 1)
          + "].");
    }
    notifyItemRangeInserted(positionStart + newHeaderItemCount + newContentItemCount, itemCount);
  }

  public final void notifyFooterItemChanged(int position) {
    if (position < 0 || position >= footerItemCount) {
      throw new IndexOutOfBoundsException("The given position "
          + position
          + " is not within the position bounds for footer items [0 - "
          + (footerItemCount - 1)
          + "].");
    }
    notifyItemChanged(position + headerItemCount + contentItemCount);
  }

  public final void notifyFooterItemRangeChanged(int positionStart, int itemCount) {
    if (positionStart < 0 || itemCount < 0 || positionStart + itemCount > footerItemCount) {
      throw new IndexOutOfBoundsException("The given range ["
          + positionStart
          + " - "
          + (positionStart + itemCount - 1)
          + "] is not within the position bounds for footer items [0 - "
          + (footerItemCount - 1)
          + "].");
    }
    notifyItemRangeChanged(positionStart + headerItemCount + contentItemCount, itemCount);
  }

  public final void notifyFooterItemMoved(int fromPosition, int toPosition) {
    if (fromPosition < 0
        || toPosition < 0
        || fromPosition >= footerItemCount
        || toPosition >= footerItemCount) {
      throw new IndexOutOfBoundsException("The given fromPosition "
          + fromPosition
          + " or toPosition "
          + toPosition
          + " is not within the position bounds for footer items [0 - "
          + (footerItemCount - 1)
          + "].");
    }
    notifyItemMoved(fromPosition + headerItemCount + contentItemCount,
        toPosition + headerItemCount + contentItemCount);
  }

  public final void notifyFooterItemRemoved(int position) {
    if (position < 0 || position >= footerItemCount) {
      throw new IndexOutOfBoundsException("The given position "
          + position
          + " is not within the position bounds for footer items [0 - "
          + (footerItemCount - 1)
          + "].");
    }
    notifyItemRemoved(position + headerItemCount + contentItemCount);
  }

  public final void notifyFooterItemRangeRemoved(int positionStart, int itemCount) {
    if (positionStart < 0 || itemCount < 0 || positionStart + itemCount > footerItemCount) {
      throw new IndexOutOfBoundsException("The given range ["
          + positionStart
          + " - "
          + (positionStart + itemCount - 1)
          + "] is not within the position bounds for footer items [0 - "
          + (footerItemCount - 1)
          + "].");
    }
    notifyItemRangeRemoved(positionStart + headerItemCount + contentItemCount, itemCount);
  }

  protected int getHeaderItemViewType(int position) {
    return 0;
  }

  protected int getFooterItemViewType(int position) {
    return 0;
  }

  protected int getContentItemViewType(int position) {
    return 0;
  }

  protected abstract int getHeaderItemCount();

  protected abstract int getFooterItemCount();

  protected abstract int getContentItemCount();

  protected abstract RecyclerView.ViewHolder onCreateHeaderItemViewHolder(ViewGroup parent,
      int headerViewType);

  protected abstract RecyclerView.ViewHolder onCreateFooterItemViewHolder(ViewGroup parent,
      int footerViewType);

  protected abstract RecyclerView.ViewHolder onCreateContentItemViewHolder(ViewGroup parent,
      int contentViewType);

  protected abstract void onBindHeaderItemViewHolder(RecyclerView.ViewHolder headerViewHolder,
      int position);

  protected abstract void onBindFooterItemViewHolder(RecyclerView.ViewHolder footerViewHolder,
      int position);

  protected abstract void onBindContentItemViewHolder(RecyclerView.ViewHolder contentViewHolder,
      int position);
}