/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Security;

/**
 *
 * @author vivi-
 */
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.util.SimpleByteSource;

public class SHA1 {

    public static String generateHash(String password) {
        DefaultHashService hashService = new DefaultHashService();
        hashService.setHashIterations(500000); // 500000
        hashService.setHashAlgorithmName(Sha256Hash.ALGORITHM_NAME);

        // Same salt as in shiro.ini, but NOT base64-encoded!!
        hashService.setPrivateSalt(new SimpleByteSource("myprivatesalt"));
        hashService.setGeneratePublicSalt(true);

        DefaultPasswordService passwordService = new DefaultPasswordService();
        passwordService.setHashService(hashService);
        String encryptedPassword = passwordService.encryptPassword(password);

        return encryptedPassword;

    }

}
