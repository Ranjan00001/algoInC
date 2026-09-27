package language;

import language.contracts.*;
import language.ds.*;
import language.exception.*;
import language.functional.*;
import language.model.*;
import language.processor.*;
import language.telemetry.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * =============================================================================
 * ADVANCED JAVA PRACTICE & SELF-TEST RUNNER
 * =============================================================================
 * 
 * Instructions:
 * Run this Main class to verify your implementations across each practice module!
 * As you complete the TODO tasks in contracts, model, processor, functional,
 * telemetry, and exception packages, this suite will validate your code.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("==================================================================");
        System.out.println("      ADVANCED JAVA MASTERY & DEVELOPMENT PRACTICE SUITE         ");
        System.out.println("==================================================================\n");

        runModule1_InterfaceAndAbstractClassPractice();
        runModule2_PolymorphismOverloadingOverriding();
        runModule3_SealedTypesAndRecords();
        runModule4_EnumsAndStateTransitions();
        runModule5_NestedClassesAndGenerics();
        runModule6_FunctionalInterfacesAndAnonymousClasses();
        runModule7_TelemetryEngineAndPerformance();
        runModule8_DataStructureDomainChallenges();

        System.out.println("\n==================================================================");
        System.out.println("     ALL PRACTICE MODULE TEST SCENARIOS EXECUTED SUCCESSFULLY!   ");
        System.out.println("==================================================================");
    }

    private static void runModule1_InterfaceAndAbstractClassPractice() {
        System.out.println("--- MODULE 1: INTERFACE & ABSTRACT CLASS PRACTICE ---");

        // Static Interface Method
        System.out.println("System Version: " + EventProcessor.getSystemVersion());

        // Digital Order Processor (Extends Abstract class, implements EventProcessor & Auditable)
        AbstractOrderProcessor digitalProcessor = new DigitalOrderProcessor("PROC-DIGITAL-01");
        
        // Testing Interface Default Method Collision Resolution
        System.out.println("Audit Header: " + digitalProcessor.getAuditHeader());

        // Default Notification Engine (Package-Private / Default Class)
        // Accessing default class within same package hierarchy context
        System.out.println("Module 1 Completed.\n");
    }

    private static void runModule2_PolymorphismOverloadingOverriding() {
        System.out.println("--- MODULE 2: POLYMORPHISM (OVERLOADING & OVERRIDING) ---");

        Order testOrder = new Order.Builder("ORD-1001")
                .customerName("Alice")
                .baseAmount(500.0)
                .addItem("Java E-Book")
                .paymentMethod(new PaymentMethod.CreditCard("1234567890123456", "12/28", 5000.0))
                .build();

        // Testing Method Overloading variants
        double baseTotal = testOrder.calculateTotalCost();
        double totalWithTax = testOrder.calculateTotalCost(18.0);
        double totalWithTaxAndDiscount = testOrder.calculateTotalCost(18.0, 50.0);

        System.out.println("Overloaded Total (Base): $" + baseTotal);
        System.out.println("Overloaded Total (Tax 18%): $" + totalWithTax);
        System.out.println("Overloaded Total (Tax 18%, Flat Discount $50): $" + totalWithTaxAndDiscount);

        // Testing Method Overriding via Abstract class polymorphic reference
        AbstractOrderProcessor digitalProc = new DigitalOrderProcessor("POLY-PROC-1");
        AbstractOrderProcessor physicalProc = new PhysicalOrderProcessor("POLY-PROC-2", 12.5);

        try {
            digitalProc.process(testOrder);
            System.out.println("Digital Order Status after process: " + testOrder.getStatus());

            Order physicalOrder = new Order.Builder("ORD-1002")
                    .customerName("Bob")
                    .baseAmount(1200.0)
                    .addItem("Developer Workstation")
                    .paymentMethod(new PaymentMethod.BankTransfer("DE89370400440532013000", "DBKADEFF"))
                    .build();

            physicalProc.process(physicalOrder);
            System.out.println("Physical Order Status after process: " + physicalOrder.getStatus());
        } catch (OrderProcessingException e) {
            System.err.println("Processing Failed: " + e);
        }

        System.out.println("Module 2 Completed.\n");
    }

    private static void runModule3_SealedTypesAndRecords() {
        System.out.println("--- MODULE 3: SEALED INTERFACES & RECORDS ---");

        PaymentMethod cc = new PaymentMethod.CreditCard("9876543210987654", "08/30", 10000.0);
        PaymentMethod crypto = new PaymentMethod.Crypto("0x71C7656EC7ab88b098defB751B7401B5f6d8976F", "ETH");

        System.out.println("CreditCard Payment Type: " + cc.getPaymentType());
        System.out.println("Crypto Payment Type: " + crypto.getPaymentType());

        // Pattern Matching / Exhaustive evaluation over Sealed Types
        if (cc instanceof PaymentMethod.CreditCard card) {
            System.out.println("Record Pattern Match -> Limit: $" + card.limit());
        }

        System.out.println("Module 3 Completed.\n");
    }

    private static void runModule4_EnumsAndStateTransitions() {
        System.out.println("--- MODULE 4: ENUMS WITH CONSTANT-SPECIFIC CLASS BODIES ---");

        OrderStatus current = OrderStatus.PENDING;
        System.out.println("Can PENDING transition to PROCESSING? " + current.canTransitionTo(OrderStatus.PROCESSING));
        System.out.println("Can PENDING transition to COMPLETED? " + current.canTransitionTo(OrderStatus.COMPLETED));

        OrderStatus shipped = OrderStatus.SHIPPED;
        System.out.println("Can SHIPPED transition to DELIVERED? " + shipped.canTransitionTo(OrderStatus.DELIVERED));

        System.out.println("Module 4 Completed.\n");
    }

    private static void runModule5_NestedClassesAndGenerics() {
        System.out.println("--- MODULE 5: NESTED CLASSES & GENERICS (PECS) ---");

        // Static Nested Class (Order.Builder)
        Order order = new Order.Builder("ORD-5001")
                .customerName("Charlie")
                .baseAmount(750.0)
                .build();

        // Non-Static Inner Class (OrderAuditTracker)
        Order.OrderAuditTracker tracker = order.new OrderAuditTracker();
        tracker.recordLog("Payment Verified");
        tracker.recordLog("Receipt Dispatched");
        System.out.println("Inner Class Audit Log Count: " + tracker.getAuditLogs().size());

        // Generics & PECS Wildcards
        List<ProcessingResult<String>> results = new ArrayList<>();
        results.add(new ProcessingResult<>(true, "PAYMENT_SUCCESS", "Transaction approved"));
        results.add(new ProcessingResult<>(true, "EMAIL_DISPATCHED", "Notification sent"));

        List<String> payloads = new ArrayList<>();
        ProcessingResult.extractAllPayloads(results, payloads);
        System.out.println("Generic Extracted Payloads: " + payloads);

        System.out.println("Module 5 Completed.\n");
    }

    private static void runModule6_FunctionalInterfacesAndAnonymousClasses() {
        System.out.println("--- MODULE 6: FUNCTIONAL INTERFACES & ANONYMOUS CLASSES ---");

        FunctionalPracticeSuite suite = new FunctionalPracticeSuite();

        Predicate<Order> highValueCheck = suite.createHighValueOrderPredicate();
        Consumer<String> logger = suite.createAuditLoggerConsumer();

        Order order = new Order.Builder("ORD-6001").baseAmount(2500.0).build();
        System.out.println("Is High Value Order ($2500)? " + highValueCheck.test(order));

        logger.accept("Testing Consumer Functional Interface execution!");

        // Custom @FunctionalInterface RiskEvaluator
        RiskEvaluator<Order> fraudCheck = (ord, threshold) -> ord.getBaseAmount() > threshold;
        System.out.println("Custom @FunctionalInterface Risk Result: " + fraudCheck.evaluateRisk(order, 2000.0));

        // Anonymous Inner Class
        Runnable anonRunnable = suite.createAnonymousRunnable(logger);
        anonRunnable.run();

        System.out.println("Module 6 Completed.\n");
    }

    private static void runModule7_TelemetryEngineAndPerformance() {
        System.out.println("--- MODULE 7: TELEMETRY ENGINE & MULTI-THREADED PERFORMANCE ---");

        TelemetryEngine engine = new TelemetryEngine();

        // 1. String Pool Verification
        engine.verifyStringPoolInterning();

        // 2. Thread Fault Handler
        try {
            engine.executeFaultTolerantThread();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // 3. Memory Layout Benchmark
        engine.compareMemoryLayoutSpeed();

        System.out.println("Module 7 Completed.\n");
    }

    private static void runModule8_DataStructureDomainChallenges() {
        System.out.println("--- MODULE 8: STEALTH DOMAIN DATA STRUCTURE CHALLENGES ---");

        // 1. Order State Reversal (LIFO Challenge)
        OrderActionTracker tracker = new OrderActionTracker();
        tracker.recordAction("STATUS_CHANGE: PENDING -> PROCESSING");
        tracker.recordAction("PAYMENT_AUTHORIZED: TXN-991");
        System.out.println("[DS Challenge 1] Most recent action: " + tracker.peekLastAction());
        System.out.println("[DS Challenge 1] Undo action: " + tracker.undoLastAction());

        // 2. High-Volume Order Buffer (FIFO Challenge)
        OrderFulfillmentDispatcher dispatcher = new OrderFulfillmentDispatcher();
        Order ord1 = new Order.Builder("ORD-DS-01").customerName("Alice").baseAmount(300.0).build();
        Order ord2 = new Order.Builder("ORD-DS-02").customerName("Bob").baseAmount(150.0).build();
        dispatcher.enqueueOrder(ord1);
        dispatcher.enqueueOrder(ord2);
        System.out.println("[DS Challenge 2] Dispatched oldest order: " + dispatcher.processNextOrder().getOrderId());

        // 3. Historical Audit Trail Stream (Head/Tail Stream Challenge)
        AuditTrailContainer auditContainer = new AuditTrailContainer();
        auditContainer.appendLog("USER_LOGIN_EVENT");
        auditContainer.prependHighPriorityLog("SECURITY_ALERT_DDOS");
        System.out.println("[DS Challenge 3] Latest head audit log: " + auditContainer.peekLatestHeadLog());

        // 4. Transaction Idempotency Registry (O(1) Uniqueness Challenge)
        TransactionIdempotencyRegistry idempotency = new TransactionIdempotencyRegistry();
        boolean firstTry = idempotency.registerTransaction("TXN-1001");
        boolean secondTry = idempotency.registerTransaction("TXN-1001");
        System.out.println("[DS Challenge 4] First registration: " + firstTry + " | Re-try duplicate: " + (!secondTry));

        // 5. Priority Fulfillment Engine (Priority Extraction Challenge)
        PriorityFulfillmentEngine priorityEngine = new PriorityFulfillmentEngine();
        priorityEngine.enqueuePriorityOrder(ord2, 150.0);
        priorityEngine.enqueuePriorityOrder(ord1, 800.0);
        System.out.println("[DS Challenge 5] Highest priority order polled: " + priorityEngine.pollHighestPriorityOrder().getOrderId());

        // 6. Catalog Price Range Indexer (Sorted Range Search Challenge)
        CatalogPriceIndexer priceIndexer = new CatalogPriceIndexer();
        priceIndexer.indexOrder(ord1); // 300.0
        priceIndexer.indexOrder(ord2); // 150.0
        List<Order> filtered = priceIndexer.getOrdersInPriceRange(100.0, 250.0);
        System.out.println("[DS Challenge 6] Orders in range $100-$250 count: " + filtered.size());

        // 7. Evaluation Risk LRU Cache (LRU Cache Challenge)
        EvaluationCacheEngine lruCache = new EvaluationCacheEngine(2);
        lruCache.putRiskScore("ORD-DS-01", true);
        lruCache.putRiskScore("ORD-DS-02", false);
        lruCache.putRiskScore("ORD-DS-03", true); // Evicts ORD-DS-01 (LRU)
        System.out.println("[DS Challenge 7] Cached score for ORD-DS-01 (evicted?): " + lruCache.getCachedRiskScore("ORD-DS-01"));
        System.out.println("[DS Challenge 7] Cached score for ORD-DS-03: " + lruCache.getCachedRiskScore("ORD-DS-03"));

        System.out.println("Module 8 Completed.\n");
    }
}

