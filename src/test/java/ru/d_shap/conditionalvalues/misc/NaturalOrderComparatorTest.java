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

import java.util.Collections;
import java.util.List;

import org.junit.Test;

import ru.d_shap.assertions.Assertions;
import ru.d_shap.assertions.util.DataHelper;

/**
 * Tests for {@link NaturalOrderComparator}.
 *
 * @author Dmitry Shapovalov
 */
public final class NaturalOrderComparatorTest {

    /**
     * Test class constructor.
     */
    public NaturalOrderComparatorTest() {
        super();
    }

    /**
     * {@link NaturalOrderComparator} class test.
     */
    @Test
    public void compareTest() {
        NaturalOrderComparator<String> comparator1 = new NaturalOrderComparator<>();
        NaturalOrderComparator<String> comparator2 = new NaturalOrderComparator<>(true);
        NaturalOrderComparator<String> comparator3 = new NaturalOrderComparator<>(false);

        Assertions.assertThat(comparator1.compare(null, null)).isEqualTo(0);
        Assertions.assertThat(comparator2.compare(null, null)).isEqualTo(0);
        Assertions.assertThat(comparator3.compare(null, null)).isEqualTo(0);

        Assertions.assertThat(comparator1.compare(null, "a")).isEqualTo(1);
        Assertions.assertThat(comparator2.compare(null, "a")).isEqualTo(-1);
        Assertions.assertThat(comparator3.compare(null, "a")).isEqualTo(1);

        Assertions.assertThat(comparator1.compare("a", null)).isEqualTo(-1);
        Assertions.assertThat(comparator2.compare("a", null)).isEqualTo(1);
        Assertions.assertThat(comparator3.compare("a", null)).isEqualTo(-1);

        Assertions.assertThat(comparator1.compare("a", "b")).isLessThan(0);
        Assertions.assertThat(comparator2.compare("a", "b")).isLessThan(0);
        Assertions.assertThat(comparator3.compare("a", "b")).isLessThan(0);

        Assertions.assertThat(comparator1.compare("b", "a")).isGreaterThan(0);
        Assertions.assertThat(comparator2.compare("b", "a")).isGreaterThan(0);
        Assertions.assertThat(comparator3.compare("b", "a")).isGreaterThan(0);
    }

    /**
     * {@link NaturalOrderComparator} class test.
     */
    @Test
    public void nullsFirstNaturalOrderTest() {
        List<String> values1 = DataHelper.createArrayList("a", "b", "c", "d");
        Collections.sort(values1, new NaturalOrderComparator<String>(true));
        Assertions.assertThat(values1).containsExactlyInOrder("a", "b", "c", "d");

        List<String> values2 = DataHelper.createArrayList("d", "c", "b", "a");
        Collections.sort(values2, new NaturalOrderComparator<String>(true));
        Assertions.assertThat(values2).containsExactlyInOrder("a", "b", "c", "d");

        List<String> values3 = DataHelper.createArrayList("b", "d", "a", "c");
        Collections.sort(values3, new NaturalOrderComparator<String>(true));
        Assertions.assertThat(values3).containsExactlyInOrder("a", "b", "c", "d");

        List<String> values4 = DataHelper.createArrayList("a", "b", "b", "a");
        Collections.sort(values4, new NaturalOrderComparator<String>(true));
        Assertions.assertThat(values4).containsExactlyInOrder("a", "a", "b", "b");

        List<String> values5 = DataHelper.createArrayList(null, "c", "b", "a", "a", null, "d", "d", "c", null);
        Collections.sort(values5, new NaturalOrderComparator<String>(true));
        Assertions.assertThat(values5).containsExactlyInOrder(null, null, null, "a", "a", "b", "c", "c", "d", "d");
    }

    /**
     * {@link NaturalOrderComparator} class test.
     */
    @Test
    public void nullsLastNaturalOrderTest() {
        List<String> values11 = DataHelper.createArrayList("a", "b", "c", "d");
        Collections.sort(values11, new NaturalOrderComparator<String>());
        Assertions.assertThat(values11).containsExactlyInOrder("a", "b", "c", "d");

        List<String> values12 = DataHelper.createArrayList("a", "b", "c", "d");
        Collections.sort(values12, new NaturalOrderComparator<String>(false));
        Assertions.assertThat(values12).containsExactlyInOrder("a", "b", "c", "d");

        List<String> values21 = DataHelper.createArrayList("d", "c", "b", "a");
        Collections.sort(values21, new NaturalOrderComparator<String>());
        Assertions.assertThat(values21).containsExactlyInOrder("a", "b", "c", "d");

        List<String> values22 = DataHelper.createArrayList("d", "c", "b", "a");
        Collections.sort(values22, new NaturalOrderComparator<String>(false));
        Assertions.assertThat(values22).containsExactlyInOrder("a", "b", "c", "d");

        List<String> values31 = DataHelper.createArrayList("b", "d", "a", "c");
        Collections.sort(values31, new NaturalOrderComparator<String>());
        Assertions.assertThat(values31).containsExactlyInOrder("a", "b", "c", "d");

        List<String> values32 = DataHelper.createArrayList("b", "d", "a", "c");
        Collections.sort(values32, new NaturalOrderComparator<String>(false));
        Assertions.assertThat(values32).containsExactlyInOrder("a", "b", "c", "d");

        List<String> values41 = DataHelper.createArrayList("a", "b", "b", "a");
        Collections.sort(values41, new NaturalOrderComparator<String>());
        Assertions.assertThat(values41).containsExactlyInOrder("a", "a", "b", "b");

        List<String> values42 = DataHelper.createArrayList("a", "b", "b", "a");
        Collections.sort(values42, new NaturalOrderComparator<String>(false));
        Assertions.assertThat(values42).containsExactlyInOrder("a", "a", "b", "b");

        List<String> values51 = DataHelper.createArrayList(null, "c", "b", "a", "a", null, "d", "d", "c", null);
        Collections.sort(values51, new NaturalOrderComparator<String>());
        Assertions.assertThat(values51).containsExactlyInOrder("a", "a", "b", "c", "c", "d", "d", null, null, null);

        List<String> values52 = DataHelper.createArrayList(null, "c", "b", "a", "a", null, "d", "d", "c", null);
        Collections.sort(values52, new NaturalOrderComparator<String>(false));
        Assertions.assertThat(values52).containsExactlyInOrder("a", "a", "b", "c", "c", "d", "d", null, null, null);
    }

