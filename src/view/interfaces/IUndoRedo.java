package view.interfaces;

/**
 * Created by oliviachisman on 2019-08-14
 */
public interface IUndoRedo extends ICommand {
    void undo();
    void redo();
}
