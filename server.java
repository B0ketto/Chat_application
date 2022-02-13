import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ScrollBarUI;
import javax.swing.plaf.basic.BasicScrollBarUI;

// import org.w3c.dom.events.MouseEvent;

import javax.swing.border.*;  
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.*;
// import java.net.ServerSocket;
// import java.util.Calendar;
// import java.text.SimpleDateFormat;   
import java.net.ServerSocket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

class server implements ActionListener{

    JPanel p1;
    JTextField t1;
    JButton b1;
    static JPanel a1;
    // static JPanel a1;
    static JFrame f1 = new JFrame();
    static Box vertical = Box.createVerticalBox();

    static ServerSocket skt;
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;

    Boolean typing;

    server(){
        f1.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        p1 = new JPanel();
        
        p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(0, 33, 44));
        p1.setBounds(0, 0, 500, 60);
        f1.add(p1);
        
        ImageIcon i1 =  new ImageIcon(ClassLoader.getSystemResource("icons/dp2.png"));
        Image i2 = i1.getImage().getScaledInstance(60, 60 , Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l1 = new JLabel(i3);
        l1.setBounds(13, 4, 60, 60);
        p1.add(l1);
        
        l1.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent ae){
                System.exit(0);
            }
        });
        
        ImageIcon i4 =  new ImageIcon(ClassLoader.getSystemResource("icons/back.png"));
        Image i5 = i4.getImage().getScaledInstance(13, 15 , Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel l2 = new JLabel(i6);
        l2.setBounds(7, 23, 13, 15);
        p1.add(l2);
        
        ImageIcon i7 =  new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image i8 = i7.getImage().getScaledInstance(30, 30 , Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel l3 = new JLabel(i9);
        l3.setBounds(260, 20, 20, 20);
        p1.add(l3);
        
        ImageIcon i10 =  new ImageIcon(ClassLoader.getSystemResource("icons/Vector.png"));
        Image i11 = i10.getImage().getScaledInstance(20, 15 , Image.SCALE_DEFAULT);
        ImageIcon i12 = new ImageIcon(i11);
        JLabel l4 = new JLabel(i12);
        l4.setBounds(190, 23, 90, 15);
        p1.add(l4);

        JLabel l5 = new JLabel("Sam");
        l5.setFont(new Font("SAN_SERIF", Font.BOLD, 14));
        l5.setForeground(Color.WHITE);
        l5.setBounds(75, 12, 70, 30);
        p1.add(l5);

        JLabel l6 = new JLabel("Active Now");
        l6.setFont(new Font("SAN_SERIF", Font.PLAIN, 10));
        l6.setForeground(Color.WHITE);
        l6.setBounds(75, 28, 70, 30);
        p1.add(l6);

        Timer t = new Timer(1, new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                if(!typing){
                    l6.setText("Active Now");
                }
            }
        });

        t.setInitialDelay(2000);

        a1 = new JPanel();
        // a1.setBounds(10, 70, 300, 370 );
        a1.setFont(new Font("SAN_SERIF", Font.PLAIN, 14));
        // these functions work for text area not jPanel
        // a1.setEditable(false);
        // a1.setLineWrap(true);
        // a1.setWrapStyleWord(true);
        a1.setBackground(new Color(232, 232, 232));
        a1.setForeground(Color.BLACK);
        // f1.add(a1);

        JScrollPane sp = new JScrollPane(a1);
        sp.setBounds(10, 70, 300, 370 );
        sp.setBorder(BorderFactory.createEmptyBorder());
        f1.add(sp);

        ScrollBarUI ui = new BasicScrollBarUI(){
            protected JButton createDecreaseButton(int orientation){
                JButton button = super.createDecreaseButton(orientation);
                button.setBackground(Color.black);
                button.setForeground(Color.WHITE);
                return button;
            }

            protected JButton createIncreaseButton(int orientation){
                JButton button = super.createIncreaseButton(orientation);
                button.setBackground(Color.black);
                button.setForeground(Color.WHITE);
                return button;
            }
        };

        sp.getVerticalScrollBar().setUI(ui);
        f1.add(sp); 

        
        t1 = new JTextField();
        t1.setBounds(10, 450 , 243, 30);
        t1.setBackground(new Color(0, 33, 44));
        t1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        t1.setForeground(Color.WHITE);
        f1.add(t1);

        t1.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent ke){
                l6.setText("typing....");

                t.stop();
                typing=true;
            }

            public void keyReleased(KeyEvent ke){
                typing = false;

                if(!t.isRunning()){
                    t.start();
                }
            }
        });

        b1 = new JButton("Send");
        b1.setBounds(260, 450, 48, 30);
        b1.setBackground(new Color(199, 235, 246));
        b1.setForeground(Color.WHITE);
        b1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        b1.addActionListener(this);
        f1.add(b1);     

        f1.getContentPane().setBackground(new Color(232, 232, 232));
        f1.setLayout(null);
        f1.setSize(320, 500);
        f1.setLocation(300, 100);
        f1.setUndecorated(true);
        f1.setVisible(true);

    }


    public void actionPerformed(ActionEvent ae){
        try{
            String out = t1.getText();      
            
            
            JPanel p2 = formatLabel(out);
            // sendTextToFile(out);
            a1.setLayout(new BorderLayout());
            JPanel right = new JPanel(new BorderLayout());
            right.add(p2, BorderLayout.LINE_END);
            vertical.add(right);
            vertical.add(Box.createVerticalStrut(10));

            a1.add(vertical, BorderLayout.PAGE_START);

            // a1.add(p2);

            // a1.setText(a1.getText()+"\n\t\t"+out);
            t1.setText("");
            dout.writeUTF(out);

        }catch(Exception e){
            System.out.println(e);
        }
    }


    // public void sendTextToFile(String message) throws FileNotFoundException{
    //     try(FileWriter f = new FileWriter("chat.txt");
    //             PrintWriter p = new PrintWriter(new BufferedWriter(f));){
    //         p.println("Garima: "+message);
    //     }catch (Exception e) {
    //             e.printStackTrace();
    //         }
    // }
    
    public JPanel formatLabel(String out){
        JPanel p3 = new JPanel();
        p3.setLayout(new BoxLayout(p3, BoxLayout.Y_AXIS));

        JLabel l1 = new JLabel("<html><p style = \"width: 100px\">"+out+"</html>");
        l1.setBackground(new Color(4, 69, 89));
        l1.setForeground(Color.WHITE);
        l1.setOpaque(true);
        
        l1.setBorder(new EmptyBorder(15, 15, 15, 30));

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        JLabel l2 = new JLabel();
        l2.setText(sdf.format(cal.getTime()));


        p3.add(l1);
        p3.add(l2); 
        return p3;
    }

    public static JPanel formatL(String in){
        JPanel p3 = new JPanel();
        p3.setLayout(new BoxLayout(p3, BoxLayout.Y_AXIS));

        JLabel l1 = new JLabel(in);
        l1.setBackground(new Color(171, 222, 238));
        l1.setForeground(Color.BLACK);
        l1.setOpaque(true);
        
        l1.setBorder(new EmptyBorder(15, 15, 15, 30));

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        JLabel l2 = new JLabel();
        l2.setText(sdf.format(cal.getTime()));


        p3.add(l1);
        p3.add(l2); 
        return p3;
    }

    
    public static void main(String args[]){
        new server().f1.setVisible(true);
        String msginput = "";

        try{
            skt = new ServerSocket(6001);
            s = skt.accept();
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
        
            while(true){
                msginput = din.readUTF();
                // a1.setText(a1.getText()+"\n"+msginput); 
                JPanel p2 = formatL(msginput);

                JPanel left = new JPanel(new BorderLayout());
                left.add(p2, BorderLayout.LINE_START);
                vertical.add(left);
                f1.validate();
            }

            // skt.close(); 
            // s.close();


        }catch(Exception e){}

    }
}