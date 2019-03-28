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
