package org.openjfx.listeners.massages;

import org.openjfx.controller.modelController.MassageController;
import org.openjfx.controller.modelController.UserController;
import org.openjfx.event.DeleteMassageEvent;
import org.openjfx.event.EditMassageEvent;

public class EditDeleteMassageListenerImplementation implements Edit_DeleteMassageListener {

    private MassageController controller = new MassageController();
    private UserController controller1 = new UserController();

    @Override
    public void listenEdit(EditMassageEvent event) {
        controller.editMassage(event.getMassage(), event.getText());
    }

    @Override
    public void listenDeleteTotally(DeleteMassageEvent event) {
        controller.deleteMassage(event.getMassage());
    }

    @Override
    public void listenDeleteForUser(DeleteMassageEvent event) {
        controller1.unsaveMassage(event.getUser(), event.getMassage());
    }
}
