package com.example.sprint.model

import kotlinx.android.parcel.Parcelize
import java.io.Serializable


data class Technologies(val technologies: MutableList<Tech>)

@Parcelize
class Tech(id: Int, name: String, val description: String, favorite: Boolean): MyValues(id, name, favorite)

data class Structures(val structures: MutableList<Structure>)

class Structure(id: Int, name: String, val age: String, favorite: Boolean): MyValues(id, name, favorite)

abstract class MyValues(val id: Int, val name: String, var favorite: Boolean)
