package com.cloudskol.ifeel.widget;

import com.cloudskol.ifeel.common.Tuple;

/**
 * @author tham
 */

public class WidgetAggregation {
    private Tuple positive;
    private Tuple negative;

    public WidgetAggregation(Tuple positive, Tuple negative) {
        this.positive = positive;
        this.negative = negative;
    }

    public Tuple getPositive() {
        return positive;
    }

    public Tuple getNegative() {
        return negative;
    }
}
