package codegen;

import java.util.List;

public class Test {

    public static void test(Object obj) throws ClassNotFoundException {
        DefaultFiller p = new DefaultFiller();
        Integer i = 1;
        String speed = "speeddd";

        byte[] a = byteMerger(new byte[]{i.byteValue()}, speed.getBytes());
//        a = byteMerger(a,new byte[]{i.byteValue()});
//        a = byteMerger(a,new byte[]{5,6,7});
        String file = CodeGenTools.class.getResource("/codegen.xml").getFile();
        List<PropertyFactory.Cfg> cfgList = PropertyFactory.read(file);

        // VehicleStateInfo v = new VehicleStateInfo();
//            Object obj = Class.forName("");
        p.register();
        p.fillin(obj, a, cfgList);

        System.out.println(obj);


    }


    static byte[] byteMerger(byte[] byte_1, byte[] byte_2) {
        byte[] byte_3 = new byte[byte_1.length + byte_2.length];
        System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);
        System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);
        return byte_3;
    }
}
