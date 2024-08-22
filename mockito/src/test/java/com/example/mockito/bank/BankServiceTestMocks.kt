package com.example.mockito.bank

import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.mock

class BankServiceTestMocks {
    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `createAccount_shouldCallInternalMethod`() {
        val accountRepository = spy(AccountRepository())
        val bankService = BankService(accountRepository)

        val accountNumber = "12345"
        val initialBalance = 1000.0
        TestCase.assertEquals("Account created", bankService.createAccount(accountNumber, initialBalance))
        val account = bankService.getAccount(accountNumber)
        verify(accountRepository).saveAccount(account)
    }

    @Test
    fun `fetchBalance_shouldCallInternalMethod`() {
        val accountRepository = mock<AccountRepository>()
        `when`(accountRepository.getAccount("12345"))
            .thenReturn(Account("12345", 1000.0))
        val bankService = BankService(accountRepository)

        val balance = bankService.fetchBalance("12345")

        assertEquals(1000.0, balance)
        verify(accountRepository).getAccount("12345")
    }
}
