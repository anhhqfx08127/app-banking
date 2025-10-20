package factory

// Đối tượng Singleton đóng vai trò "Factory" — chỉ có một thể hiện duy nhất trong toàn bộ chương trình
object AccountFactory {

    /**
     * Hàm tạo tài khoản ngân hàng theo loại.
     *
     * @param accountType Loại tài khoản (DEPOSIT, SAVING, v.v.)
     * @param accountNumber Số tài khoản
     * @param accountName Tên chủ tài khoản
     * @return Trả về một đối tượng thuộc lớp con tương ứng của Account
     */
    fun create(
        accountType: AccountType,
        accountNumber: String,
        accountName: String,
    ): Account {
        // In ra thông báo khi tạo tài khoản mới
        println("Tạo tài khoản $accountNumber (${accountType.title})")

        // Dựa vào loại tài khoản, Factory sẽ trả về đúng lớp cụ thể
        return when (accountType) {
            AccountType.DEPOSIT -> DepositAccount(accountNumber, accountName)
            AccountType.OVERDRAFT -> OverdraftAccount(accountNumber, accountName)
            AccountType.SALARY -> SalaryAccount(accountNumber, accountName)
            AccountType.SAVING -> SavingAccount(accountNumber, accountName)
            AccountType.CREDIT -> CreditAccount(accountNumber, accountName)
        }
    }
}
