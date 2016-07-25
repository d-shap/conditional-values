// //////////////////////////////
// This code is authored
// Dmitry Shapovalov
// //////////////////////////////
package ru.d_shap.conditionalvalues;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Object represents current conditions. Used to lookup {@link ru.d_shap.conditionalvalues.Values} in runtime.
 *
 * @author Dmitry Shapovalov
 */
public final class ConditionSet {

    private final Map<String, String> _conditions;

    private final Set<String> _conditionNames;

    ConditionSet(final Map<String, String> conditions) {
        super();
        _conditions = new HashMap<String, String>(conditions);
        _conditionNames = Collections.unmodifiableSet(_conditions.keySet());
    }

    Iterator<String> nameIterator() {
        return _conditionNames.iterator();
    }

    String getCondition(final String name) {
        return _conditions.get(name);
    }

    @Override
    public String toString() {
        return _conditions.toString();
    }

}
