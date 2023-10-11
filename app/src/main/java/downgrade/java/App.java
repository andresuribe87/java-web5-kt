package downgrade.java;

import com.nimbusds.jose.jwk.JWK;
import kotlin.Triple;
import web5.credentials.DIDKey;
import foundation.identity.did.DIDDocument;

public class App {
    public static void main(String[] args) {
        Triple<JWK, String, DIDDocument> didKey = DIDKey.Companion.generateEd25519();
        System.out.println(didKey);
    }
}
