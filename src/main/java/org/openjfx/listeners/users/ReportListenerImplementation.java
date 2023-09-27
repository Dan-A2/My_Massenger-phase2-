package org.openjfx.listeners.users;

import org.openjfx.controller.modelController.Tweet_CommentController;
import org.openjfx.event.ReportEvent;

public class ReportListenerImplementation implements ReportListener{

    private Tweet_CommentController controller = new Tweet_CommentController();

    @Override
    public void listen(ReportEvent event) {
        controller.report(event.getTweet());
    }
}