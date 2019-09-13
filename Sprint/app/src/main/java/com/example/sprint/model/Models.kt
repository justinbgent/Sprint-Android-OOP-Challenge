package com.example.sprint.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


abstract class Vehicle(open val id: String, open val weight: Int, open var favorite: Boolean): Parcelable{
    abstract fun travel(): String
}

interface AirTravel{
    fun fly(): String
}

interface WaterTravel{
    fun sail(): String
}

interface GroundTravel{
    fun drive(): String
}

@Parcelize
class Airplane(override val id: String = "Airplane", override val weight: Int = 130000, override var favorite: Boolean = false): Vehicle(id, weight, favorite),
    AirTravel, Parcelable{
    override fun travel(): String {
        return "$id travels via ${fly()}"
    }

    override fun fly(): String {
        return "sky"
    }
}

@Parcelize
class Car(override val id: String = "Car", override val weight: Int = 3500, override var favorite: Boolean = false): Vehicle(id, weight, favorite),
    GroundTravel, Parcelable{
    override fun travel(): String {
        return "$id travels via ${drive()}"
    }

    override fun drive(): String {
        return "ground"
    }
}

@Parcelize
class Boat(override val id: String = "SpeedBoat", override val weight: Int = 8000, override var favorite: Boolean = false): Vehicle(id, weight, favorite),
    WaterTravel, Parcelable{
    override fun travel(): String {
        return "$id travels via ${sail()}"
    }

    override fun sail(): String {
        return "water"
    }
}

@Parcelize
class Skateboard(override val id: String = "Skateboard", override val weight: Int = 10, override var favorite: Boolean = false): Vehicle(id, weight, favorite),
    GroundTravel, Parcelable{
    override fun travel(): String {
        return "$id travels via ${drive()}"
    }

    override fun drive(): String {
        return "ground"
    }
}

@Parcelize
class Ripstick(override val id: String = "Ripstick", override val weight: Int = 15, override var favorite: Boolean = false): Vehicle(id, weight, favorite),
    GroundTravel, Parcelable{
    override fun travel(): String {
        return "$id travels via ${drive()}"
    }

    override fun drive(): String {
        return "ground"
    }
}

@Parcelize
class Unicycle(override val id: String = "Unicycle", override val weight: Int = 25, override var favorite: Boolean = false): Vehicle(id, weight, favorite),
    GroundTravel, Parcelable{
    override fun travel(): String {
        return "$id travels via ${drive()}"
    }

    override fun drive(): String {
        return "ground"
    }
}