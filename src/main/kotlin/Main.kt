import decorator.BasicTransaction
import factory.AccountFactory
import factory.AccountType
import observer.NotificationService
import strategy.FixedFee
import strategy.NoFee
import strategy.PercentFee


val percentFee = PercentFee(2.0)
val fixedFee = FixedFee(3.0)
val noFee = NoFee()

fun main() {
    val bankAccount = BankAccount(
        account = AccountFactory.create(
            accountType = AccountType.DEPOSIT,
            accountNumber = "98877797",
            accountName = "HOANG QUOC ANH"
        ),
        balance = 1000.0
    )
    val salaryAccount = BankAccount(
        account = AccountFactory.create(
            accountType = AccountType.SALARY,
            accountNumber = "99998888",
            accountName = "HOANG QUOC ANH"
        ),
        balance = 0.0
    )

    bankAccount.addObserver(NotificationService())
    salaryAccount.addObserver(NotificationService())

    val depositTransaction = BasicTransaction(
        account = salaryAccount,
        amount = 1000.0,
        description = "Nộp tiền từ ATM",
        feeStrategy = noFee
    )
    depositTransaction.execute()

    val salaryTransaction = BasicTransaction(
        account = salaryAccount,
        amount = 3000.0,
        description = "Thanh toán lương tháng 10/2025",
        feeStrategy = noFee
    )
    salaryTransaction.execute()

    val withdrawTransaction = BasicTransaction(
        account = salaryAccount,
        amount = 500.0,
        description = "Rút tiền từ ATM",
        feeStrategy = fixedFee
    )
    withdrawTransaction.execute()
}