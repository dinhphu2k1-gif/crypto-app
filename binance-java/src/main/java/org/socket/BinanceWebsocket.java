package org.socket;

import com.binance.connector.client.impl.WebsocketStreamClientImpl;
import org.json.JSONObject;
import org.storage.MysqlConnect;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BinanceWebsocket {

    public static void main(String[] args) {
        WebsocketStreamClientImpl client = new WebsocketStreamClientImpl(); // defaults to live exchange unless stated.
        MysqlConnect mysqlConnect = new MysqlConnect();

        int stream = client.tradeStream("btcusdt",  ((event) -> {
            System.out.println(event);
            JSONObject data = new JSONObject(event);

            String eventType = data.getString("e");
            long eventTime = data.getLong("E");
            String symbol = data.getString("s");
            long tradeId = data.getLong("t");
            String price = data.getString("p");
            String quantity = data.getString("q");
            long buyOrderId = data.getLong("b");
            long sellOrderId = data.getLong("a");
            long tradeTime = data.getLong("T");
            boolean isBuy = data.getBoolean("m");
            boolean ignore = data.getBoolean("M");

            System.out.println("event type: " + eventType);
            System.out.println("event time: " + eventTime);
            System.out.println("symbol: " + symbol);
            System.out.println("trade id: " + tradeId);
            System.out.println("price:" + price);
            System.out.println("quantity: " + quantity);
            System.out.println("buy order id: " + buyOrderId);
            System.out.println("sell order id: " + sellOrderId);
            System.out.println("trade time: " + tradeTime);
            System.out.println("is buy: " + isBuy);
            System.out.println("ignore: " + ignore);

            List<Object> objectList = new ArrayList<>();
            objectList.add(eventType);
            objectList.add(eventTime);
            objectList.add(symbol);
            objectList.add(tradeId);
            objectList.add(price);
            objectList.add(quantity);
            objectList.add(buyOrderId);
            objectList.add(sellOrderId);
            objectList.add(tradeTime);
            objectList.add(isBuy);
            objectList.add(ignore);
            mysqlConnect.insert(objectList);

            System.out.println("\n");
        }));


//        wsStreamClient.closeAllConnections();

    }
}
