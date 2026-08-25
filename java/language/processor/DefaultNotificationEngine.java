package language.processor;

import language.model.Order;

/**
 * PRACTICE TOPIC: Default (Package-Private) Class Visibility
 * 
 * Target Skills:
 * 1. Package-Private Class declaration (No 'public' modifier before 'class').
 * 2. Package-Private Methods.
 * 3. Enforcing encapsulation: Classes outside package 'language.processor' CANNOT access this class directly!
 */
class DefaultNotificationEngine {

    private final String channelName;

    // Package-private constructor
    DefaultNotificationEngine(String channelName) {
        this.channelName = channelName;
    }

    /**
     * TODO: Practice Task - Package-Private Method Execution
     * Requirements:
     * - Print notification dispatch message:
     *   "[PACKAGE-PRIVATE NOTIFIER (" + channelName + ")]: Dispatching notification for Order " + order.getOrderId()
     */
    void sendNotification(Order order) {
        // TODO: Implement notification dispatch
        System.out.println("[PACKAGE-PRIVATE NOTIFIER (" + channelName + ")]: Dispatching notification for Order " + order.getOrderId());
    }

    String getChannelName() {
        return channelName;
    }
}
