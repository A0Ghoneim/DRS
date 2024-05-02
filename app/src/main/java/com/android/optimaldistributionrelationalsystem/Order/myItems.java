package com.android.optimaldistributionrelationalsystem.Order;

public class myItems {
    public String OrderId;
    public String StoreName;
    public String Quantity;
    Double lat;
    Double longt;
    public int SalePrice;


    public myItems() {

    }// end constractor

    public myItems(String orderid, String storename, String quantty,Double lat,Double longt,
                   int salePrice ) {
        OrderId = orderid;
        StoreName = storename;
        Quantity = quantty;
        SalePrice = salePrice;
        this.lat=lat;
        this.longt=longt;



    }// end constractor

 /*   public List<myItems> GetItems() {
        List<myItems> lstItems = new ArrayList<myItems>();

        lstItems.add(new myItems("1001", "store1", "756 Unit", 8577
        ));

        lstItems.add(new myItems("1002", "store2", "234 Unit", 1945
        ));

        lstItems.add(new myItems("1003", "store3", "978 Unit", 6788
        ));
        lstItems.add(new myItems("1004", "store4", "956 Unit", 7677
        ));

        lstItems.add(new myItems("1005", "store5", "898 Unit", 2437
        ));

        lstItems.add(new myItems("1006", "store6", "945 Unit", 4567
        ));
        lstItems.add(new myItems("1007", "store7", "876 Unit", 9807
        ));

        lstItems.add(new myItems("1008", "store8", "564 Unit", 3576
        ));

        lstItems.add(new myItems("1009", "store9", "743 Unit", 8764
        ));

        lstItems.add(new myItems("10010", "store10", "454 Unit", 1567
        ));

        lstItems.add(new myItems("10011", "store11", "550 Unit", 4897
        ));

        lstItems.add(new myItems("10012", "store12", "492 Unit", 6896
        ));

        return lstItems;
    }
*/
}//end class item




