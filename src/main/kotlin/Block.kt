import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.util.*
import kotlin.system.measureTimeMillis

class Block(blockchain: Blockchain, miner: CryptoMine.Miner) {
    val blockId = blockchain.blockID.getAndIncrement()
    private var minerID = miner.minerID
    private val timeStamp = System.currentTimeMillis()
    private var miningDifficulty = blockchain.difficulty.get()
    var prevBlockHash = blockchain.getPreviousHash(this)
    var blockHash = generateBlockHash("0".repeat(miningDifficulty))
    private var magicNumber = 0
    var hashingTime = 0L
    var difficultiesComparison = ""
    var organizedContent = ""

    override fun toString(): String {
        return "\nBlock:\nCreated by miner # $minerID\nId: $blockId\nTimestamp: $timeStamp\n" +
                "Magic number: $magicNumber\nHash of the previous block:\n$prevBlockHash\n" +
                "Hash of the block:\n$blockHash\nBlock data:\n$organizedContent\n" +
                "Block was generating for $hashingTime seconds\n$difficultiesComparison"
    }

    private fun generateBlockHash(zerosPrefix: String): String {
        hashingTime = measureTimeMillis {
            do {
                magicNumber = Random().nextInt(100000000)
                blockHash = applySHA256("$blockId$minerID$timeStamp$magicNumber$prevBlockHash$organizedContent")
            } while (!blockHash.startsWith(zerosPrefix))
        } /*/ 1000 % 60*/
        return blockHash
    }

    private fun applySHA256(input: String): String {
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