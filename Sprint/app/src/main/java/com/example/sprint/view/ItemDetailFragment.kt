package com.example.sprint.view

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.interfaces.R
import com.example.sprint.viewmodel.MyValues
import com.example.sprint.viewmodel.NeededValues.favorite
import com.example.sprint.viewmodel.NeededValues.structuresAndTechList
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.item_detail.view.*

class ItemDetailFragment : Fragment() {

    private var item: MyValues? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        TODO: This is just to make my added code easy to find
        item = arguments?.getParcelable<Parcelable>(ARG_ITEM_ID) as MyValues?
        activity?.toolbar_layout?.title = item?.name ?: "Null"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        TODO: This is just to make my added code easy to find. My code is from here down
        val rootView = inflater.inflate(R.layout.item_detail, container, false)
        
//        rootView.item_detail.text = item?.

        if (favorite){
            rootView.btn_favorite.text = "Unfavorite"
        }else{
            rootView.btn_favorite.text = "Favorite"
        }

        rootView.btn_favorite.setOnClickListener {
            if (!favorite){
                rootView.btn_favorite.text = "Unfavorite"
                favorite = true
                structuresAndTechList.forEach {
                    if (it.id == item?.id){
                        it.favorite = favorite
                    }
                }
                updateObject?.updateFavoriteState(favorite)
            }else{
                rootView.btn_favorite.text = "Favorite"
                favorite = false
                updateObject?.updateFavoriteState(favorite)
            }
        }
        return rootView
    }

    interface FavoriteState{
        fun updateFavoriteState(favorite: Boolean)
    }
    private var updateObject: FavoriteState? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is FavoriteState){
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
