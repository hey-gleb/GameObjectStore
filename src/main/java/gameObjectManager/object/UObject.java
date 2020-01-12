package gameObjectManager.object;

/**
 * Game object interface
 */
public interface UObject {

    /**
     * Function to get object field
     *
     * @param fieldName field name
     * @return value
     */
    Object getValue(IFieldName fieldName);
}
