package org.wahlzeit.model;

import java.util.HashMap;

public class NatureManager {

    private static final NatureManager instance = new NatureManager();
    private HashMap<Integer, Nature> natures = new HashMap<Integer, Nature>();
    private HashMap<String, NatureType> natureTypes = new HashMap<String, NatureType>();

    public static NatureManager getInstance() {
        return instance;
    }

    public Nature createNature(String typeName) {
        assertIsValidNatureTypeName(typeName);

        NatureType natureType = getNatureType(typeName);
        Nature result = natureType.createInstance();
        natures.put(result.getId(), result);

        return result;
    }

    public NatureType getNatureType(String typeName) {
        NatureType natureType = natureTypes.get(typeName);
        if (natureType == null) {
            natureType = new NatureType(typeName);
            natureTypes.put(typeName, natureType);
        }
        return natureType;
    }

    public void assertIsValidNatureTypeName(String typeName) {
        if (typeName==null || typeName.isEmpty()) {
            throw new IllegalStateException("typeName must not be null nor empty");
        }
    }
}
