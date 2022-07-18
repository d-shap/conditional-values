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
/**
 * <p>
 * Conditional values simplify conditional logic and get rid of <b>if</b>-statements in the code.
 * </p>
 * <p>
 * Conditional values is a Decision Table implementation.
 * </p>
 * <p>
 * Decision Table represents a set of conditions and corresponding actions for these conditions. This
 * Decision Table is created during a design time.
 * </p>
 * <p>
 * During a runtime Decision Table is used to find best matching conditions based on the current context.
 * So during a runtime a context and created and lookup is performed for this context. The result of
 * lookup is used to perform actions, corresponding to the triggered conditions.
 * </p>
 */
package ru.d_shap.conditionalvalues;
