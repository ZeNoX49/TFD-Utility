module app {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires javafx.graphics;

    opens app to javafx.fxml;
    opens app.controller.card.progression to javafx.fxml;
    opens app.controller.card.collectible to javafx.fxml;
    opens app.controller.card.prereglage to javafx.fxml;
    opens app.controller.page to javafx.fxml;

    exports app;
    exports app.pojo.progression.dev to com.fasterxml.jackson.databind;
    exports app.pojo.progression.user to com.fasterxml.jackson.databind;
    exports app.pojo.collectible to com.fasterxml.jackson.databind;
    exports app.pojo.prereglage to com.fasterxml.jackson.databind;
}