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

public class SHA1 {

    private MessageDigest md;
    private byte[] buffer, digest;
    private String hash = "";
   
// Encripta la contraseña asignada 
    public String getHash(String message) throws NoSuchAlgorithmException {
        buffer = message.getBytes();
        md = MessageDigest.getInstance("SHA1");
        md.update(buffer);
        digest = md.digest();

        for (byte aux : digest) {
            int b = aux & 0xff;
            if (Integer.toHexString(b).length() == 1) {
                hash += "0";
            }
            hash += Integer.toHexString(b);
        }
        return hash;
    }
    
    
    }
    

