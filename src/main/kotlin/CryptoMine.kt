import java.util.stream.IntStream

class CryptoMine {
    private val blocksToMine = 5L
    private val minersHired = 20
    fun mining(blockchain: Blockchain) {
        IntStream.rangeClosed(1, minersHired)
            .mapToObj { Miner(it) }
            .limit(blocksToMine)
            .forEach { miner -> println(miner.createNewBlock(blockchain, miner)) }
    }

    class Miner(val minerID: Int) {
        fun createNewBlock(blockchain: Blockchain, miner: Miner): Block {
            val block = Block(blockchain, miner)
            return if (blockchain.addBlockToChain(block)) block else throw Exception("Invalid block")
        }
    }
}