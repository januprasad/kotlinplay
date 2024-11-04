package com.github.kotlin_tryout.bankservice

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class BankServiceTestMockk {
    @RelaxedMockK
    lateinit var bankService: BankService

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun testGetBalance() {
        val mockBankRepository = mockk<BankRepository>()
        val expectedBalance = 1000

        every { mockBankRepository.getBalance() } returns expectedBalance

        val bankService = BankService(mockBankRepository)
        val actualBalance = bankService.getBalance()

        assertEquals(expectedBalance, actualBalance)
    }

    @Test
    fun testDeposit() {
        val mockBankRepository = mockk<BankRepository>()
        val amountToDeposit = 500
        val bankService = BankService(mockBankRepository)
        bankService.deposit(amountToDeposit)

        verify { mockBankRepository.deposit(amountToDeposit) }
    }

    @Test
    fun testWithdraw() {
        val mockBankRepository = mockk<BankRepository>()
        val amountToWithdraw = 300
        val bankService = BankService(mockBankRepository)
        bankService.withdraw(amountToWithdraw)

        verify { mockBankRepository.withdraw(amountToWithdraw) }
    }

    @Test
    fun relaxedMock() {
        // No need to stub methods, they will return default values
        val result =
            bankService.getBalance() // Will return the default value for the method's return type
        println(result)
    }
}
