package singletonobserver

import factory.Account

/**
 * Giao diện (interface) định nghĩa "người quan sát" tài khoản ngân hàng.
 *
 * Đây là một phần của mẫu thiết kế **Observer Pattern**.
 *
 * Khi số dư của tài khoản thay đổi, các đối tượng "observer" (như hệ thống thông báo,
 * nhật ký giao dịch, hoặc giao diện người dùng) sẽ được thông báo tự động.
 */
interface AccountObserver {

    /**
     * Hàm callback được gọi mỗi khi số dư tài khoản thay đổi.
     *
     * @param account Tài khoản có sự thay đổi số dư.
     * @param newBalance Số dư mới sau khi thay đổi.
     */
    fun onBalanceChanged(account: Account, newBalance: Double)
}
