package selfmade.ebookConverter.model;

import java.util.Objects;

public class TextAttributesObject<T> {

    private T deckName;
    private T modelName;
    private T front;
    private T back;
    private boolean note;
    private boolean bookmark;

    public TextAttributesObject(T deckName, T modelName, T front, T back, boolean note, boolean bookmark) {
        this.deckName = deckName;
        this.modelName = modelName;
        this.front = front;
        this.back = back;
        this.note = note;
        this.bookmark = bookmark;
    }

    public T getDeckName() {
        return deckName;
    }

    public void setDeckName(T deckName) {
        this.deckName = deckName;
    }

    public T getModelName() {
        return modelName;
    }

    public void setModelName(T modelName) {
        this.modelName = modelName;
    }

    public T getFront() {
        return front;
    }

    public void setFront(T front) {
        this.front = front;
    }

    public T getBack() {
        return back;
    }

    public void setBack(T back) {
        this.back = back;
    }

    public boolean hasNote() {
        return note;
    }

    public void setNote(boolean note) {
        this.note = note;
    }

    public boolean isBookmarked() {
        return bookmark;
    }

    public void setBookmark(boolean bookmark) {
        this.bookmark = bookmark;
    }

    @Override
    public String toString() {
        return "Text Attributes{" +
                "deckName=" + deckName +
                ", modelName=" + modelName +
                ", front=" + front +
                ", back=" + back +
                ", note=" + note +
                ", bookmark=" + bookmark +
                '}';
    }
}


