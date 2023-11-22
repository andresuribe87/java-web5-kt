package downgrade.java;


import web5.sdk.crypto.InMemoryKeyManager;
import web5.sdk.crypto.KeyManager;
import web5.sdk.dids.DidKey;

public class App {
    public static void main(String[] args) {
        KeyManager km = new InMemoryKeyManager();
        DidKey didKey = DidKey.Companion.create(km, null);
        System.out.println(didKey);
    }
}
