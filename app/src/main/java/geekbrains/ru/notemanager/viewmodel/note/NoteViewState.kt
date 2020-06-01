package geekbrains.ru.notemanager.viewmodel.note

import geekbrains.ru.notemanager.model.Note
import geekbrains.ru.notemanager.viewmodel.base.BaseViewState

class NoteViewState(note: Note? = null, error: Throwable? = null) : BaseViewState<Note?>(note, error)