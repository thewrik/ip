package duke.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;

    public DialogBox(Label l, ImageView iv, Color color) {
        text = l;
        displayPicture = iv;

        text.setTextFill(Color.WHITE);
        text.setWrapText(true);

        VBox textWrapper = new VBox();
        textWrapper.getChildren().add(text);
        textWrapper.setPadding(new Insets(10,10,10,10));
        textWrapper.setAlignment(Pos.CENTER);

        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(textWrapper, displayPicture);
        this.setBackground(new Background(new BackgroundFill(color, new CornerRadii(25), Insets.EMPTY)));
        this.setPadding(new Insets(10,10,10,10));
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * This generates a green dialog box with the relevant parameters displayed, pertaining to the User.
     *
     * @param l Text to be displayed in the dialog box.
     * @param iv The image assigned to the user's profile.
     *
     * @return The Dialogue Box with the above specifications.
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv, Color.GREEN);
    }

    /**
     * This generates a blue dialog box with the relevant parameters displayed, pertaining to Duke.
     *
     * @param l Text to be displayed in the dialog box.
     * @param iv The image assigned to Duke's profile (i.e. Morty).
     *
     * @return The Dialogue Box with the above specifications.
     */
    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv, Color.BLUE);
        db.flip();
        return db;
    }
}

