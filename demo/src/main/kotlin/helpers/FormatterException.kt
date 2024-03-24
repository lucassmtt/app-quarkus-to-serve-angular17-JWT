package helpers

import kotlin.jvm.Throws

class FormatterException(s: String) : Exception() {

    @Throws
    fun FormatterwException(msg : String) {
        throw Exception(msg)
    }
}