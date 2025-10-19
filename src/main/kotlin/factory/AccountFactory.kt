package factory

object AccountFactory {
    fun create(
        accountType: AccountType,
        accountNumber: String,
        accountName: String,
    ): Account {
        println("Tạo tài khoản $accountNumber")
        return when(accountType) {
            AccountType.DEPOSIT -> DepositAccount(accountNumber, accountName)
            AccountType.OVERDRAFT -> OverdraftAccount(accountNumber, accountName)
            AccountType.SALARY -> SalaryAccount(accountNumber, accountName)
            AccountType.SAVING -> SavingAccount(accountNumber, accountName)
            AccountType.CREDIT -> CreditAccount(accountNumber, accountName)
        }
    }

}