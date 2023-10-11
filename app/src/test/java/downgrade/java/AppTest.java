package downgrade.java;

import com.nimbusds.jose.jwk.JWK;
import foundation.identity.did.DIDDocument;
import kotlin.Triple;
import org.junit.Test;
import web5.credentials.DIDKey;

import static org.junit.Assert.*;

public class AppTest {
    @Test public void appHasAGreeting() {
        Triple<JWK, String, DIDDocument> didKey = DIDKey.Companion.generateEd25519();
        System.out.println(didKey);

        assertNotNull(didKey.component1());
        assertNotNull(didKey.component2());
        assertNotNull(didKey.component3());
    }
}
