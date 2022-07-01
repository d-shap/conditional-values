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
import ru.d_shap.conditionalvalues.data.IsDividedByPredicate;

/**
 * Lookup example implementation.
 *
 * @author Dmitry Shapovalov
 */
public final class DividersTest {

    /**
     * Test class constructor.
     */
    public DividersTest() {
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

        conditionalValuesBuilder.setPredicate(new IsDividedByPredicate());
        valueSetBuilder.addCondition("div", 2);
        valueSetBuilder.addValue("Is divided by 2");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("div", 3);
        valueSetBuilder.addValue("Is divided by 3");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("div", 5);
        valueSetBuilder.addValue("Is divided by 5");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("div", 7);
        valueSetBuilder.addValue("Is divided by 7");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("div", 11);
        valueSetBuilder.addValue("Is divided by 11");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("div", 13);
        valueSetBuilder.addValue("Is divided by 13");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("div", 17);
        valueSetBuilder.addValue("Is divided by 17");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("div", 19);
        valueSetBuilder.addValue("Is divided by 19");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());

        ConditionalValues<String> conditionalValues = conditionalValuesBuilder.build();

        conditionSetBuilder.addCondition("div", 5);
        Values<String> values1 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values1.getValues()).containsExactly("Is divided by 5");

        conditionSetBuilder.addCondition("div", 6);
        Values<String> values2 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values2.getValues()).containsExactly("Is divided by 2", "Is divided by 3");

        conditionSetBuilder.addCondition("div", 77);
        Values<String> values3 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values3.getValues()).containsExactly("Is divided by 7", "Is divided by 11");

        conditionSetBuilder.addCondition("div", 30);
        Values<String> values4 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values4.getValues()).containsExactly("Is divided by 2", "Is divided by 3", "Is divided by 5");

        conditionSetBuilder.addCondition("div", 2235987);
        Values<String> values5 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values5.getValues()).containsExactly("Is divided by 3", "Is divided by 13");

        conditionSetBuilder.addCondition("div", 1021020);
        Values<String> values6 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values6.getValues()).containsExactly("Is divided by 2", "Is divided by 3", "Is divided by 5", "Is divided by 7", "Is divided by 11", "Is divided by 13", "Is divided by 17");

        conditionSetBuilder.addCondition("div", 510510);
        Values<String> values7 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values7.getValues()).containsExactly("Is divided by 2", "Is divided by 3", "Is divided by 5", "Is divided by 7", "Is divided by 11", "Is divided by 13", "Is divided by 17");
    }

}
