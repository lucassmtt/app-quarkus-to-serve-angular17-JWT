package helpers

import com.google.gson.Gson
import com.google.gson.JsonIOException
import java.util.Objects

class JsonConverter {

    fun convert(obj : Objects, json : String): Objects? {
        var gson = Gson()

        try {
            var formattedObject = gson.fromJson(json, obj::class.java)

            return formattedObject;

        } catch (e : JsonIOException) {
            var msg = e.message;
            throw msg?.let { FormatterException(it) }!!
        }

    }

}