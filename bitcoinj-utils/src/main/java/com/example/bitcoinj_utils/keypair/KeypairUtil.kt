package com.example.bitcoinj_utils.keypair

import com.example.bitcoinj_utils.keypair.model.SerializedKeypair
import org.bitcoinj.core.ECKey

object KeypairUtil {
    @JvmStatic
    fun createKey(): SerializedKeypair = SerializedKeypair(ECKey())
}
