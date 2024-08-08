package com.github.interview_prep

import com.github.interview_prep.dbservice.DBService
import com.github.interview_prep.dbservice.School
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Test

class DBServiceTests {
    @Test
    fun `getSchool Info from database`() {
        val mockSchool = mockk<School>()
        val mockSDbService = mockk<DBService>()

        every { mockSchool.name } returns "ksbs"
        every { mockSDbService.getSchoolInfo("Ksbs") } returns mockSchool

        val result = mockSDbService.getSchoolInfo("Ksbs")

        // Verify that the expected email details are printed
        verify(exactly = 1) { mockSDbService.getSchoolInfo("Ksbs") }

        assertEquals("ksbs", result.name)
    }

    @Test
    fun `getVersion Info from database`() {
        val mockSDbService = mockk<DBService>()

        // when
        every { mockSDbService.getDBVersion() } returns "1.0"

        // then
        val result = mockSDbService.getDBVersion()

        // Verify that the expected email details are printed
        verify { mockSDbService.getDBVersion() }

        assertEquals("1.0", result)
    }
}
