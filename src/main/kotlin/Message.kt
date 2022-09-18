class Message(private val name: String, private val word: String) {
    override fun toString() = "$name said that $word someone"
}