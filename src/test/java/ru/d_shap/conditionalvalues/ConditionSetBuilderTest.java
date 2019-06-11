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
package ru.d_shap.conditionalvalues;

import org.junit.Test;

import ru.d_shap.assertions.Assertions;
import ru.d_shap.assertions.Raw;

/**
 * Tests for {@link ConditionSetBuilder}.
 *
 * @author Dmitry Shapovalov
 */
public final class ConditionSetBuilderTest {

    /**
     * Test class constructor.
     */
    public ConditionSetBuilderTest() {
        super();
    }

    /**
     * {@link ConditionSetBuilder} class test.
     */
    @Test
    public void newInstanceTest() {
        ConditionSetBuilder conditionSetBuilder1 = ConditionSetBuilder.newInstance();
        ConditionSetBuilder conditionSetBuilder2 = ConditionSetBuilder.newInstance();

        Assertions.assertThat(conditionSetBuilder1).isNotNull();
        Assertions.assertThat(conditionSetBuilder2).isNotNull();
        Assertions.assertThat(conditionSetBuilder1).isNotSameAs(conditionSetBuilder2);
        Assertions.assertThat(conditionSetBuilder2).isNotSameAs(conditionSetBuilder1);
    }

    /**
     * {@link ConditionSetBuilder} class test.
     */
    @Test
    public void addConditionStringTest() {
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        conditionSetBuilder.addCondition("cond1", "val1");
        conditionSetBuilder.addCondition("cond2", "val2");
        conditionSetBuilder.addCondition("cond3", "val3").addCondition("cond4", "val4");
        ConditionSet conditionSet1 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet1.nameIterator()).containsExactly("cond1", "cond2", "cond3", "cond4");
        Assertions.assertThat(conditionSet1.getValue("cond1")).isEqualTo("val1");
        Assertions.assertThat(conditionSet1.getValue("cond2")).isEqualTo("val2");
        Assertions.assertThat(conditionSet1.getValue("cond3")).isEqualTo("val3");
        Assertions.assertThat(conditionSet1.getValue("cond4")).isEqualTo("val4");

