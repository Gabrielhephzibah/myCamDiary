package com.enyata.camdiary.ui.deliveries.history;

public class DeliveryHistoryHeader implements  DeliveryItemInterface {

        private  String date;

        public DeliveryHistoryHeader(String date){
            this.date = date;
        }

    @Override
    public boolean isSection() {
        return true;
    }

    @Override
    public String getMyName() {
        return null;
    }

    @Override
    public String getItems() {
        return null;
    }

    @Override
    public String getNumber() {
        return null;
    }

    @Override
    public String getItemId() {
        return null;
    }

    public String getDate() {
            return date;
        }

    @Override
    public String getProductCount() {
        return null;
    }


}
