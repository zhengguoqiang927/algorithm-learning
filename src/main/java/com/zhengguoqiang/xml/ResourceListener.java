package com.zhengguoqiang.xml;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ResourceListener {

    private static final Logger logger = LoggerFactory.getLogger(ResourceListener.class);

    private static final ExecutorService pool = Executors.newFixedThreadPool(5);
    //监听文件变化服务
    private WatchService watchService;
    //监听xml文件的路径，从config.properties文件中的xmlSourceFilePath属性获取
    private String xmlSourceFilePath;
    //txt文件路径，从config.properties文件中的txtFilePath属性获取
    private String txtFilePath;
    //原xml文件转存地址，从config.properties文件中的xmlTargetFilePath属性获取
    private String xmlTargetFilePath;

    private ResourceListener(String xmlSourceFilePath,String txtFilePath,String xmlTargetFilePath) {
        try {
            watchService = FileSystems.getDefault().newWatchService();
            this.xmlSourceFilePath = xmlSourceFilePath;
            this.txtFilePath = txtFilePath;
            this.xmlTargetFilePath = xmlTargetFilePath;
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void start() {
        pool.execute(new Listener(watchService,this.xmlSourceFilePath));
    }

    public static void addListener(String xmlSourceFilePath,String txtFilePath,String xmlTargetFilePath) throws IOException {
        ResourceListener listener = new ResourceListener(xmlSourceFilePath,txtFilePath,xmlTargetFilePath);
        Path path = Paths.get(xmlSourceFilePath);
        path.register(listener.watchService,StandardWatchEventKinds.ENTRY_CREATE);
    }

    class Listener implements Runnable {
        private WatchService watchService;
        private String rootPath;

        public Listener(WatchService watchService, String rootPath) {
            this.watchService = watchService;
            this.rootPath = rootPath;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    WatchKey watchKey = watchService.take();
                    List<WatchEvent<?>> watchEvents = watchKey.pollEvents();
                    for (WatchEvent<?> event:watchEvents){
                        String path = event.context().toString();
                        boolean flag = path.startsWith("_");
                        if (flag) System.out.println("[" +path + "]文件命名不规范，不进行处理！！！");
                        if (path.endsWith(".xml") && !flag){
                            System.out.println("[" + rootPath + "/" + event.context() + "]文件发生了[" + event.kind() + "]事件");
                            ParseXmlToTxt.xmlToTxt(rootPath+ "/" + event.context(),
                                    txtFilePath,xmlTargetFilePath + "/" + event.context());
                        }
                    }
                    watchKey.reset();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                try{
                    watchService.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 应用程序入口，监听目录xml文件的创建并执行生成txt文件
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            String xmlSourceFilePath = PropertyUtil.getProperty("xmlSourceFilePath");
            String txtFilePath = PropertyUtil.getProperty("txtFilePath");
            String xmlTargetFilePath = PropertyUtil.getProperty("xmlTargetFilePath");
            if (StringUtils.isAnyEmpty(xmlSourceFilePath,txtFilePath,xmlTargetFilePath)){
                System.out.println("配置文件中xml和txt文件路径配置错误！！！");
                logger.info("配置文件中xml和txt文件路径配置错误！！！");
                return;
            }
            ResourceListener.addListener(xmlSourceFilePath,txtFilePath,xmlTargetFilePath);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("main函数报错",e);
        }
    }
}
