package com.example.demo.security;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.util.Base64Utils;

import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;

public class PasswordUtils {

	private static final int DEFAULT_COST = 16;
	private static final String ALGORITHM = "PBKDF2WithHmacSHA1";
	private static final int SIZE = 256;
	private static final SecureRandom RANDOM = new SecureRandom();
	
	public static String hashPassword(String password) {
	    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    String hashedPassword = passwordEncoder.encode(password);
	    return hashedPassword;
	}
	public static boolean checkPassword(String password, String hashedPassword) {
	    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    return passwordEncoder.matches(password, hashedPassword);
	}
	
	private static byte[] pbkdf2(char[] password, byte[] salt, int iterations) {
		KeySpec spec = new PBEKeySpec(password, salt, iterations, SIZE);
		try {
			SecretKeyFactory f = SecretKeyFactory.getInstance(ALGORITHM);
			return f.generateSecret(spec).getEncoded();
		} catch (NoSuchAlgorithmException ex) {
			throw new IllegalStateException("Missing algorithm: " + ALGORITHM, ex);
		} catch (InvalidKeySpecException ex) {
			throw new IllegalStateException("Invalid SecretKeyFactory", ex);
		}
	}

	public static boolean authenticate(String pass, String token) {
		char[] password = pass.toCharArray();
		byte[] hash = Base64Utils.decodeFromString(token);
		byte[] salt = Arrays.copyOfRange(hash, 0, SIZE / 8);
		byte[] check = pbkdf2(password, salt, 1 << DEFAULT_COST);
		int zero = 0;
		for (int idx = 0; idx < check.length; ++idx)
			zero |= hash[salt.length + idx] ^ check[idx];
		return zero == 0;
	}

}
