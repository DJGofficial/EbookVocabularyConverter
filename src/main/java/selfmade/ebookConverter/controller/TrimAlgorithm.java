package selfmade.ebookConverter.controller;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import selfmade.ebookConverter.view.EbookView;

public class TrimAlgorithm {

    public void getTextToVocabulary(ObservableList<Node> content) {
        for (Node node : content) {
            node.lookup(".toggle-button").getStyle();
            System.out.println(node);
        }
        }

}
