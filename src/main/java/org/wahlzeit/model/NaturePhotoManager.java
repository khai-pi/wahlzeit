package org.wahlzeit.model;

import java.util.HashMap;
import java.util.Map;

public class NaturePhotoManager extends PhotoManager{

    /**
     *
     */
    protected static final NaturePhotoManager instance = new NaturePhotoManager();

    /**
     * In-memory cache for photos
     */
    protected Map<PhotoId, NaturePhoto> photoCache = new HashMap<PhotoId, NaturePhoto>();


}

