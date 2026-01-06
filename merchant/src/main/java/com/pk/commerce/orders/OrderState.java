package com.pk.commerce.orders;

import com.pk.commerce.orders.event.*;

public enum OrderState {
    CUSTOMER_INITIATED {
        @Override
        public OrderState nextState(OrderEvent event) {
            return switch (event) {
                case CustomerAbandonedOrderEvent _ -> CUSTOMER_ABANDONED;
                case CustomerInitiatedOrderEvent _ -> CUSTOMER_INITIATED;
                case CustomerEnteredOrderEvent _ -> CUSTOMER_ENTERED;
                case null, default -> throw new InvalidStateForEventException();
            };
        }
    },//customer opens the page
    CUSTOMER_ABANDONED {
        @Override
        public OrderState nextState(OrderEvent event) {
            if (event instanceof CustomerAbandonedOrderEvent) {
                return CUSTOMER_ABANDONED;
            } else {
                throw new InvalidStateForEventException();
            }
        }
    },
    CUSTOMER_ENTERED {
        @Override
        public OrderState nextState(OrderEvent event) {
            if (event instanceof MerchantConfirmedOrderEvent) {
                return MERCHANT_CONFIRMED;
            } else if (event instanceof MerchantDeclinedOrderEvent) {
                return MERCHANT_DECLINED;
            } else {
                throw new InvalidStateForEventException();
            }
        }
    },
    MERCHANT_CONFIRMED {
        @Override
        public OrderState nextState(OrderEvent event) {
            if (event instanceof MerchantConfirmedOrderEvent) {
                return MERCHANT_CONFIRMED;
            } else if (event instanceof MerchantSentOrderEvent) {
                return MERCHANT_SENT;
            } else {
                throw new InvalidStateForEventException();
            }
        }
    },
    MERCHANT_DECLINED {
        @Override
        public OrderState nextState(OrderEvent event) {
            if (!(event instanceof MerchantDeclinedOrderEvent)) {
                throw new InvalidStateForEventException();
            }
            return MERCHANT_DECLINED;
        }
    },

    MERCHANT_SENT {
        @Override
        public OrderState nextState(OrderEvent event) {
            if (event instanceof OrderDeliveredEvent) {
                return DELIVERED_TO_CUSTOMER;
            } else if (event instanceof OrderReturnedEvent) {
                return RETURNED_TO_MERCHANT;
            } else {
                throw new InvalidStateForEventException();
            }
        }
    },
    DELIVERED_TO_CUSTOMER {
        @Override
        public OrderState nextState(OrderEvent event) {
            if (event instanceof OrderDeliveredEvent) {
                return DELIVERED_TO_CUSTOMER;
            } else {
                throw new InvalidStateForEventException();
            }
        }
    },
    RETURNED_TO_MERCHANT {
        @Override
        public OrderState nextState(OrderEvent event) {
            if (event instanceof OrderReturnedEvent) {
                return RETURNED_TO_MERCHANT;
            } else {
                throw new InvalidStateForEventException();
            }
        }
    };

    public abstract OrderState nextState(OrderEvent event);
}
