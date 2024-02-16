package polytech.projets10.g1._1tbonnespratiquesgreenit.sockets;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WSMessageController {

    @MessageMapping("/message/{roomId}")
    @SendTo("/game/{roomId}/messages")
    public String greeting(@DestinationVariable String roomId, WSMessage message) {
        //processMessage(message)

        //send new event to everyone in the room
        return "bjr";
    }

}
