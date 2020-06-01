package geekbrains.ru.notemanager.data

import androidx.lifecycle.LiveData
import geekbrains.ru.notemanager.model.Note
import geekbrains.ru.notemanager.model.NoteResult

interface RemoteDataProvider {

    fun subscribeToAllNotes(): LiveData<NoteResult>
    fun getNoteById(id: String): LiveData<NoteResult>
    fun saveNote(note: Note) : LiveData<NoteResult>
}
