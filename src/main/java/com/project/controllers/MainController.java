package com.project.controllers;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Controller
public class MainController {
    @RequestMapping(value="/", method = RequestMethod.GET)
    public ModelAndView index(){
        String inputFile = "C:/Users/admin/Desktop/conclusion.html";
        String outputFile = "C:/Users/admin/Desktop/conclusion.pdf";
        generatePDF(inputFile, outputFile);

        System.out.println("Done!");

        return new ModelAndView("index");
    }

    public static void generatePDF(String inputHtmlPath, String outputPdfPath){
        try {
            String url = new File(inputHtmlPath).toURI().toURL().toString();
            System.out.println("URL: " + url);

            OutputStream out = new FileOutputStream(outputPdfPath);

            //Flying Saucer part
            ITextRenderer renderer = new ITextRenderer();

            renderer.setDocument(url);
            renderer.layout();
            renderer.createPDF(out);

            out.close();
        } catch (DocumentException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
