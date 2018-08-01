// package com.ddmc.commonutils.common;
//
// import java.io.BufferedReader;
// import java.io.File;
// import java.io.FileReader;
// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Random;
// import java.util.Timer;
// import java.util.TimerTask;
//
// import org.apache.avro.Schema;
// import org.apache.avro.generic.GenericData;
// import org.apache.avro.generic.GenericRecord;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
//
/// **
// *
// * <pre>
// * Description
// * Copyright: Copyright (c) 2018
// * Company: 叮咚
// * Author: lichao
// * Version: 1.0
// * Create at: 2018年7月28日 下午9:21:37
// *
// * 修改历史:
// * 日期 作者 版本 修改描述
// * ------------------------------------------------------------------
// *
// * </pre>
// */
// public class SendReceipts {
// private static Logger LOG = LoggerFactory.getLogger(SendReceipts.class);
// private static String path = SendReceipts.class.getResource("/").getPath();
// private static String areaJsonString;
// private static String city;
// private static String cityKey;
// private static String province;
// private static String provinceKey;
// private static int separator;
//
// private static String phonePrefix;
// // private static final Random rnd = new Random();
//
// private static String[] payers = { "Merchants", "Individuals" };
// private static String[] managers = { "david", "josen", "fab", "simon",
// "banana", "tom", "scott", "ekrn", "sunshine",
// "lily", "kudu", "scala", "spark", "flume", "storm", "kafka", "avro", "linux"
// };
// private static String[] terminalTypes = { "RNM", "CNM", "RNM", "GNM", "CNJ",
// "GNJ", "RNJ", "GNM", "CNM" };
// private static String[] stores = { "连锁店", "分营店", "工厂店", "会员店", "直销店" };
// private static String[] items = { "面包", "酒", "油", "牛奶", "蔬菜", "猪肉", "牛肉",
// "羊肉", "曲奇", "手机", "耳机", "面粉", "大米", "糖",
// "苹果", "茶叶", "书", "植物", "玩具", "床", "锅", "牙膏", "洗衣粉", "酱油", "金鱼", "干货" };
// private static String[] itemsType = { "食物", "酒水", "饮料", "日用品", "电子", "数码",
// "娱乐", "家俱" };
//
// public static void main(String[] args) {
//
// Timer timer = new Timer();
// timer.schedule(new TimerTask() {
// @Override
// public void run() {
//
// Random rnd = new Random();
//
// ProduceReceipts pr = new ProduceReceipts();
// areaJsonString = pr.readJSON(path, "area.json");
//
// String transactionID = System.currentTimeMillis() + "" +
// Math.round(Math.random() * 9000 + 1000);
// String transactionDate = System.currentTimeMillis() + "";
// String taxNumber = Math.round(Math.random() * 9000 + 1000) + " "
// + Math.round(Math.random() * 9000 + 1000) + " " + Math.round(Math.random() *
// 9000 + 1000) + " "
// + Math.round(Math.random() * 9000 + 1000);
// String invoiceId = System.currentTimeMillis() + "";
// String invoiceNumber = Math.round(Math.random() * 900000000 + 100000000) +
// "";
// String invoiceDate = System.currentTimeMillis() + "";
// List<Area> listArea = pr.listArea(areaJsonString);
// int idx = rnd.nextInt(listArea.size());
// String provinceID = listArea.get(idx).getProvinceID();
// String provinceName = listArea.get(idx).getProvinceName();
// String cityID = listArea.get(idx).getCityID();
// String cityName = listArea.get(idx).getCityName();
// String telephone = provinceID + "-" + Math.round(Math.random() * 9000 + 1000)
// + " "
// + Math.round(Math.random() * 9000 + 1000);
// int managerSize = managers.length;
// String manger = managers[rnd.nextInt(managerSize)];
// int payerSize = payers.length;
// String payer = payers[rnd.nextInt(payerSize)];
// String operator = "OP" + Math.round(Math.random() * 90000 + 10000);
// int terminalTypeSize = terminalTypes.length;
// String terminalNumber = terminalTypes[rnd.nextInt(terminalTypeSize)]
// + Math.round(Math.random() * 90000 + 10000);
// String account = pr.StringReplaceWithStar(Math.round(Math.random() * 9000 +
// 1000) + " "
// + Math.round(Math.random() * 9000 + 1000) + " " + Math.round(Math.random() *
// 9000 + 1000) + " "
// + Math.round(Math.random() * 9000 + 1000));
// String tcNumber = Math.round(Math.random() * 9000 + 1000) + " "
// + Math.round(Math.random() * 9000 + 1000) + " " + Math.round(Math.random() *
// 9000 + 1000) + " "
// + Math.round(Math.random() * 9000 + 1000) + "";
//
// File file = new File(path + "receipts.avsc");
//
// String line = null;
//
// BufferedReader reader = null;
// try {
// reader = new BufferedReader(new FileReader(file));
//
// while ((line = reader.readLine()) != null) {
// // System.out.println("========>" + line);
// }
// reader.close();
// } catch (IOException e) {
// e.printStackTrace();
// } finally {
// if (reader != null) {
// try {
// reader.close();
// } catch (IOException e1) {
// }
// }
// }
// try {
// // 获得整个Schema
// Schema schema = new Schema.Parser().parse(new File(path + "receipts.avsc"));
//
// GenericRecord record = new GenericData.Record(schema);
//
// // 获取schema中的字段
//
// int storesSize = stores.length;
//
// // 获取店面的Schema
// Schema.Field storeField = schema.getField("store");
// Schema storeSchema = storeField.schema();
// GenericRecord storeRecord = new GenericData.Record(storeSchema);
//
// String storeNumber = Math.round(Math.random() * 9000 + 1000) + "";
// String address = provinceName + cityName;
// String storeName = provinceName + cityName + stores[rnd.nextInt(storesSize)];
//
// storeRecord.put("store_number", storeNumber);
// storeRecord.put("store_name", storeName);
// storeRecord.put("address", address);
//
// int itemsSize = items.length;
// int itemsTypeSize = itemsType.length;
//
// List<GenericRecord> productRecordList = new ArrayList<GenericRecord>();
// // 获取product的schema
// Schema.Field productField = schema.getField("products");
// Schema productSchema = productField.schema();
//
// for (int i = 0; i < 10; i++) {
// String itemName = items[rnd.nextInt(1000) % itemsSize];
// String itemType = itemsType[rnd.nextInt(1000) % itemsTypeSize];
// String quantity = String.valueOf(rnd.nextInt(100));
// String price = String.valueOf(rnd.nextFloat() * 100);
// String discount = String.valueOf(rnd.nextFloat());
//
// GenericRecord productRecord = new GenericData.Record(productSchema);
//
// productRecord.put("item", itemName);
// productRecord.put("item_type", itemType);
// productRecord.put("quantity", quantity);
// productRecord.put("price", price);
// productRecord.put("discount", discount);
// productRecordList.add(productRecord);
// }
//
// record.put("transaction_id", transactionID);
// record.put("transaction_date", transactionDate);
// record.put("invoice_id", invoiceId);
// record.put("invoice_number", invoiceNumber);
// record.put("telephone", telephone);
// record.put("payer", payer);
// record.put("store", storeRecord);
// record.put("operator", operator);
// record.put("terminal_number", terminalNumber);
// record.put("products", productRecordList);
// record.put("account", account);
// record.put("tc_number", terminalNumber);
//
// LOG.info(record.toString());
//
// } catch (IOException e) {
// e.printStackTrace();
// }
// }
// }, 0, 1000);
// }
// }