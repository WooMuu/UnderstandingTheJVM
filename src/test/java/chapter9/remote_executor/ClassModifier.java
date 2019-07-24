package chapter9.remote_executor;

/**
 * Created by zjb on 2019/7/22.
 */
public class ClassModifier {
    /**
     * Class文件中常量池的起始偏移量
     */
    private static final int CONSTANT_POOL_COUNT_INDEX = 8;
    /**
     * CONSTANT_utf8_info 常量的tag标志
     */
    private static final int CONSTANT_utf8_info = 1;

    /*
    常量池中11种常量所占的长度，CONSTANT_utf8_info常量除外，因为它不是定长的
     */
    private static final int[] CONSTANT_ITEM_LENGTH = {-1, -1, -1, 5, 5, 9, 9, 3, 3, 5, 5, 5, 5};
    private static final int u1 = 1;
    private static final int u2 = 2;

    private byte[] classbytes;

    public ClassModifier(byte[] classbytes) {
        this.classbytes = classbytes;
    }

    /**
     * 修改常量池中CONSTANT_utf8_info常量的内容
     */
    public byte[] modifyUFT8Constant(String oldStr, String newStr) {
        int cpc = getConstantPoolCount();
        int offset = CONSTANT_POOL_COUNT_INDEX + u2;
        for (int i = 0; i < cpc; i++) {
            int tag = BytesUtils.bytes2Int(classbytes, offset, u1);
            if (tag == CONSTANT_utf8_info) {
                int len = BytesUtils.bytes2Int(classbytes, offset + u1, u2);
                offset += (u1 + u2);
                String str = BytesUtils.bytes2String(classbytes, offset, len);
                if (str.equalsIgnoreCase(oldStr)) {
                    byte[] strBytes = BytesUtils.string2Bytes(newStr);
                    byte[] strLen = BytesUtils.int2Bytes(newStr.length(), u2);
                    classbytes = BytesUtils.bytesReplace(classbytes, offset - u2, u2, strLen);
                    BytesUtils.bytesReplace(classbytes, offset, len, strBytes);
                    return classbytes;
                } else {
                    offset += len;
                }
            } else {
                offset += CONSTANT_ITEM_LENGTH[tag];
            }
        }
        return classbytes;
    }

    /**
     * 获取常量池中常量的数量
     */
    public int getConstantPoolCount() {
        return BytesUtils.bytes2Int(classbytes, CONSTANT_POOL_COUNT_INDEX, u2);
    }


}
