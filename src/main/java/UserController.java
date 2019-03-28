import io.javalin.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserController {

    public static List<User> getUsers() {

        if (users.isEmpty()) {
            users.add(new User(UUID.randomUUID().toString(), "Maria", "maria@test.com"));
            users.add(new User(UUID.randomUUID().toString(), "Paula", "paula@test.com"));
            users.add(new User(UUID.randomUUID().toString(), "Carla", "carla@test.com"));
        }

        return users;
    }

    private static List<User> users = new ArrayList();

    public static void getAllUsers(Context context) {
        context.json(getUsers());
    }

    public static void createUser(Context context) {
        User u = context.bodyAsClass(User.class);
        users.add(new User(UUID.randomUUID().toString(), u.getName(), u.getEmail()));
    }

    public static void getUser(Context context) {
        context.json(getUsers().stream().filter(u -> u.getUuid().equals(context.pathParam(":id"))).findFirst().orElse(new User()));
    }

    public static void updateUser(Context context) {
        users.stream().filter(u -> u.getUuid().equals(context.pathParam(":id"))).findFirst().ifPresent(update -> {
            User user = context.bodyAsClass(User.class);
            update.setName(user.getName());
            update.setEmail(user.getEmail());
        });
    }

    public static void deleteUser(Context context) {
        users.stream().filter(u -> u.getUuid().equals(context.pathParam(":id"))).findFirst().ifPresent(u -> users.remove(u));
    }

    private static class User {

        public User() {

        }

        public User(String uuid, String name, String email) {
            this.uuid = uuid;
            this.name = name;
            this.email = email;
        }

        private String uuid;

        private String name;

        private String email;

        public String getUuid() {
            return uuid;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}

