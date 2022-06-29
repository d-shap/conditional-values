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

import ru.d_shap.conditionalvalues.Action;

/**
 * Action to sum all values in the {@link ru.d_shap.conditionalvalues.Values} object.
 *
 * @author Dmitry Shapovalov
 */
public final class SumAction implements Action<Integer> {

    private int _sum;

    /**
     * Create new object.
     */
    public SumAction() {
        super();
        _sum = 0;
    }

    @Override
    public void perform(final Integer value) {
        _sum += value;
    }

    /**
     * Get the sum.
     *
     * @return the sum.
     */
    public int getSum() {
        return _sum;
    }

}
