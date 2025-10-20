package decorator

import BankAccount
import strategy.FeeStrategy

interface Transaction {
    fun execute()
}

class BasicTransaction(
    private val account: BankAccount,
    private val amount: Double,
    private val description: String,
    private val feeStrategy: FeeStrategy
) : Transaction {
    override fun execute() {
        val fee = feeStrategy.fee(amount)
        val net = amount - fee

        if(amount >= 0) {
            account.deposit(net, description)
        } else {
            account.withdraw(net, description)
        }
    }
}