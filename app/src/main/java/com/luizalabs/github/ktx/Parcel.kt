package com.luizalabs.github.ktx

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import com.luizalabs.domain.bean.Repository
import org.json.JSONObject
import java.math.BigDecimal
import java.math.BigInteger
import java.util.*


interface KParcelable : Parcelable {
    override fun describeContents() = 0
    override fun writeToParcel(dest: Parcel, flags: Int)
}

inline fun <reified T> parcelableCreator(
    crossinline create: (Parcel) -> T
) = object : Parcelable.Creator<T> {
    override fun createFromParcel(source: Parcel) = create(source)
    override fun newArray(size: Int) = arrayOfNulls<T>(size)
}

inline fun <reified T> parcelableClassLoaderCreator(
    crossinline create: (Parcel, ClassLoader) -> T
) = object : Parcelable.ClassLoaderCreator<T> {
    override fun createFromParcel(
        source: Parcel,
        loader: ClassLoader
    ) = create(source, loader)

    override fun createFromParcel(
        source: Parcel
    ) = createFromParcel(source, T::class.java.classLoader!!)

    override fun newArray(size: Int) = arrayOfNulls<T>(size)
}

inline fun Parcel.readBoolean() = readInt() != 0

inline fun Parcel.writeBoolean(value: Boolean) = writeInt(if (value) 1 else 0)

inline fun <reified T : Enum<T>> Parcel.readEnum() = readString()?.let { enumValueOf<T>(it) }

inline fun <T : Enum<T>> Parcel.writeEnum(value: T?) = writeString(value?.name)

inline fun <T> Parcel.readNullable(reader: () -> T) = if (readInt() != 0) reader() else null

inline fun <T> Parcel.writeNullable(value: T?, writer: (T) -> Unit) {
    if (value != null) {
        writeInt(1)
        writer(value)
    } else {
        writeInt(0)
    }
}

fun Parcel.readDate() = readNullable { Date(readLong()) }

fun Parcel.writeDate(value: Date?) = writeNullable(value) { writeLong(it.time) }

fun Parcel.readBigInteger() = readNullable { BigInteger(createByteArray()) }

fun Parcel.writeBigInteger(
    value: BigInteger?
) = writeNullable(value) { writeByteArray(it.toByteArray()) }

fun Parcel.readBigDecimal() = readNullable { BigDecimal(BigInteger(createByteArray()), readInt()) }

fun Parcel.writeBigDecimal(value: BigDecimal?) = writeNullable(value) {
    writeByteArray(it.unscaledValue().toByteArray())
    writeInt(it.scale())
}

fun <T : Parcelable> Parcel.readTypedObjectCompat(c: Parcelable.Creator<T>) = readNullable {
    c.createFromParcel(this)
}

fun <T : Parcelable> Parcel.writeTypedObjectCompat(
    value: T?, parcelableFlags: Int
) = writeNullable(value) { it.writeToParcel(this, parcelableFlags) }

final class ListTypeToken<E : Any> : TypeToken<List<E>>()

final class MapTypeToken<String, V : Any> : TypeToken<Map<String, V>>()

final class MutableMapTypeToken<String, V : Any> : TypeToken<MutableMap<String, V>>()



fun Bundle.putNumber(key: String, number: Number) {
    when (number) {
        is Byte -> putByte(key, number)
        is Double -> putDouble(key, number)
        is Float -> putFloat(key, number)
        is Int -> putInt(key, number)
        is Long -> putLong(key, number)
        is Short -> putShort(key, number)
    }
}

fun Bundle.putMap(key: String, map: Map<String, Any>) {

    Bundle().apply {  }
}

fun Parcel.putNumber(number: Number) {
    when (number) {
        is Byte -> writeByte(number)
        is Double -> writeDouble(number)
        is Float -> writeFloat(number)
        is Int -> writeInt(number)
        is Long -> writeLong(number)
        is Short -> writeInt(number.toInt())
    }
}

fun bundle(key: String, value: JsonPrimitive) {
    val bundle = Bundle().apply {
        if (value.isBoolean) {
            putBoolean(key, value.asBoolean)
        } else if (value.isNumber) {
            put(key, value.asBoolean)
            value.asNumber
        } else if (value.isString) {
            value.asString
        }
    }
    if (value.isBoolean) {
        value.asBoolean
    } else if (value.isNumber) {
        value.asNumber
    } else if (value.isString) {
        value.asString
    }


}
fun rec(array: JsonArray): List<Any> {
    JsonPri
    return array.map {
        when (it) {
            is JsonArray -> rec(it.asJsonArray)
            is JsonObject -> rec(it.asJsonObject)
            is JsonNull -> it.asJsonNull as Any
            else -> it.asJsonPrimitive as Any
        }
    }.toList()
}

fun banana(json: JSONObject): Bundle {
    json
}
fun <E : Parcelable> rec2(array: JsonArray): List<E> {
    array.map {
        val element = when (it) {
            is JsonArray -> rec(it.asJsonArray)
            // is JsonObject -> rec(it.asJsonObject)
            is JsonNull -> null
            else -> it.
        }
    }
}

fun rec(tree: JsonObject): Map<String, Any> {
    return tree.entrySet()
        .map {
            val value = when (it.value) {
                is JsonArray -> rec(it.value.asJsonArray)
                is JsonObject -> rec(it.value.asJsonObject)
                is JsonNull -> it.value.asJsonNull as Any
                else -> it.value.asJsonPrimitive.
            }
            it.key to value
        }
        .toList()
        .toMap()
}

fun foo(value: Repository, json: JSONObject) {
    val gson = Gson()


    val type = object : TypeToken<Map<String, Any>>() {}.type
    val m: MapTypeToken<String, Any> =
        gson.fromJson(gson.toJson(value), MapTypeToken<String, Any>().type)

    val n: Map<String, Any> = rec(gson.toJsonTree(value).asJsonObject).toMutableMap()


    val p = Parcel.obtain()
    p.write
    TODO()
}