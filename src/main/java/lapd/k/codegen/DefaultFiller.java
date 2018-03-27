package lapd.k.codegen;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * default fill, contains type String, int
 */
public class DefaultFiller {

    private Map<String, Processor> types = new HashMap<>();

    protected void register() {
        types.put("String", (f,b,p,obj) -> {
            try {
                //fake implement,todo: Byte to bit, String's length
                f.set(obj, String.valueOf(BitsHelper.getCharArray(b,p/8,5)));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        types.put("int", (f,b,p,obj) -> {
            try {
                f.setInt(obj, BitsHelper.getUInt8(b, p) == 255 ? null : (int) BitsHelper.getUInt8(b, p));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });

    }

    int position = 0;
    public void fillin(Object obj, byte[] bytes, List<PropertyFactory.Cfg> cfgList) {
        for (PropertyFactory.Cfg cfg : cfgList) {
            Field f = null;
            try {
                f = obj.getClass().getDeclaredField(cfg.name);
                f.setAccessible(true);
                //int position = Integer.parseInt(cfg.index) - 1;
                types.get(cfg.type).process(f, bytes, position,obj);
                position += Integer.parseInt(cfg.length);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }

}

interface Processor {
    void process(Field f, byte[] bytes, int position, Object obj);
}
