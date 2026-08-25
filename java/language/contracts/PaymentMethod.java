package language.contracts;

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

    /**
     * CreditCard Record implementation.
     */
    record CreditCard(String cardNumber, String expiryDate, double limit) implements PaymentMethod {
        public CreditCard {
            // TODO: Practice Compact Constructor Validation!
            // - If cardNumber is null or length < 16, throw IllegalArgumentException("Invalid card number")
            // - If limit <= 0, throw IllegalArgumentException("Limit must be positive")
        }

        @Override
        public String getPaymentType() {
            return "CREDIT_CARD";
        }
    }

    /**
     * Crypto Record implementation.
     */
    record Crypto(String walletAddress, String tokenSymbol) implements PaymentMethod {
        public Crypto {
            // TODO: Practice Compact Constructor Validation!
            // - Ensure walletAddress starts with "0x" or throw IllegalArgumentException
        }

        @Override
        public String getPaymentType() {
            return "CRYPTO_" + tokenSymbol;
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
    }
}
