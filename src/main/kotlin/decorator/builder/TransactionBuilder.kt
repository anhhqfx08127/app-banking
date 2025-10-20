package decorator.builder

import BankAccount
import BasicTransaction
import Transaction
import decorator.LoggingDecorator
import decorator.NotificationDecorator
import decorator.TimeDecorator
import strategy.FeeStrategy
import strategy.NoFee

/**
 * 💰 TransactionBuilder — Builder cho các giao dịch có nhiều lớp Decorator.
 *
 * 👉 Áp dụng: Builder + Decorator
 */
class TransactionBuilder {
    private lateinit var account: BankAccount
    private var amount: Double = 0.0
    private var description: String = ""
    private var feeStrategy: FeeStrategy = NoFee()
    private var withLog = false
    private var withNotify = false
    private var withTime = false

    fun setAccount(acc: BankAccount) = apply { account = acc }
    fun setAmount(a: Double) = apply { amount = a }
    fun setDescription(desc: String) = apply { description = desc }
    fun setFeeStrategy(strategy: FeeStrategy) = apply { feeStrategy = strategy }

    fun enableLog() = apply { withLog = true }
    fun enableNotification() = apply { withNotify = true }
    fun enableTimestamp() = apply { withTime = true }

    fun build(): Transaction {
        var transaction: Transaction = BasicTransaction(account, amount, description, feeStrategy)

        if (withNotify) transaction = NotificationDecorator(account, transaction)
        if (withTime) transaction = TimeDecorator(transaction)
        if (withLog) transaction = LoggingDecorator(transaction)

        return transaction
    }
}
