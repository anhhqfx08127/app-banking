import factory.AccountFactory
import factory.AccountType
import observer.NotificationService

fun main() {
    val account = AccountFactory.create(
        accountType = AccountType.DEPOSIT,
        accountNumber = "98877797",
        accountName = "HOANG QUOC ANH"
    )
    val bankAccount = BankAccount(
        account = account,
        balance = 1000.0
    )

    bankAccount.addObserver(NotificationService())

    bankAccount.deposit(1000.0, "Xin chào")
}