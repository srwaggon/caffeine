package caffeine.net;

import java.net.Socket;

import caffeine.Caffeine;
import caffeine.Game;
import caffeine.net.msg.Query;

public class ClientWorker extends Thread {
  protected final Connection client;
  protected Caffeine game;

  public ClientWorker(Game g, Socket client) {
    game = (Caffeine) g;
    this.client = new Connection(client);
    System.out.println("" + client.getInetAddress().toString() + ":"
        + client.getPort() + " connecting");
  }

  public Game game() {
    return game;
  }

  @Override
  public void run() {
    try {
      String query = "";
      String reply = "";
      while (client.isConnected()) {
        query = client.read();
        reply = processQuery(query);
        client.send(reply);
        Thread.sleep(10);
      }
    } catch (InterruptedException e) {

    }
  }

  public String processQuery(String strquery) {
    String result = "";
    // System.err.println("Processing query: " + strquery);
    try {

      Query query = new Query(strquery);
      String[] param = query.param();
      String qtype = param[1];

      if (qtype.equals("map")) {
        // TODO: too fucking filthy.
        result = game.world().getMap(Integer.parseInt(param[2])).toString();
      } else if (qtype.equals("entity")) {
        result = game.entity(Integer.parseInt(param[2])).toString();
      }

    } catch (Exception e) {
      result = "bad query";
    }

    // System.err.println("Sending result: " + result);
    return result;
  }
}
