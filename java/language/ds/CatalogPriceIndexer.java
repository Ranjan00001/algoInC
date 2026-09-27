package language.ds;

import language.model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * DOMAIN SCENARIO 6: Catalog Price Range Indexer
 * 
 * Business Requirement:
 * E-commerce customers frequently filter orders or catalog items within specific price ranges (e.g. find all orders priced between $100.00 and $500.00).
 * 
 * Operational Constraint:
 * Keys must be kept automatically in natural sorted order, allowing logarithmic $O(\log N)$ range-view operations without scanning all entries.
 * 
 * TODO: Task
 * 1. Choose and instantiate the Red-Black Tree backed Map structure in Java Collections.
 * 2. Implement indexOrder and getOrdersInPriceRange.
 */
public class CatalogPriceIndexer {

    // TODO: Choose and initialize your chosen Navigable Map field here.
    private final NavigableMap<Double, List<Order>> priceIndex = new TreeMap<>();

    /**
     * Index an order by its base amount.
     */
    public void indexOrder(Order order) {
        // TODO: Store order in list corresponding to its price key
        double price = order.getBaseAmount();
        priceIndex.computeIfAbsent(price, k -> new ArrayList<>()).add(order);
    }

    /**
     * Retrieve all orders whose prices fall within [minPrice, maxPrice] inclusive.
     */
    public List<Order> getOrdersInPriceRange(double minPrice, double maxPrice) {
        // TODO: Perform subMap/range view extraction
        List<Order> result = new ArrayList<>();
        NavigableMap<Double, List<Order>> rangeView = priceIndex.subMap(minPrice, true, maxPrice, true);
        for (List<Order> list : rangeView.values()) {
            result.addAll(list);
        }
        return result;
    }

    public int getIndexedPricePointsCount() {
        return priceIndex.size();
    }
}
