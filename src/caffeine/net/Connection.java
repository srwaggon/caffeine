package caffeine.net;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Connection {
  protected Socket socket;
  protected Scanner in = null;
  protected PrintWriter out = null;

  public Connection(String host, int port){
    try {
      socket = new Socket(host, port);
      in = new Scanner(socket.getInputStream());
      out = new PrintWriter(socket.getOutputStream(), true);
    } catch (IOException e) {
      System.out.println("No I/O.");
      System.exit(-1);
    }
    System.out.println("Connection established to " + socket);
  }

  public Connection(Socket _socket) {
    socket = _socket;
    try {
      in = new Scanner(_socket.getInputStream());
      out = new PrintWriter(_socket.getOutputStream(), true);
    } catch (IOException e) {
      System.out.println("No I/O.");
      System.exit(-1);
    }
    System.out.println("Connection established to " + socket);
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
    out.flush();
  }

  public Scanner getScanner(){
    return in;
  }

  public void disconnect() {
    try {
      System.out.println("Disconnecting from " + this + ".");
      if (in  != null) in.close();
      if (out != null) out.close();
      if (socket != null) socket.close();
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(-1);
    }
  }

  public boolean isConnected() {
    return  !socket.isClosed() && socket.isConnected();
  }

  @Override
  public String toString() {
    return socket.getInetAddress().toString();
  }
}
