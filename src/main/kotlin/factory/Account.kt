package factory

enum class AccountType {
    DEPOSIT,
    OVERDRAFT,
    SALARY,
    SAVING,
    CREDIT,
}

interface Account {
    val accountNumber: String
    val accountName: String
    val accountType: AccountType
}

data class DepositAccount(
    override val accountNumber: String,
    override val accountName: String,
    override val accountType: AccountType = AccountType.DEPOSIT
) : Account

data class OverdraftAccount(
    override val accountNumber: String,
    override val accountName: String,
    override val accountType: AccountType = AccountType.OVERDRAFT
) : Account

data class SalaryAccount(
    override val accountNumber: String,
    override val accountName: String,
    override val accountType: AccountType = AccountType.SALARY
) : Account

data class SavingAccount(
    override val accountNumber: String,
    override val accountName: String,
    override val accountType: AccountType = AccountType.SAVING
) : Account

data class CreditAccount(
    override val accountNumber: String,
    override val accountName: String,
    override val accountType: AccountType = AccountType.CREDIT
) : Account