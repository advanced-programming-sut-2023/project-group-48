module Project.Repo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires com.google.gson;
    requires org.apache.commons.codec;

    exports controller;
    exports model;
    exports view;
    opens view to javafx.fxml;
    opens model to com.google.gson;
}