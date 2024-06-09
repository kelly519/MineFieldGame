import controller.MineFieldController;
import model.MineField;
import view.MineFieldView;

public class Main {
    public static void main(String[] args) {
        MineField model = new MineField();
        MineFieldView view = new MineFieldView(model.getBoard());
        new MineFieldController(model, view);
    }
}