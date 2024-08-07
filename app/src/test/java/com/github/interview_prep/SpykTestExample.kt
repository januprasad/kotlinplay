package com.github.interview_prep

import io.mockk.every
import io.mockk.spyk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Test

class SpykTestExample {
    @Test
    fun `getSchoolInfo test from `() {
//        val school = School("Ksbs")
        val dbService = DBService()
//        val spykSchool = spyk<School>(school)
        val spykDbService = spyk<DBService>(dbService)

//        every { spykSchool.name } returns "ksbs"
        every { spykDbService.getDBVersion() } returns "1.0.2"

        val result = spykDbService.getSchoolInfo("Ksbs")
        val db = spykDbService.getDBVersion()

        // Verify that the expected email details are printed
        verify(exactly = 1) { spykDbService.getSchoolInfo("Ksbs") }

        assertEquals("Ksbs", result.name)
        assertEquals("1.0.2", db)
    }
}
