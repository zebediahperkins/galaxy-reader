package com.example.galaxyreader;

import com.example.galaxyreader.parser.Line;
import com.example.galaxyreader.parser.PDFLineReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {
	public static void main(String[] args) throws Exception {
		// Uncomment to test pdfbox
//		for (Line line : new PDFLineReader().getLines(0, "src/test/resources/Sample1.pdf")) {
//			System.out.println("SIZE: " + line.fontSize);
//			System.out.println("TEXT: " + line.text);
//			System.out.println();
//		}
		SpringApplication.run(ServerApplication.class, args);
	}
}
