module Project.Repo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires com.google.gson;
    requires org.apache.commons.codec;

    exports controller;
    exports model;
    exports view;
    opens model.Match to com.google.gson;
    opens model.Buildings to com.google.gson;
    opens model.People to com.google.gson;
    opens model to com.google.gson, javafx.fxml;
    opens view to com.google.gson, javafx.fxml;
}