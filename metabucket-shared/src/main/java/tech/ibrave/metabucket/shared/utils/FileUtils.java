package tech.ibrave.metabucket.shared.utils;

import tech.ibrave.metabucket.shared.constant.DataUnit;

/**
 * Author: hungnm
 * Date: 22/06/2023
 */
public class FileUtils {
    public static long convertToBytes(long number, DataUnit unit) {
        switch (unit) {
            case MB -> {
                return number * 100000L;
            }
            case GB -> {
                return number * 100000L * 1024L;
            }
            case TB -> {
                return number * 100000L * 1024L * 1024L;
            }
            default -> {
                return number;
            }
        }
    }
}
