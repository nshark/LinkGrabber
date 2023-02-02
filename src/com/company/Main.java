package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class Main implements ActionListener {
    public static TextArea m;
    public static TextArea TextAreaLink;
    public static TextArea TextAreaKeyWord;
    public static void main(String[] args) {
        JFrame jFrame = new JFrame("Browser");
        JPanel jPanel = new JPanel(new BorderLayout());
        jFrame.add(jPanel);
        m = new TextArea();
        m.setPreferredSize(new Dimension(500,400));
        JScrollPane scroller = new JScrollPane(m, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jPanel.add(scroller, BorderLayout.CENTER);
        JPanel jPanel2 = new JPanel(new GridLayout(1,3));
        jPanel.add(jPanel2,BorderLayout.NORTH);
        TextAreaLink = new TextArea();
        TextAreaKeyWord = new TextArea();
        Button search = new Button("search");
        search.addActionListener(new Main());
        jPanel2.add(TextAreaLink);
        jPanel2.add(TextAreaKeyWord);
        jPanel2.add(search);

        jFrame.setSize(500,500);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.requestFocus();
    }
    public static ArrayList<String> readURL(String urlString){
        ArrayList<String> v = new ArrayList<>();
        try {

            URL url = new URL(urlString);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while((line = br.readLine()) != null){
                v.add(line);
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return v;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StringBuilder sb = new StringBuilder();
        for (String s : readURL(TextAreaLink.getText())){
            if (s.contains("href=\"")) {
                int a = s.indexOf("href=\"")+6;
                int b = 0;
                for (int i = a; i < s.length(); i++) {
                    if (s.toCharArray()[i]=='\"'){
                        b=i;
                        break;
                    }
                }

                String s2 = s.substring(a,b);

                if (s2.contains(TextAreaKeyWord.getText()) && s2.contains("http")){
                    sb.append(s2);
                    sb.append("\n");
                }
            }
        }
        System.out.println(sb);
        m.setText(sb.toString());
    }
}
