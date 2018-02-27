package com.huangtianci.commonfunction.uploadanddownload.service;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.InputStream;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 写这个东西主要是最近做了一个联通的数据迁移工作，他们就是这样导出的数据，所以我们写了这个代码。
 * 还有一个就是网上n 多都是半成品，代码不能直接运行，我这个代码能够直接运行。
 * java poi 之sax 解析10万级大数量数据，其实百万，千万都是可以的，
 * 但是想到这么大的数据一般不会使用excel 进行导入数据的行为，所以我采用了这个方法只是针对于10万级数据，
 * 另外,虚拟机的堆内存需要设置大一些，不然会报内存溢出。
 * 支持多个sheet 数据一起导入：暂时设置为只读取第一个sheet
 *
 * 支持回调处理
 */
public class MyExcel2007Process {
    private static List<Map<String, String>> headList;
    private static List<Map<String, String>> dataListT;
    private int currentRow = 0;
    private final String filename;
    private static Map<String, String> map;
    static char[] strChar;

    /**
     * 构造方法
     */
    public MyExcel2007Process(String filename) throws Exception {
        headList = new ArrayList<>();
        dataListT = new ArrayList<>();
        if (StringUtils.isEmpty(filename)) throw new Exception("文件名不能空");
        this.filename = filename;
        processSheet();
    }

    /**
     * 指定获取第一个sheet
     *
     * @throws Exception
     */
    private void processSheet() throws Exception {
        OPCPackage pkg = OPCPackage.open(filename);
        XSSFReader r = new XSSFReader(pkg);
        SharedStringsTable sst = r.getSharedStringsTable();
        XMLReader parser = fetchSheetParser(sst);
        Iterator<InputStream> it = r.getSheetsData();
        //while (it.hasNext()) 遍历所有sheet
        if (it.hasNext()) { //遍历第一个sheet
            map = null;
            InputStream sheet1 = it.next();
            InputSource sheetSource = new InputSource(sheet1);
            parser.parse(sheetSource);
            sheet1.close();
            callBack();
        }
    }

    /**
     * 加载sax 解析器
     *
     * @param sst
     * @return
     * @throws SAXException
     */
    private XMLReader fetchSheetParser(SharedStringsTable sst) throws SAXException {
        XMLReader parser =
                XMLReaderFactory.createXMLReader(
                        "com.sun.org.apache.xerces.internal.parsers.SAXParser"
                );
        ContentHandler handler = new PagingHandler(sst, this);
        parser.setContentHandler(handler);
        return parser;
    }

    /**
     * See org.xml.sax.helpers.DefaultHandler javadocs
     */
    private class PagingHandler extends DefaultHandler {

        private MyExcel2007Process myExcel2007Process;
        private SharedStringsTable sst;
        private String lastContents;
        private boolean nextIsString;
        private String index = null;
        private PagingHandler(SharedStringsTable sst, MyExcel2007Process myExcel2007Process) {
            this.sst = sst;
            this.myExcel2007Process = myExcel2007Process;
        }

        /**
         * 开始元素 （获取key 值）
         */
        @Override
        public void startElement(String uri, String localName, String name,
                                 Attributes attributes) throws SAXException {
            if (name.equals("c")) {
                index = attributes.getValue("r");
                //判断是否是新的一行
                if (Pattern.compile("^A[0-9]+$").matcher(index).find()) {
                    if (map != null && !map.isEmpty()) {
                        if("A2".equals(index)) {
                            headList.add(map);
                        } else {
                            dataListT.add(map);
                            if(dataListT.size() == 1000) {
                                myExcel2007Process.callBack();
                            }
                        }
                    }
                    map = new LinkedHashMap<>();
                    currentRow++;
                }
                String cellType = attributes.getValue("t");
                if (cellType != null && cellType.equals("s")) {
                    nextIsString = true;
                } else {
                    nextIsString = false;
                }
            }
            lastContents = "";
        }

        /**
         * 获取value
         */
        @Override
        public void endElement(String uri, String localName, String name)
                throws SAXException {
            if (nextIsString) {
                int idx = Integer.parseInt(lastContents);
                lastContents = new XSSFRichTextString(sst.getEntryAt(idx)).toString();
                nextIsString = false;
            }
            if (name.equals("v")) {
                map.put(index, lastContents);
            }
        }

        @Override
        public void characters(char[] ch, int start, int length)
                throws SAXException {
            lastContents += new String(ch, start, length);
        }

        @Override
        public void endDocument() throws SAXException {
            if (map != null && !map.isEmpty()) {
                dataListT.add(map);
            }
        }

    }

    public void callBack() {
        System.out.println("回调函数,处理了：" + dataListT.size() + "条数据");
        clearAll();
    }

    public void clearAll() {
        dataListT.clear();
    }

    public List<Map<String, String>> getMyDataList() throws Exception {
        return dataListT;
    }

    public List<Map<String,String>> getHeadList() {
        return headList;
    }

    public static void main(String[] args) throws Exception {
        Date startTime = new Date();
        MyExcel2007Process reader = new MyExcel2007Process("D://9万多数据.xlsx");
        List<Map<String, String>> headList = reader.getHeadList();
        List<Map<String, String>> list = reader.getMyDataList();
        System.out.println(list.size());
        /*int i = 0;
        for(Map<String, String> map : list) {
            System.out.print("第" + i + "行数据：");
            i++;
            for(Map.Entry<String, String> entry : map.entrySet()) {
                System.out.print(entry.getKey() + ":" + entry.getValue() + ";");
            }
            System.out.println();
        }*/
        Date endTime = new Date();
        System.out.println("共用时：" + (endTime.getTime() - startTime.getTime()) + "毫秒");
    }
}