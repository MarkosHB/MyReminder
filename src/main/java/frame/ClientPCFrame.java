package frame;

import client.Client;
import controller.ClientPCController;

public class ClientPCFrame extends ClientFrame {

    public ClientPCFrame(String title, int posX, int posY, Client client) {
        super(title, posX, posY, client);
    }

    @Override
    void assignController() {
        controller = new ClientPCController(this, client);
        this.controller(controller);
    }

}
