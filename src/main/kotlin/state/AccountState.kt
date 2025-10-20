package state

import BankAccount

/**
 * Enum định nghĩa các trạng thái có thể có của tài khoản ngân hàng.
 */
enum class AccountStateType {
    ACTIVE,  // Tài khoản đang hoạt động bình thường
    FREEZE,  // Tài khoản bị đóng băng (không thể rút hoặc nạp)
    CLOSE    // Tài khoản đã đóng (không còn giao dịch được)
}

/**
 * Giao diện (interface) đại diện cho "trạng thái" của tài khoản.
 *
 * Đây là một phần của **State Pattern** – giúp hành vi của tài khoản thay đổi
 * linh hoạt tùy thuộc vào trạng thái hiện tại mà không cần dùng nhiều câu lệnh if/when phức tạp.
 */
interface AccountState {

    // Loại trạng thái hiện tại (ACTIVE, FREEZE, hoặc CLOSE)
    val type: AccountStateType

    /**
     * Gửi tiền vào tài khoản.
     *
     * @param account  Tài khoản đang thực hiện thao tác.
     * @param amount   Số tiền nạp.
     * @param description  Ghi chú giao dịch.
     */
    fun deposit(account: BankAccount, amount: Double, description: String)

    /**
     * Rút tiền khỏi tài khoản.
     *
     * @param account  Tài khoản đang thực hiện thao tác.
     * @param amount   Số tiền rút.
     * @param description  Ghi chú giao dịch.
     */
    fun withdraw(account: BankAccount, amount: Double, description: String)

    /**
     * Đóng băng tài khoản — chuyển từ trạng thái hiện tại sang FREEZE.
     */
    fun freeze(account: BankAccount)

    /**
     * Đóng tài khoản — chuyển từ trạng thái hiện tại sang CLOSE.
     */
    fun close(account: BankAccount)
}
