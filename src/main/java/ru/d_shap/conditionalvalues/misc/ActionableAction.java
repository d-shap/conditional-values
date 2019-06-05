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
package ru.d_shap.conditionalvalues.misc;

import ru.d_shap.conditionalvalues.Action;
import ru.d_shap.conditionalvalues.Actionable;

/**
 * Action to perform for each value in the {@link ru.d_shap.conditionalvalues.Values} object.
 * Values in the {@link ru.d_shap.conditionalvalues.Values} object should implement the
 * {@link ru.d_shap.conditionalvalues.Actionable} interface.
 *
 * @author Dmitry Shapovalov
 */
public final class ActionableAction<T extends Actionable> implements Action<T> {

    /**
     * Create new object.
     */
    public ActionableAction() {
        super();
    }

    @Override
    public void perform(final T value) {
        value.perform();
    }

}
