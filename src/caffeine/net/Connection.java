package caffeine.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import caffeine.net.packet.Packet;

public class Connection {
  protected boolean connected = false;
  protected Socket socket;
  protected Scanner in = null;
  protected PrintWriter out = null;
  protected ObjectInputStream ois;
  protected ObjectOutputStream oos;
  
  public Connection(String ip, int port) {
    try {
      socket = new Socket(ip, port);
      in = new Scanner(socket.getInputStream());
      out = new PrintWriter(socket.getOutputStream(), true);
      ois = new ObjectInputStream(socket.getInputStream());
      oos = new ObjectOutputStream(socket.getOutputStream());
    } catch (IOException ioe) {
      ioe.printStackTrace();
      System.exit(-1);
    }
    connected = true;
  }
  
  public Connection(Socket socket) {
    try {
      this.socket = socket;
      in = new Scanner(socket.getInputStream());
      out = new PrintWriter(socket.getOutputStream(), true);
      oos = new ObjectOutputStream(socket.getOutputStream());
      ois = new ObjectInputStream(socket.getInputStream());
    } catch (IOException ioe) {
      ioe.printStackTrace();
      System.exit(-1);
    }
    connected = true;
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
  
  public void send(Packet packet) {
    try {
      oos.writeObject(packet);
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      oos.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public boolean hasNext() {
    return in.hasNext();
  }
  
  public boolean hasLine() {
    return in.hasNextLine();
  }
  
  public boolean hasPacket() {
    try {
      return ois.available() > 0;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return false;
  }
  
  public String read() {
    return in.next();
  }
  
  public String readLine() {
    return in.nextLine();
  }
  
  public Packet readPacket() throws IOException {
    
    try {
      Packet packet = (Packet) (ois.readObject());
      return packet;
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  public void disconnect() {
    try {
      System.out.println("Disconnecting from " + this + ".");
      connected = false;
      if (in != null)
        in.close();
      if (out != null)
        out.close();
      if (socket != null)
        socket.close();
    } catch (IOException e) {
      e.printStackTrace();
      // System.exit(-1);
    }
  }
  
  public boolean isConnected() {
    return connected;// socket.isConnected() && !socket.isClosed();
  }
  
  @Override
  public String toString() {
    return socket.getInetAddress().toString();
  }
}
