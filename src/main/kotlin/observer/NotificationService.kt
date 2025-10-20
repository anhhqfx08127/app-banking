package observer

import factory.Account

/**
 * Lớp NotificationService đóng vai trò là "Observer" (Người quan sát) trong mẫu thiết kế Observer Pattern.
 *
 * Nhiệm vụ:
 * - Theo dõi các thay đổi của tài khoản (Account).
 * - Gửi thông báo (ở đây là in ra console) khi số dư thay đổi.
 */
class NotificationService : AccountObserver {

    /**
     * Được gọi tự động khi số dư của tài khoản thay đổi.
     *
     * @param account Tài khoản có biến động số dư.
     * @param newBalance Số dư mới sau khi thay đổi.
     */
    override fun onBalanceChanged(account: Account, newBalance: Double) {
        // In thông báo ra màn hình
        println("[Biến động số dư] ${account.accountName} (${account.accountNumber}): $newBalance")
    }
}
