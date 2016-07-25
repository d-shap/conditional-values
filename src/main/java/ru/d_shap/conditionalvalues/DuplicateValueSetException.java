// //////////////////////////////
// This code is authored
// Dmitry Shapovalov
// //////////////////////////////
package ru.d_shap.conditionalvalues;

/**
 * Exception is thrown when {@link ru.d_shap.conditionalvalues.ValueSet}, added to {@link ru.d_shap.conditionalvalues.ConditionSet} is not unique.
 *
 * @author Dmitry Shapovalov
 */
public final class DuplicateValueSetException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Creates new object.
     *
     * @param valueSet {@link ru.d_shap.conditionalvalues.ValueSet}, that is not unique.
     */
    public DuplicateValueSetException(final ValueSet valueSet) {
        super("Duplicate value set: " + valueSet);
    }

}
