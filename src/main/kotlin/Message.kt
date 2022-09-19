class Message(private val name: String, private val word: String) {
    override fun toString() = "$name disse o seguinte $word para alguém"
}