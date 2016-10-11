// //////////////////////////////
// This code is authored
// Dmitry Shapovalov
// //////////////////////////////
package ru.d_shap.conditionalvalues;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Builder class used to create {@link ru.d_shap.conditionalvalues.ConditionSet} objects.
 * </p>
 * <p>
 * Object of this class is reusable. After calling the {@link #build()} method this object can be
 * used to create another {@link ru.d_shap.conditionalvalues.ConditionSet} object.
 * </p>
 * <p>
 * The internal presentation of conditions is {@code Map<String, String>}.
 * </p>
 * <p>
 * Methods {@link #addBooleanCondition(String, boolean)}, {@link #addIntegerCondition(String, int)},
 * {@link #addLongCondition(String, long)}, {@link #addFloatCondition(String, float)},
 * {@link #addDoubleCondition(String, double)} and {@link #addObjectCondition(String, Object)}
 * are convenient methods for {@link #addStringCondition(String, String)}.
 * </p>
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
    public ConditionSetBuilder addStringCondition(final String name, final String value) {
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
    public ConditionSetBuilder addBooleanCondition(final String name, final boolean value) {
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
    public ConditionSetBuilder addIntegerCondition(final String name, final int value) {
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
    public ConditionSetBuilder addLongCondition(final String name, final long value) {
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
    public ConditionSetBuilder addFloatCondition(final String name, final float value) {
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
    public ConditionSetBuilder addDoubleCondition(final String name, final double value) {
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
     * @return {@link ru.d_shap.conditionalvalues.ConditionSet} object, populated with the values, added to this builder.
     */
    public ConditionSet build() {
        ConditionSet conditionSet = new ConditionSet(_conditions);
        _conditions.clear();
        return conditionSet;
    }

}
