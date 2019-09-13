package com.example.sprint.view

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.interfaces.R
import com.example.sprint.view.ItemListActivity.Companion.favorite
import com.example.sprint.view.ItemListActivity.Companion.vehicles
import com.example.sprint.model.Vehicle
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.item_detail.view.*

class ItemDetailFragment : Fragment() {

    private var item: Vehicle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        TODO: This is just to make my added code easy to find
        item = arguments?.getParcelable<Parcelable>(ARG_ITEM_ID) as Vehicle?
        activity?.toolbar_layout?.title = item?.id ?: "Null"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        TODO: This is just to make my added code easy to find. My code is from here down
        val rootView = inflater.inflate(R.layout.item_detail, container, false)
        
        rootView.item_detail.text = item?.travel()

        if (favorite){
            rootView.btn_favorite.text = "Unfavorite"
        }else{
            rootView.btn_favorite.text = "Favorite"
        }

        rootView.btn_favorite.setOnClickListener {
            if (!favorite){
                rootView.btn_favorite.text = "Unfavorite"
                favorite = true
                updateObject?.updateFavoriteState(favorite, item)
            }else{
                rootView.btn_favorite.text = "Favorite"
                favorite = false
                updateObject?.updateFavoriteState(favorite, item)
            }
        }
        return rootView
    }

    interface UpdateArrayObject{
        fun updateFavoriteState(favorite: Boolean, item: Vehicle?){
            vehicles.forEach {
                if (it.id == item?.id){
                    it.favorite = ItemListActivity.favorite
                }
            }
        }
    }
    private var updateObject: UpdateArrayObject? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is UpdateArrayObject){
            updateObject = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        updateObject = null
    }

    companion object {
        const val ARG_ITEM_ID = "item_id"
    }
}
