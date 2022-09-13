import java.nio.charset.StandardCharsets
import java.security.MessageDigest

class Block(private val id: Int, private val timeStamp: Long) {
    private var prevBlockHash: String? = null
    val blockHash = applySha256("$id$timeStamp")
    fun setPrevHash(prevBlockHash: String) = kotlin.run { this.prevBlockHash = prevBlockHash }

    override fun toString() = "Block:\nId: $id\nTimestamp: $timeStamp\n" +
            "Hash of the previous block:\n$prevBlockHash\nHash of the block:\n$blockHash\n"

    private fun applySha256(input: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val hash = digest.digest(input.toByteArray(StandardCharsets.UTF_8))
        val hexString = StringBuilder()
        for (elem in hash) {
            val hex = Integer.toHexString(0xff and elem.toInt())
            if (hex.length == 1) hexString.append('0')
            hexString.append(hex)
        }
        return hexString.toString()
    }
}