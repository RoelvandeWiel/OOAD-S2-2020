package ooad.Controllers;

import ooad.DTO.SpelerDTO;
import ooad.Services.UserService;

public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public SpelerDTO registreerGebruiker(String gebruikersnaam, String wachtwoord) {
        return userService.registreerGebruiker(gebruikersnaam, wachtwoord);
    }

    public SpelerDTO loginGebruiker(String gebruikersnaam, String wachtwoord) {
        return userService.loginGebruiker(gebruikersnaam, wachtwoord);
    }


}
