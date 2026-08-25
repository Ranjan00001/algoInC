package language.contracts;

/**
 * PRACTICE TOPIC: Custom Functional Interfaces (@FunctionalInterface)
 * 
 * Target Skills:
 * 1. Single Abstract Method (SAM) contract.
 * 2. Custom Functional Interface default method composition (chaining Evaluators).
 * 3. Lambda expression target type compatibility.
 */
@FunctionalInterface
public interface RiskEvaluator<T> {

    /**
     * Single Abstract Method (SAM)
     * Returns true if risk score for target is acceptable under scoreThreshold.
     */
    boolean evaluateRisk(T target, double scoreThreshold);

    /**
     * TODO: Practice Task - Default Method Chaining (AND logic)
     * Requirements:
     * - Implement default method 'and(RiskEvaluator<T> other)'
     * - Return a combined RiskEvaluator lambda that evaluates true ONLY if BOTH this and other return true.
     */
    default RiskEvaluator<T> and(RiskEvaluator<T> other) {
        // TODO: Implement lambda composition for logical AND
        return (target, threshold) -> {
            throw new UnsupportedOperationException("TODO: Implement RiskEvaluator.and composition");
        };
    }

    /**
     * TODO: Practice Task - Default Method Chaining (OR logic)
     * Requirements:
     * - Implement default method 'or(RiskEvaluator<T> other)'
     * - Return a combined RiskEvaluator lambda that evaluates true if EITHER this or other returns true.
     */
    default RiskEvaluator<T> or(RiskEvaluator<T> other) {
        // TODO: Implement lambda composition for logical OR
        return (target, threshold) -> {
            throw new UnsupportedOperationException("TODO: Implement RiskEvaluator.or composition");
        };
    }

    /**
     * TODO: Practice Task - Static Helper Factory
     * Requirements:
     * - Implement static method 'alwaysSafe()' returning a RiskEvaluator that always returns true regardless of input.
     */
    static <T> RiskEvaluator<T> alwaysSafe() {
        // TODO: Implement lambda returning true
        return (target, threshold) -> true;
    }
}
