package selfmade.ebookConverter.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import selfmade.ebookConverter.model.EbookModel;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


public class EbookView extends HBox implements Initializable {
    @FXML
    private TreeView<File> treeView = buildFileSystemBrowser();
    File directory = new File("/home/usr/");

    public EbookView() {
        //   this.treeView = treeView;
        //     getChildren().add(treeView);
        //   treeView.setRoot(EbookModel.createDirectoryTree(directory));
    }

    @FXML
    protected void selectItem() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TreeView<File> treeView = buildFileSystemBrowser();
       // TreeItem<String> rootItem = new TreeItem<>(directory.getName());
       // treeView.setRoot(rootItem);

    }

    private TreeView buildFileSystemBrowser() {
        TreeItem<File> root = createNode(new File("/"));
        return new TreeView<File>(root);
    }

    private TreeItem<File> createNode(final File f) {
        return new TreeItem<File>(f) {
            private boolean isLeaf;

            private boolean isFirstTimeChildren = true;
            private boolean isFirstTimeLeaf = true;

            @Override
            public ObservableList<TreeItem<File>> getChildren() {
                if (isFirstTimeChildren) {
                    isFirstTimeChildren = false;

                    // First getChildren() call, so we actually go off and
                    // determine the children of the File contained in this TreeItem.
                    super.getChildren().setAll(buildChildren(this));
                }
                return super.getChildren();
            }

            @Override
            public boolean isLeaf() {
                if (isFirstTimeLeaf) {
                    isFirstTimeLeaf = false;
                    File f = (File) getValue();
                    isLeaf = f.isFile();
                }

                return isLeaf;
            }

            private ObservableList<TreeItem<File>> buildChildren(TreeItem<File> TreeItem) {
                File f = TreeItem.getValue();
                if (f != null && f.isDirectory()) {
                    File[] files = f.listFiles();
                    if (files != null) {
                        ObservableList<TreeItem<File>> children = FXCollections.observableArrayList();

                        for (File childFile : files) {
                            children.add(createNode(childFile));
                        }

                        return children;
                    }
                }

                return FXCollections.emptyObservableList();
            }
        };
    }
}