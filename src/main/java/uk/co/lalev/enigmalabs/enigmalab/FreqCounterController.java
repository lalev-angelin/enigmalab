package uk.co.lalev.enigmalabs.enigmalab;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FreqCounterController {
    @RequestMapping("/freqcounter")
    public String freqStart (Model model) {
        return "freqcounter";
    }

    @RequestMapping(value="/freqcounter", method = RequestMethod.POST)
    public String freqEnd (@RequestParam String text, Model model) {
        model.addAttribute("freqs", FreqCounter.count(text));
        return "freqcounter";
    }

}
