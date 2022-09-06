package com.example.galaxyreader.parser;

import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class PDFViewer {
    public static String buildPDF(List<Line> lines) throws Exception {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.LETTER);
        document.addPage(page);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        float FontSize = (float) 12.0;
        String Text = "Test";
        PDFont font = PDType1Font.HELVETICA_BOLD;
        float offset = FontSize * 1;
        float leading = 1.5f * FontSize;
        contentStream.beginText();
        contentStream.setFont(font, 12);
        contentStream.moveTextPositionByAmount( 100, 700 );
        contentStream.setLeading(leading);
        contentStream.newLineAtOffset(-25, 25);
        float lastY = 0;
        for (Line line : lines) {
            // FontSize = (float) 12.0;
            FontSize = line.fontSize;
            if(line.y <= lastY){
                page = new PDPage(PDRectangle.LETTER);
                contentStream.close();
                document.addPage(page);
                contentStream = new PDPageContentStream(document, page);
                contentStream.beginText();
                contentStream.setFont(font, 12);
                contentStream.moveTextPositionByAmount( 100, 700 );
                contentStream.setLeading(leading);
                contentStream.newLineAtOffset(-25, 25);
            }
            offset = line.y;
            lastY = line.y;
            Text = line.text;
            leading = 1.5f * FontSize;
            contentStream.setLeading(leading);
            contentStream.setFont(font, FontSize);
            contentStream.newLineAtOffset(0, -offset);

            String[] words = Text.split(" ");
            for (int i = 0; i < words.length; i++) {
                int halfLen = words[i].length() / 2;
                int len = words[i].length();
                if(len % 2 != 0){
                    halfLen += 1;
                }
                String firstHalf = words[i].substring(0, halfLen);
                String secondHalf = words[i].substring(halfLen, len);
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, FontSize);
                contentStream.showText(firstHalf);
                contentStream.setFont(PDType1Font.HELVETICA, FontSize);
                contentStream.showText(secondHalf);
                contentStream.showText(" ");
            }

            contentStream.newLineAtOffset(0, offset);
        }
        contentStream.endText();
        contentStream.close();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        document.save(os);
        String base64 = Base64.getEncoder().encodeToString(os.toByteArray());
//        document.save("HelloWorld.pdf");
        document.close();
        return base64;
    }
}