import decorator.LoggingDecorator
import decorator.NotificationDecorator
import decorator.TimeDecorator
import decorator.builder.TransactionBuilder
import factory.AccountFactory
import factory.AccountType
import singletonobserver.NotificationService
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
    depositAccount.addObserver(NotificationService.instance)
    salaryAccount.addObserver(NotificationService.instance)

    // Giao dịch NẠP TIỀN
    val depositTx = TransactionBuilder()
        .setAccount(depositAccount)
        .setAmount(1000.0)
        .setDescription("Nộp tiền mặt tại quầy")
        .setFeeStrategy(noFee)
        .enableNotification()
        .enableTimestamp()
        .enableLog()
        .build()

    depositTx.execute()

    println("----------")

    // Giao dịch NHẬN LƯƠNG
    val salaryTx = TransactionBuilder()
        .setAccount(salaryAccount)
        .setAmount(5000.0)
        .setDescription("Thanh toán lương tháng 10/2025")
        .setFeeStrategy(noFee)
        .enableNotification()
        .enableTimestamp()
        .enableLog()
        .build()

    salaryTx.execute()

    println("----------")

    // Giao dịch RÚT TIỀN (có phí cố định)
    val withdrawTx = TransactionBuilder()
        .setAccount(salaryAccount)
        .setAmount(-500.0)
        .setDescription("Rút tiền tại ATM")
        .setFeeStrategy(fixedFee)
        .enableNotification()
        .enableTimestamp()
        .enableLog()
        .build()

    withdrawTx.execute()

    println("----------")

    // Giao dịch MUA HÀNG (có phí phần trăm)
    val purchaseTx = TransactionBuilder()
        .setAccount(salaryAccount)
        .setAmount(-1200.0)
        .setDescription("Thanh toán đơn hàng Shopee")
        .setFeeStrategy(percentFee)
        .enableNotification()
        .enableTimestamp()
        .enableLog()
        .build()

    purchaseTx.execute()

    println("----------")
    depositAccount.freeze()

    val withdrawTxFreeze = TransactionBuilder()
        .setAccount(depositAccount)
        .setAmount(-500.0)
        .setDescription("Rút tiền khi tài khoản bị đóng băng")
        .setFeeStrategy(fixedFee)
        .enableNotification()
        .enableTimestamp()
        .enableLog()
        .build()

    withdrawTxFreeze.execute()

    depositAccount.close()

    // Iterator: hiển thị lịch sử giao dịch của các tài khoản
    println("\n📜 Lịch sử giao dịch của tài khoản ${depositAccount.account.accountNumber}:")
    for (rec in depositAccount.history) {
        println(" - ${rec.description}: số tiền = ${rec.amount}, phí = ${rec.fee}, số dư sau giao dịch = ${rec.balanceAfter}")
    }

    println("\n📜 Lịch sử giao dịch của tài khoản ${salaryAccount.account.accountNumber}:")
    for (rec in salaryAccount.history) {
        println(" - ${rec.description}: số tiền = ${rec.amount}, phí = ${rec.fee}, số dư sau giao dịch = ${rec.balanceAfter}")
    }


}