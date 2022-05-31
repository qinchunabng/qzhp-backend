package com.qzhp;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;

/**
 * 加密工具
 *
 * @author qcb
 * @date 2022/05/27 13:01.
 */
public class EncryptTools {

    public static void main(String[] args) {
        String password = encrypt("qzhp@2022!");
        System.out.println(password);
    }

    public static String encrypt(String plaintext){
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setPassword(System.getProperty("jasypt.encryptor.password"));
        encryptor.setConfig(config);
        return encryptor.encrypt(plaintext);
    }
}
