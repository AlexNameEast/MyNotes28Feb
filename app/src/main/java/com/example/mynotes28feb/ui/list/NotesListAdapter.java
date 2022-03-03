package com.example.mynotes28feb.ui.list;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynotes28feb.R;
import com.example.mynotes28feb.domain.Note;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class NotesListAdapter extends RecyclerView.Adapter<NotesListAdapter.NoteViewHolder> {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());

    private List<Note> data = new ArrayList<>();

    public void setData(Collection<Note> toSet) {
        data.clear();
        data.addAll(toSet);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);

        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {

        Note item = data.get(position);

        holder.title.setText(item.getTitle());
        holder.content.setText(item.getContent());
        holder.data.setText(simpleDateFormat.format(item.getCreatedAt()));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class NoteViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView content;
        TextView data;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);


            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
            data = itemView.findViewById(R.id.data);
        }
    }
}
