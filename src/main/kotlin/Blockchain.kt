import java.security.MessageDigest
import java.util.Random
import java.util.concurrent.atomic.AtomicInteger

class Blockchain{
    private var chain = mutableMapOf<Int, Block>()
    var difficulty = AtomicInteger(0)
    var blockID = AtomicInteger(1)
    private var content = mutableListOf<Message>()

    fun addBlockToChain(block: Block): Boolean {
        return if (validateBlock(block)) {
            content = Chat().addMessagesToBlock().toMutableList()
            block.organizedContent = collectAndOrganizeContent()
            block.difficultiesComparison = compareAndUpdateDifficulty(block)
            chain[block.blockId] = block
            true
        } else false
    }

    @Synchronized
    fun compareAndUpdateDifficulty(block: Block): String {
        return when (true) {
            block.hashingTime > 2 ->
                difficulty.set(difficulty.decrementAndGet().coerceAtLeast(0)).let { "N was decreased by 1" }
            block.hashingTime < 1 ->
                difficulty.set(difficulty.incrementAndGet()).let { "N was increased to ${difficulty.get()}" }
            else -> "N stays the same"
        }
    }

    fun getPreviousHash(block: Block) = if (block.blockId == 1) "0" else chain[block.blockId - 1]?.blockHash

    private fun collectAndOrganizeContent(): String {
        return if (content.isEmpty()) "no messages" else content.joinToString("\n") { it.toString() }
    }

    private fun validateBlock(block: Block): Boolean {
        return when (true) {
            block.blockHash.startsWith("0".repeat(difficulty.get())) && block.blockId == 1 -> "0" == block.prevBlockHash
            else -> block.prevBlockHash == chain[block.blockId - 1]?.blockHash
        }
    }
}