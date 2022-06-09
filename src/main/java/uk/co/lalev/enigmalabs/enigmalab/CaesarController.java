package uk.co.lalev.enigmalabs.enigmalab;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CaesarController {

    @RequestMapping("/caesar")
    public String caesar(Model model) {
        return "caesar";
    }

    @RequestMapping(value="/caesar-encrypt", method=RequestMethod.POST)
    public String caesarEncrypt (@RequestParam(value="plaintexte", defaultValue="") String plaintext,
                                 @RequestParam(value="skipe", defaultValue="0") int skip,
                                 Model model) {
        CaesarCipher c = new CaesarCipher(skip);
        model.addAttribute("plaintexte", plaintext);
        model.addAttribute("skipe", skip);
        model.addAttribute("ciphertexte", c.encrypt(plaintext));
        return "caesar";
    }

    @RequestMapping(value="/caesar-decrypt", method=RequestMethod.POST)
    public String caesarDecrypt (@RequestParam(value="ciphertextd", defaultValue="") String ciphertext,
                                 @RequestParam(value="skipd", defaultValue="0") int skip,
                                 Model model) {
        CaesarCipher c = new CaesarCipher(skip);
        model.addAttribute("ciphertextd", ciphertext);
        model.addAttribute("skipd", skip);
        model.addAttribute("plaintextd", c.decrypt(ciphertext));
        return "caesar";
    }
}
