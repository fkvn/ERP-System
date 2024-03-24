package com.tedkvn.erp.util;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

public class PhoneUtil {

    public static Boolean isPhoneValid(String phone, String region) {

        PhoneNumberUtil numberUtil = PhoneNumberUtil.getInstance();

        Phonenumber.PhoneNumber phoneNumber;

        try {
            phoneNumber = numberUtil.parse(phone, region.toUpperCase());
            return numberUtil.isValidNumber(phoneNumber);

        } catch (NumberParseException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
        return false;
    }

}
