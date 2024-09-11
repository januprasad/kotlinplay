package com.github.interview_prep.decoratorpattern

data class MachineGunDataHolder(
    val name: String,
    val zoom: Int,
    val soundEnabled: Boolean = true,
)

fun interface MachineGun {
    fun provide(): MachineGunDataHolder
}

data class BaseMachineGun(
    private val machineGunDataHolder: MachineGunDataHolder,
) : MachineGun {
    override fun provide(): MachineGunDataHolder = machineGunDataHolder
}

fun MachineGun.withBasicRifile() = MachineGun { this@withBasicRifile.provide() }

fun MachineGun.withBasicRifileDecorator() = MachineGun { this@withBasicRifileDecorator.provide() }

fun MachineGun.withZoomInActionDecorator() =
    MachineGun {
        this@withZoomInActionDecorator.provide().copy(
            zoom = 2,
        )
    }

fun MachineGun.withFitSilencerActionDecorator() =
    MachineGun {
        this@withFitSilencerActionDecorator.provide().copy(
            soundEnabled = false,
        )
    }

//
// This snippet can be converted using functional interface
// class BasicRifile(
//    val rifileData: RifileDataHolder,
// ) : Rifile {
//    override fun provide(): RifileDataHolder = rifileData
// }
//
// abstract class BasicRifileDecorator(
//    protected val rifile: Rifile,
// ) : Rifile {
//    override fun provide(): RifileDataHolder = rifile.provide()
// }
//
// class ZoomInAction(
//    val basicRifile: Rifile,
// ) : BasicRifileDecorator(basicRifile) {
//    override fun provide(): RifileDataHolder = basicRifile.provide().copy(zoom = 2)
// }
//
// class FitSilencerAction(
//    val basicRifile: Rifile,
// ) : BasicRifileDecorator(basicRifile) {
//    override fun provide(): RifileDataHolder = basicRifile.provide().copy(soundEnabled = false)
// }

fun main() {
    val basicMachineGun = BaseMachineGun(MachineGunDataHolder("AK-47", 1))
    println(basicMachineGun.provide())
    println(
        basicMachineGun
            .withBasicRifile()
            .withBasicRifileDecorator()
            .withZoomInActionDecorator()
            .withFitSilencerActionDecorator()
            .provide(),
    )
}
