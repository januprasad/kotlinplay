package com.github.kotlin_tryout.bankservice

import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BankServiceTest {
    @Mock
    lateinit var bankRepository: BankRepository

    @Test
    fun testGetBalance() {
        // Given
        val expectedBalance = 1000
        `when`(bankRepository.getBalance()).thenReturn(expectedBalance)

        // When
        val actualBalance = BankService(bankRepository).getBalance()

        // Then
        assertEquals(expectedBalance, actualBalance)
    }

    @Test
    fun testDeposit() {
        // Given
        val amountToDeposit = 500

        // When
        BankService(bankRepository).deposit(amountToDeposit)

        // Then
        verify(bankRepository).deposit(amountToDeposit)
    }

    @Test
    fun testWithdraw() {
        // Given
        val amountToWithdraw = 300

        // When
        BankService(bankRepository).withdraw(amountToWithdraw)

        // Then
        verify(bankRepository).withdraw(amountToWithdraw)
    }
}
