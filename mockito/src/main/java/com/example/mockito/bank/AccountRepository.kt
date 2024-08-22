package com.example.mockito.bank

class AccountRepository {
    private val accounts = mutableMapOf<String, Account>()

    fun saveAccount(account: Account) {
        accounts[account.accountNumber] = account
        // Implementation for saving account to the database
    }

    fun getAccount(accountNumber: String): Account {
        // Implementation for fetching account from the database
        val account = accounts[accountNumber] ?: throw IllegalArgumentException("Account not found")
        return account
    }
}
