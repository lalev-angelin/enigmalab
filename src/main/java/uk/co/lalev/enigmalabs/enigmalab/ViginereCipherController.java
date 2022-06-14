package uk.co.lalev.enigmalabs.enigmalab;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViginereCipherController {
    @RequestMapping("/viginere")
    public String caesar(Model model) {
        return "viginere";
    }

    @RequestMapping(value="/viginere-encrypt", method=RequestMethod.POST)
    public String caesarEncrypt (@RequestParam(value="plaintexte", defaultValue="") String plaintext,
                                 @RequestParam(value="keye", defaultValue="0") String key,
                                 Model model) {
        ViginereCipher c = new ViginereCipher(key);
        model.addAttribute("plaintexte", plaintext);
        model.addAttribute("keye", key);
        model.addAttribute("ciphertexte", c.encrypt(plaintext));
        return "viginere";
    }

    @RequestMapping(value="/viginere-decrypt", method=RequestMethod.POST)
    public String caesarDecrypt (@RequestParam(value="ciphertextd", defaultValue="") String ciphertext,
                                 @RequestParam(value="keyd", defaultValue="0") String key,
                                 Model model) {
        ViginereCipher c = new ViginereCipher(key);
        model.addAttribute("ciphertextd", ciphertext);
        model.addAttribute("keyd", key);
        model.addAttribute("plaintextd", c.decrypt(ciphertext));
        return "viginere";
    }
}
