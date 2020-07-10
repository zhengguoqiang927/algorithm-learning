package com.zhengguoqiang.xml;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class ParseXmlToTxt {

    private static final Logger logger = LoggerFactory.getLogger(ParseXmlToTxt.class);

    private static final AtomicLong top_index = new AtomicLong(0);
    private static final AtomicLong bottom_index = new AtomicLong(0);
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    private static final SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");

    public static void xmlToTxt(String xmlSourceFilePath, String txtFilePath, String xmlTargetFilePath) {
        //生成的txt文件名
        String filename = "";
        BufferedWriter bw = null;
        try {
            //创建xml源文件
            File xmlFile = new File(xmlSourceFilePath);

            SAXReader reader = new SAXReader();
            Document document = reader.read(xmlFile);

            //txt表头
            StringBuilder header = new StringBuilder();
            //txt内容
            StringBuilder content = new StringBuilder();

            Element rootElement = document.getRootElement();
            Element barcode = rootElement.element("Barcode");
            Element testprogram = rootElement.element("Testprogram");
            Element status = rootElement.element("Status");
            Element operator = rootElement.element("Operator");
            Element startTime = rootElement.element("StartTime");
            Element endTime = rootElement.element("EndTime");
            Element testerName = rootElement.element("TesterName");

            //barcode为空不处理文件
            if (StringUtils.isEmpty(barcode.getText())) return;

            header.append("\t").append(barcode.getName().toUpperCase());
            content.append("\t").append(barcode.getText());
            filename += barcode.getName();

            header.append("\t").append("INDEX");
            if (testprogram.getText().toLowerCase().endsWith("top")) {
                content.append("\t").append(top_index.addAndGet(1));
            } else if (testprogram.getText().toLowerCase().endsWith("bot")) {
                content.append("\t").append(bottom_index.addAndGet(1));
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
            content.append("\t").append((endDate.getTime() - startDate.getTime()) / 1000);

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

            //txt表尾
            String tail = "PadID\tComponent ID\t  Size X\t  Size Y\t T\tVolume(%)\tHeight(um)\tOffsetX(mm)\tOffsetY(mm)\t Area(%)\tVolume(um3)\tArea(um2)\t  Result\tPinNumber\t Barcode\t  PCB ID\t     Job\t  Date  \t Time \t   Shape\tArray\t   Panel\tPosX(mm)\tPosY(mm)\t    Spec\tPad Verification\tTolerance\tOffsetX(%)\tOffsetY(%)\tLibrary_Name\tHeight(%)";
            header.append("\n").append(content).append("\n").append(tail);
            System.out.println(header.toString());

            filename += ".txt";
            File targetFile = new File(txtFilePath + File.separator + filename);
            if (!targetFile.exists()) {
                boolean newFile = targetFile.createNewFile();
                System.out.println(txtFilePath + File.separator + filename + " 文件创建结果：" + (newFile ? "成功" : "失败"));
            }
            bw = new BufferedWriter(new FileWriter(targetFile.getAbsoluteFile()));
            bw.write(header.toString());
            bw.flush();


            moveFile(xmlSourceFilePath,xmlTargetFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 文件转储
     * @param sourcePath 源文件路径(含文件名)
     * @param targetPath 目标路径
     */
    public static void moveFile(String sourcePath,String targetPath){
        //xml源文件转储
        Path source = FileSystems.getDefault().getPath(sourcePath);
        Path target = FileSystems.getDefault().getPath(targetPath);
        try {
            Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("源文件转储失败,源文件路径：" + sourcePath);
        }
    }

    /**
     * 批处理时直接执行该main方法
     * @param args
     */
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(4);
        //批处理
        String xmlSourceFilePath = PropertyUtil.getProperty("xmlSourceFilePath");
        String txtFilePath = PropertyUtil.getProperty("txtFilePath");
        String xmlTargetFilePath = PropertyUtil.getProperty("xmlTargetFilePath");
        File dir = new File(xmlSourceFilePath);
        if (!dir.isDirectory()){
            System.out.println("批处理失败，源文件目录配置错误！！！");
            logger.info("批处理失败，源文件目录配置错误！！！");
            return;
        }
        File[] files = dir.listFiles();
        if (files == null) {
            System.out.println("该目录下无文件，请查看目录是否正确！！！");
            return;
        }
        for (File file:files){
            if (file.getName().endsWith(".xml")){
                pool.execute(() -> {
                    ParseXmlToTxt.xmlToTxt(xmlSourceFilePath + "/" + file.getName(),
                            txtFilePath,xmlTargetFilePath + "/" + file.getName());
                });
            }
        }
    }
}
