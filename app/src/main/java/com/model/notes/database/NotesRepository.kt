package com.model.notes.database

import androidx.lifecycle.LiveData
import com.model.notes.models.Note

class NotesRepository(private val noteDao: NoteDao) {
    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun insert(note: Note){
        noteDao.insert(note)
    }
    suspend fun delete(note: Note){
        noteDao.delete(note)
    }
    suspend fun update(note: Note){
        noteDao.update(note = note.note, id =note.id, title = note.title )
    }
}