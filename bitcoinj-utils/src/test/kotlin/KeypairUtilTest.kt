import com.example.bitcoinj_utils.keypair.KeypairUtil
import org.bitcoinj.core.ECKey
import org.bitcoinj.core.Utils
import org.junit.Assert
import org.junit.Test

class KeypairUtilTest {
    @Test
    fun createKeypair() {
        val serialized = KeypairUtil.createKey()

        val deserialized =
            ECKey.fromPrivateAndPrecalculatedPublic(
                Utils.HEX.decode(serialized.privateKeyHex),
                Utils.HEX.decode(serialized.publicKeyHex),
            )

        println(serialized)
        println(deserialized)

        Assert.assertFalse(deserialized.isPubKeyOnly)
        Assert.assertTrue(deserialized.hasPrivKey())

        Assert.assertEquals(
            serialized.publicKeyHex,
            deserialized.publicKeyAsHex,
        )
        Assert.assertEquals(
            serialized.privateKeyHex,
            deserialized.privateKeyAsHex,
        )
    }
}
