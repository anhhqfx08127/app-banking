package observer

import factory.Account

class NotificationService : AccountObserver {
    override fun onBalanceChanged(account: Account, newBalance: Double) {
        println("[Biến động số dư] ${account.accountName} (${account.accountNumber}): $newBalance")
    }
}