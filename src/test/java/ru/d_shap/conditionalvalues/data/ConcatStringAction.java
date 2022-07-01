///////////////////////////////////////////////////////////////////////////////////////////////////
// Conditional values simplify conditional logic and get rid of if-statements in the code.
// Copyright (C) 2016 Dmitry Shapovalov.
//
// This file is part of conditional values.
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
package ru.d_shap.conditionalvalues.data;

import java.util.LinkedHashSet;
import java.util.Set;

import ru.d_shap.conditionalvalues.Action;

/**
 * Action to add prefix and suffix to the value.
 *
 * @author Dmitry Shapovalov
 */
public final class ConcatStringAction implements Action<String> {

    private final Set<String> _values;

    private final String _prefix;

    private final int _suffix;

    /**
     * Create new object.
     *
     * @param prefix the prefix to add.
     * @param suffix the suffix to add.
     */
    public ConcatStringAction(final String prefix, final int suffix) {
        super();
        _values = new LinkedHashSet<>();
        _prefix = prefix;
        _suffix = suffix;
    }

    /**
     * Create new object.
     *
     * @param concatStringAction the action.
     * @param prefix             the prefix to add.
     * @param suffix             the suffix to add.
     */
    public ConcatStringAction(final ConcatStringAction concatStringAction, final String prefix, final int suffix) {
        super();
        _values = concatStringAction._values;
        _prefix = prefix;
        _suffix = suffix;
    }

    @Override
    public void perform(final String value) {
        String newValue = value;
        if (_prefix != null) {
            newValue = _prefix + newValue;
        }
        if (_suffix > 0) {
            newValue += "_" + _suffix;
        }
        _values.add(newValue);
    }

    /**
     * Get the values.
     *
     * @return the values.
     */
    public Set<String> getValues() {
        return _values;
    }

}
