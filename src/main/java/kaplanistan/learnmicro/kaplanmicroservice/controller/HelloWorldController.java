package kaplanistan.learnmicro.kaplanmicroservice.controller;

import jakarta.persistence.Version;
import kaplanistan.learnmicro.kaplanmicroservice.model.Tryout;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    private MessageSource messageSource;

    public HelloWorldController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Version
    @GetMapping(path = "aye-boyz")
    public String hello() {
        return "Kara Kaplan Beyim hoşgeldiniz.";
    }

    @GetMapping(path = "aye-boyz-again")
    public Tryout helloAgain() {
        return new Tryout("Kara Kaplan Beyim hoşgeldiniz.");
    }

    @GetMapping(path = "aye-boyz/{name}")
    public Tryout helloAgain(@PathVariable String name) {
        return new Tryout(
                String.format("%s Beyim hoşgeldiniz. ", name));
    }

    @GetMapping(path = "internationalized-boyz")
    public String helloInternationalized() {
        var locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("hello.message", null, "Default Message", locale);
    }
}
