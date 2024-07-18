import com.google.common.base.Joiner
import org.bitcoinj.core.Address
import org.bitcoinj.core.Coin
import org.bitcoinj.core.ECKey
import org.bitcoinj.core.NetworkParameters
import org.bitcoinj.params.TestNet3Params
import org.bitcoinj.script.Script
import org.bitcoinj.wallet.DeterministicSeed
import org.bitcoinj.wallet.Wallet
import org.junit.Test

class WalletKeyTest {
    val key = ECKey()
    val params = TestNet3Params.get()

    @Test
    fun walletKeyTest() {
        // Define the network parameters (MainNet, TestNet, etc.)

        // Generate a new ECKey pair
        println("Private Key: " + key.privateKeyAsHex)
        println("Public Key: " + key.publicKeyAsHex)

        // Create a new wallet and add the key
        val wallet: Wallet = Wallet.createDeterministic(params, Script.ScriptType.P2PKH)
        wallet.importKey(key)

        // Print the wallet address

        val address: Address = wallet.currentReceiveAddress()
        println("Wallet Address: $address")
    }

    @Test
    fun seedTest() {
        // Define the network parameters (MainNet, TestNet, etc.)
        val params: NetworkParameters = TestNet3Params.get()

        // Generate a new ECKey pair
        println("Private Key: " + key.privateKeyAsHex)
        println("Public Key: " + key.publicKeyAsHex)

        // Create a new wallet and add the key
        val wallet: Wallet = Wallet.createDeterministic(params, Script.ScriptType.P2PKH)
        wallet.importKey(key)
        val seed: DeterministicSeed = wallet.getKeyChainSeed()
        println("Seed words are: " + Joiner.on(" ").join(seed.mnemonicCode))
        println("Seed birthday is: " + seed.creationTimeSeconds)
    }

    @Test
    fun seedTest2() {
        val seedCode =
            "yard impulse luxury drive today throw farm pepper survey wreck glass federal"
        val creationtime = 1409478661L
        val seed = DeterministicSeed(seedCode, null, "", creationtime)
        val restoredWallet = Wallet.fromSeed(params, seed, Script.ScriptType.P2PKH)
        val address: Address = restoredWallet.currentReceiveAddress()
        println("Wallet Address: $address")
    }

    @Test
    fun seedTest3() {
        val seedCode =
            "security loan vital cabin achieve depth hundred ice feel since poem chair"
        val creationtime = 1409478661L
        val seed = DeterministicSeed(seedCode, null, "", creationtime)
        val restoredWallet = Wallet.fromSeed(params, seed, Script.ScriptType.P2PKH)
        val address: Address = restoredWallet.currentReceiveAddress()
        val aa = restoredWallet.walletTransactions
        println("Wallet Address: $aa")
    }

    @Test
    fun balanceTest() {
        val seedCode =
            "security loan vital cabin achieve depth hundred ice feel since poem chair"
        val creationtime = 1409478661L
        val seed = DeterministicSeed(seedCode, null, "", creationtime)
        val wallet = Wallet.fromSeed(params, seed, Script.ScriptType.P2PKH)
        wallet.balance.add(Coin.valueOf(100000000))
        println("You have " + wallet.balance)
    }

    @Test
    fun sendMoney() {
    }
}
