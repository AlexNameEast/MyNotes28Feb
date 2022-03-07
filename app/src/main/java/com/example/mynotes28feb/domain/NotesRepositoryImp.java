package com.example.mynotes28feb.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class NotesRepositoryImp implements NotesRepository {

    public static final NotesRepository INSTANCE = new NotesRepositoryImp();

    private ArrayList<Note> result = new ArrayList<>();

    public NotesRepositoryImp() {

        for (int i = 0; i < 30; i++) {
            result.add(new Note(UUID.randomUUID().toString(), "Title " + i, "Content " + i, new Date()));
        }
    }

    @Override
    public List<Note> getNotes() {

        return result;

    }

    @Override
    public Note add(String title, String content) {

        Note note = new Note(UUID.randomUUID().toString(), title, content, new Date());

        result.add(note);

        return note;
    }

    @Override
    public void delete(Note note) {
        result.remove(note);
    }

    @Override
    public Note update(String id, String newTitle, String newContent) {

        Note toChenge = null;
        int index = 0;

        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).getId().equals(id)) {
                toChenge = result.get(i);
                index = i;
                break;
            }
        }

        Note newNote = new Note(toChenge.getId(), newTitle, newContent, toChenge.getCreatedAt());

        result.set(index, newNote);

        return newNote;
    }
}
