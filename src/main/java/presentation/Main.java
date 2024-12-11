// package "mecha-jdbc.src.presentation"
package presentation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import bus.ReportBUS;
import dto.ReportInfoDTO;

public class Main {
    public static void main(String args[]) {
        // Create Configuration
        ReportBUS reportBUS = new ReportBUS();

        List<ReportInfoDTO> reports = reportBUS.getAll();

        String columnNames[] = { "ID", "Người báo cáo", "Người vi phạm", "Lý do", "Thời gian", "Tình trạng" };

        String data[][] = reportBUS.getData();

        JTable table = new JTable(data, columnNames);
        table.setBounds(30, 40, 200, 300);
        table.getColumnModel().getColumn(0).setPreferredWidth(8);
        table.getColumnModel().getColumn(4).setPreferredWidth(45);
        table.getColumnModel().getColumn(5).setPreferredWidth(40);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(table.getBounds());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));

        JFrame frame = new JFrame("Mecha dashboard");
        frame.setSize(800, 600);
        frame.setLayout(new FlowLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.LIGHT_GRAY);
        mainPanel.setLayout(new FlowLayout());
        mainPanel.setPreferredSize(new Dimension(800, 600));
        frame.add(mainPanel);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.add(sp);
        topPanel.add(panel);

        mainPanel.add(topPanel);

        // ----------------- Add Report -----------------
        panel.add(new JLabel("Chi tiết báo cáo"));
        JLabel reportIdLabel = new JLabel("Report ID:");
        // reportIdLabel.setAlignmentY(JPanel.TOP_ALIGNMENT);
        JTextField reportIdField = new JTextField();
        panel.add(reportIdLabel);
        panel.add(new JLabel("Báo cáo bởi:"));
        reportIdField.setEditable(false);
        reportIdField.setPreferredSize(new Dimension(300, 20));
        reportIdField.setMaximumSize(reportIdField.getPreferredSize());
        panel.add(reportIdField);

        JLabel reporterLabel = new JLabel("Người vi phạm:");
        JTextField reporterField = new JTextField();
        panel.add(reporterLabel);
        reporterField.setEditable(false);
        reporterField.setPreferredSize(new Dimension(300, 20));
        reporterField.setMaximumSize(reporterField.getPreferredSize());
        panel.add(reporterField);

        JLabel timeLabel = new JLabel("Thời gian báo cáo:");
        JTextField timeField = new JTextField();
        panel.add(timeLabel);
        timeField.setEditable(false);
        timeField.setPreferredSize(new Dimension(300, 20));
        timeField.setMaximumSize(timeField.getPreferredSize());
        panel.add(timeField);

        JLabel reasonLabel = new JLabel("Lý do:");
        JTextField reasonField = new JTextField();
        panel.add(reasonLabel);
        reasonField.setEditable(false);
        reasonField.setPreferredSize(new Dimension(300, 20));
        reasonField.setMaximumSize(reasonField.getPreferredSize());
        panel.add(reasonField);

        JLabel statLabel = new JLabel("Tình trạng:");
        JTextField statField = new JTextField();
        panel.add(statLabel);
        statField.setEditable(false);
        statField.setPreferredSize(new Dimension(300, 20));
        statField.setMaximumSize(statField.getPreferredSize());
        panel.add(statField);

        // JLabel reportedLabel = new JLabel("Reported:");
        // JTextField reportedField = new JTextField();
        // panel.add(reportedLabel);
        // reportedField.setEditable(false);
        // reportedField.setPreferredSize(new Dimension(300, 20));
        // reportedField.setMaximumSize(reportedField.getPreferredSize());
        // panel.add(reportedField);

        JButton warnButton = new JButton("Cảnh báo người dùng");
        warnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Warn button clicked");
                int reportId = Integer.valueOf(table.getValueAt(table.getSelectedRow(), 0).toString());
                reportBUS.setStatus(reportId, "Resolved");
                int reportedId = reports.get(reportId - 1).getReportedId();
                reportBUS.setUser(reportedId, "Warned");

                reports.get(table.getSelectedRow()).setStatus("Resolved");
                table.getModel().setValueAt("Resolved", table.getSelectedRow(), 5);
                statField.setText("Resolved");
            }
        });
        JButton lockButton = new JButton("Khóa người dùng");
        lockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Lock button clicked");
                int reportId = Integer.valueOf(table.getValueAt(table.getSelectedRow(), 0).toString());
                reportBUS.setStatus(reportId, "Resolved");
                int reportedId = reports.get(reportId - 1).getReportedId();
                reportBUS.setUser(reportedId, "Locked");

                reports.get(table.getSelectedRow()).setStatus("Resolved");
                table.getModel().setValueAt("Resolved", table.getSelectedRow(), 5);
                statField.setText("Resolved");
            }
        });
        JButton skipButton = new JButton("Bỏ qua báo cáo");
        skipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Skip button clicked");
                int reportId = Integer.valueOf(table.getValueAt(table.getSelectedRow(), 0).toString());
                reportBUS.setStatus(reportId, "Pending");

                reports.get(table.getSelectedRow()).setStatus("Pending");
                table.getModel().setValueAt("Pending", table.getSelectedRow(), 5);
                statField.setText("Pending");
            }
        });

        panel.add(warnButton);
        panel.add(lockButton);
        panel.add(skipButton);

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                    System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());
                    reportIdLabel.setText("Report ID: "
                            + data[Integer.valueOf(table.getValueAt(table.getSelectedRow(), 0).toString()) - 1][0]);
                    reportIdField.setText(
                            data[Integer.valueOf(table.getValueAt(table.getSelectedRow(), 0).toString()) - 1][1]);
                    reporterField.setText(
                            data[Integer.valueOf(table.getValueAt(table.getSelectedRow(), 0).toString()) - 1][2]);
                    timeField.setText(
                            data[Integer.valueOf(table.getValueAt(table.getSelectedRow(), 0).toString()) - 1][4]);
                    reasonField.setText(
                            data[Integer.valueOf(table.getValueAt(table.getSelectedRow(), 0).toString()) - 1][3]);
                    statField.setText(
                            data[Integer.valueOf(table.getValueAt(table.getSelectedRow(), 0).toString()) - 1][5]);
                            
                    // reportedField.setText(
                    // data[Integer.valueOf(table.getValueAt(table.getSelectedRow(), 0).toString())
                    // - 1][0]);
                }
            }
        });

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    static class ButtonListener implements ActionListener {
        int timesPressed;

        @Override
        public void actionPerformed(ActionEvent event) {
            timesPressed++;
            JButton button = (JButton) event.getSource();
            button.setText("Times pressed: " + timesPressed);
        }
    }
}