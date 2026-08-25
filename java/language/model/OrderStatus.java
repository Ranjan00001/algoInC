package language.model;

/**
 * PRACTICE TOPIC: Enums with Abstract Methods & Constant-Specific Class Bodies
 * 
 * Target Skills:
 * 1. Declaring an abstract method inside an enum.
 * 2. Overriding the abstract method in each enum constant body.
 * 3. Enforcing finite state machine rules.
 */
public enum OrderStatus {

    PENDING {
        @Override
        public boolean canTransitionTo(OrderStatus nextState) {
            // TODO: PENDING can transition to PROCESSING or CANCELLED. Return true if nextState matches either.
            return nextState == PROCESSING || nextState == CANCELLED;
        }
    },

    PROCESSING {
        @Override
        public boolean canTransitionTo(OrderStatus nextState) {
            // TODO: PROCESSING can transition to SHIPPED or COMPLETED. Return appropriate boolean.
            return nextState == SHIPPED || nextState == COMPLETED || nextState == CANCELLED;
        }
    },

    SHIPPED {
        @Override
        public boolean canTransitionTo(OrderStatus nextState) {
            // TODO: SHIPPED can transition to DELIVERED or RETURNED.
            return nextState == DELIVERED || nextState == RETURNED;
        }
    },

    DELIVERED {
        @Override
        public boolean canTransitionTo(OrderStatus nextState) {
            // TODO: Terminal state - DELIVERED can only transition to RETURNED.
            return nextState == RETURNED;
        }
    },

    CANCELLED {
        @Override
        public boolean canTransitionTo(OrderStatus nextState) {
            // TODO: Terminal state - CANCELLED cannot transition anywhere (return false).
            return false;
        }
    },

    COMPLETED {
        @Override
        public boolean canTransitionTo(OrderStatus nextState) {
            // TODO: Terminal state - COMPLETED cannot transition anywhere (return false).
            return false;
        }
    },

    RETURNED {
        @Override
        public boolean canTransitionTo(OrderStatus nextState) {
            // TODO: Terminal state - RETURNED cannot transition anywhere (return false).
            return false;
        }
    };

    /**
     * Abstract method enforced on every enum constant.
     */
    public abstract boolean canTransitionTo(OrderStatus nextState);
}
