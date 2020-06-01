package geekbrains.ru.notemanager.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import geekbrains.ru.notemanager.viewmodel.main.MainViewModel
import geekbrains.ru.notemanager.viewmodel.main.MainViewState
import geekbrains.ru.notemanager.R
import geekbrains.ru.notemanager.model.Note
import geekbrains.ru.notemanager.ui.base.BaseActivity
import geekbrains.ru.notemanager.ui.note.NoteActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<List<Note>?, MainViewState>() {

    override val viewModel: MainViewModel by lazy { ViewModelProviders.of(this).get(MainViewModel::class.java) }
    override val layoutRes: Int = R.layout.activity_main
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)

        adapter = MainAdapter {note-> openNoteScreen(note)}
        mainRecycler.adapter = adapter

        fab.setOnClickListener{v -> openNoteScreen(null)}
    }

    override fun renderData(data: List<Note>?) {
        if (data == null) return
        adapter.notes = data
    }

    private fun openNoteScreen(note: Note?) {
        val intent = NoteActivity.getStartIntent(this, note?.id)
        startActivity(intent)
    }
}