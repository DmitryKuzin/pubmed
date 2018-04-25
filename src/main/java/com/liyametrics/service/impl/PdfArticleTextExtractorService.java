package com.liyametrics.service.impl;

import com.liyametrics.utils.InfoMessageDecorator;
import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.rauschig.jarchivelib.Archiver;
import org.rauschig.jarchivelib.ArchiverFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;


@Service
public class PdfArticleTextExtractorService {

    private String PATH_FOR_ARTICLE_SOURCES;

    public PdfArticleTextExtractorService() {
        PATH_FOR_ARTICLE_SOURCES = System.getProperty("user.dir")+"/";
    }


    public String extractText(String PMID, String url) {

        downloadFile(PMID, url);
        unGzipFile(PMID, PMID+"temp-arr.tar.gz");
        File pdfReport = findPdfInFolder(PMID);
        if(pdfReport!=null) {
            String s = parseFromPdf(pdfReport);
            if(s!=null) {
                clean(PMID);
                return s;
            }
        }

        return null;
    }

    private void downloadFile(String PMID, String url) {
        String toFile = "temp-arr.tar.gz";

        try {

            InfoMessageDecorator.print("Скачиваем архив со статьей с PubMed API");
            //connectionTimeout, readTimeout = 10 seconds
            FileUtils.copyURLToFile(new URL(url), new File(PMID+toFile), 10000, 10000);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void unGzipFile(String PMID, String fileName) {
        //ungzip and read pdf from it

        File archive = new File(PATH_FOR_ARTICLE_SOURCES+fileName);
        File destination = new File(PMID);

        Archiver archiver = ArchiverFactory.createArchiver("tar", "gz");
        try {
            archiver.extract(archive, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File findPdfInFolder(String PMID) {
        File file = new File(PATH_FOR_ARTICLE_SOURCES+PMID+"/"+PMID);
        File[] files = file.listFiles();
        File result = null;
        for(File f: files){
            if(f.getName().contains("pdf")) {
                result = f;
            }
        }

        return result;
    }

    private String parseFromPdf(File file) {
        PDFParser parser = null;
        PDDocument pdDoc = null;
        COSDocument cosDoc = null;
        PDFTextStripper pdfStripper;
        String parsedText = null;
        try {
            RandomAccessFile raf = new RandomAccessFile(file, "r");

            parser = new PDFParser(raf);
            parser.parse();
            cosDoc = parser.getDocument();
            pdfStripper = new PDFTextStripper();
            pdDoc = new PDDocument(cosDoc);
            parsedText = pdfStripper.getText(pdDoc);
//            System.out.println(parsedText.replaceAll("[^A-Za-z0-9. ]+", ""));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (cosDoc != null)
                    cosDoc.close();
                if (pdDoc != null)
                    pdDoc.close();
            } catch (Exception e1) {
                e.printStackTrace();
            }

        }

        return parsedText;
    }

    public void clean(String PMID) {

        String tarDir = PMID+"temp-arr.tar.gz";
        String dirDir = PATH_FOR_ARTICLE_SOURCES + PMID;

        File tar = new File(tarDir);
        File dir = new File(dirDir);

        try {
            delete(tar);
            delete(dir);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void delete(File file)
            throws IOException{

        if(file.isDirectory()){

            //directory is empty, then delete it
            if(file.list().length==0){

                file.delete();
                System.out.println("Directory is deleted : "
                        + file.getAbsolutePath());

            }else{

                //list all the directory contents
                String files[] = file.list();

                for (String temp : files) {
                    //construct the file structure
                    File fileDelete = new File(file, temp);

                    //recursive delete
                    delete(fileDelete);
                }

                //check the directory again, if empty then delete it
                if(file.list().length==0){
                    file.delete();
                    System.out.println("Directory is deleted : "
                            + file.getAbsolutePath());
                }
            }

        }else{
            //if file, then delete it
            file.delete();
            System.out.println("File is deleted : " + file.getAbsolutePath());
        }
    }

}
