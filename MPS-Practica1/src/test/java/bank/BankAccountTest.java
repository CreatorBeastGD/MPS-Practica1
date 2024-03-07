package bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class BankAccountTest {
    BankAccount bank;

    @BeforeEach
    void setup() {
        bank = new BankAccount(100);
    }

    @Test
    @DisplayName("Si se saca 20 euros, el balance será de 80")
    public void IfWithdrawn20Then80EurosInBalance() {
        bank.withdraw(20);
        int result = bank.getBalance();
        int expected = 80;
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Si se saca una cantidad negativa de dinero, se devuelve excepcion")
    public void IfWithdrawnNegativeAmountThrowException() {
        String message = "Amount cannot be negative";
        Executable exe = () -> bank.withdraw(-1);
        Class<IllegalArgumentException> except = IllegalArgumentException.class;
        assertThrows(except, exe, message);
    }

    @Test
    @DisplayName("Si se saca más del saldo devuelve falso")
    public void IfWithdrawnLotThenReturnFalse() {
        boolean givenCondition = bank.withdraw(120);
        assertFalse(givenCondition);
    }

    @Test
    @DisplayName("La cantidad mostrada debe ser correcta")
    public void ShownBalanceIsCorrect() {
        int expected = 100;
        int result = bank.getBalance();
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("El deposito de dinero se hace correctamente")
    public void BalanceDepositIsDoneCorrectly(){
        int expected = 120;
        int result = bank.deposit(20);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Depositar un numero negativo devuelve excepcion")
    public void NegativeValueOnDepositThrowsException() {
        String message = "Amount cannot be negative";
        Executable exe = () -> bank.deposit(-1);
        Class<IllegalArgumentException> except = IllegalArgumentException.class;
        assertThrows(except, exe, message);
    }

    @Test
    @DisplayName("Probar calculos del pago mensual con valores válidos")
    public void PaymentWithValidAmountsIsCorrect(){
        double amount = 10000.0;
        double interest = 0.05;
        int npayments = 12;

        double result = bank.payment(amount, interest, npayments);

        assertEquals(1128.25, result, 0.01);
    }

    @Test
    @DisplayName("Calcular pago con cantidad negativa da excepción")
    public void PaymentWithNegativeTotalAmountThrowsException() {
        double totalAmount = -1.0;
        double interest = 0.05;
        int npayments = 12;

        String message = "Amounts cannot be null or negative";
        Executable exe = () -> bank.payment(totalAmount, interest, npayments);
        Class<IllegalArgumentException> except = IllegalArgumentException.class;

        assertThrows(except, exe, message);
    }

    @Test
    @DisplayName("Calcular pago con 0 intereses devuelve excepción")
    public void PaymentWithNoInterestThrowsException() {
        double totalAmount = 10000.0;
        double interest = 0.0;
        int npayments = 12;

        String message = "Amounts cannot be null or negative";
        Executable exe = () -> bank.payment(totalAmount, interest, npayments);
        Class<IllegalArgumentException> except = IllegalArgumentException.class;

        assertThrows(except, exe, message);
    }

    @Test
    @DisplayName("Calcular pago con 0 pagos devuelve excepción")
    public void PaymentWithNoPaymentsThrowsException() {
        double totalAmount = 10000.0;
        double interest = 0.05;
        int npayments = 0;

        String message = "Amounts cannot be null or negative";
        Executable exe = () -> bank.payment(totalAmount, interest, npayments);
        Class<IllegalArgumentException> except = IllegalArgumentException.class;

        assertThrows(except, exe, message);
    }

    // Las pruebas que me puso el chatgpt este
    @Test
    @DisplayName("Pago pendiente con valores validos es correcto")
    public void PendingWithValidInputsIsCorrect() {
        // Arrange
        double totalAmount = 10000.0;
        double interest = 0.05;
        int npayments = 12;
        int month = 6;

        double expected = 5726.67;

        // Act
        double result = bank.pending(totalAmount, interest, npayments, month);

        // Assert
        assertEquals(expected, result, 0.01);
    }

    @Test
    @DisplayName("Introducir una cantidad negativa devuelve excepción")
    public void PendingWithNegativeTotalAmountThrowsException() {
        double totalAmount = -10000.0;
        double interest = 0.05;
        int npayments = 12;
        int month = 6;

        String message = "Amount cannot be null or negative";
        Executable exec = () -> bank.pending(totalAmount, interest, npayments, month);
        Class<IllegalArgumentException> err = IllegalArgumentException.class;
        
        assertThrows(err, exec, message);
    }

    @Test
    @DisplayName("Hacer el pago pendiente con cero intereses devuelve excepción")
    public void PendingWithZeroInterestThrowsException() {
        double totalAmount = 10000.0;
        double interest = 0.0;
        int npayments = 12;
        int month = 6;

        String message = "Amount cannot be null or negative";
        Executable exec = () -> bank.pending(totalAmount, interest, npayments, month);
        Class<IllegalArgumentException> err = IllegalArgumentException.class;
        
        assertThrows(err, exec, message);
    }

    @Test
    @DisplayName("Hacer el pago pendiente durante 0 meses devuelve excepción")
    public void PendingWithZeroPaymentsThrowsException() {
        double totalAmount = 10000.0;
        double interest = 0.05;
        int npayments = 0;
        int month = 6;

        String message = "Amount cannot be null or negative";
        Executable exec = () -> bank.pending(totalAmount, interest, npayments, month);
        Class<IllegalArgumentException> err = IllegalArgumentException.class;
        
        assertThrows(err, exec, message);
    }

    @Test
    @DisplayName("Hacer el pago pendiente de 0 meses devuelve la cantidad total")
    public void testPendingWithZeroMonthReturnsTotalAmount() {
        double totalAmount = 10000.0;
        double interest = 0.05;
        int npayments = 12;
        int month = 0;

        double result = bank.pending(totalAmount, interest, npayments, month);

        assertEquals(totalAmount, result);
    }

    @Test
    @DisplayName("Hacer el pago pendiente durante -1 meses devuelve excepción")
    public void PendingWithNegativeMonthsThrowsException() {
        double totalAmount = 10000.0;
        double interest = 0.05;
        int npayments = 0;
        int month = -1;

        String message = "Amount cannot be null or negative";
        Executable exec = () -> bank.pending(totalAmount, interest, npayments, month);
        Class<IllegalArgumentException> err = IllegalArgumentException.class;
        
        assertThrows(err, exec, message);
    }
}
