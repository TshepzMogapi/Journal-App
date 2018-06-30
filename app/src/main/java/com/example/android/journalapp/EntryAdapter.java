package com.example.android.journalapp;

/**
 * Created by tshepisomogapi on 2018/06/26.
 */

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View.OnClickListener;

import com.example.android.journalapp.database.DiaryEntry;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;


public class EntryAdapter extends RecyclerView.Adapter<EntryAdapter.EntryViewHolder> {

    private static final String DATE_FORMAT = "dd/MM/yyy";

    final private ListItemClickListener mOnClickListener;

    private List<DiaryEntry> mDiaryEntries;

    private Context mContext;

    private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());



    private int mEntryItems;

    public interface ListItemClickListener {

        void onListItemClick(int clickedItemIndex);
    }


    public EntryAdapter(Context context, ListItemClickListener listItemClickListener) {

        mContext = context;

        mOnClickListener = listItemClickListener;

    }


    @Override
    public EntryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();

        int layoutIdForListItem = R.layout.diary_entry_layout;

        LayoutInflater inflater = LayoutInflater.from(context);

        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);

        EntryViewHolder viewHolder = new EntryViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(EntryViewHolder holder, int position) {

        DiaryEntry diaryEntry = mDiaryEntries.get(position);
        String description = diaryEntry.getDescription();
        String updatedAt = dateFormat.format(diaryEntry.getUpdatedAt());

        holder.taskDescriptionView.setText(description);
        holder.updatedAtView.setText(updatedAt);


    }


    @Override
    public int getItemCount() {

        if (mDiaryEntries == null) {
            return 0;
        }

        return mDiaryEntries.size();

    }

    public void setEntries(List<DiaryEntry> diaryEntries) {
        mDiaryEntries = diaryEntries;
        notifyDataSetChanged();
    }


    class EntryViewHolder extends RecyclerView.ViewHolder implements OnClickListener {


        TextView taskDescriptionView;
        TextView updatedAtView;


        public EntryViewHolder(View itemView) {
            super(itemView);

            taskDescriptionView = itemView.findViewById(R.id.tv_item_entry);
            updatedAtView = itemView.findViewById(R.id.entryUpdatedAt);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            int clickedPosition = mDiaryEntries.get(getAdapterPosition()).getId();

            mOnClickListener.onListItemClick(clickedPosition);

        }



    }



}
