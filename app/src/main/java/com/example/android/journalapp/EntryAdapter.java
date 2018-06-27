package com.example.android.journalapp;

/**
 * Created by tshepisomogapi on 2018/06/26.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View.OnClickListener;




public class EntryAdapter extends RecyclerView.Adapter<EntryAdapter.EntryViewHolder> {


    final private ListItemClickListener mOnClickListener;


    private int mEntryItems;

    public interface ListItemClickListener {

        void onListItemClick(int clickedItemIndex);
    }


    public EntryAdapter(int numberOfItems, ListItemClickListener listItemClickListener) {

        mEntryItems = numberOfItems;

        mOnClickListener = listItemClickListener;

    }


    @Override
    public EntryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();

        int layoutIdForListItem = R.layout.diary_entry_item;

        LayoutInflater inflater = LayoutInflater.from(context);

        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);

        EntryViewHolder viewHolder = new EntryViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(EntryViewHolder holder, int position) {

        holder.bind(position);

    }


    @Override
    public int getItemCount() {

        return mEntryItems;

    }


    class EntryViewHolder extends RecyclerView.ViewHolder implements OnClickListener {


        TextView listItemEntryView;

        public EntryViewHolder(View itemView) {
            super(itemView);

            listItemEntryView = (TextView) itemView.findViewById(R.id.tv_item_entry);


            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {

            int clickedPosition = getAdapterPosition();

            mOnClickListener.onListItemClick(clickedPosition);

        }

        void bind(int listIndex) {
            listItemEntryView.setText(String.valueOf(listIndex));
        }



    }



}
