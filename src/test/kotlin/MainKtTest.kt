package ru.netology

import calculationCommission
import org.junit.Assert.assertEquals
import org.junit.jupiter.api.Test


class MainKtTest {
    @Test
    fun calculationCommission_withCommission() {
        //arrange
        val transferInThisMonth1 = 7000.0
        val transferAmount1 = 78100.6
        val userCard1: String = "Maestro"

        //act
        val result = calculationCommission(
            transferInThisMonth = transferInThisMonth1,
            transferAmount = transferAmount1,
            userCard = userCard1
        )

        //assert
        assertEquals("Комиссия: 488 руб. 60 коп.", result)
    }

    @Test
    fun calculationCommission_withoutCommission1() {
        //arrange
        val transferInThisMonth1 = 7000.0
        val transferAmount1 = 7100.6
        val userCard1: String = "Maestro"

        //act
        val result = calculationCommission(
            transferInThisMonth = transferInThisMonth1,
            transferAmount = transferAmount1,
            userCard = userCard1
        )

        //assert
        assertEquals("Перевод без комиссии в рамках акции *", result)
    }
    @Test
    fun calculationCommission_withoutCommission2() {
        //arrange
        val transferInThisMonth1 = 7000.0
        val transferAmount1 = 7100.6
        val userCard1: String = "Vk Pay"

        //act
        val result = calculationCommission(
            transferInThisMonth = transferInThisMonth1,
            transferAmount = transferAmount1,
            userCard = userCard1
        )

        //assert
        assertEquals("Комиссия: 0 руб. 0 коп.", result)
    }

    @Test
    fun calculationCommission_withoutTransferVkPay() {
        //arrange
        val transferInThisMonth1 = 7000.0
        val transferAmount1 = 15100.6

        //act
        val result = calculationCommission(
            transferInThisMonth = transferInThisMonth1,
            transferAmount = transferAmount1,
        )

        //assert
        assertEquals("Перевод невозможен", result)
    }

    @Test
    fun calculationCommission_withoutTransferVisa() {
        //arrange
        val transferInThisMonth1 = 605000.0
        val transferAmount1 = 15100.6
        val userCard1: String = "Visa"

        //act
        val result = calculationCommission(
            transferInThisMonth = transferInThisMonth1,
            transferAmount = transferAmount1,
            userCard = userCard1
        )

        //assert
        assertEquals("Перевод невозможен", result)
    }

    @Test
    fun calculationCommission_fixCommission() {
        //arrange
        val transferInThisMonth1 = 7000.0
        val transferAmount1 = 100.0
        val userCard1: String = "Visa"

        //act
        val result = calculationCommission(
            transferInThisMonth = transferInThisMonth1,
            transferAmount = transferAmount1,
            userCard = userCard1
        )

        //assert
        assertEquals("Комиссия: 36 руб.", result)
    }

}
