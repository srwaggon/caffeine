package caffeine.net;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connection {
  protected Socket socket;
  protected BufferedReader in = null;
  protected PrintWriter out = null;

  public Connection(String host, int port) {
    try {
      System.out.println("Connecting to " + host);
      socket = new Socket(host, port);
      out = new PrintWriter(socket.getOutputStream(), true);
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    } catch (UnknownHostException e) {
      System.out.println("Unknown host: " + host);
      System.exit(1);
    } catch (IOException e) {
      System.out.println("No I/O");
      System.exit(1);
    }
    System.out.println("Connection established.");
  }

  public Connection(Socket connection) {
    socket = connection;
    try {
      in = new BufferedReader(
          new InputStreamReader(connection.getInputStream()));
      out = new PrintWriter(connection.getOutputStream(), true);
    } catch (IOException e) {
      System.out.println("No I/O.");
      System.exit(-1);
    }
  }

  @Override
  public void finalize() {
    try {
      disconnect();
      super.finalize();
    } catch (Throwable e) {
      e.printStackTrace();
    }
  }

  public void send(String msg) {
    out.println(msg);
  }

  public String read() {
    String input = "";
    try {
      input = in.readLine();
      if (input == null) {
        disconnect();
      }
    } catch (EOFException eof) {
      System.out.println(this + " closed.");
    } catch (IOException e) {
      disconnect();
    }
    return input;
  }

  public void disconnect() {
    try {
      System.out.println("Disconnecting from " + this + ".");
      in.close();
      out.close();
      socket.close();
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(-1);
    }
  }

  public boolean isConnected() {
    return socket.isConnected() && !socket.isClosed();
  }

  @Override
  public String toString() {
    return socket.getInetAddress().toString();
  }
}
