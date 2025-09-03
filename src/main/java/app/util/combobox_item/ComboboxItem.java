package app.util.combobox_item;

import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class ComboboxItem {

    // configure un ComboBox existant
    public <T extends HasImage> void setupComboBox(ComboBox<T> combobox, int size) {
        combobox.setCellFactory(param -> new ListCell<>() {
            private final ImageView imageView = new ImageView();
            private final StackPane pane = new StackPane(imageView);
            { pane.setAlignment(Pos.CENTER); }

            @Override
            protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);
                setText(null);
                if (empty || item == null || item.getImage() == null) {
                    setGraphic(null);
                } else {
                    imageView.setImage(item.getImage());
                    imageView.setFitWidth(size);
                    imageView.setFitHeight(size);
                    setGraphic(pane);
                }
            }
        });

        combobox.setButtonCell(new ListCell<>() {
            private final ImageView imageView = new ImageView();
            private final StackPane pane = new StackPane(imageView);
            { pane.setAlignment(Pos.CENTER); }

            @Override
            protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);
                setText(null);
                if (empty || item == null || item.getImage() == null) {
                    setGraphic(null);
                } else {
                    imageView.setImage(item.getImage());
                    imageView.setFitWidth(size);
                    imageView.setFitHeight(size);
                    setGraphic(pane);
                }
            }
        });
    }

}