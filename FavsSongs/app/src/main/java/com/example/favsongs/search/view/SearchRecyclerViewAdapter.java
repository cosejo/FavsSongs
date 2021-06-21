package com.example.favsongs.search.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.favsongs.search.model.ItunesSong;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SearchRecyclerViewAdapter extends RecyclerView.Adapter<SearchRecyclerViewAdapter.ViewHolder> {
    private List<ItunesSong> mSongsList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView discName;

        public ViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(android.R.id.text1);
            discName = (TextView) v.findViewById(android.R.id.text2);
        }
    }

    public SearchRecyclerViewAdapter(List<ItunesSong> songList) {
        mSongsList = songList;
    }

    @NonNull
    @NotNull
    @Override
    public SearchRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.two_line_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SearchRecyclerViewAdapter.ViewHolder holder, int position) {
        ItunesSong song = ((ItunesSong) mSongsList.get(position));
        holder.name.setText(song.getName());
        holder.discName.setText(song.getDiscName());
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mSongsList.size();
    }

}
