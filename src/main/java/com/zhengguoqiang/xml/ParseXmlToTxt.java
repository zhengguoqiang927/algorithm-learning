package com.zhengguoqiang.xml;

import org.dom4j.*;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ParseXmlToTxt {

    private static int top_index = 0;
    private static int bottom_index = 0;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    private static final SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");

    public static String xmlToTxt(String filePath){
        String filename = "";
        BufferedWriter bw = null;
        try {
            File xmlFile = new File(filePath);

            SAXReader reader = new SAXReader();
            Document document = reader.read(xmlFile);

            StringBuilder header = new StringBuilder();
            StringBuilder content = new StringBuilder();

            Element rootElement = document.getRootElement();
            Element barcode = rootElement.element("Barcode");
            Element testprogram = rootElement.element("Testprogram");
            Element status = rootElement.element("Status");
            Element operator = rootElement.element("Operator");
            Element startTime = rootElement.element("StartTime");
            Element endTime = rootElement.element("EndTime");
            Element testerName = rootElement.element("TesterName");

            header.append("\t").append(barcode.getName().toUpperCase());
            content.append("\t").append(barcode.getText());
            filename += barcode.getName();

            header.append("\t").append("INDEX");
            if (testprogram.getText().toLowerCase().endsWith("top")){
                content.append("\t").append(++top_index);
            }else if (testprogram.getText().toLowerCase().endsWith("bot")){
                content.append("\t").append(++bottom_index);
            }

            Date startDate = sdf.parse(startTime.getText());
            Date endDate = sdf.parse(endTime.getText());
            header.append("\t").append("DATE");
            content.append("\t").append(sdfDate.format(startDate));
            filename += startTime.getText();

            header.append("\t").append("S.TIME");
            content.append("\t").append(sdfTime.format(startDate));

            header.append("\t").append("E.TIME");
            content.append("\t").append(sdfTime.format(endDate));

            header.append("\t").append("CYCLE");
            content.append("\t").append("10");

            header.append("\t").append("JOB");
            content.append("\t").append("309000215998B4-Almond");

            header.append("\t").append("RESULT");
            content.append("\t").append("5.5-BOT");

            header.append("\t").append("USER");
            content.append("\t").append("GOOD");

            header.append("\t").append("LOTINFO");
            content.append("\t").append("SV");

            header.append("\t").append("MACHINE");
            content.append("\t").append("KOHYOUNG");

            String tail = "PadID\tComponent ID\t  Size X\t  Size Y\t T\tVolume(%)\tHeight(um)\tOffsetX(mm)\tOffsetY(mm)\t Area(%)\tVolume(um3)\tArea(um2)\t  Result\tPinNumber\t Barcode\t  PCB ID\t     Job\t  Date  \t Time \t   Shape\tArray\t   Panel\tPosX(mm)\tPosY(mm)\t    Spec\tPad Verification\tTolerance\tOffsetX(%)\tOffsetY(%)\tLibrary_Name\tHeight(%)";
            header.append("\n").append(content).append("\n").append(tail);
            System.out.println(header.toString());

            filename += ".txt";
            File targetFile = new File("/Users/zhengguoqiang/Documents/" + filename);
            if (!targetFile.exists()) {
                boolean newFile = targetFile.createNewFile();
                System.out.println("创建文件结果：" + newFile);
            }
            bw = new BufferedWriter(new FileWriter(targetFile.getAbsoluteFile()));
            bw.write(header.toString());
            bw.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null){
                    bw.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return filename;
    }

    public static void main(String[] args) {

    }
}
