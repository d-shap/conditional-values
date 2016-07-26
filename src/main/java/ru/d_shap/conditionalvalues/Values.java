// //////////////////////////////
// This code is authored
// Dmitry Shapovalov
// //////////////////////////////
package ru.d_shap.conditionalvalues;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents defined in runtime values.
 *
 * @param <T> Value type.
 * @author Dmitry Shapovalov
 */
public final class Values<T> {

    private final List<ValueSet<T>> _valueSets;

    private final Set<T> _allValues;

    Values(final List<ValueSet<T>> valueSets) {
        super();
        _valueSets = Collections.unmodifiableList(new ArrayList<ValueSet<T>>(valueSets));
        Set<T> allValues = new LinkedHashSet<T>();
        for (ValueSet<T> valueSet : _valueSets) {
            allValues.addAll(valueSet.getAllValues());
        }
        _allValues = Collections.unmodifiableSet(allValues);
    }

    /**
     * Does this object contains any values.
     *
     * @return true if does not.
     */
    public boolean isEmpty() {
        return _allValues.isEmpty();
    }

    /**
     * Does this object contains specified value.
     *
     * @param value test value.
     * @return true if contains.
     */
    public boolean contains(final T value) {
        return _allValues.contains(value);
    }

    /**
     * Return all values contained in this object.
     *
     * @return all values.
     */
    public Set<T> getAllValues() {
        return _allValues;
    }

    @Override
    public String toString() {
        return _valueSets.toString();
    }

}
