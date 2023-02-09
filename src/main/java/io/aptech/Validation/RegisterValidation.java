package io.aptech.Validation;

public class RegisterValidation {
    public static String checkFullName(String fullName){

        String flag = "YES";
        String regex = "^([a-zA-Z ]+){6,50}$";
        if(!fullName.matches(regex)){

            flag="NO";
        }
        return flag;
    }
    public static String checkUsername(String username){
        String flag = "YES";
        String regex = "^([a-zA-Z ]+){6,50}$";

        if(!username.matches(regex)){

            flag="NO";
        }
        return flag;
    }
    public static String checkPassword(String password){
        String flag = "YES";
        String regex = "^([a-zA-Z]+[0-9]+)$";
        if(!password.matches(regex)){
            flag="NO";
        }
        return flag;
    }
    public static String checkEmail(String email){
        String flag = "YES";
        String regex = "^[a-zA-Z]+[0-9]+@[a-z.]{3,10}[a-z]{2,3}$";
        if(!email.matches(regex)){
            flag="NO";
        }
        return flag;
    }
    public static String checkPhone(String phone){
        String flag = "YES";
        String regex = "^((033|016|09|012)[0-9]+)$";
        if(!phone.matches(regex)){
            flag="NO";
        }
        return flag;
    }
}
