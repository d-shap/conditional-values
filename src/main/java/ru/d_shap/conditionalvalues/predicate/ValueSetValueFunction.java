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
package ru.d_shap.conditionalvalues.predicate;

/**
 * The function to apply to the value from the {@link ru.d_shap.conditionalvalues.ValueSet} object.
 *
 * @author Dmitry Shapovalov
 */
public interface ValueSetValueFunction {

    /**
     * Apply the function to the value from the {@link ru.d_shap.conditionalvalues.ValueSet} object.
     *
     * @param valueSetValue the value from the {@link ru.d_shap.conditionalvalues.ValueSet} object.
     *
     * @return the result of the function application to the value from the {@link ru.d_shap.conditionalvalues.ValueSet} object.
     */
    Object apply(Object valueSetValue);

}
