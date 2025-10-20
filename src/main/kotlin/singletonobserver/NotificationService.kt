package singletonobserver

import factory.Account

/**
 * 🔔 NotificationService (Singleton)
 *
 * 👉 Mẫu thiết kế: Singleton + Observer
 * - Singleton: đảm bảo chỉ có một trung tâm thông báo duy nhất trong toàn hệ thống.
 * - Observer: lắng nghe và phản hồi khi số dư tài khoản thay đổi.
 */
class NotificationService private constructor() : AccountObserver {

    override fun onBalanceChanged(account: Account, newBalance: Double) {
        println("📢 [Thông báo] Số dư tài khoản ${account.accountNumber} của ${account.accountName} hiện là $newBalance")
    }

    companion object {
        // 🔒 Biến instance tĩnh, chỉ khởi tạo duy nhất một lần (lazy)
        val instance: NotificationService by lazy { NotificationService() }
    }
}
