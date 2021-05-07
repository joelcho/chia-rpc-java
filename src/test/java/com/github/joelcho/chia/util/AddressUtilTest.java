package com.github.joelcho.chia.util;

import com.github.joelcho.chia.types.primitive.Bytes32;
import junit.framework.TestCase;
import org.junit.Assert;

/**
 * @author Joel
 */
public class AddressUtilTest extends TestCase {
    private final String puzzleHash = "f2fa8ca53807f6bbf96d5ce15d9f2f7ca896c2416b4f1a45dff2bafc1b044087";
    private final String address = "txch17tageffcqlmth7tdtns4m8e00j5fdsjpdd8353wl72a0cxcygzrs4pv96r";

    public void testEncode() {
        byte[] data = HexUtil.decode(puzzleHash);
        String address = AddressUtil.encode(new Bytes32(data), "txch");
        Assert.assertEquals(this.address, address);
    }

    public void testDecode() {
        Bytes32 pHash = AddressUtil.decode(this.address);
        String puzzleHash = pHash.toHexString(false);
        Assert.assertEquals(this.puzzleHash, puzzleHash);
    }
}