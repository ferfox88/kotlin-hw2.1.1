fun main() {
    val result = calculationCommission(7000.0, 78100.6, "Maestro")
    println(result)
}

fun commissionCard(card: String = "Vk Pay"): Double {
    return when (card) {
        "Mastercard", "Maestro" -> 0.6
        "Visa", "Мир" -> 0.75
        else -> 0.0
    }
}

fun calculationCommission(
    transferInThisMonth: Double = 0.0,
    transferAmount: Double,
    userCard: String = "Vk Pay",

    discountEnd: Int = 7500000, // в копейках в месяц для "Mastercard", "Maestro"
    fixFee1: Int = 20, // добавть в рублях для "Mastercard", "Maestro"
    fixFee2: Int = 35 // мин комиссия в рублях для "Visa", "Мир"

): String {
    val amount = (transferAmount * 100).toInt()
    val amountMonth = (transferInThisMonth * 100).toInt()
    val transfer = amountMonth + amount
    val commission = (transferAmount * commissionCard(userCard)).toInt()
    val rub = commission / 100
    val cop = commission % 100

    if (transfer > limitTransferInThisMonth(userCard)
        || amount > limitTransferInThisDay(userCard)
    ) {
        return "Перевод невозможен"
    } else if (transfer <= discountEnd && (userCard == "Mastercard" || userCard == "Maestro")) {
        return "Перевод без комиссии в рамках акции *"
    } else if (userCard == "Mastercard" || userCard == "Maestro") {
        return "Комиссия: ${rub + fixFee1} руб. $cop коп."
    } else if ((userCard == "Visa" || userCard == "Мир") && rub < fixFee2) {
        return "Комиссия: $fixFee2 руб."
    } else {
        return "Комиссия: $rub руб. $cop коп."
    }
}

fun limitTransferInThisMonth(card: String = "Vk Pay"): Int {
    return when (card) {
        "Mastercard", "Maestro", "Visa", "Мир" -> 60000000
        else -> 4000000
    }
}

fun limitTransferInThisDay(card: String = "Vk Pay"): Int {
    return when (card) {
        "Mastercard", "Maestro", "Visa", "Мир" -> 15000000
        else -> 1500000
    }
}
