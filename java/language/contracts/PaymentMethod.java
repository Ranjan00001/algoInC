package language.contracts;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

/**
 * PRACTICE TOPIC: Sealed Interfaces & Java Records (Java 17+)
 * 
 * Target Skills:
 * 1. Sealed Interface declaration using 'sealed' and 'permits'.
 * 2. Record data carriers (immutable, auto-generated getters, equals, hashCode, toString).
 * 3. Compact Constructors in Records for input validation.
 */
public sealed interface PaymentMethod permits 
        PaymentMethod.CreditCard, 
        PaymentMethod.Crypto, 
        PaymentMethod.BankTransfer {

    String getPaymentType();

    String getPaymentDetails();

    /**
     * CreditCard Record implementation.
     */
    record CreditCard(String cardNumber, String expiryDate, double limit) implements PaymentMethod {
        public CreditCard {
            // TODO: Practice Compact Constructor Validation!
            // - If cardNumber is null or length < 16, throw IllegalArgumentException("Invalid card number")
            // - If limit <= 0, throw IllegalArgumentException("Limit must be positive")
            if (cardNumber == null || cardNumber.isBlank()) {
                throw new IllegalArgumentException("Crdit Card Number is Empty");
            }
            if (cardNumber.length() < 16) {
                throw new IllegalArgumentException("Card number must be of 16 digit");
            }
            if (expiryDate == null || expiryDate.isBlank()) {
                throw new IllegalArgumentException("Expiry Date is Empty");
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
            YearMonth expiry = YearMonth.parse(expiryDate, formatter);
            if (expiry.isBefore(YearMonth.now())) {
                throw new IllegalArgumentException("Credit card has expired");
            }

            if (limit < 0) {
                throw new IllegalArgumentException("Credit Limit can't be negative");
            }
            // validate the valid credit card number from card network.
        }

        @Override
        public String getPaymentType() {
            return "CREDIT_CARD";
        }

        @Override
        public String getPaymentDetails() {
            return this.toString();
            // return "CreditNUmber: " +  CreditNUmber + " | Expiry Date: " + expiryDate;
        }
    }

    /**
     * Crypto Record implementation.
     */
    record Crypto(String walletAddress, String tokenSymbol) implements PaymentMethod {
        public Crypto {
            // TODO: Practice Compact Constructor Validation!
            // - Ensure walletAddress starts with "0x" or throw IllegalArgumentException
            if (walletAddress == null || walletAddress.isBlank()) {
                throw new IllegalArgumentException("Wallet Address is Empty");
            }
            if (!walletAddress.startsWith("0x")) {
                throw new IllegalArgumentException("Ivalid Crypto address");
            }
        }

        @Override
        public String getPaymentType() {
            return "CRYPTO_" + tokenSymbol;
        }

        @Override
        public String getPaymentDetails() {
            return "WalletAddress: " +  walletAddress + " | TokenSymbol: " + tokenSymbol;
        }
    }

    /**
     * BankTransfer Record implementation.
     */
    record BankTransfer(String iban, String swiftCode) implements PaymentMethod {
        @Override
        public String getPaymentType() {
            return "BANK_TRANSFER";
        }
        
        @Override
        public String getPaymentDetails() {
            return "iBan: " + iban  + " | SwiftCode: " + swiftCode;
        }
    }
}
