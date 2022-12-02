package hf.api.exception

class RequestValidationException : Exception() {
    lateinit var messages: Collection<String>

    fun withMessages(messages: Collection<String>): RequestValidationException {
        this.messages = messages
        return this
    }
}