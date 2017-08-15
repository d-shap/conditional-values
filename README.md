Conditional values
==================
Classes simplify conditional logic, getting rid of **if**-statements in the code.

The main purpose is to find the best result from many predefined conditions.

For example, we should display object attributes based on some conditions.

The conditions could be:
* object type with many differen values (type1, type2, type3, etc.)
* object current state with many differen values (1, 2, 3, etc.)
* user role (initiator, performer, administrator, etc.)
* some other values

And based on this conditions there are different visible attributes (attribute1, attribute2, attribute3, etc.)

First we create `ValueSet` for each distinct condition:

```
ValueSetBuilder<String> valueSetBuilder = ConditionalValues.createValueSetBuilder();

valueSetBuilder.addCondition("type", "type1");
valueSetBuilder.addCondition("state", 1);
valueSetBuilder.addCondition("role", "initiator");
valueSetBuilder.addValue("attribute1", "attribute2");
ValueSet<String> type1Initiator1ValueSet = valueSetBuilder.buildAndClear();

valueSetBuilder.addCondition("type", "type1");
valueSetBuilder.addCondition("state", 2);
valueSetBuilder.addCondition("state", 3);
valueSetBuilder.addCondition("role", "initiator");
valueSetBuilder.addValue("attribute2", "attribute3");
ValueSet<String> type1Initiator23ValueSet = valueSetBuilder.buildAndClear();

valueSetBuilder.addCondition("type", "type1");
valueSetBuilder.addCondition("state", 2);
valueSetBuilder.addCondition("state", 3);
valueSetBuilder.addCondition("role", "performer");
valueSetBuilder.addValue("attribute1", "attribute3");
ValueSet<String> type1Performer23ValueSet = valueSetBuilder.buildAndClear();
```

Then we store this conditions in a single `ConditionalValues` object:

```
List<ValueSet<String>> valueSets = new ArrayList<ValueSet<String>>();
valueSets.add(type1Initiator1ValueSet);
valueSets.add(type1Initiator23ValueSet);
valueSets.add(type1Performer23ValueSet);
...
ConditionalValues<String> conditionalValues = ConditionalValues.createConditionalValues(valueSets);
```
 
This `ConditionalValues` object could be created statically.

In runtime now we can define visible attributes based on current condition. To perform this we create `ConditionSet` object:

```
ConditionSetBuilder conditionSetBuilder = ConditionalValues.createConditionSetBuilder();
conditionSetBuilder.addCondition("type", "type1");
conditionSetBuilder.addCondition("state", 2);
conditionSetBuilder.addCondition("role", "performer");
ConditionSet conditionSet = conditionSetBuilder.build();
```
 
And get matching `ValueSet` objects from `ConditionalValues` object:

```
Values<String> values = conditionalValues.getValues(conditionSet);
```
 
Now we can use `Values` to get all visible attributes, or to test if `Values` object contains specified attribute.

The best condition is defined by matching conditions count.

For example, there are predefined conditions:

**Value Set 1**
* type: **type1**
* isInitiator: **true**

**Value Set 2**
* type: *type1*
* isPerformer: *true*

**Value Set 3**
* type: *type1*
* state: *1*
* isInitiator: *true*

Then if we have runtime conditions (type = **type1**, state = **1**, isInitiator = **true**), then the best matching value set is **Value Set 3** (the only one matching value set).

If we have runtime conditions (type = **type1**, isInitiator = **true**, isPerformer = **true**), then the best matching value sets are **Value Set 1** and **Value Set 2** (**Values** object contains values from both **ValueSet** objects).

If we have runtime conditions (type = **type1**, state = **1**, isInitiator = **true**, isPerformer = **true**), then the best matching value sets are **Value Set 2** and **Value Set 3** (**Value Set 1** also matches, but **Value Set 3** is more specific then **Value Set 1**).

If we have runtime conditions (type = **type1**, isInitiator = **true**), then the best matching value set is **Value Set 1** (the only one matching value set).

If we have runtime conditions (isInitiator = **true**, isPerformer = **true**), then there are no matching value sets (**Values** object is empty).
