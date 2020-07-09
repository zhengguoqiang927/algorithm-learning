package com.zhengguoqiang.xml;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ResourceListener {
    private static ExecutorService pool = Executors.newFixedThreadPool(5);
    private WatchService watchService;
    private String listenePath;

    private ResourceListener(String path) {
        try {
            watchService = FileSystems.getDefault().newWatchService();
            this.listenePath = path;
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void start() {
        pool.execute(new Listener(watchService,this.listenePath));
    }

    public static void addListener(String listenePath) throws IOException {
        ResourceListener listener = new ResourceListener(listenePath);
        Path path = Paths.get(listenePath);
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
                        if (path.endsWith(".xml")){
                            System.out.println("[" + rootPath + "/" + event.context() + "]文件发生了[" + event.kind() + "]事件");
                            ParseXmlToTxt.xmlToTxt(rootPath+ "/" + event.context());
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

    public static void main(String[] args) {
        try {
            ResourceListener.addListener("/Users/zhengguoqiang/Documents");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
