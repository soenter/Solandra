package lucandra;

import org.apache.cassandra.db.marshal.AbstractType;

public class VIntType extends AbstractType {

    public static final VIntType instance = new VIntType();
    
    @Override
    public String getString(byte[] bytes) {
        return Integer.toString(CassandraUtils.readVInt(bytes));          
    }

    @Override
    public int compare(byte[] o1, byte[] o2) {
        if(null == o1){
            if(null == o2) return 0;
            else return -1;
        }
        if(null == o2) return 1;
        
        if(0 == o1.length){
            if(0 == o2.length) return 0;
            else return -1;
        }
        if(0 == o2.length) return 1;
        
        
        int i1 = CassandraUtils.readVInt(o1);
        int i2 = CassandraUtils.readVInt(o2);
        
        if(i1 == i2) return 0;
        
        return i1 < i2 ?  -1 : 1;
    }

}
