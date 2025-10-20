import decorator.LoggingDecorator
import decorator.NotificationDecorator
import decorator.TimeDecorator
import factory.AccountFactory
import factory.AccountType
import observer.NotificationService
import strategy.FixedFee
import strategy.NoFee
import strategy.PercentFee

// Các chiến lược tính phí (Strategy)
val percentFee = PercentFee(2.0)  // Phí 2% số tiền
val fixedFee = FixedFee(3.0)      // Phí cố định 3
val noFee = NoFee()               // Không tính phí

fun main() {
    // Tạo các tài khoản
    val depositAccount = BankAccount(
        account = AccountFactory.create(
            accountType = AccountType.DEPOSIT,
            accountNumber = "98877797",
            accountName = "HOANG QUOC ANH"
        ),
        balance = 0.0
    )

    val salaryAccount = BankAccount(
        account = AccountFactory.create(
            accountType = AccountType.SALARY,
            accountNumber = "99998888",
            accountName = "HOANG QUOC ANH"
        ),
        balance = 0.0
    )

    println("----------")

    // Thêm Observer để theo dõi biến động số dư
    depositAccount.addObserver(NotificationService())
    salaryAccount.addObserver(NotificationService())

    // Giao dịch NẠP TIỀN
    val depositTx = BasicTransaction(
        account = depositAccount,
        amount = 1000.0,
        description = "Nộp tiền mặt tại quầy",
        feeStrategy = noFee
    )

    // Thêm decorator
    val decoratedDepositTx = LoggingDecorator(
        TimeDecorator(
            NotificationDecorator(depositAccount, depositTx)
        )
    )

    decoratedDepositTx.execute()

    println("----------")

    // Giao dịch NHẬN LƯƠNG
    val salaryTx = BasicTransaction(
        account = salaryAccount,
        amount = 5000.0,
        description = "Thanh toán lương tháng 10/2025",
        feeStrategy = noFee
    )

    val decoratedSalaryTx = LoggingDecorator(
        TimeDecorator(
            NotificationDecorator(salaryAccount, salaryTx)
        )
    )

    decoratedSalaryTx.execute()

    println("----------")

    // Giao dịch RÚT TIỀN (có phí cố định)
    val withdrawTx = BasicTransaction(
        account = salaryAccount,
        amount = -500.0,
        description = "Rút tiền tại ATM",
        feeStrategy = fixedFee
    )

    val decoratedWithdrawTx = LoggingDecorator(
        TimeDecorator(
            NotificationDecorator(salaryAccount, withdrawTx)
        )
    )

    decoratedWithdrawTx.execute()

    println("----------")

    // Giao dịch MUA HÀNG (có phí phần trăm)
    val purchaseTx = BasicTransaction(
        account = salaryAccount,
        amount = -1200.0,
        description = "Thanh toán đơn hàng Shopee",
        feeStrategy = percentFee
    )

    val decoratedPurchaseTx = LoggingDecorator(
        TimeDecorator(
            NotificationDecorator(salaryAccount, purchaseTx)
        )
    )

    decoratedPurchaseTx.execute()

    println("----------")
    depositAccount.freeze()

    val withdrawTxFreeze = BasicTransaction(
        account = depositAccount,
        amount = -500.0,
        description = "Rút tiền tại ATM",
        feeStrategy = fixedFee
    )
    val decoratedWithdrawTxFreeze = LoggingDecorator(
        TimeDecorator(
            NotificationDecorator(depositAccount, withdrawTxFreeze)
        )
    )

    decoratedWithdrawTxFreeze.execute()

    depositAccount.close()

    // Iterator: hiển thị lịch sử giao dịch của các tài khoản
    println("\n📜 Lịch sử giao dịch của tài khoản ${depositAccount.account.accountNumber}:")
    for (rec in depositAccount.history) {
        println(" - ${rec.description}: số tiền = ${rec.amount}, số dư sau giao dịch = ${rec.balanceAfter}")
    }

    println("\n📜 Lịch sử giao dịch của tài khoản ${salaryAccount.account.accountNumber}:")
    for (rec in salaryAccount.history) {
        println(" - ${rec.description}: số tiền = ${rec.amount}, số dư sau giao dịch = ${rec.balanceAfter}")
    }


}