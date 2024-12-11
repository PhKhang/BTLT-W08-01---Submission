package presentation;

import dto.ReportInfoDTO;

import java.util.List;

import bus.ReportBUS;

// Main class
public class Thing {

    // Main driver method
    public static void main(String[] args) {
        ReportBUS rp = new ReportBUS();
        List<ReportInfoDTO> list = rp.getAll();
        
        System.out.println("Report List:" + list.size());
        for (ReportInfoDTO report : list) {
            System.out.println(report.getReportId() + " " + report.getReporterId() + " " + report.getReporter() + " " + report.getReportedId() + " " + report.getReported() + " " + report.getReason() + " " + report.getStatus() + " " + report.getCreatedAt());
        }
    }
}