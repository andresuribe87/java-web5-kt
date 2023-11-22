package downgrade.java;

import foundation.identity.did.VerificationMethod;
import io.ktor.client.engine.cio.CIO;
import kotlin.Unit;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import web5.sdk.crypto.InMemoryKeyManager;
import web5.sdk.crypto.KeyManager;
import web5.sdk.dids.*;

import static org.junit.Assert.*;

public class AppTest {
    @Test public void appHasAGreeting() {
        KeyManager km = new InMemoryKeyManager();
        DidKey didKey = DidKey.Companion.create(km, null);
        System.out.println(didKey);
    }

    @Test public void didIonGeneration() {
        KeyManager km = new InMemoryKeyManager();
        DidIonHandle did = DidIonManager.Default.create(km, new CreateDidIonOptions());
        assertTrue(did.getUri().startsWith("did:ion"));

        assert did.getCreationMetadata() != null;
        assertTrue(did.getCreationMetadata().getLongFormDid().startsWith(did.getUri()));
    }

    @Test public void didIonCustomEngine() {
        DidIonManager myManager = DidIonManagerKt.DidIonManager(AppTest::customConfig);
        DidResolutionResult result = myManager.resolve("did:ion:EiClkZMDxPKqC9c-umQfTkR8vvZ9JPhl_xLDI9Nfk38w5w", null);
        VerificationMethod verificationMethod = result.getDidDocument().getVerificationMethods().stream().findFirst().get();
        assertEquals("#someKeyId", verificationMethod.getId().toString());
    }

    @Test public void didIonCustomWithLambda() {
        DidIonManager myManager = DidIonManagerKt.DidIonManager(cfg -> {
            cfg.setIonHost("my_custom_host");
            cfg.setEngine(CIO.INSTANCE.create(cioCfg -> Unit.INSTANCE));
            return Unit.INSTANCE;
        });
        DidResolutionResult result = myManager.resolve("did:ion:EiClkZMDxPKqC9c-umQfTkR8vvZ9JPhl_xLDI9Nfk38w5w", null);
        VerificationMethod verificationMethod = result.getDidDocument().getVerificationMethods().stream().findFirst().get();
        assertEquals("#someKeyId", verificationMethod.getId().toString());
    }

    @NotNull
    private static Unit customConfig(DidIonConfiguration cfg) {
        cfg.setIonHost("https://ion.tbddev.org");
        cfg.setEngine(
                CIO.INSTANCE.create(t -> Unit.INSTANCE)
        );
        return Unit.INSTANCE;
    }

}
