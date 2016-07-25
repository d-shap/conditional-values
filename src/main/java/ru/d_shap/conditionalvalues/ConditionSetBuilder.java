// //////////////////////////////
// This code is authored
// Dmitry Shapovalov
// //////////////////////////////
package ru.d_shap.conditionalvalues;

import java.util.HashMap;
import java.util.Map;

/**
 * Builder class used to constract {@link ru.d_shap.conditionalvalues.ConditionSet} objects. Object of this class is reusable. After calling {@link #build()} method this object can be used to create another  {@link ru.d_shap.conditionalvalues.ConditionSet} object.
 *
 * @author Dmitry Shapovalov
 */
public final class ConditionSetBuilder {

    private final Map<String, String> _conditions;

    ConditionSetBuilder() {
        super();
        _conditions = new HashMap<String, String>();
    }

    /**
     * Add contition to the set. Replaces old value with the new one.
     *
     * @param name  condition name.
     * @param value condition value.
     * @return current object for chaining.
     */
    public ConditionSetBuilder addCondition(final String name, final String value) {
        doAddCondition(name, value);
        return this;
    }

    /**
     * Add contition to the set. Replaces old value with the new one.
     *
     * @param name  condition name.
     * @param value condition value.
     * @return current object for chaining.
     */
    public ConditionSetBuilder addCondition(final String name, final boolean value) {
        doAddCondition(name, String.valueOf(value));
        return this;
    }

    /**
     * Add contition to the set. Replaces old value with the new one.
     *
     * @param name  condition name.
     * @param value condition value.
     * @return current object for chaining.
     */
    public ConditionSetBuilder addCondition(final String name, final int value) {
        doAddCondition(name, String.valueOf(value));
        return this;
    }

    /**
     * Add contition to the set. Replaces old value with the new one.
     *
     * @param name  condition name.
     * @param value condition value.
     * @return current object for chaining.
     */
    public ConditionSetBuilder addCondition(final String name, final long value) {
        doAddCondition(name, String.valueOf(value));
        return this;
    }

    /**
     * Add contition to the set. Replaces old value with the new one.
     *
     * @param name  condition name.
     * @param value condition value.
     * @return current object for chaining.
     */
    public ConditionSetBuilder addCondition(final String name, final float value) {
        doAddCondition(name, String.valueOf(value));
        return this;
    }

    /**
     * Add contition to the set. Replaces old value with the new one.
     *
     * @param name  condition name.
     * @param value condition value.
     * @return current object for chaining.
     */
    public ConditionSetBuilder addCondition(final String name, final double value) {
        doAddCondition(name, String.valueOf(value));
        return this;
    }

    /**
     * Add contition to the set. Replaces old value with the new one.
     *
     * @param name  condition name.
     * @param value condition value.
     * @return current object for chaining.
     */
    public ConditionSetBuilder addObjectCondition(final String name, final Object value) {
        if (value != null) {
            if (value instanceof String) {
                doAddCondition(name, (String) value);
            } else {
                doAddCondition(name, value.toString());
            }
        }
        return this;
    }

    private void doAddCondition(final String name, final String value) {
        if (name != null && value != null) {
            _conditions.put(name, value);
        }
    }

    /**
     * Remove contition from the set.
     *
     * @param name condition name.
     * @return current object for chaining.
     */
    public ConditionSetBuilder removeCondition(final String name) {
        if (name != null) {
            _conditions.remove(name);
        }
        return this;
    }

    /**
     * Creates new {@link ru.d_shap.conditionalvalues.ConditionSet} object.
     *
     * @return {@link ru.d_shap.conditionalvalues.ConditionSet}, populated with values, added to this builder.
     */
    public ConditionSet build() {
        ConditionSet conditionSet = new ConditionSet(_conditions);
        _conditions.clear();
        return conditionSet;
    }

}
