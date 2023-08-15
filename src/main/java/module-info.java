module selfmade.ebookconverter.ebookvocabularyconverter {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires com.google.gson;
    requires google.cloud.translate;
    requires google.cloud.core;

    opens selfmade.ebookConverter to javafx.fxml;
    exports selfmade.ebookConverter;
    exports selfmade.ebookConverter.view;
    opens selfmade.ebookConverter.view to javafx.fxml;
}