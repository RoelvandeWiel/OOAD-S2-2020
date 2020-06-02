package ooad.Controllers;

import ooad.DTO.GebruikerDTO;
import ooad.Services.UserService;

public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public GebruikerDTO registreerGebruiker(String gebruikersnaam, String wachtwoord) {
        return userService.registreerGebruiker(gebruikersnaam, wachtwoord);
    }

    public GebruikerDTO loginGebruiker(String gebruikersnaam, String wachtwoord) {
        return userService.loginGebruiker(gebruikersnaam, wachtwoord);
    }


}
