package language.telemetry;

/**
 * PRACTICE TOPIC: Telemetry Metric Payload Models
 * 
 * Practice Focus: Primitive Arrays vs Boxed Objects footprint evaluation.
 */
public record TelemetryMetric(long metricId, double value, String statusTag, long timestamp) {
    
    public TelemetryMetric {
        if (statusTag == null) {
            statusTag = "UNKNOWN";
        }
    }
}
