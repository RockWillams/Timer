package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

public class ExampleDaoGenerator {
    public static void main(String[] args) throws Exception {


        Schema schema = new Schema(1, "com.entity");

/*
        addNote(schema);
        addCustomerOrder(schema);
*/

        Entity user = addUser( schema);
        addLog(schema);
        addVersion(schema);
        addEvent(schema);


        new DaoGenerator().generateAll(schema, "./mobile/src/main/java");
    }

    //simple start//////////////////////////////////////////////
    private static void addNote(Schema schema) {
        Entity note = schema.addEntity("Note");
        note.addIdProperty();
        note.addStringProperty("text").notNull();
        note.addStringProperty("comment");
        note.addDateProperty("date");
    }

    private static void addCustomerOrder(Schema schema) {
        Entity customer = schema.addEntity("Customer");
        customer.addIdProperty();
        customer.addStringProperty("name").notNull();

        Entity order = schema.addEntity("Order");
        order.setTableName("ORDERS"); // "ORDER" is a reserved keyword
        order.addIdProperty();
        Property orderDate = order.addDateProperty("date").getProperty();
        Property customerId = order.addLongProperty("customerId").notNull().getProperty();
        order.addToOne(customer, customerId);

        ToMany customerToOrders = customer.addToMany(order, customerId);
        customerToOrders.setName("orders");
        customerToOrders.orderAsc(orderDate);
    }

 //simple end   ///////////////////////////////////////////////




    private  static Entity addUser(Schema schema){
        Entity user = schema.addEntity("User");
        user.addIdProperty().autoincrement().primaryKeyAsc();
        user.addStringProperty("name").notNull();
        user.addStringProperty("password");
        user.addStringProperty("key");
        user.addStringProperty("status");
        user.addStringProperty("watchSerial");
        return  user;
    }

    private static void addVersion(Schema schema){
        Entity ver = schema.addEntity("Version");
        ver.addIdProperty();
        ver.addStringProperty("latestVersion");
        ver.addStringProperty("status");//0 old //1 latest
        ver.addStringProperty("createTime");
    }

    private static void addEvent(Schema schema){
        Entity event = schema.addEntity("Event");
        event.addIdProperty();
        event.addStringProperty("oid");
        event.addStringProperty("targetTime");
        event.addStringProperty("content");
        event.addStringProperty("notifyTime");
        event.addStringProperty("mode");
        event.addStringProperty("status");// 0 未过期开提醒 1 未过期关提醒 2 已过期（历史）
    }

    private  static  void addLog(Schema schema){
        Entity log = schema.addEntity("log");
        log.addIdProperty();
        log.addStringProperty("operType");
        log.addStringProperty("operTime").getProperty();
        log.addStringProperty("status");

    }





}