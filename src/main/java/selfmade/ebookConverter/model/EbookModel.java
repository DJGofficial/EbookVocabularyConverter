package selfmade.ebookConverter.model;

import javafx.scene.control.TreeItem;

import java.io.File;

public class EbookModel {

    public static TreeItem<String> createDirectoryTree(File directory) {
        System.out.println("createDirectoryTree(File directory)"+directory.getName());
        TreeItem<String> rootItem = new TreeItem<>(directory.getName());
        rootItem.setExpanded(true);

        createTree(directory, rootItem);

        return rootItem;
    }

    private static void createTree(File file, TreeItem<String> parentItem) {
        System.out.println("createTree(File file, TreeItem<String> parent");
        if (file.isDirectory()) {
            for (File subFile : file.listFiles()) {
                TreeItem<String> newItem = new TreeItem<>(subFile.getName());
                System.out.println(newItem.toString());
                parentItem.getChildren().add(newItem);
                createTree(subFile, newItem);
            }
        }
    }
}
