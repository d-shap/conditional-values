// //////////////////////////////
// This code is authored
// Dmitry Shapovalov
// //////////////////////////////
package ru.d_shap.conditionalvalues;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * Builder for ValueSet objects.
 *
 * @param <T> Value type.
 * @author Dmitry ValueSetShapovalov
 */
public final class ValueSetBuilder<T> {

    private final Map<String, Set<String>> _conditions;

    private final Set<T> _values;

    ValueSetBuilder() {
        super();
        _conditions = new HashMap<String, Set<String>>();
        _values = new LinkedHashSet<T>();
    }

    /**
     * Add conditions to ValueSet.
     *
     * @param name   condition name.
     * @param values condition values.
     * @return current object for chaining.
     */
    public ValueSetBuilder<T> addCondition(final String name, final String... values) {
        if (values != null) {
            for (String value : values) {
                doAddCondition(name, value);
            }
        }
        return this;
    }

    /**
     * Add conditions to ValueSet.
     *
     * @param name   condition name.
     * @param values condition values.
     * @return current object for chaining.
     */
    public ValueSetBuilder<T> addCondition(final String name, final boolean... values) {
        if (values != null) {
            for (boolean value : values) {
                doAddCondition(name, String.valueOf(value));
            }
        }
        return this;
    }

    /**
     * Add conditions to ValueSet.
     *
     * @param name   condition name.
     * @param values condition values.
     * @return current object for chaining.
     */
    public ValueSetBuilder<T> addCondition(final String name, final int... values) {
        if (values != null) {
            for (int value : values) {
                doAddCondition(name, String.valueOf(value));
            }
        }
        return this;
    }

    /**
     * Add conditions to ValueSet.
     *
     * @param name   condition name.
     * @param values condition values.
     * @return current object for chaining.
     */
    public ValueSetBuilder<T> addCondition(final String name, final long... values) {
        if (values != null) {
            for (long value : values) {
                doAddCondition(name, String.valueOf(value));
            }
        }
        return this;
    }

    /**
     * Add conditions to ValueSet.
     *
     * @param name   condition name.
     * @param values condition values.
     * @return current object for chaining.
     */
    public ValueSetBuilder<T> addCondition(final String name, final float... values) {
        if (values != null) {
            for (float value : values) {
                doAddCondition(name, String.valueOf(value));
            }
        }
        return this;
    }

    /**
     * Add conditions to ValueSet.
     *
     * @param name   condition name.
     * @param values condition values.
     * @return current object for chaining.
     */
    public ValueSetBuilder<T> addCondition(final String name, final double... values) {
        if (values != null) {
            for (double value : values) {
                doAddCondition(name, String.valueOf(value));
            }
        }
        return this;
    }

    /**
     * Add conditions to ValueSet.
     *
     * @param name   condition name.
     * @param values condition values.
     * @return current object for chaining.
     */
    public ValueSetBuilder<T> addObjectCondition(final String name, final Object... values) {
        if (values != null) {
            for (Object value : values) {
                if (value != null) {
                    if (value instanceof String) {
                        doAddCondition(name, (String) value);
                    } else {
                        doAddCondition(name, value.toString());
                    }
                }
            }
        }
        return this;
    }

    private void doAddCondition(final String name, final String value) {
        if (name != null && value != null) {
            Set<String> conditionValues = _conditions.get(name);
            if (conditionValues == null) {
                conditionValues = new HashSet<String>();
                _conditions.put(name, conditionValues);
            }
            conditionValues.add(value);
        }
    }

    /**
     * Remove conditions from ValueSet.
     *
     * @param name condition name.
     * @return current object for chaining.
     */
    public ValueSetBuilder<T> removeCondition(final String name) {
        if (name != null) {
            _conditions.remove(name);
        }
        return this;
    }

    /**
     * Remove conditions from ValueSet.
     *
     * @param name   condition name.
     * @param values condition values.
     * @return current object for chaining.
     */
    public ValueSetBuilder<T> removeCondition(final String name, final String... values) {
        if (values != null) {
            for (String value : values) {
                doRemoveCondition(name, value);
            }
        }
        return this;
    }

    /**
     * Remove conditions from ValueSet.
     *
     * @param name   condition name.
     * @param values condition values.
     * @return current object for chaining.
     */
    public ValueSetBuilder<T> removeCondition(final String name, final boolean... values) {
        if (values != null) {
            for (boolean value : values) {
                doRemoveCondition(name, String.valueOf(value));
            }
        }
        return this;
    }

    /**
     * Remove conditions from ValueSet.
     *
     * @param name   condition name.
     * @param values condition values.
     * @return current object for chaining.
     */
    public ValueSetBuilder<T> removeCondition(final String name, final int... values) {
        if (values != null) {
            for (int value : values) {
                doRemoveCondition(name, String.valueOf(value));
            }
        }
        return this;
    }

    /**
     * Remove conditions from ValueSet.
     *
     * @param name   condition name.
     * @param values condition values.
     * @return current object for chaining.
     */
    public ValueSetBuilder<T> removeCondition(final String name, final long... values) {
        if (values != null) {
            for (long value : values) {
                doRemoveCondition(name, String.valueOf(value));
            }
        }
        return this;
    }

    /**
     * Remove conditions from ValueSet.
     *
     * @param name   condition name.
     * @param values condition values.
     * @return current object for chaining.
     */
    public ValueSetBuilder<T> removeCondition(final String name, final float... values) {
        if (values != null) {
            for (float value : values) {
                doRemoveCondition(name, String.valueOf(value));
            }
        }
        return this;
    }

    /**
     * Remove conditions from ValueSet.
     *
     * @param name   condition name.
     * @param values condition values.
     * @return current object for chaining.
     */
    public ValueSetBuilder<T> removeCondition(final String name, final double... values) {
        if (values != null) {
            for (double value : values) {
                doRemoveCondition(name, String.valueOf(value));
            }
        }
        return this;
    }

    /**
     * Remove conditions from ValueSet.
     *
     * @param name   condition name.
     * @param values condition values.
     * @return current object for chaining.
     */
    public ValueSetBuilder<T> removeObjectCondition(final String name, final Object... values) {
        if (values != null) {
            for (Object value : values) {
                if (value != null) {
                    if (value instanceof String) {
                        doRemoveCondition(name, (String) value);
                    } else {
                        doRemoveCondition(name, value.toString());
                    }
                }
            }
        }
        return this;
    }

    private void doRemoveCondition(final String name, final String value) {
        if (name != null && value != null) {
            Set<String> conditionValues = _conditions.get(name);
            if (conditionValues != null) {
                conditionValues.remove(value);
                if (conditionValues.isEmpty()) {
                    _conditions.remove(name);
                }
            }
        }
    }

    /**
     * Add values to ValueSet.
     *
     * @param values values to add.
     * @return current object for chaining.
     */
    public ValueSetBuilder<T> addValue(final T... values) {
        if (values != null) {
            for (T value : values) {
                if (value != null) {
                    _values.add(value);
                }
            }
        }
        return this;
    }

    /**
     * Remove values from ValueSet.
     *
     * @param values values to remove.
     * @return current object for chaining.
     */
    public ValueSetBuilder<T> removeValue(final T... values) {
        if (values != null) {
            for (T value : values) {
                if (value != null) {
                    _values.remove(value);
                }
            }
        }
        return this;
    }

    /**
     * Creates new ValueSet object.
     *
     * @return created ValueSet object.
     */
    public ValueSet<T> build() {
        ValueSet<T> valueSet = new ValueSet<T>(_conditions, _values);
        _conditions.clear();
        _values.clear();
        return valueSet;
    }

}
