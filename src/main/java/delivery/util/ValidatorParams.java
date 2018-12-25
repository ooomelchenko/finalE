package delivery.util;

import delivery.model.entity.Order;

public class ValidatorParams {

    public boolean checkId(String id) {
        try {
            return Long.parseLong(id) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean checkOrderType(String orderType) {
        try {
            Order.Type.valueOf(orderType);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean checkWeight(String weight) {
        try {
            return Integer.parseInt(weight) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public ValidatorParams() {
    }
}
