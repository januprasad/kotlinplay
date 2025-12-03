package com.github.kotlin_tryout.decoratorpattern

data class RifileDataHolder(
    val name: String,
    val zoom: Int,
    val soundEnabled: Boolean = true,
)

interface Rifile {
    fun provide(): RifileDataHolder
}

class BasicRifile(
    val rifileData: RifileDataHolder,
) : Rifile {
    override fun provide(): RifileDataHolder = rifileData
}

abstract class BasicRifileDecorator(
    protected val rifile: Rifile,
) : Rifile {
    override fun provide(): RifileDataHolder = rifile.provide()
}

class ZoomInAction(
    val basicRifile: Rifile,
) : BasicRifileDecorator(basicRifile) {
    override fun provide(): RifileDataHolder = basicRifile.provide().copy(zoom = 2)
}

class FitSilencerAction(
    val basicRifile: Rifile,
) : BasicRifileDecorator(basicRifile) {
    override fun provide(): RifileDataHolder = basicRifile.provide().copy(soundEnabled = false)
}

fun main() {
    val basicRifile = BasicRifile(RifileDataHolder("AK-47", 1))
    println(basicRifile.provide())

    val zoomInRifile = ZoomInAction(basicRifile)
    println(zoomInRifile.provide())

    val fitSilencerRifile = FitSilencerAction(zoomInRifile)
    println(fitSilencerRifile.provide())
}
