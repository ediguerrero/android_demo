package edu.udem.primerapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


/**
 * A simple [Fragment] subclass.
 * Use the [NotesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NotesFragment : Fragment() , OnNoteSelected{

    private lateinit var notesDao: NotesDao
    private lateinit var adapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_notes, container, false)
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.recyclerview)

        adapter = NotesAdapter(requireContext(), this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        val database = MyDatabase.getInstance(requireContext())
        notesDao = database.notesDao()

        return rootView

    }

    override fun onResume() {
        super.onResume()
        showNotes()
    }

    fun showNotes() {
        val notes = notesDao?.getNotes()
        val notesNotReminder = notes.filter {!it.reminder!! }
        if (notes != null && notesNotReminder !=null) {
            adapter.setNotes(notesNotReminder)
        }
    }

    override fun onNoteSelected(id: Int) {
        //se va a ejecutar cuando se le de click a una nota

        val intent = Intent(context, EditNoteActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

}