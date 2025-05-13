package com.example.bmicalculatorplus.ui.shopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bmicalculatorplus.R

/**
 * Fragment wyświetlający listę zakupów dla aktualnie wybranego przepisu.
 * Pobiera dane z [ShoppingListViewModel] i wyświetla je w postaci listy
 * składników (RecyclerView) wraz z tytułem przepisu.
 */
class ShoppingListFragment : Fragment() {

    private var recyclerView: RecyclerView? = null
    private lateinit var viewModel: ShoppingListViewModel
    private var titleText: TextView? = null

    /**
     * Tworzy i konfiguruje strukturę widoku fragmentu.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_shopping_list, container, false)
        titleText = view.findViewById(R.id.text_recipe_title)
        recyclerView = view.findViewById(R.id.recycler_view_shopping)
        recyclerView?.layoutManager = LinearLayoutManager(context)

        viewModel = ViewModelProvider(this)[ShoppingListViewModel::class.java]

        return view
    }

    /**
     * Obserwuje dane z ViewModelu i aktualizuje interfejs użytkownika,
     * w tym listę składników oraz tytuł przepisu.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.shoppingItems.observe(viewLifecycleOwner) { items ->
            recyclerView?.adapter = ShoppingListAdapter(items)
        }

        viewModel.recipeTitle.observe(viewLifecycleOwner) { title ->
            titleText?.text = getString(R.string.ingredients_for, title)
        }

        viewModel.loadItemsFromRandomRecipe(requireContext())
    }

    /**
     * Czyszczenie odniesień do widoków, aby zapobiec wyciekom pamięci.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        recyclerView = null
        titleText = null
    }
}
