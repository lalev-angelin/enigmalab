package uk.co.lalev.enigmalabs.enigmalab;

public class CaesarCipher {
    private int offset;

    public CaesarCipher(int offset) {
        if (Math.abs(offset)>26) {
            throw new CipherException("Invalid offset for Caesar Chipher");
        }
        this.offset = offset;
    }

    private boolean isLetter(char symbol) {
        return !(symbol < 'A' || symbol > 'Z');
    }

    public String encrypt(String plaintext) {
        plaintext = plaintext.toUpperCase();
        StringBuilder response = new StringBuilder();

        for (int i=0; i<plaintext.length(); i++) {
            char c = plaintext.charAt(i);
            if (!isLetter(c)) {
                throw new CipherException("Invalid character for Caesar Cipher");
            }

            int position = c-'A';
            position = (position + offset)%26;

            if (position<0) {
                position = 26+position;   //Mind you, position is negative!
            }

            response.append((char)('A'+position));
        }
        return response.toString();
    }

    public String decrypt(String ciphertext) {
        ciphertext = ciphertext.toUpperCase();
        StringBuilder response = new StringBuilder();

        for (int i=0; i<ciphertext.length(); i++) {
            char c = ciphertext.charAt(i);
            if (!isLetter(c)) {
                throw new CipherException("Invalid character for Caesar Cipher");
            }
            int position = c-'A';
            position = position - offset;
            if (position<0) {
                position = 26+position;   //Mind you, position is negative!
            }
            if (position>=26) {
                position = position%26;
            }

            response.append((char)('A'+position));
        }
        return response.toString();
    }
}
