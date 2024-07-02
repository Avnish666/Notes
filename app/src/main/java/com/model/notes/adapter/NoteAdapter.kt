package com.model.notes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R

import com.model.notes.models.Note
import kotlin.random.Random

class NoteAdapter(private val context: Context, val listener : NotesClickListener): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
  private val NotesList = ArrayList<Note>()
  private val fullList = ArrayList<Note>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item,parent,false))
    }

    override fun getItemCount(): Int {
      return NotesList.size
    }
    fun updateList(newsList: List<Note>){
       fullList.clear()
        fullList.addAll(newsList)

        NotesList.clear()
        NotesList.addAll(newsList)
        notifyDataSetChanged()
    }
fun filterList(search:String){
    NotesList.clear()
    for (item in fullList){
        if(item.title?.lowercase()?.contains(search.lowercase()) == true  ||
            item.note?.lowercase()?.contains(search.lowercase()) == true){
            NotesList.add(item)
        }

    }
    notifyDataSetChanged()
}
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
       val currentNote=NotesList[position]
        holder.title.text = currentNote.title
        holder.title.isSelected=true

        holder.note.text=currentNote.note
        holder.date.text=currentNote.date
        holder.date.isSelected=true
        holder.notes_layout.setCardBackgroundColor(holder.itemView.resources.getColor(randomColor(),null))
        holder.notes_layout.setOnClickListener {
          listener.onItemClicked(NotesList[holder.adapterPosition])
        }
        holder.notes_layout.setOnLongClickListener {
            listener.onLongItemClicked( NotesList[holder.adapterPosition],holder.notes_layout)
            true
        }
    }
    fun randomColor() : Int{
        val list=ArrayList<Int>()
        list.add(R.color.c1)
        list.add(R.color.c2)
        list.add(R.color.c3)
        list.add(R.color.c4)
        list.add(R.color.c5)
        list.add(R.color.c6)
        list.add(R.color.c7)

        val seed=System.currentTimeMillis().toInt()
        val randomIndex= Random(seed).nextInt(list.size)
        return list[randomIndex]
    }
    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val notes_layout = itemView.findViewById<CardView>(R.id.cardView)
        val title= itemView.findViewById<TextView>(R.id.tv_title)

        val note= itemView.findViewById<TextView>(R.id.tv_note)
        val date= itemView.findViewById<TextView>(R.id.tv_date)
    }
    interface NotesClickListener{
        fun onItemClicked(note: Note)
        fun onLongItemClicked(note: Note,cardView: CardView)
    }
}