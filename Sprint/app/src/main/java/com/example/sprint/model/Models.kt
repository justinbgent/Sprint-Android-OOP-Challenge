package com.example.sprint.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class Technologies(val technologies: MutableList<Tech>)


data class Tech(val id: Int, val name: String, val description: String, var favorite: Boolean)

data class Structures(val structures: MutableList<Structure>)

data class Structure(val id: Int, val name: String, val age: String, var favorite: Boolean)

abstract class MyValues(open val id: Int, open val name: String, open var favorite: Boolean): Parcelable



// Had to make these bottom two otherwise the app crashed when I made the two primary data classes inherit from MyValues
@Parcelize
class StructureToMake(override val id: Int, override val name: String, val age: String, override var favorite: Boolean): MyValues(id, name, favorite), Parcelable

@Parcelize
class TechToMake(override val id: Int, override val name: String, val description: String, override var favorite: Boolean): MyValues(id, name, favorite), Parcelable
