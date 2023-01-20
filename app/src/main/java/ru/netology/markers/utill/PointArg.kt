package ru.netology.markers.utill

import android.os.Bundle
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

object PointXArg : ReadWriteProperty<Bundle, Float?> {
    override fun getValue(thisRef: Bundle, property: KProperty<*>): Float = thisRef.getFloat(property.name)


    override fun setValue(thisRef: Bundle, property: KProperty<*>, value: Float?) {
        thisRef.putSerializable(property.name, value)
    }
}

object PointYArg : ReadWriteProperty<Bundle, Float?> {
    override fun getValue(thisRef: Bundle, property: KProperty<*>): Float = thisRef.getFloat(property.name)


    override fun setValue(thisRef: Bundle, property: KProperty<*>, value: Float?) {
        thisRef.putSerializable(property.name, value)
    }
}