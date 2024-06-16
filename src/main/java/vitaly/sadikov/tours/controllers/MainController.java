package vitaly.sadikov.tours.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import vitaly.sadikov.tours.bot.Bot;
import vitaly.sadikov.tours.dao.PersonDAO;
import vitaly.sadikov.tours.models.Person;

@Controller
public class MainController {
    @Autowired
    PersonDAO personDAO;
    public Bot bot = new Bot();
    public MainController() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(bot);
    }
    @GetMapping("/")
    public String mainPage() {
        return "index";
    }

    @GetMapping("/academy")
    public String academy(){
        return "academ_program";
    }

    @GetMapping("/dalayn")
    public String dalayn(){
        return "dalyan";
    }

    @PostMapping("/")
    public String call(@ModelAttribute("person") Person person) {
        personDAO.save(person);
        try {
            bot.sendAds(person.getName() + " " + person.getNumber());

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }
}
