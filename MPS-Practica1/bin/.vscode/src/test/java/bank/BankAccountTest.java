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
    public void testPendingWithValidInputs() {
        // Arrange
        double totalAmount = 10000.0;
        double interest = 0.05;
        int npayments = 12;
        int month = 6;

        // Act
        double result = bank.pending(totalAmount, interest, npayments, month);

        // Assert
        assertEquals(6149.82, result, 0.01);
    }

    @Test
    public void testPendingWithNegativeAmount() {
        // Arrange
        double totalAmount = -10000.0;
        double interest = 0.05;
        int npayments = 12;
        int month = 6;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> bank.pending(totalAmount, interest, npayments, month));
    }

    @Test
    public void testPendingWithZeroInterest() {
        // Arrange
        double totalAmount = 10000.0;
        double interest = 0.0;
        int npayments = 12;
        int month = 6;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> bank.pending(totalAmount, interest, npayments, month));
    }

    @Test
    public void testPendingWithZeroPayments() {
        // Arrange
        double totalAmount = 10000.0;
        double interest = 0.05;
        int npayments = 0;
        int month = 6;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> bank.pending(totalAmount, interest, npayments, month));
    }

    @Test
    public void testPendingWithZeroMonth() {
        // Arrange
        double totalAmount = 10000.0;
        double interest = 0.05;
        int npayments = 12;
        int month = 0;

        // Act
        double result = bank.pending(totalAmount, interest, npayments, month);

        // Assert
        assertEquals(10000.0, result, 0.01);
    }
}
