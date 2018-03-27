package lapd.k.log;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

/**
 * Log4j2 gc free特性:每个写Thread在Threadlocal里保存了 CharBuffer和ByteBuffer
 * 这个例子里测试了缓存后和直接sout的gc次数
 * 测试工具 JRF
 *
 */
public class MyLogger {

    public void info(String msg) {
        info(msg, false);
    }

    public void info(String msg, boolean gcFree) {
        if (gcFree) {
            logDirectly(msg);
        } else {
            log(msg);
        }
    }


//    final Object[] threadLocalState = getThreadLocalState();
//    final CharsetEncoder charsetEncoder = (CharsetEncoder) threadLocalState[0];
//    final CharBuffer charBuffer = (CharBuffer) threadLocalState[1];
//    final ByteBuffer byteBuffer = (ByteBuffer) threadLocalState[2];

    OutputStream writer = System.out;//new ByteArrayOutputStream();
    CharBuffer charBuffer = CharBuffer.allocate(128);
    ByteBuffer byteBuffer = ByteBuffer.allocate(128);

    private void logDirectly(String msg) {
//        String msg = null;


        try {
            charBuffer.clear();
            byteBuffer.clear();
//            CharBuffer charBuffer2 =
            charBuffer.append(msg);
            charBuffer.limit(msg.length());
            charBuffer.flip();

//            System.arraycopy(charBuffer, 0, byteBuffer, 0, msg.length());
//            byteBuffer.get()
//            System.out.println(byteBuffer.array(), 0, msg.length());

            while (charBuffer.hasRemaining()) {
                byteBuffer.put((byte) charBuffer.get());
            }
            byteBuffer.flip();
//                System.out.print((char) buf.get()); // read 1 byte at a time
            writer.write(byteBuffer.array(), 0, msg.length());

//            }


//            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


//    void encodeText(final CharsetEncoder charsetEncoder, final CharBuffer charBuf, final ByteBuffer byteBuf,
//                           final StringBuilder text, final ByteBufferDestination destination)
//            throws CharacterCodingException {
////        charsetEncoder.reset();
////        if (text.length() > charBuf.capacity()) {
////            encodeChunkedText(charsetEncoder, charBuf, byteBuf, text, destination);
////            return;
////        }
////        charBuf.clear();
////        text.getChars(0, text.length(), charBuf.array(), charBuf.arrayOffset());
////        charBuf.limit(text.length());
////        final CoderResult result = charsetEncoder.encode(charBuf, byteBuf, true);
////        writeEncodedText(charsetEncoder, charBuf, byteBuf, destination, result);
//        String msg = null;
//
//
//        try {
//            charBuffer.clear();
////            CharBuffer charBuffer2 =
//                    charBuffer.append(msg);
//            charBuf.limit(msg.length());
//            writer.write(charBuffer.array());
//            writer.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    private void log(String msg) {
        System.out.println(msg);
    }


    static String msg = "msg No:";

    public static void main(String[] args) {
        MyLogger logger = new MyLogger();
        for (int i = 0; i < 100_000_000; i++) {
            logger.info(msg);

        }
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        logger.info("msg No:" + 1, true);
    }
}
