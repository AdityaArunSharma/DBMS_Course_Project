import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Backend
{
    Connection conn;
    public Backend(String username,String password,String database_name) throws  Exception
    {
        String url = "jdbc:mysql://Localhost:3306/"+database_name+"?autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true";
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn= DriverManager.getConnection(url, username, password);
        System.out.println("Connected to Database "+database_name);
    }





    // functions related to user - Customer starts

    public boolean addAND(boolean featurePresent[],int index)
    {
        for(int x=0;x<index;x++)
        {
            if(featurePresent[x])
            {
                return true;
            }
        }
        return  false;
    }

    public boolean isString(int x)
    {
        if(x==0 || x==2)
        {
            return true;
        }
        return false;
    }


    public void customer_searchProperties(String street,String numberOfBedrooms,String typeOfProperty,String minimumPrice,String maximumPrice) throws Exception
    {
        String query = "SELECT * FROM property ";


        street = street.trim();
        numberOfBedrooms = numberOfBedrooms.trim();
        typeOfProperty = typeOfProperty.trim();
        minimumPrice = minimumPrice.trim();
        maximumPrice = maximumPrice.trim();

        int n = 5;
//        String featureList[] = new String[n];
//        featureList[0] = street;
//        featureList[1] = numberOfBedrooms;
//        featureList[2] = typeOfProperty;
//        featureList[3] = minimumPrice;
//        featureList[4] = maximumPrice;
        boolean featurePresent[] = new boolean[n];
        Arrays.fill(featurePresent,false);

        //boolean street_isEmpty = false,numberOfBedrooms_isEmpty = false,typeOfProperty_isEmpty = false,minimumPrice_isEmpty = false,maximumPrice_isEmpty = false;
        boolean whereAdded = false;
        if(!street.isEmpty())
        {
            if(!whereAdded)
            {
                whereAdded = true;
                query+=" WHERE ";
            }
            query+=" street="+" '"+street+"' ";
            featurePresent[0] = true;

        }
        if(!numberOfBedrooms.isEmpty())
        {
            if(!whereAdded)
            {
                whereAdded = true;
                query+=" where ";
            }
            if(addAND(featurePresent,1))
            {
                query+=" and ";
            }
            query+=" no_of_bedrooms="+numberOfBedrooms;
            featurePresent[1] = true;
        }
        if(!typeOfProperty.isEmpty())
        {
            if(!whereAdded)
            {
                whereAdded = true;
                query+=" WHERE ";
            }
            if(addAND(featurePresent,2))
            {
                query+=" and ";
            }
            query+=" property_type = '"+typeOfProperty+"'";
            featurePresent[2] = true;
        }
        if(!minimumPrice.isEmpty())
        {
            if(!whereAdded)
            {
                whereAdded = true;
                query+=" WHERE ";
            }
            if(addAND(featurePresent,3))
            {
                query+=" and ";
            }
            if(typeOfProperty.equalsIgnoreCase("rent"))
            {
                query+=" rent>="+minimumPrice;
            }
            else if(typeOfProperty.equalsIgnoreCase("sell"))
            {
                query+=" selling_price>="+minimumPrice;
            }
            else
            {
                System.out.println("Error : type of property not selected");
                return;
            }
            featurePresent[3] = true;
        }
        if(!maximumPrice.isEmpty())
        {
            if(!whereAdded)
            {
                whereAdded = true;
                query+=" WHERE ";
            }
            if(addAND(featurePresent,4))
            {
                query+=" and ";
            }
            if(typeOfProperty.equalsIgnoreCase("rent"))
            {
                query+=" rent<="+maximumPrice;
            }
            else if(typeOfProperty.equalsIgnoreCase("sell"))
            {
                query+=" selling_price<="+maximumPrice;
            }
            else
            {
                System.out.println("Error : type of property not selected");
                return;
            }
            featurePresent[4] = true;
        }
        System.out.println(query);
        Statement stmt=conn.createStatement();

        new QueryResultDisplayer(stmt.executeQuery(query));
        stmt.close();


    }


    public String[] customer_getStreetNames() throws Exception
    {
        Statement stmt = conn.createStatement();
        String query = "select distinct street from property";
        ResultSet rs = stmt.executeQuery(query);
        String query_countRows = "select count(distinct street) from property";
        Statement stmt_countRows = conn.createStatement();
        ResultSet rs_countRows = stmt_countRows.executeQuery(query_countRows);
        rs_countRows.next();
        int numberRows = rs_countRows.getInt(1);
        String streetName[] = new String[numberRows];
        int index = 0;
        while(rs.next())
        {
            streetName[index++] = rs.getString(1);
        }
        return streetName;
    }

    public void closeDatabase() throws Exception
    {
        conn.close();
        System.out.println("Connection terminated to database");
    }

    // ends

    // functions related to user - Agent starts

    public Agent agent_agentProfile(String agentID) throws Exception // returns null for wrong input
    {
        agentID = agentID.trim();
        if(agentID.isEmpty())
        {
            System.out.println("Error : Empty agent id detected");
            return null;
        }
        Statement stmt = conn.createStatement();
        String query = "select * from agent where agent_id = "+agentID;
        ResultSet  rs = stmt.executeQuery(query);
        if (!rs.isBeforeFirst() ) {
            System.out.println("Error : No matching agent id in database found");
            return null;
        }
        rs.next();
        Agent agent = new Agent(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
        Statement stmt_contactNumbers = conn.createStatement();
        String query_contactNumber = "select phone_no from agent_contact where agent_id = "+agentID;
        System.out.println(query_contactNumber);
        ResultSet rs_contactNumber = stmt_contactNumbers.executeQuery(query_contactNumber);
        while(rs_contactNumber.next())
        {
            String contact = rs_contactNumber.getString(1);
            agent.contactNumber.add(contact);
        }

        Statement stmt_totalSold = conn.createStatement();
        Statement stmt_totalRented = conn.createStatement();
        String query_totalSold = "select selling_price from selling_details as SD join property using(property_id) where SD.agent_id = "+agentID+" and SD.property_type = 'sell'";
        String query_totalRented = "select rent from selling_details as SD join property using(property_id) where SD.agent_id = "+agentID+" and SD.property_type = 'rent'";

        ResultSet rs_totalSold = stmt_totalSold.executeQuery(query_totalSold);

        if(rs_totalSold.isBeforeFirst())
        {
            while(rs_totalSold.next())
            {
                agent.totalSold+=rs_totalSold.getInt(1);
            }
        }

        ResultSet rs_totalRented = stmt_totalRented.executeQuery(query_totalRented);

        if(rs_totalRented.isBeforeFirst())
        {
            while(rs_totalRented.next())
            {
                agent.totalRented+=rs_totalRented.getInt(1);
            }
        }


        return agent;
    }

    public void agent_propertiesAssigned(String agentID) throws Exception
    {
        agentID = agentID.trim();
        if(agentID.isEmpty())
        {
            System.out.println("Error : Empty agent id detected");
            return;
        }
        Statement stmt = conn.createStatement();
        String query = "select * from property where agent_id = "+agentID;
        new QueryResultDisplayer(stmt.executeQuery(query));
    }

    public void agent_propertiesRented(String agentID) throws Exception
    {
        agentID = agentID.trim();
        if(agentID.isEmpty())
        {
            System.out.println("Error : Empty agent id detected");
            return;
        }
        Statement stmt = conn.createStatement();
        String  query = "select * from selling_details as SD join property using(property_id) where SD.agent_id = "+agentID+" and SD.property_type = 'rent'";
        new QueryResultDisplayer(stmt.executeQuery(query));
    }

    public void agent_propertiesSold(String agentID) throws Exception
    {
        agentID = agentID.trim();
        if(agentID.isEmpty())
        {
            System.out.println("Error : Empty agent id detected");
            return;
        }
        Statement stmt = conn.createStatement();
        String  query = "select * from selling_details as SD join property using(property_id) where SD.agent_id = "+agentID+" and SD.property_type = 'sell'";
        new QueryResultDisplayer(stmt.executeQuery(query));
    }

    public void agent_clients(String agentID) throws Exception
    {
        agentID = agentID.trim();
        if(agentID.isEmpty())
        {
            System.out.println("Error : Empty agent id detected");
            return;
        }
        Statement stmt = conn.createStatement();
        String query = "select name,contact_no from owner natural join property where agent_id = "+agentID;
        new QueryResultDisplayer(stmt.executeQuery(query));
    }

    public boolean agent_addProperty(String property_id,String agent_id,String data,String property_type,String buyer_name) throws Exception // returns false for failed action
    {
        property_id = property_id.trim();
        agent_id = agent_id.trim();
        data = data.trim();
        property_type = property_type.trim();
        buyer_name = buyer_name.trim();
        try
        {
            if(property_id.isEmpty() || agent_id.isEmpty() || data.isEmpty() || property_type.isEmpty() || buyer_name.isEmpty())
            {
                System.out.println("Error : Empty fields detected");
                return false;
            }
            List<String> propertiesAssigned = new ArrayList<>();
            Statement stmt_propertyAssigned = conn.createStatement();
            String query_propertyAssigned = "select property_id from property where agent_id = "+agent_id;
            ResultSet rs_propertyAssigned = stmt_propertyAssigned.executeQuery(query_propertyAssigned);
            while(rs_propertyAssigned.next())
            {
                propertiesAssigned.add(rs_propertyAssigned.getString(1));
            }
            boolean propertyExist = false;
            for(String pID : propertiesAssigned)
            {
                if(pID.equalsIgnoreCase(property_id))
                {
                    propertyExist=true;
                    break;
                }
            }
            if(!propertyExist)
            {
                System.out.println("Error : Property not assigned to agent");
                return false;
            }
            Statement stmt_type_available = conn.createStatement();
            String query_type_available = "select property_type,availability from property where property_id = '"+property_id+"'";
            ResultSet rs_type_available = stmt_type_available.executeQuery(query_type_available);
            rs_type_available.next();
            String type = rs_type_available.getString(1);
            String availability = rs_type_available.getString(2);
            if(!type.equalsIgnoreCase(property_type))
            {
                System.out.println("Error : Wrong property type given");
                return false;
            }
            if(availability.equalsIgnoreCase("no"))
            {
                System.out.println("Error : Property not available");
                return false;
            }
            Statement stmt = conn.createStatement();
            String query = "insert into selling_details values ("+property_id+","+agent_id+",'"+data+"','"+property_type+"','"+buyer_name+"')";
            int n = stmt.executeUpdate(query);
            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery("select * from selling_details");
            Statement stmt_changeAvailability = conn.createStatement();
            String query_changeAvailability = "update property set availability = 'no' where property_id = '"+property_id+"'";
            stmt_changeAvailability.executeUpdate(query_changeAvailability);
        }
        catch (Exception e)
        {
            System.out.println("Error in insertion");
            return false;
        }
        return true;
    }

    // Agent Functions ends here

    // Functions related to Admin starts here

    public void admin_availablePropertiesForRent() throws Exception
    {
        Statement stmt = conn.createStatement();
        ResultSet rs =  stmt.executeQuery("select * from property where property_type = 'rent' and  availability like '%yes%' ");
        new QueryResultDisplayer(rs);
    }

    public void admin_availablePropertiesForSell() throws Exception
    {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from property where property_type = 'sell' and availability like '%yes%'");
        new QueryResultDisplayer(rs);
    }

    public boolean admin_addProperty(String property_id,String house_no,String street,String rent,String city,String selling_price,
                                  String agent_id,String pincode,String no_of_bedrooms,String availability,String property_type,String size)
    {
        property_id = property_id.trim();
        house_no = house_no.trim();
        street = street.trim();
        rent = rent.trim();
        city = city.trim();
        selling_price = selling_price.trim();
        agent_id = agent_id.trim();
        pincode = pincode.trim();
        no_of_bedrooms = no_of_bedrooms.trim();
        availability = availability.trim();
        property_type = property_type.trim();
        size = size.trim();
        if( property_id.isEmpty() || house_no.isEmpty() || street.isEmpty() || city.isEmpty() || agent_id.isEmpty() || pincode.isEmpty() ||
            no_of_bedrooms.isEmpty() || availability.isEmpty() || property_type.isEmpty() || size.isEmpty())
        {
            System.out.println("Error : Empty field detected");
            return false;
        }
        if(property_type.equalsIgnoreCase("rent") && rent.isEmpty())
        {
            System.out.println("Error : Rent field is empty");
            return false;
        }
        if(property_type.equalsIgnoreCase("sell") && selling_price.isEmpty())
        {
            System.out.println("Error : Selling price is empty");
            return false;
        }
        if(property_type.equalsIgnoreCase("rent"))
        {
            selling_price = "null";
        }
        else if(property_type.equalsIgnoreCase("sell"))
        {
            rent = "null";
        }
        else
        {
            System.out.println("Wrong property type given");
            return false;
        }
        try {

            Statement stmt = conn.createStatement();
            String query = "insert into property values ('"+property_id+"',"+house_no+",'"+street+"','"+city+"',"+pincode+","+agent_id+",'"+property_type+"','"+
                                                            availability+"',"+no_of_bedrooms+","+size+","+rent+","+selling_price+")";
            stmt.executeUpdate(query);
        }
        catch (Exception e)
        {
            System.out.println("Insertion failed\n"+e);
            return false;
        }
        return true;
    }


    public boolean admin_addAgent(String agent_id,String agent_name,String email,String password,String contact_number[])
    {
        agent_name = agent_name.trim();
        agent_id = agent_id.trim();
        email = email.trim();
        password = password.trim();
        if( agent_id.isEmpty() || agent_name.isEmpty() || email.isEmpty() || password.isEmpty() || contact_number.length==0)
        {
            System.out.println("Error : Empty field detected");
            return false;
        }
        try {
            Statement stmt = conn.createStatement();
            String query = "insert into agent values ("+agent_id+",'"+agent_name+"','"+email+"','"+password+"')";
            stmt.executeUpdate(query);
            Statement stmt2 = conn.createStatement();
            String query2 = "";
            for(int x=0;x<contact_number.length;x++)
            {
                query2 = "insert into agent_contact values ('"+contact_number[x]+"',"+agent_id+")";
                stmt2.executeUpdate(query2);
            }
        }
        catch (Exception e)
        {
            System.out.println("Insertion failed "+e);
            return false;
        }
        return true;
    }

    public boolean admin_addOwner(String name,String contact_no,String propert_id)
    {
        name = name.trim();
        contact_no = contact_no.trim();
        propert_id = propert_id.trim();
        if(name.isEmpty() || contact_no.isEmpty() || propert_id.isEmpty())
        {
            System.out.println("Error : Empty field detected");
            return false;
        }
        try
        {
            Statement stmt = conn.createStatement();
            String query = "insert into owner values ('"+name+"','"+contact_no+"',"+propert_id+")";
            stmt.executeUpdate(query);
        }
        catch (Exception e)
        {
            System.out.println("Insertion Failed "+e);
            return false;
        }
        return true;
    }

    // fuctions of admin ends here


    public boolean checkLogin(String username,String password,String mode)
    {
        username = username.trim();
        password = password.trim();
        System.out.println("Backend : Checking for mode "+mode);
        if(username.isEmpty() || password.isEmpty())
        {
            System.out.println("Error : Empty fields detected");
            return false;
        }
        if(mode.equalsIgnoreCase(Constants.mode_admin))
        {
            System.out.println("backend : mode = "+mode);
            if(password.equalsIgnoreCase(Constants.adminPassword) && username.equalsIgnoreCase(Constants.adminUsername))
            {
                System.out.println("Right login details for admin");
                return true;
            }
            return false;
        }
        else if(mode.equalsIgnoreCase(Constants.mode_agent))
        {
            try
            {
                Statement stmt_agentIDs = conn.createStatement();
                String query_agentIds = "select agent_id from agent";
                ResultSet rs_agentIds = stmt_agentIDs.executeQuery(query_agentIds);
                List<String> agentIds = new ArrayList<>();
                while(rs_agentIds.next())
                {
                    agentIds.add(rs_agentIds.getString(1));
                }
                boolean correctUsername = false;
                for(String agent : agentIds)
                {
                    if(agent.equalsIgnoreCase(username))
                    {
                        correctUsername = true;
                        break;
                    }
                }
                if(!correctUsername)
                {
                    System.out.println("Error : Wrong username");
                    return false;
                }
                Statement stmt_getPassword = conn.createStatement();
                String query_getPassword = "select password from agent where agent_id = '"+username+"'";
                ResultSet rs_getPassword = stmt_getPassword.executeQuery(query_getPassword);
                rs_getPassword.next();
                String correctPassword = rs_getPassword.getString(1);
//            System.out.println(correctPassword);
                if(!correctPassword.equalsIgnoreCase(password))
                {
                    System.out.println("Error : Wrong password");
                    return false;
                }
                System.out.println("Right login details for agent");
                return true;
            }
            catch (Exception e)
            {
                System.out.println("Error : Login");
                return false;
            }
        }
        return false;
    }


}
