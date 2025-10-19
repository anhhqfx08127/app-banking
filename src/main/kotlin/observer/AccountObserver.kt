package observer

import factory.Account

interface AccountObserver {
    fun onBalanceChanged(account: Account, newBalance: Double)
}