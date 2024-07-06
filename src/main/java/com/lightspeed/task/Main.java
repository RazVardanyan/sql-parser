package com.lightspeed.task;

import com.lightspeed.task.executor.SqlParserProcessorManager;
import com.lightspeed.task.facade.SqlParserFacade;
import com.lightspeed.task.service.SqlTokenizationService;
import com.lightspeed.task.utils.ComponentContainer;
import com.lightspeed.task.validation.SelectSyntaxValidation;

import static com.lightspeed.task.utils.ComponentContainer.get;

public class Main {

    public static void main(String[] args) {
        ComponentContainer.initializeContext();
        var query = "SELECT DISTINCT c.customer_id AS customerId, c.name AS customerName, o.order_id AS orderId, " +
                "p.product_name AS productName, oi.quantity AS quantityOrdered, r.rating AS productRating " +
                "FROM customers c " +
                "INNER JOIN orders o ON c.customer_id = o.customer_id " +
                "LEFT JOIN order_items oi ON o.order_id = oi.order_id " +
                "RIGHT JOIN products p ON oi.product_id = p.product_id " +
                "FULL JOIN reviews r ON c.customer_id = r.customer_id AND p.product_id = r.product_id " +
                "WHERE c.created_at > '2023-01-01' AND o.total_amount > 100 " +
                "GROUP BY c.customer_id, c.name, o.order_id, p.product_name, oi.quantity, r.rating " +
                "HAVING COUNT(o.order_id) > 1 " +
                "ORDER BY c.name ASC, o.order_id DESC " +
                "LIMIT 10 " +
                "OFFSET 5";
        var facade = new SqlParserFacade(
                get(SelectSyntaxValidation.class),
                get(SqlTokenizationService.class),
                get(SqlParserProcessorManager.class)
        );
        facade.parse(query);
    }

}