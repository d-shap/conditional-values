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
import ru.d_shap.conditionalvalues.ValueSetBuilder;
import ru.d_shap.conditionalvalues.Values;

/**
 * Lookup example implementation.
 *
 * @author Dmitry Shapovalov
 */
public final class AttributeListTest {

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

        valueSetBuilder.addCondition("type", "contract");
        valueSetBuilder.addCondition("isViewer", true);
        valueSetBuilder.addValue("title");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("type", "contract");
        valueSetBuilder.addCondition("isEditor", true);
        valueSetBuilder.addValue("subject");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("type", "contract");
        valueSetBuilder.addCondition("state", "draft");
        valueSetBuilder.addCondition("isViewer", true);
        valueSetBuilder.addValue("due date");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());

        ConditionalValues<String> conditionalValues = conditionalValuesBuilder.build();

        conditionSetBuilder.addCondition("type", "contract");
        conditionSetBuilder.addCondition("state", "draft");
        conditionSetBuilder.addCondition("isViewer", true);
        Values<String> values1 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values1.getValues()).containsExactly("due date");

        conditionSetBuilder.addCondition("type", "contract");
        conditionSetBuilder.addCondition("state", "approval");
        conditionSetBuilder.addCondition("isViewer", true);
        Values<String> values2 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values2.getValues()).containsExactly("title");

        conditionSetBuilder.addCondition("type", "contract");
        conditionSetBuilder.addCondition("isViewer", true);
        conditionSetBuilder.addCondition("isEditor", true);
        Values<String> values3 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values3.getValues()).containsExactly("title", "subject");

        conditionSetBuilder.addCondition("type", "contract");
        conditionSetBuilder.addCondition("state", "draft");
        conditionSetBuilder.addCondition("isViewer", true);
        conditionSetBuilder.addCondition("isEditor", true);
        Values<String> values4 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values4.getValues()).containsExactly("subject", "due date");

        conditionSetBuilder.addCondition("type", "contract");
        conditionSetBuilder.addCondition("isViewer", true);
        Values<String> values5 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values5.getValues()).containsExactly("title");

        conditionSetBuilder.addCondition("isViewer", true);
        conditionSetBuilder.addCondition("isEditor", true);
        Values<String> values6 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values6.getValues()).containsExactly();
    }

}
