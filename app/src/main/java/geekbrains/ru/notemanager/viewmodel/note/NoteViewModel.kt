package geekbrains.ru.notemanager.viewmodel.note

import androidx.lifecycle.Observer
import geekbrains.ru.notemanager.model.Note
import geekbrains.ru.notemanager.model.NoteResult
import geekbrains.ru.notemanager.model.Repository
import geekbrains.ru.notemanager.viewmodel.base.BaseViewModel
import geekbrains.ru.notemanager.model.NoteResult.Success
import geekbrains.ru.notemanager.model.NoteResult.Error


class NoteViewModel(val repository: Repository = Repository) : BaseViewModel<Note?, NoteViewState>() {

    private var pendingNote: Note? = null

    fun saveChanges(note: Note) {
        pendingNote = note
    }

    override fun onCleared() {
        if (pendingNote != null) {
            repository.saveNote(pendingNote!!)
        }
    }

    fun loadNote(noteId: String) {
        repository.getNoteById(noteId).observeForever(object : Observer<NoteResult> {
            override fun onChanged(t: NoteResult?) {
                if (t == null) return

                when (t) {
                    is Success<*> ->
                        viewStateLiveData.value = NoteViewState(note = t.data as? Note)
                    is Error ->
                        viewStateLiveData.value = NoteViewState(error = t.error)
                }
            }
        })
    }
}