// Conditional values simplify conditional logic and get rid of if-statements in the code.
// Copyright (C) 2016 Dmitry Shapovalov.
//
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program.  If not, see <http://www.gnu.org/licenses/>.
/**
 * <p>
 * Classes simplify conditional logic, getting rid of <b>if</b>-statements in the code.
 * </p>
 * <p>
 * The main purpose is to find the best result from many predefined conditions.
 * </p>
 * <p>
 * For example, we should display object attributes based on some conditions.
 * </p>
 * <p>
 * The conditions could be:
 * </p>
 * <ul>
 * <li>object type with many differen values (type1, type2, type3, etc.)</li>
 * <li>object current state with many differen values (1, 2, 3, etc.)</li>
 * <li>user role (initiator, performer, administrator, etc.)</li>
 * <li>some other values</li>
 * </ul>
 * <p>
 * And based on this conditions there are different visible attributes (attribute1, attribute2, attribute3, etc.)
 * </p>
 * <p>
 * First we create {@link ru.d_shap.conditionalvalues.ValueSet} for each distinct condition:
 * </p>
 * <pre>{@code
 * ValueSetBuilder<String> valueSetBuilder = ConditionalValues.createValueSetBuilder();
 *
 * valueSetBuilder.addStringCondition("type", "type1");
 * valueSetBuilder.addIntegerCondition("state", 1);
 * valueSetBuilder.addStringCondition("role", "initiator");
 * valueSetBuilder.addValue("attribute1", "attribute2");
 * ValueSet<String> type1Initiator1ValueSet = valueSetBuilder.build();
 *
 * valueSetBuilder.addStringCondition("type", "type1");
 * valueSetBuilder.addIntegerCondition("state", 2, 3);
 * valueSetBuilder.addStringCondition("role", "initiator");
 * valueSetBuilder.addValue("attribute2", "attribute3");
 * ValueSet<String> type1Initiator23ValueSet = valueSetBuilder.build();
 *
 * valueSetBuilder.addStringCondition("type", "type1");
 * valueSetBuilder.addIntegerCondition("state", 2, 3);
 * valueSetBuilder.addStringCondition("role", "performer");
 * valueSetBuilder.addValue("attribute1", "attribute3");
 * ValueSet<String> type1Performer23ValueSet = valueSetBuilder.build();
 * }</pre>
 * <p>
 * Then we store this conditions in a single {@link ru.d_shap.conditionalvalues.ConditionalValues} object:
 * </p>
 * <pre>{@code
 * List<ValueSet<String>> valueSets = new ArrayList<ValueSet<String>>();
 * valueSets.add(type1Initiator1ValueSet);
 * valueSets.add(type1Initiator23ValueSet);
 * valueSets.add(type1Performer23ValueSet);
 * ...
 * ConditionalValues<String> conditionalValues = ConditionalValues.createConditionalValues(valueSets);
 * }</pre>
 * <p>
 * This {@link ru.d_shap.conditionalvalues.ConditionalValues} object could be created statically.
 * </p>
 * <p>
 * In runtime now we can define visible attributes based on current condition.
 * To perform this we create {@link ru.d_shap.conditionalvalues.ConditionSet} object:
 * </p>
 * <pre>{@code
 * ConditionSetBuilder conditionSetBuilder = ConditionalValues.createConditionSetBuilder();
 * conditionSetBuilder.addStringCondition("type", "type1");
 * conditionSetBuilder.addIntegerCondition("state", 2);
 * conditionSetBuilder.addStringCondition("role", "performer");
 * ConditionSet conditionSet = conditionSetBuilder.build();
 * }</pre>
 * <p>
 * And get matching {@link ru.d_shap.conditionalvalues.ValueSet} objects from {@link ru.d_shap.conditionalvalues.ConditionalValues} object:
 * </p>
 * <pre>{@code
 * Values<String> values = conditionalValues.getValues(conditionSet);
 * }</pre>
 * <p>
 * Now we can use {@link ru.d_shap.conditionalvalues.Values} to get all visible attributes, or to
 * test if {@link ru.d_shap.conditionalvalues.Values} object contains specified attribute.
 * </p>
 * <p>
 * The best condition is defined by matching conditions count.
 * </p>
 * <p>
 * For example, there are predefined conditions:
 * </p>
 * <p>
 * <b>Value Set 1</b>
 * </p>
 * <ul>
 * <li>type: <b>type1</b></li>
 * <li>isInitiator: <b>true</b></li>
 * </ul>
 * <p>
 * <b>Value Set 2</b>
 * </p>
 * <ul>
 * <li>type: <b>type1</b></li>
 * <li>isPerformer: <b>true</b></li>
 * </ul>
 * <p>
 * <b>Value Set 3</b>
 * </p>
 * <ul>
 * <li>type: <b>type1</b></li>
 * <li>state: <b>1</b></li>
 * <li>isInitiator: <b>true</b></li>
 * </ul>
 * <p>
 * Then if we have runtime conditions (type = <b>type1</b>, state = <b>1</b>, isInitiator = <b>true</b>),
 * then the best matching value set is <b>Value Set 3</b> (the only one matching value set).
 * </p>
 * <p>
 * If we have runtime conditions (type = <b>type1</b>, isInitiator = <b>true</b>, isPerformer = <b>true</b>),
 * then the best matching value sets are <b>Value Set 1</b> and <b>Value Set 2</b> ({@link ru.d_shap.conditionalvalues.Values} object
 * contains values from both {@link ru.d_shap.conditionalvalues.ValueSet} objects).
 * </p>
 * <p>
 * If we have runtime conditions (type = <b>type1</b>, state = <b>1</b>, isInitiator = <b>true</b>, isPerformer = <b>true</b>),
 * then the best matching value sets are <b>Value Set 2</b> and  <b>Value Set 3</b> (<b>Value Set 1</b> also matches,
 * but <b>Value Set 3</b> is more specific then <b>Value Set 1</b>).
 * </p>
 * <p>
 * If we have runtime conditions (type = <b>type1</b>, isInitiator = <b>true</b>),
 * then the best matching value set is <b>Value Set 1</b> (the only one matching value set).
 * </p>
 * <p>
 * If we have runtime conditions (isInitiator = <b>true</b>, isPerformer = <b>true</b>),
 * then there are no matching value sets ({@link ru.d_shap.conditionalvalues.Values} object is empty).
 * </p>
 */
package ru.d_shap.conditionalvalues;
