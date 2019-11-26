package com.app.greendaodemo.databasehelper;

import com.app.greendaodemo.application.MainApplication;
import com.app.greendaodemo.model.DaoSession;
import com.app.greendaodemo.model.NoteModel;

import java.util.ArrayList;
import java.util.List;

public class NoteOperation {

    private static DaoSession daoSession = MainApplication.getSession();

    public static void insertNoteData(NoteModel noteModel) {
        daoSession.getNoteModelDao().insertInTx(noteModel);
    }

    public static void insertNoteDataList(List<NoteModel> noteModel) {
        daoSession.getNoteModelDao().insertInTx(noteModel);
    }

    public static List<NoteModel> getAllNotes() {
        return daoSession.getNoteModelDao().loadAll();
    }

    public static void updateNotes(NoteModel noteModel) {
        daoSession.getNoteModelDao().updateInTx(noteModel);
    }

    public static void updateNoteList(List<NoteModel> noteModel) {
        daoSession.getNoteModelDao().updateInTx(noteModel);
    }

    public static void deleteNote(NoteModel noteModel) {
        daoSession.getNoteModelDao().delete(noteModel);
    }

    public static void deleteNote(Long id) {
        daoSession.getNoteModelDao().deleteByKeyInTx(id);
    }

    public static void deleteAllNote() {
        daoSession.getNoteModelDao().deleteAll();
    }

}
