package com.pk.commerce.order.state;

import com.pk.commerce.order.domain.OrderStateHandler;
import com.pk.commerce.order.event.CustomerAbandonedOrderEvent;
import com.pk.commerce.order.event.CustomerEnteredOrderEvent;
import org.junit.jupiter.api.Test;
import com.pk.commerce.order.api.Order;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderStateHandlerTest {
    OrderStateHandler stateHandler = new OrderStateHandler();

    @Test
    void orderCreatedAndInCustomerInitiatedState(){
        Order order = new Order();

        assertEquals(OrderState.CUSTOMER_INITIATED, order.getState());
    }

    @Test
    void shouldTransitionFronCustomerInitiatedToEntered() {
        Order order = new Order();

        stateHandler.handleOrderEvent(order, new CustomerEnteredOrderEvent(order.getOrderRef(), System.currentTimeMillis()));
        assertEquals(OrderState.CUSTOMER_ENTERED, order.getState());
    }


    @Test
    void shouldTransitionFronCustomerInitiatedToAbandoned() {
        Order order = new Order();

        stateHandler.handleOrderEvent(order, new CustomerAbandonedOrderEvent(order.getOrderRef(), System.currentTimeMillis()));
        assertEquals(OrderState.CUSTOMER_ABANDONED, order.getState());
    }
}
