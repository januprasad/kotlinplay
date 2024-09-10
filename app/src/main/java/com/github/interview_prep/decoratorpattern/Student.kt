package com.github.interview_prep.decoratorpattern

fun interface Student {
    fun getTuition(): Double
}

data class BaseStudent(
    private val name: String,
    private val baseTuition: Double,
) : Student {
    override fun getTuition(): Double = baseTuition
}

abstract class StudentDecorator(
    protected val student: Student,
) : Student {
    override fun getTuition(): Double = student.getTuition()
}

class AcademicHonorDecorator(
    student: Student,
) : StudentDecorator(student) {
    override fun getTuition(): Double {
        return super.getTuition() * 0.9 // 10% discount for academic honors
    }
}

class FinancialAidDecorator(
    student: Student,
) : StudentDecorator(student) {
    override fun getTuition(): Double {
        if (student !is AcademicHonorDecorator) {
            return super.getTuition()
        }
        // Implement logic to determine if the student qualifies for financial aid
        // and apply the appropriate discount or fee
        return super.getTuition() * 0.8 // 20% discount for financial aid
    }
}

class ExtracurricularActivitiesDecorator(
    student: Student,
) : StudentDecorator(student) {
    override fun getTuition(): Double {
        // Implement logic to determine the number of extracurricular activities
        // and apply a discount or fee based on that
        return super.getTuition() * 1.1 // 10% fee for excessive extracurricular involvement
    }
}

val studenTutionFee = BaseStudent("JK", 10000.0)

fun main() {
    val student = BaseStudent("John Doe", 10000.0)
    val studentWithAcademicHonor = AcademicHonorDecorator(student)
    val studentWithFinancialAid = FinancialAidDecorator(studentWithAcademicHonor)
//    val studentWithExtracurricularActivities =
//        ExtracurricularActivitiesDecorator(studentWithFinancialAid)
//
    println("Base student tuition: ${student.getTuition()}")
    println("student With Academic Honor tuition: ${studentWithAcademicHonor.getTuition()}")
    println("student With FinancialAid tuition: ${studentWithFinancialAid.getTuition()}")
//    println("student With Extracurricular Activities tuition: ${studentWithExtracurricularActivities.getTuition()}")

    println("------------------------------------------------")
    println("Base student tuition: for ${studenTutionFee.getTuition()}")
    val studenTutionFeeValue = studenTutionFee.withAcademicHonor().withFinancialAid()
    println("student withAcademicHonor, withFinancialAid tuition: for ${studenTutionFeeValue.getTuition()}")
    println(studenTutionFeeValue.getTuition())
}

private fun Student.withAcademicHonor() = Student { this@withAcademicHonor.getTuition() * 0.9 }

private fun Student.withFinancialAid() = Student { this@withFinancialAid.getTuition() * 0.8 }
