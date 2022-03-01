package com.example.mynotes28feb.ui.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynotes28feb.R;
import com.example.mynotes28feb.domain.Note;
import com.example.mynotes28feb.domain.NotesRepository;
import com.example.mynotes28feb.domain.NotesRepositoryImp;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class ListFragment extends Fragment {

    private NotesRepository repository = new NotesRepositoryImp();

    public ListFragment() {
        super(R.layout.fragment_list);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        RecyclerView list = view.findViewById(R.id.list);

        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);

        list.setLayoutManager(linearLayoutManager);

        NotesListAdapter adapter = new NotesListAdapter();

        list.setAdapter(adapter);

        adapter.setData(repository.getNotes());

        adapter.notifyDataSetChanged();

//        LinearLayout container = view.findViewById(R.id.container);
//
//        for (Note note: repository.getNotes()){
//
//            View itemView = getLayoutInflater().inflate(R.layout.item_note, container, false);
//
//            itemView.findViewById(R.id.card).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                }
//            });
//
//            TextView title = itemView.findViewById(R.id.title);
//            TextView content = itemView.findViewById(R.id.content);
//            TextView data = itemView.findViewById(R.id.data);
//
//            title.setText(note.getTitle());
//            content.setText(note.getContent());
//            data.setText(simpleDateFormat.format(note.getCreatedAt()));
//
//            container.addView(itemView);
//        }

    }
}
