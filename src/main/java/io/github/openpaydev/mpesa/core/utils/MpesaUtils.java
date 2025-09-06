package io.github.openpaydev.mpesa.core.utils;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

public class MpesaUtils {

    private static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    private static final ZoneId NAIROBI_ZONE_ID = ZoneId.of("Africa/Nairobi");

    public static String getTimestamp() {
        return ZonedDateTime.now(NAIROBI_ZONE_ID).format(TIMESTAMP_FORMATTER);
    }

    public static String encodePassword(String shortCode, String passKey, String timestamp) {
        String strToEncode = shortCode + passKey + timestamp;
        return Base64.getEncoder().encodeToString(strToEncode.getBytes());
    }

    public static String formatPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be null or empty.");
        }
        if (phoneNumber.startsWith("+")) phoneNumber = phoneNumber.substring(1);
        if (phoneNumber.startsWith("07")) return "254" + phoneNumber.substring(1);
        if (phoneNumber.startsWith("7")) return "254" + phoneNumber;
        if (phoneNumber.startsWith("254")) return phoneNumber;
        throw new IllegalArgumentException("Invalid phone number format: " + phoneNumber);
    }
}