    /**
     * {@link NaturalOrderComparator} class test.
     */
    @Test
    public void nullsFirstReverseOrderTest() {
        List<String> values1 = DataHelper.createArrayList("a", "b", "c", "d");
        Collections.sort(values1, Collections.reverseOrder(new NaturalOrderComparator<String>(true)));
        Assertions.assertThat(values1).containsExactlyInOrder("d", "c", "b", "a");

        List<String> values2 = DataHelper.createArrayList("d", "c", "b", "a");
        Collections.sort(values2, Collections.reverseOrder(new NaturalOrderComparator<String>(true)));
        Assertions.assertThat(values2).containsExactlyInOrder("d", "c", "b", "a");

        List<String> values3 = DataHelper.createArrayList("b", "d", "a", "c");
        Collections.sort(values3, Collections.reverseOrder(new NaturalOrderComparator<String>(true)));
        Assertions.assertThat(values3).containsExactlyInOrder("d", "c", "b", "a");

        List<String> values4 = DataHelper.createArrayList("a", "b", "b", "a");
        Collections.sort(values4, Collections.reverseOrder(new NaturalOrderComparator<String>(true)));
        Assertions.assertThat(values4).containsExactlyInOrder("b", "b", "a", "a");

        List<String> values5 = DataHelper.createArrayList(null, "c", "b", "a", "a", null, "d", "d", "c", null);
        Collections.sort(values5, Collections.reverseOrder(new NaturalOrderComparator<String>(true)));
        Assertions.assertThat(values5).containsExactlyInOrder("d", "d", "c", "c", "b", "a", "a", null, null, null);
    }

    /**
     * {@link NaturalOrderComparator} class test.
     */
    @Test
    public void nullsLastReverseOrderTest() {
        List<String> values11 = DataHelper.createArrayList("a", "b", "c", "d");
        Collections.sort(values11, Collections.reverseOrder(new NaturalOrderComparator<String>()));
        Assertions.assertThat(values11).containsExactlyInOrder("d", "c", "b", "a");

        List<String> values12 = DataHelper.createArrayList("a", "b", "c", "d");
        Collections.sort(values12, Collections.reverseOrder(new NaturalOrderComparator<String>(false)));
        Assertions.assertThat(values12).containsExactlyInOrder("d", "c", "b", "a");

        List<String> values21 = DataHelper.createArrayList("d", "c", "b", "a");
        Collections.sort(values21, Collections.reverseOrder(new NaturalOrderComparator<String>()));
        Assertions.assertThat(values21).containsExactlyInOrder("d", "c", "b", "a");

        List<String> values22 = DataHelper.createArrayList("d", "c", "b", "a");
        Collections.sort(values22, Collections.reverseOrder(new NaturalOrderComparator<String>(false)));
        Assertions.assertThat(values22).containsExactlyInOrder("d", "c", "b", "a");

        List<String> values31 = DataHelper.createArrayList("b", "d", "a", "c");
        Collections.sort(values31, Collections.reverseOrder(new NaturalOrderComparator<String>()));
        Assertions.assertThat(values31).containsExactlyInOrder("d", "c", "b", "a");

        List<String> values32 = DataHelper.createArrayList("b", "d", "a", "c");
        Collections.sort(values32, Collections.reverseOrder(new NaturalOrderComparator<String>(false)));
        Assertions.assertThat(values32).containsExactlyInOrder("d", "c", "b", "a");

        List<String> values41 = DataHelper.createArrayList("a", "b", "b", "a");
        Collections.sort(values41, Collections.reverseOrder(new NaturalOrderComparator<String>()));
        Assertions.assertThat(values41).containsExactlyInOrder("b", "b", "a", "a");

        List<String> values42 = DataHelper.createArrayList("a", "b", "b", "a");
        Collections.sort(values42, Collections.reverseOrder(new NaturalOrderComparator<String>(false)));
        Assertions.assertThat(values42).containsExactlyInOrder("b", "b", "a", "a");

        List<String> values51 = DataHelper.createArrayList(null, "c", "b", "a", "a", null, "d", "d", "c", null);
        Collections.sort(values51, Collections.reverseOrder(new NaturalOrderComparator<String>()));
        Assertions.assertThat(values51).containsExactlyInOrder(null, null, null, "d", "d", "c", "c", "b", "a", "a");

        List<String> values52 = DataHelper.createArrayList(null, "c", "b", "a", "a", null, "d", "d", "c", null);
        Collections.sort(values52, Collections.reverseOrder(new NaturalOrderComparator<String>(false)));
        Assertions.assertThat(values52).containsExactlyInOrder(null, null, null, "d", "d", "c", "c", "b", "a", "a");
    }

}
