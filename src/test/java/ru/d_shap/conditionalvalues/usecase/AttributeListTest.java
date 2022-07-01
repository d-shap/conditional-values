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
package ru.d_shap.conditionalvalues.usecase;

import org.junit.Test;

import ru.d_shap.assertions.Assertions;
import ru.d_shap.conditionalvalues.ConditionSetBuilder;
import ru.d_shap.conditionalvalues.ConditionalValues;
import ru.d_shap.conditionalvalues.ConditionalValuesBuilder;
import ru.d_shap.conditionalvalues.ValueSet;
import ru.d_shap.conditionalvalues.ValueSetBuilder;
import ru.d_shap.conditionalvalues.Values;

/**
 * Lookup example implementation.
 *
 * @author Dmitry Shapovalov
 */
public final class AttributeListTest {

    private static final String TYPE = "type";

    private static final String TYPE_CONTRACT = "contract";

    private static final String TYPE_ORDER = "order";

    private static final String IS_VIEWER = "isViewer";

    private static final String IS_EDITOR = "isEditor";

    private static final String STATE = "state";

    private static final String STATE_DRAFT = "draft";

    private static final String STATE_APPROVAL = "approval";

    /**
     * Test class constructor.
     */
    public AttributeListTest() {
        super();
    }

    /**
     * Lookup example.
     */
    @Test
    public void lookupTest() {
        ConditionalValuesBuilder<String> conditionalValuesBuilder = ConditionalValuesBuilder.newInstance();
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        valueSetBuilder.addValue("title");
        ValueSet<String> defaultAttributes = valueSetBuilder.build();
        conditionalValuesBuilder.addValueSet(defaultAttributes);

        valueSetBuilder.addCondition(TYPE, TYPE_CONTRACT);
        valueSetBuilder.addValues(defaultAttributes);
        valueSetBuilder.addValue("sum");
        valueSetBuilder.addValue("date");
        ValueSet<String> defaultContractAttributes = valueSetBuilder.build();
        conditionalValuesBuilder.addValueSet(defaultContractAttributes);

        valueSetBuilder.addCondition(TYPE, TYPE_CONTRACT);
        valueSetBuilder.addCondition(IS_VIEWER, true);
        valueSetBuilder.addValues(defaultContractAttributes);
        valueSetBuilder.addValue("approved");
        valueSetBuilder.addValue("approver");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());

        valueSetBuilder.addCondition(TYPE, TYPE_CONTRACT);
        valueSetBuilder.addCondition(IS_EDITOR, true);
        valueSetBuilder.addValues(defaultContractAttributes);
        valueSetBuilder.addValue("approver");
        valueSetBuilder.addValue("contractor");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());

        valueSetBuilder.addCondition(TYPE, TYPE_CONTRACT);
        valueSetBuilder.addCondition(STATE, STATE_DRAFT);
        valueSetBuilder.addCondition(IS_VIEWER, true);
        valueSetBuilder.addValues(defaultContractAttributes);
        valueSetBuilder.addValue("due date");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());

        valueSetBuilder.addCondition(TYPE, TYPE_ORDER);
        valueSetBuilder.addValues(defaultAttributes);
        valueSetBuilder.addValue("employee");
        valueSetBuilder.addValue("manager");
        ValueSet<String> defaultOrderAttributes = valueSetBuilder.build();
        conditionalValuesBuilder.addValueSet(defaultOrderAttributes);

        ConditionalValues<String> conditionalValues = conditionalValuesBuilder.build();

        conditionSetBuilder.addCondition(TYPE, TYPE_CONTRACT);
        conditionSetBuilder.addCondition(STATE, STATE_DRAFT);
        conditionSetBuilder.addCondition(IS_VIEWER, true);
        Values<String> values1 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values1.getUniqueValues()).containsExactly("title", "sum", "date", "due date");

        conditionSetBuilder.addCondition(TYPE, TYPE_CONTRACT);
        conditionSetBuilder.addCondition(STATE, STATE_APPROVAL);
        conditionSetBuilder.addCondition(IS_VIEWER, true);
        Values<String> values2 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values2.getUniqueValues()).containsExactly("title", "sum", "date", "approved", "approver");

        conditionSetBuilder.addCondition(TYPE, TYPE_CONTRACT);
        conditionSetBuilder.addCondition(IS_VIEWER, true);
        conditionSetBuilder.addCondition(IS_EDITOR, true);
        Values<String> values3 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values3.getUniqueValues()).containsExactly("title", "sum", "date", "approved", "approver", "contractor");

        conditionSetBuilder.addCondition(TYPE, TYPE_CONTRACT);
        conditionSetBuilder.addCondition(STATE, STATE_DRAFT);
        conditionSetBuilder.addCondition(IS_VIEWER, true);
        conditionSetBuilder.addCondition(IS_EDITOR, true);
        Values<String> values4 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values4.getUniqueValues()).containsExactly("title", "sum", "date", "approver", "contractor", "due date");

        conditionSetBuilder.addCondition(TYPE, TYPE_CONTRACT);
        conditionSetBuilder.addCondition(IS_VIEWER, true);
        Values<String> values5 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values5.getUniqueValues()).containsExactly("title", "sum", "date", "approved", "approver");

        conditionSetBuilder.addCondition(TYPE, TYPE_ORDER);
        conditionSetBuilder.addCondition(IS_EDITOR, true);
        Values<String> values6 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values6.getUniqueValues()).containsExactly("title", "employee", "manager");

        conditionSetBuilder.addCondition(IS_VIEWER, true);
        conditionSetBuilder.addCondition(IS_EDITOR, true);
        Values<String> values7 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values7.getUniqueValues()).containsExactly("title");
    }

}
