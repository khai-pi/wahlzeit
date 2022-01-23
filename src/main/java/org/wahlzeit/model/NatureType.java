package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class NatureType extends DataObject {

    protected NatureType superType = null;
    protected Set<NatureType> subTypes = new HashSet<NatureType>();
    protected String name; // Attribute to distint different NatureType

    public NatureType(String name) {
        this.name = name;
    }


    @Override
    public String getIdAsString() {
        return null;
    }

    @Override
    public void readFrom(ResultSet rset) throws SQLException {

    }

    @Override
    public void writeOn(ResultSet rset) throws SQLException {

    }

    @Override
    public void writeId(PreparedStatement stmt, int pos) throws SQLException {

    }

    public String getName() {
        return this.name;
    }

    public Nature createInstance() {
        return new Nature(this);
    }

    public NatureType getSuperType() {
        return superType;
    }

    public Iterator<NatureType> getSubTypesIterator() {
        return subTypes.iterator();
    }

    public void setSuperType(NatureType natureType) {
        this.superType = natureType;
    }

    // add subtype to set
    public void addSubType(NatureType natureType) {
        assert (natureType!=null);
        natureType.setSuperType(this);
        this.subTypes.add(natureType);
    }

    // Recursive check if NatureType has this Nature
    public boolean hasInstance(Nature nature) {
        assert (nature != null);

        if (nature.getNatureType() == this) return true;
        for (NatureType type : subTypes) {
            if (type.hasInstance(nature)) return true;
        }

        return false;
    }

    // return true if it has a superType
    public boolean isSubType(NatureType natureType) {
        assert(natureType != null);
        if (this.superType == natureType) return true;
        if (this.superType.isSubType(natureType)) return true;
        return false;
    }
}
