package utils;

import model.Manager;
import model.Person;

import java.util.List;

import enums.Role;

public class Utils {

    public static boolean isEmailInUse(List<Person> users, String email) {
        return users.stream()
                .anyMatch(user -> user.getEmail().equals(email));
    }
    public static boolean isManager(Manager manager) {
        return manager.getRole() == Role.MANAGER;
    }
}
