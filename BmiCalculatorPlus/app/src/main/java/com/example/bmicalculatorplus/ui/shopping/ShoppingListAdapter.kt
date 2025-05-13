package com.example.bmicalculatorplus.ui.shopping

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.bmicalculatorplus.R
import com.example.bmicalculatorplus.ui.model.ShoppingItem

/**
 * Adapter dla listy zakupów, wykorzystywany przez komponent RecyclerView.
 * Wyświetla listę elementów [ShoppingItem] jako pola typu CheckBox,
 * umożliwiając zaznaczanie/odznaczanie poszczególnych produktów.
 */
class ShoppingListAdapter(private val items: List<ShoppingItem>) :
    RecyclerView.Adapter<ShoppingListAdapter.ViewHolder>() {

    /**
     * Klasa ViewHolder przechowująca odniesienie do widoku CheckBoxa.
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val checkBox: CheckBox = view.findViewById(R.id.checkbox_item)
    }

    /**
     * Tworzy nowy ViewHolder na podstawie layoutu XML.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_shopping, parent, false)
        return ViewHolder(view)
    }

    /**
     * Wypełnia dany ViewHolder danymi z konkretnego elementu listy.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.checkBox.text = item.name
        holder.checkBox.isChecked = item.isChecked
        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            item.isChecked = isChecked
        }
    }

    /**
     * Zwraca liczbę elementów w liście zakupów.
     */
    override fun getItemCount(): Int = items.size
}
