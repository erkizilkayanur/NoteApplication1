package com.example.nurerkizilkaya.noteapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nurerkizilkaya.noteapplication.R;
import com.example.nurerkizilkaya.noteapplication.model.Notes;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by a s u s on 19.9.2017.
 */
public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder>{
    public  List<Notes> data;

    public NotesAdapter(List<Notes> data)

    {
        this.data=data;
    }

    @Override
    public NotesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemview = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.item_notes_list, parent, false);
        return new NotesViewHolder(itemview);
    }
    @Override
    public void onBindViewHolder(NotesViewHolder holder, int position) {
        Notes n =data.get(position);
        holder.postId.setText(n.getId().toString());
        holder.userId.setText(n.getUserId().toString());
        holder.titleId.setText(n.getTitle().toString());

        Log.d("ADAPTER", "*****");

    }
    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class NotesViewHolder extends  RecyclerView.ViewHolder
    {
        @Bind(R.id.postId)
        TextView postId;
        @Bind(R.id.userId)
        TextView userId;
        @Bind(R.id.titleId)
        TextView titleId;

        public NotesViewHolder(View v) {
            super(v);
            ButterKnife.bind(this,v);
        }
    }

}
