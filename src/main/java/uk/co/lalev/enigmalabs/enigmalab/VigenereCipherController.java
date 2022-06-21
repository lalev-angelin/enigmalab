package uk.co.lalev.enigmalabs.enigmalab;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VigenereCipherController {
    @RequestMapping("/vigenere")
    public String caesar(Model model) {
        return "vigenere";
    }

    @RequestMapping(value="/vigenere-encrypt", method=RequestMethod.POST)
    public String vigenereEncrypt(@RequestParam(value="plaintexte", defaultValue="") String plaintext,
                                  @RequestParam(value="keye", defaultValue="0") String key,
                                  Model model) {
        VigenereCipher c = new VigenereCipher(key);
        model.addAttribute("plaintexte", plaintext);
        model.addAttribute("keye", key);
        model.addAttribute("ciphertexte", c.encrypt(plaintext));
        return "vigenere";
    }

    @RequestMapping(value="/vigenere-decrypt", method=RequestMethod.POST)
    public String vigenereDecrypt(@RequestParam(value="ciphertextd", defaultValue="") String ciphertext,
                                  @RequestParam(value="keyd", defaultValue="0") String key,
                                  Model model) {
        VigenereCipher c = new VigenereCipher(key);
        model.addAttribute("ciphertextd", ciphertext);
        model.addAttribute("keyd", key);
        model.addAttribute("plaintextd", c.decrypt(ciphertext));
        return "vigenere";
    }
}
