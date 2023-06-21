package tech.ibrave.metabucket.shared.totp;

import java.util.Random;

public final class TOTPData {
    private static final char[] hexArray = "0123456789ABCDEF".toCharArray();
    private static final Random rnd = new Random();
    private final String issuer;
    private final String user;
    private final byte[] secret;

    public TOTPData(String issuer, String user, byte[] secret) {
        this.issuer = issuer;
        this.user = user;
        this.secret = secret;
    }

    public TOTPData(byte[] secret) {
        this(null, null, secret);
    }

    public String getIssuer() {
        return this.issuer;
    }

    public String getUser() {
        return this.user;
    }

    public byte[] getSecret() {
        return this.secret;
    }

    public String getSecretAsHex() {
        char[] hexChars = new char[this.secret.length * 2];

        for(int j = 0; j < this.secret.length; ++j) {
            int v = this.secret[j] & 255;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 15];
        }

        return new String(hexChars);
    }

    public String getUrl() {
        return String.format("otpauth://totp/%s:%s?secret=%s&issuer=%s",
                this.issuer, this.user, new String(secret), this.issuer);
    }

    public static TOTPData create() {
        return new TOTPData(createSecret());
    }

    public static TOTPData create(String issuer, String user) {
        return new TOTPData(issuer, user, createSecret());
    }

    public static byte[] createSecret() {
        byte[] secret = new byte[20];
        rnd.nextBytes(secret);
        return secret;
    }
}