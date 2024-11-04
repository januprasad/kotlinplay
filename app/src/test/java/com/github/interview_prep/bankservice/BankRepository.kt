package com.github.kotlin_tryout.bankservice

interface BankRepository {
    fun getBalance(): Int

    fun deposit(amount: Int)

    fun withdraw(amount: Int)
}

class BankService(
    private val bankRepository: BankRepository,
) {
    fun getBalance(): Int = bankRepository.getBalance()

    fun deposit(amount: Int) {
        bankRepository.deposit(amount)
    }

    fun withdraw(amount: Int) {
        bankRepository.withdraw(amount)
    }
}
