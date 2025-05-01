package com.example.bmicalculatorplus.ui.shopping;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bmicalculatorplus.R;
import com.example.bmicalculatorplus.ui.model.ShoppingItem;

import java.util.List;

/**
 * Adapter dla listy zakupów, wykorzystywany przez komponent RecyclerView.
 * Wyświetla listę elementów {@link ShoppingItem} jako pola typu CheckBox,
 * umożliwiając zaznaczanie/odznaczanie poszczególnych produktów.
 */
public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ViewHolder> {
    private final List<ShoppingItem> items;

    public ShoppingListAdapter(List<ShoppingItem> items) {
        this.items = items;
    }

    /**
     * Klasa ViewHolder przechowująca odniesienie do widoku CheckBoxa.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final CheckBox checkBox;

        /**
         * Tworzy nowy ViewHolder i przypisuje CheckBox z layoutu.
         *
         * @param view widok pojedynczego elementu listy (item_shopping.xml)
         */
        public ViewHolder(View view) {
            super(view);
            checkBox = view.findViewById(R.id.checkbox_item);
        }
    }

    /**
     * Tworzy nowy ViewHolder na podstawie layoutu XML.
     *
     * @param parent   rodzic ViewHoldera (RecyclerView)
     * @param viewType typ widoku (nieużywany w tym przypadku)
     * @return nowo utworzony ViewHolder
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shopping, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Wypełnia dany ViewHolder danymi z konkretnego elementu listy.
     * Ustawia tekst i stan zaznaczenia CheckBoxa oraz obsługuje zmianę jego stanu.
     *
     * @param holder   ViewHolder do wypełnienia
     * @param position pozycja elementu w liście
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ShoppingItem item = items.get(position);
        holder.checkBox.setText(item.getName());
        holder.checkBox.setChecked(item.isChecked());
        holder.checkBox.setOnCheckedChangeListener((btnView, isChecked) -> item.setChecked(isChecked));
    }

    /**
     * Zwraca liczbę elementów w liście zakupów.
     *
     * @return liczba elementów
     */
    @Override
    public int getItemCount() {
        return items.size();
    }
}

