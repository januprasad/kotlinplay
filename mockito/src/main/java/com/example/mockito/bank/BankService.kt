package com.example.mockito.bank

class BankService(
    private val accountRepository: AccountRepository,
) {
    fun createAccount(
        accountNumber: String,
        initialBalance: Double,
    ): String {
        val account = Account(accountNumber, initialBalance)
        accountRepository.saveAccount(account)
        return "Account created"
    }

    fun fetchBalance(accountNumber: String): Double {
        val account = accountRepository.getAccount(accountNumber)
        return account.balance
    }

    fun getAccount(accountNumber: String): Account = accountRepository.getAccount(accountNumber)
}

data class Account(
    val accountNumber: String,
    var balance: Double,
)
