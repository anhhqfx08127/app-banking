package factory

// Định nghĩa các loại tài khoản khác nhau trong ngân hàng
enum class AccountType(val title: String) {
    DEPOSIT("Tài khoản tiền gửi"),    // Tài khoản tiền gửi (thông thường)
    OVERDRAFT("Tài khoản thấu chi"),  // Tài khoản thấu chi (cho phép rút quá số dư)
    SALARY("Tài khoản nhận lương"),     // Tài khoản nhận lương
    SAVING("Tài khoản tiết kiệm"),     // Tài khoản tiết kiệm
    CREDIT("Tài khoản tín dụng"),     // Tài khoản tín dụng
}

// Interface đại diện cho một tài khoản ngân hàng chung
interface Account {
    val accountNumber: String     // Số tài khoản
    val accountName: String       // Tên chủ tài khoản
    val accountType: AccountType  // Loại tài khoản (thuộc enum trên)
}

// Lớp đại diện cho tài khoản tiền gửi thông thường
data class DepositAccount(
    override val accountNumber: String,
    override val accountName: String,
    override val accountType: AccountType = AccountType.DEPOSIT // Mặc định là loại DEPOSIT
) : Account

// Lớp đại diện cho tài khoản thấu chi
data class OverdraftAccount(
    override val accountNumber: String,
    override val accountName: String,
    override val accountType: AccountType = AccountType.OVERDRAFT // Mặc định là loại OVERDRAFT
) : Account

// Lớp đại diện cho tài khoản nhận lương
data class SalaryAccount(
    override val accountNumber: String,
    override val accountName: String,
    override val accountType: AccountType = AccountType.SALARY // Mặc định là loại SALARY
) : Account

// Lớp đại diện cho tài khoản tiết kiệm
data class SavingAccount(
    override val accountNumber: String,
    override val accountName: String,
    override val accountType: AccountType = AccountType.SAVING // Mặc định là loại SAVING
) : Account

// Lớp đại diện cho tài khoản tín dụng
data class CreditAccount(
    override val accountNumber: String,
    override val accountName: String,
    override val accountType: AccountType = AccountType.CREDIT // Mặc định là loại CREDIT
) : Account