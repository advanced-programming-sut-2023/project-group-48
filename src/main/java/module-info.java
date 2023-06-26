module Project.Repo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires com.google.gson;
    requires org.apache.commons.codec;
    requires org.apache.commons.io;

    exports controller;
    exports model;
    exports view;
    opens view to javafx.fxml;
    opens model to com.google.gson;
    opens model.Match to com.google.gson;
    opens model.Buildings to com.google.gson;
    opens model.People to com.google.gson;
}