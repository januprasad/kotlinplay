package com.example.bitcoinj_utils.keypair.model

import org.bitcoinj.core.ECKey

data class SerializedKeypair(
    val publicKeyHex: String,
    val privateKeyHex: String,
) {
    constructor(source: ECKey) : this(
        publicKeyHex = source.publicKeyAsHex,
        privateKeyHex = source.privateKeyAsHex,
    )
}
