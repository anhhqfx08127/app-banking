package state

import BankAccount

/**
 * Lớp đại diện cho trạng thái "đã đóng" (CLOSE) của tài khoản ngân hàng.
 *
 * Đây là trạng thái cuối cùng — tài khoản không còn thực hiện được bất kỳ giao dịch nào.
 * Mọi thao tác nạp, rút, hoặc đổi trạng thái đều bị từ chối.
 */
class ClosedState : AccountState {

    // Loại trạng thái hiện tại
    override val type: AccountStateType = AccountStateType.CLOSE

    /**
     * Không cho phép nạp tiền khi tài khoản đã đóng.
     */
    override fun deposit(account: BankAccount, amount: Double, description: String) {
        println("❌ Không thể nạp tiền, tài khoản ${account.account.accountNumber} đã bị đóng.")
    }

    /**
     * Không cho phép rút tiền khi tài khoản đã đóng.
     */
    override fun withdraw(account: BankAccount, amount: Double, description: String) {
        println("❌ Không thể rút tiền, tài khoản ${account.account.accountNumber} đã bị đóng.")
    }

    /**
     * Không thể đóng băng tài khoản đã đóng — chỉ in thông báo.
     */
    override fun freeze(account: BankAccount) {
        println("⚠️  Không thể đóng băng, tài khoản ${account.account.accountNumber} đã bị đóng.")
    }

    /**
     * Không thể đóng lại tài khoản đã đóng.
     */
    override fun close(account: BankAccount) {
        println("🚫 Tài khoản ${account.account.accountNumber} đã đóng, không thể đóng lại.")
    }
}