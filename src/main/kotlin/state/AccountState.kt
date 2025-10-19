package state

import BankAccount

enum class AccountStateType {
    ACTIVE, FREEZE, CLOSE
}

interface AccountState {
    val type: AccountStateType
    fun deposit(account: BankAccount, amount: Double, description: String)
    fun withdraw(account: BankAccount, amount: Double, description: String)
    fun freeze(account: BankAccount)
    fun close(account: BankAccount)
}
