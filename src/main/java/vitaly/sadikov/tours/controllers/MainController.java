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

    @GetMapping("/contacts")
    public String contacts() { return "contacts"; }
    @GetMapping("/feedback")
    public String feedback() { return "feedback"; }
    @GetMapping("/academy")
    public String academy(){
        return "academ_program";
    }
    @GetMapping("/dalayn")
    public String dalayn(){
        return "dalyan";
    }

    @GetMapping("/dammam")
    public String dammam(){
        return "dammam";
    }

    @GetMapping("/beijing")
    public String beijing() { return "beijing";}
    @GetMapping("/health")
    public String health() { return "health_tour";}
    @GetMapping("/dubai")
    public String dubai() { return "dubai";}
    @PostMapping("/")
    public String call(@ModelAttribute("person") Person person) {
        String tourMessage = "";
        personDAO.save(person);
        switch (person.getTour()){
            case "none":
                tourMessage = "без выбора тура";
                break;
            case "dalyan":
                tourMessage = "г. Далянь";
                break;
            case "beijing":
                tourMessage = "г. Пекин";
                break;
            case "dammam":
                tourMessage = "г. Даммам";
                break;
            case "academ":
                tourMessage = "академическая программа в области Китайской медицины";
                break;
            case "health":
                tourMessage = "оздоровительная поездка в Китай";
                break;
            case "dubai":
                tourMessage = "поездка в Дубай";
                break;
        }
        try {
            bot.sendAds(person.getName() + " " + person.getNumber() + "\nТур: " + tourMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }
}
