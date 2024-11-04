package com.github.solid

interface IBankingService {
    fun createBankAccount(account: BasicAccount)
    fun fetchBalance()
}
data class BasicAccount(val name:String)

open class Logger{
    open fun log(message: String) {
        println(message)
    }
}
class PrintLogger : Logger() {
  override fun log(message: String){
      println("Printlogger "+message)
  }
}


class HDFCBankService : IBankingService{
    override fun createBankAccount(basicAccount: BasicAccount) {

        doSomething(basicAccount)

    }

    private fun doSomething(basicAccount: BasicAccount) {

    }

    override fun fetchBalance() {
       authBalance()
    }

    private fun authBalance() {

    }
}

class ICCIBankService : IBankingService{
    override fun createBankAccount(account: BasicAccount) {
        doSomething(account)
    }

    private fun doSomething(basicAccount: Any) {
        TODO("Not yet implemented")
    }

    override fun fetchBalance() {
        authBalance()
    }

    private fun authBalance() {
        TODO("Not yet implemented")
    }

}

class BankProccesor(){
   fun  process(bankingService: IBankingService){

    }
}

interface LongerClick {
    fun longerClick()
}
interface ShoterClick {
    fun longerClick()
}