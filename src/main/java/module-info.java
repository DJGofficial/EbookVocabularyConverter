module selfmade.ebookconverter.ebookvocabularyconverter {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;

    opens selfmade.ebookconverter.ebookvocabularyconverter to javafx.fxml;
    exports selfmade.ebookconverter.ebookvocabularyconverter;
}