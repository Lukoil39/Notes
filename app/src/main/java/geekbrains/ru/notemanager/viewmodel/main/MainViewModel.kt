package geekbrains.ru.notemanager.viewmodel.main

import androidx.lifecycle.Observer
import geekbrains.ru.notemanager.model.Note
import geekbrains.ru.notemanager.model.NoteResult
import geekbrains.ru.notemanager.model.Repository
import geekbrains.ru.notemanager.viewmodel.base.BaseViewModel
import geekbrains.ru.notemanager.model.NoteResult.Success
import geekbrains.ru.notemanager.model.NoteResult.Error

class MainViewModel(val repository: Repository = Repository) : BaseViewModel<List<Note>?, MainViewState>() {

    private val notesObserver = object : Observer<NoteResult> {//Стандартный обсервер LiveData
    override fun onChanged(t: NoteResult?) {
        if (t == null) return

        when(t) {
            is Success<*> -> {
// Может понадобиться вручную импортировать класс data.model.NoteResult.Success
                viewStateLiveData.value = MainViewState(notes = t.data as? List<Note>)
            }
            is Error -> {
// Может понадобиться вручную импортировать класс data.model.NoteResult.Error
                viewStateLiveData.value = MainViewState(error = t.error)
            }
        }
    }
    }

    private val repositoryNotes = repository.getNotes()

    init {
        viewStateLiveData.value = MainViewState()
        repositoryNotes.observeForever(notesObserver)
    }

    override fun onCleared() {
        repositoryNotes.removeObserver(notesObserver)
    }
}