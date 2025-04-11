package f2.tironcinio.whistleblowing.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;

import f2.tironcinio.whistleblowing.responses.Ruolo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

public class JwtUtil {

	public static PrivateKey getPrivateKey() {
		
		Properties props = new Properties();
		
		try {
			props.load(new FileInputStream(new File("key.properties")));
			String privateKeyURL = props.getProperty("private");
			PemReader reader = new PemReader(new FileReader(privateKeyURL));
			PemObject object = reader.readPemObject();
			byte[] content = object.getContent();
			
			PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(content);
			PrivateKey pk = KeyFactory.getInstance("RSA").generatePrivate(spec);
			return pk;
			
		} catch (IOException | InvalidKeySpecException | NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	public static PublicKey getPublicKey() {
		
		Properties props = new Properties();
		
		try {
			props.load(new FileInputStream(new File("key.properties")));
			String publicKeyURL = props.getProperty("public");
			PemReader reader = new PemReader(new FileReader(publicKeyURL));
			
			X509EncodedKeySpec spec = new X509EncodedKeySpec(reader.readPemObject().getContent());
			return KeyFactory.getInstance("RSA").generatePublic(spec);
	
		} catch (IOException | InvalidKeySpecException | NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String tokenGenerator(String nome, String cognome, String email, Ruolo ruolo) {
		
		try {
			return Jwts.builder()
				.claim("nome", nome)
				.claim("cognome", cognome)
				.claim("email", email)
				.claim("ruolo", ruolo)
				.setId(UUID.randomUUID().toString())
				.setIssuedAt(Date.from(Instant.now().plus(5, ChronoUnit.HOURS)))
				.signWith(getPrivateKey())
				.compact();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Jws<Claims> verifyToken(String token){
		
		try {
			return Jwts.parserBuilder().setSigningKey(getPublicKey()).build().parseClaimsJws(token);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
