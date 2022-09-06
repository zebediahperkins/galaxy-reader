package com.example.galaxyreader.parser;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

public class PDFLineReader extends PDFTextStripper {
    private List<Line> lines;

    public PDFLineReader() throws Exception {
        ;
    }

    public List<Line> getLines(byte[] input) throws Exception {
        lines = new ArrayList<>();
        PDDocument document = PDDocument.load(input);
        setSortByPosition(true);
        setStartPage(0);
        setEndPage(document.getNumberOfPages());
        Writer listenerStream = new OutputStreamWriter(new ByteArrayOutputStream());
        writeText(document, listenerStream);
        document.close();
        return lines;
    }

    // NOTE: Don't call this, it's a listener
    @Override
    protected void writeString(String text, List<TextPosition> textPositions) throws IOException {
        lines.add(new Line(text, textPositions.get(0).getFontSize(), textPositions.get(0).getX(), textPositions.get(0).getY()));
    }
}