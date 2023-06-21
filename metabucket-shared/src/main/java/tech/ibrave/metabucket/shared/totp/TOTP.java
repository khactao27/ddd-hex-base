package tech.ibrave.metabucket.shared.totp;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.lang.reflect.UndeclaredThrowableException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;

public final class TOTP {
    private TOTP() {
    }

    public static String getOTP(String key) {
        return getOTP(getStep(), key);
    }

    public static String generateSecretKey() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[20];
        random.nextBytes(bytes);
        Base32 base32 = new Base32();
        return base32.encodeToString(bytes);
    }

    public static boolean validate(String key, String otp) {
        return validate(getStep(), key, otp);
    }

    private static boolean validate(long step, String key, String otp) {
        return getOTP(step, key).equals(otp) || getOTP(step - 1L, key).equals(otp);
    }

    private static long getStep() {
        return System.currentTimeMillis() / 30000L;
    }

    private static String getOTP(long step, String key) {
        StringBuilder steps = new StringBuilder(Long.toHexString(step).toUpperCase());
        while (steps.length() < 16) {
            steps.insert(0, "0");
        }

        byte[] bytes = new Base32().decode(key);
        String hexKey = Hex.encodeHexString(bytes);

        byte[] msg = hexStr2Bytes(steps.toString());
        byte[] k = hexStr2Bytes(hexKey);
        byte[] hash = hmacSHA1(k, msg);
        int offset = hash[hash.length - 1] & 15;
        int binary = (hash[offset] & 127) << 24 | (hash[offset + 1] & 255) << 16 | (hash[offset + 2] & 255) << 8 | hash[offset + 3] & 255;
        int otp = binary % 1000000;

        StringBuilder result = new StringBuilder(String.valueOf(otp));
        while (result.length() < 6) {
            result.insert(0, "0");
        }

        return result.toString();
    }

    private static byte[] hexStr2Bytes(String hex) {
        byte[] bArray = (new BigInteger("10" + hex, 16)).toByteArray();
        byte[] ret = new byte[bArray.length - 1];
        System.arraycopy(bArray, 1, ret, 0, ret.length);
        return ret;
    }

    private static byte[] hmacSHA1(byte[] keyBytes, byte[] text) {
        try {
            Mac hmac = Mac.getInstance("HmacSHA1");
            SecretKeySpec macKey = new SecretKeySpec(keyBytes, "RAW");
            hmac.init(macKey);
            return hmac.doFinal(text);
        } catch (GeneralSecurityException var4) {
            throw new UndeclaredThrowableException(var4);
        }
    }
}