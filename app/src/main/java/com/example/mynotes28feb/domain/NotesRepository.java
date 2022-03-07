package com.example.mynotes28feb.domain;

import java.util.List;

public interface NotesRepository {

    List<Note> getNotes();

    Note add(String title, String content);

    void delete(Note note);

    Note update(String id, String newTitle, String newContent);
}
