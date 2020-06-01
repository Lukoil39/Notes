package geekbrains.ru.notemanager.viewmodel.main

import geekbrains.ru.notemanager.model.Note
import geekbrains.ru.notemanager.viewmodel.base.BaseViewState

class MainViewState(notes: List<Note>? = null, error: Throwable? = null)
    : BaseViewState<List<Note>?>(notes, error)