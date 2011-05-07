package eu.vitaliy.xaocevent;

/**
 *
 * @author Vitaliy Oliynyk
 */
public class MetaDataContext {
    private Object obj;
    private Class clazz;

    public MetaDataContext(Object obj, Class clazz) {
        this.obj = obj;
        this.clazz = clazz;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MetaDataContext other = (MetaDataContext) obj;
        if (this.obj != other.obj && (this.obj == null || !this.obj.equals(other.obj))) {
            return false;
        }
        if (this.clazz != other.clazz && (this.clazz == null || !this.clazz.equals(other.clazz))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + (this.obj != null ? this.obj.hashCode() : 0);
        hash = 71 * hash + (this.clazz != null ? this.clazz.hashCode() : 0);
        return hash;
    }

    






}
