package uk.co.lalev.enigmalabs.enigmalab;

public class VigenereCipher {
    private String key;

    public VigenereCipher(String key) {
        this.key = key.toUpperCase();
        if (key.length()==0) {
            throw new CipherException("Invalid key length (zero)");
        }

        for (int i=0; i<key.length(); i++) {
            if (!isLetter(key.charAt(i))) {
                throw new CipherException("Invalid character for Vigenere Cipher");
            }
        }
    }

    private boolean isLetter(char symbol) {
        return !(symbol < 'A' || symbol > 'Z');
    }

    public String encrypt(String plaintext) {
        StringBuilder response = new StringBuilder();
        int keyCounter = 0;

        for (int i=0; i<plaintext.length(); i++) {
            char c = plaintext.charAt(i);
            char k = key.charAt(keyCounter++);
            keyCounter %= key.length();

            if (!isLetter(c)) {
                throw new CipherException("Invalid character for Vigenere Cipher");
            }

           char r = (char)((c+k-2*'A')%26+'A');
           response.append(r);
        }
        return response.toString();
    }

    public String decrypt(String ciphertext) {
        StringBuilder response = new StringBuilder();
        int keyCounter = 0;

        for (int i=0; i<ciphertext.length(); i++) {
            char c = ciphertext.charAt(i);
            char k = key.charAt(keyCounter++);
            keyCounter %= key.length();

            if (!isLetter(c)) {
                throw new CipherException("Invalid character for Vigenere Cipher");
            }

            char r = (char)((((c-'A')-(k-'A')+26)%26)+'A');
            response.append(r);
        }
        return response.toString();
    }
}
