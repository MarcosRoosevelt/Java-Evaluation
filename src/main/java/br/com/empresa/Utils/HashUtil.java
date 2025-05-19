package br.com.empresa.Utils;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class HashUtil {

    public static String gerarHash(String password) {
        Argon2 argon2 = Argon2Factory.create();
        try {
            return argon2.hash(2, 65536, 1, password.toCharArray());
        } finally {
            argon2.wipeArray(password.toCharArray());
        }
    }

    public static boolean verificarSenha(String password, String hashPassword) {
        Argon2 argon2 = Argon2Factory.create();
        return argon2.verify(hashPassword, password.toCharArray());
    }

}
