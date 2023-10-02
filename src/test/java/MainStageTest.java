import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import selfmade.ebookConverter.MainStage;

import static org.junit.jupiter.api.Assertions.*;

class MainStageTest {

    private MainStage mainStage;
    @BeforeEach
    public void test(){
        mainStage = new MainStage();
    }
    @Test
    void start() {
        assertNotNull(mainStage, "MainStage object should not be null after initialization");
    }

    @Test
    void showNewStage() {
    }
}