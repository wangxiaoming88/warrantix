package com.warrantix.main.modules.crypto;

import com.scottyab.aescrypt.AESCrypt;
import java.security.GeneralSecurityException;

public class WarrantixCryptor {

    public final int PASSWORD_SELECTED = 0;
    public final int PASSWORD_NOT_SELECTED = 1;

    private String password = "";
    private int passwordStatus = PASSWORD_NOT_SELECTED;

    private static boolean DEBUG = false;
    private static WarrantixCryptor instance = null;
    public static WarrantixCryptor getInstance() {
        if (instance == null) {
            instance = new WarrantixCryptor();
            instance.init();
        }

        return instance;
    }

    public void init() {
        if (WarrantixCryptor.DEBUG == true) {
            AESCrypt.DEBUG_LOG_ENABLED = true;
        }

        password = "";
        passwordStatus = PASSWORD_NOT_SELECTED;
    }

    public void setPassword(String password) {
        this.password = password;

        if ((password == null) || (password.equalsIgnoreCase("") == true))
            this.passwordStatus = PASSWORD_NOT_SELECTED;
        else
            this.passwordStatus = PASSWORD_SELECTED;
    }

    public String encrypt(String message) {

        if (passwordStatus == PASSWORD_NOT_SELECTED) {
            // raise set password exception
            return "";
        }

        try {
            String encryptedMsg = AESCrypt.encrypt(password, message);
            return encryptedMsg;
        }
        catch (GeneralSecurityException e){
            e.printStackTrace();
        }
        return "";
    }

    public String decrypt(String encryptedMsg) {

        if (passwordStatus == PASSWORD_NOT_SELECTED) {
            // raise set password exception
            return "";
        }

        try {
            String messageAfterDecrypt = AESCrypt.decrypt(password, encryptedMsg);
        }
        catch (GeneralSecurityException e){
            e.printStackTrace();
        }

        return "";
    }
}
