package com.example.mynotes28feb.ui.list;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynotes28feb.R;
import com.example.mynotes28feb.domain.Note;
import com.example.mynotes28feb.domain.NotesRepository;
import com.example.mynotes28feb.domain.NotesRepositoryImp;
import com.example.mynotes28feb.ui.edit.EditNoteBottomSheetDialogFragment;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class ListFragment extends Fragment {

    private NotesRepository repository = NotesRepositoryImp.INSTANCE;

    private NotesListAdapter adapter;

    public ListFragment() {
        super(R.layout.fragment_list);
    }

    private Note selectedNote;
    private int selectedNoteIndex;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        RecyclerView list = view.findViewById(R.id.list);

        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);

        list.setLayoutManager(linearLayoutManager);

        adapter = new NotesListAdapter(this);

        adapter.setOnNoteClicked(new NotesListAdapter.OnNoteClicked() {
            @Override
            public void onNoteClicked(Note note) {
                Toast.makeText(requireContext(), note.getTitle(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNoteLongClicked(Note note, int position) {

                selectedNote = note;

                selectedNoteIndex = position;

            }
        });

        list.setAdapter(adapter);

        adapter.setData(repository.getNotes());

        adapter.notifyDataSetChanged();

        view.findViewById(R.id.add_note).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Note note = repository.add("Title add", "Content add");

                int index = adapter.addItem(note);

                adapter.notifyItemInserted(index);

                list.smoothScrollToPosition(index);
            }
        });

        getParentFragmentManager().setFragmentResultListener(EditNoteBottomSheetDialogFragment.KEY_REQUEST, getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                Note note = result.getParcelable(EditNoteBottomSheetDialogFragment.ARG_NOTE);

                adapter.updateItem(note, selectedNoteIndex);
                adapter.notifyItemChanged(selectedNoteIndex);
            }
        });

    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        requireActivity().getMenuInflater().inflate(R.menu.menu_notes_list_context, menu);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_edit:

                EditNoteBottomSheetDialogFragment.newInstance(selectedNote)
                        .show(getParentFragmentManager(), "EditNoteBottomSheetDialogFragment");

                return true;
            case R.id.action_delete:

                repository.delete(selectedNote);

                adapter.removeItem(selectedNoteIndex);

                adapter.notifyItemRemoved(selectedNoteIndex);

                return true;
        }

        return super.onContextItemSelected(item);
    }
}
