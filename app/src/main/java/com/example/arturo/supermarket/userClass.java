//package com.example.arturo.supermarket;
//
///**
// * Created by Arturo on 10/11/2015.
// */
//
//public class userClass {
//
//
//    public static String strSeparator = "__,__";
//    public static String convertArrayToString(String[] array){
//        String str = "";
//        for (int i = 0;i<array.length; i++) {
//            str = str+array[i];
//            // Do not append comma at the end of last element
//            if(i<array.length-1){
//                str = str+strSeparator;
//            }
//        }
//        return str;
//    }
//    public static String[] convertStringToArray(String str){
//        String[] arr = str.split(strSeparator);
//        return arr;
//    }
//
//
//    int _id;
//    private String userName;
//    private String password;
//    private String email;
//    String sArray;
//    product[] array;
//
//    public userClass(){
//     this.email="";
//     this.password="";
//     this.userName="";
//    }
//    public userClass(String usuario,String pass,String emaill){
//        userName=usuario;
//        password = pass;
//        email=emaill;
//        product[] array = new product[10];
//    }
//    public userClass(int id,String usuario,String pass,String emaill,String array){
//        _id=id;
//        userName=usuario;
//        password = pass;
//        email=emaill;
//        sArray = array;
//    }
//
//    public userClass(int id,String usuario,String pass,String emaill){
//        _id=id;
//        userName=usuario;
//        password = pass;
//        email=emaill;
//        product[] array = new product[10];
//    }
//
//    public int get_id() {
//        return _id;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public String getsArray() {
//        return sArray;
//    }
//
//    public product[] getArray() {
//        return array;
//    }
//
//
//    public String getStringArray(){
//        return convertArrayToString(productsArrayIntoStringArray(array));
//    }
//
//
//    public String productToString(product product){
//        String a,b;
//        a=product.getName();
//        b=String.valueOf(product.getLifeSpan());
//        return a+b;
//    }
//
//    public product stringToProduct (String str){
//        product product = new product("",00);
//        product.setName(str.substring(0,str.length()-2));
//        product.setLifeSpan(Integer.parseInt(str.substring(str.length()-2)));
//        return product;
//    }
//
//    public String[] productsArrayIntoStringArray (product[] pr){
//
//        String[] result = new String[pr.length];
//        String str;
//
//        if(pr.length==0) return result;
//
//        for(int i=0; i < pr.length;i++){
//            str = productToString(pr[i]);
//            result[i] = str;
//        }
//
//        return result;
//    }
//
//    public product[] stringArrayIntoProductArray(String[] st){
//        product[] pr = new product[st.length];
//        product product;
//
//        if(st.length == 0) return pr;
//
//        for(int i=0;i<st.length;i++){
//            product = stringToProduct(st[i]);
//            pr[i] = product;
//        }
//
//        return pr;
//    }
//
//    public void set_id(int _id) {
//        this._id = _id;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public void setArray(product[] array) {
//        this.array = array;
//    }
//
//    public void setSArray(String array) {this.sArray=array;}
//
//
//    public void insertIntoProducts(product[] arr,int i){
//        boolean flag = true;
//        while(flag) {
//            int count = 0;
//            if(count > 10) flag = false;
//            if (array[count] == null && count < 10)
//            {
//                array[count] = arr[i];
//                flag = false;
//            }
//            else
//                count++;
//        }
//    }
//}
