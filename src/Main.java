import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Arrays;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        String username,password,database_name;
        username = "root";
        password = "@ditya1211";
        database_name = "project";

//        System.out.println("Enter username of your sql database");
//        username = sc.next();
//        System.out.println("Enter password of your sql database");
//        password = sc.next();
//        System.out.println("Enter name of database where Project is saved");
//        database_name = sc.next();




//        Backend ob1 = new Backend(username,password,database_name);
//
//        String streetNamessArray[] = ob1.customer_getStreetNames();
//        System.out.println(Arrays.toString(streetNamessArray));


        new UserPageScreen();

        // new comment




//        String property_id = "100";
//        String agent_id = "1901011";
//        String date = "20/06/2021";
//        String property_type = "sell";
//        String buyer_name = "aman";
//        String street="G.S.Roadd";
//        String numberOfBedrooms="";
//        String typeOfProperty="rent";
////        String minimumPrice="3000";
////        String maximumPrice="100000";
////
////        ob1.customer_searchProperties(street,numberOfBedrooms,typeOfProperty,minimumPrice,maximumPrice);
//
////        System.out.println(ob1.agent_agentProfile("1901022"));
//
////        ob1.agent_addProperty(property_id,agent_id,date,property_type,buyer_name);
////        System.out.println(ob1.agent_agentProfile("1901044"));
////        ob1.agent_propertiesSold("1901044");
////        ob1.agent_propertiesRented("1901022");
////        ob1.admin_addProperty(property_id,"111","111","111","city_1111","1111",agent_id,"111","111","yes","rent",
////                "1211");
////        ob1.admin_addAgent(agent_id,"agent_name","agent_email","asd",new String[]{"34324324","75434354"});
//
////        ob1.admin_addOwner("owner_name","64543",property_id);
////        ob1.admin_availablePropertiesForSell();
//        boolean r = ob1.checkLogin("1901011"," wrong passowrd");
//        System.out.println(r);

    }
}
