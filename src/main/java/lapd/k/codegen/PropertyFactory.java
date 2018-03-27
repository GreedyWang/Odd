package lapd.k.codegen;

import java.io.*;
import java.util.*;

import org.dom4j.*;
import org.dom4j.io.*;


public class PropertyFactory {

    private PropertyFactory () {}

    public static List<Cfg> read(String file) {
        long lasting = System.currentTimeMillis();
        List<Cfg> cfgList = new ArrayList<>();
        try {
            File f = new File(file);
            SAXReader reader = new SAXReader();
            Document doc = reader.read(f);
            Element root = doc.getRootElement();
            Element foo;

            for (Iterator i = root.elementIterator(); i.hasNext(); ) {
                foo = (Element) i.next();
                Cfg cfg = new Cfg(
                        foo.elementText("name"),
                        foo.elementText("value"),
                        foo.elementText("type"),
                        foo.elementText("index"),
                        foo.elementText("length")
                );
                cfgList.add(cfg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println("total time : " + (System.currentTimeMillis() - lasting));
        return cfgList;
    }

    static class Cfg {
        String name;
        String value;
        String type;
        String index;
        String length;

        public Cfg(String name, String value, String type, String index, String length) {
            this.name = name;
            this.value = value;
            this.type = type;
            this.index = index;
            this.length = length;
        }

    }

}