        conditionSetBuilder.addCondition(null, "val");
        Assertions.assertThat(conditionSetBuilder, "_conditions", Raw.mapAssertion()).hasSize(0);
        ConditionSet conditionSet2 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet2.nameIterator()).containsExactly();

        conditionSetBuilder.addCondition("cond", null);
        Assertions.assertThat(conditionSetBuilder, "_conditions", Raw.mapAssertion()).hasSize(0);
        ConditionSet conditionSet3 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet3.nameIterator()).containsExactly();

        conditionSetBuilder.addCondition(null, null);
        Assertions.assertThat(conditionSetBuilder, "_conditions", Raw.mapAssertion()).hasSize(0);
        ConditionSet conditionSet4 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet4.nameIterator()).containsExactly();
    }

    /**
     * {@link ConditionSetBuilder} class test.
     */
    @Test
    public void addConditionBooleanTest() {
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        conditionSetBuilder.addCondition("cond1", true);
        conditionSetBuilder.addCondition("cond2", false);
        conditionSetBuilder.addCondition("cond3", true).addCondition("cond4", true);
        ConditionSet conditionSet1 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet1.nameIterator()).containsExactly("cond1", "cond2", "cond3", "cond4");
        Assertions.assertThat(conditionSet1.getValue("cond1")).isEqualTo("true");
        Assertions.assertThat(conditionSet1.getValue("cond2")).isEqualTo("false");
        Assertions.assertThat(conditionSet1.getValue("cond3")).isEqualTo("true");
        Assertions.assertThat(conditionSet1.getValue("cond4")).isEqualTo("true");

        conditionSetBuilder.addCondition(null, true);
        Assertions.assertThat(conditionSetBuilder, "_conditions", Raw.mapAssertion()).hasSize(0);
        ConditionSet conditionSet2 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet2.nameIterator()).containsExactly();
    }

    /**
     * {@link ConditionSetBuilder} class test.
     */
    @Test
    public void addConditionCharTest() {
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        conditionSetBuilder.addCondition("cond1", '1');
        conditionSetBuilder.addCondition("cond2", '2');
        conditionSetBuilder.addCondition("cond3", 'a').addCondition("cond4", 'b');
        ConditionSet conditionSet1 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet1.nameIterator()).containsExactly("cond1", "cond2", "cond3", "cond4");
        Assertions.assertThat(conditionSet1.getValue("cond1")).isEqualTo("1");
        Assertions.assertThat(conditionSet1.getValue("cond2")).isEqualTo("2");
        Assertions.assertThat(conditionSet1.getValue("cond3")).isEqualTo("a");
        Assertions.assertThat(conditionSet1.getValue("cond4")).isEqualTo("b");

        conditionSetBuilder.addCondition(null, '1');
        Assertions.assertThat(conditionSetBuilder, "_conditions", Raw.mapAssertion()).hasSize(0);
        ConditionSet conditionSet2 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet2.nameIterator()).containsExactly();
    }

    /**
     * {@link ConditionSetBuilder} class test.
     */
    @Test
    public void addConditionIntTest() {
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        conditionSetBuilder.addCondition("cond1", 1);
        conditionSetBuilder.addCondition("cond2", 2);
        conditionSetBuilder.addCondition("cond3", 3).addCondition("cond4", 4);
        ConditionSet conditionSet1 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet1.nameIterator()).containsExactly("cond1", "cond2", "cond3", "cond4");
        Assertions.assertThat(conditionSet1.getValue("cond1")).isEqualTo("1");
        Assertions.assertThat(conditionSet1.getValue("cond2")).isEqualTo("2");
        Assertions.assertThat(conditionSet1.getValue("cond3")).isEqualTo("3");
        Assertions.assertThat(conditionSet1.getValue("cond4")).isEqualTo("4");

        conditionSetBuilder.addCondition(null, 0);
        Assertions.assertThat(conditionSetBuilder, "_conditions", Raw.mapAssertion()).hasSize(0);
        ConditionSet conditionSet2 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet2.nameIterator()).containsExactly();
    }

    /**
     * {@link ConditionSetBuilder} class test.
     */
    @Test
    public void addConditionLongTest() {
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        conditionSetBuilder.addCondition("cond1", 1L);
        conditionSetBuilder.addCondition("cond2", 2L);
        conditionSetBuilder.addCondition("cond3", 3L).addCondition("cond4", 4L);
        ConditionSet conditionSet1 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet1.nameIterator()).containsExactly("cond1", "cond2", "cond3", "cond4");
        Assertions.assertThat(conditionSet1.getValue("cond1")).isEqualTo("1");
        Assertions.assertThat(conditionSet1.getValue("cond2")).isEqualTo("2");
        Assertions.assertThat(conditionSet1.getValue("cond3")).isEqualTo("3");
        Assertions.assertThat(conditionSet1.getValue("cond4")).isEqualTo("4");

        conditionSetBuilder.addCondition(null, 0L);
        Assertions.assertThat(conditionSetBuilder, "_conditions", Raw.mapAssertion()).hasSize(0);
        ConditionSet conditionSet2 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet2.nameIterator()).containsExactly();
    }

    /**
     * {@link ConditionSetBuilder} class test.
     */
    @Test
    public void addConditionFloatTest() {
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        conditionSetBuilder.addCondition("cond1", 1.0f);
        conditionSetBuilder.addCondition("cond2", 2.0f);
        conditionSetBuilder.addCondition("cond3", 3.0f).addCondition("cond4", 4.0f);
        ConditionSet conditionSet1 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet1.nameIterator()).containsExactly("cond1", "cond2", "cond3", "cond4");
        Assertions.assertThat(conditionSet1.getValue("cond1")).isEqualTo("1.0");
        Assertions.assertThat(conditionSet1.getValue("cond2")).isEqualTo("2.0");
        Assertions.assertThat(conditionSet1.getValue("cond3")).isEqualTo("3.0");
        Assertions.assertThat(conditionSet1.getValue("cond4")).isEqualTo("4.0");

        conditionSetBuilder.addCondition(null, 0.0f);
        Assertions.assertThat(conditionSetBuilder, "_conditions", Raw.mapAssertion()).hasSize(0);
        ConditionSet conditionSet2 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet2.nameIterator()).containsExactly();
    }

    /**
     * {@link ConditionSetBuilder} class test.
     */
    @Test
    public void addConditionDoubleTest() {
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        conditionSetBuilder.addCondition("cond1", 1.0);
        conditionSetBuilder.addCondition("cond2", 2.0);
        conditionSetBuilder.addCondition("cond3", 3.0).addCondition("cond4", 4.0);
        ConditionSet conditionSet1 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet1.nameIterator()).containsExactly("cond1", "cond2", "cond3", "cond4");
        Assertions.assertThat(conditionSet1.getValue("cond1")).isEqualTo("1.0");
        Assertions.assertThat(conditionSet1.getValue("cond2")).isEqualTo("2.0");
        Assertions.assertThat(conditionSet1.getValue("cond3")).isEqualTo("3.0");
        Assertions.assertThat(conditionSet1.getValue("cond4")).isEqualTo("4.0");

        conditionSetBuilder.addCondition(null, 0.0);
        Assertions.assertThat(conditionSetBuilder, "_conditions", Raw.mapAssertion()).hasSize(0);
        ConditionSet conditionSet2 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet2.nameIterator()).containsExactly();
    }

    /**
     * {@link ConditionSetBuilder} class test.
     */
    @Test
    public void addConditionObjectTest() {
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        conditionSetBuilder.addCondition("cond1", new StringBuilder().append("val1"));
        conditionSetBuilder.addCondition("cond2", new StringBuilder().append("val2"));
        conditionSetBuilder.addCondition("cond3", new StringBuilder().append("val3")).addCondition("cond4", new StringBuilder().append("val4"));
        ConditionSet conditionSet1 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet1.nameIterator()).containsExactly("cond1", "cond2", "cond3", "cond4");
        Assertions.assertThat(conditionSet1.getValue("cond1")).isEqualTo("val1");
        Assertions.assertThat(conditionSet1.getValue("cond2")).isEqualTo("val2");
        Assertions.assertThat(conditionSet1.getValue("cond3")).isEqualTo("val3");
        Assertions.assertThat(conditionSet1.getValue("cond4")).isEqualTo("val4");

        conditionSetBuilder.addCondition(null, new StringBuilder().append("val"));
        Assertions.assertThat(conditionSetBuilder, "_conditions", Raw.mapAssertion()).hasSize(0);
        ConditionSet conditionSet2 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet2.nameIterator()).containsExactly();

        conditionSetBuilder.addCondition("cond", (Object) null);
        Assertions.assertThat(conditionSetBuilder, "_conditions", Raw.mapAssertion()).hasSize(0);
        ConditionSet conditionSet3 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet3.nameIterator()).containsExactly();

        conditionSetBuilder.addCondition(null, (Object) null);
        Assertions.assertThat(conditionSetBuilder, "_conditions", Raw.mapAssertion()).hasSize(0);
        ConditionSet conditionSet4 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet4.nameIterator()).containsExactly();
    }

    /**
     * {@link ConditionSetBuilder} class test.
     */
    @Test
    public void addConditionsTest() {
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        conditionSetBuilder.addCondition("cond1", "val1");
        conditionSetBuilder.addCondition("cond2", "val2");
        conditionSetBuilder.addCondition("cond3", "val3");
        conditionSetBuilder.addCondition("cond4", "val4");
        ConditionSet addTemplate = conditionSetBuilder.build();

        conditionSetBuilder = conditionSetBuilder.addConditions(addTemplate);
        ConditionSet conditionSet1 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet1.nameIterator()).containsExactly("cond1", "cond2", "cond3", "cond4");
        Assertions.assertThat(conditionSet1.getValue("cond1")).isEqualTo("val1");
        Assertions.assertThat(conditionSet1.getValue("cond2")).isEqualTo("val2");
        Assertions.assertThat(conditionSet1.getValue("cond3")).isEqualTo("val3");
        Assertions.assertThat(conditionSet1.getValue("cond4")).isEqualTo("val4");

        conditionSetBuilder = conditionSetBuilder.addConditions(addTemplate);
        conditionSetBuilder.addCondition("cond2", "val6");
        conditionSetBuilder.addCondition("cond5", "val5");
        ConditionSet conditionSet2 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet2.nameIterator()).containsExactly("cond1", "cond2", "cond3", "cond4", "cond5");
        Assertions.assertThat(conditionSet2.getValue("cond1")).isEqualTo("val1");
        Assertions.assertThat(conditionSet2.getValue("cond2")).isEqualTo("val6");
        Assertions.assertThat(conditionSet2.getValue("cond3")).isEqualTo("val3");
        Assertions.assertThat(conditionSet2.getValue("cond4")).isEqualTo("val4");
        Assertions.assertThat(conditionSet2.getValue("cond5")).isEqualTo("val5");

        conditionSetBuilder.addCondition("cond2", "val6");
        conditionSetBuilder.addCondition("cond5", "val5");
        conditionSetBuilder.addConditions(addTemplate);
        ConditionSet conditionSet3 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet3.nameIterator()).containsExactly("cond1", "cond2", "cond3", "cond4", "cond5");
        Assertions.assertThat(conditionSet3.getValue("cond1")).isEqualTo("val1");
        Assertions.assertThat(conditionSet3.getValue("cond2")).isEqualTo("val2");
        Assertions.assertThat(conditionSet3.getValue("cond3")).isEqualTo("val3");
        Assertions.assertThat(conditionSet3.getValue("cond4")).isEqualTo("val4");
        Assertions.assertThat(conditionSet3.getValue("cond5")).isEqualTo("val5");

        conditionSetBuilder = conditionSetBuilder.addConditions(null);
        ConditionSet conditionSet4 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet4.nameIterator()).containsExactly();

        conditionSetBuilder = conditionSetBuilder.addConditions(null);
        conditionSetBuilder.addCondition("cond2", "val6");
        conditionSetBuilder.addCondition("cond5", "val5");
        ConditionSet conditionSet5 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet5.nameIterator()).containsExactly("cond2", "cond5");
        Assertions.assertThat(conditionSet5.getValue("cond2")).isEqualTo("val6");
        Assertions.assertThat(conditionSet5.getValue("cond5")).isEqualTo("val5");

        conditionSetBuilder.addCondition("cond2", "val6");
        conditionSetBuilder.addCondition("cond5", "val5");
        conditionSetBuilder.addConditions(null);
        ConditionSet conditionSet6 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet6.nameIterator()).containsExactly("cond2", "cond5");
        Assertions.assertThat(conditionSet6.getValue("cond2")).isEqualTo("val6");
        Assertions.assertThat(conditionSet6.getValue("cond5")).isEqualTo("val5");
    }

    /**
     * {@link ConditionSetBuilder} class test.
     */
    @Test
    public void removeConditionStringTest() {
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        conditionSetBuilder.addCondition("cond1", "val1");
        conditionSetBuilder.addCondition("cond2", "val2");
        conditionSetBuilder.addCondition("cond3", "val3").addCondition("cond4", "val4");
        ConditionSet addTemplate = conditionSetBuilder.build();

        conditionSetBuilder.addConditions(addTemplate);
        conditionSetBuilder.removeCondition("cond1", "val1");
        conditionSetBuilder.removeCondition("cond1", "val3");
        conditionSetBuilder.removeCondition("cond2", "val3").removeCondition("cond3", "val3");
        ConditionSet conditionSet1 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet1.nameIterator()).containsExactly("cond2", "cond4");
        Assertions.assertThat(conditionSet1.getValue("cond1")).isNull();
        Assertions.assertThat(conditionSet1.getValue("cond2")).isEqualTo("val2");
        Assertions.assertThat(conditionSet1.getValue("cond3")).isNull();
        Assertions.assertThat(conditionSet1.getValue("cond4")).isEqualTo("val4");

        conditionSetBuilder.addConditions(addTemplate);
        conditionSetBuilder.removeCondition(null, "val1");
        ConditionSet conditionSet2 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet2.nameIterator()).containsExactly("cond1", "cond2", "cond3", "cond4");
        Assertions.assertThat(conditionSet2.getValue("cond1")).isEqualTo("val1");
        Assertions.assertThat(conditionSet2.getValue("cond2")).isEqualTo("val2");
        Assertions.assertThat(conditionSet2.getValue("cond3")).isEqualTo("val3");
        Assertions.assertThat(conditionSet2.getValue("cond4")).isEqualTo("val4");

        conditionSetBuilder.addConditions(addTemplate);
        conditionSetBuilder.removeCondition("cond1", null);
        ConditionSet conditionSet3 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet3.nameIterator()).containsExactly("cond1", "cond2", "cond3", "cond4");
        Assertions.assertThat(conditionSet3.getValue("cond1")).isEqualTo("val1");
        Assertions.assertThat(conditionSet3.getValue("cond2")).isEqualTo("val2");
        Assertions.assertThat(conditionSet3.getValue("cond3")).isEqualTo("val3");
        Assertions.assertThat(conditionSet3.getValue("cond4")).isEqualTo("val4");

        conditionSetBuilder.addConditions(addTemplate);
        conditionSetBuilder.removeCondition(null, null);
        ConditionSet conditionSet4 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet4.nameIterator()).containsExactly("cond1", "cond2", "cond3", "cond4");
        Assertions.assertThat(conditionSet4.getValue("cond1")).isEqualTo("val1");
        Assertions.assertThat(conditionSet4.getValue("cond2")).isEqualTo("val2");
        Assertions.assertThat(conditionSet4.getValue("cond3")).isEqualTo("val3");
        Assertions.assertThat(conditionSet4.getValue("cond4")).isEqualTo("val4");
    }

    /**
     * {@link ConditionSetBuilder} class test.
     */
    @Test
    public void removeConditionBooleanTest() {
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        conditionSetBuilder.addCondition("cond1", true);
        conditionSetBuilder.addCondition("cond2", false);
        conditionSetBuilder.addCondition("cond3", true).addCondition("cond4", true);
        ConditionSet addTemplate = conditionSetBuilder.build();

        conditionSetBuilder.addConditions(addTemplate);
        conditionSetBuilder.removeCondition("cond1", true);
        conditionSetBuilder.removeCondition("cond1", false);
        conditionSetBuilder.removeCondition("cond2", true).removeCondition("cond3", true);
        ConditionSet conditionSet1 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet1.nameIterator()).containsExactly("cond2", "cond4");
        Assertions.assertThat(conditionSet1.getValue("cond1")).isNull();
        Assertions.assertThat(conditionSet1.getValue("cond2")).isEqualTo("false");
        Assertions.assertThat(conditionSet1.getValue("cond3")).isNull();
        Assertions.assertThat(conditionSet1.getValue("cond4")).isEqualTo("true");

        conditionSetBuilder.addConditions(addTemplate);
        conditionSetBuilder.removeCondition(null, true);
        ConditionSet conditionSet2 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet2.nameIterator()).containsExactly("cond1", "cond2", "cond3", "cond4");
        Assertions.assertThat(conditionSet2.getValue("cond1")).isEqualTo("true");
        Assertions.assertThat(conditionSet2.getValue("cond2")).isEqualTo("false");
        Assertions.assertThat(conditionSet2.getValue("cond3")).isEqualTo("true");
        Assertions.assertThat(conditionSet2.getValue("cond4")).isEqualTo("true");
    }

    /**
     * {@link ConditionSetBuilder} class test.
     */
    @Test
    public void removeConditionCharTest() {
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        conditionSetBuilder.addCondition("cond1", '1');
        conditionSetBuilder.addCondition("cond2", '2');
        conditionSetBuilder.addCondition("cond3", 'a').addCondition("cond4", 'b');
        ConditionSet addTemplate = conditionSetBuilder.build();

        conditionSetBuilder.addConditions(addTemplate);
        conditionSetBuilder.removeCondition("cond1", '1');
        conditionSetBuilder.removeCondition("cond1", 'a');
        conditionSetBuilder.removeCondition("cond2", 'a').removeCondition("cond3", 'a');
        ConditionSet conditionSet1 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet1.nameIterator()).containsExactly("cond2", "cond4");
        Assertions.assertThat(conditionSet1.getValue("cond1")).isNull();
        Assertions.assertThat(conditionSet1.getValue("cond2")).isEqualTo("2");
        Assertions.assertThat(conditionSet1.getValue("cond3")).isNull();
        Assertions.assertThat(conditionSet1.getValue("cond4")).isEqualTo("b");

        conditionSetBuilder.addConditions(addTemplate);
        conditionSetBuilder.removeCondition(null, '1');
        ConditionSet conditionSet2 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet2.nameIterator()).containsExactly("cond1", "cond2", "cond3", "cond4");
        Assertions.assertThat(conditionSet2.getValue("cond1")).isEqualTo("1");
        Assertions.assertThat(conditionSet2.getValue("cond2")).isEqualTo("2");
        Assertions.assertThat(conditionSet2.getValue("cond3")).isEqualTo("a");
        Assertions.assertThat(conditionSet2.getValue("cond4")).isEqualTo("b");
    }

    /**
     * {@link ConditionSetBuilder} class test.
     */
    @Test
    public void removeConditionIntTest() {
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        conditionSetBuilder.addCondition("cond1", 1);
        conditionSetBuilder.addCondition("cond2", 2);
        conditionSetBuilder.addCondition("cond3", 3).addCondition("cond4", 4);
        ConditionSet addTemplate = conditionSetBuilder.build();

        conditionSetBuilder.addConditions(addTemplate);
        conditionSetBuilder.removeCondition("cond1", 1);
        conditionSetBuilder.removeCondition("cond1", 3);
        conditionSetBuilder.removeCondition("cond2", 3).removeCondition("cond3", 3);
        ConditionSet conditionSet1 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet1.nameIterator()).containsExactly("cond2", "cond4");
        Assertions.assertThat(conditionSet1.getValue("cond1")).isNull();
        Assertions.assertThat(conditionSet1.getValue("cond2")).isEqualTo("2");
        Assertions.assertThat(conditionSet1.getValue("cond3")).isNull();
        Assertions.assertThat(conditionSet1.getValue("cond4")).isEqualTo("4");

        conditionSetBuilder.addConditions(addTemplate);
        conditionSetBuilder.removeCondition(null, 1);
        ConditionSet conditionSet2 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet2.nameIterator()).containsExactly("cond1", "cond2", "cond3", "cond4");
        Assertions.assertThat(conditionSet2.getValue("cond1")).isEqualTo("1");
        Assertions.assertThat(conditionSet2.getValue("cond2")).isEqualTo("2");
        Assertions.assertThat(conditionSet2.getValue("cond3")).isEqualTo("3");
        Assertions.assertThat(conditionSet2.getValue("cond4")).isEqualTo("4");
    }

    /**
     * {@link ConditionSetBuilder} class test.
     */
    @Test
    public void removeConditionLongTest() {
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        conditionSetBuilder.addCondition("cond1", 1L);
        conditionSetBuilder.addCondition("cond2", 2L);
        conditionSetBuilder.addCondition("cond3", 3L).addCondition("cond4", 4L);
        ConditionSet addTemplate = conditionSetBuilder.build();

        conditionSetBuilder.addConditions(addTemplate);
        conditionSetBuilder.removeCondition("cond1", 1L);
        conditionSetBuilder.removeCondition("cond1", 3L);
        conditionSetBuilder.removeCondition("cond2", 3L).removeCondition("cond3", 3L);
        ConditionSet conditionSet1 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet1.nameIterator()).containsExactly("cond2", "cond4");
        Assertions.assertThat(conditionSet1.getValue("cond1")).isNull();
        Assertions.assertThat(conditionSet1.getValue("cond2")).isEqualTo("2");
        Assertions.assertThat(conditionSet1.getValue("cond3")).isNull();
        Assertions.assertThat(conditionSet1.getValue("cond4")).isEqualTo("4");

        conditionSetBuilder.addConditions(addTemplate);
        conditionSetBuilder.removeCondition(null, 1L);
        ConditionSet conditionSet2 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet2.nameIterator()).containsExactly("cond1", "cond2", "cond3", "cond4");
        Assertions.assertThat(conditionSet2.getValue("cond1")).isEqualTo("1");
        Assertions.assertThat(conditionSet2.getValue("cond2")).isEqualTo("2");
        Assertions.assertThat(conditionSet2.getValue("cond3")).isEqualTo("3");
        Assertions.assertThat(conditionSet2.getValue("cond4")).isEqualTo("4");
    }

    /**
     * {@link ConditionSetBuilder} class test.
     */
    @Test
    public void removeConditionFloatTest() {
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        conditionSetBuilder.addCondition("cond1", 1.0f);
        conditionSetBuilder.addCondition("cond2", 2.0f);
        conditionSetBuilder.addCondition("cond3", 3.0f).addCondition("cond4", 4.0f);
        ConditionSet addTemplate = conditionSetBuilder.build();

        conditionSetBuilder.addConditions(addTemplate);
        conditionSetBuilder.removeCondition("cond1", 1.0f);
        conditionSetBuilder.removeCondition("cond1", 3.0f);
        conditionSetBuilder.removeCondition("cond2", 3.0f).removeCondition("cond3", 3.0f);
        ConditionSet conditionSet1 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet1.nameIterator()).containsExactly("cond2", "cond4");
        Assertions.assertThat(conditionSet1.getValue("cond1")).isNull();
        Assertions.assertThat(conditionSet1.getValue("cond2")).isEqualTo("2.0");
        Assertions.assertThat(conditionSet1.getValue("cond3")).isNull();
        Assertions.assertThat(conditionSet1.getValue("cond4")).isEqualTo("4.0");

        conditionSetBuilder.addConditions(addTemplate);
        conditionSetBuilder.removeCondition(null, 1.0f);
        ConditionSet conditionSet2 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet2.nameIterator()).containsExactly("cond1", "cond2", "cond3", "cond4");
        Assertions.assertThat(conditionSet2.getValue("cond1")).isEqualTo("1.0");
        Assertions.assertThat(conditionSet2.getValue("cond2")).isEqualTo("2.0");
        Assertions.assertThat(conditionSet2.getValue("cond3")).isEqualTo("3.0");
        Assertions.assertThat(conditionSet2.getValue("cond4")).isEqualTo("4.0");
    }

    /**
     * {@link ConditionSetBuilder} class test.
     */
    @Test
    public void removeConditionDoubleTest() {
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        conditionSetBuilder.addCondition("cond1", 1.0);
        conditionSetBuilder.addCondition("cond2", 2.0);
        conditionSetBuilder.addCondition("cond3", 3.0).addCondition("cond4", 4.0);
        ConditionSet addTemplate = conditionSetBuilder.build();

        conditionSetBuilder.addConditions(addTemplate);
        conditionSetBuilder.removeCondition("cond1", 1.0);
        conditionSetBuilder.removeCondition("cond1", 3.0);
        conditionSetBuilder.removeCondition("cond2", 3.0).removeCondition("cond3", 3.0);
        ConditionSet conditionSet1 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet1.nameIterator()).containsExactly("cond2", "cond4");
        Assertions.assertThat(conditionSet1.getValue("cond1")).isNull();
        Assertions.assertThat(conditionSet1.getValue("cond2")).isEqualTo("2.0");
        Assertions.assertThat(conditionSet1.getValue("cond3")).isNull();
        Assertions.assertThat(conditionSet1.getValue("cond4")).isEqualTo("4.0");

        conditionSetBuilder.addConditions(addTemplate);
        conditionSetBuilder.removeCondition(null, 1.0);
        ConditionSet conditionSet2 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet2.nameIterator()).containsExactly("cond1", "cond2", "cond3", "cond4");
        Assertions.assertThat(conditionSet2.getValue("cond1")).isEqualTo("1.0");
        Assertions.assertThat(conditionSet2.getValue("cond2")).isEqualTo("2.0");
        Assertions.assertThat(conditionSet2.getValue("cond3")).isEqualTo("3.0");
        Assertions.assertThat(conditionSet2.getValue("cond4")).isEqualTo("4.0");
    }

    /**
     * {@link ConditionSetBuilder} class test.
     */
    @Test
    public void removeConditionObjectTest() {
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        conditionSetBuilder.addCondition("cond1", new StringBuilder().append("val1"));
        conditionSetBuilder.addCondition("cond2", new StringBuilder().append("val2"));
        conditionSetBuilder.addCondition("cond3", new StringBuilder().append("val3")).addCondition("cond4", new StringBuilder().append("val4"));
        ConditionSet addTemplate = conditionSetBuilder.build();

        conditionSetBuilder.addConditions(addTemplate);
        conditionSetBuilder.removeCondition("cond1", new StringBuilder().append("val1"));
        conditionSetBuilder.removeCondition("cond1", new StringBuilder().append("val3"));
        conditionSetBuilder.removeCondition("cond2", new StringBuilder().append("val3")).removeCondition("cond3", new StringBuilder().append("val3"));
        ConditionSet conditionSet1 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet1.nameIterator()).containsExactly("cond2", "cond4");
        Assertions.assertThat(conditionSet1.getValue("cond1")).isNull();
        Assertions.assertThat(conditionSet1.getValue("cond2")).isEqualTo("val2");
        Assertions.assertThat(conditionSet1.getValue("cond3")).isNull();
        Assertions.assertThat(conditionSet1.getValue("cond4")).isEqualTo("val4");

        conditionSetBuilder.addConditions(addTemplate);
        conditionSetBuilder.removeCondition(null, new StringBuilder().append("val1"));
        ConditionSet conditionSet2 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet2.nameIterator()).containsExactly("cond1", "cond2", "cond3", "cond4");
        Assertions.assertThat(conditionSet2.getValue("cond1")).isEqualTo("val1");
        Assertions.assertThat(conditionSet2.getValue("cond2")).isEqualTo("val2");
        Assertions.assertThat(conditionSet2.getValue("cond3")).isEqualTo("val3");
        Assertions.assertThat(conditionSet2.getValue("cond4")).isEqualTo("val4");

        conditionSetBuilder.addConditions(addTemplate);
        conditionSetBuilder.removeCondition("cond1", (Object) null);
        ConditionSet conditionSet3 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet3.nameIterator()).containsExactly("cond1", "cond2", "cond3", "cond4");
        Assertions.assertThat(conditionSet3.getValue("cond1")).isEqualTo("val1");
        Assertions.assertThat(conditionSet3.getValue("cond2")).isEqualTo("val2");
        Assertions.assertThat(conditionSet3.getValue("cond3")).isEqualTo("val3");
        Assertions.assertThat(conditionSet3.getValue("cond4")).isEqualTo("val4");

        conditionSetBuilder.addConditions(addTemplate);
        conditionSetBuilder.removeCondition(null, (Object) null);
        ConditionSet conditionSet4 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet4.nameIterator()).containsExactly("cond1", "cond2", "cond3", "cond4");
        Assertions.assertThat(conditionSet4.getValue("cond1")).isEqualTo("val1");
        Assertions.assertThat(conditionSet4.getValue("cond2")).isEqualTo("val2");
        Assertions.assertThat(conditionSet4.getValue("cond3")).isEqualTo("val3");
        Assertions.assertThat(conditionSet4.getValue("cond4")).isEqualTo("val4");
    }

    /**
     * {@link ConditionSetBuilder} class test.
     */
    @Test
    public void removeConditionNameTest() {
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        conditionSetBuilder.addCondition("cond1", "val1");
        conditionSetBuilder.addCondition("cond2", "val2");
        conditionSetBuilder.addCondition("cond3", "val3");
        conditionSetBuilder.addCondition("cond4", "val4");
        conditionSetBuilder.addCondition("cond5", "val5");
        conditionSetBuilder.addCondition("cond6", "val6");
        ConditionSet addTemplate = conditionSetBuilder.build();

        conditionSetBuilder = conditionSetBuilder.addConditions(addTemplate);
        conditionSetBuilder.removeCondition("cond1");
        conditionSetBuilder.removeCondition("cond3");
        conditionSetBuilder.removeCondition("cond5").removeCondition("cond6");
        ConditionSet conditionSet1 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet1.nameIterator()).containsExactly("cond2", "cond4");
        Assertions.assertThat(conditionSet1.getValue("cond2")).isEqualTo("val2");
        Assertions.assertThat(conditionSet1.getValue("cond4")).isEqualTo("val4");

        conditionSetBuilder = conditionSetBuilder.addConditions(addTemplate);
        conditionSetBuilder.removeCondition(null);
        ConditionSet conditionSet2 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet2.nameIterator()).containsExactly("cond1", "cond2", "cond3", "cond4", "cond5", "cond6");
        Assertions.assertThat(conditionSet2.getValue("cond1")).isEqualTo("val1");
        Assertions.assertThat(conditionSet2.getValue("cond2")).isEqualTo("val2");
        Assertions.assertThat(conditionSet2.getValue("cond3")).isEqualTo("val3");
        Assertions.assertThat(conditionSet2.getValue("cond4")).isEqualTo("val4");
        Assertions.assertThat(conditionSet2.getValue("cond5")).isEqualTo("val5");
        Assertions.assertThat(conditionSet2.getValue("cond6")).isEqualTo("val6");
    }

    /**
     * {@link ConditionSetBuilder} class test.
     */
    @Test
    public void removeConditionNamesTest() {
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        conditionSetBuilder.addCondition("cond1", "val1");
        conditionSetBuilder.addCondition("cond2", "val2");
        conditionSetBuilder.addCondition("cond3", "val3");
        conditionSetBuilder.addCondition("cond4", "val4");
        conditionSetBuilder.addCondition("cond5", "val5");
        conditionSetBuilder.addCondition("cond6", "val6");
        ConditionSet addTemplate = conditionSetBuilder.build();

        conditionSetBuilder.addCondition("cond1", "val1");
        conditionSetBuilder.addCondition("cond4", "val2");
        conditionSetBuilder.addCondition("cond5", "val6");
        ConditionSet removeTemplate1 = conditionSetBuilder.build();

        conditionSetBuilder.addCondition("cond1", "val1");
        conditionSetBuilder.addCondition("cond2", "val2");
        conditionSetBuilder.addCondition("cond3", "val4");
        conditionSetBuilder.addCondition("cond4", "val3");
        conditionSetBuilder.addCondition("cond6", "val6");
        ConditionSet removeTemplate2 = conditionSetBuilder.build();

        conditionSetBuilder = conditionSetBuilder.addConditions(addTemplate);
        conditionSetBuilder = conditionSetBuilder.removeConditionNames(removeTemplate1);
        ConditionSet conditionSet1 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet1.nameIterator()).containsExactly("cond2", "cond3", "cond6");
        Assertions.assertThat(conditionSet1.getValue("cond2")).isEqualTo("val2");
        Assertions.assertThat(conditionSet1.getValue("cond3")).isEqualTo("val3");
        Assertions.assertThat(conditionSet1.getValue("cond6")).isEqualTo("val6");

        conditionSetBuilder = conditionSetBuilder.addConditions(addTemplate);
        conditionSetBuilder = conditionSetBuilder.removeConditionNames(removeTemplate2);
        ConditionSet conditionSet2 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet2.nameIterator()).containsExactly("cond5");
        Assertions.assertThat(conditionSet2.getValue("cond5")).isEqualTo("val5");

        conditionSetBuilder = conditionSetBuilder.addConditions(addTemplate);
        conditionSetBuilder = conditionSetBuilder.removeConditionNames(addTemplate);
        ConditionSet conditionSet3 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet3.nameIterator()).containsExactly();

        conditionSetBuilder = conditionSetBuilder.addConditions(addTemplate);
        conditionSetBuilder = conditionSetBuilder.removeConditionNames(null);
        ConditionSet conditionSet4 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet4.nameIterator()).containsExactly("cond1", "cond2", "cond3", "cond4", "cond5", "cond6");
        Assertions.assertThat(conditionSet4.getValue("cond1")).isEqualTo("val1");
        Assertions.assertThat(conditionSet4.getValue("cond2")).isEqualTo("val2");
        Assertions.assertThat(conditionSet4.getValue("cond3")).isEqualTo("val3");
        Assertions.assertThat(conditionSet4.getValue("cond4")).isEqualTo("val4");
        Assertions.assertThat(conditionSet4.getValue("cond5")).isEqualTo("val5");
        Assertions.assertThat(conditionSet4.getValue("cond6")).isEqualTo("val6");
    }

    /**
     * {@link ConditionSetBuilder} class test.
     */
    @Test
    public void removeConditionValuesTest() {
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        conditionSetBuilder.addCondition("cond1", "val1");
        conditionSetBuilder.addCondition("cond2", "val2");
        conditionSetBuilder.addCondition("cond3", "val3");
        conditionSetBuilder.addCondition("cond4", "val4");
        conditionSetBuilder.addCondition("cond5", "val5");
        conditionSetBuilder.addCondition("cond6", "val6");
        ConditionSet addTemplate = conditionSetBuilder.build();

        conditionSetBuilder.addCondition("cond1", "val1");
        conditionSetBuilder.addCondition("cond4", "val2");
        conditionSetBuilder.addCondition("cond5", "val6");
        ConditionSet removeTemplate1 = conditionSetBuilder.build();

        conditionSetBuilder.addCondition("cond1", "val1");
        conditionSetBuilder.addCondition("cond2", "val2");
        conditionSetBuilder.addCondition("cond3", "val4");
        conditionSetBuilder.addCondition("cond4", "val3");
        conditionSetBuilder.addCondition("cond6", "val6");
        ConditionSet removeTemplate2 = conditionSetBuilder.build();

        conditionSetBuilder = conditionSetBuilder.addConditions(addTemplate);
        conditionSetBuilder = conditionSetBuilder.removeConditionValues(removeTemplate1);
        ConditionSet conditionSet1 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet1.nameIterator()).containsExactly("cond2", "cond3", "cond4", "cond5", "cond6");
        Assertions.assertThat(conditionSet1.getValue("cond2")).isEqualTo("val2");
        Assertions.assertThat(conditionSet1.getValue("cond3")).isEqualTo("val3");
        Assertions.assertThat(conditionSet1.getValue("cond4")).isEqualTo("val4");
        Assertions.assertThat(conditionSet1.getValue("cond5")).isEqualTo("val5");
        Assertions.assertThat(conditionSet1.getValue("cond6")).isEqualTo("val6");

        conditionSetBuilder = conditionSetBuilder.addConditions(addTemplate);
        conditionSetBuilder = conditionSetBuilder.removeConditionValues(removeTemplate2);
        ConditionSet conditionSet2 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet2.nameIterator()).containsExactly("cond3", "cond4", "cond5");
        Assertions.assertThat(conditionSet2.getValue("cond3")).isEqualTo("val3");
        Assertions.assertThat(conditionSet2.getValue("cond4")).isEqualTo("val4");
        Assertions.assertThat(conditionSet2.getValue("cond5")).isEqualTo("val5");

        conditionSetBuilder = conditionSetBuilder.addConditions(addTemplate);
        conditionSetBuilder = conditionSetBuilder.removeConditionValues(addTemplate);
        ConditionSet conditionSet3 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet3.nameIterator()).containsExactly();

        conditionSetBuilder = conditionSetBuilder.addConditions(addTemplate);
        conditionSetBuilder = conditionSetBuilder.removeConditionValues(null);
        ConditionSet conditionSet4 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet4.nameIterator()).containsExactly("cond1", "cond2", "cond3", "cond4", "cond5", "cond6");
        Assertions.assertThat(conditionSet4.getValue("cond1")).isEqualTo("val1");
        Assertions.assertThat(conditionSet4.getValue("cond2")).isEqualTo("val2");
        Assertions.assertThat(conditionSet4.getValue("cond3")).isEqualTo("val3");
        Assertions.assertThat(conditionSet4.getValue("cond4")).isEqualTo("val4");
        Assertions.assertThat(conditionSet4.getValue("cond5")).isEqualTo("val5");
        Assertions.assertThat(conditionSet4.getValue("cond6")).isEqualTo("val6");
    }

    /**
     * {@link ConditionSetBuilder} class test.
     */
    @Test
    public void clearTest() {
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        conditionSetBuilder.addCondition("cond1", "val1").addCondition("cond2", "val2");
        conditionSetBuilder.addCondition("cond3", "val3").addCondition("cond4", "val4");
        conditionSetBuilder.addCondition("cond5", "val5").addCondition("cond6", "val6");
        ConditionSet conditionSet = conditionSetBuilder.clear().build();
        Assertions.assertThat(conditionSet.nameIterator()).containsExactly();
    }

    /**
     * {@link ConditionSetBuilder} class test.
     */
    @Test
    public void buildTest() {
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        conditionSetBuilder.addCondition("cond1", "val1");
        ConditionSet conditionSet1 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet1.nameIterator()).containsExactly("cond1");
        Assertions.assertThat(conditionSet1.getValue("cond1")).isEqualTo("val1");
        Assertions.assertThat(conditionSet1.getValue("cond2")).isNull();
        Assertions.assertThat(conditionSet1.getValue("cond3")).isNull();
        Assertions.assertThat(conditionSet1.getValue("cond4")).isNull();

        conditionSetBuilder.addCondition("cond2", "val2");
        ConditionSet conditionSet2 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet2.nameIterator()).containsExactly("cond2");
        Assertions.assertThat(conditionSet2.getValue("cond1")).isNull();
        Assertions.assertThat(conditionSet2.getValue("cond2")).isEqualTo("val2");
        Assertions.assertThat(conditionSet2.getValue("cond3")).isNull();
        Assertions.assertThat(conditionSet2.getValue("cond4")).isNull();

        conditionSetBuilder.addCondition("cond3", "val3");
        ConditionSet conditionSet3 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet3.nameIterator()).containsExactly("cond3");
        Assertions.assertThat(conditionSet3.getValue("cond1")).isNull();
        Assertions.assertThat(conditionSet3.getValue("cond2")).isNull();
        Assertions.assertThat(conditionSet3.getValue("cond3")).isEqualTo("val3");
        Assertions.assertThat(conditionSet3.getValue("cond4")).isNull();

        ConditionSet conditionSet4 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet4.nameIterator()).containsExactly();
    }

    /**
     * {@link ConditionSetBuilder} class test.
     */
    @Test
    public void buildClearTest() {
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        conditionSetBuilder.addCondition("cond1", "val1");
        ConditionSet conditionSet1 = conditionSetBuilder.build(true);
        Assertions.assertThat(conditionSet1.nameIterator()).containsExactly("cond1");
        Assertions.assertThat(conditionSet1.getValue("cond1")).isEqualTo("val1");
        Assertions.assertThat(conditionSet1.getValue("cond2")).isNull();
        Assertions.assertThat(conditionSet1.getValue("cond3")).isNull();
        Assertions.assertThat(conditionSet1.getValue("cond4")).isNull();

        conditionSetBuilder.addCondition("cond2", "val2");
        ConditionSet conditionSet2 = conditionSetBuilder.build(true);
        Assertions.assertThat(conditionSet2.nameIterator()).containsExactly("cond2");
        Assertions.assertThat(conditionSet2.getValue("cond1")).isNull();
        Assertions.assertThat(conditionSet2.getValue("cond2")).isEqualTo("val2");
        Assertions.assertThat(conditionSet2.getValue("cond3")).isNull();
        Assertions.assertThat(conditionSet2.getValue("cond4")).isNull();

        conditionSetBuilder.addCondition("cond1", "val1");
        ConditionSet conditionSet3 = conditionSetBuilder.build(false);
        Assertions.assertThat(conditionSet3.nameIterator()).containsExactly("cond1");
        Assertions.assertThat(conditionSet3.getValue("cond1")).isEqualTo("val1");
        Assertions.assertThat(conditionSet3.getValue("cond2")).isNull();
        Assertions.assertThat(conditionSet3.getValue("cond3")).isNull();
        Assertions.assertThat(conditionSet3.getValue("cond4")).isNull();

        conditionSetBuilder.addCondition("cond2", "val2");
        ConditionSet conditionSet4 = conditionSetBuilder.build(false);
        Assertions.assertThat(conditionSet4.nameIterator()).containsExactly("cond1", "cond2");
        Assertions.assertThat(conditionSet4.getValue("cond1")).isEqualTo("val1");
        Assertions.assertThat(conditionSet4.getValue("cond2")).isEqualTo("val2");
        Assertions.assertThat(conditionSet4.getValue("cond3")).isNull();
        Assertions.assertThat(conditionSet4.getValue("cond4")).isNull();

        conditionSetBuilder.addCondition("cond3", "val3");
        ConditionSet conditionSet5 = conditionSetBuilder.build(false);
        Assertions.assertThat(conditionSet5.nameIterator()).containsExactly("cond1", "cond2", "cond3");
        Assertions.assertThat(conditionSet5.getValue("cond1")).isEqualTo("val1");
        Assertions.assertThat(conditionSet5.getValue("cond2")).isEqualTo("val2");
        Assertions.assertThat(conditionSet5.getValue("cond3")).isEqualTo("val3");
        Assertions.assertThat(conditionSet5.getValue("cond4")).isNull();

        ConditionSet conditionSet6 = conditionSetBuilder.build(true);
        Assertions.assertThat(conditionSet6.nameIterator()).containsExactly("cond1", "cond2", "cond3");
        Assertions.assertThat(conditionSet6.getValue("cond1")).isEqualTo("val1");
        Assertions.assertThat(conditionSet6.getValue("cond2")).isEqualTo("val2");
        Assertions.assertThat(conditionSet6.getValue("cond3")).isEqualTo("val3");
        Assertions.assertThat(conditionSet6.getValue("cond4")).isNull();

        ConditionSet conditionSet7 = conditionSetBuilder.build(false);
        Assertions.assertThat(conditionSet7.nameIterator()).containsExactly();
    }

}
