package uk.co.lalev.enigmalabs.enigmalab;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChiSquaredController {
    @RequestMapping("/chisquared")
    public String chiStart(Model model) {
        return "chisquared";
    }

    @RequestMapping(value="/chisquared", method = RequestMethod.POST)
    public String chiEnd(@RequestParam(value="text") String text,
                           Model model) {
        ChiSquaredAnalyser c = new ChiSquaredAnalyser();
        StringBuilder result = new StringBuilder();

        for (int i=1; i<15; i++) {
            result.append("0..").append(i).append("..").append(2*i).append("...     ");
            result.append(c.computeChiSquare(text, i));
            result.append("\n");
        }

        model.addAttribute("chi", text);
        model.addAttribute("chi", result);
        return "chisquared";
    }
}
