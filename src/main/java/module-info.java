module selfmade.ebookconverter.ebookvocabularyconverter {
    requires javafx.controls;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
   requires google.cloud.core;
    requires google.cloud.translate;
    requires com.google.gson;
    //requires com.google.auth.oauth2;
    requires javafx.fxml;


    opens selfmade.ebookConverter to javafx.fxml;
    exports selfmade.ebookConverter;
    exports selfmade.ebookConverter.view;
    opens selfmade.ebookConverter.view to javafx.fxml;
    exports selfmade.ebookConverter.controller;
}

