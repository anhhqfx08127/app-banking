package state

import BankAccount

/**
 * Lớp đại diện cho trạng thái "đóng băng" (FREEZE) của tài khoản ngân hàng.
 *
 * Trong trạng thái này:
 *  - Không thể nạp hoặc rút tiền.
 *  - Có thể đóng tài khoản (chuyển sang CLOSED).
 *  - Có thể mở lại (chuyển về ACTIVE).
 */
class FrozenState : AccountState {

    // Gán đúng loại trạng thái
    override val type: AccountStateType = AccountStateType.FREEZE

    /**
     * Không thể nạp tiền khi tài khoản đang bị đóng băng.
     */
    override fun deposit(account: BankAccount, amount: Double, fee: Double, description: String) {
        println("❌ Không thể nạp tiền, tài khoản ${account.account.accountNumber} đang bị đóng băng.")
    }

    /**
     * Không thể rút tiền khi tài khoản đang bị đóng băng.
     */
    override fun withdraw(account: BankAccount, amount: Double, fee: Double, description: String) {
        println("❌ Không thể rút tiền, tài khoản ${account.account.accountNumber} đang bị đóng băng.")
    }

    /**
     * Nếu gọi freeze() nữa thì chỉ thông báo lại — tài khoản đã bị đóng băng rồi.
     */
    override fun freeze(account: BankAccount) {
        println("⚠️  Tài khoản ${account.account.accountNumber} hiện đang bị đóng băng.")
    }

    /**
     * Cho phép đóng tài khoản khi đang bị đóng băng.
     * -> Chuyển trạng thái sang ClosedState.
     */
    override fun close(account: BankAccount) {
        account.state = ClosedState()
        println("🚫 Tài khoản ${account.account.accountNumber} đã bị đóng.")
    }

    /**
     * (Mở rộng tùy chọn) — nếu muốn cho phép 'mở lại' tài khoản bị đóng băng:
     */
    fun unfreeze(account: BankAccount) {
        account.state = ActiveState()
        println("✅ Tài khoản ${account.account.accountNumber} đã được mở khóa (trở lại trạng thái hoạt động).")
    }
}