#MICROSERVICOS COM JAVALIN IO
_Javalin framework for lightweight microservices_

Java 8 or later and kotlin, servlet based, unopionated framework and main goas are simplicity.

- Goal: Write web applications quickly.

- Requirements:
    - no requirements for application structure;
    - no annotations;
    - no reflection;
    - no other magics;
    - just code!
        
- Author: **Rudson Kiyoshi Souza Carvalho**, SP - 27/03/2019

- Description: Simple Users CRUD

    - Sample Main Class Service App
    
    ```
    import io.javalin.Javalin;
    import java.io.IOException;
    import static io.javalin.apibuilder.ApiBuilder.*;
    
    public class App {
    
        public static void main(String[] args) throws IOException {
    
            Javalin app = Javalin.create().start(7000);
    
            app.after("/*", ctx -> {
                ctx.res.addHeader("x-token","xpto-token");
            });
    
            app.routes(() -> {
                path("users", () -> {
                    get(UserController::getAllUsers);
                    post(UserController::createUser);
                    path(":id", () -> {
                        get(UserController::getUser);
                        patch(UserController::updateUser);
                        delete(UserController::deleteUser);
                    });
                });
            });
        }
    }
    ```
    
    - Sample UserController Class
    
    ```
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
    ```

    - **GET** http://localhost:7000/users
    
    ```
    [
         {
             "uuid": "9f8eb100-635a-4b58-94af-62de69e141ad",
             "name": "Maria",
             "email": "maria@test.com"
         },
         {
             "uuid": "25b23274-8b6e-49d8-9fec-26786825d290",
             "name": "Paula",
             "email": "paula@test.com"
         },
         {
             "uuid": "8b88a619-cc15-433f-9fcf-6e1d3000399c",
             "name": "Carla",
             "email": "carla@test.com"
         },
         {
             "uuid": "8900b970-8335-4413-8b7f-0dc599b440bc",
             "name": "Aretuza",
             "email": "aretuza@test.com"
         }
     ]
     ```

     - **GET** http://localhost:7000/users/9f8eb100-635a-4b58-94af-62de69e141ad
 
      ```
      {
        "uuid": "9f8eb100-635a-4b58-94af-62de69e141ad",
        "name": "Maria dos Santos",
        "email": "maria@test.com"
      }
      ```

     - **PATCH** http://localhost:7000/users/9f8eb100-635a-4b58-94af-62de69e141ad
       
       (payload)
       
      ```
      {
            "uuid": "9f8eb100-635a-4b58-94af-62de69e141ad",
            "name": "Maria dos Santos",
            "email": "maria@test.com"
      }
      ```
     - **POST** http://localhost:7000/users
        
       (payload)
       
      ```
      {           
            "name": "Eduarda",
            "email": "eduarda@test.com"
      }
      ```   
    
     - **DELETE** http://localhost:7000/users/9f8eb100-635a-4b58-94af-62de69e141ad
     
     
     