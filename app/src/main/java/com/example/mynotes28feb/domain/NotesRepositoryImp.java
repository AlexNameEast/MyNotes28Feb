package com.example.mynotes28feb.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class NotesRepositoryImp implements NotesRepository {
    @Override
    public List<Note> getNotes() {

        ArrayList<Note> result = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            result.add(new Note(UUID.randomUUID().toString(), "Title " + i, "Content " + i, new Date()));
        }


        return result;

    }
}
