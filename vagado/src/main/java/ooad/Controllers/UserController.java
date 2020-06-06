package ooad.Controllers;

import ooad.DTO.SpelerDTO;
import ooad.Services.UserService;

public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public SpelerDTO registreer(String gebruikersnaam, String wachtwoord) {
        return userService.registreer(gebruikersnaam, wachtwoord);
    }

    public SpelerDTO login(String gebruikersnaam, String wachtwoord) {
        return userService.login(gebruikersnaam, wachtwoord);
    }


}
