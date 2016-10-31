///////////////////////////////////////////////////////////////////////////////////////////////////
// Conditional values simplify conditional logic and get rid of if-statements in the code.
// Copyright (C) 2016 Dmitry Shapovalov.
//
// This file is part of Conditional values.
//
// Conditional values is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// Conditional values is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with this program. If not, see <http://www.gnu.org/licenses/>.
///////////////////////////////////////////////////////////////////////////////////////////////////
package ru.d_shap.conditionalvalues;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * Builder class used to create {@link ru.d_shap.conditionalvalues.ValueSet} objects.
 * </p>
 * <p>
 * Object of this class is reusable. After calling the {@link #build()} method this object can be
 * used to create another {@link ru.d_shap.conditionalvalues.ValueSet} object.
 * </p>
 * <p>
 * The internal presentation of conditions is {@code Map<String, Set<String>>}.
 * </p>
 * <p>
 * Conditions with different names are ANDed. Conditions with the same name are ORed.
 * </p>
 * <p>
 * For example:
 * </p>
 * <pre>{@code
 * ValueSetBuilder<String> valueSetBuilder = ConditionalValues.createValueSetBuilder();
 * valueSetBuilder.addStringCondition("type", "type1");
 * valueSetBuilder.addIntegerCondition("state", 1, 2);
 * valueSetBuilder.addIntegerCondition("state", 3);
 * ValueSet<String> valueSet = valueSetBuilder.build();
 * }</pre>
 * <p>
 * means type = type1 AND (state = 1 OR state = 2 OR state = 3).
 * </p>
 * <p>
 * Methods {@link #addBooleanCondition(String, boolean...)}, {@link #addIntegerCondition(String, int...)},
 * {@link #addLongCondition(String, long...)}, {@link #addFloatCondition(String, float...)},
 * {@link #addDoubleCondition(String, double...)} and {@link #addObjectCondition(String, Object...)}
 * are convenient methods for {@link #addStringCondition(String, String...)}
 * </p>
 * <p>
 * Methods {@link #removeBooleanCondition(String, boolean...)}, {@link #removeIntegerCondition(String, int...)},
 * {@link #removeLongCondition(String, long...)}, {@link #removeFloatCondition(String, float...)},
 * {@link #removeDoubleCondition(String, double...)} and {@link #removeObjectCondition(String, Object...)}
 * are convenient methods for {@link #removeStringCondition(String, String...)}
 * </p>
 * <p>
 * The internal presentation of values is {@code Set<T>}.
 * </p>
 *
 * @param <T> value type.
 * @author Dmitry Shapovalov
 */
public final class ValueSetBuilder<T> {

    private final Map<String, Set<String>> _conditions;

    private final Set<T> _values;

    ValueSetBuilder() {
        super();
        _conditions = new HashMap<String, Set<String>>();
        _values = new HashSet<T>();
    }

    /**
     * Add conditions to the set.
     *
     * @param name   condition name.
     * @param values condition values.
     * @return current object for chaining.
     */
    public ValueSetBuilder<T> addStringCondition(final String name, final String... values) {
        if (values != null) {
            for (String value : values) {
                doAddCondition(name, value);
            }
        }
        return this;
    }

    /**
     * Add conditions to the set.
     *
     * @param name   condition name.
     * @param values condition values.
     * @return current object for chaining.
     */
    public ValueSetBuilder<T> addBooleanCondition(final String name, final boolean... values) {
        if (values != null) {
            for (boolean value : values) {
                doAddCondition(name, String.valueOf(value));
            }
        }
        return this;
    }

    /**
     * Add conditions to the set.
     *
     * @param name   condition name.
     * @param values condition values.
     * @return current object for chaining.
     */
    public ValueSetBuilder<T> addIntegerCondition(final String name, final int... values) {
        if (values != null) {
            for (int value : values) {
                doAddCondition(name, String.valueOf(value));
            }
        }
        return this;
    }

    /**
     * Add conditions to the set.
     *
     * @param name   condition name.
     * @param values condition values.
     * @return current object for chaining.
     */
    public ValueSetBuilder<T> addLongCondition(final String name, final long... values) {
        if (values != null) {
            for (long value : values) {
                doAddCondition(name, String.valueOf(value));
            }
        }
        return this;
    }

    /**
     * Add conditions to the set.
     *
     * @param name   condition name.
     * @param values condition values.
     * @return current object for chaining.
     */
    public ValueSetBuilder<T> addFloatCondition(final String name, final float... values) {
        if (values != null) {
            for (float value : values) {
                doAddCondition(name, String.valueOf(value));
            }
        }
        return this;
    }

    /**
     * Add conditions to the set.
     *
     * @param name   condition name.
     * @param values condition values.
     * @return current object for chaining.
     */
    public ValueSetBuilder<T> addDoubleCondition(final String name, final double... values) {
        if (values != null) {
            for (double value : values) {
                doAddCondition(name, String.valueOf(value));
            }
        }
        return this;
    }

    /**
     * Add conditions to the set.
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
     * Remove conditions from the set.
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
     * Remove conditions from the set.
     *
     * @param name   condition name.
     * @param values condition values.
     * @return current object for chaining.
     */
    public ValueSetBuilder<T> removeStringCondition(final String name, final String... values) {
        if (values != null) {
            for (String value : values) {
                doRemoveCondition(name, value);
            }
        }
        return this;
    }

    /**
     * Remove conditions from the set.
     *
     * @param name   condition name.
     * @param values condition values.
     * @return current object for chaining.
     */
    public ValueSetBuilder<T> removeBooleanCondition(final String name, final boolean... values) {
        if (values != null) {
            for (boolean value : values) {
                doRemoveCondition(name, String.valueOf(value));
            }
        }
        return this;
    }

    /**
     * Remove conditions from the set.
     *
     * @param name   condition name.
     * @param values condition values.
     * @return current object for chaining.
     */
    public ValueSetBuilder<T> removeIntegerCondition(final String name, final int... values) {
        if (values != null) {
            for (int value : values) {
                doRemoveCondition(name, String.valueOf(value));
            }
        }
        return this;
    }

    /**
     * Remove conditions from the set.
     *
     * @param name   condition name.
     * @param values condition values.
     * @return current object for chaining.
     */
    public ValueSetBuilder<T> removeLongCondition(final String name, final long... values) {
        if (values != null) {
            for (long value : values) {
                doRemoveCondition(name, String.valueOf(value));
            }
        }
        return this;
    }

    /**
     * Remove conditions from the set.
     *
     * @param name   condition name.
     * @param values condition values.
     * @return current object for chaining.
     */
    public ValueSetBuilder<T> removeFloatCondition(final String name, final float... values) {
        if (values != null) {
            for (float value : values) {
                doRemoveCondition(name, String.valueOf(value));
            }
        }
        return this;
    }

    /**
     * Remove conditions from the set.
     *
     * @param name   condition name.
     * @param values condition values.
     * @return current object for chaining.
     */
    public ValueSetBuilder<T> removeDoubleCondition(final String name, final double... values) {
        if (values != null) {
            for (double value : values) {
                doRemoveCondition(name, String.valueOf(value));
            }
        }
        return this;
    }

    /**
     * Remove conditions from the set.
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
     * Add values to the set.
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
     * Remove values from the set.
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
     * Creates new {@link ru.d_shap.conditionalvalues.ValueSet} object.
     *
     * @return {@link ru.d_shap.conditionalvalues.ValueSet} object, populated with the values, added to this builder.
     */
    public ValueSet<T> build() {
        ValueSet<T> valueSet = new ValueSet<T>(_conditions, _values);
        _conditions.clear();
        _values.clear();
        return valueSet;
    }

}
