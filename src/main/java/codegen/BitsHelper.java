package codegen;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class BitsHelper {

    private BitsHelper() {
    }

    public static long getUInt32L(byte[] b, int off) {
	return (long) ((b[off] & 0xFF)) + ((b[off + 1] & 0xFF) << 8) + ((b[off + 2] & 0xFF) << 16)
		+ ((b[off + 3]) << 24) & 0xffffffffL;
    }

    public static long getUInt32L(byte[] b) {
	return getUInt32L(b, 0);
    }

    public static long getUInt32B(byte[] b, int off) {
	return (long) ((b[off + 3] & 0xFF)) + ((b[off + 2] & 0xFF) << 8) + ((b[off + 1] & 0xFF) << 16)
		+ ((b[off]) << 24) & 0xffffffffL;
    }

    public static long getUInt32B(byte[] b) {
	return getUInt32B(b, 0);
    }

    /**
     * Reads four bytes at the given index, composing them into a int value
     * according to the current byte order.
     * 
     * @param bytebuffer
     * @param position
     * @return long
     */
    public static long getUInt32(ByteBuffer bb, int position) {
	return ((long) bb.getInt(position) & 0xffffffffL);
    }

    /**
     * Reads the next four bytes at this buffer's current position, composing
     * them into an int value according to the current byte order, and then
     * increments the position by four.
     * 
     * @param bytebuffer
     * @return long
     */
    public static long getUInt32(ByteBuffer bb) {
	return ((long) bb.getInt() & 0xffffffffL);
    }

    public static int getUInt16L(byte[] b, int off) {
	return (int) ((b[off] & 0xFF)) + ((b[off + 1]) << 8) & 0xffff;
    }

    public static int getUInt16L(byte[] b) {
	return getUInt16L(b, 0);
    }

    public static int getUInt16B(byte[] b, int off) {
	return (int) ((b[off + 1] & 0xFF)) + ((b[off]) << 8) & 0xffff;
    }

    public static int getUInt16B(byte[] b) {
	return getUInt16B(b, 0);
    }

    public static int getUInt16(ByteBuffer bb, int position) {
	return ((int) bb.getShort(position) & 0xffff);
    }

    public static int getUInt16(ByteBuffer bb) {
	return ((int) bb.getShort() & 0xffff);
    }

    public static short getUInt8(byte[] b, int off) {
	return (short) (b[off] & 0xFF);
    }

    public static short getUInt8(byte[] b) {
	return getUInt8(b, 0);
    }

    public static int varByteToUIntL(int count, byte[] data, int offset) {
	int val = 0;

	assert count <= 4;
	for (int i = 0; i < count; i++) {
	    val |= (int) (data[offset + i] & 0xFF) << (8 * i);
	}
	return (int) val;
    }

    public static short getUInt8(byte b) {
	return (short) (b & 0xFF);
    }

    public static short getUInt8(ByteBuffer bb, int position) {
	return (short) (bb.get(position) & 0xff);
    }

    public static short getUInt8(ByteBuffer bb) {
	return (short) (bb.get() & 0xff);
    }

    public static byte[] getByteL(int i) {
	return new byte[] { (byte) (i & 0xFF), (byte) ((i >> 8) & 0xFF), (byte) ((i >> 16) & 0xFF),
		(byte) ((i >> 24) & 0xFF) };
    }

    public static byte[] getByteL(short i) {
	return new byte[] { (byte) (i & 0xFF), (byte) ((i >> 8) & 0xFF) };
    }

    public static char[] getCharArray(byte[] b) {
	if (b == null) {
	    return null;
	}
	return getCharArray(b, 0, b.length);
    }

    public static char[] getCharArray(byte[] b, int offset, int length) {
	if (b == null) {
	    return null;
	}
	/*
	 * if (b.length > offset + length) { return null; }
	 */

	char[] c = new char[length];
	for (int i = 0; i < length; i++) {
	    c[i] = (char) b[offset + i];
	}

	return c;
    }

    public static float getFloat32B(byte[] b, int index) {
	int l;
	l = b[index + 3];
	l &= 0xff;
	l |= ((long) b[index + 2] << 8);
	l &= 0xffff;
	l |= ((long) b[index + 1] << 16);
	l &= 0xffffff;
	l |= ((long) b[index + 0] << 24);
	return Float.intBitsToFloat(l);
    }

    /**
     * 把byte数组转化成2进制字符串
     * 
     * @param bArr
     * @return
     */
    public static String getBinaryStrFromByteArr(byte[] bArr) {
	String result = "";
	for (byte b : bArr) {
	    result += getBinaryStrFromByte(b);
	}
	return result;
    }

    /**
     * 把byte转化成2进制字符串
     * 
     * @param b
     * @return
     */
    public static String getBinaryStrFromByte(byte b) {
	String result = "";
	byte a = b;
	;
	for (int i = 0; i < 8; i++) {
	    byte c = a;
	    a = (byte) (a >> 1);// 每移一位如同将10进制数除以2并去掉余数。
	    a = (byte) (a << 1);
	    if (a == c) {
		result = "0" + result;
	    } else {
		result = "1" + result;
	    }
	    a = (byte) (a >> 1);
	}
	return result;
    }

    /**
     * 把byte转化成2进制字符串
     * 
     * @param b
     * @return
     */
    public static String getBinaryStrFromByte2(byte b) {
	String result = "";
	byte a = b;
	;
	for (int i = 0; i < 8; i++) {
	    result = (a % 2) + result;
	    a = (byte) (a >> 1);
	}
	return result;
    }

    /**
     * 把byte转化成2进制字符串
     * 
     * @param b
     * @return
     */
    public static String getBinaryStrFromByte3(byte b) {
	String result = "";
	byte a = b;
	;
	for (int i = 0; i < 8; i++) {
	    result = (a % 2) + result;
	    a = (byte) (a / 2);
	}
	return result;
    }
    
    /** 
     * 把byte转为字符串的bit 
     */  
    public static String byteToBit(byte b) {  
        return ""  
                + (byte) ((b >> 7) & 0x1) + (byte) ((b >> 6) & 0x1)  
                + (byte) ((b >> 5) & 0x1) + (byte) ((b >> 4) & 0x1)  
                + (byte) ((b >> 3) & 0x1) + (byte) ((b >> 2) & 0x1)  
                + (byte) ((b >> 1) & 0x1) + (byte) ((b >> 0) & 0x1);  
    } 
    
    /**
     * bite 转  int
     * @param bit
     * @return
     */
    public static int bitToInt(String bit) {  
        int re, len;  
        if (null == bit) {  
            return 0;  
        }  
        len = bit.length();  
        if (len == 5 || len == 6) {  
            bit = padLeft(bit, 8);
            len = bit.length();
        }  
        if (len == 8) {// 8 bit处理  
            if (bit.charAt(0) == '0') {// 正数  
                re = Integer.parseInt(bit, 2);  
            } else {// 负数  
                re = Integer.parseInt(bit, 2) - 256;  
            }  
        } else {//4 bit处理  
            re = Integer.parseInt(bit, 2);  
        }  
        return  re;  
    }
    
    /**
     * fill in String with certain length
     * @param s
     * @param length
     * @return
     */
    public static String padLeft(String s, int length)
	{
	    byte[] bs = new byte[length];
	    byte[] ss = s.getBytes();
	    Arrays.fill(bs, (byte) (48 & 0xff));
	    System.arraycopy(ss, 0, bs,length - ss.length, ss.length);
	    return new String(bs);
	}


}
