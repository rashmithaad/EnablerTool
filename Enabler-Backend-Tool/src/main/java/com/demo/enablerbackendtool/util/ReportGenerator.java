package com.demo.enablerbackendtool.util;

import com.demo.enablerbackendtool.model.Employee;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTextBox;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ReportGenerator {

    private static final String[] HEADERS = {"ID", "Name", "Department", "Status", "Country", "Region", "Division", "Entity"};

    public static byte[] generateReport(List<Employee> employees, String format) throws IOException {
        switch (format.toLowerCase()) {
            case "pdf":
                return generatePdfReport(employees);
            case "doc":
                return generateWordReport(employees);
            case "ppt":
                return generatePptReport(employees);
            case "xlsx":
                return generateExcelReport(employees);
            case "powerbi":
                return generatePowerBIReport(employees);
            default:
                return null;
        }
    }

    private static byte[] generateExcelReport(List<Employee> employees) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Inactive Employees");
        createHeaderRow(sheet);
        populateRows(employees, sheet);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        workbook.close();
        return out.toByteArray();
    }

    private static byte[] generatePdfReport(List<Employee> employees) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);
        com.itextpdf.kernel.pdf.PdfDocument pdfDoc = new com.itextpdf.kernel.pdf.PdfDocument(writer);
        Document document = new Document(pdfDoc);

        document.add(new Paragraph("Inactive Employees Report"));
        Table table = createTable();
        populateTable(employees, table);
        document.add(table);

        document.close();
        return out.toByteArray();
    }

    private static byte[] generateWordReport(List<Employee> employees) throws IOException {
        XWPFDocument document = new XWPFDocument();
        XWPFParagraph title = document.createParagraph();
        XWPFRun run = title.createRun();
        run.setText("Inactive Employees Report");
        run.setBold(true);

        for (Employee employee : employees) {
            XWPFParagraph para = document.createParagraph();
            run = para.createRun();
            run.setText(formatEmployeeDetails(employee));
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        document.write(out);
        document.close();
        return out.toByteArray();
    }

    private static byte[] generatePptReport(List<Employee> employees) throws IOException {
        XMLSlideShow ppt = new XMLSlideShow();
        XSLFSlide slide = ppt.createSlide();
        XSLFTextBox title = slide.createTextBox();
        title.setText("Inactive Employees Report");

        for (Employee employee : employees) {
            XSLFTextBox shape = slide.createTextBox();
            shape.setText(formatEmployeeDetails(employee));
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ppt.write(out);
        ppt.close();
        return out.toByteArray();
    }

    public static byte[] generatePowerBIReport(List<Employee> employees) throws IOException {
        // Implement Power BI generation logic (if applicable)
        return new byte[0]; // Placeholder
    }

    private static void createHeaderRow(Sheet sheet) {
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < HEADERS.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(HEADERS[i]);
        }
    }

    private static void populateRows(List<Employee> employees, Sheet sheet) {
        int rowNum = 1;
        for (Employee employee : employees) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(employee.getId());
            row.createCell(1).setCellValue(employee.getEmployee_name());
            row.createCell(2).setCellValue(employee.getDepartment_name());
            row.createCell(3).setCellValue(employee.getEmployee_status());
            row.createCell(4).setCellValue(employee.getCountry());
            row.createCell(5).setCellValue(employee.getRegion());
            row.createCell(6).setCellValue(employee.getDivision());
            row.createCell(7).setCellValue(employee.getEntity_name());
        }
    }

    private static Table createTable() {
        return new Table(UnitValue.createPercentArray(new float[]{1, 3, 3, 1, 2, 2, 2, 2}))
                .useAllAvailableWidth();
    }

    private static void populateTable(List<Employee> employees, Table table) {
        for (String header : HEADERS) {
            table.addHeaderCell(new com.itextpdf.layout.element.Cell().add(new Paragraph(header)));
        }

        for (Employee employee : employees) {
            table.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph(String.valueOf(employee.getId()))));
            table.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph(employee.getEmployee_name())));
            table.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph(employee.getDepartment_name())));
            table.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph(String.valueOf(employee.getEmployee_status()))));
            table.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph(employee.getCountry())));
            table.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph(employee.getRegion())));
            table.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph(employee.getDivision())));
            table.addCell(new com.itextpdf.layout.element.Cell().add(new Paragraph(employee.getEntity_name())));
        }
    }

    private static String formatEmployeeDetails(Employee employee) {
        return String.format("%d - %s, %s, %s, %s, %s, %s, %s",
                employee.getId(),
                employee.getEmployee_name(),
                employee.getDepartment_name(),
                employee.getEmployee_status(),
                employee.getCountry(),
                employee.getRegion(),
                employee.getDivision(),
                employee.getEntity_name());
    }
}
