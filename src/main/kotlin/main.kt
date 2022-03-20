fun main() {
    val previousTransferPerDay = 10_000_00.00
    val previousTransferPerMonth = 80_000_00.00
    val transferSum = 5_000_00.00
    val accountType = "Maestro"

    if (!areLimitsNotOverrun(previousTransferPerDay, previousTransferPerMonth, transferSum, accountType)) {
        println("Лимит превышен. Перевод не может быть осуществлен.")
    } else {
        println(
            "Размер комиссии в копейках: ${
                calculateFee(
                    accountType,
                    previousTransferPerMonth,
                    transferSum
                ).toInt()
            }"
        )
    }
}

fun calculateFee(
    accountType: String = "VkPay",
    previousTransferPerMonth: Double = 0.00,
    transferSum: Double
): Double {
    return when (accountType) {
        "MasterCard", "Maestro" -> when (previousTransferPerMonth + transferSum) {
            in 0.00..75_000_00.00 -> 0.00
            else -> maxOf(transferSum * 0.6 / 100, 20_00.00)
        }
        "Visa", "Мир" -> maxOf(transferSum * 0.75 / 100, 35_00.00)
        "VkPay" -> 0.00
        else -> 0.00
    }
}

fun areLimitsNotOverrun(
    previousTransferPerDay: Double,
    previousTransferPerMonth: Double,
    transferSum: Double,
    accountType: String
): Boolean {
    return when (accountType) {
        "VkPay" -> transferSum <= 15_000_00.00 && previousTransferPerMonth + transferSum <= 40_000_00.00
        else -> previousTransferPerMonth + transferSum <= 600_000_00.00 && previousTransferPerDay + transferSum <= 150_000_00.00
    }
}