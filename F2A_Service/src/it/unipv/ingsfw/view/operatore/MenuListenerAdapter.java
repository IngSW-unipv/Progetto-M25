package it.unipv.ingsfw.view.operatore;

import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class MenuListenerAdapter implements MenuListener {
    private final Runnable action;

    public MenuListenerAdapter(Runnable action) {
        this.action = action;
    }

    @Override
    public void menuSelected(MenuEvent e) {
        action.run();
    }

    @Override
    public void menuDeselected(MenuEvent e) {}

    @Override
    public void menuCanceled(MenuEvent e) {}
}
