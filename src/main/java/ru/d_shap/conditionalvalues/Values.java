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

public final class Values<T> {

    private final List<ValueSet<T>> _valueSets;

    Values(final List<ValueSet<T>> valueSets) {
        super();
        _valueSets = Collections.unmodifiableList(new ArrayList<ValueSet<T>>(valueSets));
    }

    public boolean isEmpty() {
        for (ValueSet<T> valueSet : _valueSets) {
            if (!valueSet.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public boolean contains(final T value) {
        for (ValueSet<T> valueSet : _valueSets) {
            if (valueSet.contains(value)) {
                return true;
            }
        }
        return false;
    }

    public Set<T> getAllValues() {
        Set<T> result = new LinkedHashSet<T>();
        for (ValueSet<T> valueSet : _valueSets) {
            result.addAll(valueSet.getAllValues());
        }
        return result;
    }

    @Override
    public String toString() {
        return _valueSets.toString();
    }

}
